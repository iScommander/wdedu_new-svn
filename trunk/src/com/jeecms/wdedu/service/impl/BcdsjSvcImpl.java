package com.jeecms.wdedu.service.impl;

import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.dao.*;
import com.jeecms.wdedu.entity.TDataCareer;
import com.jeecms.wdedu.entity.TDataUniversityDetail;
import com.jeecms.wdedu.service.BcdsjSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @date 2018/10/17
 */
@Service
public class BcdsjSvcImpl implements BcdsjSvc {

    @Autowired
    private UniversityRankDao universityRankDao;

    @Autowired
    private ProfessionRankDao professionRankDao;

    @Autowired
    private ProfessionAndLabDao professionAndLabDao;

    @Autowired
    private UniversityTeamDao universityTeamDao;

    @Autowired
    private CareerDao careerDao;

    //@Autowired
    //public void setDao(UniversityRankDao universityRankDao) {
    //    this.universityRankDao = universityRankDao;
    //}

    @Override
    public Pagination queryUniversityRank(Integer type, String univName, String univTypes, String univProvinces, int pageNo, int pageSize) {
        Pagination page = universityRankDao.getPage(type, univName, univTypes
                , univProvinces, pageNo, pageSize);
        return page;
    }

    @Override
    public Pagination queryUniversityEnrollRank(String univName, Integer planYear, Integer majorTypeId, Integer provinceId, Integer tabType, String univProvince, String univType, int pageNo, int pageSize) {
        Pagination page = universityRankDao.getEnrollPage(univName, planYear, majorTypeId
                , provinceId, tabType, univProvince, univType, pageNo, pageSize);
        return page;
    }

    @Override
    public Pagination queryUniversitySalaryRank(String univName, Integer planYear, Integer provinceId, String univLevel, Integer tabType, String univProvinces, String univTypes, int pageNo, int pageSize) {
        Pagination page = universityRankDao.getSalaryPage(univName, planYear
                , provinceId, univLevel, tabType, univProvinces, univTypes, pageNo, pageSize);
        return page;
    }

    @Override
    public Pagination queryProfessionRank(String univName, String majorName, String undergraduate, int pageNo, int pageSize) {
        Pagination page = professionRankDao.getPage(univName, majorName, undergraduate, pageNo, pageSize);
        return page;
    }

    @Override
    public Pagination queryProfessionEnrollRank(Integer enrollYear, Integer provinceId, Integer majorTypeId, Integer tabType, String majorId,String univName,String majorName, int pageNo, int pageSize) {
        Pagination page = professionRankDao.getEnrollPage(enrollYear, provinceId, majorTypeId, tabType, majorId, univName, majorName, pageNo, pageSize);
        return page;
    }

    @Override
    public Pagination queryProfessionSalaryRank(Integer enrollYear, Integer provinceId, Integer majorTypeId, Integer tabType,String undergraduate, String majorId, String majorName, int pageNo, int pageSize) {
        Pagination page = professionRankDao.getSalaryPage(enrollYear, provinceId, majorTypeId, tabType, undergraduate,majorId, majorName, pageNo, pageSize);
        return page;
    }

    @Override
    public Pagination queryProfessionAndLab(String univName, String majorName, int pageNo, int pageSize) {
        Pagination page = professionAndLabDao.getPage(univName, majorName, pageNo, pageSize);
        return page;
    }

    @Override
    public List<TDataUniversityDetail> queryUniversityTeam() {
        List<TDataUniversityDetail> list = universityTeamDao.getList();
        return list;
    }

    @Override
    public List<TDataCareer> queryAllProfessions() {
        List<TDataCareer> list = careerDao.getList();
        return list;
    }
}
