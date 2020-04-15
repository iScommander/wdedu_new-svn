package com.jeecms.wdedu.service;

import com.jeecms.common.page.Pagination;

import java.util.List;

public interface BkckSvc {

    Pagination getBatchPage(Integer province, Integer year, Integer majorType, int pageNo, int pageSize);

    Pagination getPlanPage(int year, Integer province, Integer majorType, Integer batch, String univName, String majorName, int pageNo, int pageSize);

    List getMajorDetail(Integer year, Integer province, Integer majorType, Integer batch, Integer univId);

    Pagination getHistPage(Integer year, Integer province, Integer majorType, Integer batch, String univName, String majorName, int pageNo, int pageSize);

    Pagination getRulesPage(Integer province, Integer is211, Integer is985, Integer isFirstSchool, Integer isFirstMajor, String univName, int pageNo, int pageSize);

    List getBatchInfo(Integer year, Integer province, Integer majorType);
}
