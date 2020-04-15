package com.wdedu.service;

import com.jeecms.wdedu.entity.TDataCareerRegion;
import com.jeecms.wdedu.entity.TDataSalaryCareer;
import com.wdedu.entity.TDataCareerIntroduceEntity;
import com.wdedu.entity.TDataCareerMapMajorEntity;

import java.util.List;
import java.util.Map;

public interface FindCareerSvc {
    List<TDataCareerIntroduceEntity> findCareer1();
    List<TDataCareerIntroduceEntity> findCareer2(String careerSubjeetId);
    List<TDataCareerIntroduceEntity> findCareer3(String careerSubjeetId);
    List<Map<String,Object>> findTwo(String careerSubjeetId);
    List<Map<String,Object>> findThr(String careerSubjeetId);
    List<TDataCareerMapMajorEntity> findCareerByMajor(String majorName);
    List<TDataCareerRegion> findAreaRank(String careerId);
    List<TDataSalaryCareer> findByYear(String careerId);
    List<TDataSalaryCareer> findStationSalary(String careerOnlevelId,String careerName);
}
