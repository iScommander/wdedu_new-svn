package com.wdedu.service;

import com.jeecms.common.page.Pagination;

import java.util.List;
import java.util.Map;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉科学选科service
 *
 * @author whb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface ICourseService {

	
	/**
	 * 功能描述: <br>
	 *根据班级classes查询活动id
	 *
	 */
	public List<Map<String,Object>> selectByCode(String sql);
	
	
	public Pagination findkmList(Integer provinceId, Integer year, Integer stuid, String str, Integer pageNo, Integer pageSize, String univ_name, String major_name);


	public Pagination findXkList(Integer provinceId, Integer year, Integer stuid, String str, Integer limitType, String andOr0, String unlimited0, Integer pageNo, Integer pageSize, String univ_name, String major_name);


	public Pagination findZyList(Integer provinceId, Integer year, Integer stuid, String str, Integer pageNo, Integer pageSize, String univ_name, String major_name);
	
	public List<Map<String,Object>>  selectFirstList();
	
	public List<Map<String,Object>>  selectSecondList();
	
	public List<Map<String,Object>>  selectThirdList();
	
	
	
	
}
