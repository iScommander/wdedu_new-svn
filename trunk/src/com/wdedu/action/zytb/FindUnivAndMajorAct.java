package com.wdedu.action.zytb;

import com.alibaba.fastjson.JSON;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.*;
import com.jeecms.wdedu.service.CommonSvc;
import com.wdedu.entity.*;

import com.wdedu.service.FindCareerSvc;
import com.wdedu.service.FindMajorSvc;
import com.wdedu.service.FindUnivSvc;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;

import static com.jeecms.common.page.SimplePage.cpn;

/**
 * @author fanyue
 * @ProjectName wdedu
 * @Description: 找大学
 * @date 2018/10/24
 */
@Controller
@RequestMapping(value = "/searchUnivAndMajor")
public class FindUnivAndMajorAct {
    private final static String TPLDIR_FINDUNIVLIST = "univAct";
    private final static String UNIVLIST_REQUEST = "univList";
    private final static String UNIVMAJOR_REQUEST = "univMajor";
    private final static String UNIVSCHOOL_REQUEST = "univSchool";
    private final static String UNIVCOMPARE_REQUEST = "univCompare";

    @Autowired
    private FindUnivSvc findUnivSvc;
    @Autowired
    private CommonSvc commonSvc;
    @Autowired
    private FindMajorSvc findMajorSvc;
    @Autowired
    private FindCareerSvc findCareerSvc;
    private List<TDataUniversity> tDataUniversityList;
    private List<TDataMajor> tDataMajorListBen;
    private List<TDataMajor> tDataMajorList1;
    private List<TDataMajor> tDataMajorList2;
    private List<TDataMajor> tDataMajorListZhuan;


    //院校列表
    @RequestMapping(value = "/searchUnivList.jspx")
    public String searchUnivList(Integer pageNo, String provinceIds, Integer pageSize, String univName, String provinceId, String location, String univType,
                                 String offOrVol, String univLevel, Integer[] is211, Integer[] is985, Integer[] isKeylab, Integer[] isIndependence, String provinceName,
                                 Integer[] isDefence, Integer isFirstRateUniv, HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        FrontUtils.frontData(request, model, site);
       /*if (provinceId == null) {
            provinceId = new Integer[1];
        }*/
        if (is211 == null) {
            is211 = new Integer[1];
        }
        if (is985 == null) {
            is985 = new Integer[1];
        }
        if (isIndependence == null) {
            isIndependence = new Integer[1];
        }
        if (isKeylab == null) {
            isKeylab = new Integer[1];
        }
        if (isDefence == null) {
            isDefence = new Integer[1];
        }

        String str = "";
        if (univType != null && univType != "") {
            String[] univTypes = univType.split(",");
            /*univType = univTypes[0];*/
            str = univType.replace(",", "','");
            StringBuffer sb = new StringBuffer(str);
            sb.append("'");
            sb.insert(0, "'");
            str = sb.toString();

            model.addAttribute("univType", univType);
        }


        if (offOrVol != null && offOrVol != "" && offOrVol.length() > 2) {
           /* String[] offOrVols = offOrVol.split(",");
            offOrVol = offOrVols[0];*/
            model.addAttribute("offOrVol", offOrVol);
            offOrVol = "";
        } else {

            model.addAttribute("offOrVol", offOrVol);
        }
        if (univLevel != null && univLevel != "" && univLevel.length() > 2) {
            /*String[] univLevels = univLevel.split(",");
            univLevel = univLevels[0];*/
            model.addAttribute("univLevel", univLevel);
            univLevel = "";
        } else {
            model.addAttribute("univLevel", univLevel);
        }
        // 省份列表
        List<TBaseProvince> provinceList = commonSvc.loadAll(TBaseProvince.class);
        /*String[] str =univType.split(",");*/

        System.out.println(univType);
        Pagination pagination = findUnivSvc.getPage(cpn(pageNo), CookieUtils.getPageSize(request), provinceId, str, offOrVol, univLevel, is211[0], is985[0], isKeylab[0], isIndependence[0], isDefence[0], location, univName, isFirstRateUniv);
        String univTypeSql = "SELECT * FROM `t_data_university_type`";
        List<TDataUniversityType> universityType = commonSvc.findListbySql(univTypeSql, TDataUniversityType.class);
        model.addAttribute("universityType", universityType);
        /* model.addAttribute("provinceId", provinceId[0]);*/

        if (provinceId != null && provinceId != "") {
            String sql = "select * FROM t_base_province WHERE province_id in(" + provinceId + ")";
            List<TBaseProvince> checkList = commonSvc.findListbySql(sql, TBaseProvince.class);
            String pro = "";
            if (checkList != null && checkList.size() > 0) {
                for (TBaseProvince province : checkList) {
                    pro += province.getProvinceName() + ",";
                }
                model.addAttribute("pro", pro);
            }
        }
        /*if(univType!=null&&univType!=""){
            String sql2 ="select * FROM t_data_university_type WHERE type_name in("+univType+")";
            List<TDataUniversityType> univTypeList = commonSvc.findListbySql(sql2,TDataUniversityType.class);
            String type = "";
            if(univTypeList!=null&&univTypeList.size()>0){
                for(TDataUniversityType ty :univTypeList){
                    type+=ty.getTypeName()+",";
                }
                model.addAttribute("type", type);
            }
            model.addAttribute("univType", univType);
        }*/

        model.addAttribute("is211", is211[0]);
        model.addAttribute("is985", is985[0]);
        model.addAttribute("isKeylab", isKeylab[0]);
        model.addAttribute("isIndependence", isIndependence[0]);
        model.addAttribute("isDefence", isDefence[0]);
        model.addAttribute("isFirstRateUniv", isFirstRateUniv);
        model.addAttribute("pagination", pagination);
        model.addAttribute("provinceList", provinceList);
        model.addAttribute("pageNo", pagination.getPageNo());
        return FrontUtils.getTplPath(site.getSolutionPath(), "wdedu/zytb", "zhaodaxue");
    }

    //院校详情
    @RequestMapping(value = "/univDetail.jspx")
    public String schoolDetails(HttpServletRequest request, ModelMap model, Integer id) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        String province = user.getAttr().get("province_id");
        if (province.equals("") || province == null) {
            return "redirect:/admissonSvcAct/findbaseInfo.jspx";
        }

        //权限
        String str = "";
        String groupSql = "select * from jc_user_group where user_id='" + user.getId() + "'";
        List<JcUserGroup> userGroups = commonSvc.findListbySql(groupSql, JcUserGroup.class);
        if (userGroups != null && userGroups.size() > 0) {
            for (JcUserGroup userGroup : userGroups) {
                str += userGroup.getGroupId().toString() + ",";
            }
        }
        if (str.contains("3") || str.contains("4") || str.contains("6")
                || str.contains("10") || str.contains("13") || str.contains("19")) {
            model.addAttribute("shior", 1);
        } else {
            model.addAttribute("shior", 2);
        }

        Integer planYear = commonSvc.singleResult("SELECT dataPlanYear FROM TBaseProvince WHERE province_id = " + user.getAttr().get("province_id") + " ");
        Integer enrollYear = commonSvc.singleResult("SELECT dataEnrollYear FROM TBaseProvince WHERE province_id = " + user.getAttr().get("province_id") + " ");
        Integer batchYear = commonSvc.singleResult("SELECT dataBatchYear FROM TBaseProvince WHERE province_id = " + user.getAttr().get("province_id") + " ");


        //大学详情
        List<TDataUniversityDetail> tDataUniversityDetailList = commonSvc.findByProperty(TDataUniversityDetail.class, "id", id);
        if (tDataUniversityDetailList != null && tDataUniversityDetailList.size() > 0) {
            TDataUniversityDetail detail = tDataUniversityDetailList.get(0);
            model.addAttribute("detail", detail);
            /* String logosql ="SELECT * from t_data_university WHERE univ_name='"+detail.getUnivName()+"'";*/
            //logo
            List<TDataUniversity> logoList = commonSvc.findByProperty(TDataUniversity.class, "univName", detail.getUnivName());
            if (logoList != null && logoList.size() > 0) {
                model.addAttribute("univLogo", logoList.get(0));
            }

            //院系专业
            String sqlIntroduce = "SELECT * from t_data_university_majorintroduce WHERE university_base_id='" + id + "' GROUP BY univ_major_cate_name";
            List<TDataUniversityMajorintroduce> introduce = commonSvc.findListbySql(sqlIntroduce, TDataUniversityMajorintroduce.class);
            if (introduce != null && introduce.size() > 0) {
                model.addAttribute("introduce", introduce);
            }

            List<TDataUniversityMajorintroduce> introduceMajor = commonSvc.findByProperty(TDataUniversityMajorintroduce.class, "universityBaseId", id);
            if (introduceMajor != null && introduceMajor.size() > 0) {
                model.addAttribute("introduceMajor", introduceMajor);
            }

            List<TDataUniversity> univList = commonSvc.findByProperty(TDataUniversity.class, "universityBaseId", id);
            if (univList != null && univList.size() > 0) {
                model.addAttribute("univ", univList.get(0));

                TDataUniversity univ = univList.get(0);
                //同类大学
                String sqlSame = "select * FROM t_data_university WHERE univ_type ='" + univ.getUnivType() + "' and univ_level ='" + univ.getUnivLevel() + "' and off_OR_vol='" + univ.getOffOrVol() + "' \n" +
                        "AND is211='" + univ.getIs211() + "' AND is985 ='" + univ.getIs985() + "' AND univ_name !='" + univ.getUnivName() + "'\n" +
                        "AND SUBSTR(univ_id,-2)= 10 LIMIT 5";
                List<TDataUniversity> listSame = commonSvc.findListbySql(sqlSame, TDataUniversity.class);
                if (listSame != null && listSame.size() > 0) {
                    model.addAttribute("listSame", listSame);
                }
                TBaseProvince pro = commonSvc.get(TBaseProvince.class, univList.get(0).getProvinceId());
                model.addAttribute("pro", pro);
            }

            String rankSql = "select * from t_data_major_rank where univ_name='" + detail.getUnivName() + "' order by rank";
            List<TDataMajorRank> tDataMajorRankList = commonSvc.findListbySql(rankSql, TDataMajorRank.class);
            if (tDataMajorRankList != null && tDataMajorRankList.size() > 0) {
                model.addAttribute("tDataMajorRankList", tDataMajorRankList);
            }
            //排名
            //薪酬排名

            String sqlrankSalay = "SELECT * from t_data_salary_university where university_base_id = '" + id + "'";
            List<TDataSalaryUniversity> ListSalary = commonSvc.findListbySql(sqlrankSalay, TDataSalaryUniversity.class);
            if (ListSalary != null && ListSalary.size() > 0) {
                TDataSalaryUniversity salay = ListSalary.get(0);
                model.addAttribute("salay", salay);
            }
            //校友会
            String sqlRankXyh = "SELECT * from t_data_university_rank WHERE base_id ='" + id + "' AND rank_type =1 ";
            List<TDataUniversityRank> listXyh = commonSvc.findListbySql(sqlRankXyh, TDataUniversityRank.class);
            if (listXyh != null && listXyh.size() > 0) {
                TDataUniversityRank xyh = listXyh.get(0);
                model.addAttribute("xyh", xyh);
            }

            //武书连
            String sqlRankWsl = "SELECT * from t_data_university_rank WHERE base_id ='" + id + "' AND rank_type =2 ";
            List<TDataUniversityRank> listWsl = commonSvc.findListbySql(sqlRankWsl, TDataUniversityRank.class);
            if (listWsl != null && listWsl.size() > 0) {
                TDataUniversityRank wsl = listWsl.get(0);
                model.addAttribute("wsl", wsl);
            }
            //qs
            String sqlRankQs = "SELECT * from t_data_university_inte_rank WHERE name='" + detail.getUnivName() + "' AND type =1 ORDER BY year DESC";
            List<TDataUniversityInteRank> listQs = commonSvc.findListbySql(sqlRankQs, TDataUniversityInteRank.class);
            if (listQs != null && listQs.size() > 0) {
                TDataUniversityInteRank qs = listQs.get(0);
                model.addAttribute("qs", qs);
            }
            //US
            String sqlRankUs = "SELECT * from t_data_university_inte_rank WHERE name='" + detail.getUnivName() + "' AND type =2 ORDER BY year DESC";
            List<TDataUniversityInteRank> listUs = commonSvc.findListbySql(sqlRankUs, TDataUniversityInteRank.class);
            if (listUs != null && listUs.size() > 0) {
                TDataUniversityInteRank us = listUs.get(0);
                model.addAttribute("us", us);
            }

            //招生章程
            List<TDataUniversityConstitution> conList = commonSvc.findByProperty(TDataUniversityConstitution.class, "universityBaseId", detail.getId());
            if (conList != null && conList.size() > 0) {
                model.addAttribute("conList", conList);
            }

//            录取分数线文科
            String historyUnivSql1 = "SELECT a.*,b.batch_name,(a.low_score - b.score) AS sub FROM t_cee_enroll_history a\n" +
                    "LEFT JOIN t_cee_batch b\n" +
                    "ON a.year = b.year AND a.province_id = b.province_id\n" +
                    "AND a.major_type_id = b.major_type_id\n" +
                    "AND a.batch_id = b.batch_id\n" +
                    "WHERE a.history_type = 0 \n" +
                    "AND a.univ_name = '" + detail.getUnivName() + "' AND a.province_id = " + user.getAttr().get("province_id") + "  \n" +
                    "AND a.major_type_id = 1 AND a.year > 2016 \n" +
                    "ORDER BY a.year DESC,a.batch_id ASC\n";

            List<Map<String, Object>> tUnivList1 = commonSvc.findForJdbc(historyUnivSql1, null);
            if (tUnivList1 != null && tUnivList1.size() > 0) {
                model.addAttribute("tUnivList1", tUnivList1);
            }

            // 录取分数线理科
            String historyUnivSql2 = "SELECT a.*,b.batch_name,(a.low_score - b.score) AS sub FROM t_cee_enroll_history a\n" +
                    "LEFT JOIN t_cee_batch b\n" +
                    "ON a.year = b.year AND a.province_id = b.province_id\n" +
                    "AND a.major_type_id = b.major_type_id\n" +
                    "AND a.batch_id = b.batch_id\n" +
                    "WHERE a.history_type = 0 \n" +
                    "AND a.univ_name = '" + detail.getUnivName() + "' AND a.province_id = " + user.getAttr().get("province_id") + "  \n" +
                    "AND a.major_type_id = 2 AND a.year > 2016 \n" +
                    "ORDER BY a.year DESC,a.batch_id ASC\n";

            List<Map<String, Object>> tUnivList2 = commonSvc.findForJdbc(historyUnivSql2, null);
            if (tUnivList2 != null && tUnivList2.size() > 0) {
                model.addAttribute("tUnivList2", tUnivList2);
            }

            //招生计划
            //理科
            String planSql2 = "SELECT a.*,b.batch_name FROM t_cee_enroll_plan a \n" +
                    "LEFT JOIN t_cee_batch b \n" +
                    "ON a.year = b.year\n" +
                    "AND a.province_id = b.province_id\n" +
                    "AND a.major_type_id = b.major_type_id\n" +
                    "AND a.batch_id = b.batch_id \n" +
                    "WHERE a.year = " + planYear + " AND LEFT(a.univ_id,5) = '" + detail.getId() + "' AND a.province_id= '" + user.getAttr().get("province_id") + "' AND a.major_type_id= 2 AND a.plan_type= 1 ORDER BY a.batch_id , a.univ_id ASC\n";

            List<Map<String, Object>> planSciences = commonSvc.findForJdbc(planSql2, null);
            if (planSciences != null && planSciences.size() > 0) {
                model.addAttribute("planSciences", planSciences);
            }
            //文科
            String planSql1 = "SELECT a.*,b.batch_name FROM t_cee_enroll_plan a \n" +
                    "LEFT JOIN t_cee_batch b \n" +
                    "ON a.year = b.year\n" +
                    "AND a.province_id = b.province_id\n" +
                    "AND a.major_type_id = b.major_type_id\n" +
                    "AND a.batch_id = b.batch_id \n" +
                    "WHERE a.year = " + planYear + " AND LEFT(a.univ_id,5) = '" + detail.getId() + "' AND a.province_id= '" + user.getAttr().get("province_id") + "' AND a.major_type_id= 1 AND a.plan_type= 1 ORDER BY a.batch_id , a.univ_id ASC\n";

            List<Map<String, Object>> planArts = commonSvc.findForJdbc(planSql1, null);

            if (planArts != null && planArts.size() > 0) {
                model.addAttribute("planArts", planArts);
            }

            //综合改革
            String planSql3 = "SELECT a.*,b.batch_name FROM t_cee_enroll_plan a \n" +
                    "LEFT JOIN t_cee_batch b \n" +
                    "ON a.year = b.year\n" +
                    "AND a.province_id = b.province_id\n" +
                    "AND a.major_type_id = b.major_type_id\n" +
                    "AND a.batch_id = b.batch_id \n" +
                    "WHERE a.year = " + planYear + " AND LEFT(a.univ_id,5) = '" + detail.getId() + "' AND a.province_id= '" + user.getAttr().get("province_id") + "' AND a.major_type_id= 3 AND a.plan_type= 1 ORDER BY a.batch_id , a.univ_id ASC\n";

            List<Map<String, Object>> planZong = commonSvc.findForJdbc(planSql3, null);

            if (planZong != null && planZong.size() > 0) {
                model.addAttribute("planZong", planZong);
            }

            model.addAttribute("tDataUniversityDetailList", tDataUniversityDetailList);

//            北京历史数据
            if (user.getAttr().get("province_id") != null && "1".equals(user.getAttr().get("province_id"))) {

                TBaseProvince tBaseProvince = commonSvc.get(TBaseProvince.class, Integer.valueOf(user.getAttr().get("province_id")));

                StringBuffer historySqlBf = new StringBuffer();
                historySqlBf.append("SELECT a.*,b.batch_name FROM t_cee_enroll_history a \n" +
                        "LEFT JOIN t_cee_batch b\n" +
                        "ON a.year = b.year\n" +
                        "AND a.province_id = b.province_id\n" +
                        "AND a.major_type_id = b.major_type_id\n" +
                        "AND a.batch_id = b.batch_id\n" +
                        "WHERE a.YEAR BETWEEN " + (tBaseProvince.getDataEnrollYear() - 3) + " AND " + tBaseProvince.getDataEnrollYear() + " \n" +
                        "AND a.province_id = " + tBaseProvince.getProvinceId() + " \n" +
                        "AND LEFT(a.univ_id,5) = '" + id + "' \n" +
                        "AND a.history_type = 0 ");

//                    "AND major_type_id = 2  \n" +
//                    "AND batch_id = 1 \n" +
                historySqlBf.append("ORDER BY a.YEAR DESC ,a.batch_id ASC  ");

                List<Map<String, Object>> tCeeEnrollHistoryList = commonSvc.findForJdbc(historySqlBf.toString());
                model.addAttribute("tCeeEnrollHistoryList", tCeeEnrollHistoryList);
            }
        }

        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), "wdedu/zytb", "yuanxiaojieshao");
    }

    //招生章程
    @RequestMapping(value = "/getConstitution.jspx")
    public void getConstitution(HttpServletRequest request, HttpServletResponse response, Integer id) throws IOException {
        CmsUser user = CmsUtils.getUser(request);
        List<TDataUniversityConstitution> list = commonSvc.findByProperty(TDataUniversityConstitution.class, "id", id);
        if (list != null && list.size() > 0) {
            TDataUniversityConstitution con = list.get(0);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(con));
        }
    }

    //专业分数线
    @RequestMapping(value = "/searchMajorHistory.jspx")
    public void searchMajorHistory(HttpServletRequest request, HttpServletResponse response, String univId, Integer mjorTypeId, Integer year) throws IOException {
        CmsUser user = CmsUtils.getUser(request);
        String provinceId = user.getAttr().get("province_id");
        /*Pagination pagination = findUnivSvc.searchMajorHistory(cpn(pageNo), CookieUtils.getPageSize(request), univName, mjorTypeId, year, provinceId);*/
        /* List<TCeeEnrollHistory> list = findUnivSvc.searchMajorHistory(univId, mjorTypeId, year, provinceId);*/
        String sql = "SELECT a.*,b.batch_name FROM  t_cee_enroll_history a \n" +
                "LEFT JOIN t_cee_batch b \n" +
                "ON a.year = b.year\n" +
                "AND a.province_id = b.province_id\n" +
                "AND a.major_type_id = b.major_type_id\n" +
                "AND a.batch_id = b.batch_id \n" +
                "WHERE 1 = 1 \n" +
                "AND LEFT(a.univ_id,5) = '" + univId + "'  \n" +
                "AND a.province_id ='" + provinceId + "'  \n" +
                "AND a.history_type= 1  \n" +
                "AND a.major_type_id= " + mjorTypeId + " \n" +
                "AND a.YEAR =" + year + "\n" +
                "ORDER BY a.batch_id ,a.univ_id ,a.major_code ASC";
//        String sql = "SELECT *FROM  t_cee_enroll_history  WHERE 1 = 1  AND left(univ_id,5) = '" + univId + "'  AND province_id ='" + provinceId + "'  AND history_type=1  AND major_type_id= '" + mjorTypeId + "'  AND YEAR ='" + year + "' ORDER BY batch_id ,univ_id ,major_code asc";
        List<Map<String, Object>> list = commonSvc.findForJdbc(sql, null);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(list));
    }

    //院校对比
    @RequestMapping(value = "/univCompare.jspx")
    public String univCompare(String univIds, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        //院校明细数据
        List<TDataUniversity> univList = findUnivSvc.findUvinData(univIds);
        // 省份列表
        List<TBaseProvince> provinceList = commonSvc.loadAll(TBaseProvince.class);
        //院校排名
        List<TDataUniversityRank> rankList = findUnivSvc.findUvinRank(univIds);
        //重点学科数量
        List countList = findUnivSvc.findUvinCount(univIds);
        //重点学科
        List<String> subjectList = new ArrayList<>();
        String[] ids = univIds.split(",");
        Arrays.sort(ids);
        for (String univId : ids) {
            List<TDataMajorImport> majorName = commonSvc.findListbySql("SELECT * FROM `t_data_major_import` WHERE univ_base_id=" + univId, TDataMajorImport.class);
            String strings = "";
            for (TDataMajorImport majorImport : majorName) {
                strings += majorImport.getMajorName() + "<br/>";
            }
            subjectList.add(strings);
        }
        //师资力量
        List<TDataUniversityDetail> szllList = findUnivSvc.findUvinSzll(univIds);
        //专业对比
        List<String> zydbList = new ArrayList<>();
        for (String univId : ids) {
            List<TDataMajorRank> majorRanks = commonSvc.findListbySql("SELECT * FROM `t_data_major_rank` WHERE univ_id=" + univId + " ORDER  BY rank", TDataMajorRank.class);
            String strings = "<table class='td-table'>";
            strings += "<tr>";
            strings += "<td class='td-table-td1'>专业名称</td>";
            strings += "<td class='td-table-td2'>专业排名</td>";
            strings += "<td class='td-table-td3'>专业等级</td>";
            strings += "</tr>";
            for (TDataMajorRank majorRank : majorRanks) {
                strings += "<tr>";
                strings += "<td class='td-table-td1'>" + majorRank.getMajorName() + "</td>";
                strings += "<td class='td-table-td2'>" + majorRank.getRank() + "</td>";
                strings += "<td class='td-table-td3'>" + majorRank.getGrade() + "</td>";
                strings += "</tr>";
            }
            strings += "</table>";
            zydbList.add(strings);
        }

        model.addAttribute("univList", univList);
        model.addAttribute("provinceList", provinceList);
        model.addAttribute("rankList", rankList);
        model.addAttribute("countList", countList);
        model.addAttribute("subjectList", subjectList);
        model.addAttribute("szllList", szllList);
        model.addAttribute("zydbList", zydbList);

        return FrontUtils.getTplPath(site.getSolutionPath(), "zytb", UNIVCOMPARE_REQUEST);
    }

    //专业列表
    @RequestMapping(value = "/searchMajor.jspx")
    public String searchMajor(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        List<TDataMajor> listBen = findMajorSvc.findBen();
        List<TDataMajor> listZhuan = findMajorSvc.findZhuan();

        //2级
        List<TDataMajor> listSecondBen = findMajorSvc.findSecond(listBen.get(0).getMajorId());
        //3级listZhuan
        List<TDataMajor> listThreeBen = findMajorSvc.findThree(listBen.get(0).getMajorId());

        //2级
        List<TDataMajor> listSecondZhuan = findMajorSvc.findSecond(listZhuan.get(0).getMajorId());
        //3级
        List<TDataMajor> listThreeZhuan = findMajorSvc.findThree(listZhuan.get(0).getMajorId());


        model.addAttribute("listBen", listBen);
        model.addAttribute("listZhuan", listZhuan);
        model.addAttribute("listSecondBen", listSecondBen);
        model.addAttribute("listThreeBen", listThreeBen);
        model.addAttribute("listSecondZhuan", listSecondZhuan);
        model.addAttribute("listThreeZhuan", listThreeZhuan);

        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), "wdedu/zytb", "chazhuanye");
    }

    //2级3级专业
    @RequestMapping(value = "/MajorSecThr.jspx")
    public void MajorSecThr(HttpServletRequest request, HttpServletResponse response, ModelMap model, String majorId) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        String major = "";
        if (majorId.length() == 1) {
            major = "0" + majorId;
        } else {
            major = majorId;
        }

        //2级
        List<TDataMajor> listSecond = findMajorSvc.findSecond(major);
        //3级
        List<TDataMajor> listThree = findMajorSvc.findThree(major);
        response.setCharacterEncoding("utf-8");
        Map<String, List<TDataMajor>> map = new HashMap();
        map.put("listSecond", listSecond);
        map.put("listThree", listThree);
        response.getWriter().write(JSON.toJSONString(map));

    }


    //根据专业名查专业
    @RequestMapping(value = "/searchMajorByMajorName.jspx")
    public void searchMajorByMajorName(HttpServletRequest request, HttpServletResponse response, String majorName) throws IOException {
        Map<String, List<TDataMajor>> map = new HashMap();
      /*  String sql ="SELECT * FROM t_data_major  WHERE major_name='"+majorName+"' AND LENGTH(major_id)>4 ";
        List<TDataMajor> list = commonSvc.findListbySql(sql,TDataMajor.class);
        if(list!=null&&list.size()>0){
            String parentId =list.get(0).getParentMajorId();
            List<TDataMajor> list2 = commonSvc.findByProperty(TDataMajor.class,"majorId",parentId);
            if(list2!=null&&list2.size()>0){
                map.put("list2",list2);
            }
            List<TDataMajor> list3 = commonSvc.findByProperty(TDataMajor.class,"parentMajorId",parentId);
            if(list3!=null&&list3.size()>0){
                map.put("list3",list3);
            }
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(map));
        }*/

        String sql = "SELECT * FROM t_data_major  WHERE major_name LIKE'%" + majorName + "%' AND LENGTH(major_id)>2 AND LENGTH(major_id)<6 ORDER BY major_id asc";
        List<TDataMajor> list = commonSvc.findListbySql(sql, TDataMajor.class);
        if (list != null && list.size() > 0) {
            map.put("list", list);
            String sql2 = "SELECT * FROM t_data_major  WHERE LENGTH(major_id)>4 ";
            List<TDataMajor> list2 = commonSvc.findListbySql(sql2, TDataMajor.class);
            map.put("list2", list2);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(map));
        }
    }

    //专业详情
    @RequestMapping(value = "/majorDetail.jspx")
    public String majorDetail(HttpServletRequest request, ModelMap model, Integer pageNo, String majorId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        if (majorId.length() < 6) {
            majorId = "0" + majorId;
        }
        List<TDataMajor> list = commonSvc.findByProperty(TDataMajor.class, "majorId", majorId);
        TDataMajor tDataMajor = list.get(0);
        Blob blob = tDataMajor.getRequirement();
        Map FindXiang = new HashMap();
        try {
            //专业名称
            if (null != tDataMajor.getMajorName()) {
//                        String majorname majorName= new String(blob.getBytes(1L, (int) blob.length()), "utf-8");
                FindXiang.put("majorName", tDataMajor.getMajorName());
                model.addAttribute("majorName", tDataMajor.getMajorName());
                model.addAttribute("univMajorId", tDataMajor.getMajorId());
            }

            //专业要求
            if (null != tDataMajor.getRequirement()) {
                String requirement = new String(blob.getBytes(1L, (int) blob.length()), "utf-8");
                FindXiang.put("requirement", requirement);
            }
            //专业介绍
            if (tDataMajor.getIntroduction() != null) {
                String introduction = new String(tDataMajor.getIntroduction().getBytes(1L, (int) tDataMajor.getIntroduction().length()), "utf-8");
                FindXiang.put("introduction", introduction);
            }
            //专业目标
            if (null != tDataMajor.getTarget()) {
                String target = new String(tDataMajor.getTarget().getBytes(1L, (int) tDataMajor.getTarget().length()), "utf-8");
                FindXiang.put("target", target);
            }
            //专业前景
            if (null != tDataMajor.getFuture()) {
                String future = new String(tDataMajor.getFuture().getBytes(1L, (int) tDataMajor.getFuture().length()), "utf-8");
                FindXiang.put("future", future);
            }
            //专业背景
            if (null != tDataMajor.getHistory()) {
                String history = new String(tDataMajor.getHistory().getBytes(1L, (int) tDataMajor.getHistory().length()), "utf-8");
                FindXiang.put("history", history);
            }
            //专业知识
            if (null != tDataMajor.getKnowledge()) {
                String knowledge = new String(tDataMajor.getKnowledge().getBytes(1L, (int) tDataMajor.getKnowledge().length()), "utf-8");
                FindXiang.put("knowledge", knowledge);
            }
            //大类专业介绍
            if (null != tDataMajor.getParentMajor()) {
                String parentMajor = new String(tDataMajor.getParentMajor().getBytes(1L, (int) tDataMajor.getParentMajor().length()), "utf-8");
                FindXiang.put("parentMajor", parentMajor);
            }
            //开设课程
            if (null != tDataMajor.getCourse()) {
                String course = new String(tDataMajor.getCourse().getBytes(1L, (int) tDataMajor.getCourse().length()), "utf-8");
                FindXiang.put("course", course);
            }
            //学生能力
            if (null != tDataMajor.getStudentCapacity()) {
                String studentCapacity = new String(tDataMajor.getStudentCapacity().getBytes(1L, (int) tDataMajor.getStudentCapacity().length()), "utf-8");
                FindXiang.put("studentCapacity", studentCapacity);
            }
            //就业去向
            if (null != tDataMajor.getEmploymentInfo()) {
                String employmentInfo = new String(tDataMajor.getEmploymentInfo().getBytes(1L, (int) tDataMajor.getEmploymentInfo().length()), "utf-8");
                FindXiang.put("employmentInfo", employmentInfo);
            }
            //著名学校
            if (null != tDataMajor.getFamousSchools()) {
                String famousSchools = new String(tDataMajor.getFamousSchools().getBytes(1L, (int) tDataMajor.getFamousSchools().length()), "utf-8");
                FindXiang.put("famousSchools", famousSchools);
            }
            //学位
            if (null != tDataMajor.getCareer()) {
                String career = new String(tDataMajor.getCareer().getBytes(1L, (int) tDataMajor.getCareer().length()), "utf-8");
                FindXiang.put("career", career);
            }
            //相似专业
            if (null != tDataMajor.getSimilarMajor()) {
                String similarMajor = new String(tDataMajor.getSimilarMajor().getBytes(1L, (int) tDataMajor.getSimilarMajor().length()), "utf-8");
                FindXiang.put("similarMajor", similarMajor);
            }
            //年份
            if (null != tDataMajor.getYear()) {
                String year = tDataMajor.getYear();
                FindXiang.put("year", year);
            }
            //专业内部id
            if (null != tDataMajor.getMajorId()) {
                String neiId = tDataMajor.getMajorId();
                FindXiang.put("neiId", neiId);
            }

            model.addAttribute("FindXiang", FindXiang);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //专业排名
        List<TDataSalaryMajor> listMajorRank = commonSvc.findByProperty(TDataSalaryMajor.class, "majorName", tDataMajor.getMajorName());
        if (listMajorRank != null && listMajorRank.size() > 0) {
            TDataSalaryMajor majorRank = listMajorRank.get(0);
            if (majorRank.getSalaryOnelevelRank() != null) {
                model.addAttribute("majorRank", majorRank.getSalaryOnelevelRank());
            }
        }
        //相同专业
        String parentMajorId = tDataMajor.getParentMajorId();
        List<TDataMajor> listSame = commonSvc.findByProperty(TDataMajor.class, "parentMajorId", parentMajorId);
        model.addAttribute("listSame", listSame);
        List<TDataMajor> maJorLv = commonSvc.findByProperty(TDataMajor.class, "majorId", parentMajorId);
        if (maJorLv != null && maJorLv.size() > 0) {
            model.addAttribute("maJorLv", maJorLv.get(0));
        }
        //就业方向
        List<TDataCareerMapMajorEntity> listCareer = findCareerSvc.findCareerByMajor(tDataMajor.getMajorName());
        if (listCareer != null && listCareer.size() > 0) {
            model.addAttribute("listCareer", listCareer);
        }
        //开设院校
        Pagination pagination = findUnivSvc.findUniv(cpn(pageNo), CookieUtils.getPageSize(request), tDataMajor.getMajorId());
        model.addAttribute("pagination", pagination);
        model.addAttribute("pageNo", pagination.getPageNo());
        //就业行业分布
        List<TDataEmployProfession> professionList = commonSvc.findByProperty(TDataEmployProfession.class, "majorId", majorId);
        if (professionList != null && professionList.size() > 0) {
            model.addAttribute("professionList", professionList);
        }
        //就业地区分布
        List<TDataEmployRegion> regionList = commonSvc.findByProperty(TDataEmployRegion.class, "majorId", majorId);
        if (regionList != null && regionList.size() > 0) {
            model.addAttribute("regionList", regionList);
        }
        //企业用人要求统计
        //工资情况
        List<TDataEmployCompany> salayList = findMajorSvc.findSalay(majorId);
        if (salayList != null && salayList.size() > 0) {
            model.addAttribute("salayList", salayList);
        }
        //经验要求
        List<TDataEmployCompany> experienceList = findMajorSvc.findExperience(majorId);
        if (experienceList != null && experienceList.size() > 0) {
            model.addAttribute("experienceList", experienceList);
        }
        //学历要求
        List<TDataEmployCompany> educationList = findMajorSvc.findEducation(majorId);
        if (educationList != null && educationList.size() > 0) {
            model.addAttribute("educationList", educationList);
        }


        //薪酬统计
        List<TDataSalaryMajor> salaryList = commonSvc.findByProperty(TDataSalaryMajor.class, "majorId", majorId);
        if (salaryList != null && salaryList.size() > 0) {
            model.addAttribute("salary", salaryList.get(0));
        } else {
            TDataSalaryMajor salaryMajor = new TDataSalaryMajor();
            model.addAttribute("salary", salaryMajor);
        }
       /* //1级学科排名
        if(listSame!=null&&listSame.size()>0){
            String onelevelId = listSame.get(0).getParentMajorId();
            List<Map<String, Object>> majorRankList = findMajorSvc.findOnelevelRank(onelevelId);
            if(majorRankList!=null&&majorRankList.size()>0){
                model.addAttribute("majorRankList", majorRankList);
            }
        }*/
        //父类专业
        List<TDataMajor> ListParMajor = commonSvc.findByProperty(TDataMajor.class, "majorId", parentMajorId);
        if (ListParMajor != null && ListParMajor.size() > 0) {
            TDataMajor parMajor = ListParMajor.get(0);
            model.addAttribute("parMajor", parMajor.getMajorName());
        }
        //专业门类排名
        List<TDataEmployIndex> catelogRankList = findMajorSvc.findCatelogRank(parentMajorId);
        if (catelogRankList != null && catelogRankList.size() > 0) {
            model.addAttribute("catelogRankList", catelogRankList);
        }
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), "wdedu/zytb", "zhuanyejieshao");
    }

    //专业学校分页下一页
    @RequestMapping(value = "/searchPage.jspx")
    public void page(HttpServletRequest request, HttpServletResponse response, Integer pageNo, String univMajorId, ModelMap model) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        Pagination pagination = findUnivSvc.findUniv(cpn(pageNo + 1), CookieUtils.getPageSize(request), univMajorId);
        model.addAttribute("pageNo", pageNo + 1);
        FrontUtils.frontData(request, model, site);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(pagination));
    }

    //专业学校分页上一页
    @RequestMapping(value = "/searchPageUp.jspx")
    public void pageUp(HttpServletRequest request, HttpServletResponse response, Integer pageNo, String univMajorId, ModelMap model) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        Pagination pagination = findUnivSvc.findUniv(cpn(pageNo - 1), CookieUtils.getPageSize(request), univMajorId);
        model.addAttribute("pageNo", pageNo - 1);
        FrontUtils.frontData(request, model, site);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(pagination));
    }

    //职业列表
    @RequestMapping(value = "/searchClass1.jspx")
    public String searchClass1(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        //职业大类
        List<TDataCareerIntroduceEntity> list = findCareerSvc.findCareer1();

        //2级
        List<TDataCareerIntroduceEntity> career2List = findCareerSvc.findCareer2(list.get(0).getCareerSubjeetId());
        //3级
        List<TDataCareerIntroduceEntity> career3List = findCareerSvc.findCareer3(list.get(0).getCareerSubjeetId());
        model.addAttribute("list", list);
        model.addAttribute("career2List", career2List);
        model.addAttribute("career3List", career3List);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), "wdedu/zytb", "kanjiuye");
    }


    //2,3级职业

    @RequestMapping(value = "/searchCareer.jspx")
    public void searchCareer(HttpServletRequest request, HttpServletResponse response, ModelMap model, String careerSubjeetId) throws IOException, JSONException {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);

        //2级
        List<Map<String, Object>> careerTwo = findCareerSvc.findTwo(careerSubjeetId);
        //3级
        List<Map<String, Object>> careerThr = findCareerSvc.findThr(careerSubjeetId);


        Map<String, Object> map = new HashMap();
        map.put("careerTwo", careerTwo);
        map.put("careerThr", careerThr);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));


    }

    //职业详情
    @RequestMapping(value = "/careerDatail.jspx", method = RequestMethod.GET)
    public String careerDatail(HttpServletRequest request, HttpServletResponse response, ModelMap model, String id) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        List<TDataCareerIntroduceEntity> list = commonSvc.findByProperty(TDataCareerIntroduceEntity.class, "careerOnlevelId", id);
        if (list != null && list.size() > 0) {
            TDataCareerIntroduceEntity careerEntity = list.get(0);
            model.addAttribute("careerEntity", careerEntity);

            //相关职业
            List<TDataCareerIntroduceEntity> sameCareer = commonSvc.findByProperty(TDataCareerIntroduceEntity.class, "careerCatelogId", careerEntity.getCareerCatelogId());
            if (sameCareer != null && sameCareer.size() > 0) {
                model.addAttribute("sameCareer", sameCareer);
            }

            //岗位
            List<TDataSalaryCareer> sataionList = commonSvc.findByProperty(TDataSalaryCareer.class, "careerOnlevelId", id);
            if (sataionList != null && sataionList.size() > 0) {
                model.addAttribute("sataionList", sataionList);
                model.addAttribute("sataionSalary", sataionList.get(0));

                //工资占比
                String salarySql = "SELECT * FROM t_data_career_company WHERE career_onlevel_id='" + id + "' And career_name ='" + sataionList.get(0).getCareerName() + "' And data_type=1 And data_type=1 limit 5";
                List<TDataCareerCompanyEntity> salaryPercent = commonSvc.findListbySql(salarySql, TDataCareerCompanyEntity.class);
                if (salaryPercent != null && salaryPercent.size() > 0) {
                    model.addAttribute("salaryPercent", salaryPercent);
                }

                //工资情况
                String percentSql = "SELECT * FROM t_data_career_company WHERE career_onlevel_id='" + id + "' And career_name ='" + sataionList.get(0).getCareerName() + "' And data_type=2 limit 5";
                List<TDataCareerCompanyEntity> salaryList = commonSvc.findListbySql(percentSql, TDataCareerCompanyEntity.class);
                if (salaryList != null && salaryList.size() > 0) {
                    model.addAttribute("salaryList", salaryList);
                }

                //学历要求
                String educationSql = "SELECT * FROM t_data_career_company WHERE career_onlevel_id='" + id + "' And career_name ='" + sataionList.get(0).getCareerName() + "' And data_type=3 limit 5";
                List<TDataCareerCompanyEntity> educationList = commonSvc.findListbySql(educationSql, TDataCareerCompanyEntity.class);
                if (educationList != null && educationList.size() > 0) {
                    model.addAttribute("educationList", educationList);
                }

                //地区薪酬排行
                String regionSql = "SELECT * FROM t_data_career_region WHERE career_onlevel_id='" + id + "' And career_name ='" + sataionList.get(0).getCareerName() + "' ORDER BY region_salary DESC LIMIT 7";
                List<TDataCareerRegion> regions = commonSvc.findListbySql(regionSql, TDataCareerRegion.class);
                if (regions != null && regions.size() > 0) {
                    model.addAttribute("regions", regions);
                }

               /* //地区需求量排行
                String regionNumSql ="SELECT * FROM t_data_career_region WHERE career_onlevel_id='"+id+"' And career_name ='"+sataionList.get(0).getCareerName()+"' ORDER BY region_sample DESC LIMIT 10";
                List<TDataCareerRegion> regionNums = commonSvc.findListbySql(regionNumSql,TDataCareerRegion.class);
                if(regions!=null&&regions.size()>0) {
                    model.addAttribute("regionNums", regionNums);
                }*/

                //行业竞争力
                String professionSql = "SELECT * FROM t_data_career_profession  WHERE career_onlevel_id='" + id + "' And career_name ='" + sataionList.get(0).getCareerName() + "' ORDER BY career_salary DESC LIMIT 7";
                List<TDataCareerProfession> professionList = commonSvc.findListbySql(professionSql, TDataCareerProfession.class);
                if (professionList != null && professionList.size() > 0) {
                    model.addAttribute("professionList", professionList);
                }

            }

          /*
            //相关专业
            List<TDataCareerMapMajorEntity> listMajor = commonSvc.findByProperty(TDataCareerMapMajorEntity.class,"jobName",careerEntity.getCareer());
            model.addAttribute("listMajor", listMajor);*/
        }

        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), "wdedu/zytb", "gangweizhize");
    }

    //获取岗位详情
    @RequestMapping(value = "/getStation.jspx")
    public void getStation(HttpServletRequest request, HttpServletResponse response, ModelMap model, String id, String name) throws IOException {

        response.setCharacterEncoding("utf-8");
        StationDetail stationDetail = new StationDetail();

        String sql = "SELECT * FROM t_data_salary_career WHERE career_onlevel_id='" + id + "' And career_name ='" + name + "'";
        List<TDataSalaryCareer> list = commonSvc.findListbySql(sql, TDataSalaryCareer.class);
        if (list != null && list.size() > 0) {
            stationDetail.setSalaryCareer(list.get(0));
        }

        //工资占比
        String salarySql = "SELECT * FROM t_data_career_company WHERE career_onlevel_id='" + id + "' And career_name ='" + name + "' And data_type=1 And data_type=1 limit 5";
        List<TDataCareerCompanyEntity> salaryPercent = commonSvc.findListbySql(salarySql, TDataCareerCompanyEntity.class);
        if (salaryPercent != null && salaryPercent.size() > 0) {
            stationDetail.setPercentList(salaryPercent);
        }

        //工资情况
        String percentSql = "SELECT * FROM t_data_career_company WHERE career_onlevel_id='" + id + "' And career_name ='" + name + "' And data_type=2 limit 5";
        List<TDataCareerCompanyEntity> salaryList = commonSvc.findListbySql(percentSql, TDataCareerCompanyEntity.class);
        if (salaryList != null && salaryList.size() > 0) {
            stationDetail.setSalaryList(salaryList);
        }

        //学历要求
        String educationSql = "SELECT * FROM t_data_career_company WHERE career_onlevel_id='" + id + "' And career_name ='" + name + "' And data_type=3 limit 5";
        List<TDataCareerCompanyEntity> educationList = commonSvc.findListbySql(educationSql, TDataCareerCompanyEntity.class);
        if (educationList != null && educationList.size() > 0) {
            stationDetail.setEducationList(educationList);
        }

        //地区薪酬排行
        String regionSql = "SELECT * FROM t_data_career_region WHERE career_onlevel_id='" + id + "' And career_name ='" + name + "' ORDER BY region_salary DESC LIMIT 7";
        List<TDataCareerRegion> regions = commonSvc.findListbySql(regionSql, TDataCareerRegion.class);
        if (regions != null && regions.size() > 0) {
            stationDetail.setRegionList(regions);
        }

        //行业竞争力
        String professionSql = "SELECT * FROM t_data_career_profession  WHERE career_onlevel_id='" + id + "' And career_name ='" + name + "' ORDER BY career_salary DESC LIMIT 7";
        List<TDataCareerProfession> professionList = commonSvc.findListbySql(professionSql, TDataCareerProfession.class);
        if (professionList != null && professionList.size() > 0) {
            stationDetail.setProfessionList(professionList);
        }

        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(stationDetail));
    }


}
