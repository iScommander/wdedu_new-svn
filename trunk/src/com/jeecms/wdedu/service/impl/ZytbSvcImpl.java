package com.jeecms.wdedu.service.impl;

import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.dao.*;
import com.jeecms.wdedu.entity.TCeeApplications;
import com.jeecms.wdedu.entity.TCeeApplicationsDetail;
import com.jeecms.wdedu.entity.TCeeBatch;
import com.jeecms.wdedu.service.ZytbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/19
 */
@Service
public class ZytbSvcImpl implements ZytbSvc {

    @Autowired
    private TCeeApplicationsDao tCeeApplicationsDao;

    @Autowired
    private TCeeApplicationsDetailDao tCeeApplicationsDetailDao;

    @Autowired
    private TCeeEnrollUnivListDao tCeeEnrollUnivListDao;

    @Autowired
    private TCeeEnrollMajorDao tCeeEnrollMajorDao;

    @Autowired
    private BatchDao batchDao;

    @Autowired
    private TDataMajorDao tDataMajorDao;

    @Override
    public Pagination queryProjects(Integer userId, String applicationName, int pageNo, int pageSize) {
        Pagination page = tCeeApplicationsDao.getPage(userId, applicationName, pageNo, pageSize);
        return page;
    }

    @Override
    public TCeeApplicationsDetail queryProjectDetail(Integer applicationId) {
        return tCeeApplicationsDetailDao.queryProjectDetail(applicationId);
    }

    @Override
    public void deleteProjectById(Integer applicationId) {
        tCeeApplicationsDao.deleteProject(applicationId);
    }

    @Override
    public TCeeApplicationsDetail saveProjectDetail(TCeeApplicationsDetail bean) {
        return tCeeApplicationsDetailDao.save(bean);
    }

    @Override
    public void saveProject(TCeeApplications bean) {
        tCeeApplicationsDao.save(bean);
    }

    @Override
    public List getBatchInfo(Map map) {
        return tCeeApplicationsDao.getBatchInfo(map);
    }

    @Override
    public List getScoreAndRankFromBatch(Map<String, String> map) {
        return tCeeApplicationsDao.getScoreAndRankFromBatch(map);
    }

    @Override
    public Pagination getUnivInfo(Map requestMap, Integer year, Integer provinceId, Integer majorType, Integer batch, int pageNo, int pageSize) {
        Pagination pagination = tCeeEnrollUnivListDao.getUnivInfo(requestMap,year,provinceId,majorType,batch,pageNo,pageSize);
        return pagination;
    }
    @Override
    public Pagination getUnivInfo(Map requestMap, Integer year, String univProvince, Integer majorType, Integer batch,Integer yxChoose , int pageNo, int pageSize) {
        Pagination pagination = tCeeEnrollUnivListDao.getUnivInfo(requestMap,year,univProvince,majorType,batch, yxChoose ,pageNo,pageSize);
        return pagination;
    }
    @Override
    public List getPlanList(Integer univListId,Integer year) {
        return tCeeEnrollMajorDao.getPlanList(univListId, year);
    }

    @Override
    public List getYearsAgo(Integer univListId, Integer year) {
        return tCeeEnrollMajorDao.getYearsAgo(univListId,year);
    }

    @Override
    public List<TCeeBatch> getBatchList(Integer year, Integer provinceId, Integer majorTypeId) {
        return batchDao.queryBatchList(year, provinceId, majorTypeId);
    }

    @Override
    public List getUnivNum(int id) {
        return tCeeApplicationsDetailDao.getUnivNum(id);
    }

    @Override
    public List<TCeeApplicationsDetail> queryProjectDetailList(Integer applicationId) {
        return tCeeApplicationsDetailDao.queryProjectDetailList(applicationId);
    }

    @Override
    public Pagination getUnivList(Map requestMap, List univList, int pageNo, int pageSize, String isSelectd, Integer year, Integer provinceId, Integer majorType, Integer batch) {
        return tCeeEnrollUnivListDao.getUnivList(requestMap, univList, pageNo, pageSize, isSelectd, year, provinceId, majorType, batch);
    }

    @Override
    public Pagination getLevelUnivList(Map requestMap, String isSelectd, Integer level, Integer year, Integer provinceId, Integer majorType, Integer batchId, int pageNo, int pageSize) {
        return tCeeEnrollUnivListDao.getLevelUnivList(requestMap, isSelectd, level, year,provinceId,majorType,batchId, pageNo, pageSize);
    }

    @Override
    public List getMajorList(String majorId) {
        return tDataMajorDao.getMajorList(majorId);
    }
}
