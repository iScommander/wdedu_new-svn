package com.jeecms.wdedu.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.dao.EnrollUnivListDao;
import com.jeecms.wdedu.entity.TCeeEnrollUnivList;
import org.apache.commons.lang.StringUtils;

public class EnrollUnivListDaoImpl  extends HibernateBaseDao<TCeeEnrollUnivList, Long> implements EnrollUnivListDao {
    @Override
    protected Class<TCeeEnrollUnivList> getEntityClass() {
        return TCeeEnrollUnivList.class;
    }

    @Override
    public Pagination getHistPage(Integer year, Integer province, Integer majorType, Integer batch, String univName, String majorName, int pageNo, int pageSize) {
        String hql = "SELECT year,provinceId,majorTypeId,batchId,univId,univName,scoreLow1,scoreLow2,scoreLow3 FROM TCeeEnrollUnivList WHERE 1=1";
        Finder f=Finder.create(hql);

        if(null != year){
            f.append(" and year=:year").setParam("year", year);
        }
        if(null != province){
            f.append(" and batchId=:province").setParam("province", province);
        }
        if(null != majorType){
            f.append(" and majorTypeId=:majorType").setParam("majorType", majorType);
        }
        if(null != batch){
            f.append(" and batchId=:batch").setParam("batch", batch);
        }
        if(StringUtils.isNotBlank(univName)){
            f.append(" and univName=:univName").setParam("univName", univName);
        }
        if(StringUtils.isNotBlank(majorName)){
            f.append(" and majorName=:majorName").setParam("majorName", majorName);
        }

        return find(f, pageNo, pageSize);
    }
}
