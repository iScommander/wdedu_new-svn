package com.wdedu.service.impl;

import com.jeecms.cms.action.CommonUpload;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsGroupMng;
import com.jeecms.core.manager.CmsUserExtMng;
import com.wdedu.dao.ICommonDao;
import com.wdedu.entity.common.DBTable;
import com.wdedu.service.CommonService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service("commonService")
@Transactional
public class CommonServiceImpl extends CommonUpload implements CommonService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonServiceImpl.class);
	
	@Autowired
	private CmsGroupMng cmsGroupMng;
	
	@Autowired
	private CmsUserExtMng cmsUserExtMng;
	@Autowired
	public ICommonDao commonDao = null;

	public Session getSession()
	{
		return commonDao.getSession();
	}
	@Resource
	public void setCommonDao(ICommonDao commonDao) {
		this.commonDao = commonDao;
	}



	/**
	 * HIBERNATE 的 order 属性
	 */
	public static final String ORDER_ENTRIES = "orderEntries";
	/**
	 * 获取所有数据库表
	 * 
	 * @return
	 */
	public List<DBTable> getAllDbTableName() {
		return commonDao.getAllDbTableName();
	}
	public Integer getAllDbTableSize() {
		return commonDao.getAllDbTableSize();
	}

	public <T> Serializable save(T entity) {
		return commonDao.save(entity);
	}

	public <T> void saveOrUpdate(T entity) {
		commonDao.saveOrUpdate(entity);

	}

	public <T> void delete(T entity) {
		commonDao.delete(entity);

	}

	/**
	 * 删除实体集合
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteAllEntitie(Collection<T> entities) {
		commonDao.deleteAllEntitie(entities);
	}

	/**
	 * 根据实体名获取对象
	 */
	public <T> T get(Class<T> class1, Serializable id) {
		return commonDao.get(class1, id);
	}

	/**
	 * 根据实体名返回全部对象
	 * 
	 * @param <T>
	 * @param hql
	 * @param size
	 * @return
	 */
	
	public <T> List<T> getList(Class clas) {
		return commonDao.loadAll(clas);
	}

	/**
	 * 根据实体名获取对象
	 */
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
	public <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		return commonDao.findUniqueByProperty(entityClass, propertyName, value);
	}

	/**
	 * 按属性查找对象列表.
	 */
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
	public <T> List<T> loadAll(final Class<T> entityClass) {
		return commonDao.loadAll(entityClass);
	}

	public <T> T singleResult(String hql) {
		return commonDao.singleResult(hql);
	}

	public <T> List<T> singleResultList(String hql) {
		return commonDao.singleResultList(hql);
	}
	
	
	/**
	 * 删除实体主键ID删除对象
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteEntityById(Class entityName, Serializable id) {
		commonDao.deleteEntityById(entityName, id);
	}

	/**
	 * 更新指定的实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void updateEntitie(T pojo) {
		commonDao.updateEntitie(pojo);

	}

	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findByQueryString(String hql) {
		return commonDao.findByQueryString(hql);
	}

	/**
	 * 根据sql更新
	 * 
	 * @param query
	 * @return
	 */
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
	public <T> List<T> findListbySql(String query) {
		return commonDao.findListbySqlone(query);
	}

	
	/**
	 * 通过属性称获取实体带排序
	 * 
	 * @param <T>
	 * @param clas
	 * @return
	 */
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
			String propertyName, Object value, String orderName, boolean isAsc) {
		return commonDao.findByPropertyisOrder(entityClass, propertyName,
				value, orderName, isAsc);
	}
	/**
	 * 
	 * cq方式分页
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
//	public PageList getPageList(final CriteriaQuery cq, final boolean isOffset) {
//		return commonDao.getPageList(cq, isOffset);
//	}
//	
	/**
//	 * 
	 * hqlQuery方式分页
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
//	public PageList getPageList(final HqlQuery hqlQuery,
//			final boolean needParameter) {
//		return commonDao.getPageList(hqlQuery, needParameter);
//	}

	/**
	 * 
	 * sqlQuery方式分页
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
//	public PageList getPageListBySql(final HqlQuery hqlQuery,
//			final boolean isToEntity) {
//		return commonDao.getPageListBySql(hqlQuery, isToEntity);
//	}

	

	public List findByExample(final String entityName,
			final Object exampleEntity) {
		return commonDao.findByExample(entityName, exampleEntity);
	}
	/**
	 * 通过cq获取全部实体
	 * 
	 * @param <T>
	 * @param cq
	 * @return
	 */
//	public <T> List<T> getListByCriteriaQuery(final CriteriaQuery cq,
//			Boolean ispage) {
//		return commonDao.getListByCriteriaQuery(cq, ispage);
//	}
	

	
	public Integer executeSql(String sql, List<Object> param) {
		return commonDao.executeSql(sql, param);
	}

	
	public Integer executeSql(String sql, Object... param) {
		return commonDao.executeSql(sql, param);
	}

	
	public Integer executeSql(String sql, Map<String, Object> param) {
		return commonDao.executeSql(sql, param);
	}
//	
//	
	public List<Map<String, Object>> findForJdbc(String sql, int page, int rows) {
		return commonDao.findForJdbc(sql, page, rows);
	}

	
	public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
		return commonDao.findForJdbc(sql, objs);
	}

	
	public List<Map<String, Object>> findForJdbcParam(String sql, int page,
			int rows, Object... objs) {
		return commonDao.findForJdbcParam(sql, page, rows, objs);
	}
//
//	
//	public <T> List<T> findObjForJdbc(String sql, int page, int rows,
//			Class<T> clazz) {
//		return commonDao.findObjForJdbc(sql, page, rows, clazz);
//	}
//    
//    public <T> List<T> findBySql(String sql, Class<T> clazz) {
//        return commonDao.findBySql(sql, clazz);
//    }

	
	public Map<String, Object> findOneForJdbc(String sql, Object... objs) {
		return commonDao.findOneForJdbc(sql, objs);
	}

	
	public Long getCountForJdbc(String sql) {
		return commonDao.getCountForJdbc(sql);
	}
	public Long getCountForJdbc2(String sql) {
		return commonDao.getCountForJdbc2(sql);
	}
//
	public Long getCountForJdbcParam(String sql, Object[] objs) {
		return commonDao.getCountForJdbcParam(sql,objs);
	}
//	
//	public <T> Criteria createCriteria(Class<T> entityClass, String orderName, boolean isAsc, Criterion... criterions)
//	{
//		return commonDao.createCriteria(entityClass, orderName, isAsc,criterions);
//	}
//	public <T> Criteria createCriteria(Class<T> entityClass,  Criterion... criterions)
//	{
//		return commonDao.createCriteria(entityClass,criterions);
//	}
	public  <T> List<T> findByCriterions(Class<T> entityClass, Criterion... criterions)
	{
		return commonDao.findByCriterions(entityClass, criterions);
	}
//	public <T> void batchSave(List<T> entitys) {
//		this.commonDao.batchSave(entitys);
//	}
//
    public <T> void batchSaveOrUpdate(List<T> entitys) {
        this.commonDao.batchSaveOrUpdate(entitys);
    }
//
//	*//**
//	 * 通过hql 查询语句查找对象
//	 * 
//	 * @param <T>
//	 * @param query
//	 * @return
//	 *//*
//	public <T> List<T> findHql(String hql, Object... param) {
//		return this.commonDao.findHql(hql, param);
//	}
//
//    public <T> List<T> pageList(DetachedCriteria dc, int firstResult,
//            int maxResult) {
//        return this.commonDao.pageList(dc, firstResult, maxResult);
//    }

	@SuppressWarnings("unchecked")
//	public Pager queryForPage(int curPage, int pagesize,final CriteriaQuery cq) {
//		//String queryCount = "SELECT COUNT(*) from (" + sql + ") as _t3";
//		Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(getSession());
//		
//		int allcounts=criteria.list().size();
//		Pager page = new Pager();
//		page.setCurPage(curPage);
//		page.setPageRows(pagesize);
//		page.setTotalRows(allcounts);
//		page.settotal(page.totalRows);
//		
//		int offset = PagerUtil.getOffset(allcounts, curPage, pagesize);
//		criteria.setFirstResult(offset);
//		criteria.setMaxResults(pagesize);	
//		List data =criteria.list();
//		page.setData(data);
//		return page;
//	}


   /* public <T> List<T> findByDetached(DetachedCriteria dc) {
        return this.commonDao.findByDetached(dc);
    }*/

	
	
	/**
	 * 通过HQL查询对象列表
	 * 
	 * @param hql
	 *            hql语句
	 * @param values
	 *            数量可变的参数
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
	 * @param finder
	 *            Finder对象
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页条数
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
	 * 通过Criteria获得分页数据
	 * 
	 * @param crit
	 * @param pageNo
	 * @param pageSize
	 * @param projection
	 * @param orders
	 * @return
	 */
//	@SuppressWarnings("unchecked")
//	public Pagination findByCriteria(Criteria crit, int pageNo, int pageSize) {
//		CriteriaImpl impl = (CriteriaImpl) crit;
//		// 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
//		Projection projection = impl.getProjection();
//		ResultTransformer transformer = impl.getResultTransformer();
//		List<CriteriaImpl.OrderEntry> orderEntries;
//		try {
//			orderEntries = (List) MyBeanUtils
//					.getFieldValue(impl, ORDER_ENTRIES);
//			MyBeanUtils.setFieldValue(impl, ORDER_ENTRIES, new ArrayList());
//		} catch (Exception e) {
//			throw new RuntimeException(
//					"cannot read/write 'orderEntries' from CriteriaImpl", e);
//		}
//
//		int totalCount = ((Number) crit.setProjection(Projections.rowCount())
//				.uniqueResult()).intValue();
//		Pagination p = new Pagination(pageNo, pageSize, totalCount);
//		if (totalCount < 1) {
//			p.setList(new ArrayList());
//			return p;
//		}
//
//		// 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
//		crit.setProjection(projection);
//		if (projection == null) {
//			crit.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
//		}
//		if (transformer != null) {
//			crit.setResultTransformer(transformer);
//		}
//		try {
//			MyBeanUtils.setFieldValue(impl, ORDER_ENTRIES, orderEntries);
//		} catch (Exception e) {
//			throw new RuntimeException(
//					"set 'orderEntries' to CriteriaImpl faild", e);
//		}
//		crit.setFirstResult(p.getFirstResult());
//		crit.setMaxResults(p.getPageSize());
//		p.setList(crit.list());
//		return p;
//	}

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
	
	public <T> T getOneResult(Finder finder) {
		T t = null;
		Query query = finder.createQuery(getSession());
		List list = query.list();
		if(list.size()>0)
		{
			t=(T) list.get(0);
		}
		return t;
	}

    @Override
    public void sendMessage(String title, Integer userId, String content) {

    }

    @Override
    public void sendSMS(int tempId, String phone, Object[] param) {

    }

    private Query setParamsToQuery(Finder finder, Query query){
		finder.setParamsToQuery(query);
		if (finder.isCacheable()) {
			query.setCacheable(true);
		}
		return query;
	}

//	public EasyuiPager findEasyuiPager(Finder finder, int pageNo, int pageSize){
//		int totalCount = countQueryResult(finder);
//		EasyuiPager e=new EasyuiPager();
//		Pagination p = new Pagination(pageNo, pageSize, totalCount);
//		
//		e.setRows(p.getList());
//		e.setTotal(p.getTotalCount());
//		
//		if (totalCount < 1) {
//			e.setRows(new ArrayList());			
//			return e;
//		}
//		Query query = getSession().createQuery(finder.getOrigHql());
//		finder.setParamsToQuery(query);
//		query.setFirstResult(p.getFirstResult());
//		query.setMaxResults(p.getPageSize());
//		if (finder.isCacheable()) {
//			query.setCacheable(true);
//		}
//		List list = query.list();
//		e.setRows(list);
//		//p.setList(list);
//		return e;		
//		
//	}
	
	/*public Map<String , Object> saveOrder(T_n_contract_info contract ,String userName, String phone , String orderId, MultipartFile[] files,Integer userId,
			String[] types, CmsSite site, String deleteId, HttpServletRequest request){
		
		Map<String , Object> returnMap = new HashMap<String, Object>();
		returnMap.put("flag", true);
		
		Session session=getSession();
		//开启事务
		Transaction tx = session.getTransaction();
		try {
		    // 用户账号
            String userAcount = StringUtils.isBlank(userName)?phone:userName;

            //用户属性信息
            Map<String ,String> attrMap = new HashMap<String, String>();
            attrMap.put("province", request.getParameter("province"));
            attrMap.put("city", request.getParameter("city"));
            attrMap.put("borough", request.getParameter("region"));
            
            String school = request.getParameter("schoolName");
            String school1 = request.getParameter("school1");
            if(!"".equals(school)){
                attrMap.put("schoolname", school);
            }else{
                attrMap.put("schoolname", school1);
            }
//          attrMap.put("schoolname", request.getParameter("schoolName"));
            attrMap.put("majorType", request.getParameter("majorType"));
            attrMap.put("email", request.getParameter("email"));
            attrMap.put("mailAddress", request.getParameter("mailAddress"));
            attrMap.put("examYear", request.getParameter("examYear"));
            
            //扩展信息
            CmsUserExt userExt = null;
            CmsUser customUser = cmsUserMng.findByUsername(userAcount);
            
            // 判断是无忧还是学大
            boolean xdFlag = false;
            for (int i = 0; i < types.length; i++) {
                if ("xdzy".equals(types[i])) {
                    xdFlag = true;
                    break;
                }
            }
            
            //用户存在更新用户组以及其他基本信息
            if(customUser != null){
                
                userExt = customUser.getUserExt();
                userExt.setGender(Boolean.valueOf(request.getParameter("sex")));
                userExt.setRealname(request.getParameter("name"));
                userExt.setPhone(request.getParameter("phone"));
                
                cmsUserMng.updateMember(customUser.getId(), attrMap);//保存属性信息
                if (xdFlag) {
                    customUser.setGroup(cmsGroupMng.findById(CommonConstant.GROUP_ID_1));//更新用户组：学大家长未绑卡的状态
                } else {
                    customUser.setGroup(cmsGroupMng.findById(CommonConstant.GROUP_ID_5));//更新用户组：一对一用户
                }
                cmsUserExtMng.update(userExt, customUser);
            }
            
            //用户不存在，注册账户
            else{
                userExt = new CmsUserExt();
                userExt.setGender(Boolean.valueOf(request.getParameter("sex")));
                userExt.setRealname(request.getParameter("name"));
                userExt.setPhone(phone);
                String ip = RequestUtils.getIpAddr(request);
                String pwd = "123456";//默认密码
                String groupId = "5";
                if (xdFlag) {
                    groupId = "1";//学大家长未绑卡的状态
                }
                
                cmsUserMng.registerMember(userAcount, request.getParameter("email"), pwd, ip, groupId,null,false,userExt,attrMap);
            }

            //1.1.保存合同
            contract.setIsVioded(CommonConstant.IS_VIOLDED_0);
            String companyName = StringUtils.EMPTY;
            String companyId = request.getParameter("branchCompany");
            if (companyId != null) {
                T_n_company company = get(T_n_company.class,
                        Integer.parseInt(request.getParameter("branchCompany")));
                companyName = company.getCompanyName();
            }
            contract.setBranchCompany(companyName);    //分公司
            commonDao.saveOrUpdate(contract);

			//2.保存订单信息
			String type = "";
			for (int i = 0; i < types.length; i++) {
				type = types[i];
				String typeName = "志愿填报一对一";
				if ("zzzs".equals(type)) {
				    typeName = "自主招生一对一";
				} else if ("gat".equals(type)) {
                    typeName = "港澳台一对一";
				} else if ("xdzy".equals(type)) {
                    typeName = "学大志愿一对一";
				} else if ("bjts".equals(type)) {
                    typeName = "背景提升项目";
                }

				String[] serviceContents = request.getParameterValues(type+"Content");
				String serviceContent = "";
				if(serviceContents != null && serviceContents.length > 0){
					for (String string : serviceContents) {
						serviceContent += string+",";
					}
				}
				if(StringUtils.isNotBlank(serviceContent)){
					serviceContent = serviceContent.substring(0,serviceContent.length()-1);
				}
				//orderId为空表示新增，否则为修改
				if(StringUtils.isBlank(orderId)){
					List<T_n_order> orderList = commonDao.findByCriterions(T_n_order.class, new Criterion[]{
						Restrictions.eq("username", userAcount),Restrictions.eq("serviceType", type),Restrictions.eq("isVioded", CommonConstant.IS_VIOLDED_0)});
					if(!CollectionUtils.isEmpty(orderList)){
						returnMap.put("message", "用户号=["+userAcount+"],服务类型=["+typeName+"]的订单重复,请不要重复录入！");
					}
				}
				T_n_order order = createOrder(orderId , type, serviceContent, userAcount, userId, companyName, request,i);
				commonDao.saveOrUpdate(order);

	            //1上传文件
	            String linkId = contract.getContractNo();
                // 删除要删除的文件
                if (StringUtils.isNotBlank(deleteId)) {
                    String[] array = deleteId.split(";");
                    for (int j = 0; j < array.length; j++) {
                        int mId = Integer.parseInt(array[j]);
                        deleteEntityById(T_n_material.class, mId);
                    }
                }
	            // 上传新增附件
                MultipartFile file=files[0];
           
	            if (files != null && files.length != 0  && !file.isEmpty()) {
	                String ctx = request.getContextPath();
	                updateFile(site, files, linkId, ctx);
	            }

	            // 通知会计
	            if (StringUtils.isNotBlank(companyId)) {
	                T_n_company company = get(T_n_company.class, Integer.parseInt(companyId));
	                String provinceName = company.getProvince();
	                List<Province> provinceList = findByProperty(Province.class, "name", provinceName);
	                if (provinceList != null && provinceList.size() > 0) {
	                    Integer provinceId = provinceList.get(0).getId();
	                    
	                    // 查找有该省数据权限的用户
	                    StringBuilder teacherSql = new StringBuilder();
	                    teacherSql.append("select DISTINCT(a.user_id), a.username from jc_user a, jc_user_province c, jc_user_role d where 1=1 ");
	                    teacherSql.append(" and a.user_id=c.user_id and a.user_id=d.user_id " );
	                    if (FrontUtils.isXueDa(CmsUtils.getUser(request))) {
	                        teacherSql.append(" and d.role_id = " + CommonConstant.roleId_xd_kj);
	                    } else {
	                        teacherSql.append(" and d.role_id = " + CommonConstant.roleId_kj);
	                    }
	                    teacherSql.append(" and a.is_disabled=0 " );
	                    teacherSql.append(" and c.province_id = " + provinceId);
	                    List<Map<String,Object>> teacherList = findForJdbc(teacherSql.toString());
                        if (teacherList != null) {
                            for (Map<String, Object> tMap : teacherList) {
                                sendMessage("订单确认通知", Integer.parseInt(String.valueOf(tMap.get("user_id"))),
                                        "订单【" + order.getOrderId() + "】已新建,请您确认付款信息！");

                                if (FrontUtils.isPhone(String.valueOf(tMap.get("username")))) {
                                    sendSMS(CommonConstant.TEMP_201, String.valueOf(tMap.get("username")),
                                            new Object[] {order.getOrderId()});
                                }
                            }
                        }
	                }
	            }
			}

			
		}
		catch (Exception e) {
			returnMap.put("flag", false);
			returnMap.put("message", "新增/编辑订单异常");
			tx.rollback();
		}
		return returnMap;
	}*/
	
//    private T_n_order createOrder(String orderId, String type, String serviceContent,
//            String userAcount, Integer userId, String companyName, HttpServletRequest request,
//            int index) {
//        T_n_order order = null;
//		if(StringUtils.isBlank(orderId)){
//			orderId = this.getOrderId(index);
//            order = new T_n_order();
//            // 只有新建的时候更新签单人
//            order.setUserId(userId);                                    //录入订单人
//            order.setSingler(request.getParameter("contractMan"));      //签单人
//            order.setSingleTime(request.getParameter("contractDate"));  //签单时间
//		} else {
//		    order = get(T_n_order.class, orderId);
//
//		    if (order == null) {
//	            order = new T_n_order();
//	            // 只有新建的时候更新签单人
//	            order.setUserId(userId);                                    //录入订单人
//	            order.setSingler(request.getParameter("contractMan"));      //签单人
//	            order.setSingleTime(request.getParameter("contractDate"));  //签单时间
//		    }
//		}
//
//		order.setOrderId(orderId);									//订单号
//		order.setServiceType(type);									//服务类型
//		order.setServiceContent(serviceContent);					//服务内容
//		order.setContractNo(request.getParameter("contractNo"));				//合同号
//		order.setIsVioded(CommonConstant.IS_VIOLDED_0);							//是否有效
//		order.setOrderAmount(request.getParameter(type+"Amount"));	//签单价格
//        order.setOtherDescription(request.getParameter(type+"OtherDiscription").trim());//其他说明
//        order.setPayAll(request.getParameter(type + "payAll"));// 1:预付款，2:全款
//		order.setProvince(request.getParameter("provinceName"));	//订单省
//		order.setBranchCompany(companyName);	//分公司
//		order.setStatus(CommonConstant.order_status_1);									//订单状态
//		order.setUpdateTime(DateUtil.getDateStr("yyyy-MM-dd"));		//更新时间
//		order.setUsername(userAcount);		//服务客户的用户号
//		order.setServiceNode("等待付款确认");//服务节点
//		order.setStudentName(request.getParameter("name"));			//学生姓名
//
//		return order;
//	}
//
//	/**
//	 * 生成订单号  D+yyyyMMdd+五位序号
//	 * @return
//	 */
//	private String getOrderId(int i){
//		String queryMaxOrderIdSql = "select max(orderId) as orderId from t_n_order";
//		Map<String , Object> orderMap = commonDao.findOneForJdbc(queryMaxOrderIdSql, new Object[]{});
//		String index = "";
//		if(orderMap.get("orderId") == null){
//			index = "00001";
//		}else{
//			index = (String) orderMap.get("orderId");
//			int indexNum = Integer.parseInt(index.substring(9))+1+i;
//			if(indexNum>=100000){
//				indexNum = 1;
//			}
//			index = String.valueOf(indexNum);
//			while(index.length()<5){
//				index = "0"+index;
//			}
//		}
//		return "D"+DateUtil.getDateStr("yyyyMMdd")+index;
//	}

//      public void sendSMS(int tempId, String phone, Object[] param) {
//        // 获取短信模板
//        H_d_msg_template template = get(H_d_msg_template.class, tempId);
//        String content = String.format(template.getContent(), param);
//        // 发送短信
//        H_sms_record msg = new H_sms_record();
//        msg.setContent(content);
//        msg.setCreate_date(new Date());
//        msg.setPhone(phone);
//        msg.setType(3);
//        msg.setSend_state(false);
//        saveOrUpdate(msg);
//    }

//    @Override
//    public void sendMessage(String title, Integer userId, String content) {
//        T_n_message message = new T_n_message();
//        message.setTitle(title);
//        message.setUser_id(userId);
//        message.setReceive_time(DateUtil.getDateStr("yyyy/MM/dd HH:mm:ss"));
//        message.setStatus("0");
//        message.setContent(content);
//        save(message);
//    }

    @Override
    public String getUserName(Integer userId) {
        CmsUser user = cmsUserMng.findById(userId);

        String userName = StringUtils.EMPTY;
        if (user != null && StringUtils.isNotBlank(user.getUsername())) {
            userName = user.getUsername();
        }
        return userName;
    }

    @Override
    public String getNameByIdOrCode(String id, String code, String key) {
        return null;
    }
//
//
//
//    // t_n_code_mas表根据id或者code取name
//    @Override
//    public String getNameByIdOrCode(String id, String code, String key) {
//        if (StringUtils.isBlank(id) && StringUtils.isBlank(code)) {
//            return StringUtils.EMPTY;
//        }
//
//        Finder f = Finder.create(" from T_n_code_mas bean where bean.key=:key ");
//        f.setParam("key", key);
//        if (StringUtils.isNotBlank(id)) {
//            f.append(" and bean.id=:id ");
//            f.setParam("id", id);
//        }
//        if (StringUtils.isNotBlank(code)) {
//            f.append(" and bean.code=:code ");
//            f.setParam("code", code);
//        }
//        f.append(" order by order ");
//        List<T_n_code_mas> list = find(f);
//        if (ArrayUtils.isNotEmpty(list)) {
//            return list.get(0).getName();
//        } else {
//            return StringUtils.EMPTY;
//        }
//    }

////    // 更新文件
//    public void updateFile(CmsSite site, MultipartFile[] files, String id, String ctx)
//            throws IOException {
//        for (int i = 0; i < files.length; i++) {
//
//            MultipartFile file = files[i];
//            // 重定向
//            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//            String origName = file.getOriginalFilename();
//
//            // 查询是否已经存在
//            Finder f = Finder.create("from t_n_material bean where 1=1");
//            f.append(" and bean.linkId=:linkId");
//            f.setParam("linkId", id);
//            f.append(" and bean.fileName=:fileName");
//            f.setParam("fileName", origName);
//            T_n_material mat = getOneResult(f);
//
//            if (!StringUtils.isEmpty(origName) && mat == null) {
//                String ext = FilenameUtils.getExtension(origName).toLowerCase(Locale.ENGLISH);
//                String fileUrl = ctx + fileRepository.storeByExt(site.getUploadPath(), ext, file);
//                T_n_material material = new T_n_material();
//                material.setFileName(origName);
//                material.setFileUrl(fileUrl);
//                material.setLinkId(id);
//                material.setTime(time);
//                saveOrUpdate(material);
//            }
//        }
//    }
	
}
