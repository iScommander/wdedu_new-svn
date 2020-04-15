package com.wdedu.service.impl;

import com.jeecms.wdedu.entity.TDataCareerRegion;
import com.jeecms.wdedu.entity.TDataSalaryCareer;
import com.wdedu.dao.FindCareerDao;
import com.wdedu.entity.TDataCareerIntroduceEntity;
import com.wdedu.entity.TDataCareerMapMajorEntity;
import com.wdedu.service.FindCareerSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FindCareerSvcImpl implements FindCareerSvc {
    @Autowired
    private FindCareerDao findCareerDao;
    @Override
    public List<TDataCareerIntroduceEntity> findCareer1() {
        return this.findCareerDao.findCareer1();
    }


    @Override
    public List<TDataCareerIntroduceEntity> findCareer2(String careerSubjeetId) {
        return this.findCareerDao.findCareer2(careerSubjeetId);
    }

    @Override
    public List<TDataCareerIntroduceEntity> findCareer3(String careerSubjeetId) {
        return this.findCareerDao.findCareer3(careerSubjeetId);
    }

    @Override
    public List<Map<String,Object>> findTwo(String careerSubjeetId) {
        return this.findCareerDao.findTwo(careerSubjeetId);
    }

    @Override
    public List<Map<String,Object>> findThr(String careerSubjeetId) {
        return this.findCareerDao.findThr(careerSubjeetId);
    }

    @Override
    public List<TDataCareerMapMajorEntity> findCareerByMajor(String majorName) {
        return this.findCareerDao.findCareerByMajor(majorName);
    }

    @Override
    public List<TDataCareerRegion> findAreaRank(String careerId) {
        return this.findCareerDao.findAreaRank(careerId);
    }

    @Override
    public List<TDataSalaryCareer> findByYear(String careerId) {
        return this.findCareerDao.findByYear(careerId);
    }

    @Override
    public List<TDataSalaryCareer> findStationSalary(String careerOnlevelId, String careerName) {
        return this.findCareerDao.findStationSalary(careerOnlevelId,careerName);
    }
}
