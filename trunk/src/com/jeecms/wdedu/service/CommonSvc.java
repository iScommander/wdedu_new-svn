package com.jeecms.wdedu.service;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.page.Pagination;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
//import com.sygh.pojo.common.DBTable;


public interface CommonSvc {
//    public List<DBTable> getAllDbTableName();

    public Integer getAllDbTableSize();


    public <T> Serializable save(T entity);


    public <T> void saveOrUpdate(T entity);


    public <T> void delete(T entity);


    public Pagination findPager(Finder finder, int pageNo, int pageSize);

    /**
     * 删除实体集合
     *
     * @param <T>
     * @param entities
     */
    public <T> void deleteAllEntitie(Collection<T> entities);

    /**
     * 根据实体名获取对象
     */
    public <T> T get(Class<T> class1, Serializable id);

    /**
     * 根据实体名返回全部对象
     *
     * @param <T>
     * @return
     */
    public <T> List<T> getList(Class clas);

    /**
     * 根据实体名获取对象
     */
    public <T> T getEntity(Class entityName, Serializable id);

    /**
     * 根据实体名称和字段名称和字段值获取唯一记录
     *
     * @param <T>
     * @param entityClass
     * @param propertyName
     * @param value
     * @return
     */
    public <T> T findUniqueByProperty(Class<T> entityClass,String propertyName, Object value);

    /**
     * 按属性查找对象列表.
     */
    public <T> List<T> findByProperty(Class<T> entityClass,
                                      String propertyName, Object value);

    /**
     * 加载全部实体
     *
     * @param <T>
     * @param entityClass
     * @return
     */
    public <T> List<T> loadAll(final Class<T> entityClass);


    //根据hql返回单个对象
    public <T> T singleResult(String hql);


    //根据hql返回多个对象
    public <T> List<T> singleResultList(String hql);

    /**
     * 删除实体主键ID删除对象
     *
     * @param <T>
     */
    public <T> void deleteEntityById(Class entityName, Serializable id);

    /**
     * 更新指定的实体
     *
     * @param <T>
     * @param pojo
     */
    public <T> void updateEntitie(T pojo);

    /**
     * 通过hql 查询语句查找对象
     *
     * @param <T>
     * @return
     */
    public <T> List<T> findByQueryString(String hql);

    /**
     * 根据sql更新
     *
     * @return
     */
    public int updateBySqlString(String sql);

    /**
     * 根据sql查找List
     *
     * @param <T>
     * @param query
     * @return
     */
    public <T> List<T> findListbySql(String query, Class<T> clazz);

    /**
     * 通过属性称获取实体带排序
     *
     * @param <T>
     * @return
     */
    public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
                                             String propertyName, Object value, String orderName, boolean isAsc);

    /**
     * 获取自动完成列表
     *
     * @return
     */

    public Integer executeSql(String sql, List<Object> param);


    public Integer executeSql(String sql, Object... param);


    public Integer executeSql(String sql, Map<String, Object> param);


    public List<Map<String, Object>> findForJdbc(String sql, int page, int rows);


    public List<Map<String, Object>> findForJdbc(String sql, Object... objs);


    public List<Map<String, Object>> findForJdbcParam(String sql, int page,
                                                      int rows, Object... objs);

    public Map<String, Object> findOneForJdbc(String sql, Object... objs);


    public Long getCountForJdbc(String sql);

    public Long getCountForJdbc2(String sql);

    public Long getCountForJdbcParam(String sql, Object[] objs);

    public <T> void batchSaveOrUpdate(List<T> entitys);


    public <T> List<T> findByCriterions(Class<T> entityClass, Criterion... criterions);

    public List find(Finder finder);

    public <T> T getOneResult(Finder finder);
    public String getUserName(Integer userId);

//    public void sendMessage(String title, Integer userId, String content);
//
//    public void sendSMS(int tempId, String phone, Object[] param);

//    public String getUserName(Integer userId);

//    public String getNameByIdOrCode(String id, String code, String key);

}
