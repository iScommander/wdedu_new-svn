package com.jeecms.wdedu.action.telecom;

import com.alibaba.fastjson.JSON;
import com.bcloud.msg.http.HttpSender;
import com.jeecms.common.web.HttpClientUtil;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.CmsUserExt;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.wdedu.entity.JcUserGroup;
import com.jeecms.wdedu.entity.TDMsgTemplate;
import com.jeecms.wdedu.service.CommonSvc;
import com.jeecms.wdedu.service.impl.CommonsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Copyright (C),
 * FileName:
 * Author:
 * Date:
 * Description: //电信用户入口注册
 * History: //修改记录
 * &lt;author&gt;      &lt;time&gt;      &lt;version&gt;    &lt;desc&gt;
 * 修改人姓名             修改时间            版本号                  描述
 **/
@Controller
@RequestMapping(value = "/telecom")
public class TelecomLogin {

    @Autowired
    private CmsUserMng cmsUserMng;

    @Autowired
    private CommonSvc commonSvc;

    @Autowired
    private CommonsImpl commons;

    @RequestMapping(value = "/loginTelecom.jspx")
    public void loginTelecom(HttpServletRequest request, HttpServletResponse response, ModelMap model, String telenum) throws IOException {
        Map <String, Object> map = new HashMap <>();
        //生成随机密码
        Random random = new Random();
        String password = "";
        password="985211";
//        for (int i = 0; i < 6; i++) {
//            password += random.nextInt( 10 );
//            System.out.println( "密码：" + password );
//        }
         try {
            //查询用户是否存在
            String sql = "SELECT * FROM jo_user WHERE username='" + telenum + "'";
            List <Map <String, Object>> list = commonSvc.findForJdbc( sql );
            if (list.size() == 0) {
                // 注册电信用户
                Map <String, String> attr = new HashMap <>();
                attr.put( "dianxin", "18" );

                cmsUserMng.registerMember( telenum, null, password, RequestUtils.getIpAddr( request ), 18, false, new CmsUserExt(), attr );
                CmsUser cmsUser = cmsUserMng.findByUsername( telenum );
                JcUserGroup jcUserGroup1 = new JcUserGroup();
                jcUserGroup1.setGroupId( 18 );
                jcUserGroup1.setUserId( cmsUser.getId() );
                commonSvc.save( jcUserGroup1 );
                //发送短息提醒
                String uri = "http://send.18sms.com/msg/HttpBatchSendSM";//应用地址
                String account = "173kbz ";//示远账号
                String pswd = "B9pL6iPk";//示远密码
                String mobile = telenum;//手机号码，多个号码使用","分割
                String content = "感谢您使用沃得志愿填报系统,您的账号为:"+telenum+",密码为:"+password+",如有疑问,请致电全国统一服务热线400-9288-985,祝您报考顺利!";//短信内容，注意内容中的逗号请使用中文状态下的逗号
                content=new String(content.getBytes(),"UTF-8");
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
                map.put( "url" , "http://106.15.89.230:9092/wdedu " );
                map.put( "telenum" , telenum );
                map.put( "password" , password );
                map.put( "msg" , "手机号注册成功，请直接登录！！" );

            } else {
                map.put( "msg" , "该手机号已经注册，请直接登录！！" );
            }
        } catch (Exception e) {
            map.put( "msg" , "注册异常，请重新尝试！！" );
        }
        String json = JSON.toJSONString(map);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(json);
    }

}
