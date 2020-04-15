package com.jeecms.wdedu.service;

import com.jeecms.wdedu.entity.TDataMajor;

import java.util.List;

public interface FindMajorSvc {
    List<TDataMajor> findBen();
    List<TDataMajor> findZhuan();
    List<TDataMajor> finBenNext(String parentMajorId);
    List<TDataMajor> findBenXiang(String majorId);
}
