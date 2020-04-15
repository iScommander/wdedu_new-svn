package com.wdedu.dao;

import java.util.List;
import java.util.Map;


/**
 * 
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉   科学选科dao
 *
 * @author whb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface ICourseDao {


	List<Map<String,Object>> findSqlList(String sql);
	

}
