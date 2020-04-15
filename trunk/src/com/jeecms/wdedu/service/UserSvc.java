package com.jeecms.wdedu.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface UserSvc {
    Map<String, Object> login(String username, String password,Map<String, Object> returnMap, HttpServletRequest request, HttpServletResponse response);
}
