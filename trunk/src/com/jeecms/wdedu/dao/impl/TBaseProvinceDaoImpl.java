package com.jeecms.wdedu.dao.impl;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.wdedu.dao.TBaseProvinceDao;
import com.jeecms.wdedu.entity.TBaseProvince;

import java.util.List;

public class TBaseProvinceDaoImpl extends HibernateBaseDao<TBaseProvince, Long> implements TBaseProvinceDao {
    @Override
    protected Class<TBaseProvince> getEntityClass() {
        return TBaseProvince.class;
    }

    @Override
    public List<TBaseProvince> getProvinceList() {
        String hql="from TBaseProvince";
        return find(hql);
    }
}
