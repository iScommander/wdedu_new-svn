package com.wdedu.dao.impl;

import com.jeecms.wdedu.entity.TDataCareerRegion;
import com.jeecms.wdedu.entity.TDataSalaryCareer;
import com.jeecms.wdedu.service.CommonSvc;
import com.wdedu.dao.FindCareerDao;
import com.wdedu.entity.TDataCareerIntroduceEntity;
import com.wdedu.entity.TDataCareerMapMajorEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class FindCareerDaoImpl implements FindCareerDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private CommonSvc commonSvc;

    private Session session;
    @Override
    public List<TDataCareerIntroduceEntity> findCareer1() {
        Session session = sessionFactory.openSession();
        String sql ="SELECT * FROM t_data_career_introduce  GROUP BY career_subjeet_name ORDER BY career_subjeet_ID asc";
        List<TDataCareerIntroduceEntity> list = commonSvc.findListbySql(sql,TDataCareerIntroduceEntity.class);
        return list;
    }

    @Override
    public List<TDataCareerIntroduceEntity> findCareer2(String careerSubjeetId) {
        Session session = sessionFactory.openSession();
        String sql ="SELECT * FROM t_data_career_introduce WHERE career_subjeet_id='"+careerSubjeetId+"' GROUP BY career_catelog_id ";
        List<TDataCareerIntroduceEntity> list = commonSvc.findListbySql(sql,TDataCareerIntroduceEntity.class);
        return list;
    }

    @Override
    public List<TDataCareerIntroduceEntity> findCareer3(String careerSubjeetId) {
        Session session = sessionFactory.openSession();
        String sql ="SELECT * FROM t_data_career_introduce WHERE career_subjeet_id='"+careerSubjeetId+"' GROUP BY career_onlevel_id ";
        List<TDataCareerIntroduceEntity> list = commonSvc.findListbySql(sql,TDataCareerIntroduceEntity.class);
        return list;
    }

    @Override
    public List<Map<String,Object>> findTwo(String careerSubjeetId) {
        Session session = sessionFactory.openSession();
        String sql ="SELECT career_catelog_id,career_catelog_name FROM t_data_career_introduce WHERE career_subjeet_id='"+careerSubjeetId+"' GROUP BY career_catelog_id ";
        List<Map<String,Object>> list = commonSvc.findForJdbc(sql);
        return list;
    }

    @Override
    public List<Map<String,Object>> findThr(String careerSubjeetId) {
        Session session = sessionFactory.openSession();
        String sql ="SELECT career_catelog_id,career_catelog_name,career_onlevel_id,career_onlevel_name FROM t_data_career_introduce WHERE career_subjeet_id='"+careerSubjeetId+"' GROUP BY career_onlevel_id ";
        List<Map<String,Object>> list = commonSvc.findForJdbc(sql);
        return list;
    }

    @Override
    public List<TDataCareerMapMajorEntity> findCareerByMajor(String majorName) {
        String sql ="SELECT * FROM t_data_career_map_major WHERE major_name='"+majorName+"'";
        List<TDataCareerMapMajorEntity> list = commonSvc.findListbySql(sql,TDataCareerMapMajorEntity.class);
        return list;
    }

    @Override
    public List<TDataCareerRegion> findAreaRank(String careerId) {
        Session session = sessionFactory.openSession();
        String sql ="SELECT * FROM t_data_career_region WHERE career_id='"+careerId+"' ORDER BY region_salary DESC";
        List<TDataCareerRegion> list = commonSvc.findListbySql(sql,TDataCareerRegion.class);
        return list;
    }

    @Override
    public List<TDataSalaryCareer> findByYear(String careerId) {
        Session session = sessionFactory.openSession();
        String sql ="SELECT * FROM t_data_salary_career WHERE career_id='"+careerId+"' ORDER BY YEAR DESC";
        List<TDataSalaryCareer> list = commonSvc.findListbySql(sql,TDataSalaryCareer.class);
        return list;
    }

    @Override
    public List<TDataSalaryCareer> findStationSalary(String careerOnlevelId, String careerName) {
        Session session = sessionFactory.openSession();
        String sql ="SELECT * FROM t_data_salary_career WHERE career_onlevel_id='"+careerOnlevelId+"' And career_name ='"+careerName+"'";
        List<TDataSalaryCareer> list = commonSvc.findListbySql(sql,TDataSalaryCareer.class);
        return list;
    }
}
