package com.jeecms.wdedu.action;

import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 高考选科
 */
@Controller
@RequestMapping("/gkxk")
public class GkxkAct {
    public static final String OPTIONAL = "optional";
    public static final String TO_CHOOSE = "to_choose";


    @RequestMapping(value = "/queryOrder.jspx")
    public String  queryOrder(HttpServletRequest request,  HttpServletResponse response, ModelMap model){
       CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
     /*    System.out.println("程序进来了****************");
        System.out.println("*****"+FrontUtils.getTplPath(site.getSolutionPath(),  "ydyfw", "jbxx_xd.html"));
        System.out.println("*****"+FrontUtils.getTplPath(site.getSolutionPath(),  "ydyfw", "test.html"));*/
        return FrontUtils.getTplPath(site.getSolutionPath(),  "ydyfw", "test");
    }


    @RequestMapping(value = "/to_choose.jspx")
    public String toChoose(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if(null!=user){
            model.addAttribute("group",user.getGroup());
        }
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), OPTIONAL, TO_CHOOSE);
    }

    @RequestMapping(value = "/oneToOne.jspx")
    public String oneToOne(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        return FrontUtils.getTplPath(site.getSolutionPath(), OPTIONAL, "oneToOne");
    }
}
