package com.jeecms.wdedu.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.dao.ActiveDetailDao;
import com.jeecms.wdedu.entity.TScActiveDetail;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author fanyue
 * @ProjectName wdedu
 * @Description: 用户中心
 * @date 2018/10/19
 */
public class ActiveDetailActDaoImpl extends HibernateBaseDao<TScActiveDetail, Integer> implements ActiveDetailDao {

    SimpleDateFormat simformat = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
    @Override
    public Pagination getPage(String proname, String cityname, String quxianname,int pageNo, int pageSize) {
        Finder f=createFinder(proname, cityname, quxianname);
        return find(f,pageNo,pageSize);
    }
    @Override
    public Finder createFinder(String proname, String cityname, String quxianname) {
        String sql="SELECT * FROM t_sc_active_detail WHERE 1=1 ";
        Finder f = Finder.create(sql);
        StringBuilder procity = new StringBuilder();
        f.append("SELECT * FROM t_sc_active_detail WHERE 1=1 ");
        if (!StringUtils.isEmpty(proname) && !"全部".equals(proname)) {
            f.append(" AND province='" + proname + "' ");
            procity.append(proname);
        }
        if (!StringUtils.isEmpty(cityname) && !"全部".equals(cityname)) {
            f.append(" AND city='" + cityname + "'");
            procity.append("-" + cityname);
        }
        if (!StringUtils.isEmpty(quxianname) && !"全部".equals(quxianname)) {
            f.append(" AND quxian='" + quxianname + "'");
            procity.append("-" + quxianname);
        }
        f.append(" AND '" + simformat.format(new Date()) + "'< active_time ");
        f.append(" ORDER BY active_time desc");
//        List<Map<String, Object>> activeList = session.createSQLQuery(f.toString()).list();
        f.setCacheable(true);
        System.out.println(f);
        return f;
    }

    @Override
    protected Class<TScActiveDetail> getEntityClass() {
        return null;
    }
}
