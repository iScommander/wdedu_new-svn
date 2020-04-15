package com.jeecms.wdedu.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.wdedu.dao.UniversityTeamDao;
import com.jeecms.wdedu.entity.TDataUniversityDetail;
import com.jeecms.wdedu.entity.TDataUniversityRank;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/18
 */
public class UniversityTeamDaoImpl extends HibernateBaseDao<TDataUniversityDetail, Integer> implements UniversityTeamDao {
    @Override
    protected Class<TDataUniversityDetail> getEntityClass() {
        return null;
    }

    @Override
    public List<TDataUniversityDetail> getList() {
        String hql="SELECT id,univName FROM TDataUniversityDetail";
        Finder f=Finder.create(hql);
        f.setCacheable(true);
        return find(f);
    }

    @Override
    public List<TDataUniversityDetail> findUvinSzll(String univIds) {
        Finder f = Finder.create("select szll from TDataUniversityDetail bean where 1=1");

        if (!StringUtils.isEmpty(univIds)) {
            List<Integer> array = new ArrayList<>();

            String[] ids = univIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                array.add(Integer.parseInt(ids[i]));
            }

            f.append(" and bean.id in :ids").setParamList("ids", array.toArray());

            f.append(" order by bean.id");
        }

        return find(f);
    }
}
