package com.jeecms.wdedu.dao;

import com.jeecms.common.page.Pagination;

import java.util.List;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/18
 */
public interface ProfessionAndLabDao {
    Pagination getPage(String univName, String majorName, int pageNo, int pageSize);

    List findUvinCount(String univIds);
}
