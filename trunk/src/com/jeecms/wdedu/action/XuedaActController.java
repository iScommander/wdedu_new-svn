package com.jeecms.wdedu.action;

import com.alibaba.fastjson.JSON;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.TBaseProvince;
import com.jeecms.wdedu.entity.TCeeBatch;
import com.jeecms.wdedu.entity.TDataUniversityType;
import com.jeecms.wdedu.entity.TKstbBase;
import com.jeecms.wdedu.service.CommonSvc;

import com.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("xuedaActController")
public class XuedaActController {

    @Autowired
    private CommonSvc commonSvc;

//    public final  static String TPLDIR_KSTB = "xd";

    @RequestMapping(value = "/infoEntry.jspx")
    public String InfoEntry(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);

        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        Map userInfo = new HashMap();

        userInfo.put("provinceId", user.getAttr().get("province_id"));
        userInfo.put("majorTypeId", user.getAttr().get("major_type_id"));

        String batch_sql = "SELECT MAX(YEAR) as year FROM t_cee_batch WHERE province_id = " + userInfo.get("provinceId") + " AND major_type_id = " + userInfo.get("majorTypeId") + " ";

        Map<String, Object> batchMap = commonSvc.findOneForJdbc(batch_sql);
        int year = (int) batchMap.get("year");

        model.addAttribute("year", year);
        model.addAttribute("provinceId", userInfo.get("provinceId"));
        model.addAttribute("majorTypeId", userInfo.get("majorTypeId"));

        String batch_hql = " FROM TCeeBatch WHERE provinceId = " + userInfo.get("provinceId") + " AND majorTypeId = " + userInfo.get("majorTypeId") + " AND YEAR = " + year + " AND isShow = 1 ";
        List<TCeeBatch> tCeeBatches = commonSvc.findByQueryString(batch_hql);
        model.addAttribute("tCeeBatches", tCeeBatches);

        List<TBaseProvince> provinceList = commonSvc.loadAll(TBaseProvince.class);
        model.addAttribute("provinceList", provinceList);

        List<TDataUniversityType> universityType = commonSvc.loadAll(TDataUniversityType.class);
        model.addAttribute("universityType", universityType);

        String intentProvinceId = request.getParameter("intentProvinceId");
        String avoidProvinceId = request.getParameter("avoidProvinceId");
        String intentUnivType = request.getParameter("intentUnivType");
        String avoidUnivType = request.getParameter("avoidUnivType");

        String schoolname = request.getParameter("schoolname");

        String yuanxiaoshuxing = request.getParameter("univattr");
        String youxianji = request.getParameter("youxianji");
        String scores = request.getParameter("scores");
        String tijianqingkuang = request.getParameter("tijianqingkuang");
        String remark = request.getParameter("remark");
        int is211 = 0, is985 = 0, isDefence = 0, isExcellent = 0, isIndependence = 0, isFirstRate = 0, isKeylab = 0;

        if (StringUtil.isNotEmpty(yuanxiaoshuxing)) {
            if (yuanxiaoshuxing.contains("1")) {
                is211 = 1;
            }
            if (yuanxiaoshuxing.contains("2")) {
                is985 = 1;
            }
            if (yuanxiaoshuxing.contains("3") && yuanxiaoshuxing.contains("4")) {
                isFirstRate = 1;
            } else if (yuanxiaoshuxing.contains("4") && !yuanxiaoshuxing.contains("3")) {
                isFirstRate = 2;
            }
            if (yuanxiaoshuxing.contains("5")) {
                isIndependence = 1;
            }
            if (yuanxiaoshuxing.contains("6")) {
                isDefence = 1;
            }
            if (yuanxiaoshuxing.contains("7")) {
                isExcellent = 1;
            }
            if (yuanxiaoshuxing.contains("8")) {
                isKeylab = 1;
            }
        }

//        boolean isAdmin = false;
//        if (user.getRoleIds() != null && user.getRoleIds().length != 0) {
//            isAdmin = true;
//        }
//
//        if (user.isSuper()) {
//            isAdmin = true;
//        }
        TKstbBase kstbBase = new TKstbBase();

        kstbBase.setIntentProvinceId(intentProvinceId);
        kstbBase.setAvoidProvinceId(avoidProvinceId);
        kstbBase.setIntentUnivType(intentUnivType);
        kstbBase.setAvoidUnivType(avoidUnivType);
        kstbBase.setIs211(is211);
        kstbBase.setIs985(is985);
        kstbBase.setIsFirstRate(isFirstRate);
        kstbBase.setIsIndependence(isIndependence);
        kstbBase.setIsDefence(isDefence);
        kstbBase.setIsExcellent(isExcellent);
        kstbBase.setIsKeylab(isKeylab);
        kstbBase.setFavoriteUnivNames(schoolname);
        kstbBase.setChooseOrder(youxianji);

        if (StringUtil.isNotEmpty(request.getParameter("chongwenbao"))) {
            String[] chongwenbao = request.getParameter("chongwenbao").split("\\|");
            kstbBase.setAdvancedUnivNum(Integer.parseInt(chongwenbao[0]));
            kstbBase.setStableUnivNum(Integer.parseInt(chongwenbao[1]));
            kstbBase.setBackwardUnivNum(Integer.parseInt(chongwenbao[2]));
            kstbBase.setCwbBatchId(Integer.parseInt(chongwenbao[3]));
        }
        kstbBase.setScore(scores);
        kstbBase.setPhysicalExamination(tijianqingkuang);
        kstbBase.setRemark(remark);

        return FrontUtils.getTplPath(site.getSolutionPath(), "xd", "infoEntry");

    }

    @RequestMapping(value = "/batch.jspx") 
    public void getBatchInfo(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> param = new HashMap<>();
        param.put("batchId", request.getParameter("batchId"));
        param.put("provinceId", request.getParameter("provinceId"));
        param.put("majorTypeId", request.getParameter("provinceId"));
        param.put("year", request.getParameter("year"));

        String batch_hql = " FROM TCeeBatch WHERE provinceId = " + param.get("provinceId") + " AND majorTypeId = " + param.get("majorTypeId") + " AND YEAR = " + param.get("year") + " AND batchId = " + param.get("batchId") + "  ";
        List<TCeeBatch> tCeeBatches = commonSvc.findByQueryString(batch_hql);

        try {
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(tCeeBatches));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
