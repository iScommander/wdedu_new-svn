package com.jeecms.wdedu.dao.impl;

import com.jeecms.wdedu.dao.FastZytbDao;
import com.jeecms.wdedu.service.CommonSvc;
import com.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

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
public class FastZytbDaoImpl implements FastZytbDao {

    @Autowired
    private CommonSvc commonSvc;


    @Override
    public int update(String applicationId, String batchId, String univ_province_id, String univ_is985,
                      String univ_is211, String univ_isIndependence, String univ_isFirstRate,
                      String univ_isKeylab, String univ_isExcellent, String univ_type,
                      String major_second, String advanced_univ_num, String stable_univ_num,
                      String backward_univ_num, String intent_univ, String avoid_univ, int userId, int tbType) {

        StringBuffer sql = new StringBuffer("UPDATE `t_cee_applications_require` set ");
        if (tbType == 2) {
            if (StringUtil.isNotEmpty(univ_province_id) && !("null").equals(univ_province_id)) {
                if (univ_province_id.contains(",")) {
                    univ_province_id = univ_province_id.replace(",", "|");
                }

                sql.append("intent_province_id = '" + univ_province_id + "',");
            } else {
                sql.append("intent_province_id = '0',");
            }

        }
        if (tbType == 3) {
            if (StringUtil.isNotEmpty(univ_type) && !("null").equals(univ_type)) {
                if (univ_type.contains(",")) {
                    univ_type = univ_type.replace(",", "|");
                }
                sql.append("intent_univ_type = '" + univ_type + "',");
            } else {
                sql.append("intent_univ_type = '0',");
            }
            if (StringUtil.isNotEmpty(univ_is211) && !("null").equals(univ_is211)) {
                sql.append("is211 = '" + univ_is211 + "',");
            } else {
                sql.append("is211 = '0',");
            }
            if (StringUtil.isNotEmpty(univ_is985) && !("null").equals(univ_is985)) {
                sql.append("is985 = '" + univ_is985 + "',");
            } else {
                sql.append("is985 = '0',");
            }


            if("0".equals(univ_isFirstRate) && univ_isFirstRate.equals(univ_isIndependence)){
                sql.append("isFirstRate = '0',");
            }else if("1".equals(univ_isFirstRate) && univ_isFirstRate.equals(univ_isIndependence)){
                sql.append("isFirstRate = '1',");
            }else if("2".equals(univ_isFirstRate) && univ_isFirstRate.equals(univ_isIndependence)){
                sql.append("isFirstRate = '2',");
            }else{
                if (StringUtil.isNotEmpty(univ_isIndependence) && !("null").equals(univ_isIndependence) && !"0".equals(univ_isIndependence)) {
                    sql.append("isFirstRate = '1',");
                } else {
                    if (StringUtil.isNotEmpty(univ_isFirstRate) && !("null").equals(univ_isFirstRate) && !"0".equals(univ_isFirstRate)) {
                        sql.append("isFirstRate = '2',");
                    } else {
                        sql.append("isFirstRate = '0',");
                    }
                }
            }
        }


        if (tbType == 4) {
            if (StringUtil.isNotEmpty(major_second) && !("null").equals(major_second)) {
                if (major_second.contains(",")) {
                    major_second = major_second.replace(",", "|");
                }
                sql.append("intent_major_id = '" + major_second + "',");
            } else {
                sql.append("intent_major_id = '0',");
            }
        }
        if (tbType == 5) {
            if (StringUtil.isNotEmpty(advanced_univ_num) && !("null").equals(advanced_univ_num)) {
                sql.append("advanced_univ_num = '" + advanced_univ_num + "',");
            } else {
                sql.append("advanced_univ_num = '0',");
            }
            if (StringUtil.isNotEmpty(stable_univ_num) && !("null").equals(stable_univ_num)) {
                sql.append("stable_univ_num = '" + stable_univ_num + "',");
            } else {
                sql.append("stable_univ_num = '0',");
            }
            if (StringUtil.isNotEmpty(backward_univ_num) && !("null").equals(backward_univ_num)) {
                sql.append("backward_univ_num = '" + backward_univ_num + "',");
            } else {
                sql.append("backward_univ_num = ' 0',");
            }
        }


//        if (StringUtil.isNotEmpty(intent_univ) && !("null").equals(intent_univ)) {
//            sql.append("intent_univ = '" + intent_univ + "',");
//        } else {
//            sql.append("intent_univ = '',");
//        }
//        if (StringUtil.isNotEmpty(avoid_univ) && !("null").equals(avoid_univ)) {
//            sql.append("avoid_univ = '" + avoid_univ + "',");
//        } else {
//            sql.append("avoid_univ = '',");
//        }
        sql.append(" batch_id='" + batchId + "' WHERE stu_user_id = '" + userId + "' and  application_id = '" + applicationId + "' and batch_id = '" + batchId + "'  ");

        int num = commonSvc.executeSql(sql.toString());

        return num;
    }
}
