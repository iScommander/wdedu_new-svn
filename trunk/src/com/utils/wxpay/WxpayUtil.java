package com.utils.wxpay;

import com.jeecms.wdedu.entity.TBaseProducts;
import com.jeecms.wdedu.entity.WxPayDto;
import com.wxpay.utils.GetWxOrderno;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

public class WxpayUtil {

    private static Object Server;

    /**
     * 把对象转换成字符串
     * 
     * @param obj
     * @return String 转换成字符串,若对象为null,则返回空字符串.
     */
    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        }

        return obj.toString();
    }

    /**
     * 把对象转换为int数值.
     * 
     * @param obj 包含数字的对象.
     * @return int 转换后的数值,对不能转换的对象返回0。
     */
    public static int toInt(Object obj) {
        int a = 0;
        try {
            if (obj != null) {
                a = Integer.parseInt(obj.toString());
            }
        } catch (Exception e) {

        }
        return a;
    }

    /**
     * 获取当前时间 yyyyMMddHHmmss
     * 
     * @return String
     */
    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = outFormat.format(now);
        return s;
    }

    /**
     * 获取当前日期 yyyyMMdd
     * 
     * @param date
     * @return String
     */
    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String strDate = formatter.format(date);
        return strDate;
    }

    /**
     * 取出一个指定长度大小的随机正整数.
     * 
     * @param length int 设定所取出随机数的长度。length小于11
     * @return int 返回生成的随机数。
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * 获取编码字符集
     * 
     * @param request
     * @param response
     * @return String
     */

    public static String getCharacterEncoding(HttpServletRequest request,
            HttpServletResponse response) {

        if (null == request || null == response) {
            return "gbk";
        }

        String enc = request.getCharacterEncoding();
        if (null == enc || "".equals(enc)) {
            enc = response.getCharacterEncoding();
        }

        if (null == enc || "".equals(enc)) {
            enc = "gbk";
        }

        return enc;
    }

    public static String URLencode(String content) {

        String URLencode;

        URLencode = replace(Server.equals(content), "+", "%20");

        return URLencode;
    }

    private static String replace(boolean equals, String string, String string2) {

        return null;
    }

    /**
     * 获取unix时间，从1970-01-01 00:00:00开始的秒数
     * 
     * @param date
     * @return long
     */
    public static long getUnixTime(Date date) {
        if (null == date) {
            return 0;
        }

        return date.getTime() / 1000;
    }

    public static String QRfromGoogle(String chl) {
        int widhtHeight = 300;
        String EC_level = "L";
        int margin = 0;
        String QRfromGoogle;
        chl = URLencode(chl);

        QRfromGoogle = "http://chart.apis.google.com/chart?chs=" + widhtHeight + "x" + widhtHeight
                + "&cht=qr&chld=" + EC_level + "|" + margin + "&chl=" + chl;

        return QRfromGoogle;
    }

    /**
     * 时间转换成字符串
     * 
     * @param date 时间
     * @param formatType 格式化类型
     * @return String
     */
    public static String date2String(Date date, String formatType) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatType);
        return sdf.format(date);
    }

    /**
     * 获取随机字符串
     * 
     * @return
     */
    public static String getNonceStr() {
        // 随机数
        String currTime = WxpayUtil.getCurrTime();
        // 8位日期
        String strTime = currTime.substring(8, currTime.length());
        // 四位随机数
        String strRandom = WxpayUtil.buildRandom(4) + "";
        // 10位序列号,可以自行调整。
        return strTime + strRandom;
    }

    /**
     * 元转换成分
     * 
     * @param
     * @return
     */
    public static String getMoney(String amount) {
        if (amount == null) {
            return "";
        }
        // 金额转化为分为单位
        String currency = amount.replaceAll("\\$|\\￥|\\,", ""); // 处理包含, ￥ 或者$的金额
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0L;
        if (index == -1) {
            amLong = Long.valueOf(currency + "00");
        } else if (length - index >= 3) {
            amLong = Long.valueOf((currency.substring(0, index + 3)).replace(".", ""));
        } else if (length - index == 2) {
            amLong = Long.valueOf((currency.substring(0, index + 2)).replace(".", "") + 0);
        } else {
            amLong = Long.valueOf((currency.substring(0, index + 1)).replace(".", "") + "00");
        }
        return amLong.toString();
    }

    /** 获取微信扫码支付二维码连接 */
    public static String getCodeurl(WxPayDto tpWxPayDto, TBaseProducts product) {

        // 1 参数
        // 订单号
        String orderId = tpWxPayDto.getOrderId();
        // 附加数据 原样返回
        String attach = tpWxPayDto.getAttach();
        // 总金额以分为单位，不带小数点
        String totalFee = getMoney(tpWxPayDto.getTotalFee());

        // 订单生成的机器 IP
        String spbill_create_ip = tpWxPayDto.getSpbillCreateIp();
        // 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
        String notify_url = tpWxPayDto.getNotifyUrl();
        String trade_type = "NATIVE";

        // 微信支付商户开通后 微信会提供appid和appsecret和商户号partner
        // 应用ID
        String appid = product.getCftEmail();
        // 应用秘钥
        String appsecret = product.getCftKey();
        // 商户号
        String mch_id = product.getCftPartner();
        // 这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
        String partnerkey = product.getZfbKey();

        // 随机字符串
        String nonce_str = getNonceStr();

        // 商品描述根据情况修改
        String body = tpWxPayDto.getBody();

        // 商户订单号
        String out_trade_no = orderId;

        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body);
        packageParams.put("attach", attach);
        packageParams.put("out_trade_no", out_trade_no);

        // 这里写的金额为1 分到时修改
        packageParams.put("total_fee", totalFee);
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", notify_url);

        packageParams.put("trade_type", trade_type);

        RequestHandler reqHandler = new RequestHandler(null, null);
        reqHandler.init(appid, appsecret, partnerkey);


        String sign = reqHandler.createSign(packageParams);
//        String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>" + mch_id + "</mch_id>"
//                + "<nonce_str>" + nonce_str + "</nonce_str>" + "<sign>" + sign + "</sign>"
//                + "<body><![CDATA[" + body + "]]></body>" + "<out_trade_no>" + out_trade_no
//                + "</out_trade_no>" + "<attach>" + attach + "</attach>" + "<total_fee>" + totalFee
//                + "</total_fee>" + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
//                + "<notify_url>" + notify_url + "</notify_url>" + "<trade_type>" + trade_type
//                + "</trade_type>" + "</xml>";

                String xml = "<xml>\n" +
                        "   <appid>"+appid+"</appid>\n" +
                        "   <attach>"+attach+"</attach>\n" +
                        "   <body>"+body+"</body>\n" +
                        "   <mch_id>"+mch_id+"</mch_id>\n" +
                        "   <nonce_str>"+nonce_str+"</nonce_str>\n" +
                        "   <notify_url>"+notify_url+"</notify_url>\n" +
                        "   <out_trade_no>"+out_trade_no+"</out_trade_no>\n" +
                        "   <spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>\n" +
                        "   <total_fee>"+totalFee+"</total_fee>\n" +
                        "   <trade_type>"+trade_type+"</trade_type>\n" +
                        "   <sign>"+sign+"</sign>\n" +
                        "</xml>";
//        String code_url = "";
        String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String code_url = new GetWxOrderno().getCodeUrl(createOrderURL, xml);
        System.out.println("code_url----------------" + code_url);

        return code_url;
    }
}
