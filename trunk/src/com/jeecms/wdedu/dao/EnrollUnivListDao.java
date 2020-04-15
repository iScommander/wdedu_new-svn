package com.jeecms.wdedu.dao;

import com.jeecms.common.page.Pagination;

public interface EnrollUnivListDao {
    public Pagination getHistPage(Integer year, Integer province, Integer majorType, Integer batch, String univName, String majorName, int pageNo, int pageSize);
}
