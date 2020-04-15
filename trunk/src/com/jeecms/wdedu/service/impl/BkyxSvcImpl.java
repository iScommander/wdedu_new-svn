package com.jeecms.wdedu.service.impl;

import com.jeecms.wdedu.dao.impl.BkyxDaoImpl;
import com.jeecms.wdedu.entity.TBaseProvince;
import com.jeecms.wdedu.entity.TDataMajor;
import com.jeecms.wdedu.entity.TDataUniversityType;
import com.jeecms.wdedu.entity.TMgrSrvNode;
import com.jeecms.wdedu.service.BkyxSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fanyue
 * @ProjectName wdedu
 * @Description: 报考意向
 * @date 2018/10/17
 */

@Service
public class BkyxSvcImpl implements BkyxSvc {

    @Autowired
    private BkyxDaoImpl bkyxDaoImpl;


    @Override
    public List<TBaseProvince> queryProvince() {
        return this.bkyxDaoImpl.queryProvince();
    }

    @Override
    public List<TDataUniversityType> queryUniType() {
        return this.bkyxDaoImpl.queryUniType();
    }

    @Override
    public List<TDataMajor> queryDataMajorAll() {
        return this.bkyxDaoImpl.queryDataMajorAll();
    }

    @Override
    public List<TDataMajor> queryDataMajorZ() {
        return this.bkyxDaoImpl.queryDataMajorZ();
    }

    @Override
    public List<TDataMajor> queryDataMajorB() {
        return this.bkyxDaoImpl.queryDataMajorB();
    }

    @Override
    public boolean save(TMgrSrvNode bkyx) {
        return this.bkyxDaoImpl.save(bkyx);
    }
}
