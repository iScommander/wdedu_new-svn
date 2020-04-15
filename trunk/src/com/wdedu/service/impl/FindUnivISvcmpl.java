package com.wdedu.service.impl;

import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.entity.TCeeEnrollHistory;
import com.jeecms.wdedu.entity.TDataUniversity;
import com.jeecms.wdedu.entity.TDataUniversityDetail;
import com.jeecms.wdedu.entity.TDataUniversityRank;
import com.wdedu.dao.*;
import com.wdedu.service.FindUnivSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindUnivISvcmpl implements FindUnivSvc {
    @Autowired
    private FindUnivDao findUnivDao;
    @Autowired
    private DataUniversityDao dataUniversityDao;
    @Autowired
    private UniversityRankDao universityRankDao;
    @Autowired
    private ProfessionAndLabDao professionAndLabDao;
    @Autowired
    private UniversityTeamDao universityTeamDao;

    @Override
    public Pagination getPage(int pageNo, int pageSize, String provinceId , String univType, String offOrVol, String univLevel, Integer is211, Integer is985, Integer isKeylab, Integer isIndependence, Integer isDefence, String location, String univName,Integer isFirstRateUniv) {
        Pagination page = findUnivDao.getPage(pageNo, pageSize, provinceId, univType, offOrVol, univLevel, is211, is985, isKeylab, isIndependence, isDefence,location,univName,isFirstRateUniv);
        return page;
    }

    @Override
    public List<TDataUniversity> tDataUnivList(Integer provinceId, String univType, String offORvol, String univLevel, Integer is211, Integer is985, Integer isKeylab, Integer isIndependence, Integer isDefence) {
        return this.findUnivDao.tDataUnivList(provinceId, univType, offORvol, univLevel, is211, is985, isKeylab, isIndependence, isDefence);
    }

    @Override
    public List<TDataUniversityDetail> queryById(String univName) {
        return this.findUnivDao.queryById(univName);
    }

    @Override
    public List<TDataUniversity> findUvinData(String univIds) {
        return this.dataUniversityDao.findUvinData(univIds);
    }

    @Override
    public List<TDataUniversityRank> findUvinRank(String univIds) {
        return this.universityRankDao.findUvinRank(univIds);
    }

    @Override
    public List findUvinCount(String univIds) {
        return this.professionAndLabDao.findUvinCount(univIds);
    }

    @Override
    public List<TDataUniversityDetail> findUvinSzll(String univIds) {
        return this.universityTeamDao.findUvinSzll(univIds);
    }

    @Override
    public List<TCeeEnrollHistory> searchMajorHistory( String univName, Integer mjorTypeId, Integer year, String provinceId) {
        List<TCeeEnrollHistory> list = findUnivDao.searchMajorHistory(univName,mjorTypeId,year,provinceId);
        return list;
    }

    @Override
    public Pagination findUniv(int pageNo, int pageSize, String univMajorId) {
        Pagination page = findUnivDao.findUniv(pageNo,pageSize,univMajorId);
        return  page;
    }
}
