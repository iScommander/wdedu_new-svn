package com.wdedu.action.admissionSvc;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.util.AliPayCommonUtil;
import com.bcloud.msg.http.HttpSender;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsGroup;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.action.AliPayAct;
import com.jeecms.wdedu.entity.*;
import com.jeecms.wdedu.service.ActivitySvc;
import com.jeecms.core.web.util.JsonWriteUtil;
import com.jeecms.wdedu.entity.JcUserGroup;
import com.jeecms.wdedu.entity.TBaseProducts;
import com.jeecms.wdedu.entity.WxPayDto;
import com.jeecms.wdedu.service.CommonSvc;
import com.jeecms.wdedu.service.ZytbSvc;
import com.jeecms.wdedu.service.impl.CommonsImpl;
import com.utils.CommonConstant;
import com.utils.PasswordUtil;
import com.utils.StringUtil;
import com.utils.StringUtils;
import com.utils.wxpay.WxpayUtil;
import com.wdedu.entity.TDataOnlineSignEntity;
import com.wdedu.service.ActiveApplySvc;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.alipay.config.AlipayConfig.*;
import static com.alipay.config.AlipayConfig.ALI_TIMEOUT_EXPRESS;
import static com.jeecms.common.page.SimplePage.cpn;

@Controller
@RequestMapping(value = "/admissonSvcAct")
public class AdmissonSvcAct {
    @Autowired
    private ActiveApplySvc activeApplySvc;
    @Autowired
    private ActivitySvc activitySvc;
    @Autowired
    private CommonSvc commonSvc;
    private List<TNActivity> tnActivities;
    private List<TNAward> awardList;
    private List<TNUser> tnUserList;
    private List<TNReferee> tnRefereeList;
    private List<TNScore> tnScoreList;
    private List<TNPatent> tnPatentList;
    private List<TNEducation> tnEducationList;
    private List<TNFamily> tnFamilyList;
    private List<TNSchool> tnSchoolList;
    private List<TBaseProvince> tBaseProvince;
    private List<TBaseProvince> tBaseProvince2;
    private List<TBaseHighshool> tBaseHighshoolList;
    private List<TBaseHighshool> tBaseHighshoolList1;
    private List<TBaseHighshool> tBaseHighshoolList2;
    private List<TScActiveTeacher> tScActiveTeacherList;
    @Autowired
    private CmsUserMng cmsUserMng;
    @Autowired
    private CommonSvc commonService;
    @Autowired
    private CommonsImpl commons;
    @Autowired
    private ZytbSvc zytbSvc;

    //支付宝回调地址
    public static String ALI_PAY_NOTIFY_URL = "http://47.100.123.15:80/wdedu/admissonSvcAct/alipayCallback.jspx";
    //支付宝回调地址2
    public static String ALI_PAY_NOTIFY_URL_NO_LOGIN = "http://47.100.123.15:80/wdedu/admissonSvcAct/alipayCallback2.jspx";
    //微信回调地址
    public static String WX_PAY_NOTIFY_URL = "http://47.100.123.15:80/wdedu/admissonSvcAct/wxBuyCallback.jspx";
    //微信回调地址2
    public static String WX_PAY_NOTIFY_URL_NO_LOGIN = "http://47.100.123.15:80/wdedu/admissonSvcAct/wxBuyCallback2.jspx";
    private static final Logger log = LoggerFactory.getLogger(AliPayAct.class);

    //    基本信息
    @RequestMapping(value = "/findbaseInfo.jspx")
    public String findbaseInfo(HttpServletRequest request, HttpServletResponse response, String province_id, String cityId, String quxianId, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        //判断角色是否可修改省份及文理科信息
        Set<CmsGroup> group = user.getGroups();
        String prem = "";
        if (group != null && group.size() > 0) {
            for (CmsGroup group1 : group) {
                String groupId = String.valueOf(group1.getId());
                String permQuery = "SELECT p.uri FROM jc_group_permission AS p WHERE p.group_id=" + groupId;
                List<Map<String, Object>> permList = commonSvc.findForJdbc(permQuery);
                for (Map<String, Object> permMap : permList) {
                    prem = String.valueOf(permMap.get("uri"));
                    if ("ShengfenShezhi:*".equals(prem)) {
                        model.addAttribute("pro", "yes");
                    }
                    if ("WenlikeSheshi:*".equals(prem)) {
                        model.addAttribute("major", "yes");
                    }
                }
            }
        }
        CmsGroup group1 = user.getGroup();
        if (group1 != null) {
            String groupId = String.valueOf(group1.getId());
            String permQuery = "SELECT p.uri FROM jc_group_permission AS p WHERE p.group_id=" + groupId;
            List<Map<String, Object>> permList = commonSvc.findForJdbc(permQuery);
            for (Map<String, Object> permMap : permList) {
                prem = String.valueOf(permMap.get("uri"));
                if ("ShengfenShezhi:*".equals(prem)) {
                    model.addAttribute("pro", "true");
                }
                if ("WenlikeSheshi:*".equals(prem)) {
                    model.addAttribute("major", "true");
                }
            }
        }

        //        用户省份文理科切换权限
        String provinceChange = "false";
        String majorChange = "false";
        if (user.getAttr().get("province_id") == null || user.getAttr().get("city_id") == null || user.getAttr().get("quxian_id") == null || user.getAttr().get("school_id") == null) {
            provinceChange = "true";
        }

        if (user.getAttr().get("major_type_id") == null) {
            majorChange = "true";
        }

        Set<CmsGroup> userGroups = user.getGroups();
        Iterator<CmsGroup> userGroupsIt = userGroups.iterator();
        while (userGroupsIt.hasNext()) {
            Integer groupId = userGroupsIt.next().getId();
            if (groupId == 4 || groupId == 6) {
                provinceChange = "true";
                majorChange = "true";
                break;
            }
        }

        model.addAttribute("provinceChange", provinceChange);
        model.addAttribute("majorChange", majorChange);

        //毕业年份
        String biyeQuery = "SELECT * FROM t_mgr_comm_code_mas AS a WHERE a.KEY = 'locationYear'";
        List<Map<String, Object>> biyeList = commonSvc.findForJdbc(biyeQuery);
        model.addAttribute("biyeList", biyeList);
        String hql = "FROM TBaseProvince WHERE provinceName = '江苏'";
        tBaseProvince = commonSvc.findByQueryString(hql);
        //省份
        tBaseProvince2 = commonSvc.loadAll(TBaseProvince.class);
        model.addAttribute("tBaseProvince2", tBaseProvince2);
        //城市
        String hql2 = "SELECT DISTINCT cityName ,cityId FROM TBaseHighshool WHERE provinceId = '" + user.getAttr().get("province_id") + "'";
        List<Map<String, Object>> cityList = commonSvc.findByQueryString(hql2);
        model.addAttribute("cityList", cityList);
        //区县
        String hql3 = "SELECT DISTINCT quxianName,quxianId  FROM TBaseHighshool WHERE city_id = '" + user.getAttr().get("city_id") + "'";
        tBaseHighshoolList1 = commonSvc.findByQueryString(hql3);
        model.addAttribute("tBaseHighshoolList1", tBaseHighshoolList1);
        //学校
        String hql4 = "SELECT DISTINCT schoolId,schoolName  FROM TBaseHighshool WHERE quxian_id = '" + user.getAttr().get("quxian_id") + "'";
        tBaseHighshoolList2 = commonSvc.findByQueryString(hql4);
        List<Object> provinces = commonSvc.findByQueryString("FROM TBaseProvince WHERE provinceId ='" + user.getAttr().get("province_id") + "'");
//        List<JcUserGroup> groupId = commonSvc.findListbySql("SELECT group_id FROM jc_user_group WHERE user_id = '"+user.getId()+"'",JcUserGroup.class);
        model.addAttribute("provinces", provinces);

        model.addAttribute("tBaseHighshoolList2", tBaseHighshoolList2);
        model.addAttribute("shenfen", user.getAttr().get("province_id"));
        String proHql = "FROM TBaseProvince WHERE provinceId ='" + user.getAttr().get("province_id") + "' ";
        TBaseProvince t = commonSvc.singleResult(proHql);
        //毕业年份
        String year = user.getAttr().get("biyeYear");
        String sqlYear = "SELECT * FROM `t_mgr_comm_code_mas` WHERE id='" + year + "'";
        List<Map<String, Object>> list = commonSvc.findForJdbc(sqlYear);
        model.addAttribute("shenfenName", t.getProvinceName());
        model.addAttribute("wenli", user.getAttr().get("major_type_id"));
        model.addAttribute("cityName", user.getAttr().get("city_id"));
        model.addAttribute("quxianName", user.getAttr().get("quxian_id"));
        model.addAttribute("schoolName", user.getAttr().get("school_id"));
//        model.addAttribute("biyeYear", list.get(0).get("name"));
        model.addAttribute("banji", user.getAttr().get("banji"));
        model.addAttribute("realName", user.getRealname());
        // Alltodo: 2020/1/2 毕业年份取动态数据
//        model.addAttribute("realName",user.getAttr().get("realName"));
        model.addAttribute("biyeYear", year);
        model.addAttribute("sex", user.getAttr().get("sex"));
        model.addAttribute("qq", user.getAttr().get("qq"));
        model.addAttribute("msn", user.getAttr().get("msn"));
        model.addAttribute("phone", user.getAttr().get("phone"));
        model.addAttribute("pid", user.getAttr().get("province_id"));
        model.addAttribute("mid", user.getAttr().get("major_type_id"));

        return FrontUtils.getTplPath(site.getSolutionPath(), "wdedu/hyzx", "huiyuanzhongxin");
    }

    //    修改密码
    @RequestMapping(value = "/changePassword.jspx")
    public String changePassword(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        return FrontUtils.getTplPath(site.getSolutionPath(), "wdedu/hyzx", "changepassword");
    }


    //讲座信息
    @RequestMapping(value = "/findActiveList.jspx")
    public String findActive(HttpServletRequest request, HttpServletResponse response,
                             Integer pageNo, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        String telephone = user.getUsername();
        String userName = user.getAttr().get("realName");
        List<TDataOnlineSignEntity> list = commonSvc.findByProperty(TDataOnlineSignEntity.class, "telephone", telephone);
        if (list != null && list.size() > 0) {
            model.addAttribute("list", list);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lastTime = formatter.format(user.getLastLoginTime());
        model.addAttribute("userName", userName);
        model.addAttribute("lastTime", lastTime);
        model.addAttribute("telephone", telephone);
        /* List<Map<String, Object>> list = activeApplySvc.findAppointment(telephone);*/
        Date date = new Date();
        String now = formatter.format(date);
        model.addAttribute("now", now);
        List<TScActiveDetail> myAct = activeApplySvc.myActive(telephone);
        if (myAct != null && myAct.size() > 0) {
            model.addAttribute("myAct", myAct);
        }

        return FrontUtils.getTplPath(site.getSolutionPath(), "wdedu/hyzx", "jiangzuoxinxi");
    }

    //服务信息
    @RequestMapping(value = "/findProducts.jspx")
    public String findProducts(HttpServletRequest request, HttpServletResponse response,
                               ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        String provinceId = user.getAttr().get("province_id");
        String telephone = user.getAttr().get("phone");
        String userName = user.getAttr().get("realName");
        Integer userId = user.getId();
        List<TBaseProducts> list = commonSvc.findByProperty(TBaseProducts.class, "provinceId", Integer.valueOf(provinceId));
        if (list != null && list.size() > 0) {
            model.addAttribute("list", list);
        }
        String sql = "SELECT * FROM t_yn_online_order WHERE user_id ='" + userId + "' and pay_state=1 ";
        List<TYnOnlineOrder> orderList = commonSvc.findListbySql(sql, TYnOnlineOrder.class);
        if (orderList != null && orderList.size() > 0) {
            model.addAttribute("orderList", orderList);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lastTime = formatter.format(user.getLastLoginTime());
        model.addAttribute("telephone", telephone);
        model.addAttribute("userName", userName);
        model.addAttribute("lastTime", lastTime);
        return FrontUtils.getTplPath(site.getSolutionPath(), "wdedu/hyzx", "wodefuwu");
    }

    //微信购买
    @RequestMapping(value = "/wxpay/buyCard.jspx")
    public void buyCardWx(HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer productId) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        CmsUser user = CmsUtils.getUser(request);
        TBaseProducts product = commonService.get(TBaseProducts.class, productId);
        Map<String, String> map = new HashMap();
        Integer userId = user.getId();
        String phone = user.getUsername();
        String sql = "SELECT * FROM t_yn_online_order WHERE user_id ='" + userId + "' and pay_state=1 AND product_id='" + product.getId() + "'";
        List<TYnOnlineOrder> orderList = commonSvc.findListbySql(sql, TYnOnlineOrder.class);
        if (orderList != null && orderList.size() > 0) {
            map.put("msg", "已购买");
        } else {
            // 生成预付ORderID
            String outTradeNo = WxpayUtil.getNonceStr();
            BigDecimal fee1 = (product.getDiscountCost().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP));
//            BigDecimal fee1 = (product.getCost().subtract(product.getDiscountCost()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP));
            String qr = qrPay(outTradeNo, "127.0.0.1", phone, String.valueOf(fee1), product);
            map.put("result", qr);

            TYnOnlineOrder order = new TYnOnlineOrder();
            order.setCftSeq(outTradeNo);
            order.setCreateDate(new Date());
            BigDecimal fee2 = fee1.multiply(new BigDecimal(100));
            order.setCost(fee2);
            double cost = fee2.intValue();
            cost = cost / 100;
            map.put("cost", String.valueOf(cost));
            order.setPayState(0);
            order.setPhonenumber(phone);
            order.setProductId(product.getId());
            order.setChannel(CommonConstant.SELL_CHANNEL_WX);
            order.setGroupId(product.getGroupId());
            order.setUserId(userId);
            commonService.saveOrUpdate(order);

            System.out.println("outTradeNo:" + outTradeNo);
            System.out.println("res.result:" + map.get("result"));
        }
        JsonWriteUtil.write(response, map);
    }

    //判断用户是否购买跳转页面
    @RequestMapping(value = "/testUserJump.jspx")
    public void testUserJump(HttpServletRequest request, HttpServletResponse response, Integer productId) throws IOException {
        CmsUser user = CmsUtils.getUser(request);
        Map<String, Boolean> map = new HashMap();
        Integer userId = user.getId();
        String sql = "SELECT * FROM t_yn_online_order WHERE user_id ='" + userId + "' and pay_state=1 AND product_id='" + productId + "'";
        List<TYnOnlineOrder> orderList = commonSvc.findListbySql(sql, TYnOnlineOrder.class);
        if (orderList != null && orderList.size() > 0) {
            map.put("msg", true);
        } else {
            map.put("msg", false);
        }
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }


    //微信未登录购买
    @RequestMapping(value = "/wxpay/buyCardWxNologin.jspx")
    public void buyCardWxNologin(HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer productId, String phone) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        CmsUser user = CmsUtils.getUser(request);
        TBaseProducts product = commonService.get(TBaseProducts.class, productId);
        Map<String, String> map = new HashMap();
       /* String sql = "SELECT * FROM t_yn_online_order WHERE phonenumber ='" + phone + "' and pay_state=1 AND product_id='" + product.getId() + "'";
        List<TYnOnlineOrder> orderList = commonSvc.findListbySql(sql, TYnOnlineOrder.class);
        if (orderList != null && orderList.size() > 0) {
            map.put("msg", "已购买");
        } else {*/
        // 生成预付ORderID
        String outTradeNo = WxpayUtil.getNonceStr();
        BigDecimal fee1 = (product.getDiscountCost().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP));
        String qr = qrPay2(outTradeNo, "127.0.0.1", phone, String.valueOf(fee1), product);
        map.put("result", qr);

        TYnOnlineOrder order = new TYnOnlineOrder();
        order.setCftSeq(outTradeNo);
        order.setCreateDate(new Date());
        BigDecimal fee2 = fee1.multiply(new BigDecimal(100));
        order.setCost(fee2);
        double cost = fee2.intValue();
        cost = cost / 100;
        map.put("cost", String.valueOf(cost));
        order.setPayState(0);
        order.setPhonenumber(phone);
        order.setProductId(product.getId());
        order.setChannel(CommonConstant.SELL_CHANNEL_WX);
        order.setGroupId(product.getGroupId());
        commonService.saveOrUpdate(order);

        System.out.println("outTradeNo:" + outTradeNo);
        System.out.println("res.result:" + map.get("result"));

        JsonWriteUtil.write(response, map);
    }

    //微信升级
    @RequestMapping(value = "/wxpay/buyUpgrade .jspx")
    public void wxBuyUpgrade(HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer productId, String strm) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        CmsUser user = CmsUtils.getUser(request);
        TBaseProducts product = commonService.get(TBaseProducts.class, productId);
        Map<String, String> map = new HashMap();
        Integer userId = user.getId();
        String phone = user.getUsername();
        String provinceId = user.getAttr().get("province_id");
        String sql = "SELECT * FROM t_yn_online_order WHERE user_id ='" + userId + "' and pay_state=1 AND product_id='" + product.getId() + "'";
        List<TYnOnlineOrder> orderList = commonSvc.findListbySql(sql, TYnOnlineOrder.class);
        if (orderList != null && orderList.size() > 0) {
            map.put("msg", "已购买");
        } else {

            String buyPrice = "SELECT * from jc_user_group WHERE user_id='" + userId + "' AND group_id in('" + strm + "')";
            List<JcUserGroup> buyGroup = commonSvc.findListbySql(buyPrice, JcUserGroup.class);
            BigDecimal price = new BigDecimal(0);
            if (buyGroup != null && buyGroup.size() > 0) {
                for (JcUserGroup group : buyGroup) {
                    String buyCost = "SELECT * FROM t_base_products WHERE province_id='" + provinceId + "' AND group_id='" + group.getGroupId() + "'";
                    List<TBaseProducts> products = commonSvc.findListbySql(buyCost, TBaseProducts.class);
                    if (products != null && products.size() > 0) {
                        price = price.add(products.get(0).getCost());
                    }
                }
            }
            // 生成预付ORderID
            String outTradeNo = WxpayUtil.getNonceStr();
            BigDecimal fee1 = (price.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP));
            String qr = qrPay(outTradeNo, "127.0.0.1", phone, String.valueOf(fee1), product);
            map.put("result", qr);

            TYnOnlineOrder order = new TYnOnlineOrder();
            order.setCftSeq(outTradeNo);
            order.setCreateDate(new Date());
            BigDecimal fee2 = fee1.multiply(new BigDecimal(100));
            order.setCost(fee2);
            double cost = fee2.intValue();
            cost = cost / 100;
            map.put("cost", String.valueOf(cost));
            order.setPayState(0);
            order.setPhonenumber(phone);
            order.setProductId(product.getId());
            order.setChannel(CommonConstant.SELL_CHANNEL_WX);
            order.setGroupId(product.getGroupId());
            order.setUserId(userId);
            commonService.saveOrUpdate(order);

            System.out.println("outTradeNo:" + outTradeNo);
            System.out.println("res.result:" + map.get("result"));

        }
        JsonWriteUtil.write(response, map);
    }

    // 获取微信扫码支付二维码连接
    @RequestMapping(value = "/wxpay/cardUrl.jspx")
    public String qrPay(String outTradeNo, String ip, String phone, String fee,
                        TBaseProducts product) {
        WxPayDto tpWxPay = new WxPayDto();
        tpWxPay.setOrderId(outTradeNo);// 订单号
        tpWxPay.setTotalFee(fee);
        tpWxPay.setSpbillCreateIp(ip);
        tpWxPay.setNotifyUrl(WX_PAY_NOTIFY_URL);
        tpWxPay.setBody(product.getDesc());
        tpWxPay.setOpenId("");
        String attach = phone + "," + product.getProvinceId().toString();
        tpWxPay.setAttach(attach);
        return WxpayUtil.getCodeurl(tpWxPay, product);
    }


    // 获取微信扫码支付二维码连接
    @RequestMapping(value = "/wxpay/cardUrl2.jspx")
    public String qrPay2(String outTradeNo, String ip, String phone, String fee,
                         TBaseProducts product) {
        WxPayDto tpWxPay = new WxPayDto();
        tpWxPay.setOrderId(outTradeNo);// 订单号
        tpWxPay.setTotalFee(fee);
        tpWxPay.setSpbillCreateIp(ip);

        tpWxPay.setNotifyUrl(WX_PAY_NOTIFY_URL_NO_LOGIN);
        tpWxPay.setBody(product.getDesc());
        tpWxPay.setOpenId("");
        String attach = phone + "," + product.getProvinceId().toString();
        tpWxPay.setAttach(attach);
        return WxpayUtil.getCodeurl(tpWxPay, product);
    }


    //微信回调
    @RequestMapping(value = "/wxBuyCallback.jspx")
    public void buyZyCallback(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 把如下代码贴到的你的处理回调的servlet 或者.do 中即可明白回调操作
        System.out.print("微信支付志愿服务回调数据开始");
        // 示例报文
        // String xml =
        // "<xml><appid><![CDATA[wxb4dc385f953b356e]]></appid><bank_type><![CDATA[CCB_CREDIT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1228442802]]></mch_id><nonce_str><![CDATA[1002477130]]></nonce_str><openid><![CDATA[o-HREuJzRr3moMvv990VdfnQ8x4k]]></openid><out_trade_no><![CDATA[1000000000051249]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[1269E03E43F2B8C388A414EDAE185CEE]]></sign><time_end><![CDATA[20150324100405]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1009530574201503240036299496]]></transaction_id></xml>";
        String inputLine;
        String notityXml = "";
        String resXml = "";

        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                notityXml += inputLine;
            }
            request.getReader().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("接收到的报文：" + notityXml);
        Map<String, String> m = parseXmlToList2(notityXml);
        WxPayResult wpr = new WxPayResult();
        wpr.setAppid(m.get("appid").toString());
        wpr.setBankType(m.get("bank_type").toString());
        wpr.setCashFee(m.get("cash_fee").toString());
        wpr.setFeeType(m.get("fee_type").toString());
        wpr.setIsSubscribe(m.get("is_subscribe").toString());
        wpr.setMchId(m.get("mch_id").toString());
        wpr.setNonceStr(m.get("nonce_str").toString());
        wpr.setOpenid(m.get("openid").toString());
        wpr.setOutTradeNo(m.get("out_trade_no").toString());
        wpr.setResultCode(m.get("result_code").toString());
        wpr.setReturnCode(m.get("return_code").toString());
        wpr.setSign(m.get("sign").toString());
        wpr.setTimeEnd(m.get("time_end").toString());
        wpr.setTotalFee(m.get("total_fee").toString());
        wpr.setTradeType(m.get("trade_type").toString());
        wpr.setTransactionId(m.get("transaction_id").toString());

        System.out.println("支付结果:" + wpr.getResultCode());
        if ("SUCCESS".equals(wpr.getResultCode())) {
            // 支付成功
            resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                    + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            Finder ff = Finder.create("from TYnOnlineOrder where cftSeq=:cft_seq");
            ff.setParam("cft_seq", wpr.getOutTradeNo());
            TYnOnlineOrder order = commonService.getOneResult(ff);
            String phone = order.getPhonenumber();
            // 更新用户组
            
            // Alltodo: 2020/3/9 保存信息
            try {
                JcUserGroup group = new JcUserGroup();
                group.setUserId(order.getUserId());
                group.setGroupId(order.getGroupId());
                commonSvc.saveOrUpdate(group);

                // 保存订单信息
                order.setPayDate(new Date());
                order.setPayState(1);
                commonService.saveOrUpdate(order);

                // 发送短信
                String uri = "http://send.18sms.com/msg/HttpBatchSendSM";//应用地址
                String account = "173kbz ";//示远账号
                String pswd = "B9pL6iPk";//示远密码
                String mobile = phone;//手机号码，多个号码使用","分割
                String content = "您好，您已成功购买服务";//短信内容，注意内容中的逗号请使用中文状态下的逗号
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


            } catch (Exception e) {
                System.out.println("购买志愿填报服务后更新用户组失败：" + order.getPhonenumber());
                System.out.println(e.getMessage());
            }


        } else {
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";


        }
        System.out.println("微信支付回调数据结束");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();

    }

    //微信回调2
    @RequestMapping(value = "/wxBuyCallback2.jspx")
    public void buyZyCallback2(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 把如下代码贴到的你的处理回调的servlet 或者.do 中即可明白回调操作
        System.out.print("微信支付志愿服务回调数据开始");
        // 示例报文
        // String xml =
        // "<xml><appid><![CDATA[wxb4dc385f953b356e]]></appid><bank_type><![CDATA[CCB_CREDIT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1228442802]]></mch_id><nonce_str><![CDATA[1002477130]]></nonce_str><openid><![CDATA[o-HREuJzRr3moMvv990VdfnQ8x4k]]></openid><out_trade_no><![CDATA[1000000000051249]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[1269E03E43F2B8C388A414EDAE185CEE]]></sign><time_end><![CDATA[20150324100405]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1009530574201503240036299496]]></transaction_id></xml>";
        String inputLine;
        String notityXml = "";
        String resXml = "";

        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                notityXml += inputLine;
            }
            request.getReader().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("接收到的报文：" + notityXml);
        Map<String, String> m = parseXmlToList2(notityXml);
        WxPayResult wpr = new WxPayResult();
        wpr.setAppid(m.get("appid").toString());
        wpr.setBankType(m.get("bank_type").toString());
        wpr.setCashFee(m.get("cash_fee").toString());
        wpr.setFeeType(m.get("fee_type").toString());
        wpr.setIsSubscribe(m.get("is_subscribe").toString());
        wpr.setMchId(m.get("mch_id").toString());
        wpr.setNonceStr(m.get("nonce_str").toString());
        wpr.setOpenid(m.get("openid").toString());
        wpr.setOutTradeNo(m.get("out_trade_no").toString());
        wpr.setResultCode(m.get("result_code").toString());
        wpr.setReturnCode(m.get("return_code").toString());
        wpr.setSign(m.get("sign").toString());
        wpr.setTimeEnd(m.get("time_end").toString());
        wpr.setTotalFee(m.get("total_fee").toString());
        wpr.setTradeType(m.get("trade_type").toString());
        wpr.setTransactionId(m.get("transaction_id").toString());

        System.out.println("支付结果:" + wpr.getResultCode());
        if ("SUCCESS".equals(wpr.getResultCode())) {
            // 支付成功
            resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                    + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            Finder ff = Finder.create("from TYnOnlineOrder where cftSeq=:cft_seq");
            ff.setParam("cft_seq", wpr.getOutTradeNo());
            TYnOnlineOrder order = commonService.getOneResult(ff);
            String phone = order.getPhonenumber();

            try {

                // 保存订单信息
                Date payDate = new Date();
                order.setPayDate(payDate);
                order.setPayState(1);
                TBaseVrcards card = new TBaseVrcards();
                card.setCreateTime(new Date());
                String password = PasswordUtil.randomPassword();
                card.setPassword(password);
                card.setActiveStatus(false);
                card.setInOutStorage(1);// 出库状态
                card.setProvinceId(order.getProduct().getProvinceId());
                card.setGroupId(order.getProduct().getGroupId());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(payDate);
                calendar.add(Calendar.YEAR, 1);
                payDate = calendar.getTime();
                card.setLosedTime(payDate);
                //commonSvc.saveOrUpdate(card);
                Long cardNo = (Long) commonSvc.save(card);
//                订单表保存卡号密码
                order.setCardId(cardNo);
                order.setPassword(password);
                commonService.saveOrUpdate(order);

                // 发送短信
                String uri = "http://send.18sms.com/msg/HttpBatchSendSM";//应用地址
                String account = "173kbz ";//示远账号
                String pswd = "B9pL6iPk";//示远密码
                String mobile = phone;//手机号码，多个号码使用","分割
                String content = "您好，您购买的账号是：" + cardNo + "密码是" + password;//短信内容，注意内容中的逗号请使用中文状态下的逗号
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


            } catch (Exception e) {
                System.out.println("购买志愿填报服务后更新用户组失败：" + order.getPhonenumber());
                System.out.println(e.getMessage());
            }


        } else {
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";


        }
        System.out.println("微信支付回调数据结束");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();

    }

    /**
     * description: 解析微信通知xml
     *
     * @param xml
     * @return
     * @author ex_yangxiaoyi
     * @see
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Map<String, String> parseXmlToList2(String xml) {
        Map<String, String> retMap = new HashMap();
        try {
            StringReader read = new StringReader(xml);
            // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
            InputSource source = new InputSource(read);
            // 创建一个新的SAXBuilder
            SAXBuilder sb = new SAXBuilder();
            // 通过输入源构造一个Document
            Document doc = (Document) sb.build(source);
            Element root = doc.getRootElement();// 指向根节点
            List<Element> es = root.getChildren();
            if (es != null && es.size() != 0) {
                for (Element element : es) {
                    retMap.put(element.getName(), element.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retMap;
    }

    //    支付宝购买
    @RequestMapping("/aliPay/buyCard.jspx")
    public void toPay(Integer productId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        CmsUser user = CmsUtils.getUser(request);
        TBaseProducts product = commonService.get(TBaseProducts.class, productId);
        Map<String, String> map = new HashMap();
        Integer userId = user.getId();
        String phone = user.getUsername();
        String sql = "SELECT * FROM t_yn_online_order WHERE user_id ='" + userId + "' and pay_state=1 AND product_id='" + product.getId() + "'";
        List<TYnOnlineOrder> orderList = commonSvc.findListbySql(sql, TYnOnlineOrder.class);
        if (orderList != null && orderList.size() > 0) {
            map.put("msg", "已购买");
        } else {
            // 生成预付ORderID
            String outTradeNo = WxpayUtil.getNonceStr();
            //创建预付订单信息 并保存b
            TYnOnlineOrder order = new TYnOnlineOrder();
            //计算价格 单位：元
            BigDecimal payAmount = product.getDiscountCost().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
            order.setCftSeq(outTradeNo);
            order.setCreateDate(new Date());
            order.setCost(payAmount.multiply(new BigDecimal(100)));
            order.setPayState(0);
            order.setPhonenumber(phone);
            order.setProductId(product.getId());

            order.setUserId(userId);
            order.setGroupId(product.getGroupId());
            commonSvc.saveOrUpdate(order);
            //生成二维码连接
            try {
                String qr_code = getAliPayUrl(outTradeNo, product.getName(), payAmount, phone);
                map.put("qr_code", qr_code);
//            model.addAttribute("qr_code", qr_code);
                response.setCharacterEncoding("utf-8");
//            response.getWriter().write(qr_code);
                JsonWriteUtil.write(response, map);
            } catch (AlipayApiException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //    支付宝未登录购买
    @RequestMapping("/aliPay/buyCardNologin.jspx")
    public void buyCardNologin(Integer productId, String phone, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        CmsUser user = CmsUtils.getUser(request);
        TBaseProducts product = commonService.get(TBaseProducts.class, productId);
        Map<String, String> map = new HashMap();
      /*  String sql = "SELECT * FROM t_yn_online_order WHERE phonenumber ='" + phone + "' and pay_state=1 AND product_id='" + product.getId() + "'";
        List<TYnOnlineOrder> orderList = commonSvc.findListbySql(sql, TYnOnlineOrder.class);
        if (orderList != null && orderList.size() > 0) {
            map.put("msg", "已购买");
        } else {*/
        // 生成预付ORderID
        String outTradeNo = WxpayUtil.getNonceStr();
        //创建预付订单信息 并保存b
        TYnOnlineOrder order = new TYnOnlineOrder();
        //计算价格 单位：元
        BigDecimal payAmount = product.getDiscountCost().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
        order.setCftSeq(outTradeNo);
        order.setCreateDate(new Date());
        order.setCost(payAmount.multiply(new BigDecimal(100)));
        order.setPayState(0);
        order.setPhonenumber(phone);
        order.setProductId(product.getId());
        order.setGroupId(product.getGroupId());
        commonSvc.saveOrUpdate(order);
        //生成二维码连接
        try {
            String qr_code = getAliPayUrl2(outTradeNo, product.getName(), payAmount, phone);
            map.put("qr_code", qr_code);
//            model.addAttribute("qr_code", qr_code);
            response.setCharacterEncoding("utf-8");
//            response.getWriter().write(qr_code);
            JsonWriteUtil.write(response, map);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //    支付宝升级
    @RequestMapping("/aliPay/buyUpgrade.jspx")
    public void aliBuyUpgrade(Integer productId, String strm, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        CmsUser user = CmsUtils.getUser(request);
        TBaseProducts product = commonService.get(TBaseProducts.class, productId);
        Map<String, String> map = new HashMap();
        Integer userId = user.getId();
        String phone = user.getUsername();
        String provinceId = user.getAttr().get("province_id");
        String sql = "SELECT * FROM t_yn_online_order WHERE user_id ='" + userId + "' and pay_state=1 AND product_id='" + product.getId() + "'";
        List<TYnOnlineOrder> orderList = commonSvc.findListbySql(sql, TYnOnlineOrder.class);
        if (orderList != null && orderList.size() > 0) {
            map.put("msg", "已购买");
        } else {
            String buyPrice = "SELECT * from jc_user_group WHERE user_id='" + userId + "' AND group_id in('" + strm + "')";
            List<JcUserGroup> buyGroup = commonSvc.findListbySql(buyPrice, JcUserGroup.class);
            BigDecimal price = new BigDecimal(0);
            if (buyGroup != null && buyGroup.size() > 0) {
                for (JcUserGroup group : buyGroup) {
                    String buyCost = "SELECT * FROM t_base_products WHERE province_id='" + provinceId + "' AND group_id='" + group.getGroupId() + "'";
                    List<TBaseProducts> products = commonSvc.findListbySql(buyCost, TBaseProducts.class);
                    if (products != null && products.size() > 0) {
                        price = price.add(products.get(0).getCost());
                    }
                }
            }

            // 生成预付ORderID
            String outTradeNo = WxpayUtil.getNonceStr();
            //创建预付订单信息 并保存b
            TYnOnlineOrder order = new TYnOnlineOrder();
            //计算价格 单位：元
            BigDecimal payAmount = price.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
            order.setCftSeq(outTradeNo);
            order.setCreateDate(new Date());
            order.setCost(payAmount.multiply(new BigDecimal(100)));
            order.setPayState(0);
            order.setPhonenumber(phone);
            order.setProductId(product.getId());

            order.setUserId(userId);
            order.setGroupId(product.getGroupId());
            commonSvc.saveOrUpdate(order);
            //生成二维码连接
            try {
                String qr_code = getAliPayUrl(outTradeNo, product.getName(), payAmount, phone);
                map.put("qr_code", qr_code);
//            model.addAttribute("qr_code", qr_code);
                response.setCharacterEncoding("utf-8");
//            response.getWriter().write(qr_code);
                JsonWriteUtil.write(response, map);
            } catch (AlipayApiException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //生成支付宝二维码
    @RequestMapping(value = "/aliPay/cardUrl.jspx")
    public String getAliPayUrl(String outTradeNo, String subject, BigDecimal payAmount, String phone) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(GATE_WAY_URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayTradePrecreateRequest aliRequest = new AlipayTradePrecreateRequest();//创建API对应的request类
        //设置回调地址
        aliRequest.setNotifyUrl(ALI_PAY_NOTIFY_URL);
        Map<String, Object> map = new HashMap<>();
        map.put("out_trade_no", outTradeNo);
        map.put("total_amount", payAmount);
        map.put("subject", subject);
        map.put("phoneNumber", phone);
        map.put("timeout_express", ALI_TIMEOUT_EXPRESS);
        //设置请求业务内容
        aliRequest.setBizContent(JSON.toJSONString(map));
        AlipayTradePrecreateResponse aliResponse = alipayClient.execute(aliRequest);
        //System.out.print(aliResponse.getBody());
        String body = aliResponse.getBody();
        Map<String, Map<String, String>> res = JSON.parseObject(body, Map.class);
        //获取qr_code的url
        String qr_code = res.get("alipay_trade_precreate_response").get("qr_code").replace("\\", "");
        return qr_code;
    }

    //生成支付宝二维码2
    @RequestMapping(value = "/aliPay/cardUrl2.jspx")
    public String getAliPayUrl2(String outTradeNo, String subject, BigDecimal payAmount, String phone) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(GATE_WAY_URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayTradePrecreateRequest aliRequest = new AlipayTradePrecreateRequest();//创建API对应的request类
        //设置回调地址
        aliRequest.setNotifyUrl(ALI_PAY_NOTIFY_URL_NO_LOGIN);
        Map<String, Object> map = new HashMap<>();
        map.put("out_trade_no", outTradeNo);
        map.put("total_amount", payAmount);
        map.put("subject", subject);
        map.put("phoneNumber", phone);
        map.put("timeout_express", ALI_TIMEOUT_EXPRESS);
        //设置请求业务内容
        aliRequest.setBizContent(JSON.toJSONString(map));
        AlipayTradePrecreateResponse aliResponse = alipayClient.execute(aliRequest);
        //System.out.print(aliResponse.getBody());
        String body = aliResponse.getBody();
        Map<String, Map<String, String>> res = JSON.parseObject(body, Map.class);
        //获取qr_code的url
        String qr_code = res.get("alipay_trade_precreate_response").get("qr_code").replace("\\", "");
        return qr_code;
    }


    /**
     * 支付宝回调通知
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/alipayCallback.jspx", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(readOnly = false)
    public void aliPayNotify(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(">>>>>REQUEST JSON STRING:" + JSON.toJSONString(request.getParameterMap()));
        Map<String, String> aliPayMap = AliPayCommonUtil.toAliMap(request);
        try {
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            Boolean flag = AlipaySignature.rsaCheckV1(aliPayMap, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE);
            System.out.println(">>flag=" + flag);
            if (flag) {
                String orderSeq = aliPayMap.get("out_trade_no").toString(); //订单序列
                TYnOnlineOrder order = commonSvc.findUniqueByProperty(TYnOnlineOrder.class, "cftSeq", orderSeq);
                String phone = order.getPhonenumber();
                System.out.println(">>>>>order:" + JSON.toJSONString(order));
                String tradeStatus = aliPayMap.get("trade_status");
                System.out.println("tradeStatus=" + tradeStatus);
                if ("TRADE_SUCCESS".equals(tradeStatus)) {
                    System.out.println("交易成功");
                    writer.write("success");

                    //修改订单信息
                    Date payDate = new Date(); //交易成功时间
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(payDate);
                    calendar.add(Calendar.YEAR, 1);
                    payDate = calendar.getTime();
                    order.setPayDate(payDate);
                    order.setPayState(1); //已付款
                    commonSvc.saveOrUpdate(order);

                    JcUserGroup group = new JcUserGroup();
                    group.setUserId(order.getUserId());
                    group.setGroupId(order.getGroupId());
                    commonSvc.saveOrUpdate(group);

                    String uri = "http://send.18sms.com/msg/HttpBatchSendSM";//应用地址
                    String account = "173kbz ";//示远账号
                    String pswd = "B9pL6iPk";//示远密码
                    String mobile = phone;//手机号码，多个号码使用","分割
                    String content = "您好，您已成功购买服务";//短信内容，注意内容中的逗号请使用中文状态下的逗号
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
//
                } else {
                    log.info(">>>交易失败");
                    writer.write("fail");
                }
            } else {
                log.info(">>>解析异常");
                writer.write("fail");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 支付宝回调通知2
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/alipayCallback2.jspx", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(readOnly = false)
    public void aliPayNotify2(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(">>>>>REQUEST JSON STRING:" + JSON.toJSONString(request.getParameterMap()));
        Map<String, String> aliPayMap = AliPayCommonUtil.toAliMap(request);
        try {
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            Boolean flag = AlipaySignature.rsaCheckV1(aliPayMap, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE);
            System.out.println(">>flag=" + flag);
            if (flag) {
                String orderSeq = aliPayMap.get("out_trade_no").toString(); //订单序列
                TYnOnlineOrder order = commonSvc.findUniqueByProperty(TYnOnlineOrder.class, "cftSeq", orderSeq);
                String phone = order.getPhonenumber();
                System.out.println(">>>>>order:" + JSON.toJSONString(order));
                String tradeStatus = aliPayMap.get("trade_status");
                System.out.println("tradeStatus=" + tradeStatus);
                if ("TRADE_SUCCESS".equals(tradeStatus)) {
                    System.out.println("交易成功");
                    writer.write("success");

                    //修改订单信息
                    Date payDate = new Date(); //交易成功时间
                    order.setPayDate(payDate);
                    order.setPayState(1); //已付款

                    TBaseVrcards card = new TBaseVrcards();
                    card.setCreateTime(new Date());
                    String password = PasswordUtil.randomPassword();
                    card.setPassword(password);
                    card.setActiveStatus(false);
                    card.setInOutStorage(1);// 出库状态
                    card.setProvinceId(order.getProduct().getProvinceId());
                    card.setGroupId(order.getProduct().getGroupId());
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(payDate);
                    calendar.add(Calendar.YEAR, 1);
                    payDate = calendar.getTime();
                    card.setLosedTime(payDate);
                    //commonSvc.saveOrUpdate(card);
                    Long cardNo = (Long) commonSvc.save(card);

                    order.setCardId(cardNo);
                    order.setPassword(password);
                    commonSvc.saveOrUpdate(order);

                    // 发送短信
                    String uri = "http://send.18sms.com/msg/HttpBatchSendSM";//应用地址
                    String account = "173kbz ";//示远账号
                    String pswd = "B9pL6iPk";//示远密码
                    String mobile = phone;//手机号码，多个号码使用","分割
                    String content = "您好，您购买的账号是：" + cardNo + "密码是" + password;//短信内容，注意内容中的逗号请使用中文状态下的逗号
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
//
                } else {
                    log.info(">>>交易失败");
                    writer.write("fail");
                }
            } else {
                log.info(">>>解析异常");
                writer.write("fail");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //    新增志愿表
    ////    @RequiresPermissions("ZhiyuanTianbao:*")
    @RequestMapping(value = "/findVoluntary.jspx")
    public String findVoluntary(String applicationName, Integer pageNo, HttpServletRequest request, ModelMap model) {

        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        //判断用户是否有省份选择和文理科选择的权限，有的话能动态选择省份
        Set<CmsGroup> groups = user.getGroups();
        Iterator<CmsGroup> groupIt = groups.iterator();
        String yesOrNo = "false";
        while (groupIt.hasNext()) {
            Set<String> groupss = groupIt.next().getPerms();
            Iterator<String> groupssIt = groupss.iterator();
            while (groupssIt.hasNext()) {
                if ("shengfenXuanze:*".equals(groupssIt.next())) {
                    model.addAttribute("roledId", "3");
                    yesOrNo = "true";
                    break;
                }
            }
        }

//        用户省份文理科切换权限
        String provinceChange = "false";
        String majorChange = "false";
        Set<CmsGroup> userGroups = user.getGroups();
        Iterator<CmsGroup> userGroupsIt = userGroups.iterator();
        while (userGroupsIt.hasNext()) {
            Integer groupId = userGroupsIt.next().getId();
            if (groupId == 4 || groupId == 6) {
                provinceChange = "true";
                majorChange = "true";
                break;
            }
        }
        model.addAttribute("provinceChange", provinceChange);
        model.addAttribute("majorChange", majorChange);

        //方案分页列表
        Pagination pagination = zytbSvc.queryProjects(user.getId(), applicationName, cpn(pageNo), CookieUtils.getPageSize(request));
        //用户信息
        Map userInfo = new HashMap();
        userInfo.put("topRoleLevel", user.getTopRoleLevel());
        userInfo.put("provinceId", user.getAttr().get("province_id"));
        userInfo.put("majorTypeId", user.getAttr().get("major_type_id"));
        /*判断是否为管理角色*/
        boolean isSuper = false;
        if (user.getRoleIds() != null && user.getRoleIds().length != 0) {
            isSuper = true;
        }
        if (user.isSuper()) {
            isSuper = true;
        }
        /**/
        userInfo.put("isSuper", isSuper);
        //省份列表
        List<TBaseProvince> provinceList = commonSvc.loadAll(TBaseProvince.class);
        Map<String, String> provinceMap = new HashMap<>();
        for (TBaseProvince t : provinceList) {
            provinceMap.put(String.valueOf(t.getProvinceId()), t.getProvinceName());
        }
        //批次排名信息
        List batchInfo = zytbSvc.getBatchInfo(userInfo);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("provinceList", provinceList);
        model.addAttribute("provinceMap", provinceMap);
        model.addAttribute("pagination", pagination);
        model.addAttribute("batchInfo", batchInfo);
        model.addAttribute("majorTypeId", user.getAttr().get("major_type_id"));
        model.addAttribute("provinceId", user.getAttr().get("province_id"));
        FrontUtils.frontData(request, model, site);
//        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, APPLICATIONS_RESULT);

//        String TPL_zhiyuanbiao = "project";

        return FrontUtils.getTplPath(site.getSolutionPath(), "wdedu/hyzx", "project");
    }

    //    志愿表列表
    @RequestMapping(value = "/findApplicationList.jspx")
    public String findApplicationList(String applicationName, Integer pageNo, HttpServletRequest request, ModelMap model) {

        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        //用户信息
        Map userInfo = new HashMap();
        userInfo.put("topRoleLevel", user.getTopRoleLevel());
        userInfo.put("provinceId", user.getAttr().get("province_id"));
        userInfo.put("majorTypeId", user.getAttr().get("major_type_id"));
        /*判断是否为管理角色*/
        boolean isSuper = false;
        if (user.getRoleIds() != null && user.getRoleIds().length != 0) {
            isSuper = true;
        }
        if (user.isSuper()) {
            isSuper = true;
        }
        /**/
        userInfo.put("isSuper", isSuper);
        //省份列表
        List<TBaseProvince> provinceList = commonSvc.loadAll(TBaseProvince.class);
        Map<String, String> provinceMap = new HashMap<>();
        for (TBaseProvince t : provinceList) {
            provinceMap.put(String.valueOf(t.getProvinceId()), t.getProvinceName());
        }
        //方案分页列表
        Pagination pagination = zytbSvc.queryProjects(user.getId(), applicationName, cpn(pageNo), CookieUtils.getPageSize(request));
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("pagination", pagination);
        model.addAttribute("provinceMap", provinceMap);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), "wdedu/hyzx", "projectList");
    }


//    志愿列表更新分数排名
@RequestMapping(value = "/updateApplicationScore.jspx")
public void updateApplicationScore(String applicationId,String applicationName,String score,HttpServletResponse response) throws IOException {

        TCeeApplications tCeeApplications = commonSvc.get(TCeeApplications.class,Integer.valueOf(applicationId));
        Integer year = tCeeApplications.getYear();
        Integer province_id = tCeeApplications.getProvinceId();
        Integer major_type_id = tCeeApplications.getMajorTypeId();

    String msg = "方案信息修改成功！";

        if(StringUtil.isNotEmpty(score)) {

            String getRankHql = " FROM TCeeScoreRank\n" +
                    "WHERE YEAR = " + year + " \n" +
                    "AND provinceId = " + province_id + " \n" +
                    "AND majorTypeId = " + major_type_id + " \n" +
                    "AND score = " + score + " ";
            TCeeScoreRank tCeeScoreRank = commonSvc.singleResult(getRankHql);

            tCeeApplications.setScore(Integer.valueOf(score));
            tCeeApplications.setRank(tCeeScoreRank.getRank());
        }else{
            msg="分数输入不可为空!";
        }

        if(StringUtil.isNotEmpty(applicationName)){
            tCeeApplications.setApplicationName(applicationName);
        }else{
            msg="方案名称不可为空!";
        }

        commonSvc.updateEntitie(tCeeApplications);


    Map<String, String> resultMap = new HashMap<>();
    resultMap.put("msg", msg);
    response.setCharacterEncoding("utf-8");
    response.getWriter().write(JSON.toJSONString(resultMap));


    }

    /**
     * 查询分数和排名
     */
    @RequestMapping(value = "/batch.jspx")
    public void getBatchInfo(HttpServletRequest request, HttpServletResponse response) {
        CmsUser user = CmsUtils.getUser(request);
        Map<String, String> param = new HashMap<>();
        param.put("batchId", request.getParameter("batchId"));
        param.put("provinceId", request.getParameter("provinceId"));
        param.put("majorTypeId", request.getParameter("majorTypeId"));
        param.put("gap", request.getParameter("gap"));
        if (StringUtils.isEmpty(param.get("provinceId"))) {
            param.put("provinceId", user.getAttr().get("province_id"));
        }
        if (StringUtils.isEmpty(param.get("majorTypeId"))) {
            param.put("majorTypeId", user.getAttr().get("major_type_id"));
        }
        if (StringUtils.isEmpty(param.get("gap"))) {
            param.put("gap", "0");
        }
        List list = zytbSvc.getScoreAndRankFromBatch(param);
        try {
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询一分一段
     *
     * @param score
     * @param request
     * @param response
     */
    @RequestMapping("/rank.jspx")
    public void getRankByScore(String score, String provinceId, String rank, String majorTypeId, HttpServletRequest request, HttpServletResponse response) {
        CmsUser user = CmsUtils.getUser(request);
        if (StringUtils.isEmpty(provinceId)) {
            provinceId = user.getAttr().get("province_id");
        }
        if (StringUtils.isEmpty(majorTypeId)) {
            majorTypeId = user.getAttr().get("major_type_id");
        }
        Integer year = commonSvc.singleResult("SELECT dataScoreYear FROM TBaseProvince WHERE province_id =" + provinceId + " ");
        String hql = "from TCeeScoreRank where year=" + year +
                " and provinceId=" + provinceId + " and majorTypeId=" + majorTypeId;
        if (StringUtils.isNotBlank(score)) {
            hql += " and score=" + score;
        }
        if (StringUtils.isNotBlank(rank)) {
            hql += " and rank >=" + rank + "order by score desc";
        }
        List<TCeeScoreRank> list = commonSvc.findByQueryString(hql);
        try {
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(list.size() > 0 ? list.get(0) : ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除方案
     *
     * @param applicationId 方案ID
     * @param request       request
     * @param model         model
     * @return string
     */
//    @RequiresPermissions("shanchu:*")
    @RequestMapping("/removeProject.jspx")
    public void removeProject(Integer applicationId, HttpServletRequest request, ModelMap model, HttpServletResponse response) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        commonSvc.deleteEntityById(TCeeApplications.class, applicationId);


        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("msg", "删除成功");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(resultMap));
    }


    @RequestMapping(value = "/city.jspx")
    public String city(HttpServletRequest request, ModelMap model, String province_id, HttpServletResponse response) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        //城市
        String hql2 = "SELECT DISTINCT cityName ,cityId FROM TBaseHighshool WHERE provinceId = '" + province_id + "'";
        tBaseHighshoolList = commonSvc.findByQueryString(hql2);
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(JSON.toJSONString(tBaseHighshoolList));
            return "";
        } catch (IOException e) {
            e.printStackTrace();
        }
        //区县
        return FrontUtils.getTplPath(site.getSolutionPath(), "hyzx", "huiyuanshezhi");
    }

    @RequestMapping(value = "/quxian.jspx")
    public String quxian(HttpServletRequest request, ModelMap model, String cityId, HttpServletResponse response) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        //城市
        String hql3 = "SELECT DISTINCT quxianId,quxianName  FROM TBaseHighshool WHERE cityId = '" + cityId + "'";
        tBaseHighshoolList1 = commonSvc.findByQueryString(hql3);
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(JSON.toJSONString(tBaseHighshoolList1));
            return "";
        } catch (IOException e) {
            e.printStackTrace();
        }
        //区县
        return FrontUtils.getTplPath(site.getSolutionPath(), "hyzx", "huiyuanshezhi");
    }

    @RequestMapping(value = "/school.jspx")
    public String school(HttpServletRequest request, ModelMap model, String quxianId, HttpServletResponse response) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        //城市
        String hql4 = "SELECT schoolName,schoolId FROM TBaseHighshool WHERE quxianId = '" + quxianId + "'";
        tBaseHighshoolList2 = commonSvc.findByQueryString(hql4);
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(JSON.toJSONString(tBaseHighshoolList2));
            return "";
        } catch (IOException e) {
            e.printStackTrace();
        }
        //区县
        return FrontUtils.getTplPath(site.getSolutionPath(), "hyzx", "huiyuanshezhi");
    }

    @RequestMapping(value = "/bigData.jspx")
    public String bigData(HttpServletRequest request, ModelMap model, HttpServletResponse response) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        return FrontUtils.getTplPath(site.getSolutionPath(), "wdedu/hyzx", "grzxdsjcx");
    }

    /* 页面开发中*/
    @RequestMapping(value = "/development.jspx")
    public String development(HttpServletRequest request, ModelMap model, HttpServletResponse response) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        return FrontUtils.getTplPath(site.getSolutionPath(), "wdedu/index", "jianshe");
    }
}
