package com.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;



/**
 * 系统工具类。
 * 
 * @author carver.gu
 * @since 1.0, Sep 12, 2009
 */
public abstract class GetDataUtils {

    private static String CHAR_SET = HTTP.UTF_8;

    private GetDataUtils() {

    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, Map<String, String> map, String charset) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            // 设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * json转map
     */
    public static List<Map<String, Object>> json2map(String res, String dataCellName) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        JSONObject jsonObj = JSONObject.parseObject(res, Feature.OrderedField);
        if (jsonObj != null) {
            // 得到指定json key对象的value对象
            if (jsonObj.containsKey(dataCellName)) {
                String data = jsonObj.get(dataCellName).toString();
                resultList =
                        JSON.parseObject(data, new TypeReference<List<Map<String, Object>>>() {});
            }
        }

        return resultList;
    }

    // 调用接口的例子
    public static void main(String[] args) {
        ApiHashMap createMap = new ApiHashMap();
        createMap.put("encrypt", "none");
        createMap.put("uName", "");
        createMap.put("batch", "");
        createMap.put("province", "");
        createMap.put("type", "");
        createMap.put("major", "");
        createMap.put("isJbw", "");
        createMap.put("isEyy", "");
        createMap.put("page", "1");
        createMap.put("num", "100");
        String res = sendPost("https://app.jseea.cn/Bus700101", createMap, CHAR_SET);
        System.out.println("返回值：" + res);

        List<Map<String, Object>> resultList = json2map(res, "doc");

        for (Map<String, Object> map : resultList) {
            System.out.println(map.get("name"));
            createMap = new ApiHashMap();
            createMap.put("encrypt", "none");
            createMap.put("batch", "0");
            createMap.put("code", map.get("code"));
            createMap.put("clazz", "2");
            res = sendPost("https://app.jseea.cn/Bus700301", createMap, CHAR_SET);
            
            List<Map<String, Object>> resultList2 = json2map(res, "doc");
            for (Map<String, Object> map2 : resultList2) {
                System.out.println(map2);
            }

            System.out.println();
        }


        System.out.println("结束");
    }

}
