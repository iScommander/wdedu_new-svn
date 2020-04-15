package com.wdedu.dao.impl;

import com.wdedu.dao.ICourseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class CourseDaoImpl implements ICourseDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {

		// 事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession();

	}

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	



	@Override
	public List<Map<String,Object>> findSqlList(String sql) {
		
		return  jdbcTemplate.queryForList(sql);
	}

}
