package com.jeecms.wdedu.action;

import com.jeecms.cms.action.member.ForgotPasswordAct;
import com.jeecms.common.email.EmailSender;
import com.jeecms.common.email.MessageTemplate;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.MemberConfig;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.core.web.WebErrors;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.jeecms.cms.Constants.TPLDIR_MEMBER;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/16
 */
@Controller
@RequestMapping(value = "/demo")
public class Demo {
    public static final String FORGOT_PASSWORD_INPUT = "forget";
    public static final String FORGOT_PASSWORD_RESULT = "demo";
    public static final String TPLDIR_USER = "user";
    private static Logger log = LoggerFactory.getLogger(ForgotPasswordAct.class);
    @Autowired
    private UnifiedUserMng unifiedUserMng;

    @RequestMapping(value = "/demo.jspx", method = RequestMethod.GET)
    public String demo(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        MemberConfig mcfg = site.getConfig().getMemberConfig();
        // 没有开启会员功能
        //if (!mcfg.isMemberOn()) {
        //    return FrontUtils.showMessage(request, model, "member.memberClose");
        //}
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        return FrontUtils.getTplPath(site.getSolutionPath(),
                "user", "login");
    }

    @RequestMapping(value = "/member/forget.jspx", method = RequestMethod.GET)
    public String forgotPasswordSubmit(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_USER, FORGOT_PASSWORD_INPUT);
    }

    @RequestMapping(value = "/member/forget.jspx", method = RequestMethod.POST)
    public String forgotPasswordSubmit(String email, String username, HttpServletRequest request,
                                       HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
//        String username = RequestUtils.getQueryParam(request, "username");
//        UnifiedUser user = unifiedUserMng.getByUsername(username);
//        model.addAttribute("user", user);
//        FrontUtils.frontData(request, model, site);
//        if (user == null) {
//            // 用户名不存在
//            model.addAttribute("status", 1);
//        } else if (StringUtils.isBlank(user.getEmail())) {
//            // 用户没有设置邮箱
//            model.addAttribute("status", 2);
//        } else if (!user.getEmail().equals(email)) {
//            // 邮箱输入错误
//            model.addAttribute("status", 3);
//        } else {
//            try {
////                unifiedUserMng.passwordForgotten(user.getId(), sender, msgTpl);
//                model.addAttribute("status", 0);
//            } catch (Exception e) {
//                // 发送邮件异常
                model.addAttribute("status", username+":"+email);
//                model.addAttribute("message", e.getMessage());
//                log.error("send email exception.", e);
//            }
//        }
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_USER, FORGOT_PASSWORD_RESULT);
    }
}
