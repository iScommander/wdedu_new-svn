package com.jeecms.wdedu.service.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.dao.ActiveDetailDao;
import com.jeecms.wdedu.service.ActiveDetailSvc;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author fanyue
 * @ProjectName wdedu
 * @Description: 用户中心
 * @date 2018/10/19
 */
public class ActiveDetailSvcImpl implements ActiveDetailSvc {

    @Autowired
    private ActiveDetailDao activeDetailActDao;

    @Override
    public Pagination queryUniversityRank(String proname, String cityname, String quxianname, int pageNo, int pageSize) {
        Pagination page = activeDetailActDao.getPage(proname, cityname
                , quxianname, pageNo, pageSize);
        return page;
    }

    @Override
    public Finder createFinder(String proname, String cityname, String quxianname) {
        return this.activeDetailActDao.createFinder(proname, cityname, quxianname);
    }
}
