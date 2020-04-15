package com.jeecms.wdedu.dao;


import com.jeecms.wdedu.entity.TBaseProvince;
import com.jeecms.wdedu.entity.TDataMajor;
import com.jeecms.wdedu.entity.TDataUniversityType;
import com.jeecms.wdedu.entity.TMgrSrvNode;

import java.util.List;

public interface BkyxDao {
   List<TBaseProvince>  queryProvince();
   List<TDataUniversityType> queryUniType();
   List<TDataMajor> queryDataMajorAll();
   List<TDataMajor> queryDataMajorZ();
   List<TDataMajor> queryDataMajorB();
   boolean save(TMgrSrvNode bkyx);
}

