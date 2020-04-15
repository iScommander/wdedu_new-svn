package com.jeecms.wdedu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.JcUserGroup;
import com.utils.SimpleCached;
import com.utils.StringUtil;
import com.jeecms.wdedu.entity.JcUserGroup;

import com.jeecms.wdedu.service.CommonSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class XuedaAct {

    public final static String TPLDIR_ACTIVITY = "activity";
    public final static String YANJIUYUAN_SHIPIN = "shipin_xd";

    @Autowired
    private CommonSvc commonService;

    @RequestMapping(value = "/xueDa.jspx")
    public String indexXd(HttpServletRequest request, ModelMap model, HttpServletResponse response) {

        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);

        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        Integer admin = 0 ;
        if (user.getAdmin()) {
            admin = 1 ;
        }

        model.addAttribute("admin", admin);

        Integer userId = user.getId();
        List<JcUserGroup> groups = commonService.findByProperty(JcUserGroup.class, "userId", userId);

        Integer group_id = 0;
        for (JcUserGroup groupList : groups) {
            if (groupList.getGroupId() == 2) {
                group_id = 2;
            }
        }
        model.addAttribute("group_id", group_id);

        Integer provincrId = Integer.valueOf(user.getAttr().get("province_id"));
        Integer majorTypeId = Integer.valueOf(user.getAttr().get("major_type_id"));

        model.addAttribute("province_id", provincrId);
        model.addAttribute("major_type_id", majorTypeId);

        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, YANJIUYUAN_SHIPIN);
//        if (user != null) {
//            Integer provinceId = 1;
//            if (!StringUtil.isEmpty(user.getAttr().get("province_id"))) {
//                provinceId = Integer.parseInt(user.getAttr().get("province_id"));
//            }
//
//            Integer majorTypeId = 1;
//            if (!StringUtil.isEmpty(user.getAttr().get("major_type_id"))) {
//                majorTypeId = Integer.parseInt(user.getAttr().get("major_type_id"));
//            }
//
//            SimpleCached cached = SimpleCached.getInstance();
//            List list = (List) cached.getCache("school" + String.valueOf(provinceId) + "-" + String.valueOf(majorTypeId));
//
//            if (list == null) {
//
//                Finder f = Finder.create("select bean.provinceId,bean.schoolName from t_s_college_admission bean where 1=1 ");
//                f.append("and bean.provinceId = :peovinceId ");
//                f.append("and bean.majorType = :majorType ");
//                f.append("group by bean.provinceId,bean.majorType ");
//                f.setParam("provinceId", provinceId);
//                f.setParam("majorType", majorTypeId);
//
//                list = com
//            }
//
//
//        }
    }
}
