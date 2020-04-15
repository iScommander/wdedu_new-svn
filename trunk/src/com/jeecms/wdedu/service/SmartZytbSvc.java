package com.jeecms.wdedu.service;

import com.jeecms.wdedu.entity.*;

import java.util.List;

public interface SmartZytbSvc {
    public TCeeBatch getBatch(Integer batchYear, Integer provinceId, Integer majorTypeId, Integer batchId);

    public TCeeApplicationsRequire getApplicationRequire(String applicationId, Integer batchId);

    public int getBatchMaxScore(Integer planYear, Integer provinceId, Integer batchId, Integer majorTypeId);

    public int getRank(Integer majorTypeId, Integer provinceId, Integer scoreYear, Integer score);

    public List<TCeeEnrollUnivListMiddle> getLastUniv(Integer upperScoreRank, Integer lowerScoreRank,TCeeApplications tCeeApplications,TCeeApplicationsRequire tCeeApplicationsRequire);

    public List<TCeeEnrollUnivListMiddle> getUnivPSK(List<TCeeEnrollUnivListMiddle> tCeeEnrollUnivListMiddles, Integer gradeRankP, Integer gradeRankS, Integer gradeRankK);

    public void setUnivListUser(List<TCeeEnrollUnivListMiddle> tCeeEnrollUnivListMiddles, TCeeApplications tCeeApplications,TCeeApplicationsRequire tCeeApplicationsRequire);

    public void setUnivListChooseUniv(TCeeApplications tCeeApplications,TCeeApplicationsRequire tCeeApplicationsRequire ,TCeeBatch tCeeBatch);

    public void setUnivListChooseMajor(TCeeApplications tCeeApplications, TCeeApplicationsRequire tCeeApplicationsRequire, TCeeBatch tCeeBatch);

    public void doInsertApplicationDetail(TCeeApplications tCeeApplications, TCeeApplicationsRequire tCeeApplicationsRequire, TCeeBatch tCeeBatch);
}
