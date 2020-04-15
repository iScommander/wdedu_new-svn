package com.jeecms.wdedu.dao.impl;

import com.jeecms.wdedu.dao.FwYlDao;
import com.jeecms.wdedu.entity.TMgrSrvNode;
import com.jeecms.wdedu.entity.TMgrSrvOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FwYlDaoImpl implements FwYlDao {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction ta;

    private List<TMgrSrvOrder> tnOrderList = null;
    private List<TMgrSrvNode> tnNodeList = null;

    @Override
    public List<TMgrSrvOrder> queryOrder(Integer userId) {
        Session session = sessionFactory.openSession();
        String sql = "SELECT stu_name,sex,phoneNumber,majorType,examProvince,examCity,examRegion,examSchool,email,mailAddress,service_content,order_id FROM t_n_user U INNER JOIN t_n_order O ON U.userId = O.stu_user_id WHERE U.userId = '" + userId + "'";
        tnOrderList = session.createSQLQuery(sql).list();
        session.close();
        System.out.println();
        return tnOrderList;
    }

    @Override
    public List<TMgrSrvNode> queryBeijing(Integer userId) {
        Session session = sessionFactory.openSession();
        String sql = "SELECT b.txt1,b.txt2,b.fwName FROM t_n_order a, t_n_node b WHERE a.stu_user_id = '"+userId+"' AND a.order_id = b.orderId ";
        tnNodeList = session.createSQLQuery(sql).list();
        session.close();
        return tnNodeList;
    }
}
