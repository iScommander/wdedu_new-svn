package com.jeecms.cms.manager.assist.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.assist.CmsOracleDataBackDao;
import com.jeecms.cms.entity.back.CmsField;
import com.jeecms.cms.manager.assist.CmsOracleDataBackMng;

@Service
@Transactional
public class CmsOracleDataBackMngImpl implements CmsOracleDataBackMng {
	@Override
    @Transactional(readOnly = true)
	public String createTableDDL(String tablename) {
		return dao.createTableDDL(tablename);
	}
	
	@Override
    @Transactional(readOnly = true)
	public String createFKconstraintDDL(String constraint){
		return dao.createFKconstraintDDL(constraint);
	}

	@Override
    @Transactional(readOnly = true)
	public List<String> createIndexDDL(String tablename) {
		return dao.createIndexDDL(tablename);
	}

	@Override
    @Transactional(readOnly = true)
	public List<String> getSequencesList(String user) {
		return dao.getSequencesList(user);
	}

	@Override
    @Transactional(readOnly = true)
	public String createSequenceDDL(String sqname) {
		return dao.createSequenceDDL(sqname);
	}

	@Override
    @Transactional(readOnly = true)
	public List<Object[][]> createTableData(String tablename) {
		return dao.createTableData(tablename);
	}

	@Override
    @Transactional(readOnly = true)
	public List<CmsField> listFields(String tablename) {
		return dao.listFields(tablename);
	}

	@Override
    @Transactional(readOnly = true)
	public List<String> getColumns(String tablename) {
		return dao.getColumns(tablename);
	}
	
	@Override
    @Transactional(readOnly = true)
	public List<String> getFkConstraints(String tablename){
		return dao.getFkConstraints(tablename);
	}

	@Override
    @Transactional(readOnly = true)
	public List<String> listTabels() {
		return dao.listTables();
	}

	@Override
    @Transactional(readOnly = true)
	public String getDefaultCatalog() throws SQLException {
		return dao.getDefaultCatalog();
	}

	@Override
    @Transactional(readOnly = true)
	public String getJdbcUserName() throws SQLException {
		return dao.getJdbcUserName();
	}

	@Override
    public void setDefaultCatalog(String catalog) throws SQLException {
		dao.setDefaultCatalog(catalog);
	}

	@Override
    public void executeSQL(String sql, String prefix) throws SQLException {
		dao.executeSQL(sql,prefix);
	}

	private CmsOracleDataBackDao dao;

	@Autowired
	public void setDao(CmsOracleDataBackDao dao) {
		this.dao = dao;
	}

}