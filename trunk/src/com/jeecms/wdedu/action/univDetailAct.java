package com.jeecms.wdedu.action;

import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.TCeeEnrollHistory;
import com.jeecms.wdedu.entity.TCeeEnrollPlan;
import com.jeecms.wdedu.service.CommonSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author fanyue
 * @ProjectName wdedu
 * @Description: 院校详情
 * @date 2018/12/21
 */
@Controller
public class univDetailAct {

    @Autowired
    private CommonSvc commonSvc;

    @RequestMapping(value = "/findUnivDetail.jspx")
    public String findUnivDetail(HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer province_id, Integer major_type_id,
                                 Integer batch_id, Integer univ_id, Integer year) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        String hql = "FROM TCeeEnrollHistory WHERE  year in(2016,2017,2018) AND provinceId = '" + province_id + "' AND majorTypeId = '" + major_type_id + "' AND batchId = '" + batch_id + "' AND univId = '" + univ_id + "' ORDER BY historyType,lowScore DESC";
        List<TCeeEnrollHistory> tCeeEnrollHistoryList = commonSvc.findByQueryString(hql);
        model.addAttribute("tCeeEnrollHistoryList", tCeeEnrollHistoryList);
        model.addAttribute("univ_id", univ_id);
        model.addAttribute("province_id", province_id);
        model.addAttribute("major_type_id", major_type_id);
        model.addAttribute("batch_id", batch_id);
        String hqlSchool = "SELECT univName FROM TDataUniversity WHERE univId = '"+univ_id+"'";
        List name = commonSvc.findByQueryString(hqlSchool);
        model.addAttribute("name",name);
        for (TCeeEnrollHistory tcee : tCeeEnrollHistoryList) {
            if (tcee.getSegScoreHigh() != null) {
                if (tcee.getYear() == 2016) {
                    int scoreHigh6 = tcee.getSegScoreHigh();
                    model.addAttribute("scoreHigh6", scoreHigh6);
                }
                if (tcee.getYear() == 2017) {
                    int scoreHigh7 = tcee.getSegScoreHigh();
                    model.addAttribute("scoreHigh7", scoreHigh7);
                }
                if (tcee.getYear() == 2018) {
                    int scoreHigh8 = tcee.getSegScoreHigh();
                    model.addAttribute("scoreHigh8", scoreHigh8);
                }
            }
            if (tcee.getSegScoreLow() != null) {
                int scoreLow = tcee.getSegScoreLow();
                model.addAttribute("scoreLow", scoreLow);
            }
        }
        if (province_id == 1 || province_id == 2 || province_id == 3) {

            return FrontUtils.getTplPath(site.getSolutionPath(), "schoolDetail", "lishishuju2");
        }
        return FrontUtils.getTplPath(site.getSolutionPath(), "schoolDetail", "lishishuju");

    }

    @RequestMapping(value = "/schoolPlan.jspx")
    public String schoolPlan(HttpServletRequest request, ModelMap model, Integer province_id, Integer major_type_id,
                             Integer batch_id, Integer univ_id) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        model.addAttribute("univ_id", univ_id);
        model.addAttribute("province_id", province_id);
        model.addAttribute("major_type_id", major_type_id);
        model.addAttribute("batch_id", batch_id);
        String hqlSchool = "SELECT univName FROM TDataUniversity WHERE univId = '"+univ_id+"'";
        List name = commonSvc.findByQueryString(hqlSchool);
        String hql = "FROM TCeeEnrollPlan WHERE univId = '" + univ_id + "' AND year =2018  AND provinceId = '" + province_id + "' AND majorTypeId = '"+major_type_id+"' AND batchId = '"+batch_id+"' ORDER BY planType, major_code";
        List<TCeeEnrollPlan> tCeeEnrollPlanList = commonSvc.findByQueryString(hql);
        model.addAttribute("name",name);
        model.addAttribute("tCeeEnrollPlanList",tCeeEnrollPlanList);
        return FrontUtils.getTplPath(site.getSolutionPath(), "schoolDetail", "jihuashuju");
    }
}
