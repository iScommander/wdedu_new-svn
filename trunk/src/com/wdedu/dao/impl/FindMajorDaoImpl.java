package com.wdedu.dao.impl;


import com.jeecms.wdedu.entity.TDataEmployCompany;
import com.jeecms.wdedu.entity.TDataEmployIndex;
import com.jeecms.wdedu.entity.TDataMajor;
import com.jeecms.wdedu.service.CommonSvc;
import com.wdedu.dao.FindMajorDao;
import com.wdedu.service.CommonService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class FindMajorDaoImpl implements FindMajorDao {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction ta;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CommonSvc commonSvc;
    List<TDataMajor> tDataMajorList;

    @Override
    public List<TDataMajor> findBen() {
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM t_data_major WHERE LENGTH(major_id ) = 2 AND (id <= 12 OR major_id IN (11,15))";
        tDataMajorList = commonSvc.findListbySql(sql,TDataMajor.class);
        return tDataMajorList;
    }

    @Override
    public List<TDataMajor> findZhuan() {
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM t_data_major WHERE LENGTH(major_id) = 2 AND id > 12 AND major_id NOT IN (11,15);";
        tDataMajorList = commonSvc.findListbySql(sql,TDataMajor.class);
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

    @Override
    public List<TDataMajor> findByMajorName(String majorName) {
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM t_data_major WHERE LENGTH(major_id) > 4 AND major_name = '"+majorName+"'";
        tDataMajorList = session.createSQLQuery(sql).list();
        return tDataMajorList;
    }

    @Override
    public List<Map<String, Object>> findOnelevelRank(String onelevelId) {
/*
        String sql = "select * FROM t_data_salary_major WHERE major_onlevel_id = '"+onelevelId+"' ORDER BY Salary_onelevel_rank DESC";
*/
        String sql ="select *,e.item_index FROM t_data_salary_major s\n" +
                "LEFT JOIN t_data_employ_index e ON\n" +
                "s.major_id =  e.major_id\n" +
                "WHERE s.major_onlevel_id = '"+onelevelId+"' ORDER BY s.aveSalary DESC";
        List<Map<String, Object>> list = commonService.findForJdbc(sql);
        return list;
    }

    @Override
    public List<TDataEmployIndex> findCatelogRank(String catelogId) {
        String sql ="select * from t_data_employ_index WHERE SUBSTR(major_id,1,4)='"+catelogId+"' ORDER BY item_index DESC";
        List<TDataEmployIndex> list = commonSvc.findListbySql(sql,TDataEmployIndex.class);
        return list;
    }

    @Override
    public List<TDataMajor> findSecond(String majorId) {
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM t_data_major WHERE LENGTH(parent_major_id ) = 2 AND parent_major_id = '"+majorId+"'";
        tDataMajorList = commonSvc.findListbySql(sql,TDataMajor.class);
        return tDataMajorList;
    }

    @Override
    public List<TDataMajor> findThree(String majorId) {
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM t_data_major WHERE LENGTH(parent_major_id ) = 4 AND LEFT(parent_major_id,2) = '"+majorId+"'";
        tDataMajorList = commonSvc.findListbySql(sql,TDataMajor.class);
        return tDataMajorList;
    }

    @Override
    public List<TDataEmployCompany> findSalay(String majorId) {
       String sql ="select * FROM t_data_employ_company where major_id='"+majorId+"' AND require_item='工资情况' ORDER BY item_name DESC ";
       List<TDataEmployCompany> list = commonSvc.findListbySql(sql,TDataEmployCompany.class);
       return list;
    }

    @Override
    public List<TDataEmployCompany> findExperience(String majorId) {
        String sql ="select * FROM t_data_employ_company where major_id='"+majorId+"' AND require_item='经验要求' ORDER BY item_name DESC ";
        List<TDataEmployCompany> list = commonSvc.findListbySql(sql,TDataEmployCompany.class);
        return list;
    }

    @Override
    public List<TDataEmployCompany> findEducation(String majorId) {
        String sql ="select * FROM t_data_employ_company where major_id='"+majorId+"' AND require_item='学历要求' ORDER BY item_name DESC ";
        List<TDataEmployCompany> list = commonSvc.findListbySql(sql,TDataEmployCompany.class);
        return list;
    }


}
