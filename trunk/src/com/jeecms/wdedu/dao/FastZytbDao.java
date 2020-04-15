package com.jeecms.wdedu.dao;

import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

public interface FastZytbDao {

    int update(  String applicationId, String batchId, String univ_province_id,
               String univ_is985, String univ_is211, String univ_isIndependence, String univ_isFirstRate, String univ_isKeylab, String univ_isExcellent,
               String univ_type, String major_second, String advanced_univ_num, String stable_univ_num, String backward_univ_num, String intent_univ, String avoid_univ, int userId,int tbType);




}
