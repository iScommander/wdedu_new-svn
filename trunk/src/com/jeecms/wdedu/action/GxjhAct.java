package com.jeecms.wdedu.action;

import com.alibaba.fastjson.JSON;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsGroup;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.*;
import com.jeecms.wdedu.service.CommonSvc;
import com.jeecms.wdedu.service.ZytbSvc;
import com.utils.StringUtil;
import com.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

import static com.jeecms.common.page.SimplePage.cpn;

/**
 * @Description: 高校计划与历史数据
 * @Auther: Chenbo
 * @Date: 2019-01-05 13:06
 */
@Controller
public class GxjhAct {

    @Autowired
    private CommonSvc commonSvc;
    @Autowired
    private ZytbSvc zytbSvc;

    /**
     * 院校列表
     *
     * @param city
     * @param univProvince
     * @param univClassify 院校分类
     * @param univType     院校类型
     * @param fenlei       专业分类
     * @param majorName    专业名称
     * @param filterType   排名过滤类型
     * @param score        上下调整分数
     * @param pageNo
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/univList.jspx")
    public String univList(String univ, String major, String[] city, Integer[] univProvince, String[] univClassify, String[] univType,
                           String[] fenlei, String[] majorName, String filterType, Integer score, Integer pageNo,
                           Integer batchId, Integer provinceId, Integer majorTypeId,
                           HttpServletRequest request, HttpServletResponse response, ModelMap model, String provinceName) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (provinceId !=null&& provinceId > 0) {
            String proSql1 = "SELECT * FROM `t_base_province` WHERE province_id = '" + provinceId + "'";
            List<Map<String, Object>> proList1 = commonSvc.findForJdbc(proSql1);
            String pro_name1 = String.valueOf(proList1.get(0).get("province_name"));
            if (StringUtils.isNotBlank(provinceName)) {
                if (provinceName.equals(pro_name1)) {
                    model.addAttribute("provinceName", pro_name1);
                } else {
                    model.addAttribute("provinceName", provinceName);
                }
            } else {
                model.addAttribute("provinceName", pro_name1);
            }
        }
        String proid = "1";
        //用户省份
        if (user.getAttr().get("province_id")!=null) {
            proid = user.getAttr().get("province_id");
        }

//        String proid = user.getAttr().get("province_id");
        Integer proid2 = Integer.parseInt(proid);
        String proSql = "SELECT * FROM `t_base_province` WHERE province_id = " + proid2 + "";
        List<Map<String, Object>> proList = commonSvc.findForJdbc(proSql);
        String pro_name = String.valueOf(proList.get(0).get("province_name"));
        //用户城市名称
        String cityId = user.getAttr().get("city_id");
        String cityName = null;
        if (StringUtils.isNotBlank(cityId)) {
            String sql = "SELECT DISTINCT city_name FROM t_base_location WHERE city_id='" + cityId + "'";
            List<Map<String, Object>> cityList = commonSvc.findForJdbc(sql);
            cityName = String.valueOf(cityList.get(0).get("city_name"));
        }
        FrontUtils.frontData(request, model, site);

        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        //省份id
        if (!StringUtil.isNotEmpty(provinceId)) {
            provinceId = Integer.valueOf(user.getAttr().get("province_id"));
        }
        //文理科id
        if (!StringUtil.isNotEmpty(majorTypeId)) {
            majorTypeId = Integer.valueOf(user.getAttr().get("major_type_id"));
        }
        //根据省份取最大年份
        Integer year = 2018, batchYear = 2018, dataPlanYear = 2018;

        if (StringUtil.isNotEmpty(provinceId)) {
            TBaseProvince tBaseProvince = commonSvc.getEntity(TBaseProvince.class, provinceId);
            dataPlanYear = tBaseProvince.getDataPlanYear();
            year = tBaseProvince.getDataEnrollYear();
            batchYear = tBaseProvince.getDataBatchYear();
        }

        // 省份列表
        List<TBaseProvince> provinceList = commonSvc.loadAll(TBaseProvince.class);
        model.addAttribute("provinceList", provinceList);
        // 院校分类
        List<TDataUniversityType> universityType = commonSvc.loadAll(TDataUniversityType.class);
        model.addAttribute("universityType", universityType);
        //查询批次信息
        List<TCeeBatch> batchList = zytbSvc.getBatchList(batchYear, provinceId, majorTypeId);
        model.addAttribute("batchList", batchList);

        String hql = "FROM TCeeEnrollUnivList bean WHERE 1=1 ";
        Finder f = Finder.create(hql);
        if (StringUtil.isNotEmpty(city)) {
            f.append(" and bean.univCity in (:univCity) ").setParamList("univCity", city);
        }
        //省份多选数据回填
        String proName = "";
        if (StringUtil.isNotEmpty(univProvince)) {
            f.append(" and bean.univProvince in (:univProvince) ").setParamList("univProvince", univProvince);

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
        if (StringUtil.isNotEmpty(univClassify)) {
            f.append(" and bean.univType in(:univClassify)").setParamList("univClassify", univClassify);
        }
        if (StringUtil.isNotEmpty(univType)) {
            String type = StringUtil.joinString(univType, "-");
            if (type.contains("1")) {
                //985学校
                f.append(" and bean.univIs985=1 ");
            }
            if (type.contains("2")) {
                //211学校
                f.append(" and bean.univIs211=1 ");
            }
            if (type.contains("3")) {
                //国防生
                f.append(" and bean.univIsDefence=1 ");
            }
            if (type.contains("4")) {
                //卓越计划
                f.append(" and bean.univIsExcellent=1 ");
            }
            if (type.contains("5")) {
                //自主招生
                f.append(" and bean.univIsIndependence=1 ");
            }
            if (type.contains("6")) {
                //一流大学
                f.append(" and bean.univIsFirstRateUniv=1 ");
            }
            if (type.contains("7")) {
                //一流学科
                f.append(" and bean.univIsFirstRateMajor=1 ");
            }
        }

        if (StringUtil.isNotEmpty(year)) {
            f.append(" and bean.year=:year").setParam("year", dataPlanYear);
        }

        Integer LastprovinceId = 0;
        if (StringUtils.isNotBlank(provinceName)) {
            String sql = "select province_id from t_base_province where province_name = '" + provinceName + "'";
            Integer province_id = Integer.valueOf(commonSvc.findForJdbc(sql).get(0).get("province_id").toString());

            if (province_id.equals(provinceId)) {
                f.append(" and bean.provinceId=:province").setParam("province", provinceId);
                LastprovinceId = provinceId;
            } else {
                f.append(" and bean.provinceId=:province").setParam("province", province_id);
                LastprovinceId = province_id;
            }
        } else {
            if (StringUtil.isNotEmpty(provinceId)) {
                f.append(" and bean.provinceId=:province").setParam("province", provinceId);
                LastprovinceId = provinceId;
            }
        }
        if (StringUtil.isNotEmpty(majorTypeId)) {
            f.append(" and bean.majorTypeId=:majorType").setParam("majorType", majorTypeId);
        }
        if (StringUtil.isNotEmpty(batchId)) {
            f.append(" and bean.batchId=:batch").setParam("batch", batchId);
        }
//        if (StringUtil.isNotEmpty(univ)) {
////            (bean.univ_name LIKE '%电子科技大学%' or bean.univ_id in (SELECT DISTINCT major.univ_id FROM `t_cee_enroll_major_list` as major WHERE major.plan_or_history=1 AND major.major_name like '%电子科技大学%'))
//            f.append(" and (bean.univName like '%" +univ+ "%' or bean.univId in (SELECT DISTINCT major.univId FROM TCeeEnrollMajorList major WHERE major.planOrHistory=1 AND major.majorName like '%" +univ+ "%'))");
//        }
        if (StringUtil.isNotEmpty(univ)) {
//            SELECT * FROM `t_cee_enroll_univ_list`  WHERE univ_name LIKE '北京大学'  AND YEAR=2017 AND province_id=17 AND major_type_id=2 AND batch_id=1
//            f.append(" and bean.univName like '%"+String.valueOf(requestMap.get("univOrMajor"))+"%'");
//            appendHql(year, province, majorType, batch, f);
//            f.append(" ORDER BY bean.rankScoreLow1  ASC NULLS LAST , bean.rankScoreLow2  ASC NULLS LAST, bean.rankScoreLow3  ASC NULLS LAST, bean.rankScoreLow4  ASC NULLS LAST");
//            Pagination pagination = find(f, pageNo, pageSize);
//            if (pagination.getList().size() > 0){
//                return pagination;
//            }else {
////                SELECT * FROM `t_cee_enroll_univ_list`  WHERE  univ_id IN (SELECT DISTINCT univ_id FROM `t_cee_enroll_major_list` WHERE major_name LIKE '%计算机科学与技术%' AND YEAR=2017  AND province_id=17 AND major_type_id=2 AND batch_id=2 AND plan_or_history=1 )  AND YEAR=2017  AND province_id=17 AND major_type_id=2 AND batch_id=2
////                SELECT DISTINCT univ_id FROM `t_cee_enroll_major_list` WHERE major_name LIKE '%计算机科学与技术%' AND YEAR=2017  AND province_id=17 AND major_type_id=2 AND batch_id=2 AND plan_or_history=1
//                String hq="FROM TCeeEnrollUnivList bean WHERE 1=1 ";
//                Finder finder=Finder.create(hq);
//
//                //查专业对应的univId
//                String h = "SELECT DISTINCT univId FROM TCeeEnrollMajorList bean WHERE 1=1 and planOrHistory=1";
//                Finder fin=Finder.create(h);
//                fin.append(" and majorName like '%"+String.valueOf(requestMap.get("univOrMajor"))+"%'");
//                appendHql(year, province, majorType, batch, fin);
//                List list = find(fin);
//
//                //查对应univId的院校信息
//                if (list.size() > 0){
//                    finder.append(" and bean.univId in (:univId) ").setParamList("univId", list);
//                }
//
//                appendHql(year, province, majorType, batch, finder);
//                finder.append(" ORDER BY bean.rankScoreLow1  ASC NULLS LAST , bean.rankScoreLow2  ASC NULLS LAST, bean.rankScoreLow3  ASC NULLS LAST, bean.rankScoreLow4  ASC NULLS LAST");
//                return find(finder, pageNo, pageSize);
//            }
            f.append(" and (bean.univName like '%" + univ + "%' )");
        }

        if (StringUtil.isNotEmpty(major)) {
            f.append(" and ( bean.univId in (SELECT DISTINCT major.univId FROM TCeeEnrollMajorList major WHERE major.planOrHistory= 1 and major.year = '" + year + "' AND major.provinceId ='" + LastprovinceId + "' and major.majorTypeId = '" + majorTypeId + "' AND major.majorName like '%" + major + "%')) ");
        }

        f.append(" ORDER BY bean.rankScoreLow1  ASC NULLS LAST , bean.rankScoreLow2  ASC NULLS LAST, bean.rankScoreLow3  ASC NULLS LAST, bean.rankScoreLow4  ASC NULLS LAST");

        Pagination pagination = commonSvc.findPager(f, cpn(pageNo), CookieUtils.getPageSize(request));

        model.addAttribute("year", year);
        model.addAttribute("pagination", pagination);
        if (city == null) {
            model.addAttribute("city", cityName);
        } else {
            model.addAttribute("city", StringUtil.joinString(city, ","));
        }

        model.addAttribute("proName", proName);
        if (StringUtil.isNotEmpty(univProvince)) {
            model.addAttribute("pro_name", proName);
        } else {
            model.addAttribute("proName", pro_name);
        }
        model.addAttribute("dataPlanYear", dataPlanYear);
        model.addAttribute("univClassify", StringUtil.joinString(univClassify, ","));
        model.addAttribute("univType", StringUtil.joinString(univType, ","));
//        model.addAttribute("fenlei",fenlei);
//        model.addAttribute("majorName",majorName);
        model.addAttribute("filterType", filterType);
        model.addAttribute("score", score);
        model.addAttribute("batchId", batchId);
        model.addAttribute("majorType", majorTypeId);
        model.addAttribute("provinceId", provinceId);
//        model.addAttribute("univOrMajor",univOrMajor);
        model.addAttribute("univ", univ);
        model.addAttribute("major", major);

        return FrontUtils.getTplPath(site.getSolutionPath(), "zytb", "univList");
    }

    /**
     * 招生计划
     *
     * @param univId
     * @param batchId
     * @param majorTypeId
     * @param provinceId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/enrollmentPlan.jspx")
    public String enrollmentPlan(Integer univId, Integer batchId, Integer majorTypeId, Integer provinceId,
                                 Integer year, Integer univCode, HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);

        // 省份列表
        List<TBaseProvince> provinceList = commonSvc.loadAll(TBaseProvince.class);
        model.addAttribute("provinceList", provinceList);

        TBaseProvince tBaseProvince = commonSvc.findUniqueByProperty(TBaseProvince.class, "provinceId", Integer.valueOf(user.getAttr().get("province_id")));
        int planYear = tBaseProvince.getDataPlanYear();

        //招生计划
        String hql = "FROM TCeeEnrollMajorList WHERE univId = '" + univId + "' AND year =" + planYear + " AND provinceId = '" + provinceId + "' " +
                "AND majorTypeId = '" + majorTypeId + "' AND batchId = '" + batchId + "' AND planOrHistory = '1' ORDER BY data_type, major_code";
        List<TCeeEnrollMajorList> tCeeEnrollPlanList = commonSvc.findByQueryString(hql);
        model.addAttribute("tCeeEnrollPlanList", tCeeEnrollPlanList);

//        //历年数据
        String hql1 = " FROM TCeeEnrollHistory  WHERE  year IN(" + (year - 3) + "," + (year - 2) + "," + (year - 1) + "," + year + ") AND provinceId = '" + provinceId + "' AND majorTypeId = '" + majorTypeId + "' AND batchId = '" + batchId + "' AND univId = '" + univId + "'  AND historyType = 0  ORDER BY year DESC";
//        List<TCeeEnrollHistory> tCeeEnrollHistoryList = commonSvc.findByQueryString(hql1);
//        String sql = "SELECT * FROM `t_cee_batch` WHERE YEAR = " + year + " AND province_id = '" + province + "' AND major_type_id='" + majorType + "' AND is_show = 1 ";

        String sql = "SELECT * FROM t_cee_enroll_history\n" +
                "WHERE YEAR BETWEEN " + (year - 3) + " and " + year + " " +
                "AND province_id = " + provinceId + " \n" +
                "AND major_type_id = " + majorTypeId + " \n" +
                "AND batch_id = " + batchId + " \n" +
                "AND univ_id = " + univId + " \n" +
                "AND history_type = 0 \n" +
                "ORDER BY YEAR DESC , low_score DESC";

        List<Map<String, Object>> tCeeEnrollHistoryList = commonSvc.findForJdbc(sql);

        model.addAttribute("tCeeEnrollHistoryList", tCeeEnrollHistoryList);

        //招生章程&自招章程
        TDataUniversityDetail universityDetail = commonSvc.getEntity(TDataUniversityDetail.class, Integer.parseInt(String.valueOf(univId).substring(0, 5)));
        model.addAttribute("universityDetail", universityDetail);
        TDataUniversity university = commonSvc.findUniqueByProperty(TDataUniversity.class, "univId", univId);
        model.addAttribute("university", university);
        model.addAttribute("year", year);
        model.addAttribute("univId", univId);
        model.addAttribute("provinceId", provinceId);
        model.addAttribute("majorTypeId", majorTypeId);
        model.addAttribute("batchId", batchId);
        model.addAttribute("univCode", univCode);
        if (provinceId == 1 || provinceId == 2 || provinceId == 3) {
            model.addAttribute("qubie", 1);
        } else {
            model.addAttribute("qubie", 2);
        }
        return FrontUtils.getTplPath(site.getSolutionPath(), "zytb", "collegePlans");
    }


    @RequestMapping(value = "/queryHistoryPlanDoor.jspx")
    public String queryHistoryPlanDoor(HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer province_id, Integer major_type_id,
                                       Integer batch_id, Integer univ_id, Integer year) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
//        String hql =  "FROM TCeeEnrollHistory  WHERE  year ='"+year+"' AND provinceId = '" + province_id + "' AND majorTypeId = '" + major_type_id + "' AND batchId = '" + batch_id + "' AND univId = '" + univ_id + "'  AND historyType in(0,1,2,3)   ORDER BY year DESC";
//        List<TCeeEnrollHistory> tCeeEnrollHistoryList = commonSvc.findByQueryString(hql);
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("success", true);
        returnMap.put("msg", "queryHistoryPlan.jspx?province_id=" + province_id + "&major_type_id=" + major_type_id + "&batch_id=" + batch_id + "&univ_id=" + univ_id + "&year=" + year);
        String json = JSON.toJSONString(returnMap);
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    @RequestMapping(value = "/queryHistoryPlan.jspx")
    public String queryHistoryPlan(HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer province_id, Integer major_type_id,
                                   Integer batch_id, Integer univ_id, Integer year) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        String firstRow = " FROM TCeeEnrollHistory  WHERE year ='" + year + "' AND provinceId = '" + province_id + "' AND majorTypeId = '" + major_type_id + "' AND batchId = '" + batch_id + "' AND univId = '" + univ_id + "'  AND historyType = 0 ";
        List<TCeeEnrollHistory> tCeeEnrollHistorys = commonSvc.findByQueryString(firstRow);
        model.addAttribute("tCeeEnrollHistorys", tCeeEnrollHistorys);
        String hql = "FROM TCeeEnrollHistory  WHERE  year ='" + year + "' AND provinceId = '" + province_id + "' AND majorTypeId = '" + major_type_id + "' AND batchId = '" + batch_id + "' AND univId = '" + univ_id + "'  AND historyType =0   ORDER BY historyType ASC , batchIdOld ASC ,rankLowScore ASC NULLS LAST";
        String hql1 = "FROM TCeeEnrollHistory  WHERE  year ='" + year + "' AND provinceId = '" + province_id + "' AND majorTypeId = '" + major_type_id + "' AND batchId = '" + batch_id + "' AND univId = '" + univ_id + "'  AND historyType =1   ORDER BY historyType ASC , batchIdOld ASC ,rankLowScore ASC NULLS LAST";
        String hql2 = "FROM TCeeEnrollHistory  WHERE  year ='" + year + "' AND provinceId = '" + province_id + "' AND majorTypeId = '" + major_type_id + "' AND batchId = '" + batch_id + "' AND univId = '" + univ_id + "'  AND historyType =2   ORDER BY historyType ASC , batchIdOld ASC ,rankLowScore ASC NULLS LAST";
        String hql3 = "FROM TCeeEnrollHistory  WHERE  year ='" + year + "' AND provinceId = '" + province_id + "' AND majorTypeId = '" + major_type_id + "' AND batchId = '" + batch_id + "' AND univId = '" + univ_id + "'  AND historyType =3   ORDER BY historyType ASC , batchIdOld ASC ,rankLowScore ASC NULLS LAST";
        List<TCeeEnrollHistory> tCeeEnrollHistoryList = commonSvc.findByQueryString(hql);
        List<TCeeEnrollHistory> tCeeEnrollHistoryList1 = commonSvc.findByQueryString(hql1);
        List<TCeeEnrollHistory> tCeeEnrollHistoryList2 = commonSvc.findByQueryString(hql2);
        List<TCeeEnrollHistory> tCeeEnrollHistoryList3 = commonSvc.findByQueryString(hql3);
        model.addAttribute("tCeeEnrollHistoryList", tCeeEnrollHistoryList);
        model.addAttribute("tCeeEnrollHistoryList1", tCeeEnrollHistoryList1);
        model.addAttribute("tCeeEnrollHistoryList2", tCeeEnrollHistoryList2);
        model.addAttribute("tCeeEnrollHistoryList3", tCeeEnrollHistoryList3);
        model.addAttribute("provinceId", province_id);
        model.addAttribute("batchId", batch_id);
        if (province_id != 1 && province_id != 2 && province_id != 3) {
            return FrontUtils.getTplPath(site.getSolutionPath(), "schoolDetail", "remote2");
        }
        return FrontUtils.getTplPath(site.getSolutionPath(), "schoolDetail", "remote");

    }

}
