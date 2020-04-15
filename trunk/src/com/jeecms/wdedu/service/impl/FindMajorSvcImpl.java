package com.jeecms.wdedu.service.impl;

import com.jeecms.wdedu.dao.FindMajorDao;
import com.jeecms.wdedu.entity.TDataMajor;
import com.jeecms.wdedu.service.FindMajorSvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FindMajorSvcImpl implements FindMajorSvc {
    @Autowired
    private FindMajorDao findMajorDao;
    @Override
    public List<TDataMajor> findBen() {
        return this.findMajorDao.findBen();
    }

    @Override
    public List<TDataMajor> findZhuan() {
        return this.findMajorDao.findZhuan();
    }

    @Override
    public List<TDataMajor> finBenNext(String parentMajorId) {
        return this.findMajorDao.finBenNext(parentMajorId);
    }

    @Override
    public List<TDataMajor> findBenXiang(String majorId) {
        return this.findMajorDao.findBenXiang(majorId);
    }
}
