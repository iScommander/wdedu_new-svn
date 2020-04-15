package com.jeecms.wdedu.dao.impl;

import com.jeecms.wdedu.dao.BkyxDao;
import com.jeecms.wdedu.entity.TBaseProvince;
import com.jeecms.wdedu.entity.TDataMajor;
import com.jeecms.wdedu.entity.TDataUniversityType;
import com.jeecms.wdedu.entity.TMgrSrvNode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author fanyue
 * @ProjectName wdedu
 * @Description: 报考意向
 * @date 2018/10/17
 */

public class BkyxDaoImpl implements BkyxDao {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction ta;
    List<TBaseProvince> tBaseProvincesList = null;
    List<TDataUniversityType> tDataUniversityTypes = null;
    List<TDataMajor> tDataMajors = null;
    List<TDataMajor> tDataMajorsZ = null;
    List<TDataMajor> tDataMajorsB = null;

    @Override
    public List<TBaseProvince> queryProvince() {
        Session session = sessionFactory.openSession();
        String sql = "select * from t_base_province";
        tBaseProvincesList = session.createSQLQuery(sql).list();
        session.close();
        return tBaseProvincesList;
    }

    @Override
    public List<TDataUniversityType> queryUniType() {
        Session session = sessionFactory.openSession();
        String sql="select * from t_data_university_type";
        tDataUniversityTypes = session.createSQLQuery(sql).list();
        session.close();
        return tDataUniversityTypes;
    }

    @Override
    public List<TDataMajor> queryDataMajorAll() {
        Session session = sessionFactory.openSession();
        String sql = "select * from t_data_major WHERE LENGTH(major_id ) = 2 ";
        tDataMajors = session.createSQLQuery(sql).list();
        session.close();
        return tDataMajors;
    }

    @Override
    public List<TDataMajor> queryDataMajorZ() {
        Session session =sessionFactory.openSession();
        String sql = "SELECT major_id,major_name FROM t_data_major WHERE LENGTH(major_id) = 2 AND id > 12 AND major_id NOT IN (11,15)";
        tDataMajorsZ = session.createSQLQuery(sql).list();
        session.close();
        return tDataMajorsZ;
    }

    @Override
    public List<TDataMajor> queryDataMajorB() {
        Session session = sessionFactory.openSession();
        String sql = "SELECT major_id,major_name FROM t_data_major WHERE LENGTH(major_id ) = 2 AND (id <= 12 OR major_id IN (11,15) )";
        tDataMajorsB = session.createSQLQuery(sql).list();
        session.close();
        return tDataMajorsB;
    }

    @Override
    public boolean save(TMgrSrvNode bkyx) {
        try {
            session = this.sessionFactory.openSession();
            ta = session.beginTransaction();
            session.save(bkyx);
            ta.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ta.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
