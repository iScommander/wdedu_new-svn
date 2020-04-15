package com.jeecms.cms.action.admin.main;

import com.jeecms.cms.entity.assist.CmsWebservice;
import com.jeecms.cms.manager.assist.CmsWebserviceMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.core.entity.*;
import com.jeecms.core.manager.CmsConfigItemMng;
import com.jeecms.core.manager.CmsGroupMng;
import com.jeecms.core.manager.CmsLogMng;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.web.WebErrors;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.wdedu.entity.JcUserGroup;
import com.jeecms.wdedu.entity.TBaseProvince;
import com.jeecms.wdedu.service.CommonSvc;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jeecms.common.page.SimplePage.cpn;

@Controller
public class CmsMemberAct {
	private static final Logger log = LoggerFactory
			.getLogger(CmsMemberAct.class);

	@Autowired
	private CommonSvc commonSvc;
	List<TBaseProvince> tBaseProvinceList;

	@RequiresPermissions("member:v_list")
	@RequestMapping("/member/v_list.do")
	public String list(String queryUsername, String queryEmail,
			Integer queryGroupId, Boolean queryDisabled, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		Pagination pagination = manager.getPage(queryUsername, queryEmail,
				null, queryGroupId, queryDisabled, false, null, 
				null,null,null,cpn(pageNo),
				CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);

		model.addAttribute("queryUsername", queryUsername);
		model.addAttribute("queryEmail", queryEmail);
		model.addAttribute("queryGroupId", queryGroupId);
		model.addAttribute("queryDisabled", queryDisabled);
		model.addAttribute("groupList", cmsGroupMng.getList());
		return "member/list";
	}

	@RequiresPermissions("member:v_add")
	@RequestMapping("/member/v_add.do")
	public String add(ModelMap model,HttpServletRequest request) {
		CmsSite site=CmsUtils.getSite(request);
		List<CmsGroup> groupList = cmsGroupMng.getList();
		List<CmsConfigItem>registerItems=cmsConfigItemMng.getList(site.getConfig().getId(), CmsConfigItem.CATEGORY_REGISTER);
		tBaseProvinceList = commonSvc.loadAll(TBaseProvince.class);
		model.addAttribute("tBaseProvinceList",tBaseProvinceList);
		model.addAttribute("registerItems", registerItems);
		model.addAttribute("groupList", groupList);
		return "member/add";
	}

	@RequiresPermissions("member:v_edit")
	@RequestMapping("/member/v_edit.do")
	public String edit(Integer id, Integer queryGroupId, Boolean queryDisabled,
			HttpServletRequest request, ModelMap model) {
		String queryUsername = RequestUtils.getQueryParam(request,
				"queryUsername");
		String queryEmail = RequestUtils.getQueryParam(request, "queryEmail");
		CmsSite site=CmsUtils.getSite(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsUser user=manager.findById(id);
		List<CmsGroup> groupList = cmsGroupMng.getList();
		List<CmsConfigItem>registerItems=cmsConfigItemMng.getList(site.getConfig().getId(), CmsConfigItem.CATEGORY_REGISTER);
		List<String>userAttrValues=new ArrayList<String>();
		for(CmsConfigItem item:registerItems){
			userAttrValues.add(user.getAttr().get(item.getField()));
		}
		Integer[] groupIds = CmsGroup.fetchIds(user.getGroups());
		model.addAttribute("queryUsername", queryUsername);
		model.addAttribute("queryEmail", queryEmail);
		model.addAttribute("queryGroupId", queryGroupId);
		model.addAttribute("queryDisabled", queryDisabled);
		model.addAttribute("groupList", groupList);
		model.addAttribute("groupIds", groupIds);
		model.addAttribute("cmsMember", user);
		model.addAttribute("registerItems", registerItems);
		model.addAttribute("userAttrValues", userAttrValues);
		tBaseProvinceList = commonSvc.loadAll(TBaseProvince.class);
		model.addAttribute("tBaseProvinceList",tBaseProvinceList);
		return "member/edit";
	}

	@RequiresPermissions("member:o_save")
	@RequestMapping("/member/o_save.do")
	public String save(CmsUser bean, CmsUserExt ext, String username,
			String email, String password, Integer groupId,Integer grain,
			HttpServletRequest request, ModelMap model, Integer[] groupIds,
					   String province_id,String major_type_id,String vipEndTime) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String ip = RequestUtils.getIpAddr(request);
		groupId = 1; //存新表，原来的设置个默认的
		Map<String, String> attrs = RequestUtils.getRequestMap(request, "attr_");
		attrs.put("major_type_id", major_type_id);
		attrs.put("province_id", province_id);
		attrs.put("vip_valid_time", vipEndTime);

		bean = manager.registerMember(username, email, password, ip, groupId, grain, false, ext, attrs);
		//保存用户组
		Map<String, String> attr = new HashMap<>();

		if (groupIds!=null && groupIds.length!=0) {
			List<JcUserGroup> groupList = new ArrayList<>();
			for (int i = 0; i < groupIds.length; i++) {
				JcUserGroup g = new JcUserGroup();
				g.setUserId(bean.getId());
				g.setGroupId(groupIds[i]);
				groupList.add(g);
			}
		commonSvc.batchSaveOrUpdate(groupList);
		}


		cmsWebserviceMng.callWebService("false",username, password, email, ext,CmsWebservice.SERVICE_TYPE_ADD_USER);
		log.info("save CmsMember id={}", bean.getId());
		cmsLogMng.operating(request, "cmsMember.log.save", "id=" + bean.getId()
				+ ";username=" + bean.getUsername());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("member:o_update")
	@RequestMapping("/member/o_update.do")
	public String update(Integer id, String email, String password,
			Boolean disabled, CmsUserExt ext, Integer groupId,Integer grain,
			String queryUsername, String queryEmail, Integer queryGroupId,
			Boolean queryDisabled, Integer pageNo, HttpServletRequest request,
			ModelMap model, Integer[] groupIds,String major_type_id,String province_id,String vipEndTime) {
		WebErrors errors = validateUpdate(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Map<String,String>attrs=RequestUtils.getRequestMap(request, "attr_");
		attrs.put("major_type_id",major_type_id);
		attrs.put("province_id",province_id);
		attrs.put("vip_valid_time",vipEndTime);

		CmsUser bean = manager.updateMember(id, email, password, disabled, ext,groupId,grain,attrs);
		//保存用户组权限
		List<JcUserGroup> groupList = new ArrayList<>();
		List<JcUserGroup> user_group = commonSvc.findByProperty(JcUserGroup.class, "userId", id);
		commonSvc.deleteAllEntitie(user_group);
		if (groupIds!=null && groupIds.length!=0) {

			for (int i = 0; i < groupIds.length; i++) {
				JcUserGroup g = new JcUserGroup();
				g.setUserId(id);
				g.setGroupId(groupIds[i]);
				groupList.add(g);
			}
			commonSvc.batchSaveOrUpdate(groupList);
		}

		cmsWebserviceMng.callWebService("false",bean.getUsername(), password, email, ext,CmsWebservice.SERVICE_TYPE_UPDATE_USER);
		log.info("update CmsMember id={}.", bean.getId());
		cmsLogMng.operating(request, "cmsMember.log.update", "id="
				+ bean.getId() + ";username=" + bean.getUsername());
		return list(queryUsername, queryEmail, queryGroupId, queryDisabled,
				pageNo, request, model);
	}

	@RequiresPermissions("member:o_delete")
	@RequestMapping("/member/o_delete.do")
	public String delete(Integer[] ids, Integer queryGroupId,
			Boolean queryDisabled, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		String queryUsername = RequestUtils.getQueryParam(request,
				"queryUsername");
		String queryEmail = RequestUtils.getQueryParam(request, "queryEmail");
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsUser[] beans = manager.deleteByIds(ids);
		for (CmsUser bean : beans) {
			Map<String,String>paramsValues=new HashMap<String, String>();
			paramsValues.put("username", bean.getUsername());
			paramsValues.put("admin", "false");
			cmsWebserviceMng.callWebService(CmsWebservice.SERVICE_TYPE_DELETE_USER, paramsValues);
			log.info("delete CmsMember id={}", bean.getId());
			cmsLogMng.operating(request, "cmsMember.log.delete", "id="
					+ bean.getId() + ";username=" + bean.getUsername());
		}
		return list(queryUsername, queryEmail, queryGroupId, queryDisabled,
				pageNo, request, model);
	}
	
	@RequiresPermissions("member:o_check")
	@RequestMapping("/member/o_check.do")
	public String check(Integer[] ids, Integer queryGroupId,
			Boolean queryDisabled, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		String queryUsername = RequestUtils.getQueryParam(request,
				"queryUsername");
		String queryEmail = RequestUtils.getQueryParam(request, "queryEmail");
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		for(Integer id:ids){
			CmsUser user=manager.findById(id);
			user.setDisabled(false);
			manager.updateUser(user);
			log.info("check CmsMember id={}", user.getId());
			cmsLogMng.operating(request, "cmsMember.log.delete", "id="
					+ user.getId() + ";username=" + user.getUsername());
		}
		return list(queryUsername, queryEmail, queryGroupId, queryDisabled,
				pageNo, request, model);
	}

	@RequiresPermissions("member:v_check_username")
	@RequestMapping(value = "/member/v_check_username.do")
	public void checkUsername(HttpServletRequest request, HttpServletResponse response) {
		String username=RequestUtils.getQueryParam(request,"username");
		String pass;
		if (StringUtils.isBlank(username)) {
			pass = "false";
		} else {
			pass = manager.usernameNotExist(username) ? "true" : "false";
		}
		ResponseUtils.renderJson(response, pass);
	}
	
	

	private WebErrors validateSave(CmsUser bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		return errors;
	}

	private WebErrors validateEdit(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldExist(id, errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldExist(id, errors)) {
			return errors;
		}
		// TODO 验证是否为管理员，管理员不允许修改。
		return errors;
	}

	private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if(!errors.ifEmpty(ids, "ids")){
			for (Integer id : ids) {
				vldExist(id, errors);
			}
		}
		return errors;
	}

	private boolean vldExist(Integer id, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		CmsUser entity = manager.findById(id);
		if (errors.ifNotExist(entity, CmsUser.class, id)) {
			return true;
		}
		return false;
	}

	@Autowired
	private CmsGroupMng cmsGroupMng;
	@Autowired
	private CmsLogMng cmsLogMng;
	@Autowired
	private CmsUserMng manager;
	@Autowired
	private CmsConfigItemMng cmsConfigItemMng;
	@Autowired
	private CmsWebserviceMng cmsWebserviceMng;
}