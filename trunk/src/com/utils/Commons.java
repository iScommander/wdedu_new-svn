package com.utils;

public interface Commons{
	boolean sendsms(String username, String sms_content);
	public void sendsmsByTemplate(Integer TemplateId, String phone, String... val) ;
}
