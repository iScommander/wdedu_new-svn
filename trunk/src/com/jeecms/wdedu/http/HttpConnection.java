package com.jeecms.wdedu.http;

/**
 * HTTP通信接口
 * 
 * @author xiaoxh
 * @link 
 * 
 * @version $Revision: 1.00 $ $Date: 2010-07-12
 */

public interface HttpConnection {
	
	ResBean getDataStream(String queryUrl);
	ResBean getDataStream(String queryUrl, int timeOut);
}
