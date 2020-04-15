package com.wdedu.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.entity.TDataUniversityRank;
import com.jeecms.wdedu.entity.TScActiveDetail;
import com.jeecms.wdedu.service.CommonSvc;
import com.wdedu.dao.ActiveApplyDao;
import com.wdedu.entity.TDataActivityDetailEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ActiveApplyDaoImpl extends HibernateBaseDao implements ActiveApplyDao {
    DateFormat format = new SimpleDateFormat("MM月dd日 HH时mm分");
    SimpleDateFormat simformat = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
    @Autowired
    private CommonSvc commonSvc;
    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    @Override
    public List<Map<String, Object>> findAppointment(String telephone) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM t_data_activity_detail");
        sb.append("LEFT JOIN t_data_online_sign o ON a.activity_id = o.activity_id");
        sb.append("WHERE o.telephone ='"+telephone+"'");
        sb.append(" AND '" + simformat.format(new Date()) + "'< a.activity_start_time ");
        sb.append(" ORDER BY a.activity_start_time");
        List<Map<String, Object>> activeList = commonSvc.findForJdbc(sb.toString());
        return activeList;
    }

    @Override
    public List<TDataActivityDetailEntity> findList() {
        /*StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM t_data_activity_detail");
        *//*sb.append(" WHERE '" + simformat.format(new Date()) + "'< activity_start_time ");*//*
        sb.append(" ORDER BY activity_start_time DESC");
        List <TDataActivityDetailEntity> list = commonSvc.findListbySql(sb.toString(),TDataActivityDetailEntity.class);*/

        Session session = sessionFactory.openSession();
        String sql ="SELECT * FROM t_data_activity_detail ORDER BY activity_start_time DESC";
        List<TDataActivityDetailEntity> list = session.createSQLQuery(sql).list();
        session.close();
        return list;


    }

    @Override
    public Pagination findActive(int pageNo, int pageSize) {

        String hql ="From TScActiveDetail  WHERE 1 = 1 ";
        Finder f = Finder.create(hql);
        f.append(" ORDER BY activeTime DESC");
        f.setCacheable(true);
        return find(f,pageNo,pageSize);
    }

    @Override
    public List<TScActiveDetail> myActive(String phone) {
        String sql="SELECT * FROM t_sc_active_detail d\n" +
                "LEFT JOIN\n" +
                "t_sc_active_sign s ON d.active_Id = s.active_Id\n" +
                "WHERE s.telephone='"+phone+"'";
        List<TScActiveDetail> list = commonSvc.findListbySql(sql,TScActiveDetail.class);
        return list;
    }


    @Override
    protected Class getEntityClass() {
        return null;
    }
}
