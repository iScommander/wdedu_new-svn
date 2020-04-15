package com.jeecms.wdedu.action;

import com.alibaba.fastjson.JSON;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.*;
import com.jeecms.wdedu.service.CommonSvc;
import com.jeecms.wdedu.service.FindMajorSvc;
import com.jeecms.wdedu.service.FindUnivSvc;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
 * @Description: 找大学 查专业
 * @date 2018/10/24
 */
@Controller
@RequestMapping(value = "/FindUniv")
public class FindUnivAct {
    private final static String TPLDIR_FINDUNIVLIST = "univAct";
    private final static String UNIVLIST_REQUEST = "univList";
    private final static String UNIVMAJOR_REQUEST = "univMajor";
    private final static String UNIVSCHOOL_REQUEST = "univSchool";
    private final static String UNIVCOMPARE_REQUEST = "univCompare";

    @Autowired
    private FindUnivSvc findUnivSvc;
    @Autowired
    private FindMajorSvc findMajorSvc;
    @Autowired
    private CommonSvc commonSvc;

    private List<TDataUniversity> tDataUniversityList;
    private List<TDataMajor> tDataMajorList;
    private List<TDataMajor> tDataMajorList1;
    private List<TDataMajor> tDataMajorList2;
    private List<TDataMajor> tDataMajors;
    private List<TDataUniversityDetail> tDataUniversityDetailList;
    private List<TDataUniversityDetail> tDataUniversityDetailList1;
    private List<TDataUniversityDetail> tDataUniversityDetailList2;
    private List<TDataUniversityDetail> tDataUniversityDetailList3;
    private List<TDataUniversityDetail> tDataUniversityDetailList4;
    private List<TDataMajorRank> tDataMajorRankList;

    @RequiresPermissions("ZhaoDaXue:*")
    @RequestMapping(value = "/findUnivList.jspx")
    public String FindUnivList(Integer pageNo, String provinceIds, Integer pageSize, String univName, Integer[] provinceId, String location, String univType,
                               String offOrVol, String univLevel, Integer[] is211, Integer[] is985, Integer[] isKeylab, Integer[] isIndependence,
                               Integer[] isDefence, HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        FrontUtils.frontData(request, model, site);
        if (provinceId == null) {
            provinceId = new Integer[1];
        }
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
        if (univType != null && univType != "") {
            String[] strings = univType.split(",");
            univType = strings[0];
            model.addAttribute("univType", strings[0]);
        }
        if (offOrVol != null && offOrVol != "") {
            String[] offOrVols = offOrVol.split(",");
            offOrVol = offOrVols[0];
            model.addAttribute("offOrVol", offOrVols[0]);
        }
        if (univLevel != null && univLevel != "") {
            String[] univLevels = univLevel.split(",");
            univLevel = univLevels[0];
            model.addAttribute("univLevel", univLevels[0]);
        }
        Pagination pagination = findUnivSvc.getPage(cpn(pageNo), CookieUtils.getPageSize(request), provinceId[0], univType, offOrVol, univLevel, is211[0], is985[0], isKeylab[0], isIndependence[0], isDefence[0], location, univName);
        model.addAttribute("provinceId", provinceId[0]);
        model.addAttribute("univLevel", univLevel);
        model.addAttribute("is211", is211[0]);
        model.addAttribute("is985", is985[0]);
        model.addAttribute("isKeylab", isKeylab[0]);
        model.addAttribute("isIndependence", isIndependence[0]);
        model.addAttribute("isDefence", isDefence[0]);
        model.addAttribute("pagination", pagination);
        model.addAttribute("pageNo", pagination.getPageNo());
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_FINDUNIVLIST, UNIVLIST_REQUEST);
    }

    @RequestMapping(value = "/univCompare.jspx")
    public String univCompare(String univIds, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

//        Integer province = Integer.valueOf(user.getAttr().get("province_id"));
//        Integer majorType = Integer.valueOf(user.getAttr().get("major_type_id"));

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

        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_FINDUNIVLIST, UNIVCOMPARE_REQUEST);
    }

    @RequestMapping(value = "/schoolDetails.jspx")
    public String schoolDetails(HttpServletRequest request, ModelMap model, Integer id, Integer provinceId, String univName) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        try {
            tDataMajorRankList = commonSvc.findByProperty(TDataMajorRank.class, "univName", univName);
            tDataUniversityDetailList = commonSvc.findByProperty(TDataUniversityDetail.class, "univName", univName);
            tDataUniversityDetailList2 = commonSvc.findByProperty(TDataUniversityDetail.class, "univName", univName);
            String hql = "FROM  TCeeEnrollHistory WHERE univName = '" + univName + "' AND YEAR = 2016 AND provinceId='" + user.getAttr().get("province_id") + "'";
            String hql1 = "FROM  TCeeEnrollHistory WHERE univName = '" + univName + "' AND YEAR = 2017 AND provinceId='" + user.getAttr().get("province_id") + "'";
            String hql2 = "FROM  TCeeEnrollHistory WHERE univName = '" + univName + "' AND YEAR = 2018 AND provinceId='" + user.getAttr().get("province_id") + "'";
            tDataUniversityDetailList1 = commonSvc.findByQueryString(hql);
            tDataUniversityDetailList3 = commonSvc.findByQueryString(hql1);
            tDataUniversityDetailList4 = commonSvc.findByQueryString(hql2);
        } catch (Exception e) {
            e.printStackTrace();
        }


//        tCeeEnrollUnivListList  = commonSvc.findByProperty(TCeeEnrollUnivList.class,"id",id);

//        tDataUniversityDetailList1 = findUnivSvc.queryById(univName);
        String hql = "FROM TCeeEnrollPlan WHERE univName = '" + univName + "' AND YEAR =2016 AND provinceId = '" + user.getAttr().get("province_id") + "'";
        String hql1 = "FROM TCeeEnrollPlan WHERE univName = '" + univName + "' AND YEAR =2017 AND provinceId = '" + user.getAttr().get("province_id") + "'";
        String hql2 = "FROM TCeeEnrollPlan WHERE univName = '" + univName + "' AND YEAR =2018 AND provinceId = '" + user.getAttr().get("province_id") + "'";
        List<TCeeEnrollPlan> tCeeEnrollPlan = commonSvc.findByQueryString(hql);
        List<TCeeEnrollPlan> tCeeEnrollPlan1 = commonSvc.findByQueryString(hql1);
        List<TCeeEnrollPlan> tCeeEnrollPlan2 = commonSvc.findByQueryString(hql2);
        model.addAttribute("tCeeEnrollPlan", tCeeEnrollPlan);
        model.addAttribute("tCeeEnrollPlan1", tCeeEnrollPlan1);
        model.addAttribute("tCeeEnrollPlan2", tCeeEnrollPlan2);
        model.addAttribute("tDataUniversityDetailList", tDataUniversityDetailList);
        model.addAttribute("tDataMajorRankList", tDataMajorRankList);
        model.addAttribute("tDataUniversityDetailList1", tDataUniversityDetailList1);
        model.addAttribute("tDataUniversityDetailList3", tDataUniversityDetailList3);
        model.addAttribute("tDataUniversityDetailList4", tDataUniversityDetailList4);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_FINDUNIVLIST, UNIVSCHOOL_REQUEST);
    }

    @RequestMapping(value = "/findUnivdo.jspx")
    public String FindUnivList(HttpServletRequest request, ModelMap model, Integer provinceId, String univType, String offORvol, String univLevel, Integer is211, Integer is985, Integer isKeylab, Integer isIndependence, Integer isDefence) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        FrontUtils.frontData(request, model, site);
        tDataUniversityList = findUnivSvc.tDataUnivList(provinceId, univType, offORvol, univLevel, is211, is985, isKeylab, isIndependence, isDefence);
        model.addAttribute("tDataUniversityList", tDataUniversityList);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_FINDUNIVLIST, UNIVLIST_REQUEST);
    }

    @RequiresPermissions("ChaZhuanYe:*")
    @RequestMapping(value = "/findMajor.jspx")
    public String findMajor(HttpServletRequest request, HttpServletResponse response, ModelMap model, String parentMajorId, String majorId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        tDataMajorList = findMajorSvc.findBen();
        tDataMajors = findMajorSvc.findZhuan();
        if (parentMajorId != null) {
            tDataMajorList1 = findMajorSvc.finBenNext(parentMajorId);
            response.setCharacterEncoding("utf-8");
            try {
                response.getWriter().write(JSON.toJSONString(tDataMajorList1));
                return "";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (majorId != null) {
            tDataMajorList2 = commonSvc.findByProperty(TDataMajor.class, "majorId", majorId);
            response.setCharacterEncoding("utf-8");
            for (int i = 0; i < tDataMajorList2.size(); i++) {
                TDataMajor tDataMajor = tDataMajorList2.get(i);
                Blob blob = tDataMajor.getRequirement();
                Map FindXiang = new HashMap();
                try {
                    //专业名称
                    if (null != tDataMajor.getMajorName()) {
//                        String majorname = new String(blob.getBytes(1L, (int) blob.length()), "utf-8");
                        FindXiang.put("majorname", tDataMajor.getMajorName());
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
                    if (null != tDataMajor.getStudentCapacity()) {
                        String studentCapacity = new String(tDataMajor.getStudentCapacity().getBytes(1L, (int) tDataMajor.getStudentCapacity().length()), "utf-8");
                        FindXiang.put("studentCapacity", studentCapacity);
                    }
                    if (null != tDataMajor.getEmploymentInfo()) {
                        String employmentInfo = new String(tDataMajor.getEmploymentInfo().getBytes(1L, (int) tDataMajor.getEmploymentInfo().length()), "utf-8");
                        FindXiang.put("employmentInfo", employmentInfo);
                    }
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
                    if (null != tDataMajor.getYear()) {
                        String year = tDataMajor.getYear();
                        FindXiang.put("year", year);
                    }
                    if (null != tDataMajor.getMajorId()) {
                        String neiId = tDataMajor.getMajorId();
                        FindXiang.put("neiId", neiId);
                    }
//                    FindXiang.put("target",target);
//                    FindXiang.put("future",future);
//                    FindXiang.put("history",history);
//                    FindXiang.put("knowledge",knowledge);
//                    FindXiang.put("course",course);
//                    FindXiang.put("similarMajor",similarMajor);
//                    FindXiang.put("year",year);
//                    FindXiang.put("studentCapacity",studentCapacity);
//                    FindXiang.put("employmentInfo",employmentInfo);
//                    FindXiang.put("famousSchools",famousSchools);
                    model.addAttribute("FindXiang", FindXiang);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        model.addAttribute("tDataMajorList", tDataMajorList);
        model.addAttribute("tDataMajors", tDataMajors);
        model.addAttribute("tDataMajorList1", tDataMajorList1);

        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_FINDUNIVLIST, UNIVMAJOR_REQUEST);
    }
}
