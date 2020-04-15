package com.jeecms.wdedu.dao.impl;

import com.jeecms.wdedu.dao.ActivityDao;
import com.jeecms.wdedu.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author fanyue
 * @ProjectName wdedu
 * @Description: 背景提升
 * @date 2018/10/19
 */

public class ActivityDaoImpl implements ActivityDao {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    List<TNActivity> tnActivities = null;
    List<TNAward> awardList = null;
    List<TNUser> tnUserList = null;
    List<TNReferee> tnRefereeList = null;
    List<TNScore> tnScoreList = null;
    List<TNPatent> tnPatentList = null;
    List<TNEducation> tnEducationList = null;
    List<TNFamily> tnFamilyList = null;
    List<TNSchool> tnSchoolList = null;

    @Override
    public List<TNActivity> activityList(Integer userId) {
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM t_n_activity where userId ='" + userId + "'";
        tnActivities = session.createSQLQuery(sql).list();
        session.close();
        return tnActivities;
    }

    @Override
    public List<TNAward> awardList(Integer userId) {
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM t_n_award where where userId ='" + userId + "'";
        awardList = session.createSQLQuery(sql).list();
        session.close();
        return awardList;
    }

    @Override
    public List<TNUser> tNUserList(Integer userId) {
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM t_n_user where userId ='" + userId + "'";
        tnUserList = session.createSQLQuery(sql).list();
        session.close();
        return tnUserList;
    }

    @Override
    public List<TNReferee> tNRefereeList(Integer userId) {
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM t_n_referee where where userId ='" + userId + "'";
        tnRefereeList = session.createSQLQuery(sql).list();
        session.close();
        return tnRefereeList;
    }

    @Override
    public List<TNScore> tNScoreList(Integer userId) {
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM t_n_score where userId ='" + userId + "'";
        tnScoreList = session.createSQLQuery(sql).list();
        session.close();
        return tnScoreList;
    }

    @Override
    public List<TNPatent> tNPatentList(Integer userId) {
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM t_n_patent where userId ='" + userId + "'";
        tnPatentList = session.createSQLQuery(sql).list();
        session.close();
        return tnPatentList;
    }

    @Override
    public List<TNEducation> tNEducationList(Integer userId) {
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM t_n_education where userId='" + userId + "'";
        tnEducationList = session.createSQLQuery(sql).list();
        session.close();
        return tnEducationList;
    }

    @Override
    public List<TNFamily> tNFamily(Integer userId) {
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM t_n_family where userId='" + userId + "'";
        tnFamilyList = session.createSQLQuery(sql).list();
        session.close();
        return tnFamilyList;
    }

    @Override
    public List<TNSchool> tNSchoolList(Integer userId) {
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM t_n_school where userId='" + userId + "'";
        tnSchoolList = session.createSQLQuery(sql).list();
        session.close();
        return tnSchoolList;
    }

    @Override
    public boolean updateUser(TNUser tnUser,String userAccount) {
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            tnUser = (TNUser) session.get(TNUser.class,userAccount);
//            tnUser.setBirthday("2017-10-1");
//            tnUser.setUserName("范越");
            session.update(tnUser);
            System.out.println(tnUser.getUserAccount());
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean saveUser(TNUser tnUser) {
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(tnUser);
            System.out.println(tnUser.getUserAccount());
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean savePatent(TNPatent tnPatent) {
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(tnPatent);
            System.out.println(tnPatent.getId());
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean updatePatent(TNPatent tnPatent, String id) {
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            tnPatent = (TNPatent) session.get(TNPatent.class,id);
            session.update(tnPatent);
            System.out.println(tnPatent.getId());
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean addScore(TNScore tnScore) {
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(tnScore);
            System.out.println(tnScore.getId());
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean updateScore(TNScore tnScore, String id) {
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            tnScore = (TNScore) session.get(TNScore.class,id);
            session.update(tnScore);
            System.out.println(tnScore.getId());
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean updateEducation(TNEducation tnEducation, String id) {
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            tnEducation = (TNEducation) session.get(TNEducation.class,id);
            session.update(tnEducation);
            System.out.println(tnEducation.getId());
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean saveEducation(TNEducation tnEducation) {
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(tnEducation);
            System.out.println(tnEducation.getId());
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return false;
    }
}
