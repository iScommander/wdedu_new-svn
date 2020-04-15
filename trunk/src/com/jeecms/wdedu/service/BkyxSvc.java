package com.jeecms.wdedu.service;

import com.jeecms.wdedu.entity.TBaseProvince;
import com.jeecms.wdedu.entity.TDataMajor;
import com.jeecms.wdedu.entity.TDataUniversityType;
import com.jeecms.wdedu.entity.TMgrSrvNode;

import java.util.List;

/**
 * @author fanyue
 * @ProjectName wdedu
 * @Description: 报考意向
 * @date 2018/10/17
 */
public interface BkyxSvc {
    List<TBaseProvince> queryProvince();
    List<TDataUniversityType> queryUniType();
    List<TDataMajor> queryDataMajorAll();
    List<TDataMajor> queryDataMajorZ();
    List<TDataMajor> queryDataMajorB();
    boolean save(TMgrSrvNode bkyx);
}
