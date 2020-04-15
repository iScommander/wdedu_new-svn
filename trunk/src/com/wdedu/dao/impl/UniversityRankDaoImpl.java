package com.wdedu.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.entity.TDataUniversityRank;
import com.wdedu.dao.UniversityRankDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @Description: TODO
 * @date 2018/10/17
 */
@Repository
public class UniversityRankDaoImpl extends HibernateBaseDao<TDataUniversityRank, Integer> implements UniversityRankDao {


    @Override
    protected Class<TDataUniversityRank> getEntityClass() {
        return null;
    }

    @Override
    public Pagination getPage(String univName, String grade, Integer provinceId, int pageNo, int pageSize) {
        Finder f=createFinder(univName, grade, provinceId);
        return find(f, pageNo, pageSize);
    }

    @Override
    public List<TDataUniversityRank> findUvinRank(String univIds) {
        Finder f = Finder.create("from TDataUniversityRank bean where 1=1");

        if (!StringUtils.isEmpty(univIds)) {
            List<Integer> array = new ArrayList<>();

            String[] ids = univIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                array.add(Integer.parseInt(ids[i]));
            }

            f.append(" and bean.baseId in :ids").setParamList("ids", array.toArray());

            f.append(" order by bean.baseId");
        }

        return find(f);
    }

    private Finder createFinder(String univName, String grade, Integer provinceId) {
        String hql="from TDataUniversityRank where 1=1 ";
        Finder f= Finder.create(hql);
        if (univName != null) {
            f.append(" and univName like :univName").setParam("univName", "%" + univName + "%");
        }
        if (grade != null) {
            f.append(" and grade=:grade").setParam("grade", grade);
        }
        if (provinceId != null) {
            f.append(" and provinceId=:provinceId").setParam("provinceId", provinceId);
        }
        f.append(" order by rank");
        f.setCacheable(true);
        return f;
    }
}
