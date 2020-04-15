package com.jeecms.wdedu.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.dao.BatchDao;
import com.jeecms.wdedu.entity.TCeeBatch;

import java.util.List;

public class BatchDaoImpl extends HibernateBaseDao<TCeeBatch, Long> implements BatchDao {
    @Override
    protected Class<TCeeBatch> getEntityClass() {
        return TCeeBatch.class;
    }

    @Override
    public Pagination getBatchPage(Integer province, Integer year, Integer majorType, int pageNo, int pageSize) {
        String hql="from TCeeBatch bean where 1=1 ";
        Finder f=Finder.create(hql);
        if(null != province){
            f.append(" and bean.provinceId=:province").setParam("province", province);
        }
        if(null != year){
            f.append(" and bean.year=:year").setParam("year", year);
        }
        if(null != majorType){
            f.append(" and bean.majorTypeId=:majorType").setParam("majorType", majorType);
        }
        f.append(" ORDER BY provinceId,year DESC");

        return find(f, pageNo, pageSize);
    }

    @Override
    public List getBatchInfo(Integer year, Integer province, Integer majorType) {
        String hql = "select batchId, batchName from TCeeBatch where 1=1 ";
        Finder f=Finder.create(hql);
        if (year != null) {
            f.append(" and year =:year").setParam("year", year);
        }
        if (province != null) {
            f.append(" and provinceId =:provinceId").setParam("provinceId", province);
        }
        if (majorType != null) {
            f.append(" and majorTypeId =:majorTypeId").setParam("majorTypeId", majorType);
        }
        return find(f);
    }

    @Override
    public List<TCeeBatch> queryBatchList(Integer year, Integer provinceId, Integer majorTypeId) {
        String hql = "from TCeeBatch where isShow=1 ";
        Finder f=Finder.create(hql);
        if (year != null) {
            f.append(" and year =:year").setParam("year", year);
        }
        if (provinceId != null) {
            f.append(" and provinceId =:provinceId").setParam("provinceId", provinceId);
        }
        if (majorTypeId != null) {
            f.append(" and majorTypeId =:majorTypeId").setParam("majorTypeId", majorTypeId);
        }
        f.append(" order by order");
        return find(f);
    }
}
