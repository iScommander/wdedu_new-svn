package com.jeecms.wdedu.action;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.util.AliPayCommonUtil;
import com.bcloud.msg.http.HttpSender;
import com.jeecms.common.web.HttpClientUtil;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.CmsGroup;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.core.web.util.JsonWriteUtil;
import com.jeecms.wdedu.entity.TBaseProducts;
import com.jeecms.wdedu.entity.TBaseVrcards;
import com.jeecms.wdedu.entity.TYnOnlineOrder;
import com.jeecms.wdedu.outsides.UserWebServieImpl;
import com.jeecms.wdedu.outsides.UserWebServieImplService;
import com.jeecms.wdedu.service.CommonSvc;
import com.jeecms.wdedu.service.impl.CommonsImpl;
import com.utils.PasswordUtil;
import com.utils.wxpay.WxpayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

import static com.alipay.config.AlipayConfig.*;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @Description: 微信支付
 * @date 2018/11/8
 */
@RequestMapping("/alipay")
public class AliPayAct {
    private static final Logger log = LoggerFactory.getLogger(AliPayAct.class);
    @Autowired
    private CommonsImpl commons;

    @Autowired
    private SessionProvider session;

    @Autowired
    private CommonSvc commonSvc;

//    生成支付宝二维码、价格
    @RequestMapping("/toPay.jspx")
    public void toPay(Integer product_id, String phone, String buyName, String remark, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
//        phone = user.getPhone();
//        buyName = user.getRealname();

        CmsUser tmpUser = commonSvc.findUniqueByProperty(CmsUser.class, "username", phone);
        CmsGroup tyUserGroup = commonSvc.findUniqueByProperty(CmsGroup.class, "name", "体验用户");
        TBaseProducts product = new TBaseProducts();
        Map<String, String> res = new HashMap<String, String>();
        if (tmpUser != null && tmpUser.getGroups().contains(tyUserGroup)) {
            product = commonSvc.get(TBaseProducts.class, 66);
            res.put("sp_fee1", product.getCost().toString());
            res.put("sp_fee2", product.getDiscountCost().toString());
        } else {
            product = commonSvc.get(TBaseProducts.class, product_id);
        }

        // 生成预付ORderID
        String outTradeNo = WxpayUtil.getNonceStr();
        //创建预付订单信息 并保存b
        TYnOnlineOrder order = new TYnOnlineOrder();
        //计算价格 单位：元
        BigDecimal payAmount = product.getCost().subtract(product.getDiscountCost()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
        order.setCftSeq(outTradeNo);
        order.setCreateDate(new Date());
        order.setCost(payAmount.multiply(new BigDecimal(100)));
        order.setPayState(0);
        order.setPhonenumber(phone);
        order.setProductId(product.getId());
//        order.setBuyname(buyName);
        order.setRemark(remark);
        //order.setChannel(CommonConstant.SELL_CHANNEL_WX);
        commonSvc.saveOrUpdate(order);
        //生成二维码连接
        try {
            String qr_code = getAliPayUrl(outTradeNo, product.getName(), payAmount, phone);
            res.put("qr_code", qr_code);
//            model.addAttribute("qr_code", qr_code);
            response.setCharacterEncoding("utf-8");
//            response.getWriter().write(qr_code);
            JsonWriteUtil.write(response, res);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/toPay_sygh.jspx")
    public void toPay_sygh(Integer product_id, String phone, String buyName, String remark, String province,String majorType,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
//        phone = user.getPhone();
//        buyName = user.getRealname();

        CmsUser tmpUser = commonSvc.findUniqueByProperty(CmsUser.class, "username", phone);
        CmsGroup tyUserGroup = commonSvc.findUniqueByProperty(CmsGroup.class, "name", "体验用户");
        TBaseProducts product = new TBaseProducts();
        Map<String, String> res = new HashMap<String, String>();
        if (tmpUser != null && tmpUser.getGroups().contains(tyUserGroup)) {
            product = commonSvc.get(TBaseProducts.class, 66);
            res.put("sp_fee1", product.getCost().toString());
            res.put("sp_fee2", product.getDiscountCost().toString());
        } else {
            product = commonSvc.get(TBaseProducts.class, product_id);
        }

        // 生成预付ORderID
        String outTradeNo = WxpayUtil.getNonceStr();
        //创建预付订单信息 并保存b
        TYnOnlineOrder order = new TYnOnlineOrder();
        //计算价格 单位：元
        BigDecimal payAmount = product.getCost().subtract(product.getDiscountCost()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
        order.setCftSeq(outTradeNo);
        order.setCreateDate(new Date());
        order.setCost(payAmount.multiply(new BigDecimal(100)));
        order.setPayState(0);
        order.setPhonenumber(phone);
        order.setProductId(product.getId());
//        order.setBuyname(buyName);
        order.setRemark(remark);
        order.setProvinceId(Integer.valueOf(province));
        order.setMajorTypeId(Integer.valueOf(majorType));
        //order.setChannel(CommonConstant.SELL_CHANNEL_WX);
        commonSvc.saveOrUpdate(order);
        //生成二维码连接
        try {
            String qr_code = getAliPayUrl(outTradeNo, product.getName(), payAmount, phone);
            res.put("qr_code", qr_code);
//            model.addAttribute("qr_code", qr_code);
            response.setCharacterEncoding("utf-8");
//            response.getWriter().write(qr_code);
            JsonWriteUtil.write(response, res);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 支付宝回调通知
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/alipayCallback.jspx", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(readOnly = false)
    public void aliPayNotify(HttpServletRequest request, HttpServletResponse response, String phone) {
        System.out.println(">>>>>REQUEST JSON STRING:" + JSON.toJSONString(request.getParameterMap()));
        Map<String, String> aliPayMap = AliPayCommonUtil.toAliMap(request);
        try {
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            Boolean flag = AlipaySignature.rsaCheckV1(aliPayMap, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE);
            System.out.println(">>flag=" + flag);
            if (flag) {
                String orderSeq = aliPayMap.get("out_trade_no").toString(); //订单序列
                TYnOnlineOrder order = commonSvc.findUniqueByProperty(TYnOnlineOrder.class, "cftSeq", orderSeq);
                phone = order.getPhonenumber();
                System.out.println(">>>>>order:" + JSON.toJSONString(order));
                String tradeStatus = aliPayMap.get("trade_status");
                System.out.println("tradeStatus=" + tradeStatus);
                if ("TRADE_SUCCESS".equals(tradeStatus)) {
                    System.out.println("交易成功");
                    writer.write("success");
                    //如果交易成功
                    Date payDate = new Date(); //交易成功时间
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(payDate);
                    calendar.add(Calendar.YEAR, 1);
                    payDate = calendar.getTime();
                    //创建新卡
                    TBaseVrcards card = new TBaseVrcards();
                    card.setCreateTime(new Date());
                    String password = PasswordUtil.randomPassword();
                    card.setPassword(password);
                    card.setActiveStatus(false);
                    card.setInOutStorage(1);// 出库状态
                    card.setProvinceId(order.getProduct().getProvinceId());
                    card.setGroupId(order.getProduct().getGroupId());
                    card.setLosedTime(payDate);
                    //commonSvc.saveOrUpdate(card);
                    Long cardNo = (Long) commonSvc.save(card);
                    System.out.println(">>cardNo=" + cardNo);
                    //修改订单信息
                    order.setCardId(cardNo);
                    order.setPassword(password);
                    order.setPayDate(payDate);
                    order.setPayState(1); //已付款
                    commonSvc.saveOrUpdate(order);

                    //生涯规划注册用户
                    Integer province = order.getProvinceId();
                    Integer majorTypeId = order.getMajorTypeId();
                    Integer type = 4;
                    Integer groupId = 4;
                    String name = order.getPhonenumber();
                    UserWebServieImpl impl = new UserWebServieImplService().getUserWebServieImplPort();
                    impl.registUser(name,password,province,type,majorTypeId,groupId);


                    String uri = "http://send.18sms.com/msg/HttpBatchSendSM";//应用地址
                    String account = "173kbz ";//示远账号
                    String pswd = "B9pL6iPk";//示远密码
                    String mobile = phone;//手机号码，多个号码使用","分割
                    String content = "您好，您购买的账号是：" + name + "密码是" + password;//短信内容，注意内容中的逗号请使用中文状态下的逗号
                    boolean needstatus = true;//是否需要状态报告，需要true，不需要false
                    String product = "";//产品ID(不用填写)
                    String extno = "";//扩展码(不用填写)

                    String url= "http://www.wodecareer.com:8085/wdedu/wxpay/createSyghUser.jspx?username=15965456998";
                    String res= HttpClientUtil.getInstance().get(url);

                    try {
                        String returnString = HttpSender.batchSend(uri, account, pswd, mobile, content, needstatus, product, extno);
                        System.out.println(returnString);
                        //TODO 处理返回值,参见HTTP协议文档
                    } catch (Exception e) {
                        //TODO 处理异常
                        e.printStackTrace();
                    }
//                    Map<String, Object> map = new HashMap<String, Object>();
//                    session.setAttribute(request, response, "cardNo", cardNo);
//                    session.setAttribute(request, response, "password", password);
//                    session.setAttribute(request, response, "phone", phone);
                    System.out.println("卡号：" + cardNo + "【沃得教育】");
                    System.out.println("手机号：" + phone + "【沃得教育】");
                    System.out.println("密码：" + password + "【沃得教育】");
//                    commons.sendsmsByTemplate(101, phone.toString(), password,cardNo.toString());
                } else {
                    log.info(">>>交易失败");
                    writer.write("fail");
                }
            } else {
                log.info(">>>解析异常");
                writer.write("fail");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param outTradeNo 订单序列号
     * @param subject    产品名称
     * @param payAmount  订单金额 单位：元
     * @return
     */
    public String getAliPayUrl(String outTradeNo, String subject, BigDecimal payAmount, String phone) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(GATE_WAY_URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayTradePrecreateRequest aliRequest = new AlipayTradePrecreateRequest();//创建API对应的request类
        //设置回调地址
        aliRequest.setNotifyUrl(ALI_NOTIFY_URL);
        Map<String, Object> map = new HashMap<>();
        map.put("out_trade_no", outTradeNo);
        map.put("total_amount", payAmount);
        map.put("subject", subject);
        map.put("phoneNumber", phone);
        map.put("timeout_express", ALI_TIMEOUT_EXPRESS);
        //设置请求业务内容
        aliRequest.setBizContent(JSON.toJSONString(map));
        AlipayTradePrecreateResponse aliResponse = alipayClient.execute(aliRequest);
        //System.out.print(aliResponse.getBody());
        String body = aliResponse.getBody();
        Map<String, Map<String, String>> res = JSON.parseObject(body, Map.class);
        //获取qr_code的url
        String qr_code = res.get("alipay_trade_precreate_response").get("qr_code").replace("\\", "");
        return qr_code;
    }

    /**
     * 支付入口
     */
    @RequestMapping(value = "/fuwuzhongxin.jspx")
    public String fuWuZhongXin(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
//        if (user == null) {
//            return FrontUtils.showLogin(request, model, site);
//        }
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), "pay", "fuwuzhongxin");
    }

    //@RequestMapping("/test.jspx")
    //public void test() {
    //    //String sql = "select max(cardNo) cardNo from t_base_vrcards";
    //    //long card_id = Long.parseLong(commonSvc.findOneForJdbc(sql).get("cardNo").toString()) + 1;
    //    TBaseVrcards card = new TBaseVrcards();
    //    //commonSvc.findUniqueByProperty(TBaseVrcards.class, "cardNo", 5);
    //    card.setCreateTime(new Date());
    //    String password = PasswordUtil.randomPassword();
    //    card.setPassword(password);
    //    card.setActiveStatus(false);
    //    card.setInOutStorage(1);// 出库状态
    //    card.setProvinceId(1);
    //    card.setGroupId(3);
    //    //card.setCardNo(card_id);
    //    Long cardNo = (Long) commonSvc.save(card);
    //    System.out.println(cardNo);
    //}
}
