package com.jeecms.wdedu.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.dao.UniversityRankDao;
import com.jeecms.wdedu.entity.TDataUniversityRank;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName wdedu
 * @date 2018/10/17
 */
public class UniversityRankDaoImpl extends HibernateBaseDao<TDataUniversityRank, Integer> implements UniversityRankDao {


    @Override
    protected Class<TDataUniversityRank> getEntityClass() {
        return null;
    }

    @Override
    public Pagination getPage(Integer type, String univName, String univTypes, String univProvinces, int pageNo, int pageSize) {
        if (type == 1 || type == 2) {
            Finder f = createFinder(type, univName, univTypes, univProvinces);
            return find(f, pageNo, pageSize);
        } else if (type == 3 || type == 4 || type == 5) {
            Finder f = createInteFinder(type, univName, univTypes, univProvinces);
            return find(f, pageNo, pageSize);
        } else {
            Finder f = createFinder(type, univName, univTypes, univProvinces);
            return find(f, pageNo, pageSize);
        }
    }

    private Finder createFinder(Integer type, String univName, String univTypes, String univProvinces) {
        String hql = "from TDataUniversityRank where ";
        Finder f = Finder.create(hql);
        if (type != null) {
            if (type == 1) {
                f.append(" rankType = 1 ");
            } else if (type == 2) {
                f.append(" rankType = 2");
            }
        }
        if (univName != null) {
            f.append(" and univName like :univName").setParam("univName", "%" + univName + "%");
        }

        if (StringUtils.isNotBlank(univProvinces)) {
            String univProvince = "\'" + univProvinces.substring(1).replace(",", "','") + "\'";
            f.append(" AND provinceName IN ( " + univProvince + " ) ");
        }

        if (StringUtils.isNotBlank(univTypes)) {
            String univType = "\'" + univTypes.substring(1).replace(",", "','") + "\'";
            f.append(" AND cateName IN ( " + univType + " ) ");
        }

        f.append(" order by rank");
        f.setCacheable(true);
        return f;
    }

    private Finder createInteFinder(Integer type, String univName, String grade, String univProvince) {
        String hql = "from TDataUniversityInteRank where 1=1 ";

        Finder f = Finder.create(hql);
        if (type != null) {
            if (type == 3) {
                f.append(" AND TYPE = 1 ");
            } else if (type == 4) {
                f.append(" AND TYPE = 2 ");
            } else if (type == 5) {
                f.append(" AND TYPE = 3 ");
            }
        }
        if (univName != null) {
            f.append(" and name like :univName").setParam("univName", "%" + univName + "%");
        }
        f.append(" order by rank");
        f.setCacheable(true);
        return f;
    }

    @Override
    public Pagination getEnrollPage(String univName, Integer planYear, Integer grade, Integer provinceId, Integer tabType, String univProvince, String univType, int pageNo, int pageSize) {
        Finder f = createEnrollFinder(univName, planYear, grade, provinceId, tabType, univProvince, univType);
        return find(f, pageNo, pageSize);
    }

    @Override
    public Pagination getSalaryPage(String univName, Integer planYear, Integer provinceId, String univLevel, Integer tabType, String univProvinces, String univTypes, int pageNo, int pageSize) {
        Finder f = createSalaryFinder(univName, planYear, provinceId, univLevel, tabType, univProvinces, univTypes);
        return find(f, pageNo, pageSize);
    }

    private Finder createEnrollFinder(String univName, Integer planYear, Integer majorTypeId, Integer provinceId, Integer tabType, String univProvinces, String univTypes) {

        String hql = " FROM TCeeEnrollUnivList where ";
        Finder f = Finder.create(hql);

        f.append(" year = " + planYear + "");
        if (provinceId != null) {
            f.append(" and provinceId=:provinceId").setParam("provinceId", provinceId);
        }
        if (majorTypeId != null) {
            f.append(" and majorTypeId=:grade").setParam("grade", majorTypeId);
        }

        if (StringUtils.isNotBlank(univProvinces)) {
            String univProvince = "\'" + univProvinces.replace(",", "','") + "\'";
            f.append(" AND univProvince IN (" + univProvince + ")  ");
        }
        if (StringUtils.isNotBlank(univTypes)) {
            String univType = "\'" + univTypes.replace(",", "','") + "\'";
            f.append(" and univType in (" + univType + ")");
        }

        switch (tabType) {
//            院校录取分数排名
            case 1: {
                f.append(" order by IFNULL(rankScoreLow1,99999999)  asc, IFNULL(scoreLow1,0) desc ");
                break;
            }
//            院校录取总人数排名
            case 2: {
                f.append(" order by  IFNULL(enrolledNum1,0) desc ,IFNULL(enrolledNum2,0) desc ,IFNULL(enrolledNum2,0) desc ,IFNULL(enrolledNum2,0) desc  ");
                break;
            }
//            院校录取名次变化
            case 3: {
                f.append(" order by (rankScoreLow1 - rankScoreLow2) desc ");
                break;
            }
//            院校录取总专业数
            case 4: {
                f.append(" order by  IFNULL(univMajorNum,0) desc ,id asc ");
                break;
            }
            default: {
                f.append(" order by id ");
            }
        }

        f.setCacheable(true);
        return f;
    }

    private Finder createSalaryFinder(String univName, Integer planYear, Integer provinceId, String univLevel, Integer tabType, String univProvinces, String univTypes) {

        String hql = "SELECT a , b.univAttr,b.location,b.univBelong,b.univLevel,b.eduType,b.isFirstRateUniv,b.isFirstRateMajor,b.is211,b.is985,b.isDefence,b.isExcellent,b.isIndependence,b.isArtStudent,b.isKeylab \n" +
                "FROM TDataSalaryUniversity a\n" +
                ", TDataUniversity b\n" +
                "WHERE a.universityBaseId = b.universityBaseId\n" +
                "AND a.year = " + planYear + " \n";

        Finder f = Finder.create(hql);

        if (StringUtils.isNotBlank(univLevel)) {
            f.append(" AND a.univLevel = '" + univLevel + "'");
        }

        if (StringUtils.isNotBlank(univProvinces)) {
            f.append(" AND b.provinceId IN (" + univProvinces + ")  ");
        }

        if (StringUtils.isNotBlank(univTypes)) {
            String univType = "\'" + univTypes.replace(",", "','") + "\'";
            f.append(" and b.univType in (" + univType + ")");
        }

        f.append("  GROUP BY b.universityBaseId");
        f.setCacheable(true);
        return f;
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

}
