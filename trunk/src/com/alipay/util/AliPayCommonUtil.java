package com.alipay.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @Description:
 * @date 2018/11/12
 */
public class AliPayCommonUtil {
    /**
     * 处理支付宝回调返回 转成map
     * @param request
     * @return
     */
    public static Map<String, String> toAliMap(HttpServletRequest request) {
        Map<String, String> result = new HashMap<>();
        Map<String, String[]> requestParam = request.getParameterMap();
        for (Iterator<String> iter = requestParam.keySet().iterator(); iter.hasNext() ;) {
            String name = iter.next();
            String[] value = requestParam.get(name);
            String valueStr = "";
            for (int i = 0; i < value.length; i++) {
                valueStr += i == value.length - 1 ? value[i] : value[i] + ",";
            }
            result.put(name, valueStr);
        }
        return result;
    }
}
