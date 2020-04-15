package com.jeecms.wdedu.dao.impl;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.wdedu.dao.DemoDao;
import com.jeecms.wdedu.entity.Demo;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/16
 */
public class DemoDaoImpl extends HibernateBaseDao<Demo, Integer> implements DemoDao {
    @Override
    protected Class<Demo> getEntityClass() {
        return Demo.class;
    }
}
