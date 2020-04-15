package com.jeecms.wdedu.dao;

import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.entity.TCeeBatch;

import java.util.List;

public interface BatchDao {
    Pagination getBatchPage(Integer province, Integer year, Integer majorType, int pageNo, int pageSize);

    List getBatchInfo(Integer year, Integer province, Integer majorType);

    List<TCeeBatch> queryBatchList(Integer year, Integer provinceId, Integer majorTypeId);
}
