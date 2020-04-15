package com.utils;

import com.jeecms.wdedu.entity.TDMsgMq;
import com.jeecms.wdedu.entity.TDMsgTemplate;
import com.jeecms.wdedu.service.CommonSvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonsImpl implements Commons {
    @Autowired
    private CommonSvc commonService;

    @Override
    public void sendsmsByTemplate(Integer TemplateId, String phone, String... val) {
        TDMsgTemplate template = commonService.get(TDMsgTemplate.class, TemplateId);
        String content = "";
        content = String.format(template.getContent(), val);
        TDMsgMq msg = new TDMsgMq();
        msg.setSendState(false);
        msg.setCreateDate((Timestamp) new Date());
        msg.setContent(content);
        msg.setPhone(phone);
        msg.setType(0);
        commonService.save(msg);
    }

    @Override
    public boolean sendsms(String username, String sms_content) {
        boolean result = true;
        Map<String, String> param = new HashMap<String, String>();
        param.put("userid", "");
        param.put("account", "i45h6q");
        param.put("pswd", "Fm4h26K7");
        param.put("mobile", username);
        param.put("msg", sms_content);
        param.put("needstatus", "true");

        String url = "http://send.18sms.com/msg/HttpBatchSendSM";
//        try {
//            if (username.length() == 11 || username.length() == 12) {
//                String returnString = HttpUtils.getPostUrlString(url, param);
//                System.out.println("短信接口返回值：" + returnString);
//            }
//        }  catch (IOException e) {
//            result = false;
//            e.printStackTrace();
//        }
        return true;
    }


    public <T> Serializable save(T entity) {
        return commonService.save(entity);
    }

    public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
        return commonService.findForJdbc(sql, objs);
    }

    public String getUserName(Integer userId) {
        return commonService.getUserName(userId);
    }

}
