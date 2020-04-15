package com.jeecms.wdedu.action;

import com.alibaba.fastjson.JSON;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsGroup;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsGroupMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.*;
import com.jeecms.wdedu.service.CommonSvc;
import com.jeecms.wdedu.service.FastZytbSvc;
import com.jeecms.wdedu.service.SmartZytbSvc;
import com.jeecms.wdedu.service.ZytbSvc;
import com.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.util.Date;

import static com.jeecms.common.page.SimplePage.cpn;

@Controller
@RequestMapping(value = "/fastZytb")
public class FastZytbController {

    @Autowired
    private CmsGroupMng cmsGroupMng;
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private FastZytbSvc fastZytbSvc;

    @Autowired
    private ZytbSvc zytbSvc;

    @Autowired
    private ZytbAct zytbAct;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    private CommonSvc commonSvc;

    @Autowired
    private SmartZytbSvc smartZytbSvc;

    //新建志愿方案
    @RequestMapping(value = "/volunteerFlexible.jspx")
    public String getProject(String applicationName, Integer pageNo, HttpServletRequest request, ModelMap model, HttpServletResponse response) throws IOException {

        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        //判断用户是否有省份选择和文理科选择的权限，有的话能动态选择省份
        Set<CmsGroup> groups = user.getGroups();
        Iterator<CmsGroup> groupIt = groups.iterator();
        String yesOrNo = "false";
        while (groupIt.hasNext()) {
            Set<String> groupss = groupIt.next().getPerms();
            Iterator<String> groupssIt = groupss.iterator();
            while (groupssIt.hasNext()) {
                if ("shengfenXuanze:*".equals(groupssIt.next())) {
                    model.addAttribute("roledId", "3");
                    yesOrNo = "true";
                    break;
                }
            }
        }

        CmsGroup group = cmsGroupMng.findById(19);
        if (!groups.contains(group)) {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = null;
            out = response.getWriter();
            out.println("<script language=javascript>alert('您没有智能填报方案的权限,请先购买服务或者联系客服。');window.location='../alipay/fuwuzhongxin.jspx'</script>");
        }
        //方案分页列表
        Pagination pagination = zytbSvc.queryProjects(user.getId(), applicationName, cpn(pageNo), CookieUtils.getPageSize(request));
        //用户信息
        Map userInfo = new HashMap();
        userInfo.put("topRoleLevel", user.getTopRoleLevel());
        userInfo.put("provinceId", user.getAttr().get("province_id"));
        userInfo.put("majorTypeId", user.getAttr().get("major_type_id"));
        /*判断是否为管理角色*/
        boolean isSuper = false;
        if (user.getRoleIds() != null && user.getRoleIds().length != 0) {
            isSuper = true;
        }
        if (user.isSuper()) {
            isSuper = true;
        }
        /**/
        userInfo.put("isSuper", isSuper);
        //省份列表
        List<TBaseProvince> provinceList = commonSvc.loadAll(TBaseProvince.class);
        Map<String, String> provinceMap = new HashMap<>();
        for (TBaseProvince t : provinceList) {
            provinceMap.put(String.valueOf(t.getProvinceId()), t.getProvinceName());
        }
        //批次排名信息
        List batchInfo = zytbSvc.getBatchInfo(userInfo);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("provinceList", provinceList);
        model.addAttribute("provinceMap", provinceMap);
        model.addAttribute("pagination", pagination);
        model.addAttribute("batchInfo", batchInfo);
        model.addAttribute("majorTypeId", user.getAttr().get("major_type_id"));
        model.addAttribute("provinceId", user.getAttr().get("province_id"));
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "kstbProject");
    }

    /**
     * 查询方案详细信息
     *
     * @param applicationId 方案ID
     */
    @RequestMapping("/ksVoluntary.jspx")
    public String getProjectDetail(Integer applicationId, HttpServletRequest request, ModelMap model, HttpServletResponse response) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        //查询方案信息
        TCeeApplications applicationsInfo = commonSvc.findUniqueByProperty(TCeeApplications.class, "id", applicationId);
        if (applicationsInfo == null) {
            return "";
        }

        if (request.getProtocol().compareTo("HTTP/1.0") == 0) {
            response.setHeader("Pragma", "no-cache");
        } else if (request.getProtocol().compareTo("HTTP/1.1") == 0) {
            response.setHeader("Cache-Control", "no-cache");
        }

        //查询方案详情信息
        //List<TCeeApplicationsDetail> applicationsDetailList = commonSvc.findByProperty(TCeeApplicationsDetail.class, "applicationId", applicationId);
        List<TCeeApplicationsDetail> applicationsDetailList = zytbSvc.queryProjectDetailList(applicationId);
        List<TCeeApplicationsDetail> applicationsDetailList1 = commonSvc.findByQueryString("FROM TCeeApplicationsDetail WHERE applicationId = '" + applicationId + "' AND isFormal = 1 order by  isFormal,univOrder,majorOrder");
        Map<String, List<Map<String, String>>> batchMaps = new HashMap<>();  //存批次、院校对应信息
        Map<String, Map<String, List<TCeeApplicationsDetail>>> appMaps = new HashMap(); //存院校、专业对应信息
        for (TCeeApplicationsDetail tcee : applicationsDetailList1) {
            String batchIds = String.valueOf(tcee.getBatchId());
            String univCode = String.valueOf(tcee.getUnivCode()); //院校code
            String univName = String.valueOf(tcee.getUnivName()); //院校name
            String isObey = String.valueOf(tcee.getIsObey()); //是否服从
            String univOrder = String.valueOf(tcee.getUnivOrder());//院校顺序
            String isFormal = String.valueOf(tcee.getIsFormal());//是否为备选专业
            String fanYueId = String.valueOf(tcee.getId());//ID
            Map<String, String> m = new HashMap();
            m.put("univCode", univCode);
            m.put("univName", univName);
            m.put("isObey", isObey);
            m.put("univOrder", univOrder);
            m.put("isFormal", isFormal);
            m.put("batchId", batchIds);
            if (!batchMaps.containsKey(batchIds)) {
                batchMaps.put(batchIds, new ArrayList<Map<String, String>>());
            }
            if (!batchMaps.get(batchIds).contains(m) || univCode == "null") {
                batchMaps.get(batchIds).add(m);
            }
            if (!appMaps.containsKey(batchIds)) {
                appMaps.put(batchIds, new HashMap<String, List<TCeeApplicationsDetail>>());
            }
            if (!appMaps.get(batchIds).containsKey(univOrder)) {
                appMaps.get(batchIds).put(univOrder, new ArrayList<TCeeApplicationsDetail>());
            }
            appMaps.get(batchIds).get(univOrder).add(tcee);
        }
        Map<String, List<Map<String, String>>> batchMap = new HashMap<>();  //存批次、院校对应信息
        Map<String, Map<String, List<TCeeApplicationsDetail>>> appMap = new HashMap(); //存院校、专业对应信息
        for (TCeeApplicationsDetail app : applicationsDetailList) {
            String batchId = String.valueOf(app.getBatchId()); //批次ID
            String univCode = String.valueOf(app.getUnivCode()); //院校code
            String univName = String.valueOf(app.getUnivName()); //院校name
            String isObey = String.valueOf(app.getIsObey()); //是否服从
            String univOrder = String.valueOf(app.getUnivOrder());//院校顺序
            String isFormal = String.valueOf(app.getIsFormal());//是否为备选专业
            String fanYueId = String.valueOf(app.getId());//ID
            Map<String, String> m = new HashMap();
            m.put("univCode", univCode);
            m.put("univName", univName);
            m.put("isObey", isObey);
            m.put("univOrder", univOrder);
            m.put("isFormal", isFormal);
            m.put("batchId", batchId);
            if (!batchMap.containsKey(batchId)) {
                batchMap.put(batchId, new ArrayList<Map<String, String>>());
            }
            if (!batchMap.get(batchId).contains(m) || univCode == "null") {
                batchMap.get(batchId).add(m);
            }
            if (!appMap.containsKey(batchId)) {
                appMap.put(batchId, new HashMap<String, List<TCeeApplicationsDetail>>());
            }
            if (!appMap.get(batchId).containsKey(univOrder)) {
                appMap.get(batchId).put(univOrder, new ArrayList<TCeeApplicationsDetail>());
            }
            appMap.get(batchId).get(univOrder).add(app);
        }
        //省份列表
        List<TBaseProvince> provinceList = commonSvc.loadAll(TBaseProvince.class);
        Map<String, String> provinceMap = new HashMap<>();
        for (TBaseProvince t : provinceList) {
            provinceMap.put(String.valueOf(t.getProvinceId()), t.getProvinceName());
        }
        //查询批次信息
        int year = applicationsInfo.getYear();
        List<TCeeBatch> batchList = zytbSvc.getBatchList(Integer.valueOf(year), Integer.valueOf(applicationsInfo.getProvinceId()), Integer.valueOf(applicationsInfo.getMajorTypeId()));
        //查询已报志愿数量
        List univNumList = zytbSvc.getUnivNum(applicationsInfo.getId());
        Map univNumMap = new HashMap();
        for (Object obj : univNumList) {
            Object[] arr = (Object[]) obj;
            univNumMap.put(String.valueOf(arr[0]), String.valueOf(arr[1]));
        }


        model.addAttribute("applicationsInfo", applicationsInfo);
        model.addAttribute("provinceMap", provinceMap);
        model.addAttribute("univNumMap", univNumMap);
        model.addAttribute("batchList", batchList);
        model.addAttribute("batchMap", batchMap);
        model.addAttribute("appMap", appMap);
        model.addAttribute("batchMaps", batchMaps);
        model.addAttribute("appMaps", appMaps);
        FrontUtils.frontData(request, model, site);

        if (applicationsInfo.getProvinceId() == 18) {
            return FrontUtils.getTplPath(site.getSolutionPath(), "zytb", "voluntary_zj");
        } else {
            return FrontUtils.getTplPath(site.getSolutionPath(), "zytb", "voluntary");
        }
    }

    @RequestMapping("/saveksProject.jspx")
    public String saveProject(TCeeApplications bean, HttpServletRequest request, ModelMap model, String appType, HttpServletResponse response) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        //获取当前日期
        int nowYear, nowMonth, nowDay;
        Calendar calendar = Calendar.getInstance();
        nowYear = calendar.get(Calendar.YEAR);
        nowMonth = calendar.get(Calendar.MONTH) + 1;
        nowDay = calendar.get(Calendar.DATE);
        if (nowMonth >= 9) {
            nowYear += 1;
        }
        Map<String, String> userAttr = user.getAttr(); //jc_user_attr 属性表信息

        int p = bean.getProvinceId();
        int m = bean.getMajorTypeId();
        if (p == 0 || m == 0) {
            bean.setProvinceId(Integer.parseInt(userAttr.get("province_id"))); //省份ID
            bean.setMajorTypeId(Integer.parseInt(userAttr.get("major_type_id"))); //文理科

        }
        int pId = bean.getProvinceId();

        Set<CmsGroup> groups = user.getGroups();
        CmsGroup group1 = cmsGroupMng.findById(6);
        CmsGroup group2 = cmsGroupMng.findById(4);

        long sTime = commonSvc.singleResult("SELECT UNIX_TIMESTAMP(solutionStartDate) FROM TBaseProvince WHERE provinceId = " + pId + "  ");
        long eTime = commonSvc.singleResult("SELECT UNIX_TIMESTAMP(solutionEndDate) FROM TBaseProvince WHERE provinceId = " + pId + "  ");
        List<TCeeApplications> tCeeApplicationsNum = commonSvc.findByQueryString(" FROM TCeeApplications WHERE userId = " + user.getId() + " and provinceId = " + pId + " AND UNIX_TIMESTAMP(createTime) BETWEEN " + sTime + "  AND " + eTime + " ");

        Integer year0 = commonSvc.singleResult("SELECT dataBatchYear FROM TBaseProvince WHERE province_id =" + pId + " ");
        bean.setYear(year0);

        bean.setUserId(user.getId());
        bean.setUserName(user.getUsername());
        Timestamp now = new Timestamp(new Date().getTime());
        bean.setCreateTime(now);
        bean.setUpdateTime(now);
        if (StringUtil.isNotEmpty(appType) && appType.equals("1")) {
            bean.setApplicationType(1);
        } else {
            bean.setApplicationType(2);
        }//手动方案
        //zytbSvc.saveProject(bean);
        //设置默认方案名称
        if (!StringUtils.isNotBlank(bean.getApplicationName())) {
            bean.setApplicationName("我的方案[" + bean.getScore() + "]");
        }
        if (tCeeApplicationsNum.size() == 0 || groups.contains(group1) || groups.contains(group2)) {

            int id = (int) commonSvc.save(bean);
            List<TCeeApplications> detail = commonSvc.findByQueryString("FROM TCeeApplications WHERE id ='" + id + "'");
            for (TCeeApplications tCeeApplications : detail) {
                int province_id = tCeeApplications.getProvinceId();
                int major_type_id = tCeeApplications.getMajorTypeId();
                int year = tCeeApplications.getYear();
                List<TCeeBatch> tCeeBatchList = commonSvc.findByQueryString("FROM TCeeBatch WHERE provinceId = '" + province_id + "' AND majorTypeId = '" + major_type_id + "' AND year = '" + year + "' AND isShow=1 order by order");
                for (TCeeBatch tCeeBatch : tCeeBatchList) {
                    String batchName = tCeeBatch.getBatchName();
                    int batchId = tCeeBatch.getBatchId();
                    int univNum = tCeeBatch.getUnivNum();
                    int extraNum = tCeeBatch.getExtraNum();
                    int majorsNumber = univNum + extraNum;
                    for (int i = 1; i <= majorsNumber; i++) {
                        TCeeApplicationsDetail tCeeApplicationsDetail = new TCeeApplicationsDetail();
                        tCeeApplicationsDetail.setUnivOrder(i);
                        tCeeApplicationsDetail.setApplicationId(id);
                        tCeeApplicationsDetail.setProvinceId(province_id);
                        tCeeApplicationsDetail.setMajorTypeId(major_type_id);
                        tCeeApplicationsDetail.setYear(year);
                        tCeeApplicationsDetail.setBatchId(batchId);
                        tCeeApplicationsDetail.setIsFormal(0);
                        commonSvc.save(tCeeApplicationsDetail);
                        if (i >= univNum) {
                            for (int j = 0; j < extraNum; j++) {
                                i++;
                                TCeeApplicationsDetail tcee = new TCeeApplicationsDetail();
                                tcee.setUnivOrder(j);
                                tcee.setApplicationId(id);
                                tcee.setProvinceId(province_id);
                                tcee.setMajorTypeId(major_type_id);
                                tcee.setYear(year);
                                tcee.setBatchId(batchId);
                                tcee.setIsFormal(1);
                                commonSvc.save(tcee);
                            }
                        }
                    }
                }
            }
            return "redirect:volunteerFlexible.jspx";
        } else {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = null;
            out = response.getWriter();
            out.println("<script language=javascript>alert('当前为限制新建方案时期，请在已建方案中更改方案内容。');window.location='project.jspx'</script>");
            return "";
        }
    }

    //志愿视频
    @RequestMapping(value = "/volunteerVideo.jspx")
    public String getVideo(String applicationName, HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);

        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        String major_type_id = user.getAttr().get("major_type_id");
        model.addAttribute("majorTypeId", major_type_id);

        return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "volunteerVideo");
    }

    //填报说明
    @RequestMapping(value = "/CompleteExplain.jspx")
    public String getCompleteExplain(Integer user_id, Integer is_ks_confirm, HttpServletRequest request, ModelMap
            model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);

        Integer userId = user.getId();
        String isKsConfirm = user.getAttr().get("is_ks_confirm");


        if ((isKsConfirm == null) && (is_ks_confirm != null)) {
            String sql = "INSERT INTO jc_user_attr(user_id,attr_name,attr_value) VALUES(" + userId + ",'is_ks_confirm','1')";
            commonSvc.executeSql(sql);
            isKsConfirm = "1";
        }
        model.addAttribute("userId", userId);
        model.addAttribute("isKsConfirm", isKsConfirm);
        return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "completeExplain");
    }

    /**
     * 志愿筛选-高考志愿填报
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/gkzytb.jspx")
    public String gkzytb(HttpServletRequest request, ModelMap model, String applicationId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        //用户的省份
        String province = user.getAttr().get("province_id");
        //用户的文理科
        //String majorType = user.getAttr().get("major_type_id");
        //查询t_base_province表当前省份有数据的年份
        //TBaseProvince tBaseProvince1 = commonSvc.findUniqueByProperty(TBaseProvince.class, "provinceId", Integer.valueOf(province));
        //int year1 = tBaseProvince1.getDataBatchYear();

        String batchSql = "   " +
                "SELECT c.*,d.intent_univ_num FROM (   " +
                "SELECT a.*,b.id AS application_id FROM `t_cee_batch` a, `t_cee_applications` b   " +
                "WHERE a.year = b.year   " +
                "AND a.province_id = b.province_id    " +
                "AND a.major_type_id = b.major_type_id   " +
                "AND a.`is_show` = 1   " +
                "AND b.id = '" + applicationId + "'   " +
                ") c   " +
                "LEFT JOIN (   " +
                "SELECT *,(LENGTH(intent_univ) - LENGTH(REPLACE(intent_univ,'|','')) + 1) AS intent_univ_num FROM t_cee_applications_require   " +
                "WHERE application_id = '" + applicationId + "'    " +
                "AND (intent_univ IS NOT NULL && intent_univ <> '' && intent_univ <> '0' )   " +
                ") d   " +
                "ON c.application_id = d.application_id   " +
                "AND c.batch_id = d.batch_id ORDER BY c.batch_id ASC";
        List<Map<String, Object>> batchList = commonSvc.findForJdbc(batchSql);
        model.addAttribute("batchList", batchList);
        model.addAttribute("applicationId", applicationId);
        model.addAttribute("province", province);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "volunteerFiltrate");

    }

    /**
     * 志愿筛选-地区筛选
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/dqsx.jspx")
    public String dqsx(HttpServletRequest request, ModelMap model, Integer tbType, String provinceId, String
            applicationId, String batchId, String univ_province_id, String yuanxiao,
                       String univ_is985, String univ_is211, String univ_isFirstRateUniv, String univ_isIndependence, String
                               univ_isFirstRate, String univ_isKeylab, String univ_isExcellent,
                       String univ_type, String major_first, String major_second, Integer pageNo, Integer pageSize, String
                               backward_univ_num, String intent_univ, String avoid_univ, String advanced_univ_num,
                       String stable_univ_num, Integer skip, String firstOrSecond) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        model.addAttribute("applicationId", applicationId);
        model.addAttribute("batchId", batchId);
        model.addAttribute("firstOrSecond", "1");

        TCeeApplications tCeeApplications = commonSvc.get(TCeeApplications.class, Integer.valueOf(applicationId));

        model.addAttribute("tCeeApplications", tCeeApplications);
        String maxUnivNumSql = "SELECT a.* FROM t_cee_batch a , t_cee_applications b WHERE b.id = '" + applicationId + "' AND a.year = b.year AND a.province_id = b.province_id and a.major_type_id = b.major_type_id AND a.batch_id = " + batchId + " ";
        List<Map<String, Object>> max = commonSvc.findForJdbc(maxUnivNumSql);

        model.addAttribute("max_univ_num", max.get(0).get("univ_num"));
        int cNum = 0;
        int wNum = 0;
        int bNum = 0;
        if (max != null && max.get(0).get("c_num") != null) {
            cNum = (int) max.get(0).get("c_num");
        }
        if (max != null && max.get(0).get("w_num") != null) {
            wNum = (int) max.get(0).get("w_num");
        }
        if (max != null && max.get(0).get("b_num") != null) {
            bNum = (int) max.get(0).get("b_num");
        }

        //用户id
        int userId = 1;
        //筛选条件-地区
        String diquSql = "SELECT * FROM `t_base_province`";
        List<Map<String, Object>> diquList = commonSvc.findForJdbc(diquSql);
        model.addAttribute("diquList", diquList);
        //筛选条件-院校分类
        String univTypeSql = "SELECT * FROM `t_data_university_type`";
        List<Map<String, Object>> univTypeList = commonSvc.findForJdbc(univTypeSql);
        model.addAttribute("univTypeList", univTypeList);
        //筛选条件-专业分类(第一级)
        String majorSql = "SELECT * FROM t_data_major WHERE parent_major_id = 1";
        List<Map<String, Object>> majorList = commonSvc.findForJdbc(majorSql);
        model.addAttribute("majorList", majorList);

        String[] applicationIds = applicationId.split(",");
        String app = applicationIds[0];
        String[] batchIds = batchId.split(",");
        String batch = batchIds[0];
        if ("1".equals(firstOrSecond)) {
            int num = fastZytbSvc.update(app, batch, univ_province_id,
                    univ_is985, univ_is211, univ_isIndependence, univ_isFirstRate, univ_isKeylab, univ_isExcellent,
                    univ_type, major_second, advanced_univ_num, stable_univ_num, backward_univ_num, intent_univ, avoid_univ, userId, tbType);
        }

        //查询用户保存的筛选条件
        String conditionSql = "SELECT * FROM `t_cee_applications_require` WHERE application_id ='" + app + "'  AND batch_id = " + batch + "";
        List<Map<String, Object>> conditionList = commonSvc.findForJdbc(conditionSql);

        //查询t_base_province表当前省份有数据的年份
//        TBaseProvince tBaseProvince = commonSvc.findUniqueByProperty(TBaseProvince.class, "provinceId", Integer.valueOf(provinceId));
//        int year = tBaseProvince.getDataPlanYear();
        if (conditionList.size() != 0) {
            String intentUniv = (String) conditionList.get(0).get("intent_univ");
            String[] intentUnivs = intentUniv.split("\\|");
            model.addAttribute("intentUnivs", intentUnivs);
        }

        if (StringUtil.isNotEmpty(conditionList) && conditionList.size() == 1) {
            //省份
            String intent_province_id = String.valueOf(conditionList.get(0).get("intent_province_id"));
            String is985 = String.valueOf(conditionList.get(0).get("is985"));
            String is211 = String.valueOf(conditionList.get(0).get("is211"));
            String isExcellent = String.valueOf(conditionList.get(0).get("isExcellent"));
            //String isIndependence = String.valueOf(conditionList.get(0).get("isIndependence"));
            String isFirstRate = String.valueOf(conditionList.get(0).get("isFirstRate"));
            String isKeyl = String.valueOf(conditionList.get(0).get("isKeylab"));
            String intent_univ_type = String.valueOf(conditionList.get(0).get("intent_univ_type"));
            String advanced_univ_num1 = String.valueOf(conditionList.get(0).get("advanced_univ_num"));
            String stable_univ_num1 = String.valueOf(conditionList.get(0).get("stable_univ_num"));
            String backward_univ_num1 = String.valueOf(conditionList.get(0).get("backward_univ_num"));
            String new_intent_univ = String.valueOf(conditionList.get(0).get("intent_univ"));
            String new_avoid_univ = String.valueOf(conditionList.get(0).get("avoid_univ"));
            //专业(2级)
            String intent_major_id = String.valueOf(conditionList.get(0).get("intent_major_id"));
            //专业(1级)
            String parent_major_id = "";
            if (StringUtil.isNotEmpty(intent_major_id) && !intent_major_id.equals(" ")) {
                parent_major_id = parentMajorId(intent_major_id);
            }

            model.addAttribute("intent_province_id", "|" + intent_province_id + "|");
            model.addAttribute("is985", is985);
            model.addAttribute("is211", is211);
            model.addAttribute("isExcellent", isExcellent);
            //model.addAttribute("isIndependence", isIndependence);
            model.addAttribute("isFirstRate", isFirstRate);
            model.addAttribute("isKeyl", isKeyl);
            model.addAttribute("intent_univ_type", intent_univ_type);
            model.addAttribute("intent_major_id", intent_major_id);
            model.addAttribute("parent_major_id", parent_major_id);
            model.addAttribute("advanced_univ_num", advanced_univ_num1);
            model.addAttribute("stable_univ_num", stable_univ_num1);
            model.addAttribute("backward_univ_num", backward_univ_num1);
            model.addAttribute("new_intent_univ", new_intent_univ);
            model.addAttribute("new_avoid_univ", new_avoid_univ);
        } else {
            String sql = "insert into t_cee_applications_require (stu_user_id,application_id,batch_id,advanced_univ_num,stable_univ_num,backward_univ_num) values ('" + userId + "','" + app + "','" + batch + "'," + cNum + "," + wNum + "," + bNum + ")";
            commonSvc.executeSql(sql);
            model.addAttribute("advanced_univ_num", cNum);
            model.addAttribute("stable_univ_num", wNum);
            model.addAttribute("backward_univ_num", bNum);
        }
        if (StringUtils.isEmpty(yuanxiao) || "null".equals(yuanxiao) || yuanxiao == null) {
            yuanxiao = "";
        }

        int pageS = pageSize == null ? CookieUtils.getPageSize(request) : pageSize;
        final String totalCountQuery = "CALL getAutoZhiYuanSolution('" + applicationId + "'," + batchId + ",'" + yuanxiao + "',1,-1)";
        List<Map<String, Object>> totalCountlist = find_sql_toMap(totalCountQuery, null);
//        final String query = "CALL getAutoZhiYuanSolution(" + app + "," + batch + ",'" + yuanxiao + "')";
        final String query = "CALL getAutoZhiYuanSolution('" + applicationId + "'," + batchId + ",'" + yuanxiao + "'," + cpn(pageNo) + ",20)";
        List<Map<String, Object>> list = find_sql_toMap(query, null);
        model.addAttribute("list", list);
        // 总件数
        long totalCount = totalCountlist.size();
        // 翻页jump
        Pagination pagination = new Pagination(cpn(pageNo), pageS, (int) totalCount, list);

        model.addAttribute("pagination", pagination);
        model.addAttribute("pageNo", cpn(pageNo));
        model.addAttribute("pageSize", pageS);
        FrontUtils.frontData(request, model, site);

//        if (list.size() == 0) {
//            model.addAttribute("year1", "");
//            model.addAttribute("year2", "");
//            model.addAttribute("year3", "");
//        } else {
//            //最大的年份
//            String year1 = String.valueOf(list.get(0).get("enrolled_year_1"));
//            model.addAttribute("year1", year1);
//            //中间年份
//            String year2 = String.valueOf(list.get(0).get("enrolled_year_2"));
//            model.addAttribute("year2", year2);
//            //最小的年份
//            String year3 = String.valueOf(list.get(0).get("enrolled_year_3"));
//            model.addAttribute("year3", year3);
//        }

        Integer planYear = commonSvc.singleResult("SELECT dataPlanYear FROM TBaseProvince WHERE province_id =" + tCeeApplications.getProvinceId() + " ");

        Integer enrollYear = commonSvc.singleResult("SELECT dataEnrollYear FROM TBaseProvince WHERE province_id =" + tCeeApplications.getProvinceId() + " ");

        Integer year1 = enrollYear;
        Integer year2 = enrollYear - 1;
        Integer year3 = enrollYear - 2;
        Integer year4 = enrollYear - 3;

        model.addAttribute("year1", year1);
        model.addAttribute("year2", year2);
        model.addAttribute("year3", year3);
        model.addAttribute("year4", year4);

        model.addAttribute("planYear", planYear);
        if (tbType == 2) {
            return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "volunteerFiltrate_area");
        } else if (tbType == 3) {
            return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "volunteerFiltrate_univ");
        } else if (tbType == 4) {
            return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "volunteerFiltrate_major");
        } else if (tbType == 5) {
            return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "volunteerFiltrate_cwb");
        } else {
            return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "volunteerFiltrate");
        }
       /* String path = FrontUtils.getTplPath(site.getSolutionPath(), "WD", "volunteerFiltrate_bak") ;
        String path1 = ""+path+"#intentionList_2";
        return path1;*/


//        if (skip != null && skip == 3) {
//            return "redirect:dqsx.jspx?applicationId=" + app + "&batchId=" + batch + "#intentionList_3";
//        }
//        if (skip != null && skip == 2) {
//            return "redirect:dqsx.jspx?applicationId=" + app + "&batchId=" + batch + "#intentionList_2";
//        }
//        if (skip != null && skip == 4) {
//            return "redirect:dqsx.jspx?applicationId=" + app + "&batchId=" + batch + "#intentionList_4";
//        }
//        if (skip != null && skip == 5) {
//            return "redirect:dqsx.jspx?applicationId=" + app + "&batchId=" + batch + "#intentionList_5";
//        }
//        else {
//               return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "volunteerFiltrate_bak");
////            return "redirect:dqsx.jspx?applicationId=" + app + "&batchId=" + batch + "#intentionList_2";
//        }

    }

    /**
     * 获取专业一级
     *
     * @param id
     * @return
     */
    private String parentMajorId(String id) {
        String major_first_id = id.replace("|", ",");
        String major_first_sql = "SELECT * FROM `t_data_major` WHERE major_id IN ( SELECT parent_major_id FROM t_data_major WHERE major_id IN (" + major_first_id + "))";
        List<Map<String, Object>> major_firstList = commonSvc.findForJdbc(major_first_sql);

        List<String> parent_major_idList = new ArrayList<>();
        for (int i = 0; i < major_firstList.size(); i++) {
            String majorFirstId = String.valueOf(major_firstList.get(i).get("parent_major_id"));
            parent_major_idList.add(majorFirstId);
        }
        String parent_major_id = StringUtils.join(parent_major_idList.toArray(), "|");
        return parent_major_id;
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> find_sql(String queryString, Object[] params) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(queryString);
//        if (null != params) {
//            for (int i = 0; i < params.length; i++) {
//                query.setParameter(i, params[i]);
//            }
//        }
        List<Map<String, Object>> list = query.list();
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> find_sql_toMap(String queryString, Object[] params) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(queryString);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//        if (null != params) {
//            for (int i = 0; i < params.length; i++) {
//                query.setParameter(i, params[i]);
//            }
//        }
        List<java.util.Map<String, Object>> list = query.list();
        return list;
    }

    /**
     * 联动-一级专业选择二级专业
     *
     * @param response
     * @param major_id
     * @throws IOException
     */
    @RequestMapping(value = "/queryMajorSecond.jspx")
    public void majorSecond(HttpServletResponse response, String major_id) throws IOException {
        String sql = "SELECT * FROM `t_data_major` WHERE parent_major_id = '" + major_id + "'";
        List<Map<String, Object>> list = commonSvc.findForJdbc(sql);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(list));
    }

    /**
     * 已选变为不选
     *
     * @param request
     * @param applicationId
     * @param batchId
     * @param avoid_univId
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/changeChoose.jspx")
    public void changeChoose(HttpServletRequest request, String applicationId, String batchId, String
            avoid_univId, HttpServletResponse response) throws IOException {
        String sql = "SELECT * FROM `t_cee_applications_require` WHERE application_id='" + applicationId + "' AND batch_id ='" + batchId + "'";
        List<Map<String, Object>> list = commonSvc.findForJdbc(sql);
        String avoid_univ = String.valueOf(list.get(0).get("avoid_univ"));
        if (avoid_univ.contains(avoid_univId)) {
            String new_avoid_univ1 = "|" + avoid_univId;
            String new_avoid_univ2 = avoid_univId + "|";
            if (avoid_univ.contains(new_avoid_univ1)) {
                avoid_univ = avoid_univ.replace(new_avoid_univ1, "");
            } else if (avoid_univ.contains(new_avoid_univ2)) {
                avoid_univ = avoid_univ.replace(new_avoid_univ2, "");
            } else {
                avoid_univ = avoid_univ.replace(avoid_univId, "");
            }
            String intentSql = "UPDATE t_cee_applications_require SET avoid_univ = '" + avoid_univ + "'  WHERE application_id='" + applicationId + "' AND batch_id ='" + batchId + "' ";
            int num = commonSvc.executeSql(intentSql);
            Map<String, Object> map = new HashMap<>();
            if (num > 0) {
                map.put("msg", "取消成功！！");
            } else {
                map.put("msg", "不存在，操作无效！！");
            }
            String json = JSON.toJSONString(map);
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json;charset=UTF-8");
            response.getWriter().write(json);


        }


    }

    /**
     * 非意向保存
     *
     * @param request
     * @param applicationId 志愿Id
     * @param batchId       批次Id
     * @param avoid_univId  非意向Id
     */
    @RequestMapping(value = "intentOrAvoid.jspx")
    public void saveOrUpdate(HttpServletRequest request, String applicationId, String batchId, String
            avoid_univId, HttpServletResponse response) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        //用户id
        int userId = user.getId();
        String sql = "SELECT * FROM `t_cee_applications_require` WHERE application_id='" + applicationId + "' AND batch_id ='" + batchId + "'";
        List<Map<String, Object>> list = commonSvc.findForJdbc(sql);
        boolean type = false;
        //点击非意向
        if (StringUtil.isNotEmpty(avoid_univId)) {
            String avoid_univ = String.valueOf(list.get(0).get("avoid_univ"));
            if (StringUtil.isNotEmpty(avoid_univ) && !"0".equals(avoid_univ) && !"null".equals(avoid_univ)) {
                if (!avoid_univ.contains(avoid_univId)) {
                    avoid_univ += "|" + avoid_univId;
                    type = true;
                } else {
                    String new_avoid_univ1 = "|" + avoid_univId;
                    String new_avoid_univ2 = avoid_univId + "|";
                    if (avoid_univ.contains(new_avoid_univ1)) {
                        avoid_univ = avoid_univ.replace(new_avoid_univ1, "");
                    } else if (avoid_univ.contains(new_avoid_univ2)) {
                        avoid_univ = avoid_univ.replace(new_avoid_univ2, "");
                    } else {
                        avoid_univ = avoid_univ.replace(avoid_univId, "");
                    }
                    String intentSql = "UPDATE t_cee_applications_require SET avoid_univ = '" + avoid_univ + "'  WHERE application_id='" + applicationId + "' AND batch_id ='" + batchId + "' ";
                    int num = commonSvc.executeSql(intentSql);
                    Map<String, Object> map = new HashMap<>();
                    if (num > 0) {
                        map.put("msg", "取消成功！！");
                    } else {
                        map.put("msg", "不存在，操作无效！！");
                    }
                    String json = JSON.toJSONString(map);
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("text/json;charset=UTF-8");
                    response.getWriter().write(json);
                }
            } else {
                avoid_univ = avoid_univId;
                type = true;
            }

            if (type) {

                String intent_univ = String.valueOf(list.get(0).get("intent_univ"));
                if (StringUtil.isNotEmpty(intent_univ) && !"null".equals(intent_univ) && !"0".equals(intent_univ)) {
                    String new_avoid_univId = "|" + avoid_univId;
                    String new_avoid_univId2 = avoid_univId + "|";
                    if (intent_univ.contains(avoid_univId)) {
                        if (intent_univ.contains(new_avoid_univId)) {
                            intent_univ = intent_univ.replace(new_avoid_univId, "");
                        } else if (intent_univ.contains(new_avoid_univId2)) {
                            intent_univ = intent_univ.replace(new_avoid_univId2, "");
                        } else {
                            intent_univ = intent_univ.replace(avoid_univId, "0");
                        }
                    }
                } else {
                    intent_univ = "0";
                }
                //剔除专业
                String intent_major = String.valueOf(list.get(0).get("intent_major"));
                if (StringUtil.isNotEmpty(avoid_univId)) {

                    //查询避让学校的所有专业
                    String univSql = "SELECT * FROM `t_cee_enroll_major_list` WHERE plan_or_history = 1 AND data_type = 1 and  univ_list_id = '" + avoid_univId + "';";
                    List<Map<String, Object>> univList = commonSvc.findForJdbc(univSql);
                    for (int i = 0; i < univList.size(); i++) {
                        if (StringUtil.isNotEmpty(intent_major) && intent_major != null && !"null".equals(intent_major)) {
                            if (intent_major.contains(univList.get(i).get("id").toString())) {
                                String new1 = "|" + univList.get(i).get("id");
                                String new2 = univList.get(i).get("id") + "|";
                                if (intent_major.contains(new1)) {
                                    intent_major = intent_major.replace(new1, "");
                                } else if (intent_major.contains(new2)) {
                                    intent_major = intent_major.replace(new2, "");
                                } else {
                                    intent_major = intent_major.replace(univList.get(i).get("id").toString(), "0");
                                }
                            }

                        } else {
                            intent_major = "0";
                        }
                    }


                }


                String intentSql = "UPDATE t_cee_applications_require SET intent_univ = '" + intent_univ + "' ,avoid_univ = '" + avoid_univ + "',intent_major='" + intent_major + "'  WHERE application_id='" + applicationId + "' AND batch_id ='" + batchId + "' ";
                int num = commonSvc.executeSql(intentSql);
                Map<String, Object> map = new HashMap<>();
                if (num > 0) {
                    map.put("msg", "保存成功！！");
                } else {
                    map.put("msg", "已经添加过，不可重复添加！！");
                }
                String json = JSON.toJSONString(map);
                response.setCharacterEncoding("utf-8");
                response.setContentType("text/json;charset=UTF-8");
                response.getWriter().write(json);
            }
        }
    }

    @RequestMapping(value = "/jump.jspx")
    public void jump(String univId, HttpServletResponse response, String applicationId, String batchId) throws
            IOException {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("success", true);
        returnMap.put("msg", "majorList.jspx?univId=" + univId + "&applicationId=" + applicationId + "&batchId=" + batchId);
        String json = JSON.toJSONString(returnMap);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json);
    }

    @RequestMapping(value = "/jump2.jspx")
    public void jump1(HttpServletResponse response, String majorId, String applicationId, String batchId, String
            intent_univId) throws IOException {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("success", true);
        returnMap.put("msg", "${base}/fastZytb/saveOrUpdateIntentMajor.jspx?applicationId=" + applicationId + "&batchId=" + batchId + "&intent_univId=" + intent_univId + "&majorId=" + majorId);
        String json = JSON.toJSONString(returnMap);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json);
    }

    /**
     * 点击意向弹出专业选项
     *
     * @param request
     * @param model
     * @param univId
     * @return
     */
    @RequestMapping(value = "/majorList.jspx")
    public String majorList(HttpServletRequest request, ModelMap model, String univId, String applicationId, String
            batchId) {
        CmsUser user = CmsUtils.getUser(request);
        CmsSite site = CmsUtils.getSite(request);

        //查询t_base_province表当前省份有数据的年份
        TBaseProvince tBaseProvince = commonSvc.findUniqueByProperty(TBaseProvince.class, "provinceId", Integer.valueOf(user.getAttr().get("province_id")));
        int year = tBaseProvince.getDataEnrollYear();
        int planYear = tBaseProvince.getDataPlanYear();
        String univSql = "SELECT * FROM `t_cee_enroll_major_list` WHERE plan_or_history = 1 AND data_type = 1 and  univ_list_id = '" + univId + "' and year = " + planYear + ";";
        List<Map<String, Object>> univList = commonSvc.findForJdbc(univSql);

        String univMSql = "select * from t_cee_enroll_major_list where 1=1 and year =" + planYear + " and plan_or_history=1 and data_type=0 and univ_list_id=" + univId;
        Map univM = commonSvc.findOneForJdbc(univMSql);

        TCeeEnrollUnivList univDetail = commonSvc.get(TCeeEnrollUnivList.class, Integer.parseInt(univId));
        model.addAttribute("univM", univM);
        model.addAttribute("univDetail", univDetail);
        model.addAttribute("year", year);
        model.addAttribute("year1", year - 1);
        model.addAttribute("year2", year - 2);

        String maxUnivNumSql1 = "SELECT a.* FROM t_cee_batch a , t_cee_applications b WHERE b.id = '" + applicationId + "' AND a.year = b.year AND a.province_id = b.province_id and a.major_type_id = b.major_type_id AND a.batch_id = " + batchId + " ";
        List<Map<String, Object>> max1 = commonSvc.findForJdbc(maxUnivNumSql1);

        model.addAttribute("max_univ_num1", max1.get(0).get("univ_num"));
        model.addAttribute("max_major_num", max1.get(0).get("major_num"));

        //前一年数据
        List<Map<String, Object>> yearAgo1 = diffYear(year, univId);
        //前两年数据
        List<Map<String, Object>> yearAgo2 = diffYear(year - 1, univId);
        //前三年数据
        List<Map<String, Object>> yearAgo3 = diffYear(year - 2, univId);

        String conditionSql = "SELECT * FROM `t_cee_applications_require` WHERE application_id ='" + request.getParameter("applicationId") + "'  AND batch_id = " + request.getParameter("batchId") + "";
        List<Map<String, Object>> conditionList = commonSvc.findForJdbc(conditionSql);

//        String intentMajorId = (String) conditionList.get(0).get("intent_major");

        if (conditionList.size() != 0) {
            if (StringUtil.isNotEmpty(conditionList.get(0).get("intent_major"))) {
                String[] intentMajorId = ((String) conditionList.get(0).get("intent_major")).split("\\|");
                model.addAttribute("intentMajorId", intentMajorId);
            }
        }

        model.addAttribute("conditionList", conditionList);
        model.addAttribute("year", year);
        model.addAttribute("univList", univList);
        model.addAttribute("yearAgo1", yearAgo1);
        model.addAttribute("yearAgo2", yearAgo2);
        model.addAttribute("yearAgo3", yearAgo3);
        model.addAttribute("applicationId", applicationId);
        model.addAttribute("batchId", batchId);
        model.addAttribute("univId", univId);
        return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "jumpRemote");
    }

    public List<Map<String, Object>> diffYear(int year, String univId) {
        String sql = "SELECT * FROM `t_cee_enroll_major_list` WHERE plan_or_history = 2 AND data_type = 1 and year = '" + year + "' and  univ_list_id = '" + univId + "';";
        List<Map<String, Object>> list = commonSvc.findForJdbc(sql);
        return list;
    }

    /**
     * 保存意向学校和意向专业
     *
     * @param request
     * @param intent_univId 院校id
     * @param majorId       选择的专业id 用"|"隔开
     * @param applicationId 志愿id
     * @param batchId       批次id
     */
    @RequestMapping(value = "/saveOrUpdateIntentMajor.jspx")
    public void saveOrUpdateIntentMajor(HttpServletRequest request, HttpServletResponse response, String
            intent_univId, String majorId, String applicationId, String batchId) throws IOException {
        CmsUser user = CmsUtils.getUser(request);
        //获取require表中意向专业的值
        String reqSql = "SELECT * FROM `t_cee_applications_require` WHERE application_id = '" + applicationId + "' AND batch_id='" + batchId + "'";
        List<Map<String, Object>> reqList = commonSvc.findForJdbc(reqSql);
        //意向专业
        String intent_majorId = String.valueOf(reqList.get(0).get("intent_major"));
        //意向学校
        String intent_univ = String.valueOf(reqList.get(0).get("intent_univ"));
        //非意向学校
        String avoid_univ = String.valueOf(reqList.get(0).get("avoid_univ"));
        //当前学校的所有专业ID
        String univSql = "SELECT * FROM `t_cee_enroll_major_list` WHERE plan_or_history = 1 AND data_type = 1 and  univ_list_id = '" + intent_univId + "';";
        List<Map<String, Object>> univList = commonSvc.findForJdbc(univSql);
        //先清空当前学校所有的专业id
        if (StringUtil.isNotEmpty(intent_majorId)) {
            if (StringUtil.isNotEmpty(univList)) {
                for (int i = 0; i < univList.size(); i++) {
                    String univId1 = String.valueOf(univList.get(i).get("id"));
                    String univId2 = "|" + univId1;
                    String univId3 = univId1 + "|";

                    if (intent_majorId.contains(univId1)) {
                        if (intent_majorId.contains(univId2)) {
                            intent_majorId = intent_majorId.replace(univId2, "");
                        } else if (intent_majorId.contains(univId3)) {
                            intent_majorId = intent_majorId.replace(univId3, "");
                        } else {
                            intent_majorId = intent_majorId.replace(univId1, "");
                        }
                    }

                }
            }

        }


        //如果选择了专业修改意向专业和意向学校
        if (StringUtil.isNotEmpty(majorId)) {
            //意向专业
            String[] major_arrayId = majorId.split(",");
            if (StringUtil.isNotEmpty(intent_majorId) && !"0".equals(intent_majorId) && intent_majorId != null && !("null").equals(intent_majorId)) {
                for (int i = 0; i < major_arrayId.length; i++) {
                    intent_majorId += "|" + major_arrayId[i];
                }
            } else {
                if (major_arrayId.length == 1) {
                    intent_majorId = major_arrayId[0];
                } else {
                    intent_majorId = major_arrayId[0];
                    for (int i = 1; i < major_arrayId.length; i++) {
                        intent_majorId += "|" + major_arrayId[i];
                    }
                }
            }

            //意向学校
            if (StringUtil.isNotEmpty(intent_univId)) {
                if (StringUtil.isNotEmpty(intent_univ) && !"0".equals(intent_univ)) {
                    if (!intent_univ.contains(intent_univId)) {
                        intent_univ += "|" + intent_univId;
                    }
                } else {
                    intent_univ = intent_univId;
                }
                if (StringUtil.isNotEmpty(avoid_univ)) {
                    String new_intent_univId = "|" + intent_univId;
                    String new_intent_univId2 = intent_univId + "|";
                    if (avoid_univ.contains(intent_univId)) {
                        if (avoid_univ.contains(new_intent_univId)) {
                            avoid_univ = avoid_univ.replace(new_intent_univId, "");
                        } else if (avoid_univ.contains(new_intent_univId2)) {
                            avoid_univ = avoid_univ.replace(new_intent_univId2, "");
                        } else {
                            avoid_univ = avoid_univ.replace(intent_univId, "");
                        }
                    }
                }
            }
        }

        boolean result = false;
        Map<String, Object> map = new HashMap<>();
        TCeeApplications tCeeApplications = commonSvc.getEntity(TCeeApplications.class, Integer.valueOf(applicationId));
        //查询t_base_province表当前省份有数据的年份
        TBaseProvince tBaseProvince = commonSvc.findUniqueByProperty(TBaseProvince.class, "provinceId", Integer.valueOf(user.getAttr().get("province_id")));
        int year = tBaseProvince.getDataBatchYear();
        //获取院校，专业可以填报的数量
        String batchNumSql = "SELECT * FROM `t_cee_batch` WHERE YEAR = '" + year + "' AND province_Id = '" + tCeeApplications.getProvinceId() + "' AND major_type_id = '" + tCeeApplications.getMajorTypeId() + "' AND batch_id='" + batchId + "'";
        List<Map<String, Object>> batchNumList = commonSvc.findForJdbc(batchNumSql);
        //院校数量
        int univNum = Integer.parseInt(batchNumList.get(0).get("univ_num").toString());
        //专业数量
        int majorNum = Integer.parseInt(batchNumList.get(0).get("major_num").toString());
        //校验院校和专业数量
        if (StringUtil.isNotEmpty(intent_univ)) {
            int new_univNum = intent_univ.split("\\|").length;
            if (new_univNum <= univNum) {
                result = true;
            } else {
                result = false;
                int num = new_univNum - univNum;
                map.put("success", "false");
                map.put("msg", "院校数量超过限制，超出( " + num + " )个，不能选择新的学校！！");
            }
        }
        if (result) {
            if (StringUtil.isNotEmpty(majorId)) {
                int new_intentMajorNum = majorId.split("\\|").length;
                if (new_intentMajorNum <= majorNum) {
                    result = true;
                } else {
                    result = false;
                    int num = new_intentMajorNum - majorNum;
                    map.put("success", "false");
                    map.put("msg", "专业数量超过限制，超出( " + num + " )个，请减少专业数量！！");
                }
            }
        }

        if (result) {
            String intent_majorSql = "UPDATE t_cee_applications_require SET intent_major = '" + intent_majorId + "' , intent_univ = '" + intent_univ + "' ,avoid_univ = '" + avoid_univ + "'  WHERE application_id='" + applicationId + "' AND batch_id ='" + batchId + "' ";
            commonSvc.executeSql(intent_majorSql);
            map.put("success", "success");
            map.put("msg", "操作成功！！");
        }
        String json = JSON.toJSONString(map);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(json);
    }

    @RequestMapping(value = "/chooseZhiyuanSolution.jspx")
    public void chooseZhiyuanSolution(HttpServletRequest request, HttpServletResponse response, ModelMap
            model, String application_idd) throws Exception {

        String reqSql = "SELECT * FROM t_cee_applications_require\n" +
                "WHERE application_id = '" + application_idd + "'";
        List<Map<String, Object>> reqList = commonSvc.findForJdbc(reqSql);

        String isSuccess = "false";
        String msg = "";
        if (StringUtil.isNotEmpty(reqList)) {
            for (int i = 0; i < reqList.size(); i++) {
                final String query = "CALL getAutoChooseZhiyuanSolution (" + application_idd + ", " + reqList.get(i).get("batch_id") + ")";
                executeVoidProcedureSql(query, null);
                msg = "志愿方案已生成完毕，请返回志愿方案列表点击\"评估\"查看。";
                isSuccess = "true";
            }
        } else {
            msg = "请查看方案意向条件是否完整。";
        }

        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("isSuccess", isSuccess);
        String json = JSON.toJSONString(map);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(json);

    }

    public void executeVoidProcedureSql(final String queryString, final Object[] params) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.doWork(new Work() {
            @Override
            public void execute(Connection conn) throws SQLException {
                ResultSet rs = null;
                CallableStatement call = conn.prepareCall("{" + queryString + "}");
                if (null != params) {
                    for (int i = 0; i < params.length; i++) {
                        call.setObject(i + 1, params[i]);
                    }
                }
                rs = call.executeQuery();
                call.close();
                rs.close();
            }
        });
    }


    // Alltodo: 2019/11/22 智能志愿填报逻辑
    @RequestMapping("/getAutoZhiyuanSolution.jspx")
    public String getAutoZhiyuanSolution(HttpServletRequest request, ModelMap model, String applicationId, Integer batchId, String univName, Integer pageNo, Integer pageSize) {
        CmsSite site = CmsUtils.getSite(request);
//        applicationId = "1050039";
//        batchId = 2;
        TCeeApplications tCeeApplications = commonSvc.get(TCeeApplications.class, Integer.valueOf(applicationId));
        TCeeApplicationsRequire tCeeApplicationsRequire = smartZytbSvc.getApplicationRequire(applicationId, batchId);
        TCeeBatch tCeeBatch = smartZytbSvc.getBatch(tCeeApplications.getYear(), tCeeApplications.getProvinceId(), tCeeApplications.getMajorTypeId(), tCeeApplicationsRequire.getBatchId());

        Integer a = getAllTypeRank(tCeeBatch, tCeeApplications, 1);
        Integer b = getAllTypeRank(tCeeBatch, tCeeApplications, 2);
        Integer c = getAllTypeRank(tCeeBatch, tCeeApplications, 3);
        Integer d = getAllTypeRank(tCeeBatch, tCeeApplications, 4);
        Integer e = getAllTypeRank(tCeeBatch, tCeeApplications, 5);

        List<TCeeEnrollUnivListMiddle> tCeeEnrollUnivListMiddles = smartZytbSvc.getLastUniv(getAllTypeRank(tCeeBatch, tCeeApplications, 1), getAllTypeRank(tCeeBatch, tCeeApplications, 2), tCeeApplications, tCeeApplicationsRequire);
        List<TCeeEnrollUnivListMiddle> tCeeEnrollUnivListMiddleList = smartZytbSvc.getUnivPSK(tCeeEnrollUnivListMiddles, getAllTypeRank(tCeeBatch, tCeeApplications, 3), getAllTypeRank(tCeeBatch, tCeeApplications, 4), getAllTypeRank(tCeeBatch, tCeeApplications, 5));



        smartZytbSvc.setUnivListUser(tCeeEnrollUnivListMiddleList, tCeeApplications, tCeeApplicationsRequire);
        smartZytbSvc.setUnivListChooseUniv(tCeeApplications, tCeeApplicationsRequire, tCeeBatch);
        smartZytbSvc.setUnivListChooseMajor(tCeeApplications, tCeeApplicationsRequire, tCeeBatch);
        smartZytbSvc.doInsertApplicationDetail(tCeeApplications, tCeeApplicationsRequire, tCeeBatch);
        doSetOrderApplicationDetail(applicationId, batchId);
        zytbAct.findAndCreateDetail(applicationId, batchId, tCeeBatch, tCeeApplications, 0);
        FrontUtils.frontData(request, model, site);
//        重定向打开志愿表
        String url = "" + request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "" + request.getContextPath() + "/zytb/voluntary.jspx?applicationId=" + applicationId + "";
//        String url = "" + request.getScheme() + "://" + request.getServerName() + "/zytb/voluntary.jspx?applicationId=" + applicationId + "";


        return "redirect:" + url + "";
        /*
        applicationId = String.valueOf(1050039);
        batchId = 2;

        TCeeApplications tCeeApplications = commonSvc.get(TCeeApplications.class, applicationId);
//        文理科
        Integer majorTypeId = tCeeApplications.getMajorTypeId();
//        省份
        Integer provinceId = tCeeApplications.getProvinceId();
//        分数年份
        Integer scoreYear = tCeeApplications.getYear();
//        省份表
        TBaseProvince tBaseProvince = commonSvc.get(TBaseProvince.class, provinceId);
//        批次年份
        Integer batchYear = tBaseProvince.getDataBatchYear();
//        计划年份
        Integer planYear = tBaseProvince.getDataPlanYear();
//        批次
        TCeeBatch tCeeBatch = smartZytbSvc.getBatch(batchYear, provinceId, majorTypeId, batchId);
//        需求表
        TCeeApplicationsRequire tCeeApplicationsRequire = smartZytbSvc.getApplicationRequire(applicationId, batchId);
//        方案分数
        Integer userScore = tCeeApplications.getScore();
//        高考总分一
        Integer firstScoreValue = tBaseProvince.getFirstScoreValue();
//        高考分数二
        Integer secondScoreValue = tBaseProvince.getSecondScoreValue();
//        冲分数
        Integer pValue = tBaseProvince.getGradP();
//        稳分数
        Integer sValue = tBaseProvince.getGradS();
//        保分数
        Integer kValue = tBaseProvince.getGradK();
//        下限值
        Integer lowerValue = tBaseProvince.getLowerValue();
//        上限值
        Integer upperValue = tBaseProvince.getUpperValue();
////        选科等级
//        String subjectLevel = tCeeApplications.getSubjectsLevel();
//        if (StringUtils.isBlank(subjectLevel) || subjectLevel.equals('0') || subjectLevel == "") {
//            subjectLevel = "999999999";
//        }
////        选科等级值
//        Integer subjectValue = 0;
//        if (provinceId == 17) {
//            if (com.utils.StringUtils.isNotBlank(subjectLevel) && !subjectLevel.equals("999999999")) {
//                subjectLevel = subjectLevel.replace(",", "");
//                subjectValue = subjectLevel.indexOf(1) + subjectLevel.lastIndexOf(1);
//            }
//        }
//
//        if (StringUtils.isBlank(univName) || univName.equals('0') || univName == "") {
//            univName = "999999999";
//        }
////        意向专业大类
//        String intentMajorId = tCeeApplicationsRequire.getIntentMajorId();
//        if (StringUtils.isBlank(intentMajorId) || intentMajorId.equals('0') || intentMajorId == "") {
//            intentMajorId = "999999999";
//        }
//
////        意向专业
//        String intentMajor = tCeeApplicationsRequire.getIntentMajor();
//        if (StringUtils.isBlank(intentMajor) || intentMajor.equals('0') || intentMajor == "") {
//            intentMajor = "999999999";
//        }
//
////        意向院校类型
//        String intentUnivType = tCeeApplicationsRequire.getIntentUnivType();
//        if (StringUtils.isBlank(intentUnivType) || intentUnivType.equals('0') || intentUnivType == "") {
//            intentUnivType = "999999999";
//        }
//
////        意向省份
//        String intentProvinceId = tCeeApplicationsRequire.getIntentProvinceId();
//        if (StringUtils.isBlank(intentProvinceId) || intentProvinceId.equals('0') || intentProvinceId == "") {
//            intentProvinceId = "999999999";
//        }
//
////        意向院校
//        String intentUniv = tCeeApplicationsRequire.getIntentUniv();
//        if (StringUtils.isBlank(intentUniv) || intentUniv.equals('0') || intentUniv == "") {
//            intentUniv = "999999999";
//        }
//
////        规避院校
//        String avoidUniv = tCeeApplicationsRequire.getAvoidUniv();
//        if (StringUtils.isBlank(avoidUniv) || avoidUniv.equals('0') || avoidUniv == "") {
//            avoidUniv = "999999999";
//        }


//        批次最高分
        Integer batchMaxScore = smartZytbSvc.getBatchMaxScore(planYear, provinceId, batchId, majorTypeId);

//        比较最高分
        Integer queryMaxScore = 0;
        if (batchMaxScore < userScore) {
            queryMaxScore = batchMaxScore;
        } else {
            queryMaxScore = userScore;
        }
//        上限排名
        Integer upperScoreRank = 0;
//        下限排名
        Integer lowerScoreRank = 0;
//        冲排名
        Integer gradeRankP = 0;
//        稳排名
        Integer gradeRankS = 0;
//        保排名
        Integer gradeRankK = 0;

        if (provinceId == 17) {
            if ((userScore + upperValue) <= firstScoreValue) {
                upperScoreRank = smartZytbSvc.getRank(majorTypeId, provinceId, scoreYear, queryMaxScore + upperValue);
            } else {
                upperScoreRank = 1;
            }
            lowerScoreRank = smartZytbSvc.getRank(majorTypeId, provinceId, scoreYear, queryMaxScore + lowerValue);
            gradeRankP = smartZytbSvc.getRank(majorTypeId, provinceId, scoreYear, userScore + pValue);
            gradeRankS = smartZytbSvc.getRank(majorTypeId, provinceId, scoreYear, userScore + sValue);
            gradeRankK = smartZytbSvc.getRank(majorTypeId, provinceId, scoreYear, userScore + kValue);
        } else {
            if ((userScore + upperValue) <= secondScoreValue) {
                upperScoreRank = smartZytbSvc.getRank(majorTypeId, provinceId, scoreYear, queryMaxScore + upperValue);
            } else {
                upperScoreRank = 1;
            }
            lowerScoreRank = smartZytbSvc.getRank(majorTypeId, provinceId, scoreYear, queryMaxScore + lowerValue);
            gradeRankP = smartZytbSvc.getRank(majorTypeId, provinceId, scoreYear, userScore + pValue);
            gradeRankS = smartZytbSvc.getRank(majorTypeId, provinceId, scoreYear, userScore + sValue);
            gradeRankK = smartZytbSvc.getRank(majorTypeId, provinceId, scoreYear, userScore + kValue);
        }

        List<TCeeEnrollUnivListMiddle> tCeeEnrollUnivListMiddles = smartZytbSvc.getLastUniv(upperScoreRank, lowerScoreRank);
        List<TCeeEnrollUnivListMiddle> tCeeEnrollUnivListMiddleList = smartZytbSvc.getUnivPSK(tCeeEnrollUnivListMiddles, gradeRankP, gradeRankS, gradeRankK);

*/
    }

    //    对志愿详情表重新排序
    public void doSetOrderApplicationDetail(String applicationId, Integer batchId) {

//        初始志愿详情表
        String tCeeApplicationsDetailsSql = "SELECT * FROM `t_cee_applications_detail`\n" +
                "WHERE application_id = '" + applicationId + "' AND batch_id = " + batchId + " ";

        List<TCeeApplicationsDetail> tCeeApplicationsDetails = commonSvc.findListbySql(tCeeApplicationsDetailsSql, TCeeApplicationsDetail.class);

//        根据分数和排名对存在数据进行排序查询
        String univListOrderSql = "SELECT * FROM `t_cee_enroll_univ_list`\n" +
                "WHERE (YEAR,province_id,major_type_id,batch_id,univ_id,univ_code,univ_name) IN \n" +
                "(\n" +
                "SELECT YEAR,province_id,major_type_id,batch_id,univ_id,univ_code,univ_name FROM `t_cee_applications_detail` \n" +
                "WHERE application_id = '" + applicationId + "' AND batch_id = " + batchId + " \n" +
                "AND univ_name IS NOT NULL \n" +
                "GROUP BY YEAR,province_id,major_type_id,batch_id,univ_code,univ_name \n" +
                ")\n" +
                "ORDER BY score_low_1 DESC , rank_score_low_1 ASC ,score_low_2 DESC , rank_score_low_2 ASC ,score_low_3 DESC , rank_score_low_3 ASC ,score_low_4 DESC , rank_score_low_4 ASC ";

        List<TCeeEnrollUnivList> tCeeEnrollUnivListsOrder = commonSvc.findListbySql(univListOrderSql, TCeeEnrollUnivList.class);

        for (int i = 0; i < tCeeEnrollUnivListsOrder.size(); i++) {

            for (TCeeApplicationsDetail tCeeApplicationsDetailToUpdate : tCeeApplicationsDetails) {

                int orderYear = tCeeEnrollUnivListsOrder.get(i).getYear();
                int orderProvinceId = tCeeEnrollUnivListsOrder.get(i).getProvinceId();
                int orderMajorTypeId = tCeeEnrollUnivListsOrder.get(i).getMajorTypeId();
                int orderBatchId = tCeeEnrollUnivListsOrder.get(i).getBatchId();
                String orderUnivCode = tCeeEnrollUnivListsOrder.get(i).getUnivCode();
//                Integer orderUnivId = tCeeEnrollUnivListsOrder.get(i).getUnivId();
                String orderUnivName = tCeeEnrollUnivListsOrder.get(i).getUnivName();

                int toUpdateYear = tCeeApplicationsDetailToUpdate.getYear();
                int toUpdateProvinceId = tCeeApplicationsDetailToUpdate.getProvinceId();
                int toUpdateMajorTypeId = tCeeApplicationsDetailToUpdate.getMajorTypeId();
                int toUpdateBatchId = tCeeApplicationsDetailToUpdate.getBatchId();
                String toUpdateUnivCode = tCeeApplicationsDetailToUpdate.getUnivCode();
//                Integer toUpdateUnivId = Integer.valueOf(tCeeApplicationsDetailToUpdate.getUnivId());
                String toUpdateUnivName = tCeeApplicationsDetailToUpdate.getUnivName();

                if (orderYear == toUpdateYear) {
                    if (orderProvinceId == toUpdateProvinceId) {
                        if (orderMajorTypeId == toUpdateMajorTypeId) {
                            if (orderBatchId == toUpdateBatchId) {
                                if (orderUnivCode.equals(toUpdateUnivCode)) {
//                                    if (orderUnivId.equals(toUpdateUnivId)) {
                                    if (orderUnivName.equals(toUpdateUnivName)) {
                                        tCeeApplicationsDetailToUpdate.setUnivOrder(i + 1);
                                        commonSvc.updateEntitie(tCeeApplicationsDetailToUpdate);
                                    }
//                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        String deleteNullDetailSql = " DELETE FROM t_cee_applications_detail\n" +
                "WHERE application_id = " + applicationId + " AND batch_id = " + batchId + " AND is_formal = 0 AND univ_name IS NULL";

        commonSvc.executeSql(deleteNullDetailSql);
    }

    //    算出院校排名上下限及冲稳保排名
    public Integer getAllTypeRank(TCeeBatch tCeeBatch, TCeeApplications tCeeApplications, Integer type) {

        TBaseProvince tBaseProvince = commonSvc.get(TBaseProvince.class, tCeeApplications.getProvinceId());
        Integer scoreP = tBaseProvince.getGradP();
        Integer scoreS = tBaseProvince.getGradS();
        Integer scoreK = tBaseProvince.getGradK();
        Integer scoreUpper = tBaseProvince.getUpperValue();
        Integer scoreLower = tBaseProvince.getLowerValue();
        Integer scoreSum1 = tBaseProvince.getFirstScoreValue();
        Integer scoreSum2 = tBaseProvince.getSecondScoreValue();

        String batchMaxScoreSql = "SELECT MAX(score_low_1) AS batchmaxscore\n" +
                "               FROM t_cee_enroll_univ_list \n" +
                "               WHERE YEAR = " + tCeeApplications.getYear() + " AND province_id =" + tCeeApplications.getProvinceId() + " AND batch_id = " + tCeeBatch.getBatchId() + " AND  major_type_id = " + tCeeApplications.getMajorTypeId() + " ";
        Map batchMaxScoreMap = commonSvc.findOneForJdbc(batchMaxScoreSql, null);
        Integer batchMaxScore = Integer.parseInt(batchMaxScoreMap.get("batchmaxscore").toString());
        Integer userScore = tCeeApplications.getScore();
        Integer queryScore = batchMaxScore < userScore ? batchMaxScore : userScore;

        Integer rankP;
        Integer rankS;
        Integer rankK;
        Integer rankUpper;
        Integer rankLower;

        if (tCeeApplications.getProvinceId() == 17) {

            if (userScore + scoreUpper <= scoreSum1) {
                rankP = getRank(tCeeApplications.getMajorTypeId(), tCeeApplications.getProvinceId(), tBaseProvince, userScore, scoreP);
                rankS = getRank(tCeeApplications.getMajorTypeId(), tCeeApplications.getProvinceId(), tBaseProvince, userScore, scoreS);
                rankK = getRank(tCeeApplications.getMajorTypeId(), tCeeApplications.getProvinceId(), tBaseProvince, userScore, scoreK);
                rankUpper = getRank(tCeeApplications.getMajorTypeId(), tCeeApplications.getProvinceId(), tBaseProvince, queryScore, scoreUpper);
                rankLower = getRank(tCeeApplications.getMajorTypeId(), tCeeApplications.getProvinceId(), tBaseProvince, queryScore, scoreLower);
            } else {
                rankP = getRank(tCeeApplications.getMajorTypeId(), tCeeApplications.getProvinceId(), tBaseProvince, userScore, scoreP);
                rankS = getRank(tCeeApplications.getMajorTypeId(), tCeeApplications.getProvinceId(), tBaseProvince, userScore, scoreS);
                rankK = getRank(tCeeApplications.getMajorTypeId(), tCeeApplications.getProvinceId(), tBaseProvince, userScore, scoreK);
                rankUpper = 1;
                rankLower = getRank(tCeeApplications.getMajorTypeId(), tCeeApplications.getProvinceId(), tBaseProvince, queryScore, scoreLower);
            }
        } else {
            if (userScore + scoreUpper <= scoreSum2) {
                rankP = getRank(tCeeApplications.getMajorTypeId(), tCeeApplications.getProvinceId(), tBaseProvince, userScore, scoreP);
                rankS = getRank(tCeeApplications.getMajorTypeId(), tCeeApplications.getProvinceId(), tBaseProvince, userScore, scoreS);
                rankK = getRank(tCeeApplications.getMajorTypeId(), tCeeApplications.getProvinceId(), tBaseProvince, userScore, scoreK);
                rankUpper = getRank(tCeeApplications.getMajorTypeId(), tCeeApplications.getProvinceId(), tBaseProvince, queryScore, scoreUpper);
                rankLower = getRank(tCeeApplications.getMajorTypeId(), tCeeApplications.getProvinceId(), tBaseProvince, queryScore, scoreLower);
            } else {
                rankP = getRank(tCeeApplications.getMajorTypeId(), tCeeApplications.getProvinceId(), tBaseProvince, userScore, scoreP);
                rankS = getRank(tCeeApplications.getMajorTypeId(), tCeeApplications.getProvinceId(), tBaseProvince, userScore, scoreS);
                rankK = getRank(tCeeApplications.getMajorTypeId(), tCeeApplications.getProvinceId(), tBaseProvince, userScore, scoreK);
                rankUpper = 1;
                rankLower = getRank(tCeeApplications.getMajorTypeId(), tCeeApplications.getProvinceId(), tBaseProvince, queryScore, scoreLower);
            }


        }

        switch (type) {
            case 1:
                return rankUpper;
            case 2:
                return rankLower;
            case 3:
                return rankP;
            case 4:
                return rankS;
            case 5:
                return rankK;
            default:
                return rankUpper;
        }
    }

    //    获取排名
    public Integer getRank(Integer majorTypeId, Integer provinceId, TBaseProvince tBaseProvince, Integer score1, Integer score2) {



        String getRankSql = "SELECT rank FROM t_cee_score_rank \n" +
                "WHERE major_type_id = " + majorTypeId + " AND  province_id = " + provinceId + " AND YEAR = " + tBaseProvince.getDataScoreYear() + " AND score = " + score1 + " + " + score2 + " ";
        Map getRankMap = commonSvc.findOneForJdbc(getRankSql, null);
        Integer rank = Integer.valueOf(getRankMap.get("rank").toString());
        return rank;
    }
}
