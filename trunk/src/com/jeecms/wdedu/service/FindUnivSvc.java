package com.jeecms.wdedu.service;

import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.entity.TDataUniversity;
import com.jeecms.wdedu.entity.TDataUniversityDetail;
import com.jeecms.wdedu.entity.TDataUniversityRank;

import java.util.List;

public interface FindUnivSvc {
    Pagination getPage(int pageNo, int pageSize,Integer provinceId , String univType, String offOrVol, String univLevel, Integer is211, Integer is985, Integer isKeylab, Integer isIndependence, Integer isDefence,String location,String univName);
    List<TDataUniversity> tDataUnivList(Integer provinceId , String univType, String offORvol, String univLevel, Integer is211, Integer is985, Integer isKeylab, Integer isIndependence, Integer isDefence);
    List<TDataUniversityDetail> queryById(String univName);
    List<TDataUniversity> findUvinData(String univIds);

    List<TDataUniversityRank> findUvinRank(String univIds);

    List findUvinCount(String univIds);

    List<TDataUniversityDetail> findUvinSzll(String univIds);
}
