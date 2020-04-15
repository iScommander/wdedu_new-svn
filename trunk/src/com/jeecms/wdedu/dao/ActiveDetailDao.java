package com.jeecms.wdedu.dao;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.page.Pagination;

public interface ActiveDetailDao {
    Pagination getPage(String proname, String cityname, String quxianname,int pageNo, int pageSize);
    Finder createFinder(String proname, String cityname, String quxianname);
}
