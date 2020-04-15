package com.jeecms.wdedu.dao;

import com.jeecms.common.page.Pagination;

/**
 * @ProjectName wdedu
 * @date 2018/10/18
 */
public interface ProfessionRankDao {

    Pagination getPage(String univName, String majorName,String undergraduate, int pageNo, int pageSize);

    Pagination getEnrollPage(Integer planYear, Integer provinceId, Integer majorTypeId, Integer tabType, String majorId,String univName,String majorName, int pageNo, int pageSize);

    Pagination getSalaryPage(Integer planYear, Integer provinceId, Integer majorTypeId, Integer tabType,String undergraduate, String majorId,String majorName, int pageNo, int pageSize);
}
