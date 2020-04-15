package com.jeecms.wdedu.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.wdedu.dao.TCeeEnrollMajorDao;
import com.jeecms.wdedu.entity.TCeeEnrollMajorList;

import java.util.List;

public class TCeeEnrollMajorDaoImpl extends HibernateBaseDao<TCeeEnrollMajorList, Integer> implements TCeeEnrollMajorDao {
    @Override
    protected Class<TCeeEnrollMajorList> getEntityClass() {
        return TCeeEnrollMajorList.class;
    }

    @Override
    public List getPlanList(Integer univListId,Integer year) {
        String hql = "from TCeeEnrollMajorList where 1=1 and planOrHistory=1 and dataType=1";
        Finder f=Finder.create(hql);
        if (univListId != null) {
            f.append(" and univListId =:univListId").setParam("univListId", univListId);
        }
        if (year != null) {
            f.append(" and year =:year").setParam("year", year);
        }
        return find(f);
    }

    @Override
    public List getYearsAgo(Integer univListId, Integer year) {
        String hql = "from TCeeEnrollMajorList where 1=1 and planOrHistory=2 and dataType=1 ";
        Finder f=Finder.create(hql);
        if (year != null) {
            f.append(" and year =:year").setParam("year", year);
        }
        if (univListId != null) {
            f.append(" and univListId =:univListId").setParam("univListId", univListId);
        }

        f.append(" order by histLowScore desc") ;
        return find(f);
    }
}
