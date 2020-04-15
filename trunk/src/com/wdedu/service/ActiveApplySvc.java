package com.wdedu.service;

import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.entity.TScActiveDetail;
import com.wdedu.entity.TDataActivityDetailEntity;

import java.util.List;
import java.util.Map;

public interface ActiveApplySvc {
    List<Map<String, Object>> findAppointment(String telephone);

    List<TDataActivityDetailEntity> findList();

    Pagination findActive(int pageNo, int pageSize);

    List<TScActiveDetail> myActive(String phone);
}
