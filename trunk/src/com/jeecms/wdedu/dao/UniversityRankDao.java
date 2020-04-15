package com.jeecms.wdedu.dao;

import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.entity.TDataUniversityRank;

import java.util.List;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @date 2018/10/17
 */
public interface UniversityRankDao {
    Pagination getPage(Integer type,String univName, String univTypes, String univProvinces, int pageNo, int pageSize);

    Pagination getEnrollPage(String univName, Integer planYear, Integer grade, Integer provinceId, Integer tabType, String univProvince, String univType, int pageNo, int pageSize);

    Pagination getSalaryPage(String univName, Integer planYear, Integer provinceId,String univLevel, Integer tabType, String univProvinces, String univTypes, int pageNo, int pageSize);

    List<TDataUniversityRank> findUvinRank(String univIds);
}
