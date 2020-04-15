package com.wdedu.service.impl;


import com.jeecms.wdedu.entity.TDataEmployCompany;
import com.jeecms.wdedu.entity.TDataEmployIndex;
import com.jeecms.wdedu.entity.TDataMajor;
import com.wdedu.dao.FindMajorDao;
import com.wdedu.service.FindMajorSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
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

    @Override
    public List<TDataMajor> findByMajorName(String majorName) {
        return this.findMajorDao.findByMajorName(majorName);
    }

    @Override
    public List<Map<String, Object>> findOnelevelRank(String onelevelId) {
        return this.findMajorDao.findOnelevelRank(onelevelId);
    }

    @Override
    public List<TDataEmployIndex> findCatelogRank(String catelogId) {
        return this.findMajorDao.findCatelogRank(catelogId);
    }

    @Override
    public List<TDataMajor> findSecond(String majorId) {
        return this.findMajorDao.findSecond(majorId);
    }

    @Override
    public List<TDataMajor> findThree(String majorId) {
        return this.findMajorDao.findThree(majorId);
    }

    @Override
    public List<TDataEmployCompany> findSalay(String majorId) {
        return this.findMajorDao.findSalay(majorId);
    }

    @Override
    public List<TDataEmployCompany> findExperience(String majorId) {
        return this.findMajorDao.findExperience(majorId);
    }

    @Override
    public List<TDataEmployCompany> findEducation(String majorId) {
        return this.findMajorDao.findEducation(majorId);
    }


}
