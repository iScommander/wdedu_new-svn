package com.jeecms.wdedu.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.dao.FindUnivDao;
import com.jeecms.wdedu.entity.TDataUniversity;
import com.jeecms.wdedu.entity.TDataUniversityDetail;
import com.jeecms.wdedu.entity.TDataUniversityRank;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FindUnivDaoImpl extends HibernateBaseDao<TDataUniversityRank, Integer> implements FindUnivDao {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction ta;
    List<TDataUniversity> tDataUniversityList;
    List<TDataUniversityDetail> tDataUniversityDetailList;


    @Override
    public Pagination getPage(int pageNo, int pageSize,Integer provinceId , String univType,String offOrVol,String univLevel,Integer is211,Integer is985,Integer isKeylab,Integer isIndependence,Integer isDefence,String location,String univName) {
        String sql = "FROM TDataUniversity  WHERE 1 = 1 " ;
//        String sql = "FROM TDataUniversity";
        Finder f = Finder.create(sql);
        if(null!=provinceId){
//              sql += " AND province_id='"+provinceId+"'";
            //          sql= sql+sql1;
            f.append("AND provinceId='"+provinceId+"'");
        }
        if (null!=univType && ""!=univType){
            f.append(" AND univType = '"+univType+"'");
//               sql += " AND u.univ_type = '"+univType+"'";
        }
        if(null!=offOrVol && ""!= offOrVol){
            f.append(" AND offOrVol = '"+offOrVol+"'");
//               sql+=" AND u.off_OR_vol = '"+offORvol+"'";
        }
        if (null!=univLevel && ""!=univLevel){
            f.append(" AND univLevel = '"+univLevel+"'");
//               sql+=" AND u.univ_level = '"+univLevel+"'";
        }
        if (null!=is211){
            f.append(" AND is211 = '"+is211+"'");
//               sql+=" AND u.is211 = '"+is211+"'";
        }
        if (null!=is985){
            f.append(" AND is985 = '"+is985+"'");
//               sql+=" AND u.is985 = '"+is985+"'";
        }
        if (null!=isKeylab){
            f.append(" AND isKeylab = '"+isKeylab+"'");
//               sql+=" AND u.isKeylab = '"+isKeylab+"'";
        }
        if (null!=isIndependence){
            f.append(" AND isIndependence='"+isIndependence+"'");
//               sql+=" AND u.isIndependence='"+isIndependence+"'";
        }
        if (null!=isDefence){
            f.append(" AND isDefence='"+isDefence+"'");
//               sql+=" AND u.isDefence='"+isDefence+"'";
        }
        if (null!=location){
            f.append("AND location = '"+location+"'");
        }
//        if (univName=="null"){
//            return find(f,pageNo,pageSize);
//        }
        if (""!=univName && null!=univName){
            f.append("AND univName like '%"+univName+"%'");
        }
        f.append("AND SUBSTR(univId,-2)= 10");
        f.setCacheable(true);
        return find(f,pageNo,pageSize);
    }

    @Override
    public List<TDataUniversity> tDataUnivList(Integer provinceId, String univType, String offORvol, String univLevel, Integer is211, Integer is985, Integer isKeylab, Integer isIndependence, Integer isDefence) {
//       Session session = sessionFactory.openSession();
       String sql = "FROM TDataUniversity u ,TBaseProvince b WHERE u.provinceId = b.provinceId " ;
//        String sql = "FROM TDataUniversity";
       Finder f = Finder.create(sql);
           if(null!=provinceId){
//              sql += " AND province_id='"+provinceId+"'";
               f.append("AND u.provinceId='"+provinceId+"'");
           }
           if (null!=univType){
               f.append(" AND u.univType = '"+univType+"'");
//               sql += " AND u.univ_type = '"+univType+"'";
           }
           if(null!=offORvol){
               f.append(" AND u.offORVol = '"+offORvol+"'");
//               sql+=" AND u.off_OR_vol = '"+offORvol+"'";
           }
           if (null!=univLevel){
               f.append(" AND u.univLevel = '"+univLevel+"'");
//               sql+=" AND u.univ_level = '"+univLevel+"'";
           }
           if (null!=is211){
               f.append(" AND u.is211 = '"+is211+"'");
//               sql+=" AND u.is211 = '"+is211+"'";
           }
           if (null!=is985){
               f.append(" AND u.is985 = '"+is985+"'");
//               sql+=" AND u.is985 = '"+is985+"'";
           }
           if (null!=isKeylab){
               f.append(" AND u.isKeylab = '"+isKeylab+"'");
//               sql+=" AND u.isKeylab = '"+isKeylab+"'";
           }
           if (null!=isIndependence){
               f.append(" AND u.isIndependence='"+isIndependence+"'");
//               sql+=" AND u.isIndependence='"+isIndependence+"'";
           }
           if (null!=isDefence){
                f.append(" AND u.isDefence='"+isDefence+"'");
//               sql+=" AND u.isDefence='"+isDefence+"'";
           }
        f.setCacheable(true);
//        tDataUniversityList = session.createSQLQuery(sql).list();
        return find(f);
    }

    @Override
    public List<TDataUniversityDetail> queryById(String univName) {
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM  t_cee_enroll_history WHERE univ_name = '"+univName+"' AND YEAR IN (2015,2016,2017)";
        tDataUniversityDetailList = session.createSQLQuery(sql).list();
        session.close();
        return tDataUniversityDetailList;
    }

    @Override
    protected Class<TDataUniversityRank> getEntityClass() {return null;}
}
