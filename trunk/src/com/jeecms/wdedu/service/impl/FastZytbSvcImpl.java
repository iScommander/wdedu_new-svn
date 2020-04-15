package com.jeecms.wdedu.service.impl;

import com.jeecms.wdedu.dao.FastZytbDao;
import com.jeecms.wdedu.service.FastZytbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C),
 * FileName:
 * Author:
 * Date:
 * Description: //模块目的、功能描述
 * History: //修改记录
 * &lt;author&gt;      &lt;time&gt;      &lt;version&gt;    &lt;desc&gt;
 * 修改人姓名             修改时间            版本号                  描述
 **/
@Service
public class FastZytbSvcImpl implements FastZytbSvc {

    @Autowired
    private FastZytbDao fastZytbDao;


    @Override
    public int update(String applicationId, String batchId, String univ_province_id, String univ_is985, String univ_is211,
                      String univ_isIndependence, String univ_isFirstRate, String univ_isKeylab, String univ_isExcellent,
                      String univ_type, String major_second, String advanced_univ_num, String stable_univ_num, String backward_univ_num, String intent_univ, String avoid_univ,int userId,int tbType) {
        int num = fastZytbDao.update(  applicationId,  batchId,  univ_province_id,
                 univ_is985,  univ_is211,  univ_isIndependence,  univ_isFirstRate,  univ_isKeylab,  univ_isExcellent,
                 univ_type,  major_second,  advanced_univ_num,  stable_univ_num,  backward_univ_num,  intent_univ,  avoid_univ ,userId,tbType);
        return num;
    }
}

