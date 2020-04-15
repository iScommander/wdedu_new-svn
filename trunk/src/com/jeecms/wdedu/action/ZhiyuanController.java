package com.jeecms.wdedu.action;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.ArrayUtils;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.jeecms.common.page.SimplePage.cpn;

@Controller
@RequestMapping("zytbcx")
public class ZhiyuanController {
    private static final Logger LOG = LoggerFactory.getLogger(ZhiyuanController.class);

    @RequestMapping(value = "/volunteerPage.jspx")
    public String volunteerPage(HttpServletRequest request) {
        CmsSite site = CmsUtils.getSite(request);
        return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "volunteerPage");
    }

//    //志愿填报
//    @RequestMapping(value = "/volunteerFlexible.jspx")
//    public String volunteerFlexible(HttpServletRequest request) {
//        CmsSite site = CmsUtils.getSite(request);
//        return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "volunteerFlexible");
//    }

    //志愿筛选
    @RequestMapping(value = "/volunteerFiltrate.jspx")
    public String volunteerFiltrate(HttpServletRequest request) {
        CmsSite site = CmsUtils.getSite(request);
        return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "volunteerFiltrate");
    }

    //生成志愿
    @RequestMapping(value = "/createVolunteer.jspx")
    public String createVolunteer(HttpServletRequest request) {
        CmsSite site = CmsUtils.getSite(request);
        return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "createVolunteer");
    }

    //评估报告
    @RequestMapping(value = "/assessmentReport.jspx")
    public String assessmentReport(HttpServletRequest request) {
        CmsSite site = CmsUtils.getSite(request);
        return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "assessmentReport");
    }

    //志愿视频
    @RequestMapping(value = "/volunteerVideo.jspx")
    public String volunteerVideo(HttpServletRequest request) {
        CmsSite site = CmsUtils.getSite(request);
        return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "volunteerVideo");
    }

    //专业测评
    @RequestMapping(value = "/majorAppraisal.jspx")
    public String majorAppraisal(HttpServletRequest request) {
        CmsSite site = CmsUtils.getSite(request);
        return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "majorAppraisal");
    }

    //填报说明
    @RequestMapping(value = "/completeExplain.jspx")
    public String completeExplain(HttpServletRequest request) {
        CmsSite site = CmsUtils.getSite(request);
        return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "completeExplain");
    }

    //用户中心
    @RequestMapping(value = "/volunnteerUserCenter.jspx")
    public String volunnteerUserCenter(HttpServletRequest request) {
        CmsSite site = CmsUtils.getSite(request);
        return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "volunnteerUserCenter");
    }

    //首页
    @RequestMapping(value = "/index.jspx")
    public String index(HttpServletRequest request) {
        CmsSite site = CmsUtils.getSite(request);
        return FrontUtils.getTplPath(site.getSolutionPath(), "WD", "index");
    }



}
