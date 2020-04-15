package com.wdedu.action.apply;

import com.alibaba.fastjson.JSON;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.TBaseHighshool;
import com.jeecms.wdedu.service.CommonSvc;
import com.wdedu.entity.TDataActivityDetailEntity;
import com.wdedu.entity.TDataOnlineSignEntity;
import com.wdedu.service.ActiveApplySvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/activeApplyAct")
public class ActiveApplyAct {
    @Autowired
    private ActiveApplySvc activeApplySvc;
    @Autowired
    private CommonSvc commonSvc;

    //背景提升活动列表
    @RequestMapping(value = "/findActive.jspx")
    public String findActive(HttpServletRequest request, HttpServletResponse response,
                             ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

       /* StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM t_data_activity_detail");
        sb.append(" ORDER BY activity_start_time DESC");
        List <TDataActivityDetailEntity> list = commonSvc.findListbySql(sb.toString(),TDataActivityDetailEntity.class);*/

        List<TDataActivityDetailEntity> activeList = activeApplySvc.findList();
        if (activeList != null && activeList.size() > 0) {
            model.addAttribute("activeList", activeList);
        }

        return FrontUtils.getTplPath(site.getSolutionPath(), "wdedu/zhszts", "beijingtisheng");
    }

    //验证用户
    @RequestMapping(value = "/testUser.jspx")
    public String testUser(HttpServletRequest request,ModelMap model){
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if(user==null){
            return FrontUtils.showLogin(request, model, site);
        }
        return "";
    }


    //活动报名
    @RequestMapping(value = "/applyActive.jspx")
    public String applyActive(HttpServletRequest request, HttpServletResponse response, String contentId, String activityType) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        Map<String, Object> map = new HashMap<>();

        String telephone = user.getUsername();
        String schoolId = user.getAttr().get("school_id");
        String activityId="";
       /* if (date != null && date.length() > 0) {
            date = date.substring(2, 4);
        }*/

        if (contentId==null&&activityType.equals("强击计划")){
            activityId="001";
        }
        else if (contentId==null&&activityType.equals("综合评价")){
            activityId="002";
        }
        else if (contentId==null&&activityType.equals("中外合作办学")){
            activityId="003";
        }
        else if (contentId==null&&activityType.equals("港澳台申请")){
            activityId="004";
        }
        else if (contentId==null&&activityType.equals("艺术类")){
            activityId="005";
        }
        else if (contentId==null&&activityType.equals("生涯规划专家一对一服务")){
            activityId="006";
        }
        else if (contentId==null&&activityType.equals("多元录取专家一对一服务")){
            activityId="007";
        }
        else if (contentId==null&&activityType.equals("志愿填报专家一对一服务")){
            activityId="008";
        }
        else if (contentId==null&&activityType.equals("港澳院校申请专家一对一服务")){
            activityId="009";
        }
        else {
            activityId = contentId;
        }

        String sql = "SELECT * from t_data_online_sign WHERE activity_id ='" + activityId + "' AND telephone ='" + telephone + "'";
        List<TDataOnlineSignEntity> list = commonSvc.findListbySql(sql, TDataOnlineSignEntity.class);
        if (list != null && list.size() > 0) {
            map.put("msg", "已报名");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(map));
        } else {
            TDataOnlineSignEntity os = new TDataOnlineSignEntity();
            os.setActivityId(activityId);
            os.setActivityType(activityType);
            os.setRealname(user.getAttr().get("realName"));
            os.setTelephone(telephone);
            os.setMajorType(user.getAttr().get("major_type_id"));
            os.setClasses(user.getAttr().get("banji"));
            os.setJiebie(user.getAttr().get("biyeYear"));
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = formatter.format(currentTime);
            os.setDatetimes(datetime);


            List<TBaseHighshool> schoolList = commonSvc.findByProperty(TBaseHighshool.class, "schoolId", schoolId);
            if (schoolList != null && schoolList.size() > 0) {
                TBaseHighshool school = schoolList.get(0);
                os.setSchool(school.getSchoolName());
                os.setProvince(school.getProvinceName());
                os.setCity(school.getCityName());
                os.setQuxian(school.getQuxianName());
            }
            commonSvc.save(os);
            map.put("msg", "报名成功");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(map));
        }
        return "";
    }

}
