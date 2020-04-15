package com.jeecms.wdedu.service.impl;

import com.jeecms.wdedu.dao.FwYlDao;
import com.jeecms.wdedu.entity.TMgrSrvNode;
import com.jeecms.wdedu.entity.TMgrSrvOrder;
import com.jeecms.wdedu.service.FwYlSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FwYlSvcImpl implements FwYlSvc {

    @Autowired
    public FwYlDao fwYlDao;

    @Override
    public List<TMgrSrvOrder> queryOrder(Integer userId) {
        return this.fwYlDao.queryOrder(userId);
    }

    @Override
    public List<TMgrSrvNode> queryNode(Integer userId) {
        return this.fwYlDao.queryBeijing(userId);
    }
}
