package com.jeecms.wdedu.action;

import com.alibaba.fastjson.JSON;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.*;
import com.jeecms.wdedu.service.CommonSvc;
import com.utils.StringUtil;
import com.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

@RequestMapping(value = "/tanchuang")
public class TanChuangController {

    private static final Logger LOG = LoggerFactory.getLogger(TanChuangController.class);
    public final static String TPLDIR_ZYTB = "zytb";
    @Autowired
    private CommonSvc commonSvc;

    //填志愿接口填志愿弹窗
    @RequestMapping(value = "/tianzhiyuan.jspx")
    public String zhuanye(String applicationId, String univListId, Integer batchId, Integer univOrder, Integer majorNum, String isFormal,String schoolOrMajorName, Integer pageNo, Integer setPageNum, HttpServletResponse response, HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);

//        String sql = "SELECT * FROM t_cee_enroll_fill_list WHERE data_type = 1 AND  univ_list_id = '" + univListId + "' ORDER BY hist_low_score1 DESC , hist_low_rank1 ASC ,hist_low_score2 DESC , hist_low_rank2 ASC ,hist_low_score3 DESC , hist_low_rank3 ASC ,\n" +
//                "hist_low_score11 DESC , hist_low_rank11 ASC ,hist_low_score21 DESC , hist_low_rank21 ASC ,hist_low_score31 DESC , hist_low_rank31 ASC";

        if(StringUtils.isNotBlank(schoolOrMajorName)) {
            try {
                schoolOrMajorName = URLDecoder.decode(schoolOrMajorName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("schoolOrMajorName",schoolOrMajorName);

        String sql = "SELECT * FROM t_cee_enroll_fill_list WHERE data_type = 1 AND  univ_list_id = '"+univListId+"' \n" +
                "ORDER BY hist_low_score1 DESC , hist_low_rank1 ASC ,hist_low_score2 DESC , hist_low_rank2 ASC ,hist_low_score3 DESC , hist_low_rank3 ASC ,\n" +
                "hist_low_score11 DESC , hist_low_rank11 ASC ,hist_low_score21 DESC , hist_low_rank21 ASC ,hist_low_score31 DESC , hist_low_rank31 ASC";
        List<TCeeEnrollFillList> TCeeEnrollFillListList = commonSvc.findListbySql(sql, TCeeEnrollFillList.class);
        String backShowSql = "SELECT DISTINCT univ_id,univ_code,univ_name,major_id,major_code,major_name,major_num_id FROM t_cee_applications_detail\n" +
                "WHERE application_id = '" + applicationId + "'\n" +
                "AND batch_id = " + batchId + "\n" +
                "AND major_num_id IS NOT NULL ";
        List<Map<String, Object>> backShowMap = commonSvc.findForJdbc(backShowSql);
        TCeeApplications tCeeApplications = commonSvc.getEntity(TCeeApplications.class, Integer.valueOf(applicationId));

        List<TCeeEnrollFillListTmp> list = new ArrayList<>();
        for (TCeeEnrollFillList tCeeEnrollFillList : TCeeEnrollFillListList) {
            TCeeEnrollFillListTmp tCeeEnrollFillListTmp = new TCeeEnrollFillListTmp();
            tCeeEnrollFillListTmp.setChild(tCeeEnrollFillList);
            list.add(tCeeEnrollFillListTmp);
        }

//        浙江判断专业科目限制
        if (tCeeApplications.getProvinceId() == 18) {
            String userSubjects = tCeeApplications.getSubjects();
            for (int i = 0; i < list.size(); i++) {


                String listMajorSubjectsLevel = list.get(i).getMajorSubjectsLevel();
                String listMajorSubjects = list.get(i).getMajorSubjects();
                String[] listMajorSubjectsS = listMajorSubjects.split(",");

                switch (listMajorSubjectsLevel) {
//                    不限 有资格
                    case "0":
                        list.get(i).setIsQualification(1);
                        break;

//                        同时满足
                    case "1":
                        if (listMajorSubjectsS.length == 2) {
                            if (userSubjects.contains(listMajorSubjectsS[0]) && userSubjects.contains(listMajorSubjectsS[1])) {
                                list.get(i).setIsQualification(1);
                            } else {
                                list.get(i).setIsQualification(0);
                            }
                        } else if (listMajorSubjectsS.length == 3) {
                            if (userSubjects.contains(listMajorSubjectsS[0]) && userSubjects.contains(listMajorSubjectsS[1]) && userSubjects.contains(listMajorSubjectsS[2])) {
                                list.get(i).setIsQualification(1);
                            } else {
                                list.get(i).setIsQualification(0);
                            }
                        }
//                        或者满足
                        break;
                    case "2":
                        if (listMajorSubjectsS.length == 2) {
                            if (userSubjects.contains(listMajorSubjectsS[0]) || userSubjects.contains(listMajorSubjectsS[1])) {
                                list.get(i).setIsQualification(1);
                            } else {
                                list.get(i).setIsQualification(0);
                            }
                        } else if (listMajorSubjectsS.length == 3) {
                            if (userSubjects.contains(listMajorSubjectsS[0]) || userSubjects.contains(listMajorSubjectsS[1]) || userSubjects.contains(listMajorSubjectsS[2])) {
                                list.get(i).setIsQualification(1);
                            } else {
                                list.get(i).setIsQualification(0);
                            }
                        }
                        break;
                    case "3":
                        if (userSubjects.contains(listMajorSubjects)) {
                            list.get(i).setIsQualification(1);
                        } else {
                            list.get(i).setIsQualification(0);
                        }
                        break;
                    default:
                        list.get(i).setIsQualification(0);
                }
            }
        }
        model.addAttribute("list", list);

        TCeeEnrollUnivList tCeeEnrollUnivList = commonSvc.get(TCeeEnrollUnivList.class, Integer.valueOf(univListId));
        TBaseProvince tBaseProvince = commonSvc.get(TBaseProvince.class, tCeeApplications.getProvinceId());
        model.addAttribute("tCeeApplications", tCeeApplications);
        model.addAttribute("tCeeEnrollUnivList", tCeeEnrollUnivList);
        model.addAttribute("tBaseProvince", tBaseProvince);
        model.addAttribute("backShowMap", backShowMap);
        model.addAttribute("applicationId", applicationId);
        model.addAttribute("univListId", univListId);
        model.addAttribute("batchId", batchId);
        model.addAttribute("univOrder", univOrder);
        model.addAttribute("majorNum", majorNum);
        model.addAttribute("isFormal", isFormal);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("setPageNum", setPageNum);
        String major_result = "tanchuang";
        if(tBaseProvince.getProvinceId()== 1 || tBaseProvince.getProvinceId() == 16 ){
            major_result = "tanchuang_bj";
        }
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, major_result);
    }

    //填志愿接口填志愿弹窗智能
    @RequestMapping(value = "/tianzhiyuanFast.jspx")
    public String tianzhiyuanFast(String applicationId, String univListId, Integer batchId, Integer univOrder, Integer majorNum, String isFormal, Integer pageNo, Integer setPageNum, HttpServletResponse response, HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        String sql = "SELECT * FROM t_cee_enroll_fill_list WHERE data_type = 1 AND  univ_list_id = '" + univListId + "'";
        List<TCeeEnrollFillList> TCeeEnrollFillListList = commonSvc.findListbySql(sql, TCeeEnrollFillList.class);

        String tCeeApplicationsRequireHql = "FROM TCeeApplicationsRequire\n" +
                "WHERE applicationId = '" + applicationId + "' \n" +
                "AND batchId = " + batchId + " ";
        TCeeApplicationsRequire tCeeApplicationsRequire = commonSvc.singleResult(tCeeApplicationsRequireHql);

        if (tCeeApplicationsRequire.getIntentMajor() != null) {
            String backShowSql = "SELECT * FROM t_cee_enroll_fill_list\n" +
                    "WHERE id IN (" + tCeeApplicationsRequire.getIntentMajor().replace("|", ",") + ")";
            List<TCeeEnrollFillList> tCeeEnrollFillLists = commonSvc.findListbySql(backShowSql, TCeeEnrollFillList.class);
            model.addAttribute("tCeeEnrollFillLists", tCeeEnrollFillLists);
        }
        TCeeApplications tCeeApplications = commonSvc.getEntity(TCeeApplications.class, Integer.valueOf(applicationId));
        List<TCeeEnrollFillListTmp> list = new ArrayList<>();
        for (TCeeEnrollFillList tCeeEnrollFillList : TCeeEnrollFillListList) {
            TCeeEnrollFillListTmp tCeeEnrollFillListTmp = new TCeeEnrollFillListTmp();
            tCeeEnrollFillListTmp.setChild(tCeeEnrollFillList);
            list.add(tCeeEnrollFillListTmp);
        }
        //        浙江判断专业科目限制
        if (tCeeApplications.getProvinceId() == 18) {
            String userSubjects = tCeeApplications.getSubjects();
            for (int i = 0; i < list.size(); i++) {


                String listMajorSubjectsLevel = list.get(i).getMajorSubjectsLevel();
                String listMajorSubjects = list.get(i).getMajorSubjects();
                String[] listMajorSubjectsS = listMajorSubjects.split(",");

                switch (listMajorSubjectsLevel) {
//                    不限 有资格
                    case "0":
                        list.get(i).setIsQualification(1);
                        break;
//                        同时满足
                    case "1":
                        if (listMajorSubjectsS.length == 2) {
                            if (userSubjects.contains(listMajorSubjectsS[0]) && userSubjects.contains(listMajorSubjectsS[1])) {
                                list.get(i).setIsQualification(1);
                            } else {
                                list.get(i).setIsQualification(0);
                            }
                        } else if (listMajorSubjectsS.length == 3) {
                            if (userSubjects.contains(listMajorSubjectsS[0]) && userSubjects.contains(listMajorSubjectsS[1]) && userSubjects.contains(listMajorSubjectsS[2])) {
                                list.get(i).setIsQualification(1);
                            } else {
                                list.get(i).setIsQualification(0);
                            }
                        }
//                        或者满足
                        break;
                    case "2":
                        if (listMajorSubjectsS.length == 2) {
                            if (userSubjects.contains(listMajorSubjectsS[0]) || userSubjects.contains(listMajorSubjectsS[1])) {
                                list.get(i).setIsQualification(1);
                            } else {
                                list.get(i).setIsQualification(0);
                            }
                        } else if (listMajorSubjectsS.length == 3) {
                            if (userSubjects.contains(listMajorSubjectsS[0]) || userSubjects.contains(listMajorSubjectsS[1]) || userSubjects.contains(listMajorSubjectsS[2])) {
                                list.get(i).setIsQualification(1);
                            } else {
                                list.get(i).setIsQualification(0);
                            }
                        }
                        break;
                    case "3":
                        if (userSubjects.contains(listMajorSubjects)) {
                            list.get(i).setIsQualification(1);
                        } else {
                            list.get(i).setIsQualification(0);
                        }
                        break;
                    case "4":
                        if (listMajorSubjectsS.length == 2) {
                            if (userSubjects.contains(listMajorSubjectsS[0]) && userSubjects.contains(listMajorSubjectsS[1])) {
                                list.get(i).setIsQualification(1);
                            } else {
                                list.get(i).setIsQualification(0);
                            }
                        } else if (listMajorSubjectsS.length == 3) {
                            if (userSubjects.contains(listMajorSubjectsS[0]) && userSubjects.contains(listMajorSubjectsS[1]) && userSubjects.contains(listMajorSubjectsS[2])) {
                                list.get(i).setIsQualification(1);
                            } else {
                                list.get(i).setIsQualification(0);
                            }
                        }
                        break;
                    case "5":
                        if (listMajorSubjectsS.length == 2) {
                            if (userSubjects.contains(listMajorSubjectsS[0]) || userSubjects.contains(listMajorSubjectsS[1])) {
                                list.get(i).setIsQualification(1);
                            } else {
                                list.get(i).setIsQualification(0);
                            }
                        } else if (listMajorSubjectsS.length == 3) {
                            if (userSubjects.contains(listMajorSubjectsS[0]) || userSubjects.contains(listMajorSubjectsS[1]) || userSubjects.contains(listMajorSubjectsS[2])) {
                                list.get(i).setIsQualification(1);
                            } else {
                                list.get(i).setIsQualification(0);
                            }
                        }
                        break;
                    default:
                        list.get(i).setIsQualification(0);
                }
            }
        }

        model.addAttribute("list",list);
        TBaseProvince tBaseProvince = commonSvc.get(TBaseProvince.class, tCeeApplications.getProvinceId());
        TCeeEnrollUnivList tCeeEnrollUnivList = commonSvc.get(TCeeEnrollUnivList.class, Integer.valueOf(univListId));
        model.addAttribute("tCeeApplications", tCeeApplications);
        model.addAttribute("tCeeEnrollUnivList", tCeeEnrollUnivList);
        model.addAttribute("tBaseProvince", tBaseProvince);
        model.addAttribute("applicationId", applicationId);
        model.addAttribute("univListId", univListId);
        model.addAttribute("batchId", batchId);
        model.addAttribute("univOrder", univOrder);
        model.addAttribute("majorNum", majorNum);
        model.addAttribute("isFormal", isFormal);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("setPageNum", setPageNum);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, "tanchuang_fast");
    }

    //保存专业
    @RequestMapping(value = "/saveApplication.jspx")
    public void saveApplication(HttpServletResponse response, String applicationId, String univListId, Integer batchId, Integer univOrder, Integer majorNum, String isFormal, String fillListId, ModelMap model) {

        TCeeApplications tCeeApplications = commonSvc.get(TCeeApplications.class, Integer.valueOf(applicationId));
        TCeeBatch tCeeBatch = new TCeeBatch();
        String batchSql = " FROM TCeeBatch\n" +
                "WHERE YEAR = " + tCeeApplications.getYear() + " \n" +
                "AND provinceId = " + tCeeApplications.getProvinceId() + "\n" +
                "AND majorTypeId = " + tCeeApplications.getMajorTypeId() + " \n" +
                "AND batchId = " + batchId + " ";
        tCeeBatch = commonSvc.singleResult(batchSql);

        TCeeEnrollUnivList tCeeEnrollUnivList = commonSvc.get(TCeeEnrollUnivList.class, Integer.valueOf(univListId));
        if (tCeeEnrollUnivList != null) {
            String sqlDetail = "SELECT * FROM t_cee_applications_detail \n" +
                    "WHERE application_id = " + applicationId + " \n" +
                    "AND province_id = " + tCeeEnrollUnivList.getProvinceId() + "\n" +
                    "AND major_type_id = " + tCeeEnrollUnivList.getMajorTypeId() + "\n" +
                    "AND batch_id = " + tCeeEnrollUnivList.getBatchId() + "\n" +
                    "AND is_formal = " + isFormal + "\n" +
                    "AND (\n" +
                    "(\n" +
                    "univ_id = '" + tCeeEnrollUnivList.getUnivId() + "'\n" +
                    "AND univ_code = '" + tCeeEnrollUnivList.getUnivCode() + "'\n" +
                    "AND univ_name = '" + tCeeEnrollUnivList.getUnivName() + "'\n" +
                    ")\n" +
                    "OR \n" +
                    "univ_order = " + univOrder + " )";
            List<TCeeApplicationsDetail> tCeeApplicationsDetails = commonSvc.findListbySql(sqlDetail, TCeeApplicationsDetail.class);

            if (tCeeApplicationsDetails != null) {
//                commonSvc.deleteAllEntitie(tCeeApplicationsDetails);
                for (TCeeApplicationsDetail tCeeApplicationsDetailToDelete : tCeeApplicationsDetails) {
                    commonSvc.delete(tCeeApplicationsDetailToDelete);
                }
            }
        }
        int i = 1;
        if (!"".equals(fillListId)) {
            fillListId = fillListId.substring(0, fillListId.length() - 1);
            String sqlFill = "SELECT * FROM t_cee_enroll_fill_list\n" +
                    "WHERE id IN ( " + fillListId + " )\n" +
                    "ORDER BY FIELD(id," + fillListId + ")";
            List<TCeeEnrollFillList> tCeeEnrollFillLists = commonSvc.findListbySql(sqlFill, TCeeEnrollFillList.class);
            for (TCeeEnrollFillList tCeeEnrollFillList : tCeeEnrollFillLists) {
                TCeeApplicationsDetail tCeeApplicationsDetail = new TCeeApplicationsDetail();
                tCeeApplicationsDetail.setApplicationId(Integer.parseInt(applicationId));
                tCeeApplicationsDetail.setYear(tCeeEnrollFillList.getYear());
                tCeeApplicationsDetail.setProvinceId(tCeeEnrollFillList.getProvinceId());
                tCeeApplicationsDetail.setMajorTypeId(tCeeEnrollFillList.getMajorTypeId());
                tCeeApplicationsDetail.setBatchId(tCeeEnrollFillList.getBatchId());
                tCeeApplicationsDetail.setUnivId(tCeeEnrollFillList.getUnivId());
                tCeeApplicationsDetail.setUnivCode(tCeeEnrollFillList.getUnivCode());
                tCeeApplicationsDetail.setUnivName(tCeeEnrollFillList.getUnivName());
                tCeeApplicationsDetail.setUnivOrder(univOrder);
                tCeeApplicationsDetail.setMajorId(tCeeEnrollFillList.getMajorId());
                tCeeApplicationsDetail.setMajorCode(tCeeEnrollFillList.getMajorCode());
                tCeeApplicationsDetail.setMajorName(tCeeEnrollFillList.getMajorName());
                tCeeApplicationsDetail.setMajorOrder(i);
                tCeeApplicationsDetail.setMajorNumId(String.valueOf(tCeeEnrollFillList.getId()));
                tCeeApplicationsDetail.setIsObey("1");
                tCeeApplicationsDetail.setIsFormal(Integer.valueOf(isFormal));

                tCeeApplicationsDetail.setUnivMajorGroup(tCeeEnrollFillList.getUnivMajorGroup());
                tCeeApplicationsDetail.setUnivTestRemark(tCeeEnrollFillList.getUnivTestRemark());


                i++;
                commonSvc.save(tCeeApplicationsDetail);
            }
        }

        StringBuffer nullTableSql = new StringBuffer();
        nullTableSql.append(" SELECT * FROM ( ");
        if ("0".equals(isFormal)) {
            for (int j = 1; j <= tCeeBatch.getUnivNum(); j++) {
                if (j == tCeeBatch.getUnivNum()) {
                    nullTableSql.append("SELECT " + j + " AS number \n" +
                            ") a \n ");
                } else {
                    nullTableSql.append("SELECT " + j + " AS number \n" +
                            "UNION \n ");
                }
            }
        } else if ("1".equals(isFormal)) {

            for (int k = 1; k <= tCeeBatch.getExtraNum(); k++) {
                if (k == tCeeBatch.getExtraNum()) {
                    nullTableSql.append("SELECT " + k + " AS number \n" +
                            ") a \n ");
                } else {
                    nullTableSql.append("SELECT " + k + " AS number \n" +
                            "UNION \n ");
                }
            }
        }
        nullTableSql.append("WHERE number NOT IN (\n" +
                "SELECT DISTINCT univ_order FROM t_cee_applications_detail \n" +
                "WHERE application_id = '" + applicationId + "' \n" +
                "AND batch_id = " + batchId + "\n" +
                "AND is_formal = " + isFormal + " ) \n");

        List<Map<String, Object>> nullTableList = commonSvc.findForJdbc(String.valueOf(nullTableSql));
        for (Map<String, Object> map : nullTableList) {
            TCeeApplicationsDetail tCeeApplicationsDetail = new TCeeApplicationsDetail();
            tCeeApplicationsDetail.setApplicationId(Integer.parseInt(applicationId));
            tCeeApplicationsDetail.setYear(tCeeApplications.getYear());
            tCeeApplicationsDetail.setProvinceId(tCeeApplications.getProvinceId());
            tCeeApplicationsDetail.setMajorTypeId(tCeeApplications.getMajorTypeId());
            tCeeApplicationsDetail.setBatchId(batchId);
            tCeeApplicationsDetail.setUnivOrder(Integer.valueOf(map.get("number").toString()));
            tCeeApplicationsDetail.setMajorOrder(0);
            tCeeApplicationsDetail.setIsObey("1");
            tCeeApplicationsDetail.setIsFormal(Integer.valueOf(isFormal));
            commonSvc.save(tCeeApplicationsDetail);
        }

        model.addAttribute("majorNum", majorNum);
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("success", true);
        returnMap.put("msg", "保存成功");
        returnResp(response, returnMap);
        return;
    }

    //删除专业
    @RequestMapping(value = "/deleteApplication.jspx")
    public void deleteApplication(HttpServletResponse response, String id, String applicationId) {
        Map map = commonSvc.findOneForJdbc("SELECT * FROM t_cee_enroll_fill_list WHERE id = '" + id + "'");
        String univ_code = String.valueOf(map.get("univ_code"));
        String major_code = String.valueOf(map.get("major_code"));
        String sql = "detele FROM `t_cee_applications_detail` WHERE application_id='" + applicationId + "' AND univ_code = '" + univ_code + "' AND major_code='" + major_code + "'";
        commonSvc.executeSql(sql);
    }

    //保存意向学校
//    @RequestMapping(value = "/saveYX.jspx")
//    public void saveYX(String batchId, String provinceId, String univ_is985, String univ_is211, String univ_isDefence,
//                       String univ_isExcellent, String univ_isIndependence, String univ_isFirstRateUniv,
//                       String univ_isFirstRateMajor, String univSchoolType, HttpServletRequest request, String applicationId, String marjorId) {
//        CmsSite site = CmsUtils.getSite(request);
//        CmsUser user = CmsUtils.getUser(request);
//        String sql = "SELECT * FROM t_cee_applications_require WHERE stu_user_id='" + user.getId() + "' AND application_id='" + applicationId + "' AND batch_id='" + batchId + "'";
//        List<TCeeApplicationsRequire> list = commonSvc.findListbySql(sql, TCeeApplicationsRequire.class);
//        String major = "";
//        int advanced_univ_num = 0;
//        int stable_univ_num = 0;
//        int backward_univ_num = 0;
//        String intent_univ = "";
//        String avoid_univ = "";
//        if (list.size() > 0) {
//            major = list.get(0).getIntentMajorId();
//            advanced_univ_num = list.get(0).getAdvancedUnivNum();
//            stable_univ_num = list.get(0).getStableUnivNum();
//            backward_univ_num = list.get(0).getBackwardUnivNum();
//            intent_univ = list.get(0).getIntentUniv();
//            avoid_univ = list.get(0).getAvoidUniv();
//            String deleteSql = "delete from t_cee_applications_require where stu_user_id='" + user.getId() + "' and application_id='" + applicationId + "' and batch_id = '" + batchId + "'";
//            commonSvc.executeSql(deleteSql);
//        }
//        TCeeApplicationsRequire tCeeApplicationsRequire = new TCeeApplicationsRequire();
//        tCeeApplicationsRequire.setStuUserId(user.getId());
//        tCeeApplicationsRequire.setApplicationId(Integer.valueOf(applicationId));
//        tCeeApplicationsRequire.setBatchId(Integer.valueOf(batchId));
//        tCeeApplicationsRequire.setIntentProvinceId(provinceId.replaceAll(",", "\\|"));
//        tCeeApplicationsRequire.setIntentUnivType(univSchoolType.replaceAll(",", "\\|"));
//        tCeeApplicationsRequire.setIs211(Integer.valueOf(univ_is211));
//        tCeeApplicationsRequire.setIs985(Integer.valueOf(univ_is985));
//        tCeeApplicationsRequire.setIsExcellent(Integer.valueOf(univ_isExcellent));
//        tCeeApplicationsRequire.setIsIndependence(Integer.valueOf(univ_isIndependence));
//        if (StringUtil.isNotEmpty(univ_isFirstRateUniv)) {
//            if (StringUtil.isNotEmpty(univ_isFirstRateMajor)) {
//                tCeeApplicationsRequire.setIsFirstRate(1);
//            } else {
//                tCeeApplicationsRequire.setIsFirstRate(0);
//            }
//        } else {
//            if (StringUtil.isNotEmpty(univ_isFirstRateMajor)) {
//                tCeeApplicationsRequire.setIsFirstRate(2);
//            } else {
//                tCeeApplicationsRequire.setIsFirstRate(0);
//            }
//        }
//        tCeeApplicationsRequire.setIntentMajorId(marjorId);
//        tCeeApplicationsRequire.setAdvancedUnivNum(advanced_univ_num);
//        tCeeApplicationsRequire.setStableUnivNum(stable_univ_num);
//        tCeeApplicationsRequire.setBackwardUnivNum(backward_univ_num);
//        tCeeApplicationsRequire.setIntentUniv(intent_univ);
//        tCeeApplicationsRequire.setAvoidUniv(avoid_univ);
//        commonSvc.save(tCeeApplicationsRequire);
//    }

    /**
     * 智能填报保存筛选条件
     */
    @RequestMapping(value = "/saveZNChoose.jspx")
    public void saveZNChoose(HttpServletRequest request, HttpServletResponse response, String batchId, String
            univProvince, String univ_is985, String univ_is211, String univ_isDefence, String univ_isExcellent,
                             String univ_isIndependence, String univ_isFirstRateUniv, String univ_isFirstRateMajor, String
                                     schoolMajorType, String applicationId) {
        CmsUser user = CmsUtils.getUser(request);
        String hql = "SFROM TCeeApplicationsRequire WHERE stuUserId='" + user.getId() + "' AND applicationId='" + applicationId + "' AND batchId='" + batchId + "'";
        TCeeApplicationsRequire tCeeApplicationsRequire = commonSvc.singleResult(hql);
        if (StringUtils.isNotBlank(batchId)) {
            tCeeApplicationsRequire.setBatchId(Integer.valueOf(batchId));
        }
        if (StringUtils.isNotBlank(univProvince)) {
            tCeeApplicationsRequire.setIntentProvinceId(univProvince.replaceAll(",", "\\|"));
        }
        if (StringUtils.isNotBlank(univ_is985)) {
            tCeeApplicationsRequire.setIs985(1);
        }
        if (StringUtils.isNotBlank(univ_is211)) {
            tCeeApplicationsRequire.setIs211(1);
        }
        if (StringUtils.isNotBlank(univ_isExcellent)) {
            tCeeApplicationsRequire.setIsExcellent(1);
        }
        if (StringUtils.isNotBlank(univ_isIndependence)) {
            tCeeApplicationsRequire.setIsIndependence(1);
        }
        if (StringUtil.isNotEmpty(univ_isFirstRateUniv)) {
            if (StringUtil.isNotEmpty(univ_isFirstRateMajor)) {
                tCeeApplicationsRequire.setIsFirstRate(1);
            } else {
                tCeeApplicationsRequire.setIsFirstRate(0);
            }
        } else {
            if (StringUtil.isNotEmpty(univ_isFirstRateMajor)) {
                tCeeApplicationsRequire.setIsFirstRate(2);
            } else {
                tCeeApplicationsRequire.setIsFirstRate(0);
            }
        }
        commonSvc.saveOrUpdate(tCeeApplicationsRequire);
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

}
