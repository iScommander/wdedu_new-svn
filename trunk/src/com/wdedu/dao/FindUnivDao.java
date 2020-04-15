package com.wdedu.dao;

import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.entity.TCeeEnrollHistory;
import com.jeecms.wdedu.entity.TDataUniversity;
import com.jeecms.wdedu.entity.TDataUniversityDetail;

import java.util.List;

public interface FindUnivDao {
    Pagination getPage(int pageNo, int pageSize, String provinceId, String univType, String offOrVol, String univLevel, Integer is211, Integer is985, Integer isKeylab, Integer isIndependence, Integer isDefence, String location, String univName,Integer isFirstRateUniv);
    List<TDataUniversity> tDataUnivList(Integer provinceId, String univType, String offORvol, String univLevel, Integer is211, Integer is985, Integer isKeylab, Integer isIndependence, Integer isDefence);
    List<TDataUniversityDetail> queryById(String univName);

    /*Pagination searchMajorHistory(int pageNo, int pageSize,String univName,Integer mjorTypeId,Integer year,Integer provinceId);*/
    List<TCeeEnrollHistory> searchMajorHistory(String univName,Integer mjorTypeId,Integer year,String provinceId);

    Pagination findUniv(int pageNo, int pageSize,String univMajorId);

}
