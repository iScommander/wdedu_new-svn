package com.jeecms.wdedu.dao;

import com.jeecms.common.page.Pagination;

import java.util.List;
import java.util.Map;

public interface TCeeEnrollUnivListDao {
    Pagination getUnivInfo(Map requestMap, Integer year, Integer provinceId, Integer majorType, Integer batch, int pageNo, int pageSize);
    Pagination getUnivInfo(Map requestMap, Integer year, String univProvince, Integer majorType, Integer batch,Integer yxChoose , int pageNo, int pageSize);

    Pagination getUnivList(Map requestMap, List univList, int pageNo, int pageSize, String isSelectd, Integer year, Integer provinceId, Integer majorType, Integer batch);

    Pagination getLevelUnivList(Map requestMap, String isSelectd, Integer level, Integer year, Integer provinceId, Integer majorType, Integer batchId, int pageNo, int pageSize);
}
