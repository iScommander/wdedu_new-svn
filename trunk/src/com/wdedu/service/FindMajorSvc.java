package com.wdedu.service;

import com.jeecms.wdedu.entity.TDataEmployCompany;
import com.jeecms.wdedu.entity.TDataEmployIndex;
import com.jeecms.wdedu.entity.TDataMajor;

import java.util.List;
import java.util.Map;

public interface FindMajorSvc {
    List<TDataMajor> findBen();
    List<TDataMajor> findZhuan();
    List<TDataMajor> finBenNext(String parentMajorId);
    List<TDataMajor> findBenXiang(String majorId);
    List<TDataMajor> findByMajorName(String majorName);
    List<Map<String, Object>> findOnelevelRank(String onelevelId);
    List<TDataEmployIndex> findCatelogRank(String catelogId);
    //2级
    List<TDataMajor> findSecond(String majorId);
    //3级
    List<TDataMajor> findThree(String majorId);

    List<TDataEmployCompany> findSalay(String majorId);
    List<TDataEmployCompany> findExperience(String majorId);
    List<TDataEmployCompany> findEducation(String majorId);
}
