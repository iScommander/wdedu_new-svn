package com.jeecms.wdedu.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.wdedu.dao.CareerDao;
import com.jeecms.wdedu.entity.TDataCareer;

import java.util.List;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/18
 */
public class CareerDaoImpl extends HibernateBaseDao<TDataCareer, Integer> implements CareerDao {
    @Override
    protected Class<TDataCareer> getEntityClass() {
        return TDataCareer.class;
    }

    @Override
    public List<TDataCareer> getList() {
        String hql="SELECT DISTINCT class1, class2 FROM TDataCareer";
        Finder f=Finder.create(hql);
        f.setCacheable(true);
        return find(f);
    }
}
