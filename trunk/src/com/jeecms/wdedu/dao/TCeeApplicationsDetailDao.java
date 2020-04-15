package com.jeecms.wdedu.dao;

import com.jeecms.wdedu.entity.TCeeApplicationsDetail;

import java.util.List;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @date 2018/10/19
 */
public interface TCeeApplicationsDetailDao {
    TCeeApplicationsDetail queryProjectDetail(Integer applicationId);

    TCeeApplicationsDetail save(TCeeApplicationsDetail bean);

    List getUnivNum(int id);

    List<TCeeApplicationsDetail> queryProjectDetailList(Integer applicationId);
}
