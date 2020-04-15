package com.jeecms.wdedu.action;

import com.alibaba.fastjson.JSON;
import com.jeecms.core.entity.CmsGroup;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.*;
import com.jeecms.wdedu.service.ActivitySvc;
import com.jeecms.wdedu.service.CommonSvc;
import com.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author fanyue
 * @ProjectName wdedu
 * @Description: 资料管理
 * @date 2018/10/19
 */
@Controller
@RequestMapping(value = "/ActivityAct")
public class ActivityAct {
    public final static String TPLDIR_ACTIVITY = "activity";
    public final static String ACTIVITYLIST_RESULT = "actibityList";
    public final static String AWARDLIST_RESULT = "awardList";
    public final static String T_N_USERLIST_RESULT = "userList";
    public final static String T_N_REFEREE_RESULT = "refereeList";
    public final static String T_N_SCORE_RESULT = "scoreList";
    public final static String T_N_PATENT_RESULT = "pantentList";
    public final static String T_N_EDUCATION_RESULT = "educationList";
    public final static String T_N_FAMILY_RESULT = "familyList";
    public final static String T_N_SCHOOL_RESULT = "schoolList";
    public final static String HUIYUANSHEZHI_RESULT = "huiyuanshezhi";
    public final static String INDEX = "index";
    public final static String INDEX_RESULT = "index";
    public final static String YANJIUYUAN_ZIXUN = "zixun";
    public final static String YANJIUYUAN_SHIPIN = "shipin";
    public final static String YANJIUYUAN_ZHUANJIATUANDUI = "zhuanjiatuandui";
    public final static String YANJIUYUAN_ZHUANJIAXIANGQING = "zhuanjiaxiangqing";
    public final static String BKCL = "bkcl";
    public final static String BKCL_SUCCESS = "userCenter";


    @Autowired
    private ActivitySvc activitySvc;
    @Autowired
    private CommonSvc commonSvc;
    private List<TNActivity> tnActivities;
    private List<TNAward> awardList;
    private List<TNUser> tnUserList;
    private List<TNReferee> tnRefereeList;
    private List<TNScore> tnScoreList;
    private List<TNPatent> tnPatentList;
    private List<TNEducation> tnEducationList;
    private List<TNFamily> tnFamilyList;
    private List<TNSchool> tnSchoolList;
    private List<TBaseProvince> tBaseProvince;
    private List<TBaseProvince> tBaseProvince2;
    private List<TBaseHighshool> tBaseHighshoolList;
    private List<TBaseHighshool> tBaseHighshoolList1;
    private List<TBaseHighshool> tBaseHighshoolList2;
    private List<TScActiveTeacher> tScActiveTeacherList;
    @Autowired
    private CmsUserMng cmsUserMng;


    /**
     * 沃德研究院（沃德咨询）
     */
    @RequestMapping(value = "/zixun*.jspx")
    public String ziXun(HttpServletRequest request, ModelMap model, String title1, String title2) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        FrontUtils.frontPageData(request, model);

        model.addAttribute("title1", title1);
        model.addAttribute("title2", title2);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, YANJIUYUAN_ZIXUN);
    }

    /**
     * 沃德研究院（讲座视频）
     */
    @RequestMapping(value = "/shipin.jspx")
    public String shiPin(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        String major_type_id = user.getAttr().get("major_type_id");
        model.addAttribute("majorTypeId", major_type_id);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, YANJIUYUAN_SHIPIN);
    }

    /**
     * 沃德研究院（专家团队）
     */
    @RequestMapping(value = "/zhuanjiatuandui.jspx")
    public String zhuaJiaTuanDui(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, YANJIUYUAN_ZHUANJIATUANDUI);
    }

    /**
     * 沃德研究院（专家详情）
     */
    @RequestMapping(value = "/zhuanjixiangqing.jspx")
    public String zhuanJiaXiangQing(HttpServletRequest request, ModelMap model, Integer teacher_id) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        tScActiveTeacherList = commonSvc.findByProperty(TScActiveTeacher.class, "teacherId", teacher_id);
        //BLOB 转 String
        for (int i = 0; i < tScActiveTeacherList.size(); i++) {
            TScActiveTeacher ts = tScActiveTeacherList.get(i);
            Blob blob = ts.getIntroduce4();
            try {
                String introduce4 = new String(blob.getBytes(1L, (int) blob.length()), "utf-8");
                model.addAttribute("introduce4", introduce4);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("tScActiveTeacherList", tScActiveTeacherList);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, YANJIUYUAN_ZHUANJIAXIANGQING);
    }

    /**
     * 个人信息保存
     */
    @RequestMapping(value = "/savaOrUpd.jspx")
    public String huiYuanSheZhi(HttpServletRequest request, ModelMap model, String realName, String qq, String phone, String province_id, String major_type_id,
                                String cityId, String quxianId, String banji, String biyeYear, String sex, String msn, String school_id, String pro, String major) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        //jc_user_ext 添加内容
//        if(user.getUserExt() == null){
//            CmsUserExt cmsUserExt = new CmsUserExt();
//            Set<CmsUserExt> cmsUserExtset = new HashSet<>();
//            cmsUserExt.setId(user.getId());
//            cmsUserExt.setMsn(msn);
//            cmsUserExt.setGender(sex);
//            cmsUserExt.setQq(qq);
//            cmsUserExt.setRealname(realName);
//            cmsUserExt.setPhone(phone);
//            cmsUserExtset.add(cmsUserExt);
//            user.setUserExtSet(cmsUserExtset);
//        }else{
//            user.getUserExt().setMsn(msn);
//            user.getUserExt().setGender(sex);
//            user.getUserExt().setQq(qq);
//            user.getUserExt().setRealname(realName);
//            user.getUserExt().setPhone(phone);
//        }
//        commonSvc.saveOrUpdate(user.getUserExt());
//       CmsUserExt cmsUserExt = new CmsUserExt();
//       cmsUserExt.setId(user.getId());
//       cmsUserExt.setMsn(msn);
//       cmsUserExt.setGender(sex);
//       cmsUserExt.setQq(qq);
//       cmsUserExt.setRealname(realName);
//       cmsUserExt.setPhone(phone);
//       user.getUserExt().setId(user.getId());
//       cmsUserExt.setUser(user);
//       commonSvc.saveOrUpdate(cmsUserExt);
//
//       SessionFactory sessionFactory;
        //jc_user_attr 添加内容
//        CmsConfigAttr cmsConfigAttr = new CmsConfigAttr();
        if (user.getAttr() == null) {
            Map<String, String> attr = new HashMap<>();
            user.getId();
            attr.put("major_type_id", major_type_id);//文理科
            attr.put("province_id", province_id);//省份
            attr.put("banji", banji);//班级
            attr.put("biyeYear", biyeYear);//毕业年份
            attr.put("sex", sex);//性别
            attr.put("quxianId", quxianId);//区县
            attr.put("cityId", cityId);//城市
            attr.put("school_id", school_id);//学校
            attr.put("realName", realName);//姓名
            attr.put("phone", phone);
            attr.put("qq", qq);
            attr.put("msn", msn);
        } else {
            user.getId();
            if ("yes".equals(pro)) {
                user.getAttr().put("province_id", province_id);
            } else {
                if (user.getAttr().get("province_id") != null) {
                    user.getAttr().put("province_id", user.getAttr().get("province_id"));
                } else {
                    user.getAttr().put("province_id", province_id);
                }
            }
            if ("yes".equals(major)) {
                user.getAttr().put("major_type_id", major_type_id);
            } else {
                if (user.getAttr().get("major_type_id") != null) {
                    user.getAttr().put("major_type_id", user.getAttr().get("major_type_id"));
                } else {
                    user.getAttr().put("major_type_id", major_type_id);
                }
            }


            if (StringUtils.isNotBlank(banji)) {
                user.getAttr().put("banji", banji);
            }
            if (StringUtils.isNotBlank(biyeYear)) {

            user.getAttr().put("biyeYear", biyeYear);
            }
            if (StringUtils.isNotBlank(sex)) {
            user.getAttr().put("sex", sex);//性别
            }
            if (StringUtils.isNotBlank(quxianId)) {
            user.getAttr().put("quxian_id", quxianId);//区县
            }
            if (StringUtils.isNotBlank(cityId)) {
            user.getAttr().put("city_id", cityId);//城市
            }
            if (StringUtils.isNotBlank(school_id)) {
            user.getAttr().put("school_id", school_id);
            }
            if (StringUtils.isNotBlank(realName)) {
            user.getAttr().put("realName", realName);//姓名
            }
            if (StringUtils.isNotBlank(phone)) {
            user.getAttr().put("phone", phone);
            }
            if (StringUtils.isNotBlank(qq)) {
            user.getAttr().put("qq", qq);
            }
            if (StringUtils.isNotBlank(msn)) {
                user.getAttr().put("msn", msn);
            }
        }
//        commonSvc.saveOrUpdate(user);
        commonSvc.updateEntitie(user);

        return "redirect:/admissonSvcAct/findbaseInfo.jspx";
    }

    @RequestMapping(value = "/zhuye.jspx")
    public String Zhuye(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        return FrontUtils.getTplPath(site.getSolutionPath(), "bkcl", "userCenter");
    }

    /**
     * 补充信息页面
     */
    @RequestMapping(value = "/huiyuanshezhi.jspx")
    public String addUser(HttpServletRequest request, ModelMap model, String province_id, String cityId, String quxianId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }

        //判断角色是否可修改省份及文理科信息
        Set<CmsGroup> group = user.getGroups();
        String prem = "";
        if (group != null && group.size() > 0) {
            for (CmsGroup group1 : group) {
                String groupId = String.valueOf(group1.getId());
                String permQuery = "SELECT p.uri FROM jc_group_permission AS p WHERE p.group_id=" + groupId;
                List<Map<String, Object>> permList = commonSvc.findForJdbc(permQuery);
                for (Map<String, Object> permMap : permList) {
                    prem = String.valueOf(permMap.get("uri"));
                    if ("ShengfenShezhi:*".equals(prem)) {
                        model.addAttribute("pro", "yes");
                    }
                    if ("WenlikeSheshi:*".equals(prem)) {
                        model.addAttribute("major", "yes");
                    }
                }
            }
        }
        CmsGroup group1 = user.getGroup();
        if (group1 != null) {
            String groupId = String.valueOf(group1.getId());
            String permQuery = "SELECT p.uri FROM jc_group_permission AS p WHERE p.group_id=" + groupId;
            List<Map<String, Object>> permList = commonSvc.findForJdbc(permQuery);
            for (Map<String, Object> permMap : permList) {
                prem = String.valueOf(permMap.get("uri"));
                if ("ShengfenShezhi:*".equals(prem)) {
                    model.addAttribute("pro", "yes");
                }
                if ("WenlikeSheshi:*".equals(prem)) {
                    model.addAttribute("major", "yes");
                }
            }
        }

        //毕业年份
        String biyeQuery = "SELECT * FROM t_mgr_comm_code_mas AS a WHERE a.KEY = 'locationYear'";
        List<Map<String, Object>> biyeList = commonSvc.findForJdbc(biyeQuery);
        model.addAttribute("biyeList", biyeList);
        String hql = "FROM TBaseProvince WHERE provinceName = '江苏'";
        tBaseProvince = commonSvc.findByQueryString(hql);
        //省份
        tBaseProvince2 = commonSvc.loadAll(TBaseProvince.class);
        model.addAttribute("tBaseProvince2", tBaseProvince2);
        //城市
        String hql2 = "SELECT DISTINCT cityName ,cityId FROM TBaseHighshool WHERE provinceId = '" + user.getAttr().get("province_id") + "'";
        List<Map<String, Object>> cityList = commonSvc.findByQueryString(hql2);
        model.addAttribute("cityList", cityList);
        //区县
        String hql3 = "SELECT DISTINCT quxianName,quxianId  FROM TBaseHighshool WHERE city_id = '" + user.getAttr().get("city_id") + "'";
        tBaseHighshoolList1 = commonSvc.findByQueryString(hql3);
        model.addAttribute("tBaseHighshoolList1", tBaseHighshoolList1);
        //学校
        String hql4 = "SELECT DISTINCT schoolId,schoolName  FROM TBaseHighshool WHERE quxian_id = '" + user.getAttr().get("quxian_id") + "'";
        tBaseHighshoolList2 = commonSvc.findByQueryString(hql4);
        List<Object> provinces = commonSvc.findByQueryString("FROM TBaseProvince WHERE provinceId ='" + user.getAttr().get("province_id") + "'");
//        List<JcUserGroup> groupId = commonSvc.findListbySql("SELECT group_id FROM jc_user_group WHERE user_id = '"+user.getId()+"'",JcUserGroup.class);
        model.addAttribute("provinces", provinces);
        model.addAttribute("tBaseHighshoolList2", tBaseHighshoolList2);
        model.addAttribute("shenfen", user.getAttr().get("province_id"));
        model.addAttribute("wenli", user.getAttr().get("major_type_id"));
        model.addAttribute("cityName", user.getAttr().get("city_id"));
        model.addAttribute("quxianName", user.getAttr().get("quxian_id"));
        model.addAttribute("schoolName", user.getAttr().get("school_id"));
        model.addAttribute("biyeYear", user.getAttr().get("biyeYear"));
        model.addAttribute("banji", user.getAttr().get("banji"));
        model.addAttribute("realName", user.getAttr().get("realName"));
        model.addAttribute("sex", user.getAttr().get("sex"));
        model.addAttribute("qq", user.getAttr().get("qq"));
        model.addAttribute("msn", user.getAttr().get("msn"));
        model.addAttribute("phone", user.getAttr().get("phone"));
        model.addAttribute("pid", user.getAttr().get("province_id"));
        model.addAttribute("mid", user.getAttr().get("major_type_id"));
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, HUIYUANSHEZHI_RESULT);
    }

    @RequestMapping(value = "/quxian.jspx")
    public String quxian(HttpServletRequest request, ModelMap model, String cityId, HttpServletResponse response) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        //城市
        String hql3 = "SELECT DISTINCT quxianId,quxianName  FROM TBaseHighshool WHERE cityId = '" + cityId + "'";
        tBaseHighshoolList1 = commonSvc.findByQueryString(hql3);
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(JSON.toJSONString(tBaseHighshoolList1));
            return "";
        } catch (IOException e) {
            e.printStackTrace();
        }
        //区县
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, HUIYUANSHEZHI_RESULT);
    }

    @RequestMapping(value = "/school.jspx")
    public String school(HttpServletRequest request, ModelMap model, String quxianId, HttpServletResponse response) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        //城市
        String hql4 = "SELECT schoolName,schoolId FROM TBaseHighshool WHERE quxianId = '" + quxianId + "'";
        tBaseHighshoolList2 = commonSvc.findByQueryString(hql4);
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(JSON.toJSONString(tBaseHighshoolList2));
            return "";
        } catch (IOException e) {
            e.printStackTrace();
        }
        //区县
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, HUIYUANSHEZHI_RESULT);
    }

    @RequestMapping(value = "/city.jspx")
    public String city(HttpServletRequest request, ModelMap model, String province_id, HttpServletResponse response) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        //城市
        String hql2 = "SELECT DISTINCT cityName ,cityId FROM TBaseHighshool WHERE provinceId = '" + province_id + "'";
        tBaseHighshoolList = commonSvc.findByQueryString(hql2);
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(JSON.toJSONString(tBaseHighshoolList));
            return "";
        } catch (IOException e) {
            e.printStackTrace();
        }
        //区县
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, HUIYUANSHEZHI_RESULT);
    }

    /**
     * 活动信息列表（应用于背景提升）
     *
     * @return
     */
    @RequestMapping(value = "/member/activityList.jspx")
    public String activityList(HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer userId) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        tnActivities = commonSvc.loadAll(TNActivity.class);
//        tnActivities = activitySvc.tnActivityList(userId);
        model.addAttribute("tnActivities", tnActivities);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, ACTIVITYLIST_RESULT);
    }

    /**
     * 获奖信息列表
     *
     * @return
     */
    @RequestMapping(value = "/member/awardList.jspx")
    public String awardList(HttpServletRequest request, ModelMap model, Integer userId) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        awardList = activitySvc.awardList(userId);
        model.addAttribute("awardList", awardList);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, AWARDLIST_RESULT);
    }

    /**
     * 个人资料详情
     *
     * @return
     */
    @RequestMapping(value = "/member/tNUserList.jspx")
    public String tNUserList(HttpServletRequest request, ModelMap model, Integer userId) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        tnUserList = activitySvc.tNUserList(userId);
        model.addAttribute("tnUserList", tnUserList);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, T_N_USERLIST_RESULT);
    }

    /**
     * 推荐人信息列表
     *
     * @return
     */
    @RequestMapping(value = "/member/tNRefereeList.jspx")
    public String tNRefereeList(HttpServletRequest request, ModelMap model, Integer userId) {
        CmsSite site = CmsUtils.getSite(request);
        tnRefereeList = activitySvc.tNRefereeList(userId);
        model.addAttribute("tnRefereeList", tnRefereeList);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, T_N_REFEREE_RESULT);
    }

    /**
     * 成绩信息列表
     *
     * @return
     */
    @RequestMapping(value = "/member/tNScoreList.jspx")
    public String tNScoreList(HttpServletRequest request, ModelMap model, Integer userId) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        tnScoreList = activitySvc.tNScoreList(userId);
        model.addAttribute("tnScoreList", tnScoreList);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, T_N_SCORE_RESULT);
    }

    /**
     * 新增成绩信息
     *
     * @return
     */
    @RequestMapping(value = "/member/addScore.jspx")
    public String addScore(HttpServletRequest request, ModelMap model, TNScore tnScore) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        model.addAttribute("tnScore", activitySvc.saveScore(tnScore));
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, T_N_SCORE_RESULT);
    }

    /**
     * 修改成绩信息
     *
     * @return
     */
    @RequestMapping(value = "/member/updScore")
    public String updateScore(HttpServletRequest request, ModelMap model, TNScore tnScore, String id) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        model.addAttribute("tnScore", activitySvc.updateScore(tnScore, id));
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, T_N_SCORE_RESULT);
    }

    /**
     * 专利信息列表
     *
     * @return
     */
    @RequestMapping(value = "/member/tNPatentList.jspx")
    public String tNPatentList(HttpServletRequest request, ModelMap model, Integer userId) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        tnPatentList = activitySvc.tNPatentList(userId);
        model.addAttribute("tnPatentList", tnPatentList);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, T_N_PATENT_RESULT);
    }

    /**
     * 新增专利信息
     *
     * @return
     */
    @RequestMapping(value = "/member/addPatent.jspx")
    public String addPatent(HttpServletRequest request, ModelMap model, TNPatent tnPatent) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        model.addAttribute("tnPent", activitySvc.savePatent(tnPatent));
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, T_N_PATENT_RESULT);
    }

    /**
     * 修改专利信息
     *
     * @retuen
     */
    @RequestMapping(value = "/member/updPatent.jspx")
    public String updPatent(HttpServletRequest request, ModelMap model, TNPatent tnPatent, String id) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        model.addAttribute("tnPatent", activitySvc.updatePatent(tnPatent, id));
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, T_N_PATENT_RESULT);
    }

    /**
     * 教育经历列表
     *
     * @return
     */
    @RequestMapping(value = "/member/tNEducationList.jspx")
    public String tNEducationList(HttpServletRequest request, ModelMap model, Integer userId) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        tnEducationList = activitySvc.tNEducationList(userId);
        model.addAttribute("tnEducationList", tnEducationList);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, T_N_EDUCATION_RESULT);
    }

    /**
     * 修改教育经历
     *
     * @return
     */
    @RequestMapping(value = "/member/updEducation.jspx")
    public String updateEducatioin(HttpServletRequest request, ModelMap model, TNEducation tnEducation, String id) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        model.addAttribute("tnEducation", activitySvc.updateEducation(tnEducation, id));
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, T_N_EDUCATION_RESULT);
    }

    /**
     * 新增教育经历
     *
     * @return
     */
    @RequestMapping(value = "/member/addEducation.jspx")
    public String addEducation(HttpServletRequest request, ModelMap model, TNEducation tnEducation) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        model.addAttribute("tnEducation", activitySvc.saveEductioin(tnEducation));
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, T_N_EDUCATION_RESULT);
    }

    /**
     * 家庭信息列表
     *
     * @return
     */
    @RequestMapping(value = "/member/tNFamilyList.jspx")
    public String tNFamilyList(HttpServletRequest request, ModelMap model, Integer userId) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        tnFamilyList = activitySvc.tNFamily(userId);
        model.addAttribute("tnFamilyList", tnFamilyList);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, T_N_FAMILY_RESULT);
    }

    /**
     * 中学信息列表
     *
     * @return
     */
    @RequestMapping(value = "/member/tNSchoolList.jspx")
    public String tNSchoolList(HttpServletRequest request, ModelMap model, Integer userId) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        tnSchoolList = activitySvc.tNSchoolList(userId);
        model.addAttribute("tnSchoolList", tnSchoolList);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, T_N_SCHOOL_RESULT);
    }

    /**
     * 修改个人资料
     *
     * @return
     */
    @RequestMapping(value = "/member/updateUser.jspx")
    public String updateUser(HttpServletRequest request, ModelMap model, TNUser tnUser, String userAccount) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        model.addAttribute("tnUser", activitySvc.updateUser(tnUser, userAccount));
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, T_N_USERLIST_RESULT);
    }

    /**
     * 新增个人资料
     *
     * @return
     */
    @RequestMapping(value = "/member/saveUser.jspx")
    public String savaUser(HttpServletRequest request, ModelMap model, TNUser tnUser) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        model.addAttribute("addTnUser", activitySvc.saveUser(tnUser));
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_ACTIVITY, T_N_USERLIST_RESULT);
    }


}
