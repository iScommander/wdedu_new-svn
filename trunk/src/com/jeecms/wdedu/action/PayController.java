package com.jeecms.wdedu.action;

import com.alibaba.fastjson.JSON;
import com.bcloud.msg.http.HttpSender;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.HttpClientUtil;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.core.entity.CmsGroup;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.CmsUserExt;
import com.jeecms.core.manager.CmsGroupMng;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.core.web.util.JsonWriteUtil;
import com.jeecms.wdedu.entity.*;
import com.jeecms.wdedu.outsides.UserWebServieImpl;
import com.jeecms.wdedu.outsides.UserWebServieImplService;
import com.jeecms.wdedu.service.CommonSvc;
import com.jeecms.wdedu.service.impl.CommonsImpl;
import com.utils.CommonConstant;
import com.utils.PasswordUtil;
import com.utils.wxpay.WxpayUtil;
import org.apache.http.client.ClientProtocolException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.*;

import static com.jeecms.common.page.SimplePage.cpn;

/**
 * 支付Controller层
 *
 * @author
 */
@Controller
@Transactional
public class PayController {
    private static final Logger LOG = LoggerFactory.getLogger(PayController.class);
    public static final String TPLDIR = "pay";

    @Autowired
    private CmsGroupMng cmsGroupMng;
    @Autowired
    private CmsUserMng cmsUserMng;
    @Autowired
    private CommonSvc commonService;
    @Autowired
    private CommonsImpl commons;

    @RequestMapping(value = "/wxpay/index.jspx")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb6501f7e96a2b8ce&redirect_uri=http://http://222.95.86.167:8088/zywy/wxpay/wxpay.jspx&response_type=code&scope=snsapi_base&state=456123456#wechat_redirect";
    }

//    显示产品列表及地区
    @RequestMapping(value = "/wxpay/wxpay.jspx")
    public String wxpay(String code, Integer type, Integer product_id, Integer pageNo,
                        HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ClientProtocolException, IOException {
        System.out.println("################有人进入支付页面了##############");
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        model.addAttribute("provinceList", commonService.loadAll(TBaseProvince.class));
        Finder f = Finder.create("from TBaseProducts bean where 1=1 ");
        if (type != null) {
            f.append(" and bean.type=:type ");
            f.setParam("type", type);
        } else {
            f.append(" and bean.type=:type ");
            f.setParam("type", 1);
        }
        if (product_id != null) {
            f.append(" and bean.provinceId=:province_id ");
            f.setParam("province_id", product_id);
        }
        Pagination pagination = commonService.findPager(f, cpn(pageNo), 50);
        model.addAttribute("code", code);
        model.addAttribute("pagination", pagination);
        model.addAttribute("pageNo", pageNo);
        return FrontUtils.getTplPath(site.getSolutionPath(), "pay", "payment");
//        return FrontUtils.getTplPath(site.getSolutionPath(), "pay", "wxpay");
    }

    @RequestMapping(value = "/wxpay/getwx_data.jspx")
    public void getwx_jsapi(String openid, Integer product_id, String phone, String buyname,
                            String channel, String remark, HttpServletRequest request, HttpServletResponse response,
                            ModelMap model) throws IOException {
        System.out.println("###提交订单###");
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
//        buyname = user.getRealname();
        FrontUtils.frontData(request, model, site);
        Map<String, String> res = new HashMap<String, String>();

        CmsUser tmpUser = commonService.findUniqueByProperty(CmsUser.class, "username", phone);
        CmsGroup tyUserGroup = commonService.findUniqueByProperty(CmsGroup.class, "name", "体验用户");
        TBaseProducts product = new TBaseProducts();
        if (tmpUser != null && tmpUser.getGroups().contains(tyUserGroup)) {
            product = commonService.get(TBaseProducts.class, 66);
            res.put("sp_fee1", product.getCost().toString());
            res.put("sp_fee2", product.getDiscountCost().toString());
        } else {
            product = commonService.get(TBaseProducts.class, product_id);
        }
        // 生成预付ORderID
        String outTradeNo = WxpayUtil.getNonceStr();
        BigDecimal fee1 = (product.getCost().subtract(product.getDiscountCost()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP));
        String qr = qrPay(outTradeNo, "127.0.0.1", phone, String.valueOf(fee1), product);
        res.put("result", qr);
        TYnOnlineOrder order = new TYnOnlineOrder();
        order.setCftSeq(outTradeNo);
        order.setCreateDate(new Date());
        BigDecimal fee2 = fee1.multiply(new BigDecimal(100));
        order.setCost(fee2);
        order.setPayState(0);
        order.setPhonenumber(phone);
        order.setProductId(product.getId());
//        order.setBuyname(buyname);
        order.setRemark(remark);
        order.setChannel(CommonConstant.SELL_CHANNEL_WX);
        commonService.saveOrUpdate(order);

        System.out.println("outTradeNo:" + outTradeNo);
        System.out.println("res.result:" + res.get("result"));

        JsonWriteUtil.write(response, res);
    }

//    显示微信支付二维码、价格
    @RequestMapping(value = "/wxpay/getwx_data_sygh.jspx")
    public void getwx_sygh(String openid, Integer product_id, String phone, String buyname,
                            String channel, String remark,String province,String majorType, HttpServletRequest request, HttpServletResponse response,
                            ModelMap model) throws IOException {
        System.out.println("###提交订单###");
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
//        buyname = user.getRealname();
        FrontUtils.frontData(request, model, site);
        Map<String, String> res = new HashMap<String, String>();

        CmsUser tmpUser = commonService.findUniqueByProperty(CmsUser.class, "username", phone);
        CmsGroup tyUserGroup = commonService.findUniqueByProperty(CmsGroup.class, "name", "体验用户");
        TBaseProducts product = new TBaseProducts();
        if (tmpUser != null && tmpUser.getGroups().contains(tyUserGroup)) {
            product = commonService.get(TBaseProducts.class, 66);
            res.put("sp_fee1", product.getCost().toString());
            res.put("sp_fee2", product.getDiscountCost().toString());
        } else {
            product = commonService.get(TBaseProducts.class, product_id);
        }
        // 生成预付ORderID
        String outTradeNo = WxpayUtil.getNonceStr();
        BigDecimal fee1 = (product.getCost().subtract(product.getDiscountCost()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP));
        String qr = qrPay(outTradeNo, "127.0.0.1", phone, String.valueOf(fee1), product);
        res.put("result", qr);
        TYnOnlineOrder order = new TYnOnlineOrder();
        order.setCftSeq(outTradeNo);
        order.setCreateDate(new Date());
        BigDecimal fee2 = fee1.multiply(new BigDecimal(100));
        order.setCost(fee2);
        order.setPayState(0);
        order.setPhonenumber(phone);
        order.setProductId(product.getId());
//        order.setBuyname(buyname);
        order.setRemark(remark);
        order.setProvinceId(Integer.parseInt(province));
        order.setMajorTypeId(Integer.parseInt(majorType));
        order.setChannel(CommonConstant.SELL_CHANNEL_WX);
        commonService.saveOrUpdate(order);

        System.out.println("outTradeNo:" + outTradeNo);
        System.out.println("res.result:" + res.get("result"));

        JsonWriteUtil.write(response, res);
    }

    @RequestMapping(value = "/wxpayCallback.jspx")
    public void notify(HttpServletRequest request, HttpServletResponse response, String phone) throws Exception {
        // 把如下代码贴到的你的处理回调的servlet 或者.do 中即可明白回调操作
        System.out.print("微信支付回调数据开始");
        // 示例报文
        // String xml =
        // "<xml><appid><![CDATA[wxb4dc385f953b356e]]></appid><bank_type><![CDATA[CCB_CREDIT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1228442802]]></mch_id><nonce_str><![CDATA[1002477130]]></nonce_str><openid><![CDATA[o-HREuJzRr3moMvv990VdfnQ8x4k]]></openid><out_trade_no><![CDATA[1000000000051249]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[1269E03E43F2B8C388A414EDAE185CEE]]></sign><time_end><![CDATA[20150324100405]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1009530574201503240036299496]]></transaction_id></xml>";
        String inputLine;
        String notityXml = "";
        String resXml = "";

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
        if ("SUCCESS".equals(wpr.getResultCode())) {
            // 支付成功
            resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                    + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            Finder ff = Finder.create("from TYnOnlineOrder where cftSeq=:cftSeq");
            ff.setParam("cftSeq", wpr.getOutTradeNo());
            TYnOnlineOrder order = commonService.getOneResult(ff);
            phone = order.getPhonenumber();


            // 插入卡信息
            TBaseVrcards card = new TBaseVrcards();
//            card.setCardNo(card_id);
            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, 1);
            date = calendar.getTime();
            String password = PasswordUtil.randomPassword();
            card.setPassword(password);
            card.setCreateTime(new Date());
            card.setActiveStatus(false);
            card.setInOutStorage(1);// 出库状态
            card.setProvinceId(order.getProduct().getProvinceId());
            card.setGroupId(order.getProduct().getGroupId());
            card.setLosedTime(date);
            Long cardNo = (Long) commonService.save(card);
            System.out.println(">>cardNo=" + cardNo);
            System.out.println("-------------------" + JSON.toJSONString(card));
            System.out.println("################密码：" + password + "##############");

            // 保存订单信息
            order.setCardId(cardNo);
            order.setPassword(password);
            order.setPayDate(new Date());
            order.setPayState(1);
            commonService.saveOrUpdate(order);

            //生涯规划注册用户
            Integer province = order.getProvinceId();
            Integer majorTypeId = order.getMajorTypeId();
            Integer type = 4;
            Integer groupId = 4;
            String name = order.getPhonenumber();
            UserWebServieImpl impl = new UserWebServieImplService().getUserWebServieImplPort();
            impl.registUser(name,password,province,type,majorTypeId,groupId);

            System.out.println("ORDER>>>>>>>>>>>>>" + JSON.toJSONString(order));

            String uri = "http://send.18sms.com/msg/HttpBatchSendSM";//应用地址
            String account = "173kbz ";//示远账号
            String pswd = "B9pL6iPk";//示远密码
            String mobile = phone;//手机号码，多个号码使用","分割
            String content = "您好，您购买的账号是：" + name + "密码是" + password;//短信内容，注意内容中的逗号请使用中文状态下的逗号
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
            System.out.println("卡号：" + cardNo + "【沃得教育】");
            System.out.println("手机号：" + phone + "【沃得教育】");
            System.out.println("密码：" + password + "【沃得教育】");
            // 获取短信模板
            //t_d_msg_template template = commonService.get(t_d_msg_template.class, 101);
            //String content = String.format(template.getContent(), card_id, password);
            // 发送短信
            //t_d_msg_mq msg = new t_d_msg_mq();
            //msg.setContent(content);
            //msg.setCreate_date(new Date());
            //msg.setPhone(order.getPhone());
            //msg.setType(3);
            //msg.setSend_state(false);
            //commonService.saveOrUpdate(msg);

        } else {
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }

        System.out.println("微信支付回调数据结束");

        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();

    }

    /**
     * description: 解析微信通知xml
     *
     * @param xml
     * @return
     * @author ex_yangxiaoyi
     * @see
     */
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
    public String qrPay(String outTradeNo, String ip, String phone, String fee,
                        TBaseProducts product) {
        WxPayDto tpWxPay = new WxPayDto();
        tpWxPay.setOrderId(outTradeNo);// 订单号
        tpWxPay.setTotalFee(fee);
        tpWxPay.setSpbillCreateIp(ip);
        tpWxPay.setNotifyUrl(product.getCftNotifyUrl());
        tpWxPay.setBody(product.getDesc());
        tpWxPay.setOpenId("");
        String attach = phone + "," + product.getProvinceId().toString();
        tpWxPay.setAttach(attach);
        return WxpayUtil.getCodeurl(tpWxPay, product);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/wxpay/buyZhiyuan.jspx")
    public String buyZhiyuan(Integer type, Integer product_id, HttpServletRequest request,
                             HttpServletResponse response, ModelMap model)
            throws ClientProtocolException, IOException {
        System.out.println("################有人进入志愿填报支付页面了##############");
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        model.addAttribute("provinceList", commonService.loadAll(TBaseProvince.class));

        Finder f = Finder.create("from TBaseProducts bean where 1=1 ");
        if (type != null) {
            f.append(" and bean.type=:type ");
            f.setParam("type", type);
        } else {
            f.append(" and bean.type=:type ");
            f.setParam("type", 2);
        }
        if (product_id != null) {
            f.append(" and bean.province_id=:province_id ");
            f.setParam("province_id", product_id);
        }
        List<TBaseProducts> productList = commonService.find(f);
        model.addAttribute("productList", productList);
        return FrontUtils.getTplPath(site.getSolutionPath(), "pay", "buyZhiyuan");
    }

    @RequestMapping(value = "/wxpay/getwx_zhiyuan.jspx")
    public void getwx_zhiyuan(String openid, Integer product_id, String phone, String buyname,
                              String channel, String remark, HttpServletRequest request, HttpServletResponse response,
                              ModelMap model) throws IOException {
        System.out.println("###提交购买志愿填报订单###");
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        Map<String, String> res = new HashMap<String, String>();

        // 判断用户是否是14:学大家长(8kt/4kt)
        CmsUser user = cmsUserMng.findByUsername(phone);
        if (CommonConstant.GROUP_ID_14 != user.getGroup().getId()) {
            res.put("msg", "您所在的用户组不可购买此服务!");
            JsonWriteUtil.write(response, res);
            return;
        }

        TBaseProducts product = commonService.get(TBaseProducts.class, product_id);

        // 生成预付ORderID
        //String outTradeNo = WxpayUtil.getNonceStr();
        //Double fee1 = product.getFee().subtract(product.getDiscount_fee()).doubleValue() / 100;
        String outTradeNo = WxpayUtil.getNonceStr();
        BigDecimal fee1 = (product.getCost().subtract(product.getDiscountCost()).divide(new BigDecimal(100), 0, BigDecimal.ROUND_HALF_UP));

        String qr = qrPay(outTradeNo, "127.0.0.1", phone, String.valueOf(fee1), product);
        res.put("result", qr);

        TYnOnlineOrder order = new TYnOnlineOrder();
        order.setCftSeq(outTradeNo);
        order.setCreateDate(new Date());
        BigDecimal fee2 = (fee1.multiply(new BigDecimal(100)));
        order.setCost(fee2);
        order.setPayState(0);
        order.setPhonenumber(phone);
        order.setProductId(product_id);
        order.setBuyname(buyname);
        order.setRemark(remark);
        order.setChannel(CommonConstant.SELL_CHANNEL_WX);
        commonService.saveOrUpdate(order);

        System.out.println("outTradeNo:" + outTradeNo);
        System.out.println("res.result:" + res.get("result"));

        JsonWriteUtil.write(response, res);
    }

    @RequestMapping(value = "/buyZyCallback.jspx")
    public void buyZyCallback(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 把如下代码贴到的你的处理回调的servlet 或者.do 中即可明白回调操作
        System.out.print("微信支付志愿服务回调数据开始");
        // 示例报文
        // String xml =
        // "<xml><appid><![CDATA[wxb4dc385f953b356e]]></appid><bank_type><![CDATA[CCB_CREDIT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1228442802]]></mch_id><nonce_str><![CDATA[1002477130]]></nonce_str><openid><![CDATA[o-HREuJzRr3moMvv990VdfnQ8x4k]]></openid><out_trade_no><![CDATA[1000000000051249]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[1269E03E43F2B8C388A414EDAE185CEE]]></sign><time_end><![CDATA[20150324100405]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1009530574201503240036299496]]></transaction_id></xml>";
        String inputLine;
        String notityXml = "";
        String resXml = "";

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
        if ("SUCCESS".equals(wpr.getResultCode())) {
            // 支付成功
            resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                    + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            Finder ff = Finder.create("from TYnOnlineOrder where cftSeq=:cft_seq");
            ff.setParam("cft_seq", wpr.getOutTradeNo());
            TYnOnlineOrder order = commonService.getOneResult(ff);

            // 更新用户组
            try {
                CmsUser user = cmsUserMng.findByUsername(order.getPhonenumber());
                user.setGroup(cmsGroupMng.findById(CommonConstant.GROUP_ID_16));
                cmsUserMng.updateUser(user);
            } catch (Exception e) {
                System.out.println("购买志愿填报服务后更新用户组失败：" + order.getPhonenumber());
                System.out.println(e.getMessage());
            }

            // 保存订单信息
            order.setPayDate(new Date());
            order.setPayState(1);
            commonService.saveOrUpdate(order);

            // 发送短信
            //commonService.sendSMS(CommonConstant.TEMP_220, order.getPhone(), new Object[] {});
            commons.sendsmsByTemplate(1, order.getPhonenumber(), "服务购买成功");
        } else {
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }

        System.out.println("微信支付回调数据结束");

        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }


    @RequestMapping(value = "/wxpay/kstbPay.jspx")
    public String kstbPay(String code, Integer type, Integer product_id, Integer pageNo,
                          HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ClientProtocolException, IOException {
        System.out.println("################有人进入支付页面了##############");
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        model.addAttribute("provinceList", commonService.loadAll(TBaseProvince.class));
        Finder f = Finder.create("from TBaseProducts bean where 1=1 ");
        if (type != null) {
            f.append(" and bean.type=:type ");
            f.setParam("type", type);
        } else {
            f.append(" and bean.type=:type ");
            f.setParam("type", 4);
        }
        if (product_id != null) {
            f.append(" and bean.provinceId=:province_id ");
            f.setParam("province_id", product_id);
        }
        Pagination pagination = commonService.findPager(f, cpn(pageNo), 50);
        model.addAttribute("code", code);
        model.addAttribute("pagination", pagination);
        model.addAttribute("pageNo", pageNo);
        return FrontUtils.getTplPath(site.getSolutionPath(), "pay", "payment_kstb");
//        return FrontUtils.getTplPath(site.getSolutionPath(), "pay", "wxpay");
    }

    @RequestMapping(value = "/wxpay/syghPay.jspx")
    public String syghPay(String code, Integer type, Integer product_id, Integer pageNo,
                          HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws ClientProtocolException, IOException {
        System.out.println("################有人进入支付页面了##############");
        CmsSite site = CmsUtils.getSite(request);
//        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        model.addAttribute("provinceList", commonService.loadAll(TBaseProvince.class));
        Finder f = Finder.create("from TBaseProducts bean where 1=1 ");
        if (type != null) {
            f.append(" and bean.type=:type ");
            f.setParam("type", type);
        } else {
            f.append(" and bean.type=:type ");
            f.setParam("type", 5);
        }
        if (product_id != null) {
            f.append(" and bean.provinceId=:province_id ");
            f.setParam("province_id", product_id);
        }
        Pagination pagination = commonService.findPager(f, cpn(pageNo), 50);
        model.addAttribute("code", code);
        model.addAttribute("pagination", pagination);
        model.addAttribute("pageNo", pageNo);
        return FrontUtils.getTplPath(site.getSolutionPath(), "pay", "payment_sygh");
//        return FrontUtils.getTplPath(site.getSolutionPath(), "pay", "wxpay");
    }

    @RequestMapping(value = "/wxpay/createSyghUser.jspx")
    public String createSyghUser(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            password += random.nextInt();
        }
        String sql = "SELECT * FROM jo_user WHERE username='" + username + "'";
        List<Map<String, Object>> list = commonService.findForJdbc(sql);
        if (list.size() == 0) {
            Map<String, String> attr = new HashMap<>();
            cmsUserMng.registerMember(username, "", password, RequestUtils.getIpAddr(request), 3, false, new CmsUserExt(), attr);
            map.put("ok", "1");
        } else {
            map.put("ok", "2");
        }
        return "";
    }


    @RequestMapping(value="/wxpay/loginTelecom1.jspx")
    public String test(){
        String url= "http://localhost:8080/wdedu/wxpay/createSyghUser.jspx?username=1595135656";
//        StringBuffer paramBuff=new StringBuffer();
//        paramBuff.append("username="+"15951235656");
        String res= HttpClientUtil.getInstance().get(url);
//        String res= HttpClientUtil.postParams(url,null);
        String aa = "";
        return aa;
    }

    @ResponseBody
    @RequestMapping(value="/payBack.jspx")
    public String payBack(String name){
        UserWebServieImpl impl = new UserWebServieImplService().getUserWebServieImplPort();
        Integer back = impl.selectPay(name);
        String reback = back.toString();
      /*  String reback ="2";*/
        return reback;
    }


}
