package com.jeecms.wdedu.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.dao.EnrollPlanDao;
import com.jeecms.wdedu.entity.TCeeEnrollPlan;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class EnrollPlanDaoImpl extends HibernateBaseDao<TCeeEnrollPlan, Long> implements EnrollPlanDao {
    @Override
    protected Class<TCeeEnrollPlan> getEntityClass() {
        return TCeeEnrollPlan.class;
    }

    public Integer getMaxYear() {
        String hql="select max(year) from TCeeEnrollPlan";
        return (Integer) findUnique(hql);
    }

    @Override
    public Pagination getPlanPage(int year, Integer province, Integer majorType, Integer batch, String univName, String majorName, int pageNo, int pageSize) {

        String hql = "SELECT provinceId,majorTypeId,batchId,univName,univId, ";
        Finder f=Finder.create(hql);
        f.append("SUM(CASE WHEN year='" + String.valueOf(year) + "' THEN planNum END) as a, ");
        f.append("SUM(CASE WHEN year='" + String.valueOf(year-1) + "' THEN planNum END) as b, ");
        f.append("SUM(CASE WHEN year='" + String.valueOf(year-2) + "' THEN planNum END) as c ");
        f.append("FROM TCeeEnrollPlan where planType=0 ");

        if(null != province){
            f.append(" and provinceId=" + province);
        }
        if(null != majorType){
            f.append(" and majorTypeId=" + majorType);
        }
        if(null !=  batch){
            f.append(" and batchId=" + batch);
        }
        if(StringUtils.isNotBlank(univName)){
            f.append(" and univName like '%"+ univName +"%'");
        }
        if(StringUtils.isNotBlank(majorName)){
            f.append(" and majorName like '%"+ majorName +"%'");
        }
        f.append(" GROUP BY provinceId,majorTypeId,batchId,univName,univId");
        f.append(" ORDER BY provinceId");

        //return find(f, pageNo, pageSize);
        String countSql = "select count(*) from ( select * "+f.getCountHql().replace("planType", "plan_type")
                .replace("provinceId", "province_id").replace("majorTypeId", "major_type_id")
                .replace("batchId", "batch_id").replace("univName", "univ_name")
                .replace("majorName", "major_name").replace("TCeeEnrollPlan", "t_cee_enroll_plan")
                .replace("univId", "univ_id")+") t";
        return findByGroup(f, countSql, pageNo, pageSize);
    }

    @Override
    public List getMajorList(Integer year, Integer province, Integer majorType, Integer batch, Integer univId) {
        String hql = "SELECT majorName ,planNum, majorFee,univName FROM TCeeEnrollPlan where planType=1 ";
        Finder f=Finder.create(hql);
        f.append(" and year=:year").setParam("year", year);

        if(null !=  province){
            f.append(" and provinceId=:province").setParam("province", Integer.valueOf(province));
        }
        if(null !=  majorType){
            f.append(" and majorTypeId=:majorType").setParam("majorType", Integer.valueOf(majorType));
        }
        if(null !=  batch){
            f.append(" and batchId=:batch").setParam("batch", Integer.valueOf(batch));
        }
        if(null !=  univId){
            f.append(" and univId=:univId").setParam("univId", Integer.valueOf(univId));
        }
        f.append(" ORDER BY provinceId");

        return find(f);
    }
}
