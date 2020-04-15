package com.jeecms.wdedu.dao;

import com.jeecms.common.page.Pagination;

import java.util.List;

public interface EnrollPlanDao {
    public Pagination getPlanPage(int year, Integer province, Integer majorType, Integer batch, String univName, String majorName, int pageNo, int pageSize);

    public List getMajorList(Integer year, Integer province, Integer majorType, Integer batch, Integer univId);
}
