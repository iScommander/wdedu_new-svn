package com.jeecms.wdedu.service.impl;

import com.jeecms.wdedu.dao.SmartZytbDao;
import com.jeecms.wdedu.entity.*;
import com.jeecms.wdedu.service.CommonSvc;
import com.jeecms.wdedu.service.SmartZytbSvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class SmartZytbSvcImpl implements SmartZytbSvc {

    @Autowired
    private SmartZytbDao smartZytbDao;

    @Autowired
    private CommonSvc commonSvc;

    @Override
    public TCeeBatch getBatch(Integer batchYear, Integer provinceId, Integer majorTypeId, Integer batchId) {
        return this.smartZytbDao.getBatch(batchYear, provinceId, majorTypeId, batchId);
    }

    @Override
    public TCeeApplicationsRequire getApplicationRequire(String applicationId, Integer batchId) {
        return this.smartZytbDao.getApplicationRequire(applicationId, batchId);
    }

    @Override
    public int getBatchMaxScore(Integer planYear, Integer provinceId, Integer batchId, Integer majorTypeId) {
        return this.smartZytbDao.getBatchMaxScore(planYear, provinceId, batchId, majorTypeId);
    }

    @Override
    public int getRank(Integer majorTypeId, Integer provinceId, Integer scoreYear, Integer score) {
        return this.smartZytbDao.getRank(majorTypeId, provinceId, scoreYear, score);
    }

    @Override
    public List<TCeeEnrollUnivListMiddle> getLastUniv(Integer upperScoreRank, Integer lowerScoreRank,TCeeApplications tCeeApplications,TCeeApplicationsRequire tCeeApplicationsRequire) {
        return this.smartZytbDao.getLastUniv(upperScoreRank, lowerScoreRank, tCeeApplications, tCeeApplicationsRequire);
    }

    @Override
    public List<TCeeEnrollUnivListMiddle> getUnivPSK(List<TCeeEnrollUnivListMiddle> tCeeEnrollUnivListMiddles, Integer gradeRankP, Integer gradeRankS, Integer gradeRankK) {
        return this.smartZytbDao.getUnivPSK(tCeeEnrollUnivListMiddles, gradeRankP, gradeRankS, gradeRankK);
    }

    @Override
    public void setUnivListUser(List<TCeeEnrollUnivListMiddle> tCeeEnrollUnivListMiddles, TCeeApplications tCeeApplications, TCeeApplicationsRequire tCeeApplicationsRequire) {
        this.smartZytbDao.setUnivListUser(tCeeEnrollUnivListMiddles, tCeeApplications, tCeeApplicationsRequire);
    }

    @Override
    public void setUnivListChooseUniv(TCeeApplications tCeeApplications, TCeeApplicationsRequire tCeeApplicationsRequire, TCeeBatch tCeeBatch) {
        this.smartZytbDao.setUnivListChooseUniv(tCeeApplications, tCeeApplicationsRequire, tCeeBatch);
    }

    @Override
    public void setUnivListChooseMajor(TCeeApplications tCeeApplications, TCeeApplicationsRequire tCeeApplicationsRequire, TCeeBatch tCeeBatch) {
        this.smartZytbDao.setUnivListChooseMajor(tCeeApplications, tCeeApplicationsRequire, tCeeBatch);
    }

    @Override
    public void doInsertApplicationDetail(TCeeApplications tCeeApplications, TCeeApplicationsRequire tCeeApplicationsRequire, TCeeBatch tCeeBatch) {
        this.smartZytbDao.doInsertApplicationDetail(tCeeApplications, tCeeApplicationsRequire, tCeeBatch);
    }

}
