package com.jeecms.wdedu.service.impl;

import com.jeecms.wdedu.dao.ActivityDao;
import com.jeecms.wdedu.entity.*;
import com.jeecms.wdedu.service.ActivitySvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fanyue
 * @ProjectName wdedu
 * @Description: 背景提升
 * @date 2018/10/19
 */
@Service
public class ActivitySvcImpl implements ActivitySvc {
    @Autowired
    private ActivityDao activityDao;
    @Override
    public List<TNActivity> tnActivityList(Integer userId) {
        return this.activityDao.activityList(userId);
    }

    @Override
    public List<TNAward> awardList(Integer userId) {
        return this.activityDao.awardList(userId);
    }

    @Override
    public List<TNUser> tNUserList(Integer userId) {
        return this.activityDao.tNUserList(userId);
    }

    @Override
    public List<TNReferee> tNRefereeList(Integer userId) {
        return this.activityDao.tNRefereeList(userId);
    }

    @Override
    public List<TNScore> tNScoreList(Integer userId) {
        return this.activityDao.tNScoreList(userId);
    }

    @Override
    public List<TNPatent> tNPatentList(Integer userId) {
        return this.activityDao.tNPatentList(userId);
    }

    @Override
    public List<TNEducation> tNEducationList(Integer userId) {
        return this.activityDao.tNEducationList(userId);
    }

    @Override
    public List<TNFamily> tNFamily(Integer userId) {
        return this.activityDao.tNFamily(userId);
    }

    @Override
    public List<TNSchool> tNSchoolList(Integer userId) {
        return this.activityDao.tNSchoolList(userId);
    }

    @Override
    public boolean updateUser(TNUser tnUser,String userAccount) {
        return this.activityDao.updateUser(tnUser,userAccount);
    }

    @Override
    public boolean saveUser(TNUser tnUser) {
        return this.activityDao.saveUser(tnUser);
    }

    @Override
    public boolean savePatent(TNPatent tnPatent) {
        return this.activityDao.savePatent(tnPatent);
    }

    @Override
    public boolean updatePatent(TNPatent tnPatent, String id) {
        return this.activityDao.updatePatent(tnPatent,id);
    }

    @Override
    public boolean saveScore(TNScore tnScore) {
        return this.activityDao.addScore(tnScore);
    }

    @Override
    public boolean updateScore(TNScore tnScore, String id) {
        return this.activityDao.updateScore(tnScore, id);
    }

    @Override
    public boolean updateEducation(TNEducation tnEducation, String id) {
        return this.activityDao.updateEducation(tnEducation,id);
    }

    @Override
    public boolean saveEductioin(TNEducation tnEducation) {
        return this.activityDao.saveEducation(tnEducation);
    }
}
