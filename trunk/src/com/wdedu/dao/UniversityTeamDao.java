package com.wdedu.dao;


import com.jeecms.wdedu.entity.TDataUniversityDetail;

import java.util.List;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @Description: TODO
 * @date 2018/10/18
 */
public interface UniversityTeamDao {
    List<TDataUniversityDetail> getList();

    List<TDataUniversityDetail> findUvinSzll(String univIds);
}
