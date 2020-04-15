package com.jeecms.cms.api.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;

import com.jeecms.common.util.AES128Util;
import com.jeecms.common.util.Num62;
import com.jeecms.common.util.PayUtil;
import com.jeecms.common.web.HttpClientUtil;

public class TestAccount {

	public static void main(String[] args) {
		//testDrawList();
		//testDrawApply();
		//testDrawDel();
		//testGetAccountInfo();
	}
	
	private static String testDrawList(){
		String url="http://192.168.0.173:8080/jeecmsv8f/api/draw/list.jspx";
		StringBuffer paramBuff=new StringBuffer();
		paramBuff.append("&first="+"0");
		paramBuff.append("&count="+5);
		paramBuff.append("&appId="+appId);
		Map<String, String>param=new HashMap<String, String>();
		String []params=paramBuff.toString().split("&");
		for(String p:params){
			String[] keyValue = p.split("=");
			if(keyValue.length==2){
				param.put(keyValue[0], keyValue[1]);
			}
		}
		String encryptSessionKey="";
		try {
			encryptSessionKey=AES128Util.encrypt(sessionKey, aesKey,ivKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		paramBuff.append("&sessionKey="+encryptSessionKey);
		param.put("sessionKey", encryptSessionKey);
		String res= HttpClientUtil.postParams(url, param);
		System.out.println("res->"+res);
		return res;
	}
	
	private static String testDrawApply(){
		String url="http://192.168.0.173:8080/jeecmsv8f/api/draw/apply.jspx";
		StringBuffer paramBuff=new StringBuffer();
		paramBuff.append("drawAmout="+1);
		paramBuff.append("&appId="+appId);
		String nonce_str=RandomStringUtils.random(16,Num62.N62_CHARS);
		paramBuff.append("&nonce_str="+nonce_str);
		String encryptSessionKey="";
		try {
			encryptSessionKey=AES128Util.encrypt(sessionKey, aesKey,ivKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		paramBuff.append("&sessionKey="+encryptSessionKey);
		Map<String, String>param=new HashMap<String, String>();
		String []params=paramBuff.toString().split("&");
		for(String p:params){
			String[] keyValue = p.split("=");
			if(keyValue.length==2){
				param.put(keyValue[0], keyValue[1]);
			}
		}
		String sign=PayUtil.createSign(param, appKey);
		param.put("sign", sign);
		String res= HttpClientUtil.postParams(url, param);
		System.out.println("res->"+res);
		return res;
	}
	
	private static String testDrawDel(){
		String url="http://192.168.0.173:8080/jeecmsv8f/api/draw/delete.jspx";
		StringBuffer paramBuff=new StringBuffer();
		paramBuff.append("ids="+3);
		//paramBuff.append("ids="+"1,2");
		paramBuff.append("&appId="+appId);
		String nonce_str=RandomStringUtils.random(16,Num62.N62_CHARS);
		paramBuff.append("&nonce_str="+nonce_str);
		Map<String, String>param=new HashMap<String, String>();
		String []params=paramBuff.toString().split("&");
		for(String p:params){
			String[] keyValue = p.split("=");
			if(keyValue.length==2){
				param.put(keyValue[0], keyValue[1]);
			}
		}
		String encryptSessionKey="";
		try {
			encryptSessionKey=AES128Util.encrypt(sessionKey, aesKey,ivKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		paramBuff.append("&sessionKey="+encryptSessionKey);
		param.put("sessionKey", encryptSessionKey);
		String sign=PayUtil.createSign(param, appKey);
		paramBuff.append("&sign="+sign);
		param.put("sign", sign);
		String res= HttpClientUtil.postParams(url, param);
		System.out.println("res->"+res);
		return res;
	}
	
	private static String testGetAccountInfo(){
		String url="http://192.168.0.173:8080/jeecmsv8f/api/account/get.jspx";
		StringBuffer paramBuff=new StringBuffer();
		paramBuff.append("appId="+appId);
		Map<String, String>param=new HashMap<String, String>();
		String []params=paramBuff.toString().split("&");
		for(String p:params){
			String[] keyValue = p.split("=");
			if(keyValue.length==2){
				param.put(keyValue[0], keyValue[1]);
			}
		}
		String encryptSessionKey="";
		try {
			encryptSessionKey=AES128Util.encrypt(sessionKey, aesKey,ivKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		paramBuff.append("&sessionKey="+encryptSessionKey);
		param.put("sessionKey", encryptSessionKey);
		String res= HttpClientUtil.postParams(url, param);
		System.out.println("res->"+res);
		return res;
	}
	
	
	
	private static String appId="111111";
	private static String appKey="Sd6qkHm9o4LaVluYRX5pUFyNuiu2a8oi";
	private static String sessionKey="9CBB23E7490F2053418499E9A7079ACE";
	private static String aesKey="S9u978Q31NGPGc5H";
	private static String ivKey="X83yESM9iShLxfwS";
}
