package com.jeecms.wdedu.dao;

import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.entity.TCeeApplications;

import java.util.List;
import java.util.Map;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @date 2018/10/19
 */
public interface TCeeApplicationsDao {
    Pagination getPage(Integer userId, String applicationName, int pageNo, int pageSize);

    TCeeApplications deleteProject(Integer applicationId);

    void save(TCeeApplications bean);

    List getBatchInfo(Map map);

    List getScoreAndRankFromBatch(Map<String, String> map);
}
