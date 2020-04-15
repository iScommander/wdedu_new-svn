package com.jeecms.wdedu.service;

import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.entity.TDataCareer;
import com.jeecms.wdedu.entity.TDataUniversityDetail;

import java.util.List;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @date 2018/10/17
 */
public interface BcdsjSvc {

    Pagination queryUniversityRank(Integer type,String univName, String univTypes, String univProvinces, int pageNo, int pageSize);

    Pagination queryUniversityEnrollRank(String univName, Integer planYear, Integer majorTypeId, Integer provinceId, Integer tabType, String univProvince, String univType, int pageNo, int pageSize);

    Pagination queryUniversitySalaryRank(String univName, Integer planYear, Integer provinceId,String univLevel, Integer tabType, String univProvinces, String univTypes, int pageNo, int pageSize);

    Pagination queryProfessionRank(String univName, String majorName,String undergraduate, int pageNo, int pageSize);

    Pagination queryProfessionEnrollRank(Integer planYear, Integer provinceId, Integer majorTypeId, Integer tabType, String majorId,String univName,String majorName, int pageNo, int pageSize);

    Pagination queryProfessionSalaryRank(Integer planYear, Integer provinceId, Integer majorTypeId, Integer tabType,String undergraduate, String majorId,String majorName, int pageNo, int pageSize);


    Pagination queryProfessionAndLab(String univName, String majorName, int cpn, int pageSize);

    List<TDataUniversityDetail> queryUniversityTeam();

    List<TDataCareer> queryAllProfessions();
}
