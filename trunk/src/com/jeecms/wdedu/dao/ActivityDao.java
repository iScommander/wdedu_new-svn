package com.jeecms.wdedu.dao;

import com.jeecms.wdedu.entity.*;

import java.util.List;

public interface ActivityDao {
    List<TNActivity> activityList(Integer userId);
    List<TNAward> awardList(Integer userId);
    List<TNUser> tNUserList(Integer userId);
    List<TNReferee> tNRefereeList(Integer userId);
    List<TNScore> tNScoreList(Integer userId);
    List<TNPatent> tNPatentList(Integer userId);
    List<TNEducation> tNEducationList(Integer userId);
    List<TNFamily> tNFamily(Integer userId);
    List<TNSchool> tNSchoolList(Integer userId);
    boolean updateUser(TNUser tnUser,String userAccount);
    boolean saveUser(TNUser tnUser);
    boolean savePatent(TNPatent tnPatent);
    boolean updatePatent(TNPatent tnPatent,String id);
    boolean addScore(TNScore tnScore);
    boolean updateScore(TNScore tnScore,String id);
    boolean updateEducation(TNEducation tnEducation,String id);
    boolean saveEducation(TNEducation tnEducation);
}
