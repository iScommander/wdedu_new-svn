package com.jeecms.wdedu.service.impl;

import com.jeecms.common.security.BadCredentialsException;
import com.jeecms.common.security.UsernameNotFoundException;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.Authentication;
import com.jeecms.core.manager.AuthenticationMng;
import com.jeecms.wdedu.service.UserSvc;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.web.subject.WebSubject.Builder;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserSvcImpl implements UserSvc {

    @Autowired
    private AuthenticationMng authMng;
    @Autowired
    private SessionProvider session;

    /**
     * 自动登录
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @Override
    public Map<String, Object> login(String username, String password,Map<String, Object> returnMap, HttpServletRequest request, HttpServletResponse response) {


        try {
            Authentication auth = authMng.login(username, password, RequestUtils.getIpAddr(request), request, response, session);
            if (auth.getUid() != null) {
                PrincipalCollection principals = new SimplePrincipalCollection(username, username);
                Builder builder = new WebSubject.Builder(request, response);
                builder.authenticated(true);
                builder.principals(principals);
                WebSubject subject = builder.buildWebSubject();
                ThreadContext.bind(subject);
            }

            returnMap.put("success", true);
            returnMap.put("msg", "验证成功");

        } catch (BadCredentialsException e) {
            returnMap.put("success", false);
            returnMap.put("msg", "密码错误");
        } catch (UsernameNotFoundException e) {
            returnMap.put("success", false);
            returnMap.put("msg", "无此用户");
        }

        return returnMap;
    }
}
