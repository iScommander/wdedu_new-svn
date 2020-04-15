package com.jeecms.wdedu.dao;

import java.util.List;

public interface TCeeEnrollMajorDao {

    List getPlanList(Integer univListId,Integer year);

    List getYearsAgo(Integer univListId, Integer year);
}
