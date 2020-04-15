package com.wdedu.dao.impl;

import com.wdedu.dao.ICommonDao;
import com.wdedu.dao.IGenericBaseCommonDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class CommonDaoImpl extends GenericBaseCommonDaoImpl implements ICommonDao, IGenericBaseCommonDao {

	@Override
	public <T> List<T> findListbySqlone(String query) {
		Query querys = getSession().createSQLQuery(query);
		return querys.list();
	}

	@Override
	public List<Map<String, Object>> findForJdbc(String sql, int page, int rows) {
		return null;
	}

	@Override
	public List<Map<String, Object>> findForJdbcParam(String sql, int page, int rows, Object... objs) {
		return null;
	}

}
		