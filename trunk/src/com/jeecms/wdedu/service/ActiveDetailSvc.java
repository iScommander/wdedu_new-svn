package com.jeecms.wdedu.service;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.page.Pagination;

public interface ActiveDetailSvc {
    Pagination queryUniversityRank(String proname, String cityname, String quxianname,int pageNo, int pageSize);
    Finder createFinder(String proname, String cityname, String quxianname);
}
