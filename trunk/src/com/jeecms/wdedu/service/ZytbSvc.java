package com.jeecms.wdedu.service;

import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.entity.TCeeApplications;
import com.jeecms.wdedu.entity.TCeeApplicationsDetail;
import com.jeecms.wdedu.entity.TCeeBatch;

import java.util.List;
import java.util.Map;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @Description: 志愿填报接口
 * @date 2018/10/19
 */
public interface ZytbSvc {
    Pagination queryProjects(Integer userId, String applicationName, int cpn, int pageSize);

    TCeeApplicationsDetail queryProjectDetail(Integer applicationId);

    void deleteProjectById(Integer applicationId);

    TCeeApplicationsDetail saveProjectDetail(TCeeApplicationsDetail bean);

    void saveProject(TCeeApplications bean);

    List getBatchInfo(Map map);

    List getScoreAndRankFromBatch(Map<String, String> map);

    Pagination getUnivInfo(Map requestMap, Integer year, Integer univProvince, Integer majorType, Integer batch, int cpn, int pageSize);
    Pagination getUnivInfo(Map requestMap, Integer year, String univProvince, Integer majorType, Integer batch,Integer yxChoose , int cpn, int pageSize);

    List getPlanList(Integer univListId,Integer year);

    List getYearsAgo(Integer univListId, Integer year);


    List<TCeeBatch> getBatchList(Integer year, Integer provinceId, Integer majorTypeId);

    List getUnivNum(int id);

    List<TCeeApplicationsDetail> queryProjectDetailList(Integer applicationId);

    Pagination getUnivList(Map requestMap, List univList, int cpn, int pageSize, String isSelectd, Integer year, Integer provinceId, Integer majorType, Integer batch);

    Pagination getLevelUnivList(Map requestMap, String isSelectd, Integer level, Integer year, Integer provinceId, Integer majorType, Integer batchId, int cpn, int pageSize);

    List getMajorList(String majorId);

    //void updateProjectDetail(List<TCeeApplicationsDetail> bean);
}
