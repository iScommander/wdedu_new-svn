package com.jeecms.wdedu.dao.impl;

import com.jeecms.wdedu.dao.FindMajorDao;
import com.jeecms.wdedu.entity.TDataMajor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FindMajorDaoImpl implements FindMajorDao {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction ta;
    List<TDataMajor> tDataMajorList;
    @Override
    public List<TDataMajor> findBen() {
        Session session = sessionFactory.openSession();
        String sql = "SELECT major_id,major_name FROM t_data_major WHERE LENGTH(major_id ) = 2 AND (id <= 12 OR major_id IN (11,15));";
        tDataMajorList = session.createSQLQuery(sql).list();
        return tDataMajorList;
    }

    @Override
    public List<TDataMajor> findZhuan() {
        Session session = sessionFactory.openSession();
        String sql = "SELECT major_id,major_name FROM t_data_major WHERE LENGTH(major_id) = 2 AND id > 12 AND major_id NOT IN (11,15);";
        tDataMajorList = session.createSQLQuery(sql).list();
        return tDataMajorList;
    }

    @Override
    public List<TDataMajor> finBenNext(String parentMajorId) {
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM t_data_major WHERE parent_major_id = '"+parentMajorId+"'";
        tDataMajorList = session.createSQLQuery(sql).list();
        return tDataMajorList;
    }

    @Override
    public List<TDataMajor> findBenXiang(String majorId) {
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM t_data_major WHERE major_id = '"+majorId+"'";
        tDataMajorList = session.createSQLQuery(sql).list();
        return tDataMajorList;
    }
}
