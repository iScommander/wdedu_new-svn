package com.jeecms.wdedu.action;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.TBaseProvince;
import com.jeecms.wdedu.entity.TScActiveDetail;
import com.jeecms.wdedu.entity.TScActiveTeacher;
import com.jeecms.wdedu.service.ActiveDetailSvc;
import com.jeecms.wdedu.service.CommonSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import static com.jeecms.common.page.SimplePage.cpn;

/**
 * @author fanyue
 * @ProjectName wdedu
 * @Description: 用户中心 讲座中心
 * @date 2018/12/3
 */
@Controller
@RequestMapping(value = "/ActiveDetailAct")
public class ActiveDetailAct {

    public final static String TPLDIR_BKYX = "ActiveDetail";
    public final static String ACTIVE_DETAIL_RESULT = "ActiveDetailAct";

    @Autowired
    private ActiveDetailSvc activeDetailSvc;
    @Autowired
    private CommonSvc commonSvc;
    private List<TScActiveTeacher> tScActiveTeacherList;
    DateFormat format = new SimpleDateFormat("MM月dd日 HH时mm分");
    SimpleDateFormat simformat = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");

    /**
     * 功能描述: <br>
     * 〈查看所有讲座〉
     */

    @RequestMapping(value = "/member/findActive.jspx")
    public String findActive_get(String proname, String cityname, String quxianname, Integer pageNo, HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        Pagination pagination = activeDetailSvc.queryUniversityRank(proname, cityname, quxianname, cpn(pageNo), CookieUtils.getPageSize(request));
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_BKYX, ACTIVE_DETAIL_RESULT);
    }

    /**
     * 沃德研究院（专家团队）
     *
     * @param request
     * @param response
     * @param model
     * @param province
     * @param city
     * @param pageNo
     * @param pageSize
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/teacherList.jspx")
    public String TeacherList(HttpServletRequest request, HttpServletResponse response, ModelMap model, String province, String city,
                              Integer pageNo, Integer pageSize) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        /*
         * 查询条件
         */
        Finder f = Finder.create("FROM  TScActiveTeacher bean WHERE 1=1");
        f.append(" order by bean.id asc");
        Pagination pagination = commonSvc.findPager(f, cpn(pageNo), CookieUtils.getPageSize(request));
        model.addAttribute("pageNo", pagination.getPageNo());
        model.addAttribute("pagination", pagination);
        return FrontUtils.getTplPath(site.getSolutionPath(), "activity", "zhuanjiatuandui");

    }


    @RequestMapping(value = "/expertLectures.jspx")
    public String expertLectures(HttpServletRequest request, ModelMap model, String proname, String cityname, String quxianname, String city, String city1, String city2) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
//        if (user == null) {
//            return FrontUtils.showLogin(request, model, site);
//        }
        StringBuilder sb = new StringBuilder();
        StringBuilder procity = new StringBuilder();
        sb.append("SELECT * FROM t_sc_active_detail WHERE 1=1 AND ispublish!=3");
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
        List<Map<String, Object>> activeList = commonSvc.findForJdbc(sb.toString());
        if (procity != null && procity.toString().length() != 0) {
            model.addAttribute("procity", procity.toString());
        }
        List<TScActiveDetail> tBaseProvinceList = commonSvc.findByQueryString("SELECT distinct city from TScActiveDetail");
        List<TScActiveDetail> tScActiveDetailList1 = commonSvc.findByQueryString("SELECT DISTINCT activeType FROM TScActiveDetail");
        model.addAttribute("tScActiveDetailList1", tScActiveDetailList1);
        model.addAttribute("tBaseProvinceList", tBaseProvinceList);
        List<Map<String, Object>> tScActiveDetailList;
        model.addAttribute("activeList", activeList);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), "activity", "gongyijiangzuo");
    }

    @RequestMapping(value = "/queryJiang.jspx")
    public String queryJiang(HttpServletRequest request, HttpServletResponse response, ModelMap model, String citys,
                             String types) {
        CmsSite site = CmsUtils.getSite(request);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM t_sc_active_detail \n" +
                "WHERE 1 =1 \n");
        if (com.utils.StringUtils.isNotBlank(citys)) {
            String city = "\'" + citys.substring(0,citys.length()-1).replace(",", "','") + "\'";
            model.addAttribute("cityss",city);
            sql.append("AND city IN ( " + city + " ) \n");
        }
        if (com.utils.StringUtils.isNotBlank(types)) {
            String type = "\'" + types.substring(0,types.length()-1).replace(",", "','") + "\'";
            model.addAttribute("typess",type);
            sql.append("AND active_type IN ( " + type + " ) \n");
        }

        sql.append(" ORDER BY active_time DESC LIMIT 0,10");
        List<Map<String, Object>> activeList;
        activeList = commonSvc.findForJdbc(sql.toString());
        List<TBaseProvince> tBaseProvinceList = commonSvc.findByQueryString("SELECT distinct city from TScActiveDetail");
        List<TScActiveDetail> tScActiveDetailList1 = commonSvc.findByQueryString("SELECT DISTINCT activeType FROM TScActiveDetail");
        model.addAttribute("tScActiveDetailList1", tScActiveDetailList1);
        model.addAttribute("tBaseProvinceList", tBaseProvinceList);
        model.addAttribute("activeList", activeList);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), "activity", "gongyijiangzuo");
    }

}
