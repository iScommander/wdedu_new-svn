package com.jeecms.wdedu.action;

import com.alibaba.fastjson.JSON;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.TBaseProvince;
import com.jeecms.wdedu.entity.TDataUniversity;
import com.jeecms.wdedu.entity.TDataUniversityDetail;
import com.jeecms.wdedu.service.BkckSvc;
import com.jeecms.wdedu.service.CommonSvc;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.jeecms.common.page.SimplePage.cpn;
import static com.jeecms.wdedu.action.BkyxAct.BKCL_INPUT;
import static com.jeecms.wdedu.action.BkyxAct.ZXXQ;

/**
 * 报考参考
 */
@Controller
@RequestMapping(value = "/bkck")
public class BkckAct {

    public static final String REFERENCE = "reference";
    public static final String ONE_SEGMENT_QUERY = "one_segment_query";
    public static final String BATCH_QUERY = "batch_query";
    public static final String ENROLMENT_PLAN = "enroll_plan";
    public static final String HISTORY_ENROLL = "history_enroll";
    public static final String ENROLL_RULES = "enroll_rules";
    public static final String ENROLL_RULES_DETAIL = "enroll_rules_detail";
    @Autowired
    private BkckSvc bkckSvc;
    @Autowired
    private CommonSvc commonSvc;

    /**
     * 一分段查询
     *
     * @param province
     * @param year
     * @param majorType
     * @param score
     * // Alltodo: 2019/11/11 一分一段表对接新页面及跳转，设置非内部人员无法切换省份
     */
    /*@RequiresPermissions("YiFenYiDuan:*")*/
    @RequestMapping(value = "/one_segment_query.jspx")
    public String oneSegmentQuery(Integer province, Integer year, Integer majorType, Integer score, Integer pageNo,
                                  HttpServletRequest request, HttpServletResponse response, ModelMap model, String ksType) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        String kstype = ksType;
        model.addAttribute("kstype", kstype);

      /*  Integer provinceId = Integer.valueOf(user.getAttr().get("province_id"));
        if (user.getTopRoleLevel() < 10) {
//         初始化用户信息
            province = provinceId;
            majorType = Integer.valueOf(user.getAttr().get("major_type_id"));
        }*/

//        是否内部人员
        Integer isAdmin = 0;
        if (user.getAdmin()) {
            isAdmin = 1;
        }

        model.addAttribute("isAdmin", isAdmin);

        //根据省份取最大年份
        if (null != province) {
            TBaseProvince tBaseProvince = commonSvc.getEntity(TBaseProvince.class, province);
            year = tBaseProvince.getDataScoreYear();
        } else {
            year = 2019;
        }

        String sql = " SELECT a.province_id,a.major_type_id,a.score,a.low_rank as lowrank,a.rank,b.low_rank as lowrank1,b.rank as rank1,c.low_rank as lowrank2,c.rank as rank2 ";
        String countSql = " SELECT count(1) ";

        String formSql = " FROM t_cee_score_rank  a  LEFT JOIN (SELECT province_id,year,major_type_id,score,low_rank,rank FROM t_cee_score_rank ) b ON b.score = a.score AND a.major_type_id = b.major_type_id AND a.province_id = b.province_id AND b.year =" + (year - 1);
        formSql += " LEFT JOIN (SELECT province_id,year,major_type_id,score,low_rank,rank FROM t_cee_score_rank ) c  ON b.score = c.score AND a.major_type_id = c.major_type_id AND a.province_id = c.province_id AND c.year =" + (year - 2);

        String whereSql = " WHERE a.year=" + year;
        if (null != province) {
            whereSql += " and a.province_id=" + province;
        }
        if (null != majorType) {
            whereSql += " and a.major_type_id=" + majorType;
        }
        if (null != score) {
            whereSql += " and a.score=" + score;
        }
        whereSql += " ORDER BY a.major_type_id,a.score DESC  ";

        //查询列表数据
        List<Map<String, Object>> copyList = commonSvc.findForJdbc(sql + formSql + whereSql, cpn(pageNo), CookieUtils.getPageSize(request));
        // 查询总条数数
        long totalCount = commonSvc.getCountForJdbc(countSql + formSql + whereSql);
        Pagination pagination = new Pagination(cpn(pageNo), CookieUtils.getPageSize(request), (int) totalCount, copyList);

        List<TBaseProvince> provinceList = commonSvc.loadAll(TBaseProvince.class);
        model.addAttribute("provinceList", provinceList);
        model.addAttribute("province", province);
        model.addAttribute("year", year);
        model.addAttribute("majorType", majorType);
        model.addAttribute("score", score);
        model.addAttribute("pagination", pagination);
        model.addAttribute("pageNo", pagination.getPageNo());

        return FrontUtils.getTplPath(site.getSolutionPath(), REFERENCE, ONE_SEGMENT_QUERY);
    }

    /**
     * 批次查询
     *
     * @param province
     * @param year
     * @param majorType
     * // Alltodo: 2019/11/11 对接批次查询新页面及跳转，设置非内部人员无法切换省份
     */
    @RequiresPermissions("PiCiChaCun:*")
    @RequestMapping(value = "/batch_query.jspx")
    public String batchQuery(Integer province, Integer year, Integer majorType, Integer pageNo,
                             HttpServletRequest request, HttpServletResponse response, ModelMap model, String ksType) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        //        是否内部人员
        Integer isAdmin = 0;
        if (user.getAdmin()) {
            isAdmin = 1;
        }
        model.addAttribute("isAdmin", isAdmin);

        if (province == null) {
            province = Integer.valueOf(user.getAttr().get("province_id"));
        }

        if (majorType == null) {
            majorType = Integer.valueOf(user.getAttr().get("major_type_id"));
        }

        if (year == null) {
            year = commonSvc.singleResult("SELECT dataBatchYear FROM TBaseProvince WHERE province_id =" + province + " ");
        }
        String kstype = ksType;
        model.addAttribute("kstype", kstype);

        Pagination pagination = bkckSvc.getBatchPage(province, year, majorType, cpn(pageNo), CookieUtils.getPageSize(request));
        model.addAttribute("pagination", pagination);
        model.addAttribute("pageNo", pagination.getPageNo());

        List<TBaseProvince> provinceList = commonSvc.loadAll(TBaseProvince.class);
        model.addAttribute("provinceList", provinceList);
        model.addAttribute("province", province);
        model.addAttribute("year", year);
        model.addAttribute("majorType", majorType);


        return FrontUtils.getTplPath(site.getSolutionPath(), REFERENCE, BATCH_QUERY);
    }

    /**
     * 招生计划
     *
     * @param majorType
     * @param batch
     * @param univName
     * @param majorName
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/enroll_plan.jspx")
    public String enrollPlan(Integer province, Integer majorType, Integer batch, String univName, String majorName, Integer pageNo,
                             HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        if (!user.isSuper()) {
            //初始化用户信息
            province = Integer.valueOf(user.getAttr().get("province_id"));
            majorType = Integer.valueOf(user.getAttr().get("major_type_id"));
        }
        //批次信息
        if (null != province && null != majorType) {
            Integer year = Calendar.getInstance().get(Calendar.YEAR);
            List batchInfo = bkckSvc.getBatchInfo(year, province, majorType);
            model.addAttribute("batchInfo", batchInfo);
        }

        Integer year = commonSvc.singleResult("select max(year) from TCeeScoreRank");
        Pagination pagination = bkckSvc.getPlanPage(year, province, majorType, batch, univName, majorName, cpn(pageNo), CookieUtils.getPageSize(request));
        List<TBaseProvince> provinceList = commonSvc.loadAll(TBaseProvince.class);

        model.addAttribute("provinceList", provinceList);
        model.addAttribute("province", province);
        model.addAttribute("year", year);
        model.addAttribute("majorType", majorType);
        model.addAttribute("batch", batch);
        model.addAttribute("univName", univName);
        model.addAttribute("majorName", majorName);
        model.addAttribute("pagination", pagination);
        model.addAttribute("pageNo", pagination.getPageNo());

        return FrontUtils.getTplPath(site.getSolutionPath(), REFERENCE, ENROLMENT_PLAN);
    }

    /**
     * 专业详情
     *
     * @param province
     * @param majorType
     * @param batch
     * @param univId
     * @param request
     * @param response
     * @param model
     */
    @RequestMapping(value = "/major_detail.jspx")
    public void majorDetail(Integer year, Integer province, Integer majorType, Integer batch, Integer univId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        List majorList = bkckSvc.getMajorDetail(year, province, majorType, batch, univId);
        List univList = commonSvc.findByProperty(TDataUniversity.class, "univId", univId);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(majorList));
    }

    /**
     * 历史录取
     *
     * @param majorType
     * @param batch
     * @param univName
     * @param majorName
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/history_enroll.jspx")
    public String historyEnroll(Integer year, Integer province, Integer majorType, Integer batch, String univName, String majorName, Integer pageNo,
                                HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        if (user.getTopRoleLevel() < 10) {
            //初始化用户信息
            province = Integer.valueOf(user.getAttr().get("province_id"));
            majorType = Integer.valueOf(user.getAttr().get("major_type_id"));
        }

        //历史录取分页列表
        Pagination pagination = bkckSvc.getHistPage(year, province, majorType, batch, univName, majorName, cpn(pageNo), CookieUtils.getPageSize(request));
        //批次信息
        if (null != province && null != majorType) {
            year = new Date().getYear();
            List batchInfo = bkckSvc.getBatchInfo(year, province, majorType);
            model.addAttribute("batchInfo", batchInfo);
        }
        // 省份列表
        List<TBaseProvince> provinceList = commonSvc.loadAll(TBaseProvince.class);
        model.addAttribute("provinceList", provinceList);
        model.addAttribute("province", province);
        model.addAttribute("majorType", majorType);
        model.addAttribute("batch", batch);
        model.addAttribute("pagination", pagination);
        model.addAttribute("pageNo", pagination.getPageNo());

        return FrontUtils.getTplPath(site.getSolutionPath(), REFERENCE, HISTORY_ENROLL);
    }

    /**
     * 招生章程
     * // Alltodo: 2019/11/11 对接招生章程新页面
     */
    @RequiresPermissions("ZhaoShengZhangCheng:*")
    @RequestMapping(value = "/enroll_rules.jspx")
    public String enrollRules(Integer province, Integer is211, Integer is985, Integer isFirstSchool, Integer isFirstMajor, String univName, Integer pageNo,
                              HttpServletRequest request, HttpServletResponse response, ModelMap model, String ksType) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        String kstype = ksType;
        model.addAttribute("kstype", kstype);
        //章程分页信息
        Pagination pagination = bkckSvc.getRulesPage(province, is211, is985, isFirstSchool, isFirstMajor, univName, cpn(pageNo), CookieUtils.getPageSize(request));
        // 省份列表
        List<TBaseProvince> provinceList = commonSvc.loadAll(TBaseProvince.class);

        model.addAttribute("provinceList", provinceList);
        model.addAttribute("province", province);
        model.addAttribute("is211", is211);
        model.addAttribute("is985", is985);
        model.addAttribute("isFirstSchool", isFirstSchool);
        model.addAttribute("isFirstMajor", isFirstMajor);
        model.addAttribute("univName", univName);
        model.addAttribute("pagination", pagination);
        model.addAttribute("pageNo", pagination.getPageNo());

        return FrontUtils.getTplPath(site.getSolutionPath(), REFERENCE, ENROLL_RULES);
    }

    /**
     * 章程详情页
     */
    @RequestMapping(value = "/enroll_rules_detail.jspx")
    public String showContent(HttpServletRequest request, HttpServletResponse response, ModelMap model, String univId, Integer type) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        TDataUniversityDetail detail = commonSvc.getEntity(TDataUniversityDetail.class, Integer.parseInt(univId));

        model.addAttribute("detail", detail);
        model.addAttribute("type", type);

        return FrontUtils.getTplPath(site.getSolutionPath(), REFERENCE, ENROLL_RULES_DETAIL);
    }


    /**
     * 查询批次信息
     *
     * @param province
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/get_batch_info.jspx")
    public void getBatchInfo(Integer year, Integer province, Integer majorType, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        List batchInfo = bkckSvc.getBatchInfo(year, province, majorType);
        try {
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(batchInfo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 咨询详情页
     */
    @RequestMapping(value = "/show.jspx")
    public String showContent(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), BKCL_INPUT, ZXXQ);
    }

}
