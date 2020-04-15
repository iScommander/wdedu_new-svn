package com.jeecms.wdedu.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.dao.ProfessionRankDao;
import com.jeecms.wdedu.entity.TDataMajorRank;
import com.utils.StringUtil;
import com.utils.StringUtils;

/**
 * @date 2018/10/18
 */
public class ProfessionRankDaoImpl extends HibernateBaseDao<TDataMajorRank, Integer> implements ProfessionRankDao {

    @Override
    protected Class<TDataMajorRank> getEntityClass() {
        return TDataMajorRank.class;
    }

    @Override
    public Pagination getPage(String univName, String majorName, String undergraduate, int pageNo, int pageSize) {
        Finder f = createFinder(univName, majorName, undergraduate);
        return find(f, pageNo, pageSize);
    }

    private Finder createFinder(String univName, String majorName, String undergraduate) {
        String hql = "from TDataMajorRank where 1=1 ";
        Finder f = Finder.create(hql);

        if (StringUtils.isNotBlank(undergraduate)) {
            String undergraduates = "\'" + undergraduate.replace(",", "','") + "\'";
            f.append(" AND SUBSTRING(majorId,1,2) IN ( " + undergraduates + " )");
        }

        if (univName != null) {
            f.append(" and univName like :univName").setParam("univName", "%" + univName + "%");
        }
        if (majorName != null) {
            f.append(" and majorName like :majorName").setParam("majorName", "%" + majorName + "%");
        }
        f.append(" order by rank");
        f.setCacheable(true);
        return f;
    }

    @Override
    public Pagination getEnrollPage(Integer enrollYear, Integer provinceId, Integer majorTypeId, Integer tabType, String majorId, String univName, String majorName, int pageNo, int pageSize) {
        Finder f = createEnrollFinder(enrollYear, provinceId, majorTypeId, tabType, majorId, univName, majorName);
        return find(f, pageNo, pageSize);
    }

    private Finder createEnrollFinder(Integer enrollYear, Integer provinceId, Integer majorTypeId, Integer tabType, String majorId, String univName, String majorName) {
        String hql = "from TCeeEnrollFillList where 1=1 ";
        Finder f = Finder.create(hql);

        f.append(" and year = " + enrollYear + "");
        if (provinceId != null) {
            f.append(" and provinceId=:provinceId").setParam("provinceId", provinceId);
        }
        if (majorTypeId != null) {
            f.append(" and majorTypeId=:majorTypeId").setParam("majorTypeId", majorTypeId);
        }

        if (StringUtils.isNotBlank(majorId)) {
            f.append(" AND majorId IN (" + majorId + ") ");
        }

        if (StringUtils.isNotBlank(univName)) {
            f.append(" AND univName LIKE '%" + univName + "%' ");
        }

        if (StringUtils.isNotBlank(majorName)) {
            f.append(" AND majorName LIKE '%" + majorName + "%'  ");
        }

        switch (tabType) {
//            专业录取分数排名
            case 1: {
                f.append(" ORDER BY histLowScore1 desc , histLowRank1 asc ");
                break;
            }
//            专业录取总人数排名
            case 2: {
                f.append(" ORDER BY histNum1 DESC ");
                break;
            }
//            专业录取名次变化
            case 3: {
                f.append(" ORDER BY ABS(histLowRank1 - histLowRank2)");
                break;
            }
            default: {
                f.append(" ORDER BY id");
            }
        }

        f.setCacheable(true);
        return f;
    }

    @Override
    public Pagination getSalaryPage(Integer enrollYear, Integer provinceId, Integer majorTypeId, Integer tabType, String undergraduate, String majorId, String majorName, int pageNo, int pageSize) {

        if (tabType == 1) {
            //    薪酬排行榜
            Finder f = createSalaryFinder(enrollYear, provinceId, majorTypeId, tabType, undergraduate, majorId, majorName);
            return find(f, pageNo, pageSize);
        } else if (tabType == 2) {
            //    就业综合指数
            Finder f = createEmployIndexFinder(enrollYear, provinceId, majorTypeId, tabType, majorId, majorName);
            return find(f, pageNo, pageSize);
        } else {
            Finder f = createSalaryFinder(enrollYear, provinceId, majorTypeId, tabType, undergraduate, majorId, majorName);
            return find(f, pageNo, pageSize);
        }
    }

    private Finder createSalaryFinder(Integer enrollYear, Integer provinceId, Integer majorTypeId, Integer tabType, String undergraduate, String majorId, String majorName) {
        String hql = "FROM TDataSalaryMajor where 1=1 ";
        Finder f = Finder.create(hql);
        f.append(" and year = " + enrollYear + " ");
        if (StringUtils.isNotBlank(undergraduate)) {
            String undergraduates = "\'" + undergraduate.replace(",", "','") + "\'";
            f.append(" AND SUBSTRING(majorId,1,2) IN ( " + undergraduates + " ) ");
        }
        if (StringUtils.isNotBlank(majorId)) {
            f.append(" AND majorId IN (" + majorId + ") ");
        }
        if (StringUtils.isNotBlank(majorName)) {
            f.append(" AND majorName LIKE '%" + majorName + "%' ");
        }
        f.append(" ORDER BY threefiveYearSalary DESC  ");
        f.setCacheable(true);
        return f;

    }

    private Finder createEmployIndexFinder(Integer enrollYear, Integer provinceId, Integer majorTypeId, Integer tabType, String majorId, String majorName) {
        String hql = " SELECT a FROM TDataEmployIndex a WHERE a.id IN (\n" +
                "SELECT MIN(id)  FROM TDataEmployIndex GROUP BY majorName )\n" +
                "ORDER BY CONVERT(a.itemIndex,UNSIGNED) DESC ";
        Finder f = Finder.create(hql);
        if (StringUtils.isNotBlank(majorId)) {
            f.append(" AND majorId IN (" + majorId + ") ");
        }
        if (StringUtils.isNotBlank(majorName)) {
            f.append(" AND major_name LIKE '%" + majorName + "%' ");
        }
        f.setCacheable(true);
        return f;
    }

}
