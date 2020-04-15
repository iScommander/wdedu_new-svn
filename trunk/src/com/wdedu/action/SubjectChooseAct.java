package com.wdedu.action;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.core.web.util.JsonWriteUtil;
import com.wdedu.service.ICourseService;
import com.jeecms.wdedu.service.CommonSvc;
import com.wdedu.entity.TMajorfollowEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉               科学选课Controller
 *
 * @author whb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 * // Alltodo: 2019/11/8 对接新高考选科新页面及跳转
 */
@Controller
public class SubjectChooseAct {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectChooseAct.class);
    public final static String TPLDIR_ZYTB = "wdedu/xgkxk";

    /* 科学选课的入口页面
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/kxxk/courceIndex.jspx", method = RequestMethod.GET)
    public String index(HttpServletRequest request,
                        HttpServletResponse response, ModelMap model)
            throws UnsupportedEncodingException {

        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, "kxxk");
    }

    /**
     * 根据科目选专业
     *
     * @param
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/kxxk/courceToMajor.jspx", method = RequestMethod.GET)
    public String selectByCourse_get(HttpServletRequest request, HttpServletResponse response,
                                     ModelMap model, String subjects, Integer pageNo, Integer pageSize, String andOr0, String unlimited0, String univName, String majorName) throws IOException {
        return selectByCourse(request, response, model, subjects, pageNo, pageSize, andOr0, unlimited0, univName, majorName);

    }

    @RequestMapping(value = "/kxxk/courceToMajor.jspx", method = RequestMethod.POST)
    public String selectByCourse_post(HttpServletRequest request, HttpServletResponse response,
                                      ModelMap model, String subjects, Integer pageNo, Integer pageSize, String andOr0, String unlimited0, String univName, String majorName) throws IOException {
        return selectByCourse(request, response, model, subjects, pageNo, pageSize, andOr0, unlimited0, univName, majorName);
    }

    public String selectByCourse(HttpServletRequest request, HttpServletResponse response,
                                 ModelMap model, String subjects, Integer pageNo, Integer pageSize, String andOr0, String unlimited0, String univName, String majorName) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        HttpSession session = request.getSession(true);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        int provinceId = 1;
        int userId = user.getId();
        provinceId = Integer.parseInt(user.getAttr().get("province_id"));

        model.addAttribute("andOr0", andOr0);
        model.addAttribute("unlimited0", unlimited0);

        if (pageSize == null || pageSize == 0) {
            pageSize = 20;
        }

        String yearSql = "SELECT MAX(YEAR) as year FROM `t_majorsubjectselector` WHERE province_id = " + provinceId;
        Map<String, Object> yearMap = commonService.findOneForJdbc(yearSql, new Object[]{});

        if(yearMap.get("year") == null){
            provinceId = 1 ;
            String yearSqlTmp = "SELECT MAX(YEAR) as year FROM `t_majorsubjectselector` WHERE province_id = " + provinceId;
            yearMap = commonService.findOneForJdbc(yearSqlTmp, new Object[]{});
        }

        int year = Integer.parseInt(yearMap.get("year").toString());

        String typeSql = "SELECT MIN(limit_type) AS TYPE FROM t_majorsubjectselector WHERE province_id = " + provinceId + " AND YEAR = " + year;
        Map<String, Object> typeMap = commonService.findOneForJdbc(typeSql, new Object[]{});
        int limitType = 0;
        limitType = Integer.parseInt(typeMap.get("TYPE").toString());
        model.addAttribute("limitType", limitType);

        Pagination pagination = courseService.findXkList(provinceId, year, userId, subjects, limitType, andOr0, unlimited0, pageNo, pageSize, univName, majorName);

        model.addAttribute("pagination", pagination);
        model.addAttribute("subjects", subjects);

        List<String> sublist = null;
        if (subjects != null && subjects != "") {
            sublist = new ArrayList<String>();
            String[] subs = subjects.split(",");
            for (String string : subs) {
                sublist.add(string);
            }
        }
        model.addAttribute("sublist", sublist);
        model.addAttribute("univ_name", univName);
        model.addAttribute("major_name", majorName);

        if (limitType == 0) {
            return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, "kxxk-km0");
        } else if (limitType == 1) {
            return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, "kxxk-km1");
        } else {
            return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, "kxxk-km0");
        }
    }

    public List subjectList(Integer userId) {
        String claSql = "SELECT id,subject FROM t_courseSelectionActivity WHERE id IN (SELECT activityId FROM  "
                + "  t_courseSelectionActivityClass WHERE classes IN ( select distinct c.id from t_student s,t_class c "
                + "  where s.classes = c.code and c.code in(SELECT classes FROM  t_student WHERE id = '" + userId + "'))) ";

        Map<String, Object> kmmap = commonService.findOneForJdbc(claSql, null);
        List<String> list = new ArrayList<String>();
        if (kmmap != null) {
            String[] subject = kmmap.get("subject").toString().split(",");
            for (String string : subject) {
                list.add(string);
            }
        }
        return list;
    }

    /**
     * 根据专业选科目初始页
     *
     * @param
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/kxxk/majorToCource.jspx", method = RequestMethod.GET)
    public String selectBymajor_get(HttpServletRequest request,
                                    HttpServletResponse response, ModelMap model, String majorNames, Integer pageNo, Integer pageSize, String univName, String majorName) throws IOException {
        return selectBymajor(request, response, model, majorNames, pageNo, pageSize, univName, majorName);

    }

    @RequestMapping(value = "/kxxk/majorToCource.jspx", method = RequestMethod.POST)
    public String selectBymajor_post(HttpServletRequest request,
                                     HttpServletResponse response, ModelMap model, String majorNames, Integer pageNo, Integer pageSize, String univName, String majorName) throws IOException {
        return selectBymajor(request, response, model, majorNames, pageNo, pageSize, univName, majorName);
    }

    public String selectBymajor(HttpServletRequest request, HttpServletResponse response,
                                ModelMap model, String majorNames, Integer pageNo, Integer pageSize, String univName, String majorName) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        int provinceId = 1;
        int userId = user.getId();
        provinceId = Integer.parseInt(user.getAttr().get("province_id"));

        //查询所有专业
        //一级专业
        List<Map<String, Object>> oneList = this.queryMajorByParentId("'1','2'");
        //二级专业

        List<Map<String, Object>> twoList = this.queryMajorByParentId(subString(oneList));
        //三级专业
        List<Map<String, Object>> threeLists = this.queryMajorByParentId(subString(twoList));

        List<Map<String, Object>> threeList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map2 : threeLists) {
            map2.put("msid", "m" + map2.get("major_Id").toString().subSequence(0, 2));
            threeList.add(map2);
        }
        model.addAttribute("oneList", oneList);
        model.addAttribute("twoList", twoList);
        model.addAttribute("threeList", threeList);

        //测试数据
        //majorNames="080901,080902";
        if (pageSize == null || pageSize == 0) {
            pageSize = 20;
        }

        String yearSql = "SELECT MAX(YEAR) as year FROM `t_majorsubjectselector` WHERE province_id = " + provinceId;
        Map<String, Object> yearMap = commonService.findOneForJdbc(yearSql, new Object[]{});

        if(yearMap.get("year") == null){
            provinceId = 1 ;
            String yearSqlTmp = "SELECT MAX(YEAR) as year FROM `t_majorsubjectselector` WHERE province_id = " + provinceId;
            yearMap = commonService.findOneForJdbc(yearSqlTmp, new Object[]{});
        }

        int year = Integer.parseInt(yearMap.get("year").toString());

        Pagination pagination = courseService.findkmList(provinceId, year, userId, majorNames, pageNo, pageSize, univName, majorName);
        model.addAttribute("pagination", pagination);
        model.addAttribute("majorNames", majorNames);
        List<Map<String, Object>> majorlist = new ArrayList<Map<String, Object>>();
        String majorids = "";
        if (majorNames != null && majorNames != "") {
            String[] pidss = majorNames.split(",");
            for (String string : pidss) {
                Map<String, Object> map1 = new HashMap<String, Object>();
                map1.put("majorid", string);
                majorlist.add(map1);
                majorids += string.subSequence(0, 2) + ",";
            }
        }
        model.addAttribute("majorlist", majorlist);
        model.addAttribute("majorids", majorids);
        model.addAttribute("univ_name", univName);
        model.addAttribute("major_name", majorName);

        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, "kxxk-zy");

    }

    /**
     * 功能描述: <br>
     * 〈关注〉
     *
     * @param request
     * @param response
     * @param model
     * @param Id
     * @throws IOException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/kxxk/followmajor.jspx")
    public void followmajor(HttpServletRequest request, HttpServletResponse response,
                            ModelMap model, Integer Id) throws IOException {

        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        CmsUser user = CmsUtils.getUser(request);

        int provinceId = 1;
        int userId = user.getId();
        provinceId = Integer.parseInt(user.getAttr().get("province_id"));

        TMajorfollowEntity majorFollow = new TMajorfollowEntity();
        majorFollow.setStudent(userId);
        majorFollow.setSelector(Id);

        commonService.saveOrUpdate(majorFollow);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        JsonWriteUtil.write(response, map);
    }


    /**
     * 功能描述: <br>
     * 〈取消关注〉
     *
     * @param request
     * @param response
     * @param model
     * @param Id
     * @throws IOException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/kxxk/cancelfollowmajor.jspx")
    public void cancelfollowmajor(HttpServletRequest request, HttpServletResponse response,
                                  ModelMap model, Integer Id) throws IOException {

        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        CmsUser user = CmsUtils.getUser(request);

        int provinceId = 1;
        int userId = user.getId();
        provinceId = Integer.parseInt(user.getAttr().get("province_id"));

        String sql = "DELETE FROM t_majorFollow WHERE student=" + userId + " AND selector=" + Id;
        Integer success = commonService.executeSql(sql);
        Map<String, Object> map = new HashMap<String, Object>();
        if (success == 1) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        JsonWriteUtil.write(response, map);
    }

    private List<Map<String, Object>> queryMajorByParentId(String parentId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parentId", parentId);
        String queryFristMajor =
                "SELECT parent_Major_Id,major_Id,major_Name FROM t_s_major_introduce WHERE parent_Major_Id IN(" + parentId + ") ORDER BY major_Id";

        return commonService.findForJdbc(queryFristMajor, null);
    }

    public String subString(List<Map<String, Object>> List) {
        String twoStr = "";
        for (Map<String, Object> map : List) {
            twoStr += "'" + map.get("major_Id") + "',";
        }
        twoStr = (String) twoStr.subSequence(0, twoStr.length() - 1);
        return twoStr;
    }


    /**
     * 根据职业选专业
     *
     * @param
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/kxxk/professionToMajor.jspx", method = RequestMethod.GET)
    public String selectByProfession_get(HttpServletRequest request, HttpServletResponse response,
                                         ModelMap model, String careerIds, Integer pageNo, Integer pageSize, String univName, String majorName) throws IOException {
        return selectByProfession(request, response, model, careerIds, pageNo, pageSize, univName, majorName);
    }

    @RequestMapping(value = "/kxxk/professionToMajor.jspx", method = RequestMethod.POST)
    public String selectByProfession_post(HttpServletRequest request, HttpServletResponse response,
                                          ModelMap model, String careerIds, Integer pageNo, Integer pageSize, String univName, String majorName) throws IOException {
        return selectByProfession(request, response, model, careerIds, pageNo, pageSize, univName, majorName);
    }

    public String selectByProfession(HttpServletRequest request, HttpServletResponse response,
                                     ModelMap model, String careerIds, Integer pageNo, Integer pageSize, String univName, String majorName) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        int provinceId = 1;
        int userId = user.getId();
        provinceId = Integer.parseInt(user.getAttr().get("province_id"));

        //职业三级目录
        List<Map<String, Object>> firstList = courseService.selectFirstList();
        List<Map<String, Object>> secondList = courseService.selectSecondList();
        List<Map<String, Object>> thirdLists = courseService.selectThirdList();

        List<Map<String, Object>> thirdList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map2 : thirdLists) {
            map2.put("msid", "m" + map2.get("catalogCode1"));
            thirdList.add(map2);
        }

        model.addAttribute("firstList", firstList);
        model.addAttribute("secondList", secondList);
        model.addAttribute("thirdList", thirdList);

        if (pageSize == null || pageSize == 0) {
            pageSize = 20;
        }

        String yearSql = "SELECT MAX(YEAR) as year FROM `t_majorsubjectselector` WHERE province_id = " + provinceId;
        Map<String, Object> yearMap = commonService.findOneForJdbc(yearSql, new Object[]{});

        if(yearMap.get("year") == null){
            provinceId = 1 ;
            String yearSqlTmp = "SELECT MAX(YEAR) as year FROM `t_majorsubjectselector` WHERE province_id = " + provinceId;
            yearMap = commonService.findOneForJdbc(yearSqlTmp, new Object[]{});
        }

        int year = Integer.parseInt(yearMap.get("year").toString());

        Pagination pagination = courseService.findZyList(provinceId, year, userId, careerIds, pageNo, pageSize, univName, majorName);

        model.addAttribute("pagination", pagination);
        model.addAttribute("careerIds", careerIds);

        List<Map<String, Object>> careerList = new ArrayList<Map<String, Object>>();
        String careerCodes = "";
        if (careerIds != null && careerIds != "") {
            String[] pidss = careerIds.split(",");
            for (String string : pidss) {
                Map<String, Object> map1 = new HashMap<String, Object>();
                map1.put("majorid", string);
                careerList.add(map1);
                careerCodes += string.subSequence(0, 2) + ",";
            }
        }
        model.addAttribute("careerList", careerList);
        model.addAttribute("careerCodes", careerCodes);
        model.addAttribute("univ_name", univName);
        model.addAttribute("major_name", majorName);

        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ZYTB, "kxxk-gz");
    }

    @Autowired
    private CommonSvc commonService;
    @Autowired
    private ICourseService courseService;

}

