package com.jeecms.wdedu.dao;

import com.jeecms.wdedu.entity.TDataMajor;

import java.util.List;

public interface FindMajorDao {
    List<TDataMajor> findBen();
    List<TDataMajor> findZhuan();
    List<TDataMajor> finBenNext(String parentMajorId);
    List<TDataMajor> findBenXiang(String majorId);
}
