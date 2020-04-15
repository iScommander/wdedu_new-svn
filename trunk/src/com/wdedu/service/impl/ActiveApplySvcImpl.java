package com.wdedu.service.impl;

import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.entity.TScActiveDetail;
import com.wdedu.dao.ActiveApplyDao;
import com.wdedu.entity.TDataActivityDetailEntity;
import com.wdedu.service.ActiveApplySvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ActiveApplySvcImpl implements ActiveApplySvc {
    @Autowired
    private ActiveApplyDao activeApplyDao;
    @Override
    public List<Map<String, Object>> findAppointment(String telephone) {
        return this.activeApplyDao.findAppointment(telephone);
    }

    @Override
    public List<TDataActivityDetailEntity> findList() {
        return this.activeApplyDao.findList();
    }

    @Override
    public Pagination findActive(int pageNo, int pageSize) {
        return  this.activeApplyDao.findActive(pageNo,pageSize);
    }

    @Override
    public List<TScActiveDetail> myActive(String phone) {
        return this.activeApplyDao.myActive(phone);
    }


}
