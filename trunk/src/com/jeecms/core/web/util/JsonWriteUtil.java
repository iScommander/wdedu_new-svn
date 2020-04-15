package com.jeecms.core.web.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonWriteUtil {
	
	public static void write(HttpServletResponse response,Object o) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		response.setStatus(200);
		PrintWriter pw=response.getWriter();
		if(o==null) {
            JsonWriteUtil.write(response, "");
        } else {
            pw.write(write(o));
        }
			pw.flush();
			pw.close();
	}
	

	public static String write(Object o) throws IOException{
		if(o==null) {
            return null;
        } else if(o instanceof String || o instanceof Boolean || o instanceof Integer || o instanceof Double || o instanceof Double || o instanceof Float ){
			return String.valueOf(o);
		}
		else {
			return JsonUtil.toString(o);
		}
	}

}
