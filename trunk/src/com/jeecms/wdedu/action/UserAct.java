package com.jeecms.wdedu.action;

import com.alibaba.fastjson.JSON;
import com.bcloud.msg.http.HttpSender;
import com.jeecms.common.security.encoder.PwdEncoder;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.*;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.JcUserGroup;
import com.jeecms.wdedu.entity.TBaseVrcards;
import com.jeecms.wdedu.service.CommonSvc;
import com.jeecms.wdedu.service.UserSvc;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.*;

/**
 * 用户操作
 */
@Controller
public class UserAct {

    public static final String TPLDIR_USER = "user";
    public static final String USER_REGISTER = "user_register";
    public static final String FORGET_PASSWORD = "forget_password";
    public static final String ACTIVATE_CARD = "activate_card";
    private static final Logger LOG = LoggerFactory.getLogger(UserAct.class);

    @Autowired
    private CmsUserMng cmsUserMng;
    @Autowired
    private UnifiedUserMng unifiedUserMng;
    @Autowired
    private UserSvc userSvc;
    @Autowired
    private CommonSvc commonSvc;
    @Autowired
    private SessionProvider session;
    @Autowired
    private PwdEncoder pwdEncoder;

    private JcUserGroup jcUserGroup;

    /**
     * 用户注册页面
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/user_register.jspx")
    public String userRegister(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_USER, USER_REGISTER);
    }

    /**
     * 用户注册/忘记密码提交
     *
     * @param username
     * @param password
     * @param captcha
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/user_submit.jspx")
    public void userSubmit(String username, String password, String captcha,
                           HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        Map<String, Object> returnMap = new HashMap<String, Object>();

        if (StringUtils.isEmpty(username)) {
            returnMap.put("success", false);
            returnMap.put("submit_msg", "请输入手机号");
//            returnResp(response, returnMap);
            return;
        }
        if (StringUtils.isEmpty(password)) {
            returnMap.put("success", false);
            returnMap.put("submit_msg", "请输入密码");
//            returnResp(response, returnMap);
            return;
        }
        if (StringUtils.isEmpty(captcha)) {
            returnMap.put("success", false);
            returnMap.put("submit_msg", "请输入验证码");
//            returnResp(response, returnMap);
            return;
        }
        //判断验证码是否正确
        Serializable captchaId = session.getAttribute(request, "captcha");
        Serializable usernames = session.getAttribute(request, "username");
        if (!captcha.equals(String.valueOf(captchaId)) || !username.equals(String.valueOf(usernames))) {
            returnMap.put("success", false);
            returnMap.put("submit_msg", "验证码输入不正确！");
//            returnResp(response, returnMap);
            return;
        }

        try {
            // 注册普通用户
            Map<String, String> attr = new HashMap<>();
            attr.put("groupId", "1");
//            attr.put("major_type_id","2");
//            attr.put("province_id","17");
            cmsUserMng.registerMember(username, null, password, RequestUtils.getIpAddr(request), 1, false, new CmsUserExt(), attr);
            CmsUser cmsUser = cmsUserMng.findByUsername(username);
            JcUserGroup jcUserGroup1 = new JcUserGroup();
            jcUserGroup1.setGroupId(1);
            jcUserGroup1.setUserId(cmsUser.getId());
            commonSvc.save(jcUserGroup1);
            returnMap.put("success", true);
            returnMap.put("submit_msg", "注册成功！");
//            returnResp(response, returnMap);

        } catch (ConstraintViolationException e) {
            // 账号已存在，则修改密码。
            CmsUser cmsUser = cmsUserMng.findByUsername(username);
            unifiedUserMng.update(cmsUser.getId(), password, null);

            String updateError = "UPDATE jo_user\n" +
                    "SET error_time = NULL , error_count = 0 , error_ip = NULL\n" +
                    "WHERE user_id = '" + cmsUser.getId() + "' AND username = '" + username + "' ";
            commonSvc.executeSql(updateError);
            returnMap.put("success", true);
            returnMap.put("submit_msg", "此账号已存在，密码已更新！");
//            returnResp(response, returnMap);

        }

        //自动登录
        returnMap = userSvc.login(username, password,returnMap, request, response);
        returnResp(response, returnMap);
    }

    /**
     * 忘记密码
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/forget_password.jspx")
    public String forgetPassword(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_USER, FORGET_PASSWORD);
    }

    /**
     * 验证是否为卡号登录
     *
     * @param cardId
     * @param request
     * @param response
     * @param model
     * @throws IOException
     */
    @RequestMapping(value = "/check_card.jspx", method = RequestMethod.POST)
    public void checkCard(String cardId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        Map<String, Object> returnMap = new HashMap<String, Object>();

        if (StringUtils.isEmpty(cardId)) {
            returnMap.put("success", false);
            returnMap.put("msg", "请输入账号");
            returnResp(response, returnMap);
            return;
        }

        // 根据卡号检查卡是否存在
        TBaseVrcards card = commonSvc.get(TBaseVrcards.class, Long.valueOf(cardId));
        returnMap.put("cardId", cardId);
        // 卡号不存在
        if (card == null) {
            returnMap.put("success", false);
            returnResp(response, returnMap);
        } else {
            returnMap.put("success", true);
            returnResp(response, returnMap);
        }

    }

    /**
     * 使用卡号登录
     *
     * @param cardId
     * @param password
     * @param request
     * @param response
     * @param model
     */
    @RequestMapping(value = "/login_with_card.jspx", method = RequestMethod.POST)
    public void loginWithCard(String cardId, String password, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        Map<String, Object> returnMap = new HashMap<String, Object>();

        if (StringUtils.isEmpty(cardId)) {
            returnMap.put("success", false);
            returnMap.put("msg", "请输入账号");
            returnResp(response, returnMap);
            return;
        }
        if (StringUtils.isEmpty(password)) {
            returnMap.put("success", false);
            returnMap.put("msg", "请输入密码");
            returnResp(response, returnMap);
            return;
        }

        // 根据卡号检查卡是否存在
        TBaseVrcards card = commonSvc.get(TBaseVrcards.class, Long.valueOf(cardId));
        // 卡号不存在
        if (card == null) {
            returnMap.put("success", false);
            returnMap.put("msg", "该卡暂时未开通，请联系025-98765432开通！");
            returnResp(response, returnMap);
            return;
        }
        returnMap.put("cardId", cardId);
        //验证卡是否已被激活
        if (!card.getActiveStatus()) {
            // 未激活
            // 校验卡密码
            if (!card.getPassword().equals(password)) {
                returnMap.put("success", false);
                returnMap.put("msg", "请确认卡密码");
                returnResp(response, returnMap);
                return;
            } else {
                // 跳转到卡激活界面
                returnMap.put("success", true);
                returnMap.put("msg", "show");
                /*      returnMap.put("msg", "activate_card.jspx?cardId=" + cardId);*/
                returnResp(response, returnMap);
                return;
            }

        } else {
            // 已激活
            // 卡已被激活,取出关联用户信息
            Integer userId = card.getBndUserId();
            if (userId == null) {
                returnMap.put("success", false);
                returnMap.put("msg", "该卡已激活，但未关联用户手机，请联系客服处理！");
                returnResp(response, returnMap);
                return;
            }
//            CmsUser user = cmsUserMng.findById(userId);
            UnifiedUser user = unifiedUserMng.findById(userId);
            if (user == null) {
                returnMap.put("success", false);
                returnMap.put("msg", "该卡已激活，关联用户id：" + userId + ",不存在此用户，请联系客服处理！");
                returnResp(response, returnMap);
                return;
            }
            if (!pwdEncoder.isPasswordValid(user.getPassword(), password)) {
                returnMap.put("success", false);
                returnMap.put("msg", "密码错误");
                returnResp(response, returnMap);
                return;
            }

            //自动登录
            returnMap = userSvc.login(user.getUsername(), password,returnMap, request, response);


            if ((boolean) returnMap.get("success")) {
                returnMap.remove("msg");
                returnMap.put("msg", "/wdedu");
            }
            returnResp(response, returnMap);
        }
    }

    /**
     * 激活卡
     *
     * @param cardId
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/activate_card.jspx", method = RequestMethod.GET)
    public String activeCard(String cardId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        model.addAttribute("cardId", cardId);

        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_USER, ACTIVATE_CARD);
    }

    /**
     * 激活卡
     *
     * @param cardId
     * @param username
     * @param captcha
     * @param password
     * @param request
     * @param response
     * @param model
     */
    @RequestMapping(value = "/activate_card.jspx", method = RequestMethod.POST)
    public void activeCard(String cardId, String username, String captcha, String password,
                           HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        Map<String, Object> returnMap = new HashMap<String, Object>();

        if (StringUtils.isEmpty(username)) {
            returnMap.put("success", false);
            returnMap.put("msg", "请输入手机号");
            returnResp(response, returnMap);
            return;
        }
        if (StringUtils.isEmpty(password)) {
            returnMap.put("success", false);
            returnMap.put("msg", "请输入密码");
            returnResp(response, returnMap);
            return;
        }
        if (StringUtils.isEmpty(captcha)) {
            returnMap.put("success", false);
            returnMap.put("msg", "请输入验证码");
            returnResp(response, returnMap);
            return;
        }
        //判断验证码是否正确
        Serializable captchaId = session.getAttribute(request, "captcha");
        Serializable usernames = session.getAttribute(request, "username");
        if (!captcha.equals(String.valueOf(captchaId)) || !username.equals(String.valueOf(usernames))) {
            returnMap.put("success", false);
            returnMap.put("msg", "验证码输入不正确！");
            returnResp(response, returnMap);
            return;
        }

        //1、校验卡信息包含省份，有效性，是否被激活，是否关联用户，等信息
        TBaseVrcards card = commonSvc.get(TBaseVrcards.class, Long.valueOf(cardId));
        // 1.1、 卡号不存在
        if (card == null) {
            returnMap.put("success", false);
            returnMap.put("msg", "该卡暂时未开通，请联系025-98765432开通！");
            returnResp(response, returnMap);
            return;
        }
        //1.2、卡片省份
        if (null == card.getProvinceId() || card.getProvinceId() == 0) {
            returnMap.put("success", false);
            returnMap.put("msg", "该卡尚未启用，请联系客服进行卡号启用。");
            returnResp(response, returnMap);
            return;
        }
        //1.3、卡片有效性
        if (card.getLosedTime().before(new Date())) {
            returnMap.put("success", false);
            returnMap.put("msg", "亲，卡号已过期！激活时间：" + card.getLosedTime());
            returnResp(response, returnMap);
            return;
        }
        //1.4、卡片激活状态
        if (card.getActiveStatus() == true) {
            returnMap.put("success", false);
            returnMap.put("msg", "该卡已激活！欢迎使用志愿无忧系统，请前往用户中心设置会员资料。");
            returnResp(response, returnMap);
            return;
        }

        //2、同步属性
        CmsUser cmsUser = cmsUserMng.findByUsername(username);
        Map<String, String> attrs = new HashMap<>();
        String groupIds = "";
        if (cmsUser == null) {
            // 手机号未注册，添加手机用户，设置密码未卡号密码。
            attrs = new HashMap<String, String>();
            attrs.put("province_id", card.getProvinceId().toString());
            attrs.put("cardId", String.valueOf(card.getCardNo()));
//            attrs.put("major_type_id","2");
            groupIds = "1";
            groupIds += "," + String.valueOf(card.getGroupId());
            attrs.put("groupId", groupIds);

            cmsUserMng.registerMember(username, null, password, RequestUtils.getIpAddr(request), 1, false, new CmsUserExt(), attrs);

            cmsUser = cmsUserMng.findByUsername(username);
            JcUserGroup jcUserGroup1 = new JcUserGroup();
            jcUserGroup1.setGroupId(card.getGroupId());
            jcUserGroup1.setUserId(cmsUser.getId());
            commonSvc.save(jcUserGroup1);
        } else {
            // 手机号已经注册
            attrs = cmsUser.getAttr();
            attrs.put("province_id", card.getProvinceId().toString());
            attrs.put("cardId", String.valueOf(card.getCardNo()));

            Iterator<String> iter = attrs.keySet().iterator();
            while(iter.hasNext()){
                String key = iter.next();
                if("major_type_id".equals(key)){
                    iter.remove();
                }
            }

//            attrs.put("major_type_id","2");
            groupIds = String.valueOf(attrs.get("groupId"));
            groupIds += "," + String.valueOf(card.getGroupId());
            attrs.put("groupId", groupIds);

            cmsUserMng.updateMember(cmsUser.getId(), cmsUser.getEmail(), password, cmsUser.getDisabled(), cmsUser.getUserExt(), 1, null, attrs);
            JcUserGroup jcUserGroup1 = new JcUserGroup();
            jcUserGroup1.setGroupId(card.getGroupId());
            jcUserGroup1.setUserId(cmsUser.getId());
            commonSvc.save(jcUserGroup1);
        }

        //3、更新卡状态
        card.setActiveTime(new Timestamp(new Date().getTime()));
        card.setBndUserId(cmsUser.getId());
        card.setActiveStatus(true);
        commonSvc.saveOrUpdate(card);

        //4、登录
        PrincipalCollection principals = new SimplePrincipalCollection(username, username);
        WebSubject.Builder builder = new WebSubject.Builder(request, response);
        builder.authenticated(true);
        builder.principals(principals);
        WebSubject subject = builder.buildWebSubject();
        ThreadContext.bind(subject);

        //5、返回结果
        returnMap.put("success", true);
        returnResp(response, returnMap);
    }

    /**
     * 发送手机验证码
     *
     * @param phone
     * @param request
     * @param response
     * @param model
     */
    @RequestMapping(value = "/get_captcha.jspx")
    public void getCaptcha(String phone, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws UnsupportedEncodingException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 6; i++) {
            result += random.nextInt(10);
        }
        String uri = "http://send.18sms.com/msg/HttpBatchSendSM";//应用地址
        String account = "173kbz ";//示远账号
        String pswd = "B9pL6iPk";//示远密码
        String mobile = phone;//手机号码，多个号码使用","分割
        String content = "您好，您的验证码是：" + result;//短信内容，注意内容中的逗号请使用中文状态下的逗号
        // Alltodo: 2019/12/13 短信内容乱码处理
//        content=new String(content.getBytes(),"UTF-8");
        content = URLDecoder.decode(content, "UTF-8");
        boolean needstatus = true;//是否需要状态报告，需要true，不需要false
        String product = "";//产品ID(不用填写)
        String extno = "";//扩展码(不用填写)
        try {
            String returnString = HttpSender.batchSend(uri, account, pswd, mobile, content, needstatus, product, extno);
            System.out.println(returnString);
            //TODO 处理返回值,参见HTTP协议文档
        } catch (Exception e) {
            //TODO 处理异常
            e.printStackTrace();
        }

        // Alltodo: 2019/12/13 验证码输入框改名
        session.setAttribute(request, response, "username", mobile);
        session.setAttribute(request, response, "captcha", result);
    }


    /**
     * 统一返回方法
     *
     * @param response
     * @param returnMap
     */
    private void returnResp(HttpServletResponse response, Map<String, Object> returnMap) {
        try {
            String json = JSON.toJSONString(returnMap);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        } catch (IOException e) {
            LOG.error("IO流读写失败", e);
        }
    }

}
