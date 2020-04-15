package com.jeecms.wdedu.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.wdedu.dao.TDataMajorDao;
import com.jeecms.wdedu.entity.TDataMajor;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/31
 */
public class TDataMajorDaoImpl extends HibernateBaseDao<TDataMajor, Integer> implements TDataMajorDao {
    @Override
    protected Class<TDataMajor> getEntityClass() {
        return TDataMajor.class;
    }

    @Override
    public List getMajorList(String majorId) {
        String hql = "SELECT parentMajorId,majorId,majorName FROM TDataMajor WHERE 1=1";
        Finder f = Finder.create(hql);
        if (StringUtils.isNotBlank(majorId)) {
            f.append(" and parentMajorId =:majorId").setParam("majorId", majorId);
        }
        List<Object[]> list = find(f);
        List<Map> l = new ArrayList<>();
        for (Object[] o: list) {
            Map m = new HashMap();
            m.put("parentMajorId", o[0].toString());
            m.put("majorId", o[1].toString());
            m.put("majorName", o[2].toString());
            l.add(m);
        }
        return l;
    }
}
