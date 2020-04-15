package com.wdedu.dao;

import com.jeecms.common.page.Pagination;
import com.jeecms.wdedu.entity.TDataUniversityRank;

import java.util.List;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @Description: TODO
 * @date 2018/10/17
 */
public interface UniversityRankDao {
    Pagination getPage(String univName, String grade, Integer provinceId, int pageNo, int pageSize);

    List<TDataUniversityRank> findUvinRank(String univIds);
}
