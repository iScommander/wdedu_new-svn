package com.jeecms.wdedu.service.impl;

import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.dao.*;
import com.jeecms.wdedu.service.BkckSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BkckSvcImpl implements BkckSvc {
    @Autowired
    private BatchDao batchDao;
    @Autowired
    private EnrollPlanDao enrollPlanDao;
    @Autowired
    private EnrollUnivListDao enrollUnivListDao;
    @Autowired
    private DataUniversityDao dataUniversityDao;

    @Override
    public Pagination getBatchPage(Integer province, Integer year, Integer majorType, int pageNo, int pageSize) {
        Pagination page = batchDao.getBatchPage(province, year, majorType, pageNo, pageSize);
        return page;
    }

    @Override
    public Pagination getPlanPage(int year, Integer province, Integer majorType, Integer batch, String univName, String majorName, int pageNo, int pageSize) {
        Pagination page = enrollPlanDao.getPlanPage(year, province, majorType, batch, univName, majorName, pageNo, pageSize);
        return page;
    }

    @Override
    public List getMajorDetail(Integer year, Integer province, Integer majorType, Integer batch, Integer univId) {
        List page = enrollPlanDao.getMajorList(year,province,majorType,batch,univId);
        return page;
    }

    @Override
    public Pagination getHistPage(Integer year, Integer province, Integer majorType, Integer batch, String univName, String majorName, int pageNo, int pageSize) {
        Pagination page = enrollUnivListDao.getHistPage(year, province, majorType, batch, univName, majorName, pageNo, pageSize);
        return page;
    }

    @Override
    public Pagination getRulesPage(Integer province, Integer is211, Integer is985, Integer isFirstSchool, Integer isFirstMajor, String univName, int pageNo, int pageSize) {
        Pagination page = dataUniversityDao.getRulesPage(province, is211, is985, isFirstSchool, isFirstMajor, univName, pageNo, pageSize);
        return page;
    }

    @Override
    public List getBatchInfo(Integer year, Integer province, Integer majorType) {
        List batchInfo = batchDao.getBatchInfo(year,province,majorType);
        return batchInfo;
    }

}
