package com.jeecms.wdedu.action;

import com.jeecms.cms.action.member.ForgotPasswordAct;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.*;
import com.jeecms.wdedu.service.CommonSvc;
import com.jeecms.wdedu.service.impl.BkyxSvcImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author fanyue
 * @ProjectName wdedu
 * @Description: 报考意向
 * @date 2018/10/17
 */
@Controller
@RequestMapping(value = "/BkyxAct")
public class BkyxAct {

    public static final String BKCL_INPUT = "bkcl";
    public static final String BKCL_RESULT = "bkyxact";
    public static final String BKCL_BKYXS = "bkxz";
    public static final String BKYX_RESULT = "intention";
    public static final String GRZX_LIST = "userCenter";
    public static final String ZXXQ = "zixunContent";
    private static Logger log = LoggerFactory.getLogger(ForgotPasswordAct.class);

    @Autowired
    private BkyxSvcImpl bkyxSvcImpl;
    @Autowired
    private CommonSvc commonService;

    private List<TBaseProvince> tBaseProvinces;
    private List<TDataUniversityType> tDataUniversityTypes;
    private List<TDataMajor> tDataMajors;
    private List<TDataMajor> tDataMajorsZ;
    private List<TDataMajor> tDataMajorsB;
    DateFormat format = new SimpleDateFormat("MM月dd日 HH时mm分");
    SimpleDateFormat simformat = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");

    /**
     * 报考意向录入
     *
     * @return
     */
    @RequestMapping(value = "/member/forget.jspx", method = RequestMethod.GET)
    public String addCelue(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), BKCL_INPUT, BKCL_RESULT);
    }

    /**
     * 地区类型列表
     *
     * @return
     */
    @RequestMapping(value = "/member/province.jspx")
    public String diQuList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        tBaseProvinces = bkyxSvcImpl.queryProvince();
        model.addAttribute("tBaseProvinces", tBaseProvinces);
        return FrontUtils.getTplPath(site.getSolutionPath(), BKCL_INPUT, BKCL_BKYXS);
    }

    /**
     * 院校类型列表
     *
     * @return
     */
    @RequestMapping(value = "/member/unitype.jspx")
    public String queryUniType(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        tDataUniversityTypes = bkyxSvcImpl.queryUniType();
        model.addAttribute("tDataUniversityTypes", tDataUniversityTypes);
        return FrontUtils.getTplPath(site.getSolutionPath(), BKCL_INPUT, BKCL_BKYXS);
    }

    /**
     * 专业类型列表
     *
     * @return
     */
    @RequestMapping(value = "/member/majorall.jspx")
    public String queryDataMajorAll(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        tDataMajors = bkyxSvcImpl.queryDataMajorAll();
        model.addAttribute("tDataMajors", tDataMajors);
        return FrontUtils.getTplPath(site.getSolutionPath(), BKCL_INPUT, BKCL_BKYXS);
    }

    /**
     * 本科专业列表
     *
     * @return
     */
    @RequestMapping(value = "/member/benke.jspx")
    public String queryDataMajorB(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
//        tDataMajorsB = bkyxSvcImpl.queryDataMajorB();
//        model.addAttribute("tDataMajorsB", tDataMajorsB);
        return FrontUtils.getTplPath(site.getSolutionPath(), BKCL_INPUT, BKCL_BKYXS);
    }

    /**
     * 专科专业列表
     *
     * @return
     */
    @RequestMapping(value = "/member/zhuanke.jspx")
    public String queryDataMajorZ(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        tDataMajorsZ = bkyxSvcImpl.queryDataMajorZ();
        model.addAttribute("tDataMajorsZ", tDataMajorsZ);
        return FrontUtils.getTplPath(site.getSolutionPath(), BKCL_INPUT, BKCL_BKYXS);
    }

    @RequestMapping("/intention.jspx")
    public String bcyx(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        return FrontUtils.getTplPath(site.getSolutionPath(), BKCL_INPUT, BKYX_RESULT);
    }

    /**
     * 用户中心
     *
     * @param request
     * @param model
     * @param userId
     * @param proname
     * @param cityname
     * @param quxianname
     * @param realname
     * @return
     */
    @RequestMapping(value = "/userCenter.jspx")
    public String grzx(HttpServletRequest request, ModelMap model, Integer userId, String proname, String cityname, String quxianname, String realname,String ksType) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        String kstype= ksType;
        model.addAttribute("kstype",kstype);
        String name = user.getRealname();

        StringBuilder sb = new StringBuilder();
        StringBuilder procity = new StringBuilder();
        sb.append("SELECT * FROM t_sc_active_detail WHERE 1=1 ");
        if (!StringUtils.isEmpty(proname) && !"全部".equals(proname)) {
            sb.append(" AND province='" + proname + "' ");
            procity.append(proname);
        }
        if (!StringUtils.isEmpty(cityname) && !"全部".equals(cityname)) {
            sb.append(" AND city='" + cityname + "'");
            procity.append("-" + cityname);
        }
        if (!StringUtils.isEmpty(quxianname) && !"全部".equals(quxianname)) {
            sb.append(" AND quxian='" + quxianname + "'");
            procity.append("-" + quxianname);
        }
//        sb.append(" AND '" + simformat.format(new Date()) + "'< active_time ");
        sb.append(" ORDER BY active_time desc limit 0,10");
        List<Map<String, Object>> activeList = commonService.findForJdbc(sb.toString());
        if (procity != null && procity.toString().length() != 0) {
            model.addAttribute("procity", procity.toString());
        }
        List<TBaseServices> tBaseServicesList = commonService.loadAll(TBaseServices.class);
        model.addAttribute("activeList", activeList);
        model.addAttribute("name", name);
        model.addAttribute("img", user.getUserImg());
        model.addAttribute("tBaseServicesList", tBaseServicesList);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), BKCL_INPUT, GRZX_LIST);
    }
}
