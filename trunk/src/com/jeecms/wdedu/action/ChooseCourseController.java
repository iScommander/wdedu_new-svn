package com.jeecms.wdedu.action;

import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.TCourseChooseEntity;
import com.jeecms.wdedu.service.CommonSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ChooseCourseController {

    public final static String TPLDIR_ZYTB = "zytb";
    public static final String OPTIONAL = "optional";
    public static final String TO_CHOOSE = "to_choose";


    @Autowired
    private CommonSvc commonSvc;

    @RequestMapping("/choosecoursefirst.jspx")
    public String chooseCourseFirst(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);

        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        FrontUtils.frontData(request, model, site);

        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, "courseChoose");
    }

    @RequestMapping("/chooseCourseSuzhouFirst.jspx")
    public String chooseCourseSuzhouFirst(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);

        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        FrontUtils.frontData(request, model, site);

        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, "CourseChooseSuzhou");
    }


    /**
     * 功能描述: 学生六门选三门
     *
     * @auther: Commander
     * @date: 2019/1/17 11:51
     */
    @RequestMapping("/choosecourse.jspx")
    public String chooseCourse(String first, String second, HttpServletRequest request, ModelMap model, HttpServletResponse response) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        int user_id = user.getId();
        String Sfirst = first;
        String Ssecond = second;

        TCourseChooseEntity tCourseChoose = new TCourseChooseEntity();
        tCourseChoose.setUserId(user_id);
        tCourseChoose.setFirst(Sfirst);
        tCourseChoose.setSecondA(Ssecond);
//        tCourseChoose.setUsername();
        if (user == null) {
//            return FrontUtils.showLogin(request, model, site);
            response.sendRedirect("login.jspx");
        }
        commonSvc.save(tCourseChoose);
        FrontUtils.frontData(request, model, site);
//        return FrontUtils.getTplPath(site.getSolutionPath(), OPTIONAL, TO_CHOOSE);
        return "redirect:gkxk/to_choose.jspx";
    }

    @RequestMapping("/choosecourseSuzhou.jspx")
    public String choosecourseSuzhou(String first, String second, HttpServletRequest request, ModelMap model, HttpServletResponse response) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        int user_id = user.getId();

        String[] strarray = second.split(",");
        String Sfirst = strarray[0];
        String Ssecond = strarray[1];


        TCourseChooseEntity tCourseChoose = new TCourseChooseEntity();
        tCourseChoose.setUserId(user_id);
        tCourseChoose.setFirst(Sfirst);
        tCourseChoose.setSecondA(Ssecond);
//        tCourseChoose.setUsername();
        if (user == null) {
//            return FrontUtils.showLogin(request, model, site);
            response.sendRedirect("login.jspx");
        }
        commonSvc.save(tCourseChoose);
        FrontUtils.frontData(request, model, site);
//        return FrontUtils.getTplPath(site.getSolutionPath(), OPTIONAL, TO_CHOOSE);
        return "redirect:gkxk/to_choose.jspx";
    }

}
