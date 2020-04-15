package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.cms.dao.main.ApiRecordDao;
import com.jeecms.cms.entity.main.ApiRecord;

@Repository
public class ApiRecordDaoImpl extends HibernateBaseDao<ApiRecord, Long> implements ApiRecordDao {
	@Override
    public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}
	
	@Override
    public ApiRecord findBySign(String sign, String appId){
		String hql="from ApiRecord bean where bean.sign=:sign and bean.apiAccount.appId=:appId";
		Finder f=Finder.create(hql).setParam("sign", sign).setParam("appId", appId);
		List<ApiRecord>li=find(f);
		if(li.size()>0){
			return li.get(0);
		}else{
			return null;
		}
	}

	@Override
    public ApiRecord findById(Long id) {
		ApiRecord entity = get(id);
		return entity;
	}

	@Override
    public ApiRecord save(ApiRecord bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
    public ApiRecord deleteById(Long id) {
		ApiRecord entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<ApiRecord> getEntityClass() {
		return ApiRecord.class;
	}
}