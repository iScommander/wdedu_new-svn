package com.jeecms.wdedu.dao.impl;

import com.jeecms.wdedu.dao.IGenericBaseCommonDao;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.*;

//import com.sygh.pojo.common.DBTable;
//import com.sygh.pojo.common.JeecgEntityTitle;


/**
 * 类描述： DAO层泛型基类
 *
 * @param <T>
 * @param <PK>
 * @version 1.0
 * @author: jeecg
 * @date： 日期：2012-12-7 时间：上午10:16:48
 */
public class IGenericBaseCommonDaoImpl<T, PK extends Serializable> implements IGenericBaseCommonDao {
    /**
     * 分页SQL
     */
    public static final String MYSQL_SQL = "select * from ( {0}) sel_tab00 limit {1},{2}";         //mysql
    public static final String POSTGRE_SQL = "select * from ( {0}) sel_tab00 limit {2} offset {1}";//postgresql
    public static final String ORACLE_SQL = "select * from (select row_.*,rownum rownum_ from ({0}) row_ where rownum <= {1}) where rownum_>{2}"; //oracle
    public static final String SQLSERVER_SQL = "select * from ( select row_number() over(order by tempColumn) tempRowNumber, * from (select top {1} tempColumn = 0, {0}) t ) tt where tempRowNumber > {2}"; //sqlserver
    /**
     * 初始化Log4j的一个实例
     */
    private static final Logger logger = Logger.getLogger(IGenericBaseCommonDaoImpl.class);
    /**
     * 注入一个sessionFactory属性,并注入到父类(HibernateDaoSupport)
     **/
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Session getSession() {
        // 事务必须是开启的(Required)，否则获取不到
        return sessionFactory.getCurrentSession();
    }
    /**
     * 获取所有数据表
     * @return
     */
//	public List<DBTable> getAllDbTableName() {
//		List<DBTable> resultList = new ArrayList<DBTable>();
//		SessionFactory factory = getSession().getSessionFactory();
//		Map<String, ClassMetadata> metaMap = factory.getAllClassMetadata();
//		for (String key : (Set<String>) metaMap.keySet()) {
//			DBTable dbTable=new DBTable();
//			AbstractEntityPersister classMetadata = (AbstractEntityPersister) metaMap.get(key);
//			dbTable.setTableName(classMetadata.getTableName());
//			dbTable.setEntityName(classMetadata.getEntityName());
//						Class<?> c;
//			try {
//				c = Class.forName(key);
//				JeecgEntityTitle t = c.getAnnotation(JeecgEntityTitle.class);
//				dbTable.setTableTitle(t!=null?t.name():"");
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//						resultList.add(dbTable);
//		}
//		return resultList;
//	}

    /**
     * 获得该类的属性和类型
     *
     * @param entityName 注解的实体类
     */
    private <T> void getProperty(Class entityName) {
        ClassMetadata cm = sessionFactory.getClassMetadata(entityName);
        String[] str = cm.getPropertyNames(); // 获得该类所有的属性名称
        for (int i = 0; i < str.length; i++) {
            String property = str[i];
            String type = cm.getPropertyType(property).getName(); // 获得该名称的类型
            System.out.println(property + "---&gt;" + type);
        }
    }

    /**
     * 获取所有数据表
     *
     * @return
     */
    @Override
    public Integer getAllDbTableSize() {
        SessionFactory factory = getSession().getSessionFactory();
        Map<String, ClassMetadata> metaMap = factory.getAllClassMetadata();
        return metaMap.size();
    }

    /**
     * 根据实体名字获取唯一记录
     *
     * @param propertyName
     * @param value
     * @return
     */
    @Override
    public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value) {
        Assert.hasText(propertyName);
        return (T) createCriteria(entityClass, Restrictions.eq(propertyName, value)).uniqueResult();
    }

    /**
     * 按属性查找对象列表.
     */
    @Override
    public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value) {
        Assert.hasText(propertyName);
        return (List<T>) createCriteria(entityClass, Restrictions.eq(propertyName, value)).list();
    }

    /**
     * 按属性查找对象列表.
     */
    @Override
    public <T> List<T> findByCriterions(Class<T> entityClass, Criterion... criterions) {

        return (List<T>) createCriteria(entityClass, criterions).list();
    }

    /**
     * 根据传入的实体持久化对象
     */
    @Override
    public <T> Serializable save(T entity) {
        try {
            Serializable id = getSession().save(entity);
            getSession().flush();
            if (logger.isDebugEnabled()) {
                logger.debug("保存实体成功," + entity.getClass().getName());
            }
            return id;
        } catch (RuntimeException e) {
            logger.error("保存实体异常", e);
            throw e;
        }

    }

    /**
     * 批量保存数据
     *
     * @param <T>
     * @param entitys 要持久化的临时实体对象集合
     */
    @Override
    public <T> void batchSave(List<T> entitys) {
        for (int i = 0; i < entitys.size(); i++) {
            getSession().save(entitys.get(i));
            if (i % 20 == 0) {
                //20个对象后才清理缓存，写入数据库
                getSession().flush();
                getSession().clear();
            }

        }
    }

    /**
     * 批量保存数据
     *
     * @param <T>
     * @param entitys 要持久化的临时实体对象集合
     */
    @Override
    public <T> void batchSaveOrUpdate(List<T> entitys) {

        try {
            for (int i = 0; i < entitys.size(); i++) {
                getSession().saveOrUpdate(entitys.get(i));
//                if ((i+1) % 20 == 0) {
//                    //20个对象后才清理缓存，写入数据库
//                    getSession().flush();
//                    getSession().clear();
//                }
                if (i == 0) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("添加或更新成功," + entitys.get(0).getClass().getName());
                    }
                }
            }
            getSession().flush();
            getSession().clear();
        } catch (RuntimeException e) {
            logger.error("添加或更新异常", e);
            throw e;
        }
    }

    /**
     * 根据传入的实体添加或更新对象
     *
     * @param <T>
     * @param entity
     */

    @Override
    public <T> void saveOrUpdate(T entity) {
        try {
            getSession().clear();
            getSession().saveOrUpdate(entity);
            getSession().flush();
            if (logger.isDebugEnabled()) {
                logger.debug("添加或更新成功," + entity.getClass().getName());
            }
        } catch (RuntimeException e) {
            logger.error("添加或更新异常", e);
            throw e;
        }
    }

    /**
     * 根据传入的实体删除对象
     */
    @Override
    public <T> void delete(T entity) {
        try {
            getSession().delete(entity);
            getSession().flush();
            if (logger.isDebugEnabled()) {
                logger.debug("删除成功," + entity.getClass().getName());
            }
        } catch (RuntimeException e) {
            logger.error("删除异常", e);
            throw e;
        }
    }

    /**
     * 根据主键删除指定的实体
     *
     * @param <T>
     */
    @Override
    public <T> void deleteEntityById(Class entityName, Serializable id) {
        delete(get(entityName, id));
        getSession().flush();
    }

    /**
     * 删除全部的实体
     *
     * @param <T>
     * @param entitys
     */
    @Override
    public <T> void deleteAllEntitie(Collection<T> entitys) {
        for (Object entity : entitys) {
            getSession().delete(entity);
            getSession().flush();
        }
    }

    /**
     * 根据Id获取对象。
     */
    @Override
    public <T> T get(Class<T> entityClass, final Serializable id) {

        return (T) getSession().get(entityClass, id);

    }

    /**
     * 根据主键获取实体并加锁。
     *
     * @param <T>
     * @param entityName
     * @param id
     * @return
     */
    @Override
    public <T> T getEntity(Class entityName, Serializable id) {

        T t = (T) getSession().get(entityName, id);
        if (t != null) {
            getSession().flush();
        }
        return t;
    }

    /**
     * 更新指定的实体
     *
     * @param <T>
     * @param pojo
     */
    @Override
    public <T> void updateEntitie(T pojo) {
        getSession().update(pojo);
        getSession().flush();
    }

    /**
     * 更新指定的实体
     *
     * @param <T>
     */
    public <T> void updateEntitie(String className, Object id) {
        getSession().update(className, id);
        getSession().flush();
    }

    /**
     * 根据主键更新实体
     */
    @Override
    public <T> void updateEntityById(Class entityName, Serializable id) {
        updateEntitie(get(entityName, id));
    }

    /**
     * 通过hql 查询语句查找对象
     *
     * @param query
     * @return
     */
    @Override
    public List<T> findByQueryString(final String query) {

        Query queryObject = getSession().createQuery(query);
        List<T> list = queryObject.list();
        if (list.size() > 0) {
            getSession().flush();
        }
        return list;

    }

    /**
     * 通过hql查询唯一对象
     *
     * @param <T>
     * @return
     */
    @Override
    public <T> T singleResult(String hql) {
        T t = null;
        Query queryObject = getSession().createQuery(hql);
        List<T> list = queryObject.list();
        if (list.size() == 1) {
            getSession().flush();
            t = list.get(0);
        } else if (list.size() > 0) {
            try {
                throw new Exception("查询结果数:" + list.size() + "大于1");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    /**
     * 通过hql查询多个对象
     *
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> singleResultList(String hql) {
        List<T> t = new ArrayList<T>();
        Query queryObject = getSession().createQuery(hql);
        List<T> list = queryObject.list();
        if (list.size() > 0) {
            getSession().flush();
            for (T tList : list) {
                t.add(tList);
            }
            return t;
        } else {
            return t;
        }
    }

    /**
     * 通过hql 查询语句查找HashMap对象
     *
     * @return
     */
    @Override
    public Map<Object, Object> getHashMapbyQuery(String hql) {

        Query query = getSession().createQuery(hql);
        List list = query.list();
        Map<Object, Object> map = new HashMap<Object, Object>();
        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
            Object[] tm = (Object[]) iterator.next();
            map.put(tm[0].toString(), tm[1].toString());
        }
        return map;

    }

    /**
     * 通过sql更新记录
     *
     * @param query
     * @return
     */
    @Override
    public int updateBySqlString(final String query) {

        Query querys = getSession().createSQLQuery(query);
        return querys.executeUpdate();
    }

    /**
     * 通过sql查询语句查找对象
     *
     * @return
     */
    @Override
    public <T> List<T> findListbySql(final String sql) {
        Query querys = getSession().createSQLQuery(sql);
        return querys.list();
    }

    @Override
    public <T> List<T> findListbySqlone(String query, Class<T> clazz) {
        Query querys = getSession().createSQLQuery(query).addEntity(clazz);
        return querys.list();
    }

    /**
     * 创建Criteria对象，有排序功能。
     *
     * @param <T>
     * @param entityClass
     * @param isAsc
     * @param criterions
     * @return
     */
    public <T> Criteria createCriteria(Class<T> entityClass, String orderName, boolean isAsc, Criterion... criterions) {
        Criteria criteria = createCriteria(entityClass, criterions);
        if (isAsc) {
            criteria.addOrder(Order.asc(orderName));
        } else {
            criteria.addOrder(Order.desc(orderName));
        }
        return criteria;
    }

    /**
     * 创建Criteria对象带属性比较
     *
     * @param <T>
     * @param entityClass
     * @param criterions
     * @return
     */
    public <T> Criteria createCriteria(Class<T> entityClass, Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(entityClass);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }

    @Override
    public <T> List<T> loadAll(final Class<T> entityClass) {
        Criteria criteria = createCriteria(entityClass);
        return criteria.list();
    }

    /**
     * 创建单一Criteria对象
     *
     * @param <T>
     * @param entityClass
     * @return
     */
    private <T> Criteria createCriteria(Class<T> entityClass) {
        Criteria criteria = getSession().createCriteria(entityClass);
        return criteria;
    }

    /**
     * 根据属性名和属性值查询. 有排序
     *
     * @param <T>
     * @param entityClass
     * @param propertyName
     * @param value
     * @param isAsc
     * @return
     */
    @Override
    public <T> List<T> findByPropertyisOrder(Class<T> entityClass, String propertyName, Object value, String orderName, boolean isAsc) {
        Assert.hasText(propertyName);
        return createCriteria(entityClass, orderName, isAsc, Restrictions.eq(propertyName, value)).list();
    }

    /**
     * 根据属性名和属性值 查询 且要求对象唯一.
     *
     * @return 符合条件的唯一对象.
     */
    public <T> T findUniqueBy(Class<T> entityClass, String propertyName, Object value) {
        Assert.hasText(propertyName);
        return (T) createCriteria(entityClass, Restrictions.eq(propertyName, value)).uniqueResult();
    }

    /**
     * 根据查询条件与参数列表创建Query对象
     *
     * @param session Hibernate会话
     * @param hql     HQL语句
     * @param objects 参数列表
     * @return Query对象
     */
    public Query createQuery(Session session, String hql, Object... objects) {
        Query query = session.createQuery(hql);
        if (objects != null) {
            for (int i = 0; i < objects.length; i++) {
                query.setParameter(i, objects[i]);
            }
        }
        return query;
    }

    /**
     * 根据实体名返回全部对象
     *
     * @param <T>
     * @param hql
     * @param size
     * @return
     */

    /**
     * 批量插入实体
     *
     * @return
     */
    public <T> int batchInsertsEntitie(List<T> entityList) {
        int num = 0;
        for (int i = 0; i < entityList.size(); i++) {
            save(entityList.get(i));
            num++;
        }
        return num;
    }

    /**
     * 使用占位符的方式填充值 请注意：like对应的值格式："%"+username+"%" Hibernate Query
     *
     * @param hql 占位符号?对应的值，顺序必须一一对应 可以为空对象数组，但是不能为null
     * @return 2008-07-19 add by liuyang
     */
    public List<T> executeQuery(final String hql, final Object[] values) {
        Query query = getSession().createQuery(hql);
        // query.setCacheable(true);
        for (int i = 0; values != null && i < values.length; i++) {
            query.setParameter(i, values[i]);
        }

        return query.list();

    }

    /**
     * 根据实体模版查找
     *
     * @param entityName
     * @param exampleEntity
     * @return
     */

    @Override
    public List findByExample(final String entityName, final Object exampleEntity) {
        Assert.notNull(exampleEntity, "Example entity must not be null");
        Criteria executableCriteria = (entityName != null ? getSession().createCriteria(entityName) : getSession().createCriteria(exampleEntity.getClass()));
        executableCriteria.add(Example.create(exampleEntity));
        return executableCriteria.list();
    }

    /*
     */

    /**
     * 使用指定的检索标准检索数据并分页返回数据
     */
    @Override
    public List<Map<String, Object>> findForJdbc(String sql, int page, int rows) {
        //封装分页SQL
        int beginNum = (page - 1) * rows;
        String[] sqlParam = new String[3];
        sqlParam[0] = sql;
        sqlParam[1] = beginNum + "";
        sqlParam[2] = rows + "";
        sql = MessageFormat.format(MYSQL_SQL, sqlParam);

        return this.jdbcTemplate.queryForList(sql);
    }


    /**
     * 使用指定的检索标准检索数据并分页返回数据-采用预处理方式
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> findForJdbcParam(String sql, int page, int rows, Object... objs) {
        //封装分页SQL
        int beginNum = (page - 1) * rows;
        String[] sqlParam = new String[3];
        sqlParam[0] = sql;
        sqlParam[1] = beginNum + "";
        sqlParam[2] = rows + "";
        sql = MessageFormat.format(MYSQL_SQL, sqlParam);

        return this.jdbcTemplate.queryForList(sql, objs);
    }

    /**
     * 使用指定的检索标准检索数据并分页返回数据For JDBC
     */
    @Override
    public Long getCountForJdbc(String sql) {
        return this.jdbcTemplate.queryForObject(sql, null, Long.class);
    }

    @Override
    public Long getCountForJdbc2(String sql) {
        String newsql = "select count(*) from (" + sql + ") as t";
        return this.jdbcTemplate.queryForObject(sql, null, Long.class);
    }

    /**
     * 使用指定的检索标准检索数据并分页返回数据For JDBC-采用预处理方式
     */
    @Override
    public Long getCountForJdbcParam(String sql, Object[] objs) {
        return this.jdbcTemplate.queryForObject(sql, objs, Long.class);
    }

    @Override
    public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
        return this.jdbcTemplate.queryForList(sql, objs);
    }

    @Override
    public Integer executeSql(String sql, List<Object> param) {
        return this.jdbcTemplate.update(sql, param);
    }

    @Override
    public Integer executeSql(String sql, Object... param) {
        return this.jdbcTemplate.update(sql, param);
    }

    public Integer countByJdbc(String sql, Object... param) {
        return this.jdbcTemplate.queryForObject(sql, param, Integer.class);
    }

    @Override
    public Map<String, Object> findOneForJdbc(String sql, Object... objs) {
        try {
            return this.jdbcTemplate.queryForMap(sql, objs);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
