package com.jeecms.cms.dao.assist.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.assist.CmsOracleDataBackDao;
import com.jeecms.cms.entity.back.CmsField;

@Repository
public class CmsOracleDataBackDaoImpl extends JdbcDaoSupport implements
		CmsOracleDataBackDao {
	@Override
    public String createTableDDL(String tablename) {
		String tableDDL = "";
		String sql = " SELECT DBMS_METADATA.GET_DDL('TABLE',u.table_name)FROM USER_TABLES u WHERE table_name='"
				+ tablename + "'";
		tableDDL=getJdbcTemplate().query(sql, new ResultSetExtractor<String>(){
			@Override
            public String extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				rs.next();
				return lobHandler.getClobAsString(rs, 1).replace("NOT NULL ENABLE", "NOT NULL");
			}
		});
		return tableDDL;
	}
	
	@Override
    public String createFKconstraintDDL(String constraint){
		String constraintDDL = "";
		String sql = " SELECT DBMS_METADATA.GET_DDL('REF_CONSTRAINT',u.constraint_name) FROM USER_CONSTRAINTS u where CONSTRAINT_NAME='"+constraint+"'";
		constraintDDL=getJdbcTemplate().query(sql, new ResultSetExtractor<String>(){
			@Override
            public String extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				rs.next();
				return lobHandler.getClobAsString(rs, 1).replace("DISABLE", "");
			}
		});
		return constraintDDL;
	}
	
	@Override
    public List<String> createIndexDDL(String tablename){
		List<String>results;
		String sql = " SELECT DBMS_METADATA.GET_DDL('INDEX',u.index_name)FROM USER_INDEXES u WHERE table_name='"
				+ tablename + "'";
		results=getJdbcTemplate().query(sql, new ResultSetExtractor<List<String>>(){
			@Override
            public List<String> extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<String>result=new ArrayList<String>();
				while(rs.next()){
					result.add(lobHandler.getClobAsString(rs, 1)) ;
				}
				return result;
			}
		});
		return results;
	}
	
	@Override
    public List<String> getSequencesList(String user){
		String sql = "select SEQUENCE_NAME from sys.dba_sequences where sequence_owner='"+user+"'";
		List<String> sequences = new ArrayList<String>();
		SqlRowSet set = getJdbcTemplate().queryForRowSet(sql);
		while (set.next()) {
			sequences.add(set.getString(1));
		}
		return sequences;
	}
	
	@Override
    public String createSequenceDDL(String sqname){
		String sequenceDDL = "";
		String sql = " select dbms_metadata.get_ddl('SEQUENCE','"+sqname+"') FROM dual";
		sequenceDDL=getJdbcTemplate().query(sql, new ResultSetExtractor<String>(){
			@Override
            public String extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				rs.next();
				return lobHandler.getClobAsString(rs, 1);
			}
		});
		return sequenceDDL;
	}
	
	@Override
    public List<String> getFkConstraints(String tablename){
		String sql = "select constraint_name from user_constraints where constraint_type = 'R' and table_name='"+tablename+"' " ;
		List<String> constraints = new ArrayList<String>();
		SqlRowSet set = getJdbcTemplate().queryForRowSet(sql);
		while (set.next()) {
			constraints.add(set.getString(1));
		}
		return constraints;
	}

	@Override
    public List<String> getColumns(String tablename) {
		String sql = "select COLUMN_NAME from USER_TAB_COLS where table_name='"+tablename+"'";
		List<String> columns = new ArrayList<String>();
		SqlRowSet set = getJdbcTemplate().queryForRowSet(sql);
		while (set.next()) {
			columns.add(set.getString(1));
		}
		return columns;
	}

	@Override
    public List<Object[][]> createTableData(final String tablename) {
		final int filedNum = getTableFieldNums(tablename);
		List<Object[][]> results = new ArrayList<Object[][]>();
		String sql = " select * from   " + tablename;
		results=getJdbcTemplate().query(sql, new ResultSetExtractor<List<Object[][]>>(){
			@Override
            public List<Object[][]> extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<Object[][]>result=new ArrayList<Object[][]>();
				while(rs.next()){
					Object[][] oneResult = new Object[filedNum][2];
					for (int i = 1; i <= filedNum; i++) {
							if(isDateTimeColumn(tablename, i)){
								oneResult[i - 1][0] = rs.getTimestamp(i);
								oneResult[i - 1][1]=false;
							}else if(isColbColumn(tablename, i)){
								oneResult[i - 1][0] = lobHandler.getClobAsString(rs, i);
								oneResult[i - 1][1]=true;
							}else{
								oneResult[i - 1][0] = rs.getObject(i);
								oneResult[i - 1][1]=false;
							}
					}
					result.add(oneResult);
				}
				return result;
			}
		});
		return results;
	}

	@Override
    public List<CmsField> listFields(String tablename) {
		String sql = "select COLUMN_NAME,DATA_TYPE,NULLABLE,DATA_LENGTH from USER_TAB_COLS where table_name='"
				+ tablename + "'";
		List<CmsField> fields = new ArrayList<CmsField>();
		SqlRowSet set = getJdbcTemplate().queryForRowSet(sql);
		while (set.next()) {
			CmsField field = new CmsField();
			field.setName(set.getString(1));
			field.setFieldType(set.getString(2));
			field.setNullable(set.getString(3));
			field.setLength(set.getString(4));
			fields.add(field);
		}
		return fields;
	}

	@Override
    public List<String> listTables() {
		String sql = " select table_name from user_tables order by table_name ";
		List<String> tables = new ArrayList<String>();
		SqlRowSet set = getJdbcTemplate().queryForRowSet(sql);
		while (set.next()) {
			tables.add(set.getString(1));
		}
		return tables;
	}

	@Override
    public String getDefaultCatalog() throws SQLException {

		 getJdbcTemplate().getDataSource().getConnection().getMetaData().getUserName();
		return getJdbcTemplate().getDataSource().getConnection().getCatalog();
	}

	@Override
    public void setDefaultCatalog(String catalog) throws SQLException {
		getJdbcTemplate().getDataSource().getConnection().setCatalog(catalog);
	}
	
	@Override
    public String getJdbcUserName()throws SQLException {
		return getJdbcTemplate().getDataSource().getConnection().getMetaData().getUserName();
	}

	@Override
    public void executeSQL(String sql, String prefix)throws SQLException {
		String[] s = sql.split(prefix);
		for (String sqls : s) {
			if (StringUtils.isNotBlank(sqls)) {
				getJdbcTemplate().execute(sqls.trim());
			}
		}
	}

	@Override
    public void executeSQLWithNoResult(String sql) {
		getJdbcTemplate().execute(sql.trim());
	}
	
	private int getTableFieldNums(String tablename) {
		String sql = "select count(COLUMN_NAME) from USER_TAB_COLS where table_name='"+tablename+"'";
		int rownum=0;
		rownum=Integer.parseInt(getJdbcTemplate().queryForObject(sql,
				new RowMapper<String>() {
			@Override
            public String mapRow(ResultSet set, int arg1)
					throws SQLException {
				return set.getString(1);
			}
		}));
		return rownum;
	}
	
	private boolean isDateTimeColumn(String tablename,int columnIndex){
		if(StringUtils.isNotBlank(tablename)){
			String upperTableName=tablename.toUpperCase();
			if("JC_ACQUISITION".equals(upperTableName)){
				if(columnIndex==7||columnIndex==8){
					return true;
				}
			}
			if("JC_ADVERTISING".equals(upperTableName)){
				if(columnIndex==10||columnIndex==11){
					return true;
				}
			}
			if("JC_COMMENT".equals(upperTableName)){
				if(columnIndex==6||columnIndex==7){
					return true;
				}
			}
			if("JC_CONTENT".equals(upperTableName)){
				if(columnIndex==7){
					return true;
				}
			}
			if("JC_CONTENT_CHECK".equals(upperTableName)){
				if(columnIndex==6){
					return true;
				}
			}
			if("JC_CONTENT_EXT".equals(upperTableName)){
				if(columnIndex==8){
					return true;
				}
			}
			if("JC_GUESTBOOK".equals(upperTableName)){
				if(columnIndex==7||columnIndex==8){
					return true;
				}
			}
			if("JC_LOG".equals(upperTableName)){
				if(columnIndex==5){
					return true;
				}
			}
			if("JC_MESSAGE".equals(upperTableName)){
				if(columnIndex==4){
					return true;
				}
			}
			if("JC_RECEIVER_MESSAGE".equals(upperTableName)){
				if(columnIndex==4){
					return true;
				}
			}
			if("JC_SITE_FLOW".equals(upperTableName)){
				if(columnIndex==5){
					return true;
				}
			}
			if("JC_USER".equals(upperTableName)){
				if(columnIndex==6||columnIndex==8||columnIndex==14){
					return true;
				}
			}
			if("JC_USER_EXT".equals(upperTableName)){
				if(columnIndex==4){
					return true;
				}
			}
			if("JC_VOTE_RECORD".equals(upperTableName)){
				if(columnIndex==4){
					return true;
				}
			}
			if("JC_VOTE_TOPIC".equals(upperTableName)){
				if(columnIndex==5||columnIndex==6){
					return true;
				}
			}
			if("JO_AUTHENTICATION".equals(upperTableName)){
				if(columnIndex==5||columnIndex==7){
					return true;
				}
			}
			if("JO_USER".equals(upperTableName)){
				if(columnIndex==5||columnIndex==7||columnIndex==12){
					return true;
				}
			}
			if("JC_JOB_APPLY".equals(upperTableName)){
				if(columnIndex==4){
					return true;
				}
			}
			if("JC_WORKFLOW_EVENT".equals(upperTableName)){
				if(columnIndex==5||columnIndex==6){
					return true;
				}
			}
			if("JC_WORKFLOW_RECORD".equals(upperTableName)){
				if(columnIndex==5){
					return true;
				}
			}
			if("JC_TASK".equals(upperTableName)){
				if(columnIndex==19){
					return true;
				}
			}
			if("JC_USER_RESUME".equals(upperTableName)){
				if(columnIndex==8||columnIndex==16){
					return true;
				}
			}
			if("JC_PLUG".equals(upperTableName)){
				if(columnIndex==6||columnIndex==7||columnIndex==8){
					return true;
				}
			}
			if("JC_SITE_ACCESS".equals(upperTableName)){
				if(columnIndex==4||columnIndex==5){
					return true;
				}
			}
			if("JC_SITE_ACCESS_PAGES".equals(upperTableName)){
				if(columnIndex==4||columnIndex==5){
					return true;
				}
			}
			if("JC_SITE_ACCESS_COUNT".equals(upperTableName)){
				if(columnIndex==4){
					return true;
				}
			}
			if("JC_SITE_ACCESS_STATISTIC".equals(upperTableName)){
				if(columnIndex==2){
					return true;
				}
			}
			if("JC_CONFIG".equals(upperTableName)){
				if(columnIndex==21||columnIndex==22||columnIndex==36||columnIndex==37){
					return true;
				}
			}
			if("JC_CONTENT_BUY".equals(upperTableName)){
				if(columnIndex==9){
					return true;
				}
			}
			if("JC_CONTENT_CHARGE".equals(upperTableName)){
				if(columnIndex==7){
					return true;
				}
			}
			if("JC_CONFIG_CONTENT_CHARGE".equals(upperTableName)){
				if(columnIndex==18){
					return true;
				}
			}
			if("JC_USER_ACCOUNT".equals(upperTableName)){
				if(columnIndex==13||columnIndex==14){
					return true;
				}
			}
			if("JC_ACCOUNT_DRAW".equals(upperTableName)){
				if(columnIndex==7){
					return true;
				}
			}
			if("JC_ACCOUNT_PAY".equals(upperTableName)){
				if(columnIndex==7){
					return true;
				}
			}
			
		}
		return false;
	}
	
	private boolean isColbColumn(String tablename,int columnIndex){
		if(StringUtils.isNotBlank(tablename)){
			String upperTableName=tablename.toUpperCase();
			if("JC_ACQUISITION".equals(upperTableName)){
				//PLAN_LIST
				if(columnIndex==15){
					return true;
				}
			}
			if("JC_ADVERTISING".equals(upperTableName)){
				//AD_CODE
				if(columnIndex==6){
					return true;
				}
			}
			if("JC_CHANNEL_TXT".equals(upperTableName)){
				//TXT TXT1 TXT2 TXT3
				if(columnIndex==2||columnIndex==3||columnIndex==4||columnIndex==5){
					return true;
				}
			}
			if("JC_COMMENT_EXT".equals(upperTableName)){
				//TEXT REPLY
				if(columnIndex==3||columnIndex==4){
					return true;
				}
			}
			if("JC_CONTENT_TXT".equals(upperTableName)){
				//TXT TXT1 TXT2 TXT3
				if(columnIndex==2||columnIndex==3||columnIndex==4||columnIndex==5){
					return true;
				}
			}
			if("JC_GUESTBOOK_EXT".equals(upperTableName)){
				//CONTENT REPLY
				if(columnIndex==3||columnIndex==4){
					return true;
				}
			}
			if("JC_MESSAGE".equals(upperTableName)){
				//MSG_CONTENT
				if(columnIndex==3){
					return true;
				}
			}
			if("JC_RECEIVER_MESSAGE".equals(upperTableName)){
				//MSG_CONTENT
				if(columnIndex==3){
					return true;
				}
			}
			if("JC_SITE_TXT".equals(upperTableName)){
				//TXT_VALUE
				if(columnIndex==3){
					return true;
				}
			}
			if("JC_SITE_COMPANY".equals(upperTableName)){
				//DESCRIPTION
				if(columnIndex==7){
					return true;
				}
			}
			if("JC_VOTE_REPLY".equals(upperTableName)){
				//REPLY
				if(columnIndex==2){
					return true;
				}
			}
			if("JC_USER_RESUME".equals(upperTableName)){
				//JOB_DESCRIPTION
				if(columnIndex==18){
					return true;
				}
			}
			if("JC_DIRECTIVE_TPL".equals(upperTableName)){
				//JOB_DESCRIPTION
				if(columnIndex==4){
					return true;
				}
			}
		}
		return false;
	}
	
	private LobHandler lobHandler;
	public LobHandler getLobHandler() {
		return lobHandler;
	}

	public void setLobHandler(LobHandler lobHandler) {
		this.lobHandler = lobHandler;
	}
	
}