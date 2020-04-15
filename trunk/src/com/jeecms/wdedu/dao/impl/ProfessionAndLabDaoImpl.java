package com.jeecms.wdedu.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.dao.ProfessionAndLabDao;
import com.jeecms.wdedu.entity.TDataMajorImport;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/18
 */
public class ProfessionAndLabDaoImpl extends HibernateBaseDao<TDataMajorImport, Integer> implements ProfessionAndLabDao {
    @Override
    public Pagination getPage(String univName, String majorName, int pageNo, int pageSize) {
        Finder f=createFinder(univName, majorName);
        return find(f, pageNo, pageSize);
    }

    @Override
    public List findUvinCount(String univIds) {
        Finder f = Finder.create("select count(*) from TDataMajorImport bean where 1=1");

        if (!StringUtils.isEmpty(univIds)) {
            List<Integer> array = new ArrayList<>();

            String[] ids = univIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                array.add(Integer.parseInt(ids[i]));
            }

            f.append(" and bean.univBaseId in :ids").setParamList("ids", array.toArray());

            f.append(" group by bean.univBaseId");
            f.append(" order by bean.univBaseId");
        }

        return find(f);
    }

    private Finder createFinder(String univName, String majorName) {
        String hql="from TDataMajorImport where 1=1 ";
        Finder f=Finder.create(hql);
        if (univName != null) {
            f.append(" and univName like :univName").setParam("univName", "%" + univName + "%");
        }
        if (majorName != null) {
            f.append(" and majorName like :majorName").setParam("majorName", "%" + majorName + "%");
        }
        f.setCacheable(true);
        return f;
    }

    @Override
    protected Class<TDataMajorImport> getEntityClass() {
        return TDataMajorImport.class;
    }
}
