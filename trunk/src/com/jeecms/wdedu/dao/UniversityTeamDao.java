package com.jeecms.wdedu.dao;

import com.jeecms.wdedu.entity.TDataUniversityDetail;
import com.jeecms.wdedu.entity.TDataUniversityRank;

import java.util.List;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/18
 */
public interface UniversityTeamDao {
    List<TDataUniversityDetail> getList();

    List<TDataUniversityDetail> findUvinSzll(String univIds);
}
