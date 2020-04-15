package com.jeecms.wdedu.action;

import com.alibaba.fastjson.JSON;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.AuthenticationMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.core.web.util.JsonWriteUtil;
import com.jeecms.wdedu.entity.*;
import com.jeecms.wdedu.service.CommonSvc;
import com.utils.StringUtil;
import com.utils.StringUtils;
import com.wdedu.entity.TDataActivityDetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//栏目内容展示聚合

@RequestMapping(value = "/NewsDisplay")
public class NewsDisplayController {

    public final static String TPLDIR_zhszts = "wdedu/zhszts";
    public final static String TPLDIR_yzsfw = "wdedu/yzsfw";
    public final static String TPLDIR_dylq = "wdedu/dylq";
    public final static String TPLDIR_msdjt = "wdedu/msdjt";
    public final static String TPLDIR_xgkxk = "wdedu/xgkxk";
    public final static String TPLDIR_zjtd = "wdedu/zjtd";
    public final static String TPLDIR_gsjj = "wdedu/gsjj";
    public final static String TPLDIR_wdxy = "wdedu/wdxy";
    public final static int channel_id_bjts = 153;
    public final static int channel_id_gxyx = 154;
    public final static int channel_id_mqyx = 153;
    public final static int channel_id_zyty = 153;
    public final static int channel_id_ktyj = 153;
    public final static int channel_id_sszn = 153;
    public final static int channel_id_hwyx = 153;
    public final static int channel_id_shsj = 153;
    public final static int channel_id_zzzs = 167;
    public final static int channel_id_zhpj = 153;
    public final static int channel_id_zwhzbx = 153;
    public final static int channel_id_gatsq = 153;
    public final static int channel_id_ysl = 153;
    public final static int channel_id_xgkxkzx = 153;

    @Autowired
    private AuthenticationMng authMng;
    @Autowired
    private SessionProvider session;
    @Autowired
    private CommonSvc commonSvc;

    //    首页显示登陆后用户信息
    @RequestMapping(value = "/getUser.jspx")
    public void getUser(HttpServletRequest request,
                        HttpServletResponse response, ModelMap model, String id) throws Exception {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        CmsUser user = CmsUtils.getUser(request);
        String province_id = null;
        HashMap<String, Object> map = new HashMap<String, Object>();

        if (user != null) {
            province_id = user.getAttr().get("province_id");
            if (province_id == null) {
                province_id = "0";
            }
            TBaseProvince tBaseProvince = commonSvc.get(TBaseProvince.class, Integer.valueOf(province_id));
            String province_name = tBaseProvince.getProvinceName();
            String major_type_id = user.getAttr().get("major_type_id");
            if (major_type_id == null) {
                major_type_id = "未知";
            }
            String user_name = user.getUsername();
            String user_realname = user.getRealname();

            map.put("province_name", province_name);
            map.put("major_type_id", major_type_id);
            map.put("user_name", user_name);
            map.put("user_realname", user_realname);
        }

        HttpSession sessions = request.getSession();
        Map<String, String> map1 = (Map<String, String>) sessions.getAttribute("getUserInfo");
        String chooseProvince = null;
        if (map1 != null) {
            chooseProvince = map1.get("chooseProvince");
        }

        if (StringUtils.isNotBlank(id)) {
            chooseProvince = id;
        } else {
            if (StringUtils.isNotBlank(province_id)) {
                chooseProvince = province_id;
            } else {
                if (StringUtils.isEmpty(chooseProvince)) {
                    chooseProvince = "1";
                }
            }
        }
        map.put("chooseProvince", chooseProvince);

        session.setAttribute(request, response, "getUserInfo", map);

        Map<String, String> map22 = (Map<String, String>) sessions.getAttribute("getUserInfo");
        String chooseProvince11 = "1";
        if (map22 != null) {
            chooseProvince11 = map22.get("chooseProvince");
        }
        List<TScActiveDetail> tScActiveDetails = commonSvc.findByPropertyisOrder(TScActiveDetail.class, "proId", chooseProvince11, "activeTime", false);
        map.put("tScActiveDetails", tScActiveDetails);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
        /* JsonWriteUtil.write(response, map);*/
    }


    //    背景提升
    @RequestMapping(value = "/BackgroundAscension.jspx")
    public String bjts(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
//        Integer content_id = commonSvc.singleResult("select max(content_id) from jc_content_channel\n" +
//                "where channel_id = " + channel_id_bjts + " \n");

        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_bjts + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());

        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("user", user);
        model.addAttribute("newContentId", content_id);
//        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null && (userInfoMap.get("chooseProvince") != null)) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_zhszts, "beijingtisheng");
    }

    //    高校研学
    @RequestMapping(value = "/UniversityStudies.jspx")
    public String gxyx(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_gxyx + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_zhszts, "gaoxiaoyanxue");
    }

    //    名企研学
    @RequestMapping(value = "/InternationalStudies.jspx")
    public String mqyx(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);

        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_mqyx + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_zhszts, "mingqiyanxue");
    }

    //    职业体验
    @RequestMapping(value = "/WorkExperience.jspx")
    public String zyty(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_zyty + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_zhszts, "zhiyetiyan");
    }

    //    课题研究
    @RequestMapping(value = "/SubjectStudy.jspx")
    public String ktyj(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_ktyj + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_zhszts, "ketiyanjiu");
    }

    //    赛事指南
    @RequestMapping(value = "/EventGuide.jspx")
    public String sszn(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_sszn + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_zhszts, "saishizhinan");
    }

    //    海外游学
    @RequestMapping(value = "/Yoosure.jspx")
    public String hwyx(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_hwyx + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_zhszts, "haiwaiyouxue");
    }

    //    社会实践
    @RequestMapping(value = "/SocialPractice.jspx")
    public String shsj(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_shsj + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_zhszts, "shehuishijian");
    }

    //判断升学卡页面跳转
    @RequestMapping(value = "/testSxk.jspx")
    public String testSxk(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            String sql = "select p.province_name,p.province_id from t_base_products s \n" +
                    "LEFT JOIN t_base_province p \n" +
                    "ON s.province_id=p.province_id \n" +
                    "GROUP BY s.province_id  ";
            List<Map<String, Object>> provinces = commonSvc.findForJdbc(sql);
            if (provinces != null && provinces.size() > 0) {
                model.addAttribute("provinces", provinces);

                List<TBaseProducts> productsList = commonSvc.findByProperty(TBaseProducts.class, "provinceId", 1);
                if (productsList != null && productsList.size() > 0) {
                    model.addAttribute("productsList", productsList);
                    model.addAttribute("k1", productsList.get(0));
                }
            }
            FrontUtils.frontData(request, model, site);
            return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_yzsfw, "shengxuekaLogin");
        } else {
            model.addAttribute("group", user.getGroup());
            model.addAttribute("user", user);
            Integer isAdmin = 0;

            if (user.getAdmin()) {
                isAdmin = 1;
            }

            model.addAttribute("isAdmin", isAdmin);
            String provinceId = user.getAttr().get("province_id");
            String sql = "SELECT * FROM t_yn_online_order WHERE user_id ='" + user.getId() + "' and pay_state=1";
            List<TYnOnlineOrder> orderList = commonSvc.findListbySql(sql, TYnOnlineOrder.class);
            if (orderList != null && orderList.size() > 0) {
                model.addAttribute("orderList", JSON.toJSONString(orderList));
            }
            String str = "";
//            List<TBaseProducts> list = commonSvc.findByProperty(TBaseProducts.class, "provinceId", Integer.valueOf(provinceId));
            String findProductsHql = "SELECT a.*,b.user_id FROM t_base_products a \n" +
                    "LEFT JOIN (SELECT * FROM jc_user_group \n" +
                    "WHERE user_id = " + user.getId() + " ) b \n" +
                    "ON a.group_id = b.group_id \n" +
                    "WHERE a.province_id = " + Integer.valueOf(provinceId) + " ";
            List<Map<String, Object>> list = commonSvc.findForJdbc(findProductsHql, null);
            if (list != null && list.size() > 0) {
                model.addAttribute("k1", list.get(0));
                model.addAttribute("list", list);
                model.addAttribute("list_new", JSON.toJSONString(list));
                for (Map listMap : list) {
                    str = str + listMap.get("group_id") + ",";
                }
                String strm = str.substring(0, str.length() - 1);
                model.addAttribute("strm", strm);
            }


            //        获取sessions省份ID
            HttpSession sessions = request.getSession();
            Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
            Integer chooseProvinceId = null;
            if (userInfoMap != null) {
                chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
            }
            model.addAttribute("chooseProvinceId", chooseProvinceId);
            FrontUtils.frontData(request, model, site);
            return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_yzsfw, "shengxueka");
        }
    }

    //根据省份选产品
    @RequestMapping(value = "/getProdunct.jspx")
    public void getProdunct(HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer provinceId) throws IOException {
        List<TBaseProducts> productsList = commonSvc.findByProperty(TBaseProducts.class, "provinceId", provinceId);

        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(productsList));

    }

    //    升学卡
   /* @RequestMapping(value = "/EntranceCard.jspx")
    public String sxk(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
            model.addAttribute("user", user);
            String provinceId = user.getAttr().get("province_id");
            String telephone = user.getAttr().get("phone");
            String userName = user.getAttr().get("realName");
            String sql = "SELECT * FROM t_yn_online_order WHERE user_id ='" + user.getId() + "' and pay_state=1";
            List<TYnOnlineOrder> orderList = commonSvc.findListbySql(sql, TYnOnlineOrder.class);
            if (orderList != null && orderList.size() > 0) {
                model.addAttribute("orderList", JSON.toJSONString(orderList));
            }
            String str = "";
            List<TBaseProducts> list = commonSvc.findByProperty(TBaseProducts.class, "provinceId", Integer.valueOf(provinceId));
            if (list != null && list.size() > 0) {
                model.addAttribute("k1", list.get(0));
                model.addAttribute("list", list);
                model.addAttribute("list_new",JSON.toJSONString(list));
                for (TBaseProducts pro : list) {
                    str = str + pro.getGroupId() + ",";
                }
                String strm = str.substring(0, str.length() - 1);
                model.addAttribute("strm", strm);

            }
        }


        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_yzsfw, "shengxueka");
    }*/

    //    升学规划专家服务
    @RequestMapping(value = "/AcademicPlanningExpertServices.jspx")
    public String sxghzjfw(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_yzsfw, "shengxueguihuazhuanjiafuwu");
    }

    //    多元录取专家服务
    @RequestMapping(value = "/MultipleAdmissionExpertServices.jspx")
    public String dylqzjfw(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_yzsfw, "duoyuanluquzhuanjiafuwu");
    }

    //    志愿填报专家服务
    @RequestMapping(value = "/VolunteerExpertServices.jspx")
    public String zytbzjfw(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_yzsfw, "zhiyuantianbaozhuanjiafuwu");
    }

    //    港澳台申请服务
    @RequestMapping(value = "/ApplicationServiceForHongKongMacaoTaiwan.jspx")
    public String gatsqfw(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_yzsfw, "gangaotaishenqingfuwu");
    }

    //    自主招生
    @RequestMapping(value = "/IndependentRecruitment.jspx")
    public String zzzs(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_zzzs + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_dylq, "zizhuzhaosheng");
    }

    //    自主招生详情
    @RequestMapping(value = "/IndependentRecruitmentDetail.jspx")
    public String zzzsxq(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);

        Integer content_id = 0;
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_dylq, "zizhuzhaosheng_xq");
    }

    //    综合评价
    @RequestMapping(value = "/ComprehensiveAssessment.jspx")
    public String zhpj(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_zhpj + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_dylq, "zonghepingjia");
    }

    //    综合评价详情
    @RequestMapping(value = "/ComprehensiveAssessmentDetail.jspx")
    public String zhpjxq(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);

        Integer content_id = 0;
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_dylq, "zonghepingjia_xq");
    }

    //    中外合作办学
    @RequestMapping(value = "/SinoforeignCooperativeEducation.jspx")
    public String zwhzbx(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_zwhzbx + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_dylq, "zhongwaihezuobanxue");
    }

    //   中外合作办学详情
    @RequestMapping(value = "/SinoforeignCooperativeEducationDetail.jspx")
    public String zwhzbxxq(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);

        Integer content_id = 0;
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_dylq, "zhongwaihezuobanxue_xq");
    }

    //    港澳台申请
    @RequestMapping(value = "/HongKongMacaoTaiwan.jspx")
    public String gatsq(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_gatsq + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_dylq, "gangaotaishenqing");
    }

    //   港澳台申请详情
    @RequestMapping(value = "/HongKongMacaoTaiwanDetail.jspx")
    public String gatsqxq(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);

        Integer content_id = 0;
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_dylq, "gangaotaishenqing_xq");
    }

    //    艺术类
    @RequestMapping(value = "/Arts.jspx")
    public String ysl(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_ysl + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_dylq, "yishulei");
    }

    //   艺术类详情
    @RequestMapping(value = "/ArtsDetail.jspx")
    public String yslxq(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);

        Integer content_id = 0;
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_dylq, "yishulei_xq");
    }

    //    招办讲堂
    @RequestMapping(value = "/AdmissionsOfficeLectureHall.jspx")
    public String zbjt(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_msdjt, "zhaobanjiangtang");
    }

    //    招办讲堂列表
    @RequestMapping(value = "/AdmissionsOfficeLectureHallList.jspx")
    public String zbjtlb(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_msdjt, "zhaobanjiangtang_list");
    }

    //    沃得e课
    @RequestMapping(value = "/WodecareerELesson.jspx")
    public String wdek(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_msdjt, "wodeeke");
    }

    //    沃得e课
    @RequestMapping(value = "/WodecareerELessonList.jspx")
    public String wdeklb(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_msdjt, "wodeeke_list");
    }

    //    媒体访谈
    @RequestMapping(value = "/MediaInterviews.jspx")
    public String mtft(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_msdjt, "meitifangtan");
    }

    //    媒体访谈列表
    @RequestMapping(value = "/MediaInterviewsList.jspx")
    public String mtftlb(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_msdjt, "meitifangtan_list");
    }

    //    全国巡讲
    @RequestMapping(value = "/TheNationalTour.jspx")
    public String qgxj(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        Integer content_id = 0;
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_msdjt, "quanguoxunjiang");
    }

    //    全国巡讲列表
    @RequestMapping(value = "/TheNationalTourDetail.jspx")
    public String qgxjlb(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        Integer content_id = 0;
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_msdjt, "quanguoxunjiang_xq");
    }

    //    新高考选科资讯
    @RequestMapping(value = "/InformationOnTheNewCollegeEntranceExamination.jspx")
    public String xgkxkzx(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_xgkxkzx + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_xgkxk, "xingaokaoxuankezixun");
    }

    //    专家团队列表
    @RequestMapping(value = "/TeamOfExperts.jspx")
    public String zjtd(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_xgkxkzx + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_zjtd, "zhuanjiatuandui_list");
    }

    //    专家团队详情
    @RequestMapping(value = "/TeamOfExpertsDetail.jspx")
    public String zjtdxq(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_xgkxkzx + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_zjtd, "zhuanjiatuandui_xq");
    }

    //    历年案例
    @RequestMapping(value = "/AllCases.jspx")
    public String lnal(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_xgkxkzx + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_zjtd, "liniananli_list");
    }

    //    历年案例详情
    @RequestMapping(value = "/AllCasesDetail.jspx")
    public String lnalxq(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_xgkxkzx + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_zjtd, "liniananli_xq");
    }

    //    荣誉榜单
    @RequestMapping(value = "/HonorList.jspx")
    public String rybd(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_xgkxkzx + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_zjtd, "rongyubangdan_list");
    }

    //    荣誉榜单详情
    @RequestMapping(value = "/HonorListDetail.jspx")
    public String rybdxq(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_xgkxkzx + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_zjtd, "rongyubangdan_xq");
    }

    //    家长反馈
    @RequestMapping(value = "/ParentsFeedback.jspx")
    public String jzfk(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_xgkxkzx + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_zjtd, "jiazhangfankui_list");
    }

    //    家长反馈详情
    @RequestMapping(value = "/ParentsFeedbackDetail.jspx")
    public String jzfkxq(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
        model.addAttribute("user", user);
        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_xgkxkzx + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());
        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_zjtd, "jiazhangfankui_xq");
    }

    //    高考头条号
    @RequestMapping(value = "/CollegeEntranceExaminationHeadlineNumberDetail.jspx")
    public String gktth(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }
//        Integer content_id = commonSvc.singleResult("select max(content_id) from jc_content_channel\n" +
//                "where channel_id = " + channel_id_bjts + " \n");

        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_bjts + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());

        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("user", user);
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_zhszts, "gaokaotoutiaohaoxq");


    }

    //    公司简介
    @RequestMapping(value = "/companyProfile.jspx")
    public String gsjj(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }

        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_bjts + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());

        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("user", user);
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_gsjj, "gongsijianjie");
    }

    //    沃得学院
    @RequestMapping(value = "/InstituteOfWorld.jspx")
    public String wdxy(HttpServletRequest request, ModelMap model, Integer contentId) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (null != user) {
            model.addAttribute("group", user.getGroup());
        }

        String Sql = "SELECT MAX(content_id) as maxContentId FROM jc_content_channel\n" +
                "WHERE channel_id = " + channel_id_bjts + " ";
        Map<String, Object> newMap = commonSvc.findOneForJdbc(Sql);
        Integer content_id = Integer.parseInt(newMap.get("maxContentId").toString());

        if (StringUtils.isNotBlank(request.getParameter("contentId"))) {
            content_id = Integer.valueOf(request.getParameter("contentId"));
        }
        model.addAttribute("user", user);
        model.addAttribute("newContentId", content_id);
        //        获取sessions省份ID
        HttpSession sessions = request.getSession();
        Map<String, String> userInfoMap = (Map<String, String>) sessions.getAttribute("getUserInfo");
        Integer chooseProvinceId = null;
        if (userInfoMap != null) {
            chooseProvinceId = Integer.valueOf(userInfoMap.get("chooseProvince"));
        }
        model.addAttribute("chooseProvinceId", chooseProvinceId);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_wdxy, "wodexueyuan");
    }

}
