package com.jeecms.wdedu.dao;

import com.jeecms.wdedu.entity.TDataMajor;

import java.util.List;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/31
 */
public interface TDataMajorDao {
    List getMajorList(String majorId);
}
