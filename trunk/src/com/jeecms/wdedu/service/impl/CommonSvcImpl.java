package com.jeecms.wdedu.service.impl;

import com.jeecms.cms.action.CommonUpload;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.dao.IGenericBaseCommonDao;
import com.jeecms.wdedu.service.CommonSvc;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@Service
public class CommonSvcImpl extends CommonUpload implements CommonSvc {

    /**
     * HIBERNATE 的 order 属性
     */
    public static final String ORDER_ENTRIES = "orderEntries";
    @Autowired
    public IGenericBaseCommonDao commonDao;

    public Session getSession() {
        return commonDao.getSession();
    }

    @Resource
    public void setCommonDao(IGenericBaseCommonDao commonDao) {
        this.commonDao = commonDao;
    }

    //	/**
//	 * 获取所有数据库表
//	 *
//	 * @return
//	 */
//	public List<DBTable> getAllDbTableName() {
//		return commonDao.getAllDbTableName();
//	}
    @Override
    public Integer getAllDbTableSize() {
        return commonDao.getAllDbTableSize();
    }


    @Override
    public <T> Serializable save(T entity) {
        return commonDao.save(entity);
    }


    @Override
    public <T> void saveOrUpdate(T entity) {
        commonDao.saveOrUpdate(entity);

    }


    @Override
    public <T> void delete(T entity) {
        commonDao.delete(entity);

    }

    /**
     * 删除实体集合
     *
     * @param <T>
     * @param entities
     */
    @Override
    public <T> void deleteAllEntitie(Collection<T> entities) {
        commonDao.deleteAllEntitie(entities);
    }

    /**
     * 根据实体名获取对象
     */
    @Override
    public <T> T get(Class<T> class1, Serializable id) {
        return commonDao.get(class1, id);
    }

    /**
     * 根据实体名返回全部对象
     *
     * @param <T>
     * @return
     */

    @Override
    public <T> List<T> getList(Class clas) {
        return commonDao.loadAll(clas);
    }

    /**
     * 根据实体名获取对象
     */
    @Override
    public <T> T getEntity(Class entityName, Serializable id) {
        return commonDao.getEntity(entityName, id);
    }

    /**
     * 根据实体名称和字段名称和字段值获取唯一记录
     *
     * @param <T>
     * @param entityClass
     * @param propertyName
     * @param value
     * @return
     */
    @Override
    public <T> T findUniqueByProperty(Class<T> entityClass,
                                      String propertyName, Object value) {
        return commonDao.findUniqueByProperty(entityClass, propertyName, value);
    }

    /**
     * 按属性查找对象列表.
     */
    @Override
    public <T> List<T> findByProperty(Class<T> entityClass,
                                      String propertyName, Object value) {

        return commonDao.findByProperty(entityClass, propertyName, value);
    }

    /**
     * 加载全部实体
     *
     * @param <T>
     * @param entityClass
     * @return
     */
    @Override
    public <T> List<T> loadAll(final Class<T> entityClass) {
        return commonDao.loadAll(entityClass);
    }

    @Override
    public <T> T singleResult(String hql) {
        return commonDao.singleResult(hql);
    }

    @Override
    public <T> List<T> singleResultList(String hql) {
        return commonDao.singleResultList(hql);
    }


    /**
     * 删除实体主键ID删除对象
     *
     * @param <T>
     */
    @Override
    public <T> void deleteEntityById(Class entityName, Serializable id) {
        commonDao.deleteEntityById(entityName, id);
    }

    /**
     * 更新指定的实体
     *
     * @param <T>
     * @param pojo
     */
    @Override
    public <T> void updateEntitie(T pojo) {
        commonDao.updateEntitie(pojo);

    }

    /**
     * 通过hql 查询语句查找对象
     *
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> findByQueryString(String hql) {
        return commonDao.findByQueryString(hql);
    }

    /**
     * 根据sql更新
     *
     * @return
     */
    @Override
    public int updateBySqlString(String sql) {
        return commonDao.updateBySqlString(sql);
    }

    /**
     * 根据sql查找List
     *
     * @param <T>
     * @param query
     * @return
     */
    @Override
    public <T> List<T> findListbySql(String query, Class<T> clazz) {
        return commonDao.findListbySqlone(query, clazz);
    }


    /**
     * 通过属性称获取实体带排序
     *
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
                                             String propertyName, Object value, String orderName, boolean isAsc) {
        return commonDao.findByPropertyisOrder(entityClass, propertyName,
                value, orderName, isAsc);
    }


    public List findByExample(final String entityName,
                              final Object exampleEntity) {
        return commonDao.findByExample(entityName, exampleEntity);
    }


    @Override
    public Integer executeSql(String sql, List<Object> param) {
        return commonDao.executeSql(sql, param);
    }


    @Override
    public Integer executeSql(String sql, Object... param) {
        return commonDao.executeSql(sql, param);
    }


    @Override
    public Integer executeSql(String sql, Map<String, Object> param) {
        return commonDao.executeSql(sql, param);
    }


    @Override
    public List<Map<String, Object>> findForJdbc(String sql, int page, int rows) {
        return commonDao.findForJdbc(sql, page, rows);
    }


    @Override
    public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
        return commonDao.findForJdbc(sql, objs);
    }


    @Override
    public List<Map<String, Object>> findForJdbcParam(String sql, int page,
                                                      int rows, Object... objs) {
        return commonDao.findForJdbcParam(sql, page, rows, objs);
    }


    @Override
    public Map<String, Object> findOneForJdbc(String sql, Object... objs) {
        return commonDao.findOneForJdbc(sql, objs);
    }


    @Override
    public Long getCountForJdbc(String sql) {
        return commonDao.getCountForJdbc(sql);
    }

    @Override
    public Long getCountForJdbc2(String sql) {
        return commonDao.getCountForJdbc2(sql);
    }

    @Override
    public Long getCountForJdbcParam(String sql, Object[] objs) {
        return commonDao.getCountForJdbcParam(sql, objs);
    }

    @Override
    public <T> List<T> findByCriterions(Class<T> entityClass, Criterion... criterions) {
        return commonDao.findByCriterions(entityClass, criterions);
    }

    @Override
    public <T> void batchSaveOrUpdate(List<T> entitys) {
        this.commonDao.batchSaveOrUpdate(entitys);
    }


    /**
     * 通过HQL查询对象列表
     *
     * @param hql    hql语句
     * @param values 数量可变的参数
     */
    public List find(String hql, Object... values) {
        return createQuery(hql, values).list();
    }

    /**
     * 通过HQL查询唯一对象
     */
    public Object findUnique(String hql, Object... values) {
        return createQuery(hql, values).uniqueResult();
    }

    /**
     * 通过Finder获得分页数据
     *
     * @param finder   Finder对象
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return
     */
    @SuppressWarnings("unchecked")
    public Pagination find(Finder finder, int pageNo, int pageSize) {
        int totalCount = countQueryResult(finder);
        Pagination p = new Pagination(pageNo, pageSize, totalCount);
        if (totalCount < 1) {
            p.setList(new ArrayList());
            return p;
        }
        Query query = getSession().createQuery(finder.getOrigHql());
        finder.setParamsToQuery(query);
        query.setFirstResult(p.getFirstResult());
        query.setMaxResults(p.getPageSize());
        if (finder.isCacheable()) {
            query.setCacheable(true);
        }
        List list = query.list();
        p.setList(list);
        return p;
    }

    /**
     * 通过Finder获得列表数据
     *
     * @param finder
     * @return
     */
    @Override
    public List find(Finder finder) {
        Query query = finder.createQuery(getSession());
        List list = query.list();
        return list;
    }

    /**
     * 根据查询函数与参数列表创建Query对象,后续可进行更多处理,辅助函数.
     */
    public Query createQuery(String queryString, Object... values) {
        Assert.hasText(queryString);
        Query queryObject = getSession().createQuery(queryString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }
        return queryObject;
    }


    /**
     * 获得Finder的记录总数
     *
     * @param finder
     * @return
     */
    public int countQueryResult(Finder finder) {
        Query query = getSession().createQuery(finder.getRowCountHql());
        finder.setParamsToQuery(query);
        if (finder.isCacheable()) {
            query.setCacheable(true);
        }
        return ((Number) query.iterate().next()).intValue();
    }

    public int countQueryResultByGroup(Finder finder, String selectSql) {
        Query query = getSession().createQuery(finder.getRowCountTotalHql(selectSql));
        setParamsToQuery(finder, query);
        return ((Number) query.iterate().next()).intValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Pagination findPager(Finder finder, int pageNo, int pageSize) {
        int totalCount = countQueryResult(finder);
        Pagination p = new Pagination(pageNo, pageSize, totalCount);
        if (totalCount < 1) {
            p.setList(new ArrayList());
            return p;
        }
        Query query = getSession().createQuery(finder.getOrigHql());
        finder.setParamsToQuery(query);
        query.setFirstResult(p.getFirstResult());
        query.setMaxResults(p.getPageSize());
        if (finder.isCacheable()) {
            query.setCacheable(true);
        }

        List list = query.list();
        p.setList(list);
        return p;
    }

    @Override
    public <T> T getOneResult(Finder finder) {
        T t = null;
        Query query = finder.createQuery(getSession());
        List list = query.list();
        if (list.size() > 0) {
            t = (T) list.get(0);
        }
        return t;
    }

    @Override
    public String getUserName(Integer userId) {
        return null;
    }

    private Query setParamsToQuery(Finder finder, Query query) {
        finder.setParamsToQuery(query);
        if (finder.isCacheable()) {
            query.setCacheable(true);
        }
        return query;
    }

}
