package com.wdedu.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.entity.TDataUniversity;
import com.wdedu.dao.DataUniversityDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DataUniversityDaoImpl extends HibernateBaseDao<TDataUniversity, Long> implements DataUniversityDao {
    @Override
    protected Class<TDataUniversity> getEntityClass() {
        return TDataUniversity.class;
    }

    @Override
    public Pagination getRulesPage(Integer province, Integer is211, Integer is985, Integer isFirstSchool, Integer isFirstMajor, String univName, int pageNo, int pageSize) {

        String hql="FROM TDataUniversity a where 1=1 ";
        Finder f= Finder.create(hql);

        if(null != province){
            f.append(" and a.provinceId=:province").setParam("province", province);
        }
        if(StringUtils.isNotBlank(univName)){
            f.append(" and a.univName like '%" + univName + "%'");
        }

        if(null != is211){
            f.append(" and a.is211=1");
        }
        if(null != is985){
            f.append(" and a.is985=1");
        }

        if (null != isFirstSchool && null != isFirstMajor){
            f.append(" and a.isFirstRateUniv=1");
        }else if(null != isFirstSchool){
            f.append(" and a.isFirstRateUniv=1");
        }else if(null != isFirstMajor){
            f.append(" and a.isFirstRateUniv=2");
        }

        f.append(" AND SUBSTR(a.univId,-2)= 10 ");
        f.append(" ORDER BY a.universityBaseId");

        return find(f, pageNo, pageSize);
    }

    @Override
    public List<TDataUniversity> findUvinData(String univIds) {
        Finder f = Finder.create("from TDataUniversity bean where 1=1");

        if (!StringUtils.isEmpty(univIds)) {
            List<Integer> array = new ArrayList<>();

            String[] ids = univIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                array.add(Integer.parseInt(ids[i]));
            }

            f.append(" and bean.universityBaseId in :ids").setParamList("ids", array.toArray());

            f.append(" and substr(univId,-2)= 10");
            f.append(" order by bean.univId");
        }

        return find(f);
    }
}
