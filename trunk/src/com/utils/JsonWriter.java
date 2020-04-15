package com.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.jeecms.core.web.util.JsonWriteUtil;

public class JsonWriter{

	public static void write(HttpServletResponse response,Object o) throws IOException{
		String s=com.alibaba.fastjson.JSON.toJSONString(o);
		response.setContentType("text/html;charset=UTF-8");
		response.setStatus(200);
		PrintWriter pw = response.getWriter();
		pw.write(s);
		pw.flush();
		pw.close();
	}
		
}
