package com.jeecms.wdedu.dao;

import com.jeecms.wdedu.entity.TMgrSrvNode;
import com.jeecms.wdedu.entity.TMgrSrvOrder;

import java.util.List;

public interface FwYlDao {
    List<TMgrSrvOrder> queryOrder(Integer userId);
    List<TMgrSrvNode> queryBeijing(Integer userId);
}
