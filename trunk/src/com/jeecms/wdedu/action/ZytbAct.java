package com.jeecms.wdedu.action;

import com.alibaba.fastjson.JSON;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsGroup;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsGroupMng;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.*;
import com.jeecms.wdedu.service.CommonSvc;
import com.jeecms.wdedu.service.SmartZytbSvc;
import com.jeecms.wdedu.service.ZytbSvc;
import com.sun.deploy.net.URLEncoder;
import com.utils.BrowserUtils;
import com.utils.StringUtil;
import com.utils.excel.ExcelExportUtil;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import org.hibernate.SessionFactory;

import static com.jeecms.common.page.SimplePage.cpn;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @Description: 志愿填报
 * @date 2018/10/18
 */
@Controller
@RequestMapping("/zytb")
public class ZytbAct {

    private static final Logger LOG = LoggerFactory.getLogger(ZytbAct.class);
    public final static String TPLDIR_ZYTB = "wdedu/zytb";
    //    public final static String TPLDIR_ZYTB = "zytb";
//    public final static String APPLICATIONS_RESULT = "project";
    public final static String APPLICATIONS_RESULT = "tianzhiyuan";
    public final static String APPLICATIONS_RESULT_FAST = "tianzhiyuan_fast";
    //    public final static String APPLICATIONS_DETAIL_RESULT = "voluntary";
    public final static String APPLICATIONS_DETAIL_RESULT = "monitianbaobiao";
    public static final SimpleDateFormat yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private ZytbSvc zytbSvc;

    @Autowired
    private ZytbAct zytbAct;
    @Autowired
    private CommonSvc commonSvc;
    @Autowired
    private CmsGroupMng cmsGroupMng;
    @Autowired
    private SmartZytbSvc smartZytbSvc;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CmsUserMng cmsUserMng;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 查询志愿方案列表
     *
     * @param applicationName 方案名称
     * @param pageNo          页码
     * @param request         request
     * @param model           model
     * @return string
     */
    @RequestMapping("/project.jspx")
    public String getProject(String applicationName, String applicationId, Integer pageNo, HttpServletRequest request, ModelMap model, Integer batchId, String univProvince,
                             String univSchoolType, String yx_cwb, Integer yx_choose, String univ_is985, String univ_is211, String univ_isDefence, String univ_isExcellent,
                             String univ_isIndependence, String univ_isFirstRateUniv, String univ_isFirstRateMajor, String chooseType, Integer setPageNum, String schoolOrMajorName) {

        Map requestMap = new HashMap();
        requestMap.put("batchId", batchId);
        requestMap.put("univProvince", univProvince);
        requestMap.put("univ_is985", univ_is985);
        requestMap.put("univ_is211", univ_is211);
        requestMap.put("univ_isDefence", univ_isDefence);
        requestMap.put("univ_isExcellent", univ_isExcellent);
        requestMap.put("univ_isIndependence", univ_isIndependence);
        requestMap.put("univ_isFirstRateUniv", univ_isFirstRateUniv);
        requestMap.put("univ_isFirstRateMajor", univ_isFirstRateMajor);
        requestMap.put("univSchoolType", univSchoolType);
        requestMap.put("yx_cwb", yx_cwb);
        requestMap.put("yx_choose", yx_choose);
        requestMap.put("chooseType", chooseType);
        requestMap.put("schoolOrMajorName", schoolOrMajorName);
        model.addAttribute("requestMap", requestMap);
        model.addAttribute("schoolOrMajorName", schoolOrMajorName);
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        //判断用户是否有省份选择和文理科选择的权限，有的话能动态选择省份
        Set<CmsGroup> groups = user.getGroups();
        Iterator<CmsGroup> groupIt = groups.iterator();
        Iterator<CmsGroup> groupIt2 = groups.iterator();
        String yesOrNo = "false";
        String haveTianZhiyuan = "false";
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
        model.addAttribute("haveTianZhiyuan", haveTianZhiyuan);
        while (groupIt2.hasNext()) {
            Set<String> groupss2 = groupIt2.next().getPerms();
            Iterator<String> groupssIt2 = groupss2.iterator();

            while (groupssIt2.hasNext()) {
                if ("TianZhiYuan:*".equals(groupssIt2.next())) {
                    haveTianZhiyuan = "true";
                    model.addAttribute("haveTianZhiyuan", haveTianZhiyuan);
                    break;
                }
            }
        }

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
        String proName = "";
        if (StringUtils.isNotBlank(univProvince)) {
            String[] univProvinces = univProvince.split(",");

            //回填数据
            for (String proId : univProvinces) {
                for (TBaseProvince tBaseProvince : provinceList) {
                    if (proId.equals(tBaseProvince.getProvinceId())) {
                        proName += tBaseProvince.getProvinceName() + ",";
                        break;
                    }
                }
            }
        }
        model.addAttribute("proName", proName);

        // 学校类型
        List<TDataUniversityType> universityType = commonSvc.loadAll(TDataUniversityType.class);

        //查询所有专业
        //一级专业
        List<Map<String, Object>> oneList = this.queryMajorByParentId("'1','2'");
        //二级专业
        List<Map<String, Object>> twoList = this.queryMajorByParentId(subString(oneList));
        //三级专业
        List<Map<String, Object>> threeLists = this.queryMajorByParentId(subString(twoList));
        List<Map<String, Object>> threeList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map2 : threeLists) {
            map2.put("msid", "m" + map2.get("major_Id").toString().subSequence(0, 2));
            threeList.add(map2);
        }
        model.addAttribute("oneList", oneList);
        model.addAttribute("twoList", twoList);
        model.addAttribute("threeList", threeList);


        List<TCeeApplications> tCeeApplicationsListORNOT = commonSvc.findByProperty(TCeeApplications.class, "userId", user.getId());

//        若无方案则跳转至新建方案页面
        if (tCeeApplicationsListORNOT != null && tCeeApplicationsListORNOT.size() != 0) {
//        选择方案,未选择则查询用户最新的方案ID
            if (applicationId == null) {
                String sql = "SELECT MAX(id) as id FROM t_cee_applications WHERE user_id='" + user.getId() + "'";
                Map map = commonSvc.findOneForJdbc(sql);
                if (map.get("id") != null) {
                    //方案ID
                    applicationId = String.valueOf(map.get("id"));

                }
            }

//        applicationId = "1050040";
            TCeeApplications tCeeApplications = commonSvc.get(TCeeApplications.class, Integer.valueOf(applicationId));

            if (tCeeApplications.getSubjectsLevel() != null) {
                requestMap.put("subjectsLevel", tCeeApplications.getSubjectsLevel());
            }
            model.addAttribute("tCeeApplications", tCeeApplications);

            Map ApplicationInfoMap = new HashMap();
            ApplicationInfoMap.put("provinceId", tCeeApplications.getProvinceId());
            ApplicationInfoMap.put("majorTypeId", tCeeApplications.getMajorTypeId());

            for (TBaseProvince tBaseProvince : provinceList) {
                if (tCeeApplications.getProvinceId() == tBaseProvince.getProvinceId()) {
                    String provinceName = tBaseProvince.getProvinceName();
                    model.addAttribute("provinceName", provinceName);
                    break;
                }
            }
            //批次排名信息
            List batchInfo = zytbSvc.getBatchInfo(ApplicationInfoMap);

            TCeeBatch tCeeBatch = new TCeeBatch();
            if (batchId == null) {
                String minBatchIdSql = "SELECT MIN(batch_id) AS batch_id FROM t_cee_batch\n" +
                        "WHERE YEAR = " + tCeeApplications.getYear() + " \n" +
                        "AND province_id = " + tCeeApplications.getProvinceId() + "\n" +
                        "AND major_type_id = " + tCeeApplications.getMajorTypeId() + " \n" +
                        "AND is_show = '1'  \n";
                Map minBatchIdMap = commonSvc.findOneForJdbc(minBatchIdSql);
                Integer minBatchId = (Integer) minBatchIdMap.get("batch_id");
                batchId = minBatchId;
            }

            String batchSql = " FROM TCeeBatch\n" +
                    "WHERE YEAR = " + tCeeApplications.getYear() + " \n" +
                    "AND provinceId = " + tCeeApplications.getProvinceId() + "\n" +
                    "AND majorTypeId = " + tCeeApplications.getMajorTypeId() + " \n" +
                    "AND batchId = " + batchId + " ";

            tCeeBatch = commonSvc.singleResult(batchSql);
//        批次Id
            model.addAttribute("batchId", tCeeBatch.getBatchId());

            int univNum = tCeeBatch.getUnivNum();
            int extraNum = tCeeBatch.getExtraNum();
            int majorNum = tCeeBatch.getMajorNum();
//        限定平行志愿数
            model.addAttribute("univNum", univNum);
//        限定备选志愿数
            model.addAttribute("extraNum", extraNum);
//        限定专业数
            model.addAttribute("majorNum", majorNum);

            requestMap.put("applicationId", applicationId);
            requestMap.put("userId", user.getId());

            requestMap.put("provinceId", tCeeApplications.getProvinceId());
            requestMap.put("batchId", batchId);

            TBaseProvince tBaseProvince = commonSvc.get(TBaseProvince.class, tCeeApplications.getProvinceId());
            model.addAttribute("tBaseProvince", tBaseProvince);

            if (setPageNum == null) {
                setPageNum = 20;
            }
            Pagination pagination = zytbSvc.getUnivInfo(requestMap, tCeeApplications.getYear(), univProvince, tCeeApplications.getMajorTypeId(), batchId, yx_choose, cpn(pageNo), setPageNum);

            //发现详情表为空新建详情表函数
            findAndCreateDetail(applicationId, batchId, tCeeBatch, tCeeApplications, 0);
            findAndCreateDetail(applicationId, batchId, tCeeBatch, tCeeApplications, 1);

            String choosedUnivListSTr = "";
            String choosedUnivListSql = "SELECT * FROM (\n" +
                    "SELECT * FROM t_cee_applications_detail \n" +
                    "WHERE application_id = '" + applicationId + "' \n" +
                    "AND batch_id = " + batchId + "\n" +
                    "AND is_formal = 0 \n" +
                    "AND univ_id IS NOT NULL \n" +
                    "GROUP BY univ_id,univ_code,univ_name\n" +
                    "UNION \n" +
                    "SELECT * FROM t_cee_applications_detail \n" +
                    "WHERE application_id = '" + applicationId + "' \n" +
                    "AND batch_id = " + batchId + "\n" +
                    "AND is_formal = 0 \n" +
                    "AND univ_id IS NULL ) a\n" +
                    "ORDER BY univ_order ASC ";
            List<TCeeApplicationsDetail> tCeeApplicationsDetailsChoosed = commonSvc.findListbySql(choosedUnivListSql, TCeeApplicationsDetail.class);
            for (TCeeApplicationsDetail tCeeApplicationsDetail : tCeeApplicationsDetailsChoosed) {
                choosedUnivListSTr += '(' + tCeeApplicationsDetail.getUnivCode() + ')' + tCeeApplicationsDetail.getUnivName() + ',';
            }

            String choosedUnivListSTrBx = "";
            String choosedUnivListSqlBx = "SELECT * FROM (\n" +
                    "SELECT * FROM t_cee_applications_detail \n" +
                    "WHERE application_id = '" + applicationId + "' \n" +
                    "AND batch_id = " + batchId + "\n" +
                    "AND is_formal = 1 \n" +
                    "AND univ_id IS NOT NULL \n" +
                    "GROUP BY univ_id,univ_code,univ_name\n" +
                    "UNION \n" +
                    "SELECT * FROM t_cee_applications_detail \n" +
                    "WHERE application_id = '" + applicationId + "' \n" +
                    "AND batch_id = " + batchId + "\n" +
                    "AND is_formal = 1 \n" +
                    "AND univ_id IS NULL ) a\n" +
                    "ORDER BY univ_order ASC ";
            List<TCeeApplicationsDetail> tCeeApplicationsDetailsChoosedBx = commonSvc.findListbySql(choosedUnivListSqlBx, TCeeApplicationsDetail.class);
            for (TCeeApplicationsDetail tCeeApplicationsDetail1 : tCeeApplicationsDetailsChoosedBx) {
                choosedUnivListSTrBx += '(' + tCeeApplicationsDetail1.getUnivCode() + ')' + tCeeApplicationsDetail1.getUnivName() + ',';
            }

            model.addAttribute("tCeeApplicationsDetailsChoosed", JSON.toJSONString(tCeeApplicationsDetailsChoosed));
            model.addAttribute("tCeeApplicationsDetailsChoosedBx", JSON.toJSONString(tCeeApplicationsDetailsChoosedBx));
            model.addAttribute("tCeeApplicationsDetailsChoosedList", tCeeApplicationsDetailsChoosed);
            model.addAttribute("tCeeApplicationsDetailsChoosedBxList", tCeeApplicationsDetailsChoosedBx);
            model.addAttribute("choosedUnivListSTr", choosedUnivListSTr);
            model.addAttribute("universityType", universityType);
            model.addAttribute("setPageNum", setPageNum);
            model.addAttribute("userInfo", userInfo);
            model.addAttribute("provinceList", provinceList);
            model.addAttribute("provinceMap", provinceMap);
            model.addAttribute("pagination", pagination);
            model.addAttribute("batchInfo", batchInfo);
            model.addAttribute("majorTypeId", user.getAttr().get("major_type_id"));
            model.addAttribute("provinceId", user.getAttr().get("province_id"));
//        方案Id
            model.addAttribute("applicationId", applicationId);
            model.addAttribute("tCeeApplications", tCeeApplications);
            model.addAttribute("schoolOrMajorName", schoolOrMajorName);
            model.addAttribute("yx_choose", yx_choose);
            FrontUtils.frontData(request, model, site);


            String application_result = APPLICATIONS_RESULT;

            if(tCeeApplications.getProvinceId() == 1 || tCeeApplications.getProvinceId()==16){
                application_result = "tianzhiyuan_bj";
            }

            return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, application_result);
        } else {
//            return "redirect:admissonSvcAct/findVoluntary.jspx";
            String url = "" + request.getScheme() + "://" + request.getServerName() + "/admissonSvcAct/findVoluntary.jspx";
//            String url = "" + request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "" + request.getContextPath() + "/admissonSvcAct/findVoluntary.jspx";
            System.out.printf(request.getScheme());
            System.out.printf(request.getServerName());
            System.out.printf(String.valueOf(request.getServerPort()));
            System.out.printf(request.getContextPath());
            return "redirect:" + url + "";
        }
    }


    @RequestMapping("/ajaxProject.jspx")
    public void ajaxProject(String applicationName, String applicationId, HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer batchId, String univProvince,
                            String univSchoolType, String yx_cwb, Integer yx_choose, String univ_is985, String univ_is211, String univ_isDefence, String univ_isExcellent,
                            String univ_isIndependence, String univ_isFirstRateUniv, String univ_isFirstRateMajor, String chooseType, Integer setPageNum, Integer pageNo, String schoolOrMajorName, String majorIds,String univSchoolKm,String univSchoolQh) throws IOException {

        Map requestMap = new HashMap();
        requestMap.put("batchId", batchId);
        requestMap.put("univProvince", univProvince);
        requestMap.put("univ_is985", univ_is985);
        requestMap.put("univ_is211", univ_is211);
        requestMap.put("univ_isDefence", univ_isDefence);
        requestMap.put("univ_isExcellent", univ_isExcellent);
        requestMap.put("univ_isIndependence", univ_isIndependence);
        requestMap.put("univ_isFirstRateUniv", univ_isFirstRateUniv);
        requestMap.put("univ_isFirstRateMajor", univ_isFirstRateMajor);
        requestMap.put("univSchoolType", univSchoolType);
        requestMap.put("yx_cwb", yx_cwb);
        requestMap.put("yx_choose", yx_choose);
        requestMap.put("chooseType", chooseType);
        requestMap.put("schoolOrMajorName", schoolOrMajorName);
        requestMap.put("majorIds", majorIds);
//        univSchoolKm = "1,2";
//        univSchoolQh = "0";
        requestMap.put("univSchoolKm",univSchoolKm);
        requestMap.put("univSchoolQh",univSchoolQh);
        model.addAttribute("univSchoolKm",univSchoolKm);
        model.addAttribute("univSchoolQh",univSchoolQh);
        model.addAttribute("requestMap", requestMap);
        model.addAttribute("schoolOrMajorName", schoolOrMajorName);
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);

        //判断用户是否有省份选择和文理科选择的权限，有的话能动态选择省份
        Set<CmsGroup> groups = user.getGroups();
        Iterator<CmsGroup> groupIt = groups.iterator();
        Iterator<CmsGroup> groupIt2 = groups.iterator();
        String yesOrNo = "false";
        String haveTianZhiyuan = "false";
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

        while (groupIt2.hasNext()) {
            Set<String> groupss2 = groupIt2.next().getPerms();
            Iterator<String> groupssIt2 = groupss2.iterator();

            while (groupssIt2.hasNext()) {
                if ("TianZhiYuan:*".equals(groupssIt2.next())) {
                    haveTianZhiyuan = "true";
                    model.addAttribute("haveTianZhiyuan", haveTianZhiyuan);
                    break;
                }
            }
        }

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
        String proName = "";
        if (StringUtils.isNotBlank(univProvince)) {
            String[] univProvinces = univProvince.split(",");

            //回填数据
            for (String proId : univProvinces) {
                for (TBaseProvince tBaseProvince : provinceList) {
                    if (proId.equals(tBaseProvince.getProvinceId())) {
                        proName += tBaseProvince.getProvinceName() + ",";
                        break;
                    }
                }
            }
        }
        model.addAttribute("proName", proName);

        // 学校类型
        List<TDataUniversityType> universityType = commonSvc.loadAll(TDataUniversityType.class);
        //按分类选专业
        String mSql = "SELECT * FROM `t_data_major` WHERE parent_Major_Id ='1'";
        List<Map<String, Object>> majorList = commonSvc.findForJdbc(mSql);
        model.addAttribute("majorList", majorList);

//        选择方案,未选择则查询用户最新的方案ID
        if (applicationId == null) {
            String sql = "SELECT MAX(id) as id FROM t_cee_applications WHERE user_id='" + user.getId() + "'";
            Map map = commonSvc.findOneForJdbc(sql);
            //方案ID
            applicationId = String.valueOf(map.get("id"));
        }

//        applicationId = "1050040";
        TCeeApplications tCeeApplications = commonSvc.get(TCeeApplications.class, Integer.valueOf(applicationId));


        if(tCeeApplications.getProvinceId()==1 || tCeeApplications.getProvinceId()==16){
            requestMap.put("subjectsLevel", tCeeApplications.getSubjects());
        }else{
            requestMap.put("subjectsLevel", tCeeApplications.getSubjectsLevel());
        }
        Map ApplicationInfoMap = new HashMap();
        ApplicationInfoMap.put("provinceId", tCeeApplications.getProvinceId());
        ApplicationInfoMap.put("majorTypeId", tCeeApplications.getMajorTypeId());

        //批次排名信息
        List batchInfo = zytbSvc.getBatchInfo(ApplicationInfoMap);

        TCeeBatch tCeeBatch = new TCeeBatch();
        if (batchId == null) {
            String minBatchIdSql = "SELECT MIN(batch_id) AS batch_id FROM t_cee_batch\n" +
                    "WHERE YEAR = " + tCeeApplications.getYear() + " \n" +
                    "AND province_id = " + tCeeApplications.getProvinceId() + "\n" +
                    "AND major_type_id = " + tCeeApplications.getMajorTypeId() + " \n" +
                    "AND is_show = '1'  ";
            Map minBatchIdMap = commonSvc.findOneForJdbc(minBatchIdSql);
            Integer minBatchId = (Integer) minBatchIdMap.get("batch_id");
            batchId = minBatchId;
        }

        String batchSql = " FROM TCeeBatch\n" +
                "WHERE YEAR = " + tCeeApplications.getYear() + " \n" +
                "AND provinceId = " + tCeeApplications.getProvinceId() + "\n" +
                "AND majorTypeId = " + tCeeApplications.getMajorTypeId() + " \n" +
                "AND batchId = " + batchId + " ";

        tCeeBatch = commonSvc.singleResult(batchSql);
//        批次Id
        model.addAttribute("batchId", tCeeBatch.getBatchId());
        int univNum = tCeeBatch.getUnivNum();
        int extraNum = tCeeBatch.getExtraNum();
        int majorNum = tCeeBatch.getMajorNum();
//        限定平行志愿数
        model.addAttribute("univNum", univNum);
//        限定备选志愿数
        model.addAttribute("extraNum", extraNum);
//        限定专业数
        model.addAttribute("majorNum", majorNum);

        requestMap.put("applicationId", applicationId);
        requestMap.put("userId", user.getId());
// Alltodo: 2020/4/6 测试北京数据
        requestMap.put("provinceId", tCeeApplications.getProvinceId());
        if (setPageNum == null) {
            setPageNum = 20;
        }

        TBaseProvince tBaseProvince = commonSvc.get(TBaseProvince.class, tCeeApplications.getProvinceId());
        model.addAttribute("tBaseProvince", tBaseProvince);

        Pagination pagination = zytbSvc.getUnivInfo(requestMap, tCeeApplications.getYear(), univProvince, tCeeApplications.getMajorTypeId(), batchId, yx_choose, cpn(pageNo), setPageNum);

        //发现详情表为空新建详情表函数
        findAndCreateDetail(applicationId, batchId, tCeeBatch, tCeeApplications, 0);
        findAndCreateDetail(applicationId, batchId, tCeeBatch, tCeeApplications, 1);

        String choosedUnivListSTr = "";
        String choosedUnivListSql = "SELECT * FROM (\n" +
                "SELECT * FROM t_cee_applications_detail \n" +
                "WHERE application_id = '" + applicationId + "' \n" +
                "AND batch_id = " + batchId + "\n" +
                "AND is_formal = 0 \n" +
                "AND univ_id IS NOT NULL \n" +
                "GROUP BY univ_id,univ_code,univ_name\n" +
                "UNION \n" +
                "SELECT * FROM t_cee_applications_detail \n" +
                "WHERE application_id = '" + applicationId + "' \n" +
                "AND batch_id = " + batchId + "\n" +
                "AND is_formal = 0 \n" +
                "AND univ_id IS NULL ) a\n" +
                "ORDER BY univ_order ASC ";
        List<TCeeApplicationsDetail> tCeeApplicationsDetailsChoosed = commonSvc.findListbySql(choosedUnivListSql, TCeeApplicationsDetail.class);
        for (TCeeApplicationsDetail tCeeApplicationsDetail : tCeeApplicationsDetailsChoosed) {
            choosedUnivListSTr += '(' + tCeeApplicationsDetail.getUnivCode() + ')' + tCeeApplicationsDetail.getUnivName() + ',';
        }

        String choosedUnivListSTrBx = "";
        String choosedUnivListSqlBx = "SELECT * FROM (\n" +
                "SELECT * FROM t_cee_applications_detail \n" +
                "WHERE application_id = '" + applicationId + "' \n" +
                "AND batch_id = " + batchId + "\n" +
                "AND is_formal = 1 \n" +
                "AND univ_id IS NOT NULL \n" +
                "GROUP BY univ_id,univ_code,univ_name\n" +
                "UNION \n" +
                "SELECT * FROM t_cee_applications_detail \n" +
                "WHERE application_id = '" + applicationId + "' \n" +
                "AND batch_id = " + batchId + "\n" +
                "AND is_formal = 1 \n" +
                "AND univ_id IS NULL ) a\n" +
                "ORDER BY univ_order ASC ";
        List<TCeeApplicationsDetail> tCeeApplicationsDetailsChoosedBx = commonSvc.findListbySql(choosedUnivListSqlBx, TCeeApplicationsDetail.class);
        for (TCeeApplicationsDetail tCeeApplicationsDetail1 : tCeeApplicationsDetailsChoosedBx) {
            choosedUnivListSTrBx += '(' + tCeeApplicationsDetail1.getUnivCode() + ')' + tCeeApplicationsDetail1.getUnivName() + ',';
        }

//        model.addAttribute("tCeeApplicationsDetailsChoosed", tCeeApplicationsDetailsChoosed);
//        model.addAttribute("tCeeApplicationsDetailsChoosedBx", tCeeApplicationsDetailsChoosedBx);
//        model.addAttribute("tCeeApplicationsDetailsChoosedList", tCeeApplicationsDetailsChoosed);
//        model.addAttribute("tCeeApplicationsDetailsChoosedBxList", tCeeApplicationsDetailsChoosedBx);
//
//        model.addAttribute("universityType", universityType);
//        model.addAttribute("setPageNum", setPageNum);
//        model.addAttribute("userInfo", userInfo);
//        model.addAttribute("provinceList", provinceList);
//        model.addAttribute("provinceMap", provinceMap);
//        model.addAttribute("pagination", pagination);
//        model.addAttribute("batchInfo", batchInfo);
//        model.addAttribute("majorTypeId", user.getAttr().get("major_type_id"));
//        model.addAttribute("provinceId", user.getAttr().get("province_id"));

        requestMap.put("universityType", universityType);
        requestMap.put("tCeeApplications", tCeeApplications);
        requestMap.put("setPageNum", setPageNum);
        requestMap.put("userInfo", userInfo);
        requestMap.put("provinceList", provinceList);
        requestMap.put("provinceMap", provinceMap);
        requestMap.put("pagination", pagination);
        requestMap.put("batchInfo", batchInfo);
        requestMap.put("univNum", univNum);
        requestMap.put("extraNum", extraNum);
        requestMap.put("majorNum", majorNum);
        requestMap.put("schoolOrMajorName", schoolOrMajorName);
        requestMap.put("yx_choose", yx_choose);

        requestMap.put("tCeeApplicationsDetailsChoosed", tCeeApplicationsDetailsChoosed);
        requestMap.put("tCeeApplicationsDetailsChoosedBx", tCeeApplicationsDetailsChoosedBx);
        requestMap.put("choosedUnivListSTrBx", choosedUnivListSTrBx);
        requestMap.put("choosedUnivListSTr", choosedUnivListSTr);
        requestMap.put("haveTianZhiyuan", haveTianZhiyuan);


//        方案Id
        model.addAttribute("applicationId", applicationId);
        FrontUtils.frontData(request, model, site);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(requestMap));
//        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, APPLICATIONS_RESULT);
    }

    @RequestMapping("/projectFast.jspx")
    public String getProjectFast(String applicationName, String applicationId, Integer pageNo, HttpServletRequest request, ModelMap model, Integer batchId, String univProvince,
                                 String univSchoolType, String yx_cwb, String yx_choose, String univ_is985, String univ_is211, String univ_isDefence, String univ_isExcellent,
                                 String univ_isIndependence, String univ_isFirstRateUniv, String univ_isFirstRateMajor, String chooseType, Integer setPageNum, String schoolOrMajorName) {

        Map requestMap = new HashMap();
        requestMap.put("batchId", batchId);
        requestMap.put("univProvince", univProvince);
        requestMap.put("univ_is985", univ_is985);
        requestMap.put("univ_is211", univ_is211);
        requestMap.put("univ_isDefence", univ_isDefence);
        requestMap.put("univ_isExcellent", univ_isExcellent);
        requestMap.put("univ_isIndependence", univ_isIndependence);
        requestMap.put("univ_isFirstRateUniv", univ_isFirstRateUniv);
        requestMap.put("univ_isFirstRateMajor", univ_isFirstRateMajor);
        requestMap.put("univSchoolType", univSchoolType);
        requestMap.put("yx_cwb", yx_cwb);
        requestMap.put("yx_choose", yx_choose);
        requestMap.put("chooseType", chooseType);
        requestMap.put("schoolOrMajorName", schoolOrMajorName);
        model.addAttribute("requestMap", requestMap);
        model.addAttribute("schoolOrMajorName", schoolOrMajorName);
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        //判断用户是否有省份选择和文理科选择的权限，有的话能动态选择省份
        Set<CmsGroup> groups = user.getGroups();
        Iterator<CmsGroup> groupIt = groups.iterator();
        Iterator<CmsGroup> groupIt2 = groups.iterator();
        String yesOrNo = "false";
        String haveZhiNengZhiYuanTianBao = "false";
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
        while (groupIt2.hasNext()) {
            Set<String> groupss2 = groupIt2.next().getPerms();
            Iterator<String> groupssIt2 = groupss2.iterator();

            while (groupssIt2.hasNext()) {
                if ("ZhiNengZhiYuanTianBao:*".equals(groupssIt2.next())) {
                    haveZhiNengZhiYuanTianBao = "true";
                    model.addAttribute("haveZhiNengZhiYuanTianBao", haveZhiNengZhiYuanTianBao);
                    break;
                }
            }
        }


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
        String proName = "";
        if (StringUtils.isNotBlank(univProvince)) {
            String[] univProvinces = univProvince.split(",");

            //回填数据
            for (String proId : univProvinces) {
                for (TBaseProvince tBaseProvince : provinceList) {
                    if (proId.equals(tBaseProvince.getProvinceId())) {
                        proName += tBaseProvince.getProvinceName() + ",";
                        break;
                    }
                }
            }
        }
        model.addAttribute("proName", proName);
        // 学校类型
        List<TDataUniversityType> universityType = commonSvc.loadAll(TDataUniversityType.class);

        //查询所有专业
        //一级专业
        List<Map<String, Object>> oneList = this.queryMajorByParentId("'1','2'");
        //二级专业

        List<Map<String, Object>> twoList = this.queryMajorByParentId(subString(oneList));
        //三级专业
        List<Map<String, Object>> threeLists = this.queryMajorByParentId(subString(twoList));

        List<Map<String, Object>> threeList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map2 : threeLists) {
            map2.put("msid", "m" + map2.get("major_Id").toString().subSequence(0, 2));
            threeList.add(map2);
        }
        model.addAttribute("oneList", oneList);
        model.addAttribute("twoList", twoList);
        model.addAttribute("threeList", threeList);


//        选择方案,未选择则查询用户最新的方案ID
        if (applicationId == null) {
            String sql = "SELECT MAX(id) as id FROM t_cee_applications WHERE user_id='" + user.getId() + "'";
            Map map = commonSvc.findOneForJdbc(sql);
            //方案ID
            applicationId = String.valueOf(map.get("id"));
        }

//        applicationId = "1050040";
        TCeeApplications tCeeApplications = commonSvc.get(TCeeApplications.class, Integer.valueOf(applicationId));
        model.addAttribute("tCeeApplications", tCeeApplications);

        for (TBaseProvince tBaseProvince : provinceList) {
            if (tCeeApplications.getProvinceId() == tBaseProvince.getProvinceId()) {
                String provinceName = tBaseProvince.getProvinceName();
                model.addAttribute("provinceName", provinceName);
                break;
            }
        }

        Map ApplicationInfoMap = new HashMap();
        ApplicationInfoMap.put("provinceId", tCeeApplications.getProvinceId());
        ApplicationInfoMap.put("majorTypeId", tCeeApplications.getMajorTypeId());

        //批次排名信息
        List batchInfo = zytbSvc.getBatchInfo(ApplicationInfoMap);

        TCeeBatch tCeeBatch = new TCeeBatch();
        if (batchId == null) {
            String minBatchIdSql = "SELECT MIN(batch_id) AS batch_id FROM t_cee_batch\n" +
                    "WHERE YEAR = " + tCeeApplications.getYear() + " \n" +
                    "AND province_id = " + tCeeApplications.getProvinceId() + "\n" +
                    "AND major_type_id = " + tCeeApplications.getMajorTypeId() + " \n" +
                    "AND is_show = '1' \n";
            Map minBatchIdMap = commonSvc.findOneForJdbc(minBatchIdSql);
            Integer minBatchId = (Integer) minBatchIdMap.get("batch_id");
            batchId = minBatchId;
        }

        String batchSql = " FROM TCeeBatch\n" +
                "WHERE YEAR = " + tCeeApplications.getYear() + " \n" +
                "AND provinceId = " + tCeeApplications.getProvinceId() + "\n" +
                "AND majorTypeId = " + tCeeApplications.getMajorTypeId() + " \n" +
                "AND batchId = " + batchId + " ";

        tCeeBatch = commonSvc.singleResult(batchSql);
//        批次Id
        model.addAttribute("batchId", tCeeBatch.getBatchId());
        int univNum = tCeeBatch.getUnivNum();
        int extraNum = tCeeBatch.getExtraNum();
        int majorNum = tCeeBatch.getMajorNum();
//        限定平行志愿数
        model.addAttribute("univNum", univNum);
//        限定备选志愿数
        model.addAttribute("extraNum", extraNum);
//        限定专业数
        model.addAttribute("majorNum", majorNum);

        requestMap.put("applicationId", applicationId);
        requestMap.put("userId", user.getId());

        requestMap.put("provinceId", tCeeApplications.getProvinceId());
        requestMap.put("batchId", batchId);

        if (setPageNum == null) {
            setPageNum = 20;
        }

        TBaseProvince tBaseProvince = commonSvc.get(TBaseProvince.class, tCeeApplications.getProvinceId());
        model.addAttribute("tBaseProvince", tBaseProvince);
        Pagination pagination = zytbSvc.getUnivInfo(requestMap, tCeeApplications.getYear(), univProvince, tCeeApplications.getMajorTypeId(), batchId, null, cpn(pageNo), setPageNum);

        String choosedUnivListSTr = "";
        String choosedUnivListSql = "SELECT * FROM (\n" +
                "SELECT * FROM t_cee_applications_detail \n" +
                "WHERE application_id = '" + applicationId + "' \n" +
                "AND batch_id = " + batchId + "\n" +
                "AND is_formal = 0 \n" +
                "AND univ_id IS NOT NULL \n" +
                "GROUP BY univ_id,univ_code,univ_name\n" +
                "UNION \n" +
                "SELECT * FROM t_cee_applications_detail \n" +
                "WHERE application_id = '" + applicationId + "' \n" +
                "AND batch_id = " + batchId + "\n" +
                "AND is_formal = 0 \n" +
                "AND univ_id IS NULL ) a\n" +
                "ORDER BY univ_order ASC ";
        List<TCeeApplicationsDetail> tCeeApplicationsDetailsChoosed = commonSvc.findListbySql(choosedUnivListSql, TCeeApplicationsDetail.class);
        for (TCeeApplicationsDetail tCeeApplicationsDetail : tCeeApplicationsDetailsChoosed) {
            choosedUnivListSTr += '(' + tCeeApplicationsDetail.getUnivCode() + ')' + tCeeApplicationsDetail.getUnivName() + ',';
        }

        String choosedUnivListSTrBx = "";
        String choosedUnivListSqlBx = "SELECT * FROM (\n" +
                "SELECT * FROM t_cee_applications_detail \n" +
                "WHERE application_id = '" + applicationId + "' \n" +
                "AND batch_id = " + batchId + "\n" +
                "AND is_formal = 1 \n" +
                "AND univ_id IS NOT NULL \n" +
                "GROUP BY univ_id,univ_code,univ_name\n" +
                "UNION \n" +
                "SELECT * FROM t_cee_applications_detail \n" +
                "WHERE application_id = '" + applicationId + "' \n" +
                "AND batch_id = " + batchId + "\n" +
                "AND is_formal = 1 \n" +
                "AND univ_id IS NULL ) a\n" +
                "ORDER BY univ_order ASC ";
        List<TCeeApplicationsDetail> tCeeApplicationsDetailsChoosedBx = commonSvc.findListbySql(choosedUnivListSqlBx, TCeeApplicationsDetail.class);
        for (TCeeApplicationsDetail tCeeApplicationsDetail1 : tCeeApplicationsDetailsChoosedBx) {
            choosedUnivListSTrBx += '(' + tCeeApplicationsDetail1.getUnivCode() + ')' + tCeeApplicationsDetail1.getUnivName() + ',';
        }

        model.addAttribute("tCeeApplicationsDetailsChoosed", JSON.toJSONString(tCeeApplicationsDetailsChoosed));
        model.addAttribute("tCeeApplicationsDetailsChoosedBx", JSON.toJSONString(tCeeApplicationsDetailsChoosedBx));
        model.addAttribute("tCeeApplicationsDetailsChoosedList", tCeeApplicationsDetailsChoosed);
        model.addAttribute("tCeeApplicationsDetailsChoosedBxList", tCeeApplicationsDetailsChoosedBx);
        model.addAttribute("choosedUnivListSTr", choosedUnivListSTr);
        model.addAttribute("universityType", universityType);
        model.addAttribute("setPageNum", setPageNum);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("provinceList", provinceList);
        model.addAttribute("provinceMap", provinceMap);
        model.addAttribute("pagination", pagination);
        model.addAttribute("batchInfo", batchInfo);
        model.addAttribute("majorTypeId", user.getAttr().get("major_type_id"));
        model.addAttribute("provinceId", user.getAttr().get("province_id"));
//        方案Id
        model.addAttribute("applicationId", applicationId);
        model.addAttribute("tCeeApplications", tCeeApplications);
        model.addAttribute("schoolOrMajorName", schoolOrMajorName);
        model.addAttribute("yx_choose", yx_choose);
        //TCeeApplicationsRequire不存在则新建
        String tCeeApplicationsRequireHql = "FROM TCeeApplicationsRequire WHERE stuUserId='" + user.getId() + "' AND applicationId='" + applicationId + "' AND batchId='" + batchId + "'";

        TCeeApplicationsRequire tCeeApplicationsRequire = new TCeeApplicationsRequire();
        tCeeApplicationsRequire = commonSvc.singleResult(tCeeApplicationsRequireHql);
        if (tCeeApplicationsRequire == null) {
            tCeeApplicationsRequire = new TCeeApplicationsRequire();
        }

        tCeeApplicationsRequire.setApplicationId(Integer.valueOf(applicationId));
        tCeeApplicationsRequire.setBatchId(batchId);
        tCeeApplicationsRequire.setStuUserId(user.getId());
        tCeeApplicationsRequire.setAdvancedUnivNum(tCeeBatch.getcNum());
        tCeeApplicationsRequire.setStableUnivNum(tCeeBatch.getwNum());
        tCeeApplicationsRequire.setBackwardUnivNum(tCeeBatch.getbNum());
        commonSvc.saveOrUpdate(tCeeApplicationsRequire);
        model.addAttribute("tCeeApplicationsRequire", tCeeApplicationsRequire);
        model.addAttribute("tCeeApplicationsRequireintentUniv", tCeeApplicationsRequire.getIntentUniv());
        model.addAttribute("tCeeApplicationsRequireavoidUniv", tCeeApplicationsRequire.getAvoidUniv());
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, APPLICATIONS_RESULT_FAST);
    }


    @RequestMapping("/zNAjaxProject.jspx")
    public void zNAjaxProject(String applicationName, String applicationId, Integer pageNo, HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer batchId, String univProvince,
                              String univSchoolType, String yx_cwb, String yx_choose, String univ_is985, String univ_is211, String univ_isDefence, String univ_isExcellent,
                              String univ_isIndependence, String univ_isFirstRateUniv, String univ_isFirstRateMajor, String chooseType, Integer setPageNum, String schoolOrMajorName, String majorIds) throws IOException {

        Map requestMap = new HashMap();
        requestMap.put("batchId", batchId);
        requestMap.put("univProvince", univProvince);
        requestMap.put("univ_is985", univ_is985);
        requestMap.put("univ_is211", univ_is211);
        requestMap.put("univ_isDefence", univ_isDefence);
        requestMap.put("univ_isExcellent", univ_isExcellent);
        requestMap.put("univ_isIndependence", univ_isIndependence);
        requestMap.put("univ_isFirstRateUniv", univ_isFirstRateUniv);
        requestMap.put("univ_isFirstRateMajor", univ_isFirstRateMajor);
        requestMap.put("univSchoolType", univSchoolType);
        requestMap.put("yx_cwb", yx_cwb);
        requestMap.put("yx_choose", yx_choose);
        requestMap.put("chooseType", chooseType);
        requestMap.put("schoolOrMajorName", schoolOrMajorName);
        requestMap.put("majorIds", majorIds);
        model.addAttribute("requestMap", requestMap);

        model.addAttribute("schoolOrMajorName", schoolOrMajorName);
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);

        //判断用户是否有省份选择和文理科选择的权限，有的话能动态选择省份
        Set<CmsGroup> groups = user.getGroups();
        Iterator<CmsGroup> groupIt = groups.iterator();
        Iterator<CmsGroup> groupIt2 = groups.iterator();
        String yesOrNo = "false";
        String haveZhiNengZhiYuanTianBao = "false";
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
        while (groupIt2.hasNext()) {
            Set<String> groupss2 = groupIt2.next().getPerms();
            Iterator<String> groupssIt2 = groupss2.iterator();

            while (groupssIt2.hasNext()) {
                if ("ZhiNengZhiYuanTianBao:*".equals(groupssIt2.next())) {
                    haveZhiNengZhiYuanTianBao = "true";
                    model.addAttribute("ZhiNengZhiYuanTianBao:*", haveZhiNengZhiYuanTianBao);
                    break;
                }
            }
        }

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
        String proName = "";
        if (StringUtils.isNotBlank(univProvince)) {
            String[] univProvinces = univProvince.split(",");

            //回填数据
            for (String proId : univProvinces) {
                for (TBaseProvince tBaseProvince : provinceList) {
                    if (proId.equals(tBaseProvince.getProvinceId())) {
                        proName += tBaseProvince.getProvinceName() + ",";
                        break;
                    }
                }
            }
        }
        model.addAttribute("proName", proName);

        // 学校类型
        List<TDataUniversityType> universityType = commonSvc.loadAll(TDataUniversityType.class);
        //按分类选专业
        String mSql = "SELECT * FROM `t_data_major` WHERE parent_Major_Id ='1'";
        List<Map<String, Object>> majorList = commonSvc.findForJdbc(mSql);
        model.addAttribute("majorList", majorList);

//        选择方案,未选择则查询用户最新的方案ID
        if (applicationId == null) {
            String sql = "SELECT MAX(id) as id FROM t_cee_applications WHERE user_id='" + user.getId() + "'";
            Map map = commonSvc.findOneForJdbc(sql);
            //方案ID
            applicationId = String.valueOf(map.get("id"));
        }

//        applicationId = "1050040";
        TCeeApplications tCeeApplications = commonSvc.get(TCeeApplications.class, Integer.valueOf(applicationId));

        Map ApplicationInfoMap = new HashMap();
        ApplicationInfoMap.put("provinceId", tCeeApplications.getProvinceId());
        ApplicationInfoMap.put("majorTypeId", tCeeApplications.getMajorTypeId());

        //批次排名信息
        List batchInfo = zytbSvc.getBatchInfo(ApplicationInfoMap);

        TCeeBatch tCeeBatch = new TCeeBatch();
        if (batchId == null) {
            String minBatchIdSql = "SELECT MIN(batch_id) AS batch_id FROM t_cee_batch\n" +
                    "WHERE YEAR = " + tCeeApplications.getYear() + " \n" +
                    "AND province_id = " + tCeeApplications.getProvinceId() + "\n" +
                    "AND major_type_id = " + tCeeApplications.getMajorTypeId() + " \n" +
                    "AND is_show = '1'  \n";
            Map minBatchIdMap = commonSvc.findOneForJdbc(minBatchIdSql);
            Integer minBatchId = (Integer) minBatchIdMap.get("batch_id");
            batchId = minBatchId;
        }

        String batchSql = " FROM TCeeBatch\n" +
                "WHERE YEAR = " + tCeeApplications.getYear() + " \n" +
                "AND provinceId = " + tCeeApplications.getProvinceId() + "\n" +
                "AND majorTypeId = " + tCeeApplications.getMajorTypeId() + " \n" +
                "AND batchId = " + batchId + " ";

        tCeeBatch = commonSvc.singleResult(batchSql);
//        批次Id
        model.addAttribute("batchId", tCeeBatch.getBatchId());
        int univNum = tCeeBatch.getUnivNum();
        int extraNum = tCeeBatch.getExtraNum();
        int majorNum = tCeeBatch.getMajorNum();

//        限定平行志愿数
        model.addAttribute("univNum", univNum);
//        限定备选志愿数
        model.addAttribute("extraNum", extraNum);
//        限定专业数
        model.addAttribute("majorNum", majorNum);
        model.addAttribute("majorNumJosn", JSON.toJSONString(majorNum));
        requestMap.put("applicationId", applicationId);
        requestMap.put("userId", user.getId());
        requestMap.put("provinceId", tCeeApplications.getProvinceId());

        if (setPageNum == null) {
            setPageNum = 20;
        }

        TBaseProvince tBaseProvince = commonSvc.get(TBaseProvince.class, tCeeApplications.getProvinceId());
        model.addAttribute("tBaseProvince", tBaseProvince);
        Pagination pagination = zytbSvc.getUnivInfo(requestMap, tCeeApplications.getYear(), univProvince, tCeeApplications.getMajorTypeId(), batchId, null, cpn(pageNo), setPageNum);

        String choosedUnivListSTr = "";
        String choosedUnivListSql = "SELECT * FROM (\n" +
                "SELECT * FROM t_cee_applications_detail \n" +
                "WHERE application_id = '" + applicationId + "' \n" +
                "AND batch_id = " + batchId + "\n" +
                "AND is_formal = 0 \n" +
                "AND univ_id IS NOT NULL \n" +
                "GROUP BY univ_id,univ_code,univ_name\n" +
                "UNION \n" +
                "SELECT * FROM t_cee_applications_detail \n" +
                "WHERE application_id = '" + applicationId + "' \n" +
                "AND batch_id = " + batchId + "\n" +
                "AND is_formal = 0 \n" +
                "AND univ_id IS NULL ) a\n" +
                "ORDER BY univ_order ASC ";
        List<TCeeApplicationsDetail> tCeeApplicationsDetailsChoosed = commonSvc.findListbySql(choosedUnivListSql, TCeeApplicationsDetail.class);
        for (TCeeApplicationsDetail tCeeApplicationsDetail : tCeeApplicationsDetailsChoosed) {
            choosedUnivListSTr += '(' + tCeeApplicationsDetail.getUnivCode() + ')' + tCeeApplicationsDetail.getUnivName() + ',';
        }

        String choosedUnivListSTrBx = "";
        String choosedUnivListSqlBx = "SELECT * FROM (\n" +
                "SELECT * FROM t_cee_applications_detail \n" +
                "WHERE application_id = '" + applicationId + "' \n" +
                "AND batch_id = " + batchId + "\n" +
                "AND is_formal = 1 \n" +
                "AND univ_id IS NOT NULL \n" +
                "GROUP BY univ_id,univ_code,univ_name\n" +
                "UNION \n" +
                "SELECT * FROM t_cee_applications_detail \n" +
                "WHERE application_id = '" + applicationId + "' \n" +
                "AND batch_id = " + batchId + "\n" +
                "AND is_formal = 1 \n" +
                "AND univ_id IS NULL ) a\n" +
                "ORDER BY univ_order ASC ";
        List<TCeeApplicationsDetail> tCeeApplicationsDetailsChoosedBx = commonSvc.findListbySql(choosedUnivListSqlBx, TCeeApplicationsDetail.class);
        for (TCeeApplicationsDetail tCeeApplicationsDetail1 : tCeeApplicationsDetailsChoosedBx) {
            choosedUnivListSTrBx += '(' + tCeeApplicationsDetail1.getUnivCode() + ')' + tCeeApplicationsDetail1.getUnivName() + ',';
        }

        //TCeeApplicationsRequire不存在则新建
        String tCeeApplicationsRequireHql = "FROM TCeeApplicationsRequire WHERE stuUserId='" + user.getId() + "' AND applicationId='" + applicationId + "' AND batchId='" + batchId + "'";
        TCeeApplicationsRequire tCeeApplicationsRequire = new TCeeApplicationsRequire();
        tCeeApplicationsRequire = commonSvc.singleResult(tCeeApplicationsRequireHql);
        if (tCeeApplicationsRequire == null) {
            tCeeApplicationsRequire = new TCeeApplicationsRequire();
        }

        tCeeApplicationsRequire.setApplicationId(Integer.valueOf(applicationId));
        tCeeApplicationsRequire.setBatchId(batchId);
        tCeeApplicationsRequire.setStuUserId(user.getId());
        tCeeApplicationsRequire.setAdvancedUnivNum(tCeeBatch.getcNum());
        tCeeApplicationsRequire.setStableUnivNum(tCeeBatch.getwNum());
        tCeeApplicationsRequire.setBackwardUnivNum(tCeeBatch.getbNum());
        commonSvc.saveOrUpdate(tCeeApplicationsRequire);
        model.addAttribute("tCeeApplicationsRequire", tCeeApplicationsRequire);

        model.addAttribute("tCeeApplicationsDetailsChoosed", JSON.toJSONString(tCeeApplicationsDetailsChoosed));
        model.addAttribute("tCeeApplicationsDetailsChoosedBx", JSON.toJSONString(tCeeApplicationsDetailsChoosedBx));
        model.addAttribute("tCeeApplicationsDetailsChoosedList", tCeeApplicationsDetailsChoosed);
        model.addAttribute("tCeeApplicationsDetailsChoosedBxList", tCeeApplicationsDetailsChoosedBx);

        model.addAttribute("universityType", universityType);
        model.addAttribute("setPageNum", setPageNum);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("provinceList", provinceList);
        model.addAttribute("provinceMap", provinceMap);
        model.addAttribute("pagination", pagination);
        model.addAttribute("batchInfo", batchInfo);
        model.addAttribute("majorTypeId", user.getAttr().get("major_type_id"));
        model.addAttribute("provinceId", user.getAttr().get("province_id"));

        requestMap.put("universityType", universityType);
        requestMap.put("setPageNum", setPageNum);
        requestMap.put("userInfo", userInfo);
        requestMap.put("provinceList", provinceList);
        requestMap.put("provinceMap", provinceMap);
        requestMap.put("pagination", pagination);
        requestMap.put("batchInfo", batchInfo);
        requestMap.put("univNum", univNum);
        requestMap.put("extraNum", extraNum);
        requestMap.put("majorNum", majorNum);
        requestMap.put("tCeeApplicationsRequireintentUniv", tCeeApplicationsRequire.getIntentUniv());
        requestMap.put("tCeeApplicationsRequireavoidUniv", tCeeApplicationsRequire.getAvoidUniv());
        requestMap.put("tCeeApplications", tCeeApplications);
        requestMap.put("haveZhiNengZhiYuanTianBao", haveZhiNengZhiYuanTianBao);
        requestMap.put("schoolOrMajorName", schoolOrMajorName);
        requestMap.put("yx_choose", yx_choose);

//保存智能填报筛选条件数量
        saveZNChoose(requestMap, request, Integer.valueOf(applicationId));

//        方案Id
        model.addAttribute("applicationId", applicationId);
        FrontUtils.frontData(request, model, site);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(requestMap));

    }

    @RequestMapping("/cwb.jspx")
    public String cwb(HttpServletRequest request, String applicationId, Integer batchId, ModelMap model) {
        CmsUser user = CmsUtils.getUser(request);
        CmsSite site = CmsUtils.getSite(request);

        Set<CmsGroup> groups = user.getGroups();
        Iterator<CmsGroup> groupIt2 = groups.iterator();
        String haveZhiNengZhiYuanTianBao = "false";
        while (groupIt2.hasNext()) {
            Set<String> groupss2 = groupIt2.next().getPerms();
            Iterator<String> groupssIt2 = groupss2.iterator();
            while (groupssIt2.hasNext()) {
                if ("ZhiNengZhiYuanTianBao:*".equals(groupssIt2.next())) {
                    haveZhiNengZhiYuanTianBao = "true";
                    model.addAttribute("haveZhiNengZhiYuanTianBao", haveZhiNengZhiYuanTianBao);
                    break;
                }
            }
        }

//        applicationId = "1050039";
//        batchId = 2;
        TCeeApplications tCeeApplications = commonSvc.getEntity(TCeeApplications.class, Integer.valueOf(applicationId));
        model.addAttribute("applicationId", applicationId);
        TCeeBatch tCeeBatch = smartZytbSvc.getBatch(tCeeApplications.getYear(), tCeeApplications.getProvinceId(), tCeeApplications.getMajorTypeId(), batchId);
        model.addAttribute("batchId", batchId);
        model.addAttribute("tCeeBatch", tCeeBatch);

        String tCeeApplicationsRequireHql = "From TCeeApplicationsRequire where applicationId='" + applicationId + "' and batchId='" + batchId + "' ";
//        String hql = "From TCeeApplicationsRequire where stuUserId='" + user.getId() + "' and applicationId='" + applicationId + "' and batchId='" + batchId + "' ";
        TCeeApplicationsRequire tCeeApplicationsRequire = commonSvc.singleResult(tCeeApplicationsRequireHql);

//        省份列表
        List<TBaseProvince> tBaseProvinces = commonSvc.loadAll(TBaseProvince.class);
        model.addAttribute("tBaseProvinces", tBaseProvinces);
//        院校分类列表
        List<TDataUniversityType> universityType = commonSvc.loadAll(TDataUniversityType.class);
        model.addAttribute("universityType", universityType);

        StringBuffer univProvinceStrs = new StringBuffer();
        if (StringUtils.isNotBlank(tCeeApplicationsRequire.getIntentProvinceId())) {
            String[] univProvinces = tCeeApplicationsRequire.getIntentProvinceId().split("\\|");

            for (String univProvince : univProvinces) {
                for (TBaseProvince tBaseProvince : tBaseProvinces) {
                    if (univProvince.equals(String.valueOf(tBaseProvince.getProvinceId()))) {
                        univProvinceStrs.append(tBaseProvince.getProvinceName());
                        univProvinceStrs.append(",");
                    }
                }
            }
            //        已选意向地区字符串
            model.addAttribute("univProvinceStrs", univProvinceStrs.substring(0, univProvinceStrs.length() - 1).toString());
        }

        //        已选院校分类字符串
        if (StringUtils.isNotBlank(tCeeApplicationsRequire.getIntentUnivType())) {
            String univTypesStr = tCeeApplicationsRequire.getIntentUnivType().replace("|", ",");
            model.addAttribute("univTypesStr", univTypesStr);
        }
        //        已选院校属性，1或者0
        if (tCeeApplicationsRequire.getIs211() != null) {
            Integer is211 = tCeeApplicationsRequire.getIs211();
            model.addAttribute("is211", is211);
        }

        if (tCeeApplicationsRequire.getIs985() != null) {
            Integer is985 = tCeeApplicationsRequire.getIs985();
            model.addAttribute("is985", is985);
        }

        if (tCeeApplicationsRequire.getIsExcellent() != null) {
            Integer isExcellent = tCeeApplicationsRequire.getIsExcellent();
            model.addAttribute("isExcellent", isExcellent);
        }

        if (tCeeApplicationsRequire.getIsIndependence() != null) {
            Integer isIndependence = tCeeApplicationsRequire.getIsIndependence();
            model.addAttribute("isIndependence", isIndependence);
        }

        if (tCeeApplicationsRequire.getIsFirstRate() != null) {
            Integer isFirstRate = tCeeApplicationsRequire.getIsFirstRate();
            model.addAttribute("isFirstRate", isFirstRate);
        }

        if (tCeeApplicationsRequire.getIsFirstRate() != null) {
            Integer isKeylab = tCeeApplicationsRequire.getIsKeylab();
            model.addAttribute("isKeylab", isKeylab);
        }

        if (tCeeApplicationsRequire.getAdvancedUnivNum() != null) {
            Integer advancedUnivNum = tCeeApplicationsRequire.getAdvancedUnivNum();
            model.addAttribute("advancedUnivNum", advancedUnivNum);
        }

        if (tCeeApplicationsRequire.getStableUnivNum() != null) {
            Integer stableUnivNum = tCeeApplicationsRequire.getStableUnivNum();
            model.addAttribute("stableUnivNum", stableUnivNum);
        }

        if (tCeeApplicationsRequire.getBackwardUnivNum() != null) {
            Integer backwardUnivNum = tCeeApplicationsRequire.getBackwardUnivNum();
            model.addAttribute("backwardUnivNum", backwardUnivNum);
        }

        if (StringUtils.isNotBlank(tCeeApplicationsRequire.getIntentMajor())) {
            String tCeeEnrollMajorListsSql = "SELECT * FROM t_cee_enroll_major_list\n" +
                    "WHERE id IN ( " + tCeeApplicationsRequire.getIntentMajor().replace("|", ",") + " )";
            List<TCeeEnrollMajorList> tCeeEnrollMajorLists = commonSvc.findListbySql(tCeeEnrollMajorListsSql, TCeeEnrollMajorList.class);
            //        已选意向院校及意向专业
            model.addAttribute("tCeeEnrollMajorLists", tCeeEnrollMajorLists);
        }
        //        String sql = "SELECT * FROM t_cee_enroll_major_list\n" +
//                "WHERE id IN ( " + tCeeApplicationsRequire.getIntentMajor().replace("|", ",") + " )";
//        List<TCeeEnrollMajorList> tCeeEnrollMajorLists = commonSvc.findListbySql(sql, TCeeEnrollMajorList.class);

        if (StringUtils.isNotBlank(tCeeApplicationsRequire.getIntentMajorId())) {
            String tDataMajorsSql = "SELECT * FROM t_data_major\n" +
                    "WHERE major_id IN ( " + tCeeApplicationsRequire.getIntentMajorId().replace("|", ",") + " )";
            List<TDataMajor> tDataMajors = commonSvc.findListbySql(tDataMajorsSql, TDataMajor.class);
            //        已选意向专业类别，如"工科类"
            model.addAttribute("tDataMajors", tDataMajors);
        }

        if (StringUtils.isNotBlank(tCeeApplicationsRequire.getAvoidUniv())) {
            String tCeeEnrollUnivListsSql = "SELECT * FROM t_cee_enroll_univ_list\n" +
                    "WHERE id IN ( " + tCeeApplicationsRequire.getAvoidUniv().replace("|", ",") + " )";
            List<TCeeEnrollUnivList> tCeeEnrollUnivLists = commonSvc.findListbySql(tCeeEnrollUnivListsSql, TCeeEnrollUnivList.class);

//        已选避让院校名
            model.addAttribute("tCeeEnrollUnivLists", tCeeEnrollUnivLists);
        }


        model.addAttribute("tCeeApplicationsRequire", tCeeApplicationsRequire);
        model.addAttribute("tCeeApplications", tCeeApplications);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, "tianzhiyuanqr");
    }

    @RequestMapping("/saveCwb.jspx")
    public void saveCwb(HttpServletRequest request, String applicationId, Integer batchId, ModelMap model, Integer chong, Integer wen, Integer bao) {
        CmsUser user = CmsUtils.getUser(request);
        CmsSite site = CmsUtils.getSite(request);
        String hql = "From TCeeApplicationsRequire where stuUserId='" + user.getId() + "' and applicationId='" + applicationId + "' and batchId='" + batchId + "' ";
        TCeeApplicationsRequire tCeeApplicationsRequire = commonSvc.singleResult(hql);
        tCeeApplicationsRequire.setAdvancedUnivNum(chong);
        tCeeApplicationsRequire.setStableUnivNum(wen);
        tCeeApplicationsRequire.setBackwardUnivNum(bao);
        commonSvc.saveOrUpdate(tCeeApplicationsRequire);
    }


    public void saveZNChoose(Map map, HttpServletRequest request, Integer applicationId) {
        CmsUser user = CmsUtils.getUser(request);
        String hql = "From TCeeApplicationsRequire where stuUserId='" + user.getId() + "' and applicationId='" + map.get("applicationId") + "' and batchId='" + map.get("batchId") + "' ";
        TCeeApplicationsRequire tCeeApplicationsRequire = commonSvc.singleResult(hql);
        if (tCeeApplicationsRequire == null) {
            tCeeApplicationsRequire = new TCeeApplicationsRequire();
        }
        tCeeApplicationsRequire.setStuUserId(user.getId());
        tCeeApplicationsRequire.setApplicationId(applicationId);
        //批次
        Integer batchId = (Integer) map.get("batchId");
        if (batchId != 0) {
            tCeeApplicationsRequire.setBatchId(batchId);
        }
        //院校
//        Integer[] provinceArr = (Integer[]) map.get("univProvince");
        String province = null;

//        String province = StringUtils.join(provinceArr, "|");
        if (StringUtils.isNotBlank(map.get("univProvince").toString())) {
            province = map.get("univProvince").toString().replace(",", "|");
        }
        tCeeApplicationsRequire.setIntentProvinceId(province);
        //院校类型
        String univ_is985 = String.valueOf(map.get("univ_is985"));
        if (StringUtils.isNotBlank(univ_is985)) {
            tCeeApplicationsRequire.setIs985(1);
        }
        String univ_is211 = String.valueOf(map.get("univ_is211"));
        if (StringUtils.isNotBlank(univ_is211)) {
            tCeeApplicationsRequire.setIs211(1);
        }
        String univ_isExcellent = String.valueOf(map.get("univ_isExcellent"));
        if (StringUtils.isNotBlank(univ_isExcellent)) {
            tCeeApplicationsRequire.setIsExcellent(1);
        }
        String univ_isIndependence = String.valueOf(map.get("univ_isIndependence"));
        if (StringUtils.isNotBlank(univ_isIndependence)) {
            tCeeApplicationsRequire.setIsIndependence(1);
        }
        String univ_isFirstRateUniv = String.valueOf(map.get("univ_isFirstRateUniv"));
        String univ_isFirstRateMajor = String.valueOf(map.get("univ_isFirstRateMajor"));
        if (StringUtils.isNotBlank(univ_isFirstRateUniv)) {
            if (StringUtils.isNotBlank(univ_isFirstRateMajor)) {
                tCeeApplicationsRequire.setIsFirstRate(1);
            } else {
                tCeeApplicationsRequire.setIsFirstRate(3);
            }
        } else {
            if (StringUtils.isNotBlank(univ_isFirstRateMajor)) {
                tCeeApplicationsRequire.setIsFirstRate(2);
            } else {
                tCeeApplicationsRequire.setIsFirstRate(0);
            }
        }

        // Alltodo: 2020/2/26 类型全部改变
        //院校类型
//        String[] univSchoolTypeArr = (String[]) map.get("univSchoolType");
//        String univSchoolType = StringUtils.join(univSchoolTypeArr, "|");
        String univSchoolType = null;
        if (StringUtils.isNotBlank(map.get("univSchoolType").toString())) {
            univSchoolType = map.get("univSchoolType").toString().replace(",", "|");

        }
        tCeeApplicationsRequire.setIntentUnivType(univSchoolType);
        //专业
//        String[] majorIdsArr = (String[]) map.get("majorIds");
//        String majorIds = StringUtils.join(majorIdsArr, "|");
        String majorIds = null;
        if (StringUtils.isNotBlank(map.get("majorIds").toString())) {
            majorIds = map.get("majorIds").toString().replace(",", "|");
        }
        tCeeApplicationsRequire.setIntentMajorId(majorIds);
        commonSvc.saveOrUpdate(tCeeApplicationsRequire);
    }

//    @RequestMapping("/zhinengzhiyuantianbao.jspx")
//    public String zhinengzhiyuantianbao(String applicationName, Integer pageNo, HttpServletRequest request, ModelMap model, Integer batchId, Integer[] provinceId,
//                                        String univSchoolType, String yx_cwb, String yx_choose, String univ_is985, String univ_is211, String univ_isDefence, String univ_isExcellent,
//                                        String univ_isIndependence, String univ_isFirstRateUniv, String univ_isFirstRateMajor, String chooseType, Integer setPageNum, String schoolOrMajorName) {
//
//        Map requestMap = new HashMap();
//        requestMap.put("batchId", batchId);
//        requestMap.put("provinceId", provinceId);
//        requestMap.put("univ_is985", univ_is985);
//        requestMap.put("univ_is211", univ_is211);
//        requestMap.put("univ_isDefence", univ_isDefence);
//        requestMap.put("univ_isExcellent", univ_isExcellent);
//        requestMap.put("univ_isIndependence", univ_isIndependence);
//        requestMap.put("univ_isFirstRateUniv", univ_isFirstRateUniv);
//        requestMap.put("univ_isFirstRateMajor", univ_isFirstRateMajor);
//        requestMap.put("univSchoolType", univSchoolType);
//        requestMap.put("yx_cwb", yx_cwb);
//        requestMap.put("yx_choose", yx_choose);
//        requestMap.put("chooseType", chooseType);
//        requestMap.put("schoolOrMajorName", schoolOrMajorName);
//        model.addAttribute("requestMap", requestMap);
//        model.addAttribute("schoolOrMajorName", schoolOrMajorName);
//
//
//        CmsSite site = CmsUtils.getSite(request);
//        CmsUser user = CmsUtils.getUser(request);
//        if (user == null) {
//            return FrontUtils.showLogin(request, model, site);
//        }
//        //判断用户是否有省份选择和文理科选择的权限，有的话能动态选择省份
//        Set<CmsGroup> groups = user.getGroups();
//        Iterator<CmsGroup> groupIt = groups.iterator();
//        String yesOrNo = "false";
//        while (groupIt.hasNext()) {
//            Set<String> groupss = groupIt.next().getPerms();
//            Iterator<String> groupssIt = groupss.iterator();
//            while (groupssIt.hasNext()) {
//                if ("shengfenXuanze:*".equals(groupssIt.next())) {
//                    model.addAttribute("roledId", "3");
//                    yesOrNo = "true";
//                    break;
//                }
//            }
//        }
//
//        //用户信息
//        Map userInfo = new HashMap();
//        userInfo.put("topRoleLevel", user.getTopRoleLevel());
//        userInfo.put("provinceId", user.getAttr().get("province_id"));
//        userInfo.put("majorTypeId", user.getAttr().get("major_type_id"));
//        /*判断是否为管理角色*/
//        boolean isSuper = false;
//        if (user.getRoleIds() != null && user.getRoleIds().length != 0) {
//            isSuper = true;
//        }
//        if (user.isSuper()) {
//            isSuper = true;
//        }
//        /**/
//        userInfo.put("isSuper", isSuper);
//        //省份列表
//        List<TBaseProvince> provinceList = commonSvc.loadAll(TBaseProvince.class);
//        Map<String, String> provinceMap = new HashMap<>();
//        for (TBaseProvince t : provinceList) {
//            provinceMap.put(String.valueOf(t.getProvinceId()), t.getProvinceName());
//        }
//        String proName = "";
//        if (StringUtil.isNotEmpty(provinceId)) {
//
//            //回填数据
//            for (Integer proId : provinceId) {
//                for (TBaseProvince tBaseProvince : provinceList) {
//                    if (proId.equals(tBaseProvince.getProvinceId())) {
//                        proName += tBaseProvince.getProvinceName() + ",";
//                        break;
//                    }
//                }
//            }
//        }
//        model.addAttribute("proName", proName);
//        //批次排名信息
//        List batchInfo = zytbSvc.getBatchInfo(userInfo);
//        // 学校类型
//        List<TDataUniversityType> universityType = commonSvc.loadAll(TDataUniversityType.class);
//        //按分类选专业
//        String mSql = "SELECT * FROM `t_data_major` WHERE parent_Major_Id ='1'";
//        List<Map<String, Object>> majorList = commonSvc.findForJdbc(mSql);
//        model.addAttribute("majorList", majorList);
//
//        Pagination pagination = zytbSvc.getUnivInfo(requestMap, 2019, provinceId, 1, 1, cpn(pageNo), setPageNum);
//
//        //查询用户最大的一个方案id
//        String sql = "SELECT MAX(id) as id FROM t_cee_applications WHERE user_id='" + user.getId() + "'";
//        Map map = commonSvc.findOneForJdbc(sql);
//        //applicationId
//        String applicationId = String.valueOf(map.get("id"));
//        //保存筛选条件
//        requestMap.put("applicationId", applicationId);
//        zytbAct.saveSearch(requestMap, request);
//
//        model.addAttribute("universityType", universityType);
//        model.addAttribute("setPageNum", setPageNum);
//        model.addAttribute("userInfo", userInfo);
//        model.addAttribute("provinceList", provinceList);
//        model.addAttribute("provinceMap", provinceMap);
//        model.addAttribute("pagination", pagination);
//        model.addAttribute("batchInfo", batchInfo);
//        model.addAttribute("majorTypeId", user.getAttr().get("major_type_id"));
//        model.addAttribute("provinceId", user.getAttr().get("province_id"));
//        model.addAttribute("applicationId", applicationId);
//        FrontUtils.frontData(request, model, site);
////        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, APPLICATIONS_RESULT);
//        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, "zhinengzhiyuantianbao");
//    }
//

    public void saveSearch(Map map, HttpServletRequest request) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        String sql = "SELECT * FROM t_cee_applications_require WHERE stu_user_id='" + user.getId() + "' AND application_id='" + map.get("applicationId") + "' AND batch_id='" + map.get("batchId") + "'";
        List<TCeeApplicationsRequire> list = commonSvc.findListbySql(sql, TCeeApplicationsRequire.class);
        String major = "";
        int advanced_univ_num = 0;
        int stable_univ_num = 0;
        int backward_univ_num = 0;
        String intent_univ = "";
        String avoid_univ = "";
        String major_id = "";
        if (list.size() > 0) {
            major = list.get(0).getIntentMajorId();
            advanced_univ_num = list.get(0).getAdvancedUnivNum();
            stable_univ_num = list.get(0).getStableUnivNum();
            backward_univ_num = list.get(0).getBackwardUnivNum();
            intent_univ = list.get(0).getIntentUniv();
            avoid_univ = list.get(0).getAvoidUniv();
            String deleteSql = "delete from t_cee_applications_require where stu_user_id='" + user.getId() + "' and application_id='" + map.get("applicationId") + "' and batch_id = '" + map.get("batchId") + "'";
            commonSvc.executeSql(deleteSql);
        }
        TCeeApplicationsRequire tCeeApplicationsRequire = new TCeeApplicationsRequire();
        tCeeApplicationsRequire.setStuUserId(user.getId());
        tCeeApplicationsRequire.setApplicationId(Integer.valueOf(String.valueOf(map.get("applicationId"))));
        tCeeApplicationsRequire.setBatchId(Integer.valueOf((String) map.get("batchId")));
        tCeeApplicationsRequire.setIntentProvinceId(String.valueOf(map.get("provinceId")).replaceAll(",", "\\|"));
        tCeeApplicationsRequire.setIntentUnivType(String.valueOf(map.get("univSchoolType")).replaceAll(",", "\\|"));
        tCeeApplicationsRequire.setIs211(Integer.valueOf(String.valueOf(map.get("univ_is211"))));
        tCeeApplicationsRequire.setIs985(Integer.valueOf(String.valueOf(map.get("univ_is985"))));
        tCeeApplicationsRequire.setIsExcellent(Integer.valueOf(String.valueOf(map.get("univ_isExcellent"))));
        tCeeApplicationsRequire.setIsIndependence(Integer.valueOf(String.valueOf(map.get("univ_isIndependence"))));
        if (StringUtil.isNotEmpty(String.valueOf(map.get("univ_isFirstRateUniv")))) {
            if (StringUtil.isNotEmpty(String.valueOf(map.get("univ_isFirstRateUniv")))) {
                tCeeApplicationsRequire.setIsFirstRate(1);
            } else {
                tCeeApplicationsRequire.setIsFirstRate(0);
            }
        } else {
            if (StringUtil.isNotEmpty(String.valueOf(map.get("univ_isFirstRateUniv")))) {
                tCeeApplicationsRequire.setIsFirstRate(2);
            } else {
                tCeeApplicationsRequire.setIsFirstRate(0);
            }
        }
        tCeeApplicationsRequire.setIntentMajorId(String.valueOf(map.get("majorId")));
        tCeeApplicationsRequire.setAdvancedUnivNum(advanced_univ_num);
        tCeeApplicationsRequire.setStableUnivNum(stable_univ_num);
        tCeeApplicationsRequire.setBackwardUnivNum(backward_univ_num);
        tCeeApplicationsRequire.setIntentUniv(intent_univ);
        tCeeApplicationsRequire.setAvoidUniv(avoid_univ);
        commonSvc.save(tCeeApplicationsRequire);
    }


    /**
     * 查询分数和排名
     *
     * @return
     */
    @RequestMapping(value = "/batch.jspx")
    public void getBatchInfo(HttpServletRequest request, HttpServletResponse response) {
        CmsUser user = CmsUtils.getUser(request);
        Map<String, String> param = new HashMap<>();
        param.put("batchId", request.getParameter("batchId"));
        param.put("provinceId", request.getParameter("provinceId"));
        param.put("majorTypeId", request.getParameter("majorTypeId"));
        param.put("gap", request.getParameter("gap"));
        if (StringUtils.isEmpty(param.get("provinceId"))) {
            param.put("provinceId", user.getAttr().get("province_id"));
        }
        if (StringUtils.isEmpty(param.get("majorTypeId"))) {
            param.put("majorTypeId", user.getAttr().get("major_type_id"));
        }
        if (StringUtils.isEmpty(param.get("gap"))) {
            param.put("gap", "0");
        }
        List list = zytbSvc.getScoreAndRankFromBatch(param);
        try {
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询一分一段
     *
     * @param score
     * @param request
     * @param response
     */
    @RequestMapping("/rank.jspx")
    public void getRankByScore(String score, String provinceId, String rank, String majorTypeId, HttpServletRequest request, HttpServletResponse response) {
        CmsUser user = CmsUtils.getUser(request);
        if (StringUtils.isEmpty(provinceId)) {
            provinceId = user.getAttr().get("province_id");
        }
        if (StringUtils.isEmpty(majorTypeId)) {
            majorTypeId = user.getAttr().get("major_type_id");
        }
        Integer year = commonSvc.singleResult("SELECT dataScoreYear FROM TBaseProvince WHERE province_id =" + provinceId + " ");
        String hql = "from TCeeScoreRank where year=" + year +
                " and provinceId=" + provinceId + " and majorTypeId=" + majorTypeId;
        if (StringUtils.isNotBlank(score)) {
            hql += " and score=" + score;
        }
        if (StringUtils.isNotBlank(rank)) {
            hql += " and rank >=" + rank + "order by score desc";
        }
        List<TCeeScoreRank> list = commonSvc.findByQueryString(hql);
        try {
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(list.size() > 0 ? list.get(0) : ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询方案详细信息
     *
     * @param applicationId 方案ID
     * @param request       request
     * @param model         model
     * @return string
     */
    @RequestMapping("/voluntary.jspx")
    public String getProjectDetail(Integer applicationId, String univOrderStrZs, String majorOrderStrZs, String univOrderStrBx, String majorOrderStrBx, Integer batchIdToUpdate, HttpServletRequest request, ModelMap model, HttpServletResponse response) throws UnsupportedEncodingException {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        String ourPutRequest = request.getRequestURI();
        System.out.printf(ourPutRequest);
        Set<CmsGroup> groups = user.getGroups();
        Iterator<CmsGroup> groupIt = groups.iterator();
        Iterator<CmsGroup> groupIt2 = groups.iterator();
        String haveTianZhiYuan = "false";
        String havePingGu = "false";
        model.addAttribute("haveTianZhiYuan", haveTianZhiYuan);
        while (groupIt.hasNext()) {
            Set<String> groupss = groupIt.next().getPerms();
            Iterator<String> groupssIt = groupss.iterator();
            while (groupssIt.hasNext()) {
                if ("TianZhiYuan:*".equals(groupssIt.next())) {
                    haveTianZhiYuan = "true";
                    model.addAttribute("haveTianZhiYuan", haveTianZhiYuan);
                    break;
                }
            }

        }
        model.addAttribute("havePingGu", havePingGu);
        while (groupIt2.hasNext()) {
            Set<String> groupss2 = groupIt2.next().getPerms();
            Iterator<String> groupssIt2 = groupss2.iterator();

            while (groupssIt2.hasNext()) {
                if ("pinggu:*".equals(groupssIt2.next())) {
                    havePingGu = "true";
                    model.addAttribute("havePingGu", havePingGu);
                    break;
                }
            }
        }

        //查询方案信息
        TCeeApplications applicationsInfo = commonSvc.findUniqueByProperty(TCeeApplications.class, "id", applicationId);
        if (applicationsInfo == null) {
            return "";
        }


        if (batchIdToUpdate != null) {
            TCeeBatch tCeeBatch = smartZytbSvc.getBatch(applicationsInfo.getYear(), applicationsInfo.getProvinceId(), applicationsInfo.getMajorTypeId(), batchIdToUpdate);
//            正式志愿
            if (StringUtils.isNotBlank(univOrderStrZs)) {
                String[] univOrderObjectZs = univOrderStrZs.split("\\|");
                int dd = 1;
                for (int i = 0; i < univOrderObjectZs.length; i++) {
                    String ifNullObject = univOrderObjectZs[i].substring(1, univOrderObjectZs[i].length() - 1);
                    int cc = 1;
                    if (!"".equals(ifNullObject)) {
                        String[] univOrderListZs = ifNullObject.split(",");
                        if (univOrderListZs != null) {
                            String univOrder = "'" + univOrderListZs[0] + "' , '" + univOrderListZs[1] + "' , '" + univOrderListZs[2].replace("&＃40;", "(").replace("&＃41;", ")") + "' ";
                            StringBuffer findSql = new StringBuffer();
                            findSql.append("SELECT  * FROM t_cee_applications_detail \n" +
                                    "WHERE application_id = '" + applicationId + "' \n" +
                                    "AND batch_id = " + batchIdToUpdate + "\n" +
                                    "AND (univ_id,univ_code,univ_name) = (" + univOrder + ") \n");


                            if (StringUtils.isNotBlank(majorOrderStrZs)) {
                                String majorOrderObjectZs = majorOrderStrZs.replace("[", "'").replace("]", "'").replace("|", ",");
                                String majrOrderZs = majorOrderObjectZs.substring(0, majorOrderObjectZs.length() - 1);
                                findSql.append("AND id IN ( " + majrOrderZs + " )\n" +
                                        "ORDER BY FIELD(id," + majrOrderZs + ") ");
                            }

                            List<TCeeApplicationsDetail> tCeeApplicationsDetailsToUpdate = commonSvc.findListbySql(findSql.toString(), TCeeApplicationsDetail.class);

                            for (int j = 0; j < tCeeApplicationsDetailsToUpdate.size(); j++) {
//                             调整院校顺序
                                tCeeApplicationsDetailsToUpdate.get(j).setUnivOrder(i + 1);
//                            调整专业顺序
                                tCeeApplicationsDetailsToUpdate.get(j).setMajorOrder(j + 1);
//                            设置为备选志愿
                                tCeeApplicationsDetailsToUpdate.get(j).setIsFormal(0);
                                commonSvc.updateEntitie(tCeeApplicationsDetailsToUpdate.get(j));
                            }

                        }
                    }
                    //        清空之前的数据
                    String deleteNullDetailSql = "DELETE FROM t_cee_applications_detail\n" +
                            "WHERE application_id = " + applicationId + " \n" +
                            "AND batch_id = " + batchIdToUpdate + " \n" +
                            "AND is_formal = 0 \n" +
                            "AND univ_name IS NULL ";
                    commonSvc.executeSql(deleteNullDetailSql);
                    zytbAct.findAndCreateDetail(applicationId.toString(), batchIdToUpdate, tCeeBatch, applicationsInfo, 0);
                }
            }

            if (StringUtils.isNotBlank(univOrderStrBx)) {
                String[] univOrderObjectBx = univOrderStrBx.split("\\|");
                for (int i = 0; i < univOrderObjectBx.length; i++) {
                    String ifNullObject = univOrderObjectBx[i].substring(1, univOrderObjectBx[i].length() - 1);
                    if (!"".equals(ifNullObject)) {
                        String[] univOrderListBx = ifNullObject.split(",");
                        if (univOrderListBx != null) {
                            String univOrder = "'" + univOrderListBx[0] + "' , '" + univOrderListBx[1] + "' , '" + univOrderListBx[2].replace("&＃40;", "(").replace("&＃41;", ")") + "' ";
                            StringBuffer findSql = new StringBuffer();
                            findSql.append("SELECT  * FROM t_cee_applications_detail \n" +
                                    "WHERE application_id = '" + applicationId + "' \n" +
                                    "AND batch_id = " + batchIdToUpdate + "\n" +
                                    "AND (univ_id,univ_code,univ_name) = (" + univOrder + ") \n");


                            if (StringUtils.isNotBlank(majorOrderStrBx)) {
                                String majorOrderObjectBx = majorOrderStrBx.replace("[", "'").replace("]", "'").replace("|", ",");
                                String majrOrderBx = majorOrderObjectBx.substring(0, majorOrderObjectBx.length() - 1);
                                findSql.append("AND id IN ( " + majrOrderBx + " )\n" +
                                        "ORDER BY FIELD(id," + majrOrderBx + ") ");
                            }

                            List<TCeeApplicationsDetail> tCeeApplicationsDetailsToUpdate = commonSvc.findListbySql(findSql.toString(), TCeeApplicationsDetail.class);

                            for (int j = 0; j < tCeeApplicationsDetailsToUpdate.size(); j++) {
//                             调整院校顺序
                                tCeeApplicationsDetailsToUpdate.get(j).setUnivOrder(i + 1);
//                            调整专业顺序
                                tCeeApplicationsDetailsToUpdate.get(j).setMajorOrder(j + 1);
//                            设置为备选志愿
                                tCeeApplicationsDetailsToUpdate.get(j).setIsFormal(1);
                                commonSvc.updateEntitie(tCeeApplicationsDetailsToUpdate.get(j));
                            }

                        }
                    }
                    //        清空之前的数据
                    String deleteNullDetailSql = "DELETE FROM t_cee_applications_detail\n" +
                            "WHERE application_id = " + applicationId + " \n" +
                            "AND batch_id = " + batchIdToUpdate + " \n" +
                            "AND is_formal = 1 \n" +
                            "AND univ_name IS NULL ";
                    commonSvc.executeSql(deleteNullDetailSql);
                    zytbAct.findAndCreateDetail(applicationId.toString(), batchIdToUpdate, tCeeBatch, applicationsInfo, 1);
                }
            }
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
            String detailId = String.valueOf(tcee.getId());
            String batchIds = String.valueOf(tcee.getBatchId());
            String univId = tcee.getUnivId();
            String univCode = String.valueOf(tcee.getUnivCode()); //院校code
            String univName = String.valueOf(tcee.getUnivName()); //院校name
            String isObey = String.valueOf(tcee.getIsObey()); //是否服从
            String univOrder = String.valueOf(tcee.getUnivOrder());//院校顺序
            String isFormal = String.valueOf(tcee.getIsFormal());//是否为备选专业
            String fanYueId = String.valueOf(tcee.getId());//ID
            String univMajorGroup = String.valueOf(tcee.getUnivMajorGroup());
            String univTestRemark = String.valueOf(tcee.getUnivTestRemark());
            Map<String, String> m = new HashMap();
            m.put("detailId", detailId);
            m.put("univCode", univCode);
            m.put("univName", univName);
            m.put("isObey", isObey);
            m.put("univOrder", univOrder);
            m.put("isFormal", isFormal);
            m.put("batchId", batchIds);
            m.put("univId", univId);
            m.put("univMajorGroup", univMajorGroup);
            m.put("univTestRemark", univTestRemark);

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
            String detailId = String.valueOf(app.getId());
            String batchId = String.valueOf(app.getBatchId()); //批次ID
            String univId = String.valueOf(app.getUnivId()); //院校code
            String univCode = String.valueOf(app.getUnivCode()); //院校code
            String univName = String.valueOf(app.getUnivName()); //院校name
            String isObey = String.valueOf(app.getIsObey()); //是否服从
            String univOrder = String.valueOf(app.getUnivOrder());//院校顺序
            String isFormal = String.valueOf(app.getIsFormal());//是否为备选专业
            String fanYueId = String.valueOf(app.getId());//ID
            String univMajorGroup = String.valueOf(app.getUnivMajorGroup());
            String univTestRemark = String.valueOf(app.getUnivTestRemark());
            Map<String, String> m = new HashMap();
            m.put("detailId", detailId);
            m.put("univId", univId);
            m.put("univCode", univCode);
            m.put("univName", univName);
            m.put("isObey", isObey);
            m.put("univOrder", univOrder);
            m.put("isFormal", isFormal);
            m.put("batchId", batchId);
            m.put("univMajorGroup", univMajorGroup);
            m.put("univTestRemark", univTestRemark);
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
            return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, "voluntary_zj");
        }else if(applicationsInfo.getProvinceId() == 1 || applicationsInfo.getProvinceId() == 16){
            return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, "monitianbaobiao_bj");
        }
        else {
            return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, APPLICATIONS_DETAIL_RESULT);
        }
    }


    @RequestMapping("/updateObey.jspx")
    public void updateObey(String detailId,String isObey  )  {

        TCeeApplicationsDetail tCeeApplicationsDetail = commonSvc.get(TCeeApplicationsDetail.class,Integer.valueOf(detailId));
        tCeeApplicationsDetail.setIsObey(isObey);
        commonSvc.updateEntitie(tCeeApplicationsDetail);

//        Map<String, String> resultMap = new HashMap<>();
//        resultMap.put("msg", "提交成功!");
//        response.setCharacterEncoding("utf-8");
//        response.getWriter().write(JSON.toJSONString(resultMap));

    }


    @RequestMapping("/updateCWB.jspx")
    public void updateCWB(String requireId,Integer cNum,Integer wNum,Integer bNum  )  {

        TCeeApplicationsRequire tCeeApplicationsRequire = commonSvc.get(TCeeApplicationsRequire.class,Integer.valueOf(requireId));

        if(cNum!=null){
            tCeeApplicationsRequire.setAdvancedUnivNum(cNum);
        }
        if(wNum!=null){
            tCeeApplicationsRequire.setStableUnivNum(wNum);
        }
        if(bNum!=null){
            tCeeApplicationsRequire.setBackwardUnivNum(bNum);
        }

        commonSvc.updateEntitie(tCeeApplicationsRequire);

//        Map<String, String> resultMap = new HashMap<>();
//        resultMap.put("msg", "提交成功!");
//        response.setCharacterEncoding("utf-8");
//        response.getWriter().write(JSON.toJSONString(resultMap));

    }



    /**
     * 删除方案
     *
     * @param applicationId 方案ID
     * @param request       request
     * @param model         model
     * @return string
     */
    @RequiresPermissions("shanchu:*")
    @RequestMapping("/removeProject.jspx")
    public void removeProject(Integer applicationId, HttpServletRequest request, ModelMap
            model, HttpServletResponse response) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        commonSvc.deleteEntityById(TCeeApplications.class, applicationId);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("msg", "删除成功");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(resultMap));
    }

    /**
     * 保存志愿方案详情
     *
     * @param apps
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/updateProjectDetail.jspx")
    public String saveProjectDetail(String apps, HttpServletRequest request, ModelMap model, HttpServletResponse
            response) {
        CmsSite site = CmsUtils.getSite(request);
        //bean = zytbSvc.saveProjectDetail(bean);
        //zytbSvc.update(apps);
        List<TCeeApplicationsDetail> beans = JSON.parseArray(apps, TCeeApplicationsDetail.class);
        for (TCeeApplicationsDetail bean :
                beans) {
            String sql = "update t_cee_applications_detail set univ_order=" + bean.getUnivOrder() +
                    ", major_order=" + bean.getMajorOrder() +
                    ", is_obey=" + bean.getIsObey() +
                    "  where id=" + bean.getId();
            commonSvc.updateBySqlString(sql);
        }
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    @RequestMapping("/removeUniv.jspx")
    public void deleteUniv(String applicationId, Integer batchId, String univId, String univCode, String univName, Integer isFormal) {

        if (StringUtils.isNotBlank(applicationId) && StringUtils.isNotBlank(univCode) && !"null".equals(univCode)) {

            String query = "SELECT * FROM t_cee_applications_detail \n" +
                    "WHERE application_id = '" + applicationId + "' \n" +
                    "AND batch_id = " + batchId + "\n" +
                    "AND (univ_id,univ_code,univ_name ) IN ( ('" + univId + "','" + univCode + "','" + univName + "') )";
            List<TCeeApplicationsDetail> tceeList = commonSvc.findListbySql(query, TCeeApplicationsDetail.class);

            int application_id = tceeList.get(0).getApplicationId();//方案ID
            int year = tceeList.get(0).getYear();
            int province_id = tceeList.get(0).getProvinceId();//省份
            int major_type_id = tceeList.get(0).getMajorTypeId();//文理科
            int batch_id = tceeList.get(0).getBatchId();//批次
            int univ_order = tceeList.get(0).getUnivOrder();//顺序
            int is_formal = tceeList.get(0).getIsFormal();//是否备选
            commonSvc.deleteAllEntitie(tceeList);

            TCeeApplicationsDetail tCeeApplicationsDetail = new TCeeApplicationsDetail();
            tCeeApplicationsDetail.setApplicationId(application_id);
            tCeeApplicationsDetail.setYear(year);
            tCeeApplicationsDetail.setProvinceId(province_id);
            tCeeApplicationsDetail.setMajorTypeId(major_type_id);
            tCeeApplicationsDetail.setBatchId(batchId);
            tCeeApplicationsDetail.setUnivOrder(univ_order);
            tCeeApplicationsDetail.setIsFormal(is_formal);
            commonSvc.save(tCeeApplicationsDetail);
        }
    }

    /**
     * 保存志愿方案
     *
     * @param bean
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/saveProject.jspx")
    public String saveProject(TCeeApplications bean, HttpServletRequest request, ModelMap model, String
            appType, HttpServletResponse response) throws IOException {
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
        Integer aa = user.getTopRoleLevel();

        int p = bean.getProvinceId();
        int m = bean.getMajorTypeId();
        if (p == 0 || m == 0) {
            bean.setProvinceId(Integer.parseInt(userAttr.get("province_id"))); //省份ID
            bean.setMajorTypeId(Integer.parseInt(userAttr.get("major_type_id"))); //文理科

        }
        int pId = bean.getProvinceId();

        Set<CmsGroup> groups = user.getGroups();
        CmsGroup group1 = cmsGroupMng.findById(6);
        CmsGroup group2 = cmsGroupMng.findById(6);

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
                            for (int j = 1; j <= extraNum; j++) {
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
            return "redirect:project.jspx";
        } else {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = null;
            out = response.getWriter();
            out.println("<script language=javascript>alert('当前为限制新建方案时期，请在已建方案中更改方案内容。');window.location='project.jspx'</script>");
            return "";
        }

    }


    /**
     * 志愿填报表
     *
     * @param univ
     * @param major
     * @param city
     * @param univProvince
     * @param univType
     * @param is985
     * @param is211
     * @param isDefence
     * @param isExcel
     * @param isIndepen
     * @param isFirstUniv
     * @param isFirstMajor
     * @param planId
     * @param batchId
     * @param pageNo
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/beginFillIn.jspx")
    public String beginFillIn(Integer tabs, String univ, String major, String city, Integer[] univProvince, String
            univType, Integer is985, Integer is211,
                              Integer isDefence, Integer isExcel, Integer isIndepen, Integer isFirstUniv, Integer isFirstMajor,
                              Integer planId, Integer batchId, String type, Integer sc, Integer pageNo, HttpServletRequest
                                      request, Integer setPageNum,
                              HttpServletResponse response, ModelMap model, String fenlei, String majorName, String univSchoolType) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        Map requestMap = new HashMap();
//        requestMap.put("univOrMajor", univOrMajor);
        requestMap.put("univ", univ);
        requestMap.put("major", major);
        requestMap.put("city", city);
        requestMap.put("univProvince", univProvince);
        requestMap.put("univType", univType);
        requestMap.put("is985", is985);
        requestMap.put("is211", is211);
        requestMap.put("isDefence", isDefence);
        requestMap.put("isExcel", isExcel);
        requestMap.put("isIndepen", isIndepen);
        requestMap.put("isFirstUniv", isFirstUniv);
        requestMap.put("isFirstMajor", isFirstMajor);
        requestMap.put("fenlei", fenlei);
        requestMap.put("batchId", batchId);
        requestMap.put("univSchoolType", univSchoolType);
        List<TBaseProvince> provinceList = commonSvc.loadAll(TBaseProvince.class);
        model.addAttribute("provinceList", provinceList);

        if (setPageNum == null || setPageNum == 0) {
            setPageNum = 20;
        }

        String proName = "";
        if (StringUtil.isNotEmpty(univProvince)) {

            //回填数据
            for (Integer proId : univProvince) {
                for (TBaseProvince tBaseProvince : provinceList) {
                    if (proId.equals(tBaseProvince.getProvinceId())) {
                        proName += tBaseProvince.getProvinceName() + ",";
                        break;
                    }
                }
            }
        }
        model.addAttribute("proName", proName);

        //根据方案id获取方案信息
        TCeeApplications tCeeApplications = commonSvc.get(TCeeApplications.class, planId);
        //根据方案id及批次id查询已报学校
        List univDetailList = commonSvc.singleResultList("SELECT DISTINCT univId FROM TCeeApplicationsDetail WHERE  applicationId=" + planId + " and batchId=" + batchId);
        //username
        String username = tCeeApplications.getUserName();
        model.addAttribute("userName", username);
        //省份id
        Integer provinceId = tCeeApplications.getProvinceId();
        model.addAttribute("provinceId", provinceId);
        //文理科
        Integer majorType = tCeeApplications.getMajorTypeId();
        String subjects = tCeeApplications.getSubjects();
        String subjectsLevel = tCeeApplications.getSubjectsLevel();
        model.addAttribute("majorType", majorType);
        model.addAttribute("subjects", subjects);
        model.addAttribute("subjectsLevel", subjectsLevel);
        //年
//        Integer year = commonSvc.singleResult("select max(year) from TCeeEnrollUnivList where provinceId=" + provinceId + "and majorTypeId=" + majorType + " and batchId=" + batchId);
        Integer year = commonSvc.singleResult("SELECT dataBatchYear FROM TBaseProvince WHERE province_id =" + provinceId + " ");

        Integer enrollYear = commonSvc.singleResult("SELECT dataEnrollYear FROM TBaseProvince WHERE province_id =" + provinceId + " ");
        Integer planYear = commonSvc.singleResult("SELECT dataPlanYear FROM TBaseProvince WHERE province_id =" + provinceId + " ");

        model.addAttribute("enrollYear", enrollYear);
        model.addAttribute("planYear", planYear);

        if (year == null) {
            year = Calendar.getInstance().get(Calendar.YEAR);
        }
        //成绩
        Integer score = tCeeApplications.getScore();
        model.addAttribute("score", score);//分数
        //排名
        Integer rank = tCeeApplications.getRank();
        model.addAttribute("rank", rank);//排名
        //选测等级
        Integer level = 11;
        if (tCeeApplications.getSubjectsLevel() != null) {
            level = Integer.parseInt(tCeeApplications.getSubjectsLevel().replace(",", ""));
        }
        model.addAttribute("level", level);
        // 学校类型
        List<TDataUniversityType> universityType = commonSvc.loadAll(TDataUniversityType.class);
        model.addAttribute("universityType", universityType);
        //查询专业信息
        List<TDataMajor> majorList = commonSvc.findByProperty(TDataMajor.class, "parentMajorId", "1");
        model.addAttribute("majorList", majorList);
        //查询批次信息

        List<TCeeBatch> batchList = zytbSvc.getBatchList(year, provinceId, majorType);
        model.addAttribute("batchList", batchList);
        List<TCeeBatch> batchNum = commonSvc.findByQueryString("FROM TCeeBatch where year =" + year + " and provinceId =" + provinceId + " and majorTypeId =" + majorType + " and batchId=" + batchId);
        //查询热门专业
//        changePlanNum

        Integer year3 = commonSvc.singleResult("SELECT dataPlanYear FROM TBaseProvince WHERE province_id =" + provinceId + " ");
        //根据排名过滤 查询符合的院校
        if (StringUtils.isNotBlank(type)) {
            Integer lowScore = 0;
            Integer highScore = 0;
            switch (type) {
                case "1": //稳
                    lowScore = score - sc;
                    highScore = score + sc;
                    break;
                case "2": //冲
                    lowScore = score;
                    highScore = score + sc;
                    break;
                case "3": //保
                    lowScore = score - sc;
                    highScore = score;
                    break;
            }

            Integer year1 = commonSvc.singleResult("SELECT dataScoreYear FROM TBaseProvince WHERE province_id =" + provinceId + " ");
            String hql = "from TCeeScoreRank where " + "(score = " + lowScore + " or score=" + highScore + ") and year = "
                    + year1 + " and provinceId=" + provinceId + " and majorTypeId=" + majorType;
            List<TCeeScoreRank> rankList = commonSvc.findByQueryString(hql);

            hql = "select distinct univId from TCeeEnrollUnivList where province_id=" + provinceId + " and year = " + year3 + " and majorTypeId="
                    + majorType + " and rankScoreLow1 BETWEEN " + rankList.get(0).getRank() + " and " + rankList.get(1).getRank();
            List univIds = commonSvc.findByQueryString(hql);
            requestMap.put("univIdFromScore", univIds);
            requestMap.put("type", type);
            requestMap.put("sc", sc);
        }

        //查询已选专业的院校
        if (StringUtils.isNotBlank(majorName)) {
            String sql = "select distinct univId from TCeeEnrollMajorList where dataType=1";
            String[] majorNames = majorName.split(",");
            String condition = "";
            for (int i = 0; i < majorNames.length; i++) {
                if (i == 0) {
                    condition += " and (";
                } else {
                    condition += " or";
                }
                condition += " majorName like '%" + majorNames[i] + "%'";
                if (i == majorNames.length - 1) {
                    condition += " )";
                }
            }
            List univIds = commonSvc.findByQueryString(sql + condition);
            requestMap.put("univIdFromMajor", univIds);
        }

        //查询已报志愿数量
        List univNumList = zytbSvc.getUnivNum(tCeeApplications.getId());
        Map univNumMap = new HashMap();
        for (Object obj : univNumList) {
            Object[] arr = (Object[]) obj;
            univNumMap.put(String.valueOf(arr[0]), String.valueOf(arr[1]));
        }

        //查询方案详细
        List<TCeeApplicationsDetail> applicationsDetailList = zytbSvc.queryProjectDetailList(tCeeApplications.getId());
        Map<String, List<Map<String, String>>> batchMap = new HashMap<>();  //存批次、院校对应信息
        Map<String, Map<String, List<TCeeApplicationsDetail>>> appMap = new HashMap(); //存院校、专业对应信息
        for (TCeeApplicationsDetail app : applicationsDetailList) {
            String bId = String.valueOf(app.getBatchId()); //批次ID
            String univCode = String.valueOf(app.getUnivCode()); //院校code
            String univName = String.valueOf(app.getUnivName()); //院校name
            String isObey = String.valueOf(app.getIsObey()); //是否服从
            String univOrder = String.valueOf(app.getUnivOrder());//院校顺序
            String isFormal = String.valueOf(app.getIsFormal());//是否为备选专业
            Map<String, String> m = new HashMap();
            m.put("univCode", univCode);
            m.put("univName", univName);
            m.put("isObey", isObey);
            m.put("univOrder", univOrder);
            m.put("isFormal", isFormal);
            if (!batchMap.containsKey(bId)) {
                batchMap.put(bId, new ArrayList<Map<String, String>>());
            }
            if (!batchMap.get(bId).contains(m)) {
                batchMap.get(bId).add(m);
            }
            if (!appMap.containsKey(bId)) {
                appMap.put(bId, new HashMap<String, List<TCeeApplicationsDetail>>());
            }
            if (!appMap.get(bId).containsKey(univCode)) {
                appMap.get(bId).put(univCode, new ArrayList<TCeeApplicationsDetail>());
            }
            appMap.get(bId).get(univCode).add(app);
        }
        List<TCeeApplicationsDetail> applicationsDetailList1 = commonSvc.findByQueryString("FROM TCeeApplicationsDetail WHERE applicationId = '" + tCeeApplications.getId() + "' AND isFormal = 1 order by  isFormal,univOrder,majorOrder");
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

        //志愿筛选
        Pagination pagination = null;

        if (tabs == null || tabs == 1) {
            //全部院校信息
            tabs = 1;
            pagination = zytbSvc.getUnivInfo(requestMap, year3, provinceId, majorType, batchId, cpn(pageNo), setPageNum);
        } else if (tabs == 2) {
//            TCeeApplicationsDetail
            //已选志愿
            List univList = commonSvc.singleResultList("SELECT DISTINCT univId FROM TCeeApplicationsDetail WHERE  applicationId=" + planId + " and batchId=" + batchId + " and univId IS NOT NULL");
            pagination = zytbSvc.getUnivList(requestMap, univList, cpn(pageNo), setPageNum, "Y", year3, provinceId, majorType, batchId);
        } else if (tabs == 3) {
            //未选志愿
            List univList = commonSvc.singleResultList("SELECT DISTINCT univId FROM TCeeApplicationsDetail WHERE  applicationId=" + planId + " and batchId=" + batchId + " and univId IS NOT NULL");
            pagination = zytbSvc.getUnivList(requestMap, univList, cpn(pageNo), setPageNum, "N", year3, provinceId, majorType, batchId);
        } else if (tabs == 4) {
            //有资格
            pagination = zytbSvc.getLevelUnivList(requestMap, "Y", level, year3, provinceId, majorType, batchId, cpn(pageNo), setPageNum);
        } else if (tabs == 5) {
            //无资格
            pagination = zytbSvc.getLevelUnivList(requestMap, "N", level, year3, provinceId, majorType, batchId, cpn(pageNo), setPageNum);
        }


//        model.addAttribute("num",batchNum.get(0).getUnivNum());
//        model.addAttribute("univOrMajor", univOrMajor);
        model.addAttribute("setPageNum", setPageNum);
        model.addAttribute("univ", univ);
        model.addAttribute("major", major);
        model.addAttribute("pagination", pagination);
        model.addAttribute("univDetailList", univDetailList);
        model.addAttribute("tabs", tabs);
        model.addAttribute("univNumMap", univNumMap);
        model.addAttribute("batchMap", batchMap);
        model.addAttribute("appMap", appMap);
        model.addAttribute("batchMaps", batchMaps);
        model.addAttribute("appMaps", appMaps);
        model.addAttribute("batchId", batchId);//批次id
        model.addAttribute("planId", planId);//方案id
        //去除map中为空参数
        Iterator<Map.Entry<String, String>> it = requestMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            String value = String.valueOf(entry.getValue());
            String key = entry.getKey();
            if (StringUtils.isEmpty(value) || value == "null") {
                it.remove();
            }
        }
        model.addAttribute("requestMap", requestMap);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, "beginFillIn");
    }

    /**
     * 查询majorId 下所有层级
     *
     * @param majorId
     * @param response
     */
    @RequestMapping("/major.jspx")
    public void getMajor(String majorId, HttpServletResponse response) {
        List list = new ArrayList();
        List<Map> majorList = zytbSvc.getMajorList(majorId);
        for (Map major : majorList) {
            String mjId = major.get("majorId").toString();
            String mjName = major.get("majorName").toString();
            Map map = new HashMap();
            map.put("key", mjId);
            map.put("title", mjName);
            map.put("list", zytbSvc.getMajorList(mjId));
            list.add(map);
        }
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(JSON.toJSONString(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否还可以报志愿
     *
     * @param planId
     * @param batchId
     * @param univListId
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/isReportUniv.jspx")
    public void isReportUniv(Integer planId, Integer batchId, Integer isFormal, Integer univListId, Integer
            replaceCode, HttpServletRequest request, HttpServletResponse response, ModelMap model, String major) throws
            UnsupportedEncodingException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        String majorName = major;
        //根据方案id获取方案信息
        TCeeApplications tCeeApplications = commonSvc.get(TCeeApplications.class, planId);
        //省份id
        Integer provinceId = tCeeApplications.getProvinceId();
        //文理科
        Integer majorType = tCeeApplications.getMajorTypeId();

//        Integer year = commonSvc.singleResult("select max(year) from TCeeEnrollMajorList where provinceId=" + provinceId + "and majorTypeId=" + majorType + " and batchId=" + batchId);

        Integer year = commonSvc.singleResult("SELECT dataBatchYear FROM TBaseProvince WHERE province_id =" + provinceId + " ");
        //查询当前批次信息可报院校数
        List<TCeeBatch> batchList = zytbSvc.getBatchList(year, provinceId, majorType);
        Integer univNum = 0;
        Integer majorNum = 0;
        for (TCeeBatch tCeeBatch : batchList) {
            if (batchId.equals(tCeeBatch.getBatchId())) {
                univNum = tCeeBatch.getUnivNum();
                majorNum = tCeeBatch.getMajorNum();
                break;
            }
        }
        //已报院校数
        List ybUnivNum = commonSvc.singleResultList("SELECT DISTINCT univCode FROM TCeeApplicationsDetail WHERE  applicationId="
                + planId + " and batchId=" + batchId);

        //判断是否还可以继续填报
        Map<String, Object> returnMap = new HashMap<>();
        if (ybUnivNum.size() < univNum || replaceCode != null) {
            returnMap.put("success", true);
            String url = "";
            if (StringUtil.isNotEmpty(majorName)) {
                url = "remote.jspx?planId=" + planId + "&batchId=" + batchId + "&univListId=" + univListId + "&majorNum=" + majorNum + "&majorName=" + java.net.URLEncoder.encode(majorName, "utf-8") + "&replaceCode=" + replaceCode + "&isFormal=" + isFormal;
            } else {
                url = "remote.jspx?planId=" + planId + "&batchId=" + batchId + "&univListId=" + univListId + "&majorNum=" + majorNum + "&replaceCode=" + replaceCode + "&isFormal=" + isFormal;
            }
            returnMap.put("msg", url);
            String sql = "SELECT a.univ_name univName,a.year,b.batch_name batchName,c.province_name provinceName " +
                    "FROM t_cee_enroll_univ_list a,t_cee_batch b,t_base_province c " +
                    "where a.id = " + univListId + " and a.year=b.year and a.province_id=b.province_id and a.major_type_id=b.major_type_id " +
                    "and a.batch_id=b.batch_id and a.province_id=c.province_id";
            Map m = commonSvc.findOneForJdbc(sql);
            returnMap.put("univInfo", m);
            returnResp(response, returnMap);
            return;
        } else {
            returnMap.put("success", false);
            returnMap.put("msg", "该批次志愿已至最大可报志愿数");
            returnResp(response, returnMap);
            return;
        }
    }

    @RequestMapping(value = "/isReportUpd.jspx")
    public void isReportUpd(Integer planId, Integer batchId, Integer univListId, Integer
            replaceCode, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        //根据方案id获取方案信息
        TCeeApplications tCeeApplications = commonSvc.get(TCeeApplications.class, planId);
        //省份id
        Integer provinceId = tCeeApplications.getProvinceId();
        //文理科
        Integer majorType = tCeeApplications.getMajorTypeId();

        Integer year = commonSvc.singleResult("SELECT dataBatchYear FROM TBaseProvince WHERE province_id =" + provinceId + " ");

        //查询当前批次信息可报院校数
        List<TCeeBatch> batchList = zytbSvc.getBatchList(year, provinceId, majorType);
        Integer univNum = 0;
        Integer majorNum = 0;
        for (TCeeBatch tCeeBatch : batchList) {
            if (batchId.equals(tCeeBatch.getBatchId())) {
                univNum = tCeeBatch.getUnivNum();
                majorNum = tCeeBatch.getMajorNum();
                break;
            }
        }
        //已报院校数
        List ybUnivNum = commonSvc.singleResultList("SELECT DISTINCT univCode FROM TCeeApplicationsDetail WHERE  applicationId="
                + planId + " and batchId=" + batchId);

        //判断是否还可以继续填报
        Map<String, Object> returnMap = new HashMap<>();
        if (ybUnivNum.size() <= univNum || replaceCode != null) {
            returnMap.put("success", true);
            returnMap.put("msg", "remote.jspx?planId=" + planId + "&batchId=" + batchId + "&univListId=" + univListId + "&majorNum=" + majorNum + "&univCodes=" + replaceCode);
            String sql = "SELECT a.univ_name univName,a.year,b.batch_name batchName,c.province_name provinceName " +
                    "FROM t_cee_enroll_univ_list a,t_cee_batch b,t_base_province c " +
                    "where a.id = " + univListId + " and a.year=b.year and a.province_id=b.province_id and a.major_type_id=b.major_type_id " +
                    "and a.batch_id=b.batch_id and a.province_id=c.province_id";
            Map m = commonSvc.findOneForJdbc(sql);
            returnMap.put("univInfo", m);
            returnResp(response, returnMap);
            return;
        } else {

        }
    }

    //浙江院校 可选报数量校验
    @RequestMapping(value = "/isReportUMSP.jspx")
    public void isReportUMSP(Integer planId, Integer batchId, Integer isFormal, Integer univListId, Integer
            replaceCode, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        //根据方案id获取方案信息
        TCeeApplications tCeeApplications = commonSvc.get(TCeeApplications.class, planId);
        //省份id
        Integer provinceId = tCeeApplications.getProvinceId();
        //文理科
        Integer majorType = tCeeApplications.getMajorTypeId();

        Integer year = commonSvc.singleResult("SELECT dataBatchYear FROM TBaseProvince WHERE province_id =" + provinceId + " ");

        //查询当前批次信息可报院校数
        List<TCeeBatch> batchList = zytbSvc.getBatchList(year, provinceId, majorType);

        Integer univNum = 0;
        Integer majorNum = 0;
        for (TCeeBatch tCeeBatch : batchList) {
            if (batchId.equals(tCeeBatch.getBatchId())) {
                univNum = tCeeBatch.getUnivNum();
                majorNum = tCeeBatch.getMajorNum();
                break;
            }
        }

        List ybNum = commonSvc.singleResultList("SELECT DISTINCT  univCode,majorId,majorCode,majorName FROM TCeeApplicationsDetail WHERE  applicationId="
                + planId + " and batchId=" + batchId);

        //判断是否还可以继续填报
        Map<String, Object> returnMap = new HashMap<>();
//        int a = ybNum.size();
//        && replaceCode != null
        int ybNumSize = ybNum.size();
        if (ybNum.size() < majorNum) {
            returnMap.put("success", true);
            returnMap.put("msg", "remote.jspx?planId=" + planId + "&batchId=" + batchId + "&univListId=" + univListId + "&majorNum=" + majorNum + "&replaceCode=" + replaceCode + "&isFormal=" + 0 + "&ybNumSize=" + ybNumSize);
            String sql = "SELECT a.univ_name univName,a.year,b.batch_name batchName,c.province_name provinceName " +
                    "FROM t_cee_enroll_univ_list a,t_cee_batch b,t_base_province c " +
                    "where a.id = " + univListId + " and a.year=b.year and a.province_id=b.province_id and a.major_type_id=b.major_type_id " +
                    "and a.batch_id=b.batch_id and a.province_id=c.province_id";
            Map m = commonSvc.findOneForJdbc(sql);
            returnMap.put("univInfo", m);
            returnResp(response, returnMap);
            return;
        } else {
            returnMap.put("success", false);
            returnMap.put("msg", "该批次志愿已至最大可报志愿数!请返回志愿方案中心删除。");
            returnResp(response, returnMap);
            return;
        }
    }

    @RequestMapping(value = "/getbatch.jspx")
    public void batch(Integer planId, Integer batchId, Integer univListId, HttpServletRequest
            request, HttpServletResponse response, ModelMap model, Integer order) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        //根据方案id获取方案信息
        TCeeApplications tCeeApplications = commonSvc.get(TCeeApplications.class, planId);
        //省份id
        Integer provinceId = tCeeApplications.getProvinceId();
        //文理科
        Integer majorType = tCeeApplications.getMajorTypeId();

        Integer year = commonSvc.singleResult("SELECT dataBatchYear FROM TBaseProvince WHERE province_id =" + provinceId + " ");

        //查询当前批次信息可报院校数
        List<TCeeBatch> batchList = zytbSvc.getBatchList(year, provinceId, majorType);
        Integer univNum = 0;
        Integer majorNum = 0;
        for (TCeeBatch tCeeBatch : batchList) {
            if (batchId.equals(tCeeBatch.getBatchId())) {
                univNum = tCeeBatch.getUnivNum();
                majorNum = tCeeBatch.getMajorNum();
                break;
            }
        }
        //已报院校数
        List ybUnivNum = commonSvc.singleResultList("SELECT DISTINCT univCode FROM TCeeApplicationsDetail WHERE  applicationId="
                + planId + " and batchId=" + batchId);

        //判断是否还可以继续填报
        Map<String, Object> returnMap = new HashMap<>();
        if (ybUnivNum.size() < univNum) {
            returnMap.put("success", true);
            returnMap.put("msg", "getbatchnext.jspx?planId=" + planId + "&batchId=" + batchId + "&univListId=" + univListId + "&majorNum=" + majorNum + "&order=" + order);
            String sql = "SELECT a.univ_name univName,a.year,b.batch_name batchName,c.province_name provinceName " +
                    "FROM t_cee_enroll_univ_list a,t_cee_batch b,t_base_province c " +
                    "where a.id = " + univListId + " and a.year=b.year and a.province_id=b.province_id and a.major_type_id=b.major_type_id " +
                    "and a.batch_id=b.batch_id and a.province_id=c.province_id";
            Map m = commonSvc.findOneForJdbc(sql);
            returnMap.put("univInfo", m);
            returnResp(response, returnMap);
            return;
        } else {
            returnMap.put("success", false);
            returnMap.put("msg", "该批次志愿已至最大可报志愿数");
            returnResp(response, returnMap);
            return;
        }
    }

    /**
     * 专业选报
     *
     * @param planId
     * @param batchId
     * @param univListId
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/remote.jspx")
    public String report(Integer planId, Integer batchId, String majorName, Integer univCodes, Integer
            univListId, Integer isFormal, Integer replaceCode, Integer majorNum, Integer ybNumSize, HttpServletRequest
                                 request, HttpServletResponse response, ModelMap model) throws UnsupportedEncodingException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        String aa = request.getParameter("majorName");
        if (aa != null && aa != "") {
            String majorNa = new String(aa.getBytes("ISO-8859-1"), "UTF-8");
            model.addAttribute("majorNa", majorNa);
        }

        //根据方案id获取方案信息
        TCeeApplications tCeeApplications = commonSvc.get(TCeeApplications.class, planId);
        //省份id
        Integer provinceId = tCeeApplications.getProvinceId();
        //文理科
        Integer majorType = tCeeApplications.getMajorTypeId();

        Integer year = commonSvc.singleResult("SELECT dataEnrollYear FROM TBaseProvince WHERE province_id =" + provinceId + " ");

        Integer year0 = commonSvc.singleResult("SELECT dataPlanYear FROM TBaseProvince WHERE province_id =" + provinceId + " ");

        //根据univListId查询t_cee_enroll_univ_list对应的院校信息
        TCeeEnrollUnivList univDetail = commonSvc.get(TCeeEnrollUnivList.class, univListId);
        //根据方案id和批次id及学校id查询专业id
        List<TCeeApplicationsDetail> mojrDetailList = commonSvc.singleResultList("FROM TCeeApplicationsDetail WHERE  applicationId="
                + planId + " and batchId=" + batchId + " and univId=" + univDetail.getUnivId());
        List mojrList = new ArrayList();
        for (int i = 0; i < mojrDetailList.size(); i++) {
            mojrList.add(mojrDetailList.get(i).getMajorNumId());
        }

        //计划专业数据
        List<TCeeEnrollMajorList> planList = zytbSvc.getPlanList(univListId, year0);
        //前一年历史专业数据
        List yearsAgo1 = zytbSvc.getYearsAgo(univListId, year);
        //前两年历史专业数据
        List yearsAgo2 = zytbSvc.getYearsAgo(univListId, year - 1);
        //前三年历史专业数据
        List yearsAgo3 = zytbSvc.getYearsAgo(univListId, year - 2);

        //查询院校总计划数据
        String sql = "select * from t_cee_enroll_major_list where 1=1 and year =" + year0 + " and plan_or_history=1 and data_type=0 and univ_list_id=" + univListId;
        Map m = commonSvc.findOneForJdbc(sql);


        model.addAttribute("userRank", tCeeApplications.getRank());
        model.addAttribute("year", year);
        model.addAttribute("planId", planId);
        model.addAttribute("batchId", batchId);
        model.addAttribute("replaceCode", replaceCode);
        model.addAttribute("univCodes", univCodes);
        model.addAttribute("isFormal", isFormal);
        model.addAttribute("planList", planList);
        model.addAttribute("yearsAgo1", yearsAgo1);
        model.addAttribute("yearsAgo2", yearsAgo2);
        model.addAttribute("yearsAgo3", yearsAgo3);
        model.addAttribute("mojrList", mojrList);
        model.addAttribute("mojrDetailList", mojrDetailList);
        model.addAttribute("univPlan", m);
        model.addAttribute("majorNum", majorNum);
        model.addAttribute("ybNumSize", ybNumSize);
        model.addAttribute("univDetail", univDetail);
        model.addAttribute("provinceId", provinceId);

        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, "remote");
    }

    /**
     * @param planId
     * @param batchId
     * @param univListId
     * @param majorNum
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/getbatchnext.jspx")
    public String next(Integer planId, Integer batchId, Integer univListId, Integer order, Integer
            majorNum, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        //根据方案id获取方案信息
        TCeeApplications tCeeApplications = commonSvc.get(TCeeApplications.class, planId);
        model.addAttribute("tCeeApplications", tCeeApplications);

        //省份id
        Integer provinceId = tCeeApplications.getProvinceId();
        //文理科
        Integer majorType = tCeeApplications.getMajorTypeId();

        Integer year = commonSvc.singleResult("SELECT dataEnrollYear FROM TBaseProvince WHERE province_id =" + provinceId + " ");

        Integer year0 = commonSvc.singleResult("SELECT dataPlanYear FROM TBaseProvince WHERE province_id =" + provinceId + " ");

        //根据univListId查询t_cee_enroll_univ_list对应的院校信息
        TCeeEnrollUnivList univDetail = commonSvc.get(TCeeEnrollUnivList.class, univListId);
        //根据方案id和批次id及学校id查询专业id
        List<TCeeApplicationsDetail> mojrDetailList = commonSvc.singleResultList("FROM TCeeApplicationsDetail WHERE  applicationId="
                + planId + " and batchId=" + batchId + " and univId=" + univDetail.getUnivId());
        List mojrList = new ArrayList();
        for (int i = 0; i < mojrDetailList.size(); i++) {
            mojrList.add(mojrDetailList.get(i).getMajorNumId());
        }

        //计划专业数据
        List<TCeeEnrollMajorList> planList = zytbSvc.getPlanList(univListId, year0);
        //前一年历史专业数据
        List yearsAgo1 = zytbSvc.getYearsAgo(univListId, year);
        //前两年历史专业数据
        List yearsAgo2 = zytbSvc.getYearsAgo(univListId, year - 1);
        //前三年历史专业数据
        List yearsAgo3 = zytbSvc.getYearsAgo(univListId, year - 2);

        //查询院校总计划数据
        String sql = "select * from t_cee_enroll_major_list where 1=1 and plan_or_history=1 and data_type=0 and univ_list_id=" + univListId;
        Map m = commonSvc.findOneForJdbc(sql);

        model.addAttribute("univDetail", univDetail);
        model.addAttribute("year", year);
        model.addAttribute("planId", planId);
        model.addAttribute("batchId", batchId);
        model.addAttribute("order", order);
        model.addAttribute("planList", planList);
        model.addAttribute("yearsAgo1", yearsAgo1);
        model.addAttribute("yearsAgo2", yearsAgo2);
        model.addAttribute("yearsAgo3", yearsAgo3);
        model.addAttribute("mojrList", mojrList);
        model.addAttribute("mojrDetailList", mojrDetailList);
        model.addAttribute("univPlan", m);
        model.addAttribute("majorNum", majorNum);

        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, "remote");
    }

    /**
     * 判断是否可报专业
     *
     * @param planId
     * @param batchId
     * @param isSelect
     * @param request
     * @param response
     * @param model
     */
    @RequestMapping(value = "/isReportMajor.jspx")
    public void isReportMajor(Integer planId, Integer batchId, Integer isFormal, String isSelect, Integer
            replaceCode, Integer univCodes, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        //根据方案id获取方案信息
        TCeeApplications tCeeApplications = commonSvc.get(TCeeApplications.class, planId);
        //省份id
        Integer provinceId = tCeeApplications.getProvinceId();
        //文理科
        Integer majorType = tCeeApplications.getMajorTypeId();

        Integer year = commonSvc.singleResult("SELECT dataBatchYear FROM TBaseProvince WHERE province_id =" + provinceId + " ");

        //查询当前批次信息可报专业数
        List<TCeeBatch> batchList = zytbSvc.getBatchList(year, provinceId, majorType);
        Integer majorNum = 0;
        for (TCeeBatch tCeeBatch : batchList) {
            if (batchId.equals(tCeeBatch.getBatchId())) {
                majorNum = tCeeBatch.getMajorNum();
                break;
            }
        }

        //已选专业数
        Integer ybMajorNum = 0;
        if (StringUtils.isNotBlank(isSelect) && !"null".equals(isSelect)) {
            String[] isSelects = isSelect.split(",");
            ybMajorNum = isSelects.length;
        }
        //判断是否还可以继续填报
        Map<String, Object> returnMap = new HashMap<>();
        if (ybMajorNum <= majorNum) {
            String msg = "savePlanDetail.jspx?planId=" + planId + "&batchId=" + batchId + "&isSelect=" + isSelect;
            if (replaceCode != null) {
                msg += "&replaceCode=" + replaceCode;
            }
            if (univCodes != null) {
                msg += "&univCodes=" + univCodes;
            }
            if (isFormal != null) {
                msg += "&isFormal=" + isFormal;
            }
            returnMap.put("success", true);
            returnMap.put("msg", msg);
            returnResp(response, returnMap);
            return;
        } else {
            returnMap.put("success", false);
            returnMap.put("msg", "专业数量超过可选报专业数");
            returnResp(response, returnMap);
            return;
        }
    }

    /**
     * 保存志愿数据
     *
     * @param planId
     * @param batchId
     * @param isSelect
     * @param request
     * @param response
     * @param model
     */
    @RequestMapping(value = "/savePlanDetail.jspx")
    public void savePlanDetail(Integer planId, Integer batchId, Integer isFormal, String isSelect, Integer
            replaceCode, Integer univCodes, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        List<TCeeApplicationsDetail> detailList = new ArrayList<>();
        //根据方案id获取方案信息
        TCeeApplications tCeeApplications = commonSvc.get(TCeeApplications.class, planId);
        //省份id
        Integer provinceId = tCeeApplications.getProvinceId();
        if (provinceId == 18) {
            String sql = "select * from t_cee_applications_detail where application_id=" + planId + " AND batch_id=" + batchId;
            if (!StringUtils.isEmpty(ObjectUtils.toString(univCodes))) {
                sql += " AND univ_code = " + univCodes;
            }
            if (isFormal != null) {
                sql += " AND is_formal = " + isFormal;
            }
            if (replaceCode != 0) {
                sql += " AND univ_code = " + replaceCode;
            }
            List<TCeeApplicationsDetail> appList = commonSvc.findListbySql(sql, TCeeApplicationsDetail.class);
            commonSvc.deleteAllEntitie(appList);

            String[] isSelects = isSelect.split(",");
            for (int i = 0; i < isSelects.length; i++) {
                if (StringUtil.isNotEmpty(isSelects[i])) {
                    //专业信息
                    TCeeEnrollMajorList majorList = commonSvc.get(TCeeEnrollMajorList.class, Integer.parseInt(isSelects[i]));

                    String univId = majorList.getUnivId();
                    //删除该批次下对应学校的所有专业信息
                    //DELETE  FROM `t_cee_applications_detail` WHERE application_id=1017771 AND batch_id=1 AND univ_code='3131'
                    sql = "DELETE  FROM t_cee_applications_detail WHERE application_id=" + planId + " AND batch_id=" + batchId + " AND univ_id = '" + univId + "'";
                    commonSvc.executeSql(sql);

                    TCeeApplicationsDetail detail = new TCeeApplicationsDetail();
                    detail.setApplicationId(planId);
                    detail.setYear(tCeeApplications.getYear());
                    detail.setProvinceId(tCeeApplications.getProvinceId());
                    detail.setMajorTypeId(tCeeApplications.getMajorTypeId());
                    detail.setBatchId(batchId);
                    detail.setUnivId(univId);
                    detail.setUnivCode(majorList.getUnivCode());
                    detail.setUnivName(majorList.getUnivName());
//                    detail.setMajorId(isSelects[i]);
//                    String majorNumId = majorList.getMajorId();
//                    detail.setMajorNumId(majorNumId);
//                    String majorNumId = String.valueOf(majorList.getId());
                    detail.setMajorNumId(isSelects[i]);
                    detail.setMajorId(majorList.getMajorId());
                    detail.setMajorCode(majorList.getMajorCode());
                    detail.setMajorName(majorList.getMajorName());
                    detail.setMajorOrder(i);
                    if (StringUtils.isEmpty(ObjectUtils.toString(replaceCode))) {
                        replaceCode = appList.get(0).getUnivOrder();
                    }
                    if (isFormal == null) {
                        isFormal = appList.get(0).getIsFormal();
                    }
                    detail.setUnivOrder(replaceCode);
                    detail.setIsFormal(isFormal);
                    detail.setMajorSubjectsLevel(majorList.getMajorSubjectsLevel());
                    detail.setIsObey("1");

                    detailList.add(detail);
                }
            }
            commonSvc.batchSaveOrUpdate(detailList);

        } else {

            //如果有需要替换的志愿
//        Integer univOrder = 0;
//        if (order!= null){
//            univOrder = order;
            //取被替换志愿院校
            String sql = "select * from t_cee_applications_detail where application_id=" + planId + " AND batch_id=" + batchId;

            if (!StringUtils.isEmpty(ObjectUtils.toString(univCodes))) {
                sql += " AND univ_code = " + univCodes;
            }
            if (isFormal != null) {
                sql += " AND is_formal = " + isFormal;
//            sql += "select * from t_cee_applications_detail where application_id="+ planId +" AND batch_id="+batchId+" AND univ_order = "+replaceCode+" AND is_formal = "+isFormal;
            }
            if (!StringUtils.isEmpty(ObjectUtils.toString(replaceCode))) {
                sql += " AND univ_order = " + replaceCode;
            }
            List<TCeeApplicationsDetail> appList = commonSvc.findListbySql(sql, TCeeApplicationsDetail.class);
            //取出排序
//            univOrder = appList.get(0).getUnivOrder();
            commonSvc.deleteAllEntitie(appList);
//        }
//        if (replaceCode != null) {
//        }

            String[] isSelects = isSelect.split(",");
            for (int i = 0; i < isSelects.length; i++) {
                if (StringUtil.isNotEmpty(isSelects[i])) {
                    //专业信息
                    TCeeEnrollMajorList majorList = commonSvc.get(TCeeEnrollMajorList.class, Integer.parseInt(isSelects[i]));

                    String univId = majorList.getUnivId();
                    //删除该批次下对应学校的所有专业信息
                    //DELETE  FROM `t_cee_applications_detail` WHERE application_id=1017771 AND batch_id=1 AND univ_code='3131'
                    sql = "DELETE  FROM t_cee_applications_detail WHERE application_id=" + planId + " AND batch_id=" + batchId + " AND univ_id = '" + univId + "'";
                    commonSvc.executeSql(sql);

                    TCeeApplicationsDetail detail = new TCeeApplicationsDetail();
                    detail.setApplicationId(planId);
                    detail.setYear(tCeeApplications.getYear());
                    detail.setProvinceId(tCeeApplications.getProvinceId());
                    detail.setMajorTypeId(tCeeApplications.getMajorTypeId());
                    detail.setBatchId(batchId);
                    detail.setUnivId(univId);
                    detail.setUnivCode(majorList.getUnivCode());
                    detail.setUnivName(majorList.getUnivName());
                    detail.setMajorNumId(isSelects[i]);
                    detail.setMajorId(majorList.getMajorId());
                    detail.setMajorCode(majorList.getMajorCode());
                    detail.setMajorName(majorList.getMajorName());
                    detail.setMajorOrder(i);
                    if (StringUtils.isEmpty(ObjectUtils.toString(replaceCode))) {
                        replaceCode = appList.get(0).getUnivOrder();
                    }
                    if (isFormal == null) {
                        isFormal = appList.get(0).getIsFormal();
                    }
                    detail.setUnivOrder(replaceCode);
                    detail.setIsFormal(isFormal);
                    detail.setMajorSubjectsLevel(majorList.getMajorSubjectsLevel());
                    detail.setIsObey("1");

                    detailList.add(detail);
                } else {
                    TCeeApplicationsDetail detail = new TCeeApplicationsDetail();
                    detail.setApplicationId(planId);
                    detail.setYear(tCeeApplications.getYear());
                    detail.setProvinceId(tCeeApplications.getProvinceId());
                    detail.setMajorTypeId(tCeeApplications.getMajorTypeId());
                    detail.setBatchId(batchId);
                    if (StringUtils.isEmpty(ObjectUtils.toString(replaceCode))) {
                        replaceCode = appList.get(0).getUnivOrder();
                    }
                    if (isFormal == null) {
                        isFormal = appList.get(0).getIsFormal();
                    }
                    detail.setUnivOrder(replaceCode);
                    detail.setIsFormal(isFormal);
                    detailList.add(detail);
                }
            }
            commonSvc.batchSaveOrUpdate(detailList);

        }
    }

    /**
     * 批次线
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/batchLine.jspx")
    public String batchLine(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, "batchLine");
    }

    /**
     * 志愿填报服务
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/toCollegeService.jspx")
    public String toCollegeService(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, "toCollegeService");
    }

    @RequestMapping("/userCenter.jspx")
    public String userCenter(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, "userCenter");
    }

    /**
     * 绑定订单
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/bangDingOrder.jspx")
    public String bangDingOrder(HttpServletRequest request, ModelMap model, String application_id, String orderId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        String userName = user.getRealname();
        TMgrSrvNode tMgrSrvNode = new TMgrSrvNode();
        Date now = new Date();
        tMgrSrvNode.setModifyTime(yyyy_MM_dd_HH_mm_ss.format(now));
        String id = tMgrSrvNode.getId();
        if (StringUtils.isBlank(id)) {
            id = getNodeKey();
            tMgrSrvNode.setId(id);
        }
        tMgrSrvNode.setText1(application_id);
        tMgrSrvNode.setFwName(userName);
        tMgrSrvNode.setOrderId(orderId);
        tMgrSrvNode.setNodeType("院校专业推荐");
        commonSvc.saveOrUpdate(tMgrSrvNode);

        FrontUtils.frontData(request, model, site);
        return "redirect:project.jspx";
    }

    /**
     * 获取node ID  JD + 当前日期（6位） + 当日序列（8位）
     *
     * @return
     */
    public String getNodeKey() {
        String now = new SimpleDateFormat("yyyyMMdd").format(new Date());
        //查询最后一条记录的id
        String sql = "select max(id) id from t_mgr_srv_node";
        Map<String, Object> map = commonSvc.findOneForJdbc(sql);
        if (map != null && map.get("id") != null) {
            String id = map.get("id").toString();

            String id_date = id.substring(2, 10);
            String id_seq = id.substring(10);

            if (now.equals(id_date)) {
                return "JD" + id_date + String.format("%08d", (Integer.parseInt(id_seq) + 1));
            }
        }
        return "JD" + now + "00000001";
    }

    /**
     * 统一返回方法
     *
     * @param response
     * @param returnMap
     */
    private void returnResp(HttpServletResponse response, Map<String, Object> returnMap) {
        try {
            String json = JSON.toJSONString(returnMap);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        } catch (IOException e) {
            LOG.error("IO流读写失败", e);
        }
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

    private void close(CallableStatement cs, ResultSet rs) {
        try {
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出
     *
     * @param applicationId
     * @param request
     * @param response
     */
    @RequiresPermissions("daochu:*")
    @RequestMapping("/excelExport.jspx")
    public void excelExport(Integer applicationId, final HttpServletRequest request, HttpServletResponse
            response, ModelMap model) {

        // 生成提示信息，
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        String a = "sss|sddd".replace("\\|sd", "");
        try {
            codedFileName = "志愿信息";
            // 根据浏览器进行转码，使其支持中文文件名
            String browse = BrowserUtils.checkBrowse(request);
            if ("MSIE".equalsIgnoreCase(browse.substring(0, 4))) {
                response.setHeader("content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(codedFileName, "UTF-8")
                        + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
                response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
            }

            final String query = "call getEstimateReport(" + applicationId + ")";


            executeVoidProcedureSql(query, null);

            HSSFWorkbook workbook = null;
//            HSSFCellStyle cellStyle = workbook.createCellStyle();

            List<TCeeApplicationsExports> apps = commonSvc.findByProperty(TCeeApplicationsExports.class, "applicationId", applicationId);
//            List<TCeeApplicationsExports> apps = commonSvc.findByPropertyisOrder(TCeeApplicationsExports.class,"applicationId",applicationId,"id",true);
            //List<TCeeApplicationsExports> apps = commonSvc.findListbySql("select  * from t_cee_applications_exports where application_id = "+applicationId+" " , TCeeApplicationsExports.class);
            workbook = ExcelExportUtil.exportExcel("导出信息", TCeeApplicationsExports.class, apps);

            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (UnsupportedEncodingException e1) {

        } catch (Exception e) {

        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {

            }
        }
    }


    @RequestMapping(value = "/sendApp.jspx")
    public void sendApp(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws IOException {

        String application_id = request.getParameter("application_idd");
        String sd_username = request.getParameter("mobile");

        String maxId_sql = "SELECT MAX(id) as maxid FROM `t_cee_applications`";
        Map<String, Object> idmap = commonSvc.findOneForJdbc(maxId_sql, null);
        int maxId = (int) idmap.get("maxid") + 1;

        String msg = "";
        String success = "true";

        CmsUser sd_user = cmsUserMng.findByUsername(sd_username);
        if (sd_user == null) {
            msg = "此用户不存在，请检查账号！";
            success = "false";
        } else {
            String sd_sql1 = "INSERT INTO t_cee_applications(id,user_id,user_name,YEAR,province_id,major_type_id,application_type,application_name,score,rank,subjects,subjects_level,subjects_level_val,is_check,create_time,update_time,remark)\n" +
                    "SELECT " + maxId + "," + sd_user.getId() + "," + sd_user.getUsername() + ", YEAR, province_id, major_type_id, application_type, application_name, score, rank, subjects, subjects_level, subjects_level_val, is_check, create_time, update_time, remark FROM t_cee_applications WHERE id = " + application_id + "\n";
            String sd_sql2 = "INSERT INTO t_cee_applications_detail(application_id,YEAR,province_id,major_type_id,batch_id,univ_id,univ_code,univ_name,univ_order,major_id,major_code,major_name,major_order,major_subjects_level,is_obey,is_formal,major_num_id)\n" +
                    "SELECT " + maxId + ",YEAR,province_id,major_type_id,batch_id,univ_id,univ_code,univ_name,univ_order,major_id,major_code,major_name,major_order,major_subjects_level,is_obey,is_formal,major_num_id\n" +
                    "FROM t_cee_applications_detail WHERE application_id = " + application_id + "";

            int sql1 = commonSvc.updateBySqlString(sd_sql1);
            int sql2 = commonSvc.updateBySqlString(sd_sql2);

            if (sql1 == 0 && sql2 == 0) {
                msg = "发送失败，请检查方案稍后重试！";
                success = "false";
            } else {
                msg = "发送成功！";
                success = "true";
            }


        }

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("msg", msg);
        resultMap.put("success", success);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(resultMap));

    }


    // Alltodo: 2019/12/23 二级专业
    private List<Map<String, Object>> queryMajorByParentId(String parentId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parentId", parentId);
        String queryFristMajor =
                "SELECT parent_Major_Id,major_Id,major_Name FROM t_s_major_introduce WHERE parent_Major_Id IN(" + parentId + ") ORDER BY major_Id";

        return commonSvc.findForJdbc(queryFristMajor, null);
    }

    public String subString(List<Map<String, Object>> List) {
        String twoStr = "";
        for (Map<String, Object> map : List) {
            twoStr += "'" + map.get("major_Id") + "',";
        }
        twoStr = (String) twoStr.subSequence(0, twoStr.length() - 1);
        return twoStr;
    }

    //    根据univ_id去重TCeeApplicationsDetail
    private List<TCeeApplicationsDetail> removeDuplicateCaseDetail(List<TCeeApplicationsDetail> tCeeApplicationsDetails) {
        Set<TCeeApplicationsDetail> set = new TreeSet<>(new Comparator<TCeeApplicationsDetail>() {
            @Override
            public int compare(TCeeApplicationsDetail tCeeApplicationsDetail1, TCeeApplicationsDetail tCeeApplicationsDetail2) {
                //字符串,则按照asicc码升序排列
                return tCeeApplicationsDetail1.getUnivId().compareTo(tCeeApplicationsDetail2.getUnivId());
            }
        });
        set.addAll(tCeeApplicationsDetails);
        return new ArrayList<>(set);
    }

    //    发现详情表没有数据则新建详情表
    public void findAndCreateDetail(String applicationId, Integer batchId, TCeeBatch tCeeBatch, TCeeApplications tCeeApplications, Integer isFormal) {
        String tCeeApplicationsDetailsIsSizeSql = "SELECT * FROM t_cee_applications_detail \n" +
                "WHERE application_id = '" + applicationId + "'\n" +
                "AND univ_id IS NOT NULL  AND batch_id = " + batchId + " AND is_formal = " + isFormal + "  ";
        List<TCeeApplicationsDetail> tCeeApplicationsDetailsSize = commonSvc.findListbySql(tCeeApplicationsDetailsIsSizeSql, TCeeApplicationsDetail.class);

        if (tCeeApplicationsDetailsSize == null) {

            for (int i = 0; i < tCeeBatch.getUnivNum(); i++) {
                TCeeApplicationsDetail tCeeApplicationsDetailIs = new TCeeApplicationsDetail();
                tCeeApplicationsDetailIs.setYear(tCeeApplications.getYear());
                tCeeApplicationsDetailIs.setProvinceId(tCeeApplications.getProvinceId());
                tCeeApplicationsDetailIs.setMajorTypeId(tCeeApplications.getMajorTypeId());
                tCeeApplicationsDetailIs.setApplicationId(Integer.valueOf(applicationId));
                tCeeApplicationsDetailIs.setBatchId(batchId);
                tCeeApplicationsDetailIs.setUnivOrder(i + 1);
                tCeeApplicationsDetailIs.setMajorOrder(0);
                tCeeApplicationsDetailIs.setIsFormal(isFormal);
                tCeeApplicationsDetailIs.setIsObey("1");
                commonSvc.save(tCeeApplicationsDetailIs);
            }

        } else if (removeDuplicateCaseDetail(tCeeApplicationsDetailsSize).size() < tCeeBatch.getUnivNum()) {

            if (isFormal == 0) {
                if(tCeeBatch.getUnivNum()!=null && tCeeBatch.getUnivNum() !=0) {
                    StringBuffer nullTableSql = new StringBuffer();
                    nullTableSql.append(" SELECT * FROM ( ");
                    for (int j = 1; j <= tCeeBatch.getUnivNum(); j++) {
                        if (j == tCeeBatch.getUnivNum()) {
                            nullTableSql.append("SELECT " + j + " AS number \n" +
                                    ") a \n ");
                        } else {
                            nullTableSql.append("SELECT " + j + " AS number \n" +
                                    "UNION \n ");
                        }
                    }
                    nullTableSql.append("WHERE number NOT IN (\n" +
                            "SELECT DISTINCT univ_order FROM t_cee_applications_detail \n" +
                            "WHERE application_id = '" + applicationId + "' \n" +
                            "AND batch_id = " + batchId + "\n" +
                            "AND is_formal = " + isFormal + " ) \n");

                    List<Map<String, Object>> nullTableList = commonSvc.findForJdbc(String.valueOf(nullTableSql));

                    if (nullTableList != null && nullTableList.size() != 0) {
                        for (Map<String, Object> map : nullTableList) {
                            TCeeApplicationsDetail tCeeApplicationsDetail = new TCeeApplicationsDetail();
                            tCeeApplicationsDetail.setApplicationId(Integer.parseInt(applicationId));
                            tCeeApplicationsDetail.setYear(tCeeApplications.getYear());
                            tCeeApplicationsDetail.setProvinceId(tCeeApplications.getProvinceId());
                            tCeeApplicationsDetail.setMajorTypeId(tCeeApplications.getMajorTypeId());
                            tCeeApplicationsDetail.setBatchId(batchId);
                            tCeeApplicationsDetail.setUnivOrder(Integer.valueOf(map.get("number").toString()));
                            tCeeApplicationsDetail.setMajorOrder(0);
                            tCeeApplicationsDetail.setIsFormal(isFormal);
                            commonSvc.save(tCeeApplicationsDetail);
                        }
                    }
                }
            } else if (isFormal == 1) {
                if(tCeeBatch.getExtraNum()!=null && tCeeBatch.getExtraNum() !=0) {
                    StringBuffer nullTableSql = new StringBuffer();
                    nullTableSql.append(" SELECT * FROM ( ");
                    for (int k = 1; k <= tCeeBatch.getExtraNum(); k++) {
                        if (k == tCeeBatch.getExtraNum()) {
                            nullTableSql.append("SELECT " + k + " AS number \n" +
                                    ") a \n ");
                        } else {
                            nullTableSql.append("SELECT " + k + " AS number \n" +
                                    "UNION \n ");
                        }
                    }
                    nullTableSql.append("WHERE number NOT IN (\n" +
                            "SELECT DISTINCT univ_order FROM t_cee_applications_detail \n" +
                            "WHERE application_id = '" + applicationId + "' \n" +
                            "AND batch_id = " + batchId + "\n" +
                            "AND is_formal = " + isFormal + " ) \n");

                    List<Map<String, Object>> nullTableList = commonSvc.findForJdbc(String.valueOf(nullTableSql));

                    if (nullTableList != null && nullTableList.size() != 0) {
                        for (Map<String, Object> map : nullTableList) {
                            TCeeApplicationsDetail tCeeApplicationsDetail = new TCeeApplicationsDetail();
                            tCeeApplicationsDetail.setApplicationId(Integer.parseInt(applicationId));
                            tCeeApplicationsDetail.setYear(tCeeApplications.getYear());
                            tCeeApplicationsDetail.setProvinceId(tCeeApplications.getProvinceId());
                            tCeeApplicationsDetail.setMajorTypeId(tCeeApplications.getMajorTypeId());
                            tCeeApplicationsDetail.setBatchId(batchId);
                            tCeeApplicationsDetail.setUnivOrder(Integer.valueOf(map.get("number").toString()));
                            tCeeApplicationsDetail.setMajorOrder(0);
                            tCeeApplicationsDetail.setIsFormal(isFormal);
                            commonSvc.save(tCeeApplicationsDetail);
                        }
                    }
                }
            }

        }
    }
}
