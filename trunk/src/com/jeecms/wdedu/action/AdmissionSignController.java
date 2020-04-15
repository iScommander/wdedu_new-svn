/*
 * Copyright (C), 2017-2018 志愿无忧 FileName: AdmissionSignController.java Author: panglv Date:
 * 2018年1月8日 下午4:20:15 Description: //模块目的、功能描述 History: //修改记录 <author> <time> <version> <desc>
 * 修改人姓名 修改时间 版本号 描述
 */
package com.jeecms.wdedu.action;

import com.alibaba.fastjson.JSON;
import com.bcloud.msg.http.HttpSender;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.core.web.util.JsonWriteUtil;
import com.jeecms.wdedu.entity.*;
import com.jeecms.wdedu.service.CommonSvc;
import com.jeecms.wdedu.service.impl.CommonsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//import com.alipay.api.domain.AlipayTradeWapPayModel;
//import com.zywy.entity.sign.*;
//import com.zywy.entity.t_d_msg_mq;
//import com.zywy.entity.t_d_msg_template;
//import com.zywy.entity.t_s_online_order;
//import com.zywy.entity.t_d_product;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author panglv
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
public class AdmissionSignController {
    private static final Logger log = LoggerFactory.getLogger(AdmissionSignController.class);

    DateFormat format = new SimpleDateFormat("MM月dd日 HH时mm分");
    SimpleDateFormat simformat = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");

    /**
     * 首页 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/sign/index.jspx")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        return FrontUtils.getTplPath(site.getSolutionPath(), "sign", "jzqd-index");
    }

    /**
     * 功能描述: <br>
     * 〈查看所有活动〉
     *
     * @param request
     * @param response
     * @param model
     * @param proname
     * @param cityname
     * @param quxianname
     * @return
     * @throws ParseException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/sign/findActive.jspx", method = RequestMethod.GET)
    public String findActive_get(HttpServletRequest request, HttpServletResponse response,
                                 ModelMap model, String proname, String cityname, String quxianname)
            throws ParseException {
        return findActive(request, response, model, proname, cityname, quxianname);
    }


    @RequestMapping(value = "/sign/findActive.jspx", method = RequestMethod.POST)
    public String findActive_post(HttpServletRequest request, HttpServletResponse response,
                                  ModelMap model, String proname, String cityname, String quxianname)
            throws ParseException {
        return findActive(request, response, model, proname, cityname, quxianname);
    }


    public String findActive(HttpServletRequest request, HttpServletResponse response,
                             ModelMap model, String proname, String cityname, String quxianname)
            throws ParseException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        StringBuilder sb = new StringBuilder();
        StringBuilder procity = new StringBuilder();
        sb.append("SELECT * FROM t_sc_active_detail WHERE 1=1 ");
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
        sb.append(" AND '" + simformat.format(new Date()) + "'< active_end_time ");
        sb.append(" AND ispublish = 1 ");
        sb.append(" ORDER BY active_time desc");
        List<Map<String, Object>> activeList = commonService.findForJdbc(sb.toString());
        if (procity != null && procity.toString().length() != 0) {
            model.addAttribute("procity", procity.toString());
        }
        model.addAttribute("activeList", activeList);
        return FrontUtils.getTplPath(site.getSolutionPath(), "sign", "jzhd");

    }

    /**
     * 功能描述: <br>
     * 〈活动详情〉
     *
     * @param request
     * @param response
     * @param model
     * @param id
     * @return
     * @throws UnsupportedEncodingException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     * // Alltodo: 2019/11/11 对接讲座详情新页面及跳转
     */
    @RequestMapping(value = "/sign/activedetail.jspx", method = RequestMethod.GET)
    public String activedetail(HttpServletRequest request, HttpServletResponse response,
                               ModelMap model, Integer id, Integer activeid) throws UnsupportedEncodingException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        TScActiveDetail activeDetail = commonService.findUniqueByProperty(TScActiveDetail.class, "id", id);
        String dateStr = format.format(activeDetail.getActiveTime());
        BigInteger bigInt = new BigInteger("0");
        if (activeDetail.getFree() != null) {
            BigInteger bigIn = new BigInteger(activeDetail.getFree().toString());
            Double fee1 = Double.valueOf(bigIn.subtract(bigInt).doubleValue() / 100);
            model.addAttribute("free", fee1);
        }

        //获取老师信息
        TScActiveTeacher activeTeacher1 = commonService.findUniqueByProperty(TScActiveTeacher.class, "teacherId", activeDetail.getTeacher1Id());
        if (activeTeacher1 != null && activeTeacher1.getIntroduce4() != null) {
            activeTeacher1.setIntroduce4(activeTeacher1.getIntroduce4());
        }

        model.addAttribute("activeTeacher1", activeTeacher1);
        TScActiveTeacher activeTeacher2 = commonService.findUniqueByProperty(TScActiveTeacher.class, "teacherId", activeDetail.getTeacher2Id());
        if (activeTeacher2 != null && activeTeacher2.getIntroduce4() != null) {
            activeTeacher2.setIntroduce4(activeTeacher2.getIntroduce4());
        }

        model.addAttribute("activeTeacher2", activeTeacher2);

        model.addAttribute("activetime", dateStr);
        model.addAttribute("activeDetail", activeDetail);
        model.addAttribute("activeid", activeid);

        return FrontUtils.getTplPath(site.getSolutionPath(), "sign", "details");
    }

    public static String blobToString(Blob b) throws UnsupportedEncodingException {
        String result = "";
        if (b == null) {
            return result;
        }
        try {

            result = new String(b.getBytes((long) 1, (int) b.length()), "UTF-8");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }


    /**
     * 功能描述: <br>
     * 〈获取所有区县学校信息〉
     *
     * @param request
     * @param response
     * @param model
     * @param cityname
     * @throws IOException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/sign/getquxian.jspx")
    public void getquxian(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                          String cityname) throws IOException {

        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取省list
        List<Map<String, Object>> prolist =
                commonService
                        .findForJdbc("SELECT pro_code AS id  ,pro_name AS value  FROM t_xueji_sign where pro_id IN (1,2,4,5,9,10,11,17,18,20,25,26,32,33) GROUP BY pro_code ORDER BY pro_code");
        map.put("prolist", prolist);
        // 获取市list
        List<Map<String, Object>> citylist =
                commonService
                        .findForJdbc("SELECT city_code AS id ,city_name AS value, pro_code AS parentId FROM t_xueji_sign where pro_id IN (1,2,4,5,9,10,11,17,18,20,25,26,32,33)  GROUP BY city_code");
        map.put("citylist", citylist);
        // 获取区list
        List<Map<String, Object>> quxianlist =
                commonService
                        .findForJdbc("SELECT quxian_code AS id ,quxian_name AS value, city_code AS parentId FROM t_xueji_sign where  pro_id IN (1,2,4,5,9,10,11,17,18,20,25,26,32,33) GROUP BY quxian_code");
        for (Map<String, Object> quxianmap : citylist) {
            Map<String, Object> qxmap = new HashMap<String, Object>();
            qxmap.put("id", "0");
            qxmap.put("value", "全部");
            qxmap.put("parentId", quxianmap.get("id").toString());
            quxianlist.add(qxmap);
        }
        map.put("quxianlist", quxianlist);
        JsonWriteUtil.write(response, map);
    }

    @RequestMapping(value = "/sign/getschool.jspx")
    public void getschool(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                          String quxiancode) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取学校list
        List<Map<String, Object>> schoollist =
                commonService.findForJdbc("SELECT school_code as id,school_name AS value, quxian_code AS parentId FROM t_xueji_sign WHERE quxian_code='" + quxiancode + "' AND pro_id IN (1,2,4,5,9,10,11,17,18,20,25,26,32,33) GROUP BY school_code");
        map.put("schoollist", schoollist);
        JsonWriteUtil.write(response, map);
    }

    /**
     * 功能描述: <br>
     * 〈获取邀请卷界面〉
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/sign/ticketindex.jspx")
    public String ticketindex(HttpServletRequest request, HttpServletResponse response,
                              ModelMap model, Integer active_Id) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        TScActiveDetail active = commonService.findUniqueByProperty(TScActiveDetail.class, "activeId", active_Id);
        //剩余席位
        Integer people = 0;
        if (active != null && active.getPeople() != null) {
            String sql = "SELECT SUM(peonum) AS peonum FROM t_sc_active_sign WHERE active_Id='" + active_Id + "' AND ruchangquan_type='1'";
            Map<String, Object> map = commonService.findOneForJdbc(sql);
            Integer nums = 0;
            if (map.get("peonum") != null) {
                nums = Integer.parseInt(map.get("peonum").toString());
            }
            people = active.getPeople() - nums;
        }

        int initialNumber = active.getInitialNumber();
        //报名金额
        Double fee1 = new Double("0");
        if (active.getFree() != null) {
            BigInteger bigInt = new BigInteger("0");
            BigInteger bigIn = new BigInteger(active.getFree().toString());
            fee1 = Double.valueOf(bigIn.subtract(bigInt).doubleValue() / 100);
        }
        model.addAttribute("people", people);
        model.addAttribute("free", fee1);
        model.addAttribute("activeId", active_Id);
        model.addAttribute("isShowKind", active.getIsShowKind());

        return FrontUtils.getTplPath(site.getSolutionPath(), "sign", "lqrcq");

    }

    /**
     * 获取邀请卷 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @param response
     * @param model
     * @param name         //     * @param username
     * @param city
     * @param county
     * @param schoolName
     * @param grade
     * @param majorType
     * @param class_rank
     * @param peonum
     * @param captchaInput
     * @throws IOException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/sign/getAdmissionticket.jspx", method = RequestMethod.POST)
    public void getAdmissionticket(HttpServletRequest request, HttpServletResponse response,
                                   ModelMap model, String name, String telphoto, String pro, String city, String county,
                                   String schoolName, String grade, String majorType, String class_rank, String peonum,
                                   String captchaInput, Integer activeId, Boolean ifzxd) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);

        // 邀请码/交易流水号
//        String outTradeNo = WxpayUtil.getNonceStr();
        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(captchaInput)) {
            // 验证码
            Serializable session_captcha = session.getAttribute(request, "captcha");
            if (session_captcha == null) {
                map.put("success", false);
                map.put("msg", "亲，你忘了获取验证码啦！");
                JsonWriteUtil.write(response, map);
                return;
            }
            Serializable username_s = session.getAttribute(request, "username");
            if (!captchaInput.equals(session_captcha.toString())
                    || !telphoto.equals(username_s.toString())) {
                map.put("success", false);
                map.put("msg", "验证码输入不正确！");
                JsonWriteUtil.write(response, map);
                return;
            }
        }
        //获取此人报名名次
        Map<String, Object> qindaonummap = commonService.findOneForJdbc("SELECT COUNT(*) AS COUNT FROM t_sc_active_sign WHERE active_Id=" + activeId);
        Integer bmnum = Integer.parseInt(qindaonummap.get("count").toString()) + 1;

        List<TScActiveDetail> tScActiveDetail = commonService.findByProperty(TScActiveDetail.class, "activeId", activeId);

        //先保存报名信息
        Map<String, Object> activemap = this.commonService.findOneForJdbc("SELECT * FROM t_sc_active_sign WHERE active_Id=" + activeId + "  AND telephone='" + telphoto + "'");

        TScActiveSign admissionTicket = saveAdmissionTicket(name, telphoto, pro, city, county, schoolName, grade, majorType, class_rank, peonum);
        admissionTicket.setActiveId(activeId);
        admissionTicket.setQiandaoType("0");
        admissionTicket.setRuchangquanType("0");
        admissionTicket.setBranchCompany(tScActiveDetail.get(0).getBranchCompany());
        admissionTicket.setLingquanTime(this.simformat.format(new Date()));
//        admissionTicket.setRuchangquanCode(outTradeNo);
        if (activemap == null) {
            admissionTicket.setBmRank(bmnum);//报名排名
            admissionTicket.setQdRank(bmnum);
        } else {
            admissionTicket.setBmRank(bmnum - 1);
            admissionTicket.setQdRank(bmnum - 1);
        }

        if (activemap != null) {
            admissionTicket.setId(Integer.parseInt(activemap.get("id").toString()));
        }
        if (ifzxd == true) {
            admissionTicket.setIfzxd(2);
        } else {
            admissionTicket.setIfzxd(8);
        }

        this.commonService.saveOrUpdate(admissionTicket);


        //更新用户表
        TScActiveUserInfo activeUserArttr = (TScActiveUserInfo) this.commonService.findUniqueByProperty(TScActiveUserInfo.class, "telephone", telphoto);

        TScActiveUserInfo newactiveUserArttr = saveActiveUserArttr(name, telphoto, pro, city, county, schoolName, grade, majorType, class_rank);
        if (activeUserArttr != null) {
            newactiveUserArttr.setId(activeUserArttr.getId());

            Integer bnum = 1;
            if (activeUserArttr.getBmActiveNum() != null) {
                bnum = activeUserArttr.getBmActiveNum() + 1;
            }

            newactiveUserArttr.setBmActiveNum(bnum);
            Integer qdnum = 1;
            if (activeUserArttr.getQdActiveNum() != null) {
                qdnum = activeUserArttr.getQdActiveNum() + 1;
            }
            newactiveUserArttr.setQdActiveNum(qdnum);
        } else {
            newactiveUserArttr.setBmActiveNum(1);
            newactiveUserArttr.setQdActiveNum(0);
        }
        this.commonService.saveOrUpdate(newactiveUserArttr);


        //判断我是否需要付费
        TScActiveDetail activeDetail = commonService.findUniqueByProperty(TScActiveDetail.class, "activeId", activeId);
        int initialNumber = activeDetail.getInitialNumber();
        int lastBmRank = initialNumber + admissionTicket.getBmRank();
        if (activeDetail.getFree() == 0 || activeDetail.getFree() == null) {
            String sql = "UPDATE t_sc_active_sign SET ruchangquan_type = '1' ,ruchangquan_code = '免费活动' ";
            if (ifzxd != null) {
                sql += ", ifzxd=" + (ifzxd ? 1 : 0);
            }
            sql += " WHERE   id = '" + admissionTicket.getId() + "'";
            commonService.executeSql(sql);
            String realPath = request.getSession().getServletContext().getRealPath("/WebContent/r/sign/img/tips-rcq.png");
            String form = "<table align='center'> <br>    <br>    <br>    <br>    <br>    <br>    <br>    <br>            <div ><img src='" + realPath + "'></div>           <div >              <h4>                    尊敬的<span>" + newactiveUserArttr.getUsername() + "</span>家长:               </h4>               <p>请您准时参加《" + activeDetail.getTheme() + "》," + activeDetail.getLqzlContent() + "。</p>              <div>                   您是第<span>" + lastBmRank + "</span>位领取入场券的家长！             </div>              <div>讲座时间:" + simformat.format(activeDetail.getActiveStartTime()).substring(0, 15) + "</div>              <div>讲座地点:" + activeDetail.getAddress() + "</div>              <h4>请截图并保存当前页面！</h4>            </div>  </table>";
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(form);// 直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();
        } else {
            BigInteger bigInt = new BigInteger("0");
            BigInteger bigIn = new BigInteger(activeDetail.getFree().toString());
            Double fee1 = Double.valueOf(bigIn.subtract(bigInt).doubleValue() / 100);
            // 保存
            TYnOnlineOrder order = new TYnOnlineOrder();
//                  order.setCftSeq(outTradeNo);
            order.setCreateDate((Timestamp) new Date());
            long fee2 = (long) (fee1 * 100);
//                  order.setFee(BigInteger.valueOf(fee2));
            order.setPayState(0);
            order.setPhonenumber(telphoto);
            order.setId(6);
            order.setBuyname(name);
            order.setRemark("邀请卷");
            order.setChannel("支付宝");
            commonService.saveOrUpdate(order);

            // 创建支付宝支付请求
            Map<String, Object> mapo =
                    this.commonService
                            .findOneForJdbc("SELECT * FROM t_d_product WHERE TYPE=3 AND PROVINCE_id=" + activeDetail.getProId());
            Integer id = Integer.parseInt(mapo.get("id").toString());
            TBaseProducts product = (TBaseProducts) this.commonService.getEntity(TBaseProducts.class, id);

            //订单名称
            String subject = product.getName();
            // 付款金额，必填
            String total_amount = fee1.toString();
            // 商品描述，可空
            String body = product.getName();
            // 超时时间 可空
            String timeout_express = "2m";
            // 销售产品码 必填
            String product_code = "QUICK_WAP_WAY";

            String appid = product.getZfbKey();

//                  String privateKey=AlipayConfig.RSA_PRIVATE_KEY;//MyBeanUtils.blobToString(product.getZfb_private_key());
//
//                  String publicKey=AlipayConfig.ALIPAY_PUBLIC_KEY;//MyBeanUtils.blobToString(product.getZfb_public_key());
//                  /**********************/
//                  // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
//                  // 调用RSA签名方式
//                  AlipayClient client =
//                          new DefaultAlipayClient(AlipayConfig.URL, appid,
//                                  privateKey, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
//                                  publicKey, AlipayConfig.SIGNTYPE);
//                  AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();

            // 封装请求支付信息
//                  AlipayTradeWapPayModel paymodel = new AlipayTradeWapPayModel();
//                  paymodel.setOutTradeNo(outTradeNo);
//                  paymodel.setSubject(subject);
//                  paymodel.setTotalAmount(total_amount);
//                  paymodel.setBody(body);
//                  paymodel.setTimeoutExpress(timeout_express);
//                  paymodel.setProductCode(product_code);
//                  alipay_request.setBizModel(paymodel);
//                  // 设置异步通知地址
//                  alipay_request.setNotifyUrl(product.getZfbNotifyUrl());
            // 设置同步地址
//                  alipay_request.setReturnUrl(AlipayConfig.return_url);

            // form表单生产
            String form = "";
//                  try {
//                      // 调用SDK生成表单
//                      form = client.pageExecute(alipay_request).getBody();
//                      System.out.println(form);
//                      response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
//                      response.getWriter().write(form);// 直接将完整的表单html输出到页面
//                      response.getWriter().flush();
//                      response.getWriter().close();
//                  } catch (AlipayApiException e) {

//                      e.printStackTrace();
//                  }
        }


    }


    @RequestMapping(value = "/alipay/callback.jspx", method = {RequestMethod.POST, RequestMethod.GET})
    public void notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("进入回调函数"); //请不要修改或删除
        log.info("****************进入回调函数*********************");
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号

        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        log.info("****************out_trade_no:" + out_trade_no + "*********************");
        //支付宝交易号
        //String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
        log.info("****************trade_status:" + trade_status + "*********************");
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
//            boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET);

//            if(verify_result){//验证成功
//                log.info("***************验证成功*********************");

        if ("TRADE_FINISHED".equals(trade_status) || "TRADE_SUCCESS".equals(trade_status)) {
            TScActiveSign admissionTicket =
                    commonService.findUniqueByProperty(TScActiveSign.class, "ruchangquan_code",
                            out_trade_no);
            String sql =
                    "UPDATE t_sc_active_sign SET ruchangquan_type = '1'WHERE id ="
                            + admissionTicket.getId();
            commonService.executeSql(sql);
            // 修改订单记录
            Finder ff = Finder.create("from t_s_online_order where cft_seq=:cft_seq");
            ff.setParam("cft_seq", out_trade_no);
            TYnOnlineOrder order = commonService.getOneResult(ff);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datestr = sdf.format(new Date());
            String ordersql =
                    "UPDATE t_s_online_order SET pay_state = 1 ,   pay_date = '" + datestr
                            + "' WHERE id =" + order.getId();
            commonService.executeSql(ordersql);
            // 修改用户报名次数信息
            TScActiveUserInfo activeUserArttr =
                    commonService.findUniqueByProperty(TScActiveUserInfo.class, "telephone",
                            admissionTicket.getTelephone());
            // 如果用户表有数据进行数据更改
            Integer num = activeUserArttr.getBmActiveNum() + 1;
            String updatesql =
                    "UPDATE t_sc_active_user_info SET bm_active_num = '" + num + "' WHERE id = '"
                            + activeUserArttr.getId() + "' ";
            commonService.executeSql(updatesql);

            // 给支付成功人员发送成功消息
            TScActiveDetail activeDetail =
                    commonService.findUniqueByProperty(TScActiveDetail.class, "activeId",
                            admissionTicket.getActiveId());
            TDMsgTemplate template = commonService.get(TDMsgTemplate.class, 10);
            String content = "";
            try {
                content =
                        String.format(template.getContent(), simformat.format(activeDetail.getActiveTime()),
                                activeDetail.getAddress());
            } catch (Exception e) {
                content =
                        String.format(template.getContent(), activeDetail.getActiveTime(),
                                activeDetail.getAddress());
            }
            // 发送短信
            TDMsgMq msg = new TDMsgMq();
            msg.setContent(content);
            msg.setCreateDate((Timestamp) new Date());
            msg.setPhone(order.getPhonenumber());
            msg.setType(3);
            msg.setSendState(false);
            commonService.saveOrUpdate(msg);
        }


        //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
        System.out.println("success"); //请不要修改或删除

        //////////////////////////////////////////////////////////////////////////////////////////
//            }else{//验证失败
//                log.info("***************验证*********************");
//            }
    }


    @RequestMapping(value = "/alipay/returnurl.jspx", method = RequestMethod.GET)
    public void returnurl(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号

        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");


        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
//        boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET);

//        if(verify_result){//验证成功
        Map<String, Object> map = commonService.findOneForJdbc("SELECT active_Id ,name,bm_rank FROM t_sc_active_sign WHERE ruchangquan_code=" + out_trade_no);
        if (map.get("activeId") != null) {
            TScActiveDetail activeDetail = commonService
                    .findUniqueByProperty(TScActiveDetail.class, "activeId", Integer.parseInt(map.get("activeId").toString()));

            String form = "<table align='center'> <br>    <br>    <br>    <br>    <br>    <br>    <br>    <br>            <div ><img src='${base}/r/sign/img/tips-rcq.png'></div>           <div >              <h4>                    尊敬的<span>" + map.get("name") + "</span>家长:               </h4>               <p>请您准时参加《" + activeDetail.getTheme() + "》," + activeDetail.getInfoPaySuccess() + "。</p>              <div>                   您是第<span>" + map.get("bm_rank") + "</span>位领取入场券的家长！             </div>              <div>讲座时间:" + simformat.format(activeDetail.getActiveTime()).substring(0, 15) + "</div>              <div>讲座地点:" + activeDetail.getAddress() + "</div>              <h4>请截图并保存当前页面！</h4>            </div>  </table>";
//                response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
            response.getWriter().write(form);// 直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();


        }
    }
//    }


    /**
     *
     * 功能描述: <br>
     * 〈微信回调函数〉
     *
     * @param request
     * @param response
     * @throws Exception
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)

     @RequestMapping(value = "/ticketwxpayCallback.jspx")
     public void notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
     // 把如下代码贴到的你的处理回调的servlet 或者.do 中即可明白回调操作
     System.out.print("微信支付回调数据开始");
     // 示例报文1653006252
     // String xml =
     // "<xml><appid><![CDATA[wxb4dc385f953b356e]]></appid><bank_type><![CDATA[CCB_CREDIT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1228442802]]></mch_id><nonce_str><![CDATA[1002477130]]></nonce_str><openid><![CDATA[o-HREuJzRr3moMvv990VdfnQ8x4k]]></openid><out_trade_no><![CDATA[1653006252]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[1269E03E43F2B8C388A414EDAE185CEE]]></sign><time_end><![CDATA[20150324100405]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1009530574201503240036299496]]></transaction_id></xml>";
     String inputLine;
     String notityXml = "";
     try {
     while ((inputLine = request.getReader().readLine()) != null) {
     notityXml += inputLine;
     }
     request.getReader().close();
     } catch (Exception e) {
     e.printStackTrace();
     }

     System.out.println("接收到的报文：" + notityXml);

     Map<String, String> m = parseXmlToList2(notityXml);
     WxPayResult wpr = new WxPayResult();
     wpr.setAppid(m.get("appid").toString());
     wpr.setBankType(m.get("bank_type").toString());
     wpr.setCashFee(m.get("cash_fee").toString());
     wpr.setFeeType(m.get("fee_type").toString());
     wpr.setIsSubscribe(m.get("is_subscribe").toString());
     wpr.setMchId(m.get("mch_id").toString());
     wpr.setNonceStr(m.get("nonce_str").toString());
     wpr.setOpenid(m.get("openid").toString());
     wpr.setOutTradeNo(m.get("out_trade_no").toString());
     wpr.setResultCode(m.get("result_code").toString());
     wpr.setReturnCode(m.get("return_code").toString());
     wpr.setSign(m.get("sign").toString());
     wpr.setTimeEnd(m.get("time_end").toString());
     wpr.setTotalFee(m.get("total_fee").toString());
     wpr.setTradeType(m.get("trade_type").toString());
     wpr.setTransactionId(m.get("transaction_id").toString());

     System.out.println("支付结果:" + wpr.getResultCode());

     Map<String, Object> map = new HashMap<String, Object>();
     if ("SUCCESS".equals(wpr.getResultCode())) {
     // 如果交钱成功,修改报名状态
     AdmissionTicket admissionTicket =
     commonService.findUniqueByProperty(AdmissionTicket.class, "ruchangquan_code",
     wpr.getOutTradeNo());
     String sql =
     "UPDATE t_sc_active_sign SET ruchangquan_type = '1'WHERE id ="
     + admissionTicket.getId();
     commonService.executeSql(sql);
     // 修改订单记录
     Finder ff = Finder.create("from t_s_online_order where cft_seq=:cft_seq");
     ff.setParam("cft_seq", wpr.getOutTradeNo());
     t_s_online_order order = commonService.getOneResult(ff);
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String datestr = sdf.format(new Date());
     String ordersql =
     "UPDATE t_s_online_order SET pay_state = 1 ,   pay_date = '" + datestr
     + "' WHERE id =" + order.getId();
     commonService.executeSql(ordersql);
     // 修改用户报名次数信息
     ActiveUserArttr activeUserArttr =
     commonService.findUniqueByProperty(ActiveUserArttr.class, "telephone",
     admissionTicket.getTelephone());
     // 如果用户表有数据进行数据更改
     Integer num = activeUserArttr.getBm_active_num() + 1;
     String updatesql =
     "UPDATE t_sc_active_user_info SET bm_active_num = '" + num + "' WHERE id = '"
     + activeUserArttr.getId() + "' ";
     commonService.executeSql(updatesql);

     // 给支付成功人员发送成功消息
     ActiveDetail activeDetail =
     commonService.findUniqueByProperty(ActiveDetail.class, "active_Id",
     admissionTicket.getactive_Id());
     t_d_msg_template template = commonService.get(t_d_msg_template.class, 10);

     String content =
     String.format(template.getContent(), activeDetail.getActive_time(),
     activeDetail.getAddress());
     // 发送短信
     t_d_msg_mq msg = new t_d_msg_mq();
     msg.setContent(content);
     msg.setCreate_date(new Date());
     msg.setPhone(order.getPhone());
     msg.setType(3);
     msg.setSend_state(false);
     commonService.saveOrUpdate(msg);

     map.put("success", true);
     map.put("msg", "恭喜您预约成功!");
     } else {
     map.put("success", false);
     map.put("msg", "支付失败!");
     }

     JsonWriteUtil.write(response, map);
     }

     @SuppressWarnings({"rawtypes", "unchecked"})
     private static Map<String, String> parseXmlToList2(String xml) {
     Map<String, String> retMap = new HashMap();
     try {
     StringReader read = new StringReader(xml);
     // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
     InputSource source = new InputSource(read);
     // 创建一个新的SAXBuilder
     SAXBuilder sb = new SAXBuilder();
     // 通过输入源构造一个Document
     Document doc = (Document) sb.build(source);
     Element root = doc.getRootElement();// 指向根节点
     List<Element> es = root.getChildren();
     if (es != null && es.size() != 0) {
     for (Element element : es) {
     retMap.put(element.getName(), element.getValue());
     }
     }
     } catch (Exception e) {
     e.printStackTrace();
     }
     return retMap;
     }

     // 获取微信扫码支付二维码连接
     public String
     qrPay(String outTradeNo, String ip, String phone, String fee, t_d_product product) {
     WxPayDto tpWxPay = new WxPayDto();
     tpWxPay.setOrderId(outTradeNo);// 订单号
     tpWxPay.setTotalFee(fee);
     tpWxPay.setSpbillCreateIp(ip);
     tpWxPay.setNotifyUrl(product.getCft_notify_url());
     tpWxPay.setBody(product.getDesc());
     tpWxPay.setOpenId("");
     String attach = phone;
     tpWxPay.setAttach(attach);
     return WxpayUtil.getCodeurl(tpWxPay, product);
     }
     */


    /**
     * 功能描述: <br>
     * 〈签到界面〉
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/sign/signindex.jspx")
    public String signindex(HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer active_Id)
            throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        model.addAttribute("activeId", active_Id);
        TScActiveDetail active = null;
        try {
            active = commonService.findUniqueByProperty(TScActiveDetail.class, "activeId", active_Id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (active != null && active.getPeople() != null) {
            String sql = "SELECT SUM(peonum) AS peonum FROM t_sc_active_sign WHERE active_Id='" + active_Id + "' AND qiandao_type='1'";
            Map<String, Object> map = commonService.findOneForJdbc(sql);
            Integer nums = 0;
            if (map.get("peonum") != null) {
                nums = Integer.parseInt(map.get("peonum").toString());
            }
            model.addAttribute("people", active.getPeople() - nums);
            model.addAttribute("isShowKind", active.getIsShowKind());
        } else {
            model.addAttribute("people", 0);
        }
        return FrontUtils.getTplPath(site.getSolutionPath(), "sign", "jzqd");

    }


    /**
     * 功能描述: <br>
     * 〈进行签到〉
     *
     * @param request
     * @param response
     * @param model
     * @param name         //     * @param username
     * @param city
     * @param county
     * @param schoolName
     * @param grade
     * @param majorType
     * @param class_rank
     * @param peonum
     * @param captchaInput
     * @throws IOException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping(value = "/sign/signing.jspx", method = RequestMethod.POST)
    public void signing(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                        String name, String telphoto, String pro, String city, String county,
                        String schoolName, String grade, String majorType, String class_rank, String peonum,
                        String captchaInput, Integer activeId, String ifzxd) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();

        /**
         * 返回信息
         * 获取手机号时已判断是否已经签到，所以只判断注册是否成功
         * type            0                   1           
         * msg            没有验证码或验证码不正确              签到成功                    
         * activeinfo     null                 活动信息     
         * userinfo       null                只有用户姓名                          
         */
        if (!StringUtils.isEmpty(captchaInput)) {
            // 验证码
            Serializable session_captcha = session.getAttribute(request, "captcha");
            if (session_captcha == null) {
                map.put("type", 0);
                map.put("msg", "亲，你忘了获取验证码啦！");
                map.put("activeinfo", null);
                JsonWriteUtil.write(response, map);
            }
            Serializable username_s = session.getAttribute(request, "username");
            if (!captchaInput.equals(session_captcha.toString())
                    || !telphoto.equals(username_s.toString())) {
                map.put("type", 0);
                map.put("msg", "验证码输入不正确！");
                map.put("activeinfo", null);
                JsonWriteUtil.write(response, map);
            }
        }

        //判断是否为注册用户，如果是更新数据，不是新建用户信息
        TScActiveUserInfo activeUserArttr = commonService.findUniqueByProperty(TScActiveUserInfo.class, "telephone", telphoto);

        TScActiveUserInfo newActiveUserArttr = saveActiveUserArttr(name, telphoto, pro, city, county, schoolName, grade, majorType, class_rank);

        if (activeUserArttr != null) {
            newActiveUserArttr.setId(activeUserArttr.getId());
            Integer num = activeUserArttr.getQdActiveNum() + 1;
            newActiveUserArttr.setBmActiveNum(activeUserArttr.getBmActiveNum());
            newActiveUserArttr.setQdActiveNum(num);
        } else {
            newActiveUserArttr.setBmActiveNum(0);
            newActiveUserArttr.setQdActiveNum(1);
        }
        commonService.saveOrUpdate(newActiveUserArttr);

        //获取此时数据库签到人数
        Map<String, Object> qindaonummap = commonService.findOneForJdbc("SELECT COUNT(*) AS COUNT FROM t_sc_active_sign WHERE qiandao_type=1 AND  active_Id =" + activeId);
        Integer qindaonum = Integer.parseInt(qindaonummap.get("count").toString()) + 1;
        //判断是否为报名用户,是，更新数据,不是添加数据
        Map<String, Object> newMap = commonService.findOneForJdbc("SELECT * FROM t_sc_active_sign WHERE active_Id ='" + activeId + "' AND telephone='" + telphoto + "'");
        if (newMap != null) {
            String sql = "UPDATE t_sc_active_sign SET qiandao_type = '1' , qd_rank = '" + qindaonum + "' , qiandao_time = '" + simformat.format(new Date()) + "'  WHERE id =" + newMap.get("id");
            commonService.executeSql(sql);
        } else {
            TScActiveSign admissionTickets = saveAdmissionTicket(name, telphoto, pro, city, county, schoolName, grade, majorType, class_rank, peonum);
            admissionTickets.setActiveId(activeId);
            admissionTickets.setQiandaoType("1");
            admissionTickets.setQdRank(qindaonum);
            admissionTickets.setBmRank(qindaonum);
            admissionTickets.setRuchangquanType("0");
            if ("true".equals(ifzxd)) {
                admissionTickets.setIfzxd(2);
            } else {
                admissionTickets.setIfzxd(8);
            }
            Date date = new Date();
            admissionTickets.setQiandaoTime(date.toString());
            commonService.saveOrUpdate(admissionTickets);
        }

        //给前台返回活动信息
        Map<String, Object> activeinfo = commonService.findOneForJdbc("SELECT * FROM t_sc_active_detail WHERE active_Id=" + activeId);
        if (activeinfo.get("free") != null && Integer.parseInt(activeinfo.get("free").toString()) != 0) {
            activeinfo.put("lqzl_content", activeinfo.get("info_pay_success"));
        }
        map.put("type", 1);
        map.put("msg", "签到成功");
        activeinfo.put("qd_rank", qindaonum);
        activeinfo.put("username", newActiveUserArttr.getUsername());
        activeinfo.put("active_start_time", activeinfo.get("active_start_time").toString().substring(0, 15));
        map.put("activeinfo", activeinfo);
        JsonWriteUtil.write(response, map);

    }


    /**
     * 获取信息 *
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "sign/getCapt.jspx", method = RequestMethod.GET)
    public void getCaptcha(String telphoto, HttpServletRequest request,
                           HttpServletResponse response, ModelMap model, Integer activeid, String type)
            throws IOException {

        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        Map<String, Object> map = new HashMap<String, Object>();


        /**
         * 返回信息
         * type            0                  1               2
         * msg            此人为非注册用户                 此人已经报名或签到             此人为注册用户，但是没有报名，或者签到
         * activeinfo     null               活动信息                                   null
         * userinfo       null                只有用户姓名          用户所有信息
         */

        Map<String, Object> activemap =
                commonService.findOneForJdbc("SELECT * FROM t_sc_active_sign WHERE active_Id="
                        + activeid + "  AND telephone='" + telphoto + "'");

        /**
         * 判断是否是已经注册用户
         */
        Map<String, Object> userinfo = commonService.findOneForJdbc("SELECT  * FROM  t_sc_active_user_info WHERE Telephone =" + telphoto);
        if (userinfo == null) {
            Random random = new Random();
            String result = "";
            for (int i = 0; i < 6; i++) {
                result += random.nextInt(10);
            }

            String uri = "http://send.18sms.com/msg/HttpBatchSendSM";//应用地址
            String account = "173kbz ";//示远账号
            String pswd = "B9pL6iPk";//示远密码
            String mobile = request.getParameter("telphoto");//手机号码，多个号码使用","分割
            String content = "您好，您的验证码是：" + result;//短信内容，注意内容中的逗号请使用中文状态下的逗号
            content = new String(content.getBytes(), "UTF-8");
            boolean needstatus = true;//是否需要状态报告，需要true，不需要false
            String product = "";//产品ID(不用填写)
            String extno = "";//扩展码(不用填写)
            try {
                String returnString = HttpSender.batchSend(uri, account, pswd, mobile, content, needstatus, product, extno);
                System.out.println(returnString);
                //TODO 处理返回值,参见HTTP协议文档
            } catch (Exception e) {
                //TODO 处理异常
                e.printStackTrace();
            }
            session.setAttribute(request, response, "username", telphoto);
            session.setAttribute(request, response, "captcha", result);
            map.put("type", 0);
            map.put("msg", "此人为非注册用户！");
            map.put("activeinfo", activemap);
            //新用户 通过活动获取默认省市
            TScActiveDetail ts = null;
            try {
                ts = commonService.findUniqueByProperty(TScActiveDetail.class, "activeId", activeid);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Map info = JSON.parseObject(JSON.toJSONString(ts), Map.class);
            if (info != null) {
                map.put("userinfo", findcode(info));
            }
            JsonWriteUtil.write(response, map);
            System.out.println("验证码：" + result + "【沃得教育】");
            commons.sendsmsByTemplate(1, telphoto.toString(), result);
        } else {
            //活动信息

            Map<String, Object> activeinfo = commonService.findOneForJdbc("SELECT * FROM t_sc_active_detail WHERE active_Id=" + activeid);
            int initialNumber = Integer.parseInt(activeinfo.get("initial_number").toString());
            Map<String, Object> qindaonummap = commonService.findOneForJdbc("SELECT COUNT(*) AS COUNT FROM t_sc_active_sign WHERE active_Id=" + activeid);

            int LastBmRank = initialNumber;
            if (activemap == null) {
                Integer bmnum = Integer.parseInt(qindaonummap.get("count").toString()) + 1;
                LastBmRank = initialNumber + bmnum;
            } else {
                int Rank = 0;
                if ("qd".equals(type)) {
                    Rank = Integer.parseInt(activemap.get("qd_rank").toString());
                } else if ("bm".equals(type)) {
                    Rank = Integer.parseInt(activemap.get("bm_rank").toString());
                }
                LastBmRank = initialNumber + Rank;

            }


            //如果付费用户取info_pay_success字段，此为后加需求，所有为不改懂前台页面，在后台做判断
            if (activeinfo.get("free") != null && Integer.parseInt(activeinfo.get("free").toString()) != 0) {
                activeinfo.put("lqzl_content", activeinfo.get("info_pay_success"));
            }
            //Map<String, Object> activemap =
            //        commonService.findOneForJdbc("SELECT * FROM t_sc_active_sign WHERE active_Id="
            //                + activeid + "  AND telephone='" + telphoto + "'");
            //是否已经签到
            //判断此用户，是否存在于签到表，存在是否已经签到
            if (activemap != null && activemap.size() != 0 && "1".equals(activemap.get("qiandao_type")) && "qd".equals(type)) {
                // 已签到，返回活动信息
                map.put("type", 1);
                map.put("msg", "此人已经签到，返回活动信息");
                activeinfo.put("qd_rank", activemap.get("qd_rank"));
                activeinfo.put("username", userinfo.get("username"));
                activeinfo.put("active_start_time", activeinfo.get("active_start_time").toString().substring(0, 15));
                map.put("activeinfo", activeinfo);
                map.put("userinfo", null);
            } else if (activemap != null && activemap.size() != 0 && "1".equals(activemap.get("ruchangquan_type")) && "bm".equals(type)) {

                // 已报名，返回活动信息
                map.put("type", 1);
                map.put("msg", "此人已经报名，返回活动信息");
                activeinfo.put("bm_rank", LastBmRank);
                activeinfo.put("username", userinfo.get("username"));
                activeinfo.put("active_start_time", activeinfo.get("active_start_time").toString().substring(0, 15));
                map.put("activeinfo", activeinfo);
                map.put("userinfo", null);
            } else {
                //未报名 ,未签到，返回用户信息,和签到信息
                map.put("type", 2);
                map.put("msg", "此人为注册用户");
                map.put("activeinfo", null);
                map.put("userinfo", userinfo = findcode(userinfo));

            }
            JsonWriteUtil.write(response, map);
        }
//        // 发送手机验证码
//        Map<String, Object> activemap =
//                commonService.findOneForJdbc("SELECT * FROM t_sc_active_sign WHERE active_Id="
//                        + activeid + "  AND telephone='" + telphoto + "'");
//        if (activemap != null && activemap.size() != 0) {
//            if ("1".equals(activemap.get("ruchangquan_type").toString()) && "bm".equals(type)) {
//                map.put("success", false);
//                map.put("msg", "您已经报名！");
//            } else if ("1".equals(activemap.get("qiandao_type").toString()) && "qd".equals(type)) {
//                map.put("success", false);
//                map.put("msg", "您已经签到！");
//            } else {
//                map=findcode(map, activemap);
//                map.put("success", true);
//                map.put("msg", " 请您继续进行");
//                map.put("parameter", activemap);
//                commons.sendsmsByTemplate(1, telphoto.toString(), result);
//            }
//
//        } else {
//            
//            ActiveUserArttr activeUserArttr =
//                    commonService
//                            .findUniqueByProperty(ActiveUserArttr.class, "telephone", telphoto);
//            if (activeUserArttr != null) {
//                map=findcode(map, activeUserArttr);
//                map.put("success", true);
//                map.put("msg", " 请您继续进行");
//                
//                Map<String, Object> activemaps =new HashMap<String, Object>();
//                activemaps.put("telephone", activeUserArttr.getTelephone());
//                activemaps.put("pro", activeUserArttr.getProvince());
//                activemaps.put("city",activeUserArttr.getCity() );
//                activemaps.put("quxian", activeUserArttr.getQuxian());
//                activemaps.put("school_name",activeUserArttr.getSchool_name() );
//                activemaps.put("major_type",activeUserArttr.getMajor_type() );
//                activemaps.put("classes",activeUserArttr.getClasses() );
//                map.put("parameter", activemaps);
//                commons.sendsmsByTemplate(1, telphoto.toString(), result);
//            } else {
//                map.put("success", true);
//                map.put("msg", "此人之前没有进行报名或签到活动!");
//                map.put("parameter", null);
//                commons.sendsmsByTemplate(1, telphoto.toString(), result);
//            }
//
//        }
//        JsonWriteUtil.write(response, map);

    }


    public Map<String, Object> findcode(Map<String, Object> map) {

        if (map.get("province") != null && map.get("school_name") != null) {
            String sql = "SELECT  pro_code ,city_code,quxian_code,school_code FROM t_xueji_sign WHERE pro_name='" + map.get("province") + "' AND city_name='" + map.get("city") + "' AND school_name='" + map.get("school_name") + "'";
            Map<String, Object> mapinfo = commonService.findOneForJdbc(sql);
            if (mapinfo != null) {
                map.put("pro_code", mapinfo.get("pro_code"));
                map.put("city_code", mapinfo.get("city_code"));
                map.put("quxian_code", mapinfo.get("quxian_code"));
                map.put("school_code", mapinfo.get("school_code"));
            }
            if ("文科".equals(map.get("classes"))) {
                map.put("majortype", 1);
            } else if ("理科".equals(map.get("classes"))) {
                map.put("majortype", 2);
            } else {
                map.put("majortype", 3);
            }

            if ("高一".equals(map.get("classes"))) {
                map.put("classes", 1);
            } else if ("高二".equals(map.get("classes"))) {
                map.put("classes", 2);
            } else {
                map.put("classes", 3);
            }


        } else {
            String sql = "SELECT DISTINCT pro_code ,city_code FROM t_xueji_sign WHERE pro_name='" + map.get("province") + "' AND city_name='" + map.get("city") + "'";
            Map<String, Object> mapinfo = commonService.findOneForJdbc(sql);
            if (mapinfo != null) {
                map.put("pro_code", mapinfo.get("pro_code"));
                map.put("city_code", mapinfo.get("city_code"));
            }
            if ("文科".equals(map.get("classes"))) {
                map.put("majortype", 1);
            } else if ("理科".equals(map.get("classes"))) {
                map.put("majortype", 2);
            } else {
                map.put("majortype", 3);
            }

            if ("高一".equals(map.get("classes"))) {
                map.put("classes", 1);
            } else if ("高二".equals(map.get("classes"))) {
                map.put("classes", 2);
            } else {
                map.put("classes", 3);
            }
        }
        return map;

    }


    public TScActiveUserInfo saveActiveUserArttr(String name, String telphoto, String pro,
                                                 String city, String county, String schoolName, String grade, String majorType,
                                                 String class_rank) {

        TScActiveUserInfo newactiveUserArttr = new TScActiveUserInfo();
        newactiveUserArttr.setUsername(name);
        newactiveUserArttr.setTelephone(telphoto);
        if (!StringUtils.isEmpty(pro)) {
            newactiveUserArttr.setProvince(pro);
        }

        if (!StringUtils.isEmpty(city)) {
            newactiveUserArttr.setCity(city);
        }
        if (!StringUtils.isEmpty(county)) {
            newactiveUserArttr.setQuxian(county);
        }
        if (!StringUtils.isEmpty(schoolName)) {
            newactiveUserArttr.setSchoolName(schoolName);

        }
        if (!StringUtils.isEmpty(grade)) {
            newactiveUserArttr.setClasses(grade);
        }
        if (!StringUtils.isEmpty(majorType)) {
            newactiveUserArttr.setMajorType(majorType);
        }
        if (!StringUtils.isEmpty(class_rank)) {
            newactiveUserArttr.setClassRank(Integer.parseInt(class_rank.trim()));
        }

        return newactiveUserArttr;

    }


    public TScActiveSign saveAdmissionTicket(String name, String telphoto, String pro,
                                             String city, String county, String schoolName, String grade, String majorType,
                                             String class_rank, String peonum) {

        TScActiveSign admissionTicket = new TScActiveSign();
        admissionTicket.setName(name);
        admissionTicket.setTelephone(telphoto);
        if (!StringUtils.isEmpty(pro)) {
            admissionTicket.setPro(pro);
        }
        if (!StringUtils.isEmpty(city)) {
            admissionTicket.setCity(city);
        }
        if (!StringUtils.isEmpty(county)) {
            admissionTicket.setQuxian(county);
        }
        if (!StringUtils.isEmpty(schoolName)) {
            admissionTicket.setSchoolName(schoolName);

        }
        if (!StringUtils.isEmpty(grade)) {
            admissionTicket.setClasses(grade);
        }
        if (!StringUtils.isEmpty(majorType)) {
            admissionTicket.setMajorType(majorType);
        }
        if (!StringUtils.isEmpty(class_rank)) {
            admissionTicket.setClassRank(Integer.parseInt(class_rank.trim()));
        }
        if (!StringUtils.isEmpty(peonum)) {
            admissionTicket.setPeonum(Integer.parseInt(peonum.trim()));
        }
        return admissionTicket;

    }


    @RequestMapping(value = "/sign/returnurl.jspx")
    public String ceshi(HttpServletRequest request,
                        HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);


        return FrontUtils.getTplPath(site.getSolutionPath(), "sign", "returnurl");
    }


    @Autowired
    private CommonsImpl commons;

    @Autowired
    private SessionProvider session;

    @Autowired
    private CommonSvc commonService;


}
