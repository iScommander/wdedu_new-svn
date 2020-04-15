package com.jeecms.wdedu.action;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsGroup;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsGroupMng;
import com.jeecms.core.manager.CmsLogMng;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.wdedu.entity.TBaseProvince;
import com.jeecms.wdedu.entity.TBaseVrcards;
import com.jeecms.wdedu.service.CommonSvc;
import com.utils.BrowserUtils;
import com.utils.MyBeanUtils;
import com.utils.PasswordUtil;
import com.utils.excel.ExcelExportUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.jeecms.common.page.SimplePage.cpn;

/**
 * 卡片信息管理Controller
 * 
 * @author 卡片信息管理
 */
@Controller
public class CardController {
    private static final Logger LOG = LoggerFactory.getLogger(CardController.class);
    public static final String TPLDIR = "";

    @RequiresPermissions("card:v_list")
    @RequestMapping(value = "/card/v_list.do")
    public String v_list(Long from, Long to, Integer provinceId, Integer groupId, Boolean active, Boolean owner, Date activeBegin,
            Date activeEnd, Date inactiveBegin, Date inactiveEnd, Integer userId, Integer status, Integer pageNo, String channel,
            HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser cmsUser = CmsUtils.getUser(request);
        List<CmsUser> users = cmsUserMng.getAdminList(site.getId(), null, null, null);

        FrontUtils.frontData(request, model, site);

        Finder finder = Finder.create("select distinct bean from TBaseVrcards bean ");
        finder.append(" where 1=1 ");
        if (from != null) {
            finder.append(" and bean.cardNo between :from and :to ");
            finder.setParam("from", from);
            finder.setParam("to", to);
        }
        request.getSession().setAttribute("from", from);
        request.getSession().setAttribute("to", to);

        if (groupId != null) {
            finder.append(" and bean.groupId = :groupId");
            finder.setParam("groupId", groupId);
        }
        if (active != null) {
            finder.append(" and bean.activeStatus = :activeStatus");
            finder.setParam("activeStatus", active);
        }

        if (activeBegin != null && activeEnd != null) {
            finder.append(" and bean.activeTime >= :active_begin");
            finder.setParam("active_begin", activeBegin);
            finder.append(" and bean.activeTime <= :active_end");
            finder.setParam("active_end", activeEnd);
        }
        if (inactiveBegin != null && inactiveEnd != null) {
            finder.append(" and bean.losedTime >= :inactive_begin");
            finder.setParam("inactive_begin", inactiveBegin);
            finder.append(" and bean.losedTime <= :inactive_end");
            finder.setParam("inactive_end", inactiveEnd);
        }

        request.getSession().setAttribute("province_id", provinceId);
        if (provinceId != null) {
            finder.append(" and bean.provinceId=:province_id");
            finder.setParam("province_id", provinceId);
        }

        // 判断是否是本人
        if (owner != null && owner == true) {
            finder.append(" and bean.chnUserId = :channel_user_id");
            finder.setParam("channel_user_id", cmsUser.getId());
        }

        if (userId != null && userId != 0) {
            finder.append(" and bean.chnUserId = :channel_user_id");
            finder.setParam("channel_user_id", userId);
        }
        if (status != null && status != -1) {
            finder.append(" and bean.activeStatus = :status");
            finder.setParam("status", status);
        }
        if (StringUtils.isNotBlank(channel)) {
            finder.append(" and bean.order.channel = :channel");
            finder.setParam("channel", channel);
        }
        Pagination pagination = commonService.findPager(finder, cpn(pageNo), CookieUtils.getPageSize(request));
        model.addAttribute("pagination", pagination);
        model.addAttribute("pageNo", pagination.getPageNo());
        model.addAttribute("provinceList", commonService.loadAll(TBaseProvince.class));
        model.addAttribute("users", users);

        List<CmsGroup> groupList = cmsGroupMng.getList();
        model.addAttribute("groupList", groupList);

        model.addAttribute("inactive_begin", inactiveBegin);
        model.addAttribute("inactive_end", inactiveEnd);
        model.addAttribute("active_begin", activeBegin);
        model.addAttribute("active_end", activeEnd);
        model.addAttribute("is_owner", owner);
        model.addAttribute("is_active", active);
        model.addAttribute("group_id", groupId);
        model.addAttribute("userId", userId);
        model.addAttribute("status", status);
        model.addAttribute("channel", channel);
        return "wdedu/card/list";
    }

    /*
     * 展现页面
     */
    @RequiresPermissions("card:v_edit")
    @RequestMapping(value = "/card/v_edit.do")
    public String o_edit(Long id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        TBaseVrcards card = commonService.get(TBaseVrcards.class, id);

        model.addAttribute("provinceList", commonService.loadAll(TBaseProvince.class));
        model.addAttribute("t_d_card", card);

        List<CmsUser> users = cmsUserMng.getAdminList(site.getId(), null, null, null);
        model.addAttribute("users", users);

        List<CmsGroup> groupList = cmsGroupMng.getList();
        model.addAttribute("groupList", groupList);

        return "wdedu/card/edit";
    }

    @RequiresPermissions("univ:o_update")
    @RequestMapping("/card/o_update.do")
    public String o_update(TBaseVrcards old, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        TBaseVrcards new_card = commonService.get(TBaseVrcards.class, old.getCardNo());

        MyBeanUtils.copyBeanNotNull2Bean(old, new_card);
        if (!new_card.getActiveStatus())// 如果设置未激活，清空激活时间
        {
            new_card.setActiveTime(null);
            new_card.setBndUserId(null);
        }
        commonService.saveOrUpdate(new_card);
        model.addAttribute("message", "global.success");
        LOG.info("update CmsSite success. id={}", site.getId());
        //cmsLogMng.operating(request, "card.log.updateCard", user.getUsername() + "修改卡信息资料");
        return "redirect:v_list.do";
    }

    @RequiresPermissions("card:v_add")
    @RequestMapping(value = "/card/v_add.do")
    public String v_edit(Long begin, Long end, Integer groupId, Boolean random, String password, Integer channelUserId,
            HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        // 渠道列表
        List<CmsUser> users = cmsUserMng.getAdminList(site.getId(), null, null, null);
        model.addAttribute("users", users);

        List<CmsGroup> groupList = cmsGroupMng.getList();
        model.addAttribute("groupList", groupList);

        return "wdedu/card/add";
    }

    @RequiresPermissions("card:o_batchsave")
    @RequestMapping(value = "/card/o_batchsave")
    public String o_batchsave(Long begin, Long end, Integer groupId, Boolean random, String password, Integer channelUserId,
            HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        CmsUser user = CmsUtils.getUser(request);
        // 渠道列表
        List<CmsUser> users = cmsUserMng.getAdminList(site.getId(), null, null, null);
        model.addAttribute("users", users);

        if (begin != null && end != null) {
            Finder f = Finder.create("from TBaseVrcards where cardNo between :begin and :end ");
            f.setParam("begin", begin);
            f.setParam("end", end);
            List cards = commonService.find(f);
            if (cards != null && cards.size() > 0) {
                model.addAttribute("message", "卡号有重复");
                return "wdedu/card/add";
            }
            List<TBaseVrcards> list = new ArrayList<TBaseVrcards>();
            for (long i = begin; i <= end; i++) {
                TBaseVrcards card = new TBaseVrcards();
                card.setCardNo(i);
                card.setActiveStatus(false);
                card.setGroupId(groupId);
                card.setOptUserId(user.getId());
                card.setChnUserId(channelUserId);
                if (random) {
                    card.setPassword(PasswordUtil.randomPassword());
                } else {
                    card.setPassword(password);
                }
                card.setCreateTime(new Date());
                card.setInOutStorage(0);// 设置已存在入库状态
                list.add(card);
            }
            commonService.batchSaveOrUpdate(list);
            model.addAttribute("message", "global.success");
        }

        return "wdedu/card/add";
    }

    @RequiresPermissions("card:o_delete")
    @RequestMapping("/card/o_delete.do")
    public String o_del(Long[] ids, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        CmsSite site = CmsUtils.getSite(request);
        for (Long id : ids) {
            commonService.deleteEntityById(TBaseVrcards.class, id);
        }
        model.addAttribute("message", "global.success");
        //cmsLogMng.operating(request, "删除卡信息", "删除卡信息");
        return "redirect:v_list.do";
    }

    /*
     * 出库
     */
    @RequiresPermissions("card:v_out")
    @RequestMapping("/card/v_out.do")
    public String v_out(Long[] ids, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        CmsSite site = CmsUtils.getSite(request);

        if (ids == null) {
            return "wdedu/card/out";
        }

        String idstr = "";
        for (Long s : ids) {
            idstr += s.toString() + ",";
        }

        Finder f = Finder.create("from TBaseVrcards bean where bean.cardNo in(:ids) and bean.activeStatus=0 and bean.inOutStorage=0");
        f.setParamList("ids", ids);
        long count = 0;
        List list = commonService.find(f);
        if (list != null) {
            count = list.size();
        }
        String msg = "这次出库的卡数量为：" + count;
        if (count < ids.length) {
            msg += "其中剔除已激活和已出库的卡数量为：" + String.valueOf(ids.length - count);
        }
        model.addAttribute("provinceList", commonService.loadAll(TBaseProvince.class));
        model.addAttribute("idstr", idstr);
        model.addAttribute("message", msg);
        //model.addAttribute("t_d_card", list.get(0));
        // cmsLogMng.operatingInfo(request, "", "删除卡信息");
        return "wdedu/card/out";
    }

    /*
     * 出库
     */
    @RequiresPermissions("card:o_out")
    @RequestMapping("/card/o_out.do")
    public String o_out(String idstr, Integer province_id, Integer group_id, Integer outchannel, Integer fee, Date end, String remark,
            HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);

        Calendar now = Calendar.getInstance();
        Integer curYear = now.get(Calendar.YEAR);
        Integer curMonth = now.get(Calendar.MONTH);

        Calendar endDate = Calendar.getInstance();
        if (curMonth < 7) {
            endDate.set(curYear, 7, 31);
        } else {
            curYear += 1;
        }
            endDate.set(curYear, 7, 31);

        List<Long> ids = new ArrayList<Long>();
        for (String s : idstr.split(",")) {
            ids.add(Long.valueOf(s));
        }

        Calendar end_input = Calendar.getInstance();
        end_input.setTime(end);
        int end_input_year = end_input.get(Calendar.YEAR);
        int end_input_month = end_input.get(Calendar.MONTH);

        if (group_id == 2) {
            // 8-12月份，开卡到来年只能开到6月份，
            if (curMonth >= 7 && curMonth <= 11) {
                if (end_input_year > curYear && end_input_month > 5) {
                    model.addAttribute("message", "对不起，你输入的体验月份超出允许范围");
                    model.addAttribute("provinceList", commonService.loadAll(TBaseProvince.class));
                    model.addAttribute("idstr", idstr);
                    return "wdedu/card/out";
                }
            } else // 1-8月份
            {
                if (end_input_year > curYear || end_input_month > 5) {
                    model.addAttribute("message", "对不起，你输入的体验月份超出允许范围");
                    model.addAttribute("provinceList", commonService.loadAll(TBaseProvince.class));
                    model.addAttribute("idstr", idstr);
                    return "wdedu/card/out";
                }
            }

        }
        Finder f = Finder.create("from TBaseVrcards bean where bean.cardNo in(:ids) and bean.activeStatus=0 and bean.inOutStorage=0");
        f.setParamList("ids", ids.toArray());

        List<TBaseVrcards> list = commonService.find(f);
        for (TBaseVrcards card : list) {
            card.setInOutStorage(1);// 出库状态
            card.setChnUserId(user.getId());
            if (group_id == 3) // 无忧卡
            {
                card.setLosedTime(endDate.getTime());
            } else if (group_id == 2) {
                card.setLosedTime(end); // 体验卡
            }
            card.setRemark(remark);
            card.setProvinceId(province_id);
            card.setGroupId(group_id);
            card.setOutchannel(outchannel);
            card.setCardFee(fee);
            commonService.saveOrUpdate(card);
        }

        model.addAttribute("message", "出库成功");
        //cmsLogMng.operating(request, "卡出库日志", "卡出库" + idstr);

        return "redirect:v_list.do";
    }

    /*
     * 入库
     */
    @RequiresPermissions("card:o_in")
    @RequestMapping("/card/o_in.do")
    public String o_in(Long[] ids, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);

        Finder f = Finder.create("from TBaseVrcards bean where bean.cardNo in(:ids) and bean.activeStatus=0");
        f.setParamList("ids", ids);

        List<TBaseVrcards> list = commonService.find(f);
        for (TBaseVrcards card : list) {
            card.setInOutStorage(0);// 入库状态
            card.setChnUserId(null);
            card.setLosedTime(null); // 体验卡
            card.setProvinceId(null);
            card.setRemark(null);
            card.setGroupId(1);
            card.setOutchannel(null);
            card.setCardFee(null);
            commonService.saveOrUpdate(card);
        }

        model.addAttribute("message", "入库成功");

        //cmsLogMng.operating(request, "卡入库日志", "卡入库");
        return "redirect:v_list.do";
    }

    @RequiresPermissions("card:o_excelExport")
    @RequestMapping("/card/o_excelExport.do")
    public void excelExport(Long from, Long to, Integer provinceId, Integer groupId, Boolean active, Date activeBegin,
            Date activeEnd, Date inactiveBegin, Date inactiveEnd, HttpServletRequest request, HttpServletResponse response,
            ModelMap model) throws Exception {

        Finder f = Finder.create("select distinct bean from TBaseVrcards bean ");
        f.append(" where 1=1 ");
        if (from != null) {
            f.append(" and bean.cardNo between :from and :to ");
            f.setParam("from", from);
            f.setParam("to", to);
        }
        request.getSession().setAttribute("from", from);
        request.getSession().setAttribute("to", to);

        if (groupId != null) {
            f.append(" and bean.groupId = :groupId");
            f.setParam("groupId", groupId);
        }
        if (active != null) {
            f.append(" and bean.activeStatus = :active");
            f.setParam("active", active);
        }

        if (activeBegin != null && activeEnd != null) {
            f.append(" and bean.activeTime > :active_begin");
            f.setParam("active_begin", activeBegin);
            f.append(" and bean.activeTime < :active_end");
            f.setParam("active_end", activeEnd);
        }
        if (inactiveBegin != null && inactiveEnd != null) {
            f.append(" and bean.losedTime > :inactive_begin");
            f.setParam("inactive_begin", inactiveBegin);
            f.append(" and bean.losedTime < :inactive_end");
            f.setParam("inactive_end", inactiveEnd);
        }

        request.getSession().setAttribute("provinceId", provinceId);
        if (provinceId != null) {
            f.append(" and bean.provinceId=:provinceId");
            f.setParam("provinceId", provinceId);
        }

        // 生成提示信息，
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "卡号信息";
            // 根据浏览器进行转码，使其支持中文文件名
            String browse = BrowserUtils.checkBrowse(request);
            if ("MSIE".equalsIgnoreCase(browse.substring(0, 4))) {
                response.setHeader("content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(codedFileName, "UTF-8")
                        + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
                response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
            }
            // 进行转码，使其支持中文文件名
            // 产生工作簿对象
            HSSFWorkbook workbook = null;

            List<TBaseVrcards> cards = commonService.find(f);
            workbook = ExcelExportUtil.exportExcel("导出信息", TBaseVrcards.class, cards);
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (UnsupportedEncodingException e1) {

        } catch (Exception e) {

        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {

            }
        }
    }

    @Autowired
    private CmsGroupMng cmsGroupMng;
    @Autowired
    private CmsLogMng cmsLogMng;
    @Autowired
    private CmsUserMng cmsUserMng;
    @Autowired
    private CommonSvc commonService;

}
