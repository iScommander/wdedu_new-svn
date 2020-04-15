package com.jeecms.wdedu.action;

import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/kstb")
public class KuaisutianbaoAct {

    @RequestMapping(value = "/gotoindex.jspx")
    public String gotoIndex(HttpServletRequest request, HttpServletResponse response, ModelMap map) {

        CmsSite site = CmsUtils.getSite(request);
        return FrontUtils.getTplPath(site.getSolutionPath(),"kstb", "kstbIndex");
    }

}
