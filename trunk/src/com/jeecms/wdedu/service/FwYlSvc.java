package com.jeecms.wdedu.service;

import com.jeecms.wdedu.entity.TMgrSrvNode;
import com.jeecms.wdedu.entity.TMgrSrvOrder;

import java.util.List;

public interface FwYlSvc {
    List<TMgrSrvOrder> queryOrder(Integer userId);
    List<TMgrSrvNode> queryNode(Integer userId);
}
