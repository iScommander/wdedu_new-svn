package com.jeecms.wdedu.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.wdedu.dao.TCeeApplicationsDetailDao;
import com.jeecms.wdedu.entity.TCeeApplicationsDetail;
import com.jeecms.wdedu.service.CommonSvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/19
 */
public class TCeeApplicationsDetailDaoImpl extends HibernateBaseDao<TCeeApplicationsDetail, Integer> implements TCeeApplicationsDetailDao {

    @Autowired
    private CommonSvc commonSvc;
    @Override
    protected Class<TCeeApplicationsDetail> getEntityClass() {
        return TCeeApplicationsDetail.class;
    }

    @Override
    public TCeeApplicationsDetail queryProjectDetail(Integer applicationId) {
        return findUniqueByProperty("applicationId", applicationId);
    }

    @Override
    public TCeeApplicationsDetail save(TCeeApplicationsDetail bean) {
        getSession().saveOrUpdate(bean);
        return bean;
    }

    @Override
    public List getUnivNum(int id) {
        String hql = "select batchId,count(DISTINCT univId) as num from TCeeApplicationsDetail where applicationId =" + id + " GROUP BY batchId";
        Finder f=Finder.create(hql);
        return find(f);
    }

    @Override
    public List<TCeeApplicationsDetail> queryProjectDetailList(Integer applicationId) {
        String hql = "from TCeeApplicationsDetail where 1=1 AND isFormal =0";
//        String sql = "SELECT * FROM t_cee_applications_detail where 1=1 AND application_id = '"+applicationId+"' ORDER BY  CASE  WHEN is_formal = 1 THEN 1 ELSE -1 END,univ_order ASC,major_order ASC";
        Finder f=Finder.create(hql);


        if (applicationId != null) {
            f.append(" and applicationId=:applicationId").setParam("applicationId", applicationId);
        }
        f.append(" order by  isFormal,univOrder,majorOrder");
//        f.append(" SUM(CASE  WHEN isFormal = 1 THEN 1 ELSE -1 END,univOrder ASC,majorOrder ASC)");

//        return commonSvc.findListbySql(sql,TCeeApplicationsDetail.class);
        return  find(f);
    }
}
