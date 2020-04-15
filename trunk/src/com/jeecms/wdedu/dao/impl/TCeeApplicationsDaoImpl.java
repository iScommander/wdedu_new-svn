package com.jeecms.wdedu.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.dao.TCeeApplicationsDao;
import com.jeecms.wdedu.entity.TCeeApplications;
import com.jeecms.wdedu.service.CommonSvc;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @date 2018/10/19
 */
public class TCeeApplicationsDaoImpl extends HibernateBaseDao<TCeeApplications, Integer> implements TCeeApplicationsDao {

    private static final Logger logger = Logger.getLogger(TCeeApplicationsDaoImpl.class);
    @Autowired
    private CommonSvc commonSvc;

    @Override
    protected Class<TCeeApplications> getEntityClass() {
        return TCeeApplications.class;
    }

    @Override
    public Pagination getPage(Integer userId, String applicationName, int pageNo, int pageSize) {
        String hql = "FROM TCeeApplications WHERE userId=" + userId;
        Finder f = Finder.create(hql);
        if (applicationName != null) {
            f.append(" and applicationName like :applicationName").setParam("applicationName", "%" + applicationName + "%");
        }
        f.append(" ORDER BY id Desc");
        f.setCacheable(true);
        return find(f, pageNo, pageSize);
    }

    @Override
    public TCeeApplications deleteProject(Integer id) {
        TCeeApplications entity = super.get(id);
        if (entity != null) {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    public void save(TCeeApplications bean) {
        try {
            getSession().saveOrUpdate(bean);
            getSession().flush();
            if (logger.isDebugEnabled()) {
                logger.debug("添加或更新成功," + bean.getClass().getName());
            }
        } catch (RuntimeException e) {
            logger.error("添加或更新异常", e);
            throw e;
        }
    }

    @Override
    public List getBatchInfo(Map map) {
        //select batch_id, batch_name from t_cee_batch
        //where year = 2018
        //and province_id = 17
        //and major_type_id = 1
        //String hql="select b.batchName,a.score,a.rank FROM TCeeScoreRank a, TCeeBatch b WHERE a.provinceId = b.provinceId" +
        //        " and a.majorTypeId = b.majorTypeId and a.score = b.score and a.year = b.year - 1";
        String hql = "select bean.batchId, bean.batchName,bean.score,bean.univNum,bean.majorNum,bean.fillDate,bean.order from TCeeBatch AS bean where 1=1 ";

//        Integer year = commonSvc.singleResult("select max(year) from TCeeBatch");
        Integer year = commonSvc.singleResult("SELECT dataBatchYear FROM TBaseProvince WHERE province_id =" + map.get("provinceId") + " ");
        Finder f = Finder.create(hql);
        f.append(" and bean.year =:year").setParam("year", year);
        if (map.get("provinceId") != null) {
            f.append(" and bean.provinceId =:provinceId").setParam("provinceId", Integer.parseInt(map.get("provinceId").toString()));
        }
        if (map.get("majorTypeId") != null) {
            f.append(" and bean.majorTypeId =:majorTypeId").setParam("majorTypeId", Integer.parseInt(map.get("majorTypeId").toString()));
        }
        f.append(" and bean.isShow = '1' order by bean.order");
        return find(f);
    }

    @Override
    public List getScoreAndRankFromBatch(Map<String, String> map) {
        String provinceId = map.get("provinceId");
        String majorTypeId = map.get("majorTypeId");
        String batchId = map.get("batchId");
        String gap = map.get("gap");
        Integer year1 = commonSvc.singleResult("SELECT dataScoreYear FROM TBaseProvince WHERE province_id =" + provinceId + " ");

        Integer year2 = commonSvc.singleResult("SELECT dataBatchYear FROM TBaseProvince WHERE province_id =" + provinceId + " ");
        String hql = "select b.batchId,b.batchName,a.score,a.rank FROM TCeeScoreRank a, TCeeBatch b WHERE a.provinceId = b.provinceId" +
                " and a.majorTypeId = b.majorTypeId and a.year = " + year1 + "";
        Finder f = Finder.create(hql);
        f.append(" and b.year = '" + year2 + "'");
        f.append(" and b.provinceId =:provinceId").setParam("provinceId", Integer.parseInt(provinceId));
        f.append(" and b.majorTypeId =:majorTypeId").setParam("majorTypeId", Integer.parseInt(majorTypeId));
        if (gap != null) {
            f.append(" and a.score = b.score+:gap").setParam("gap", Integer.parseInt(gap));
        } else {
            f.append(" and a.score = b.score");
        }
        if (batchId != null && !"".equals(batchId)) {
            f.append(" and b.batchId =:batchId").setParam("batchId", Integer.parseInt(batchId));
        }
        f.append(" and b.isShow = '1' order by b.order");
        return find(f);
    }
}
