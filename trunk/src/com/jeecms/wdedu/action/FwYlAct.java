package com.jeecms.wdedu.action;

import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.TDataUniversityDetail;
import com.jeecms.wdedu.entity.TMgrSrvNode;
import com.jeecms.wdedu.entity.TMgrSrvOrder;
import com.jeecms.wdedu.service.CommonSvc;
import com.jeecms.wdedu.service.FwYlSvc;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author fanyue
 * @ProjectName wdedu
 * @Description: 服务一览
 * @date 2018/10/23
 */
@Controller
@RequestMapping(value = "/FwYl")
public class FwYlAct {

    public final static String TPLDIR_FWYLACT = "fwyl";
    public final static String ORDERLIST_RESULT = "orderList";
    public final static String BEIJING_RESULT = "BackgroundAscension";
    public final static String ZZZP_RESULT = "zizhuJudgedBy1";
    public final static String ZPFW_RESULT = "zizhuJudgedBy2";
    public final static String ZCZX_RESULT = "zizhuJudgedBy3";
    public final static String BKJD_RESULT = "baokaojindu";
    public final static String BJDX_RESULT = "baokaojinduBJDX";
    public final static String FWPJ_RESULT = "fuwupingjia";
    public final static String FWXZ_RESULT = "fuwuxuzhi";
    public final static String WDXX_RESULT = "wodexiaoxi";
    public final static String YXZC_RESULT = "yuanxiaozhangcheng";
    public final static String SWITHTYPE_RESULT = "switchType";

    @Autowired
    private FwYlSvc fwYlSvc;
    @Autowired
    private CommonSvc commonSvc;
    private List<TMgrSrvOrder> tnOrderList;
    private List<TMgrSrvNode> tnNodeList;
    private List<TMgrSrvNode> tnNodeList1;
    private List<TMgrSrvNode> tnNodeList2;
    private List<TMgrSrvNode> tnNodeList3;
    private List<TDataUniversityDetail> tDataUniversityDetailList;

    /**
     * 订单查询
     * @param request
     * @param model
     * @param userId
     * @return
     */
    @RequestMapping(value = "/member/orderList.jspx")
    public String orderQuery(HttpServletRequest request, ModelMap model,Integer userId){
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        userId = user.getId();
        FrontUtils.frontData(request,model,site);
//        tnOrderList =commonSvc.findByProperty(TNOrder.class,"singlerUserId",singlerUserId);
//        tnOrderList = fwYlSvc.queryOrder(userId);

        String hql = "FROM TMgrSrvOrder t,TMgrUserBaseUserEntity u WHERE t.stuUserid = u.userId AND t.stuUserid = '"+userId+"'";
        tnOrderList = commonSvc.findByQueryString(hql);
//        commonSvc.executeSql("SELECT * FROM TNOrder,TNUser WHERE singlerUserId = userId AND singlerUserId = '"+singlierUserId+"'",tnOrderList);
        String hql1 = "FROM TMgrSrvOrder a, TMgrSrvNode b WHERE a.stuUserid = '"+userId+"' AND a.orderId = b.orderId";
        tnNodeList = commonSvc.findByQueryString(hql1);
        String hql2 = "FROM TMgrSrvOrder a , CmsUser b WHERE a.srvSinglerUserid = b.id AND a.stuUserid ='"+userId+"'";
        tnNodeList1 = commonSvc.findByQueryString(hql2);
        String hql3 = "FROM TMgrSrvOrder a , CmsUser b WHERE a.srvMainTeacherid = b.id AND a.stuUserid ='"+userId+"'";
        tnNodeList2 = commonSvc.findByQueryString(hql3);
        String hql4= "FROM TMgrSrvOrder a , CmsUser b WHERE a.srvAssistTeacherid = b.id AND a.stuUserid ='"+userId+"'";
        tnNodeList3 = commonSvc.findByQueryString(hql4);
        model.addAttribute("tnNodeList",tnNodeList);
        model.addAttribute("tnOrderList",tnOrderList);
        model.addAttribute("tnNodeList1",tnNodeList1);
        model.addAttribute("tnNodeList2",tnNodeList2);
        model.addAttribute("tnNodeList3",tnNodeList3);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_FWYLACT, ORDERLIST_RESULT);
    }

    @RequiresPermissions(value = {"ZizhaoZongpin:*","PuTongZiZhuZhaoSheng:*","ZongHePingJia:*"})
    @RequestMapping(value = "/member/fwyl.jspx")
    public String fwyl(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            return FrontUtils.showLogin(request, model, site);
        }
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_FWYLACT, ZZZP_RESULT);
    }
    /**
     * 学生背景分析,性格测试，个人陈述，面试笔试等信息展示
     * @return
     */
//    @RequestMapping(value = "/member/beijing.jspx")
//    public String beiJing(HttpServletRequest request,ModelMap model,Integer userId,String nodeType){
//        CmsSite site = CmsUtils.getSite(request);
//        CmsUser user = CmsUtils.getUser(request);
//        FrontUtils.frontData(request,model,site);
//        userId = user.getId();
////        tnNodeList = fwYlSvc.queryNode(userId);
//
//        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_FWYLACT, ORDERLIST_RESULT);
//    }

    /**
     * 自主综评服务
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/zpfw.jspx")
    public String zpfw(HttpServletRequest request,ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request,model,site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_FWYLACT, ZPFW_RESULT);
    }

    /**
     * 章程咨询
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("ZhangchengZixun:*")
    @RequestMapping("/zczx.jspx")
    public String zczx(HttpServletRequest request,ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request,model,site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_FWYLACT, ZCZX_RESULT);
    }

    /**
     * 背景提升
     */
    @RequestMapping(value = "/BackgroundAscension.jspx")
    public String bjts(HttpServletRequest request,ModelMap model){
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        if(null!=user){
            model.addAttribute("group",user.getGroup());
        }
        model.addAttribute("user",user);
        FrontUtils.frontData(request,model,site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_FWYLACT, BEIJING_RESULT);
    }
    /*
     * 报考进度
     */
    @RequestMapping(value = "/baokaojindu.jspx")
    public String bkjd(HttpServletRequest request,ModelMap model){
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request,model,site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_FWYLACT, BKJD_RESULT);
    }
    /*
     * BJDX
     */
    @RequestMapping(value = "/bjdx.jspx")
    public String bjdx(HttpServletRequest request,ModelMap model){
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request,model,site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_FWYLACT, BJDX_RESULT);
    }
    /*
     * 服务评价
     */
    @RequestMapping(value = "/fuwupingjia.jspx")
    public String fwpj(HttpServletRequest request,ModelMap model){
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request,model,site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_FWYLACT, FWPJ_RESULT);
    }
    /*
     *服务须知
     */
    @RequestMapping(value = "/fuwuxuzhi.jspx")
    public String fwxz(HttpServletRequest request,ModelMap model){
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request,model,site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_FWYLACT, FWXZ_RESULT);
    }
    /*
     * 我的消息
     */
    @RequestMapping(value = "/wodexiaoxi.jspx")
    public String wdxx(HttpServletRequest request,ModelMap model){
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request,model,site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_FWYLACT, WDXX_RESULT);
    }
    /*
     * 院校章程
     */
    @RequestMapping(value = "/yuanxiaozhangcheng.jspx")
    public String yxzc(HttpServletRequest request,ModelMap model){
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
//        tDataUniversityDetailList = commonSvc.loadAll(TDataUniversityDetail.class);
        String hql = "SELECT univName FROM TDataUniversityDetail";
        tDataUniversityDetailList = commonSvc.findByQueryString(hql);
        model.addAttribute("tDataUniversityDetailList",tDataUniversityDetailList);
        FrontUtils.frontData(request,model,site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_FWYLACT, YXZC_RESULT);
    }
    /*
     * 服务类型选择
     */
    @RequestMapping(value = "/swithtype.jspx")
    public String swithType(HttpServletRequest request,ModelMap model){
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        model.addAttribute("name",user.getUsername());
        FrontUtils.frontData(request,model,site);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_FWYLACT, SWITHTYPE_RESULT);
    }

    /**
     * 自招服务资料
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/fwzl.jspx")
    public String fwzl(HttpServletRequest request,ModelMap model, String title) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request,model,site);
        model.addAttribute("title", title);
        return FrontUtils.getTplPath(site.getSolutionPath(), TPLDIR_FWYLACT, "fwzl");
    }

    /**
     * 自招招生模拟网报 D2019010300001
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("ZhaoShengZhangCheng:*")
    @RequestMapping("/mnwb.jspx")
    public String mnwb(HttpServletRequest request,ModelMap model, String orderId) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request,model,site);
        if (StringUtils.isBlank(orderId)) {
            orderId = "D2019010300001";
        }
        TMgrSrvOrder order = commonSvc.get(TMgrSrvOrder.class, orderId);
        model.addAttribute("order", order);
        return FrontUtils.getTplPath(site.getSolutionPath(), "mnwb", "mnwb");
    }
}
