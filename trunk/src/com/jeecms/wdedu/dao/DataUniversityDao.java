package com.jeecms.wdedu.dao;

import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.entity.TDataUniversity;

import java.util.List;

public interface DataUniversityDao {
    public Pagination getRulesPage(Integer province, Integer is211, Integer is985, Integer isFirstSchool, Integer isFirstMajor, String univName, int pageNo, int pageSize);

    List<TDataUniversity> findUvinData(String univIds);
}
