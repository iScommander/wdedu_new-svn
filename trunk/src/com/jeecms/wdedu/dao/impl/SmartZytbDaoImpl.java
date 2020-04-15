package com.jeecms.wdedu.dao.impl;

import com.jeecms.wdedu.dao.SmartZytbDao;
import com.jeecms.wdedu.entity.*;
import com.jeecms.wdedu.service.CommonSvc;
import com.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;
import java.util.regex.Pattern;

public class SmartZytbDaoImpl implements SmartZytbDao {

    @Autowired
    private CommonSvc commonSvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public TCeeBatch getBatch(Integer batchYear, Integer provinceId, Integer majorTypeId, Integer batchId) {

        return commonSvc.singleResult(" FROM TCeeBatch \n" +
                "WHERE YEAR = " + batchYear + "\n" +
                "AND provinceId = " + provinceId + "\n" +
                "AND majorTypeId = " + majorTypeId + "\n" +
                "AND batchId = " + batchId + "\n");
    }

    @Override
    public TCeeApplicationsRequire getApplicationRequire(String applicationId, Integer batchId) {
        return commonSvc.singleResult(" FROM  TCeeApplicationsRequire\n" +
                "WHERE applicationId = " + applicationId + " " +
                "AND batchId = " + batchId + " ");

    }

    @Override
    public int getBatchMaxScore(Integer planYear, Integer provinceId, Integer batchId, Integer majorTypeId) {

        return commonSvc.singleResult("SELECT MAX(IFNULL(scoreLow1,0)) AS batchMaxScore\n" +
                "FROM TCeeEnrollUnivList \n" +
                "WHERE YEAR = " + planYear + "\n" +
                "AND provinceId = " + provinceId + "\n" +
                "AND batchId = " + batchId + "\n" +
                "AND  majorTypeId = " + majorTypeId + "");

    }

    @Override
    public int getRank(Integer majorTypeId, Integer provinceId, Integer scoreYear, Integer score) {
        return commonSvc.singleResult("SELECT rank FROM  TCeeScoreRank \n" +
                "WHERE majorTypeId = " + majorTypeId + " \n" +
                "AND  province_id = " + provinceId + " \n" +
                "AND YEAR = " + scoreYear + "\n" +
                "AND score = " + score + " ");
    }

    //    获取包含意向专业的院校信息
    public String getMBUALL(String intentMajorId, Integer provinceId, Integer batchId, Integer planYear, Integer majorTypeId, String intentProvinceId, String intentUnivType, Integer is211, Integer is985, Integer isFirstRate, String univName, String subjectLevel) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT DISTINCT major_type_id,batch_id,univ_id FROM t_cee_enroll_major_list b\n" +
                "WHERE b.plan_or_history =1 \n" +
                "AND YEAR = " + planYear + " \n" +
                "AND b.province_id = " + provinceId + " \n" +
                "AND b.major_type_id = " + majorTypeId + "\n" +
                "AND b.batch_id = " + batchId + "  \n" +
                "AND b.data_type =1 ");

        if (StringUtils.isBlank(intentMajorId)) {
            sql.append(" and major_id LIKE '%' OR major_id IS NULL ");
        } else {
            sql.append(" and LEFT(major_id,4) REGEXP " + intentMajorId + " ");
        }

        List<Map<String, Object>> MBU = commonSvc.findForJdbc(String.valueOf(sql));

        String MBUALL = "";
        for (int i = 0; i < MBU.size(); i++) {
            String aa = String.valueOf(MBU.get(i).get("major_type_id"));
            String bb = String.valueOf(MBU.get(i).get("batch_id"));
            String cc = String.valueOf(MBU.get(i).get("univ_id"));
            String MBUStr = "(" + aa + "," + bb + ",'" + cc + "')";
            if (i != (MBU.size() - 1)) {
                MBUStr += ",";
            }
            MBUALL += MBUStr;
        }
        return MBUALL;
    }

    //获取意向院校及避让院校
    public List<TCeeEnrollUnivList> getIAUniv(Integer planYear, Integer provinceId, Integer majorTypeId, Integer batchId, String intentUniv, String avoidUniv) {

        StringBuffer hql = new StringBuffer();
        hql.append(" FROM TCeeEnrollUnivList a \n" +
                "WHERE a.year = " + planYear + " \n" +
                "AND a.provinceId = " + provinceId + "\n" +
                "AND a.majorTypeId = " + majorTypeId + " \n" +
                "AND a.batchId = " + batchId + " ");
        if (StringUtils.isNotBlank(intentUniv)) {
            hql.append(" and a.id in ( " + intentUniv.replace("|", ",") + " ) ");
        } else {
            hql.append(" and a.id < 0 ");
        }

        if (StringUtils.isNotBlank(avoidUniv)) {

            hql.append(" and a.id NOT IN ( " + avoidUniv.replace("|", ",") + " ) ");
        } else {
            hql.append(" and a.id < 0 ");
        }
        List<TCeeEnrollUnivList> IAUniv = commonSvc.singleResultList(hql.toString());
        return IAUniv;
    }

    //根据其他需求条件筛选院校
    public List<TCeeEnrollUnivList> getOtherUniv(Integer planYear, Integer provinceId, Integer majorTypeId, Integer batchId, String intentProvinceId, String intentUnivType, Integer is985, Integer is211, Integer isFirstRate, String univNames, Integer subjectValue, String intentMajorId) {

        StringBuffer hql = new StringBuffer();
        hql.append(" FROM TCeeEnrollUnivList a \n" +
                "WHERE a.year = " + planYear + " \n" +
                "AND a.provinceId = " + provinceId + "\n" +
                "AND a.majorTypeId = " + majorTypeId + " \n" +
                "AND a.batchId = " + batchId + " ");
//        意向省份
        if (StringUtils.isNotBlank(intentProvinceId)) {
            String intentProvinces = intentProvinceId.replace("|", ",");
            hql.append(" AND a.univProvince IN ( " + intentProvinces + " )\n");
        }
//      意向院校类型
        if (StringUtils.isNotBlank(intentUnivType)) {
            String intentUnivTypes = "\'" + intentUnivType.replace("|", "','") + "\'";
            hql.append(" AND a.univType IN ( " + intentUnivTypes + " )\n ");
        }

        if (is985 != null || is211 != null || isFirstRate != null) {
            hql.append(" AND ( ");
//        985
            if (is985 != null) {
                hql.append(" a.univIs985 = 1  ");
            }
//        211
            if (is211 != null) {
                if (is985 != null) {
                    hql.append(" AND a.univIs211 = 1  ");
                } else {
                    hql.append(" a.univIs211 = 1 ");
                }
            }
//        双一流类型 // Alltodo: 2019/11/25 双一流类型未完成
            if (isFirstRate != null) {
                if (is985 != null || is211 != null) {
                    hql.append(" AND a.univIsFirstRateUniv = " + isFirstRate + " ");
                } else {
                    hql.append(" a.univIsFirstRateUniv = " + isFirstRate + " ");
                }
            }
            hql.append(" ) ");
        }
//        输入搜索院校
        if (StringUtils.isNotBlank(univNames)) {
            hql.append(" AND  LOCATE('" + univNames + "',a.univName) >0 ");
        }
//        选科科目等级
//        if (provinceId == 100) {
//            if (subjectValue != null) {
//                hql.append(" AND LEFT(a.univTestLevel,1) + RIGHT(a.univTestLevel,1) >= " + subjectValue + " ");
//            }
//        }

        if (StringUtils.isNotBlank(intentMajorId)) {
            String MBUALL = getMBUALL(intentMajorId, provinceId, batchId, planYear, majorTypeId, null, null, null, null, null, null, null);
            hql.append(" AND (a.majorTypeId,a.batchId,a.univId) IN ( " + MBUALL + " ) ");
        }

        hql.append(" ORDER BY a.rankScoreLow1 ASC , a.rankScoreLow2 ASC , a.rankScoreLow2 ASC \n  ");

        List<TCeeEnrollUnivList> OtherUniv = commonSvc.singleResultList(hql.toString());
        return OtherUniv;
    }

    //    判断选测科目条件
    @Override
    public List<TCeeEnrollUnivListMiddle> getLastUniv(Integer upperScoreRank, Integer lowerScoreRank, TCeeApplications tCeeApplications, TCeeApplicationsRequire tCeeApplicationsRequire) {

//        TCeeApplications tCeeApplications = commonSvc.get(TCeeApplications.class, 1050039);
//        TCeeApplicationsRequire tCeeApplicationsRequire = commonSvc.get(TCeeApplicationsRequire.class, 1207);
        Integer planYear = tCeeApplications.getYear();
        Integer provinceId = tCeeApplications.getProvinceId();
        Integer majorTypeId = tCeeApplications.getMajorTypeId();
        Integer batchId = tCeeApplicationsRequire.getBatchId();
        String intentProvinceId = tCeeApplicationsRequire.getIntentProvinceId();
        String intentUnivType = tCeeApplicationsRequire.getIntentUnivType();
        Integer is985 = tCeeApplicationsRequire.getIs985();
        Integer is211 = tCeeApplicationsRequire.getIs211();
        Integer isFirstRate = tCeeApplicationsRequire.getIsFirstRate();
        String intentMajorId = tCeeApplicationsRequire.getIntentMajorId();
        String intentUniv = tCeeApplicationsRequire.getIntentUniv();
        String avoidUniv = tCeeApplicationsRequire.getAvoidUniv();

        List<TCeeEnrollUnivList> beginUnivList = new ArrayList<>();
        List<TCeeEnrollUnivList> IAUnivList = getIAUniv(planYear, provinceId, majorTypeId, batchId, intentUniv, avoidUniv);
        List<TCeeEnrollUnivList> otherUnivList = getOtherUniv(planYear, provinceId, majorTypeId, batchId, intentProvinceId, intentUnivType, is985, is211, null, null, null, intentMajorId);
        List<TCeeEnrollUnivListMiddle> tCeeEnrollUnivListMiddles = new ArrayList<>();


        for (TCeeEnrollUnivList tCeeEnrollUniv : IAUnivList) {
            beginUnivList.add(tCeeEnrollUniv);
        }
        for (TCeeEnrollUnivList tCeeEnrollUnivList : otherUnivList) {
            beginUnivList.add(tCeeEnrollUnivList);
        }

        Integer userSubjectValue = 0;
        //        用户选科等级
        String userSubjectLevel = tCeeApplications.getSubjectsLevel();
        if (provinceId == 17) {
            if (com.utils.StringUtils.isNotBlank(userSubjectLevel)) {
                userSubjectLevel = userSubjectLevel.replace(",", "");
                userSubjectValue = userSubjectLevel.indexOf(1) + userSubjectLevel.lastIndexOf(1);
            }
        }

        for (TCeeEnrollUnivList tCeeEnrollUniv : beginUnivList) {
            Integer rank_score_low1 = 999999;
            if (tCeeEnrollUniv.getRankScoreLow1() != null) {
                rank_score_low1 = tCeeEnrollUniv.getRankScoreLow1();
            }
            Integer rank_score_low2 = 999999;
            if (tCeeEnrollUniv.getRankScoreLow2() != null) {
                rank_score_low2 = tCeeEnrollUniv.getRankScoreLow2();
            }
            Integer rank_score_low3 = 999999;
            if (tCeeEnrollUniv.getRankScoreLow3() != null) {
                rank_score_low3 = tCeeEnrollUniv.getRankScoreLow3();
            }
            if ((rank_score_low1 <= lowerScoreRank && rank_score_low1 > upperScoreRank) || (rank_score_low2 <= lowerScoreRank && rank_score_low2 > upperScoreRank) || (rank_score_low3 <= lowerScoreRank && rank_score_low3 > upperScoreRank)) {
                if (provinceId == 17) {
//                院校选科等级要求
                    Integer subjectValue = 0;
                    if (StringUtils.isNotBlank(String.valueOf(tCeeEnrollUniv.getUnivTestLevel()))) {
                        String subjectLevel = String.valueOf(tCeeEnrollUniv.getUnivTestLevel());
                        subjectValue = subjectLevel.indexOf(1) + subjectLevel.lastIndexOf(1);
                    }
                    if (subjectValue >= userSubjectValue) {
                        TCeeEnrollUnivListMiddle tCeeEnrollUnivListMiddle = new TCeeEnrollUnivListMiddle();
                        tCeeEnrollUnivListMiddle.childSet(tCeeEnrollUniv);
                        tCeeEnrollUnivListMiddles.add(tCeeEnrollUnivListMiddle);
                    }
                } else {
                    TCeeEnrollUnivListMiddle tCeeEnrollUnivListMiddle = new TCeeEnrollUnivListMiddle();
                    tCeeEnrollUnivListMiddle.childSet(tCeeEnrollUniv);
                    tCeeEnrollUnivListMiddles.add(tCeeEnrollUnivListMiddle);
                }
            }
        }

        return tCeeEnrollUnivListMiddles;
    }

    //    附加冲稳保规则
    @Override
    public List<TCeeEnrollUnivListMiddle> getUnivPSK(List<TCeeEnrollUnivListMiddle> tCeeEnrollUnivListMiddles, Integer gradeRankP, Integer gradeRankS, Integer gradeRankK) {

        for (TCeeEnrollUnivListMiddle tCeeEnrollUnivListMiddle : tCeeEnrollUnivListMiddles) {

            Integer rank1 = 0;
            Integer rank2 = 0;
            Integer rank3 = 0;
            if (tCeeEnrollUnivListMiddle.getRankScoreLow1() != null) {
                rank1 = tCeeEnrollUnivListMiddle.getRankScoreLow1();
            }
            if (tCeeEnrollUnivListMiddle.getRankScoreLow1() != null) {
                rank2 = tCeeEnrollUnivListMiddle.getRankScoreLow2();
            }
            if (tCeeEnrollUnivListMiddle.getRankScoreLow1() != null) {
                rank3 = tCeeEnrollUnivListMiddle.getRankScoreLow3();
            }
            String PSK = doPSK(rank1, rank2, rank3, gradeRankP, gradeRankS, gradeRankK);
            tCeeEnrollUnivListMiddle.setpskSet(PSK);
        }
        return tCeeEnrollUnivListMiddles;

    }

    //    冲稳保判定规则
    public String doPSK(Integer rank1, Integer rank2, Integer rank3, Integer gradeRankP, Integer gradeRankS, Integer gradeRankK) {

        Integer pskRank1 = 3;
        Integer pskRank2 = 3;
        Integer pskRank3 = 3;

        if (rank1 != null || rank2 != null || rank3 != null) {
            if (rank1 != null) {
                if (rank1 <= gradeRankP) {
                    pskRank1 = 1;
                } else if (rank1 <= gradeRankS && rank1 > gradeRankP) {
                    pskRank1 = 2;
                } else if (rank1 > gradeRankS) {
                    pskRank1 = 3;
                }
            }

            if (rank2 != null) {
                if (rank2 <= gradeRankP) {
                    pskRank2 = 1;
                } else if (rank2 <= gradeRankS && rank2 > gradeRankP) {
                    pskRank2 = 2;
                } else if (rank2 > gradeRankS) {
                    pskRank2 = 3;
                }
            }

            if (rank3 != null) {
                if (rank3 <= gradeRankP) {
                    pskRank3 = 1;
                } else if (rank3 <= gradeRankS && rank3 > gradeRankP) {
                    pskRank3 = 2;
                } else if (rank3 > gradeRankS) {
                    pskRank3 = 3;
                }
            }
        }

        String result = "保一保";
        switch (Math.min(pskRank1, pskRank2)) {
            case 1:
                result = "冲一冲";
                break;
            case 2:
                result = "稳一稳";
                break;
            case 3:
                result = "保一保";
                break;
            default:
                result = "冲一冲";
        }
        return result;
    }


    //    生成处理后的用户意向univ_list_user
    @Override
    public void setUnivListUser(List<TCeeEnrollUnivListMiddle> tCeeEnrollUnivListMiddles, TCeeApplications tCeeApplications, TCeeApplicationsRequire tCeeApplicationsRequire) {

        String univListIds = "";
        for (int i = 0; i < tCeeEnrollUnivListMiddles.size(); i++) {
            String univListId = String.valueOf(tCeeEnrollUnivListMiddles.get(i).getId());
            String univListIdStr = " '" + univListId + "' ";
            if (i != (tCeeEnrollUnivListMiddles.size() - 1)) {
                univListIdStr += ",";
            }
            univListIds += univListIdStr;
        }

//        选出用户条件专业list，生成univ_list_user中间表
        StringBuffer hql = new StringBuffer();
        hql.append(" FROM TCeeEnrollFillList\n" +
                "WHERE YEAR = " + tCeeApplications.getYear() + "\n" +
                "AND provinceId = " + tCeeApplications.getProvinceId() + "\n" +
                "AND majorTypeId= " + tCeeApplications.getMajorTypeId() + "\n" +
                "AND batchId = " + tCeeApplicationsRequire.getBatchId() + " \n" +
//                "AND planOrHistory = 1\n" +
                "AND dataType = 1 \n");
        if (StringUtils.isNotBlank(univListIds)) {
            hql.append("AND univListId IN ( " + univListIds + " )");
        }
        List<TCeeEnrollFillList> UMajorList = commonSvc.singleResultList(hql.toString());

        List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsers = new ArrayList<TCeeEnrollUnivListUser>();
        for (TCeeEnrollFillList tCeeEnrollFillList : UMajorList) {
            TCeeEnrollUnivListUser tCeeEnrollUnivListUser = new TCeeEnrollUnivListUser();
            tCeeEnrollUnivListUser.setApplicationId(tCeeApplications.getId());
            tCeeEnrollUnivListUser.setYear(tCeeApplications.getYear());
            tCeeEnrollUnivListUser.setProvinceId(tCeeApplications.getProvinceId());
            tCeeEnrollUnivListUser.setMajorTypeId(tCeeApplications.getMajorTypeId());
            tCeeEnrollUnivListUser.setBatchId(tCeeApplicationsRequire.getBatchId());

            tCeeEnrollUnivListUser.setUnivListId(tCeeEnrollFillList.getUnivListId());
            tCeeEnrollUnivListUser.setUnivId(tCeeEnrollFillList.getUnivId());
            tCeeEnrollUnivListUser.setUnivCode(tCeeEnrollFillList.getUnivCode());
            tCeeEnrollUnivListUser.setUnivName(tCeeEnrollFillList.getUnivName());

            tCeeEnrollUnivListUser.setMajorListId(tCeeEnrollFillList.getId());
            tCeeEnrollUnivListUser.setMajorId(tCeeEnrollFillList.getMajorId());
            tCeeEnrollUnivListUser.setMajorCode(tCeeEnrollFillList.getMajorCode());
            tCeeEnrollUnivListUser.setMajorName(tCeeEnrollFillList.getMajorName());
            tCeeEnrollUnivListUser.setChooseFlag(0);

            tCeeEnrollUnivListUsers.add(tCeeEnrollUnivListUser);
        }

//        设置院校历史数据
        for (TCeeEnrollUnivListUser tCeeEnrollUnivListUser11 : tCeeEnrollUnivListUsers) {
            for (TCeeEnrollUnivListMiddle tCeeEnrollUnivListMiddle11 : tCeeEnrollUnivListMiddles) {

                int planYear1 = tCeeEnrollUnivListMiddle11.getYear();
                int provinceId1 = tCeeEnrollUnivListMiddle11.getProvinceId();
                int majorTypeId1 = tCeeEnrollUnivListMiddle11.getMajorTypeId();
                int batchId1 = tCeeEnrollUnivListMiddle11.getBatchId();
                int univId1 = tCeeEnrollUnivListMiddle11.getUnivId();
                String univCode1 = tCeeEnrollUnivListMiddle11.getUnivCode();
                int univListId1 = tCeeEnrollUnivListMiddle11.getId();

                int planYear2 = tCeeEnrollUnivListUser11.getYear();
                int provinceId2 = tCeeEnrollUnivListUser11.getProvinceId();
                int majorTypeId2 = tCeeEnrollUnivListUser11.getMajorTypeId();
                int batchId2 = tCeeEnrollUnivListUser11.getBatchId();
                int univId2 = Integer.parseInt(tCeeEnrollUnivListUser11.getUnivId());
                String univCode2 = tCeeEnrollUnivListUser11.getUnivCode();
                int univListId2 = tCeeEnrollUnivListUser11.getUnivListId();

                if (planYear1 == planYear2 && provinceId1 == provinceId2 && majorTypeId1 == majorTypeId2 && batchId1 == batchId2 && univId1 == univId2 && univListId1 == univListId2 && univCode1.equals(univCode2)) {
                    tCeeEnrollUnivListUser11.setPskSet(tCeeEnrollUnivListMiddle11.getpskSet());

                    tCeeEnrollUnivListUser11.setUnivLowScore1(tCeeEnrollUnivListMiddle11.getScoreLow1());
                    tCeeEnrollUnivListUser11.setUnivLowScore2(tCeeEnrollUnivListMiddle11.getScoreLow2());
                    tCeeEnrollUnivListUser11.setUnivLowScore3(tCeeEnrollUnivListMiddle11.getScoreLow3());

                    tCeeEnrollUnivListUser11.setUnivRankLowScore1(tCeeEnrollUnivListMiddle11.getRankScoreLow1());
                    tCeeEnrollUnivListUser11.setUnivRankLowScore2(tCeeEnrollUnivListMiddle11.getRankScoreLow2());
                    tCeeEnrollUnivListUser11.setUnivRankLowScore3(tCeeEnrollUnivListMiddle11.getRankScoreLow3());
                }
            }

            //                设置冲稳保值
            if (StringUtils.isNotBlank(tCeeEnrollUnivListUser11.getPskSet())) {
                String pskSet = tCeeEnrollUnivListUser11.getPskSet();
                if (pskSet.equals("冲一冲")) {
                    tCeeEnrollUnivListUser11.setPskSetValue(1);
                } else if (pskSet.equals("稳一稳")) {
                    tCeeEnrollUnivListUser11.setPskSetValue(2);
                } else if (pskSet.equals("保一保")) {
                    tCeeEnrollUnivListUser11.setPskSetValue(3);
                }
            } else {
                tCeeEnrollUnivListUser11.setPskSet("冲一冲");
                tCeeEnrollUnivListUser11.setPskSetValue(1);
            }
        }

        // Alltodo: 2019/11/28 major_list 更改为 fill_list 历年专业数据直接取值 以下逻辑无效 需更改
        /*
        String PMBUUUStr = "";
        for (int i = 0; i < tCeeEnrollUnivListMiddles.size(); i++) {
            String P = String.valueOf(tCeeEnrollUnivListMiddles.get(i).getProvinceId());
            String M = String.valueOf(tCeeEnrollUnivListMiddles.get(i).getMajorTypeId());
            String B = String.valueOf(tCeeEnrollUnivListMiddles.get(i).getBatchId());
            String U1 = String.valueOf(tCeeEnrollUnivListMiddles.get(i).getUnivId());
            String U2 = tCeeEnrollUnivListMiddles.get(i).getUnivCode();
            String U3 = tCeeEnrollUnivListMiddles.get(i).getUnivName();
            String PMBUUU = " ('" + P + "','" + M + "','" + B + "', '" + U1 + "','" + U2 + "','" + U3 + "') ";
            if (i != (tCeeEnrollUnivListMiddles.size() - 1)) {
                PMBUUU += ",";
            }
            PMBUUUStr += PMBUUU;
        }
        int planYear = tCeeApplications.getYear();
        StringBuffer hqlScore = new StringBuffer();
        hqlScore.append(" FROM TCeeEnrollHistory\n" +
                "WHERE historyType = 1 \n" +
                "AND YEAR IN (" + planYear + "," + (planYear - 1) + "," + (planYear - 2) + ") \n");
        hqlScore.append("AND (province_id,major_type_id,batch_id,univ_id,univ_code,univ_name) IN \n" +
                "( " + PMBUUUStr + " )\n");
        List<TCeeEnrollHistory> tCeeEnrollHistories = commonSvc.singleResultList(hqlScore.toString());
        for (TCeeEnrollUnivListUser tCeeEnrollUnivListUser22 : tCeeEnrollUnivListUsers) {
            for (TCeeEnrollHistory tCeeEnrollHistory11 : tCeeEnrollHistories) {
                int planYear12 = tCeeEnrollHistory11.getYear();
                int provinceId12 = tCeeEnrollHistory11.getProvinceId();
                int majorTypeId12 = tCeeEnrollHistory11.getMajorTypeId();
                int batchId12 = tCeeEnrollHistory11.getBatchId();
                int univId12 = Integer.parseInt(tCeeEnrollHistory11.getUnivId());
                String univCode12 = tCeeEnrollHistory11.getUnivCode();
                String univName12 = tCeeEnrollHistory11.getUnivName();
                String majorCode12 = tCeeEnrollHistory11.getMajorCode();
                String majorName12 = tCeeEnrollHistory11.getMajorName();

                int planYear22 = tCeeEnrollUnivListUser22.getYear();
                int provinceId22 = tCeeEnrollUnivListUser22.getProvinceId();
                int majorTypeId22 = tCeeEnrollUnivListUser22.getMajorTypeId();
                int batchId22 = tCeeEnrollUnivListUser22.getBatchId();
                int univId22 = Integer.parseInt(tCeeEnrollUnivListUser22.getUnivId());
                String univCode22 = tCeeEnrollUnivListUser22.getUnivCode();
                String univName22 = tCeeEnrollUnivListUser22.getUnivName();
                String majorCode22 = tCeeEnrollUnivListUser22.getMajorCode();
                String majorName22 = tCeeEnrollUnivListUser22.getMajorName();

                if (provinceId12 == provinceId22 && majorTypeId12 == majorTypeId22 && batchId12 == batchId22 && univId12 == univId22) {
                    if (univCode12.equals(univCode22) && univName12.equals(univName22)) {
                        if (majorName22.equals(majorName12) || Pattern.matches(majorName22.replace("(","\\(").replace(")","\\)"), majorName12.replace("(","\\(").replace(")","\\)"))) {
                            if (planYear12 == planYear22) {
                                tCeeEnrollUnivListUser22.setLowScore1(tCeeEnrollHistory11.getLowScore());
                                tCeeEnrollUnivListUser22.setRankLowScore1(tCeeEnrollHistory11.getRankLowScore());
                            } else if (planYear12 == (planYear22 - 1)) {
                                tCeeEnrollUnivListUser22.setLowScore2(tCeeEnrollHistory11.getLowScore());
                                tCeeEnrollUnivListUser22.setRankLowScore2(tCeeEnrollHistory11.getRankLowScore());
                            } else if (planYear12 == (planYear22 - 2)) {
                                tCeeEnrollUnivListUser22.setLowScore3(tCeeEnrollHistory11.getLowScore());
                                tCeeEnrollUnivListUser22.setRankLowScore3(tCeeEnrollHistory11.getRankLowScore());
                            }
                        }
                    }
                }
            }


        }
        */

//        清空之前的数据
        String deletSqlUser = " DELETE FROM t_cee_enroll_univ_list_user\n" +
                "WHERE application_id = " + tCeeApplications.getId() + " AND batch_id = " + tCeeApplicationsRequire.getBatchId() + " ";

        commonSvc.executeSql(deletSqlUser);

        // Alltodo: 2020/2/12  处理数据量大保存很慢
        commonSvc.batchSaveOrUpdate(tCeeEnrollUnivListUsers);

//        设置院校初始排序
        String sortSql = "SELECT DISTINCT application_id,batch_id,univ_code,univ_id\n" +
                "FROM t_cee_enroll_univ_list_user b  \n" +
                "WHERE b.application_id = " + tCeeApplications.getId() + "  \n" +
                "AND b.batch_id = " + tCeeApplicationsRequire.getBatchId() + " \n" +
                "ORDER BY IFNULL(b.univ_rank_low_score_1,999999) ASC ,IFNULL(b.univ_rank_low_score_2,999999) ASC,IFNULL(b.univ_rank_low_score_3,999999) ASC,psk_set_value ASC ";

        List<Map<String, Object>> sortList = commonSvc.findForJdbc(sortSql);

        String sortUnivHql = "FROM TCeeEnrollUnivListUser\n" +
                "WHERE applicationId = " + tCeeApplications.getId() + " AND batchId = " + tCeeApplicationsRequire.getBatchId() + " ";
        List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsersUnivSort = commonSvc.findByQueryString(sortUnivHql);

        for (TCeeEnrollUnivListUser tCeeEnrollUnivListUser33 : tCeeEnrollUnivListUsersUnivSort) {
            for (int i = 0; i < sortList.size(); i++) {
                String applicationIdSort1 = String.valueOf(sortList.get(i).get("application_id"));
                int batchId1 = (int) sortList.get(i).get("batch_id");
                String univId1 = String.valueOf(sortList.get(i).get("univ_id"));
                String univCode1 = String.valueOf(sortList.get(i).get("univ_code"));

                String applicationIdSort2 = String.valueOf(tCeeEnrollUnivListUser33.getApplicationId());
                int batchId2 = tCeeEnrollUnivListUser33.getBatchId();
                String univId2 = tCeeEnrollUnivListUser33.getUnivId();
                String univCode2 = tCeeEnrollUnivListUser33.getUnivCode();

                if (applicationIdSort1.equals(applicationIdSort2) && batchId1 == batchId2 && univId1.equals(univId2) && univCode1.equals(univCode2)) {
                    tCeeEnrollUnivListUser33.setUnivXh(i + 1);
//                    commonSvc.saveOrUpdate(tCeeEnrollUnivListUser33);
                }
            }
        }
        commonSvc.batchSaveOrUpdate(tCeeEnrollUnivListUsersUnivSort);


//        专业设置初始排序
        String sortMajorHql = " FROM TCeeEnrollUnivListUser\n" +
                "WHERE applicationId = " + tCeeApplications.getId() + " AND batchId = " + tCeeApplicationsRequire.getBatchId() + " \n" +
                "ORDER BY  univXh ASC ,rankLowScore1 ASC ,rankLowScore2 ASC ,rankLowScore3 ASC  ";
        List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsersMajorSort = commonSvc.findByQueryString(sortMajorHql);

        for (int i = 0; i < tCeeEnrollUnivListUsersMajorSort.size(); i++) {

            if (i == 0) {
                tCeeEnrollUnivListUsersMajorSort.get(i).setMajorXh(1);
            } else {
                String univId1 = tCeeEnrollUnivListUsersMajorSort.get(i - 1).getUnivId();
                String univId2 = tCeeEnrollUnivListUsersMajorSort.get(i).getUnivId();
                String univName1 = tCeeEnrollUnivListUsersMajorSort.get(i - 1).getUnivName();
                String univName2 = tCeeEnrollUnivListUsersMajorSort.get(i).getUnivName();
                if (univId1.equals(univId2) && univName1.equals(univName2)) {
                    if (tCeeEnrollUnivListUsersMajorSort.get(i - 1).getMajorXh() != null) {
                        int majorXhNext = tCeeEnrollUnivListUsersMajorSort.get(i - 1).getMajorXh();
                        int majorXhLocal = majorXhNext + 1;
                        tCeeEnrollUnivListUsersMajorSort.get(i).setMajorXh(majorXhLocal);
                    }
                } else {
                    int majorXhLocal = 1;
                    tCeeEnrollUnivListUsersMajorSort.get(i).setMajorXh(majorXhLocal);
                }
            }
//            commonSvc.saveOrUpdate(tCeeEnrollUnivListUsersMajorSort.get(i));
        }
        commonSvc.batchSaveOrUpdate(tCeeEnrollUnivListUsersMajorSort);
    }


    @Override
//    设置univ_list_choose院校
    public void setUnivListChooseUniv(TCeeApplications tCeeApplications, TCeeApplicationsRequire tCeeApplicationsRequire, TCeeBatch tCeeBatch) {

//        String univLitUserHql = " FROM TCeeEnrollUnivListUser\n" +
//                "WHERE applicationId = " + tCeeApplications.getId() + " AND batchId = " + tCeeApplicationsRequire.getBatchId() + " \n" +
//                "ORDER BY  univXh ASC ,rankLowScore1 ASC ,rankLowScore2 ASC ,rankLowScore3 ASC  ";

        String univLitUserHql = " FROM TCeeEnrollUnivListUser\n" +
                "WHERE applicationId = " + tCeeApplications.getId() + " AND batchId = " + tCeeApplicationsRequire.getBatchId() + " AND chooseFlag = 1  \n" +
                "ORDER BY  univXh ASC ,rankLowScore1 ASC ,rankLowScore2 ASC ,rankLowScore3 ASC  ";
        List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsers = commonSvc.findByQueryString(univLitUserHql);
        //        获得根据用户条件获得意向院校数
        int choosedUnivNum = removeDuplicateCaseUser(tCeeEnrollUnivListUsers).size();

        String PUnivHql = " FROM TCeeEnrollUnivListUser\n" +
                "WHERE applicationId = " + tCeeApplications.getId() + " AND batchId = " + tCeeApplicationsRequire.getBatchId() + " \n" +
                "AND pskSet = '冲一冲' ";
        List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsersPUniv = commonSvc.findByQueryString(PUnivHql);

        String SUnivHql = " FROM TCeeEnrollUnivListUser\n" +
                "WHERE applicationId = " + tCeeApplications.getId() + " AND batchId = " + tCeeApplicationsRequire.getBatchId() + " \n" +
                "AND pskSet = '稳一稳' ";
        List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsersSUniv = commonSvc.findByQueryString(SUnivHql);

        String KUnivHql = " FROM TCeeEnrollUnivListUser\n" +
                "WHERE applicationId = " + tCeeApplications.getId() + " AND batchId = " + tCeeApplicationsRequire.getBatchId() + " \n" +
                "AND pskSet = '保一保' ";
        List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsersKUniv = commonSvc.findByQueryString(KUnivHql);
//        意向冲院校数
        int choosedUnivNumP = removeDuplicateCaseUser(tCeeEnrollUnivListUsersPUniv).size();
//        意向稳院校数
        int choosedUnivNumS = removeDuplicateCaseUser(tCeeEnrollUnivListUsersSUniv).size();
//        意向保院校数
        int choosedUnivNumK = removeDuplicateCaseUser(tCeeEnrollUnivListUsersKUniv).size();
//        批次限定院校数
        int univNum = tCeeBatch.getUnivNum();
//        批次备选院校数
        int extraUnivNum = tCeeBatch.getExtraNum();
//        批次限定院校总数
        int univCount = univNum;
//        int univCount = univNum + extraUnivNum;
//        设置限定冲院校数
        int advancedUnivNum = tCeeApplicationsRequire.getAdvancedUnivNum();
//        设置限定稳院校数
        int stableUnivNum = tCeeApplicationsRequire.getStableUnivNum();
//        设置限定保院校数
        int backwardUnivNum = tCeeApplicationsRequire.getBackwardUnivNum();

//        清空之前univ_list_choose的数据
        String deletSqlChoose = " DELETE FROM t_cee_enroll_univ_list_choose\n" +
                "WHERE application_id = " + tCeeApplications.getId() + " AND batch_id = " + tCeeApplicationsRequire.getBatchId() + " ";
        commonSvc.executeSql(deletSqlChoose);

//        t_cee_enroll_univ_list_choose表中院校数量是否小于所需院校数量
        if ((choosedUnivNumK + choosedUnivNumP + choosedUnivNumS) <= univCount) {
            chooseZhiyuan(tCeeApplications, tCeeApplicationsRequire, tCeeBatch, null, univCount, null, 2);
        } else {
            chooseZhiyuan(tCeeApplications, tCeeApplicationsRequire, tCeeBatch, "冲一冲", advancedUnivNum, null, 1);
            chooseZhiyuan(tCeeApplications, tCeeApplicationsRequire, tCeeBatch, "稳一稳", stableUnivNum, null, 1);
            chooseZhiyuan(tCeeApplications, tCeeApplicationsRequire, tCeeBatch, "保一保", backwardUnivNum, null, 1);

            int cTempNumTotal = 0;
            int wTempNumTotal = 0;
            int bTempNumTotal = 0;

            int cTempNumChoose = 0;
            int wTempNumChoose = 0;
            int bTempNumChoose = 0;

//            t_cee_enroll_univ_list_user 中冲稳保个类型院校数量
            List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsersC = getAllUnivListUser(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), "冲一冲", null, null, null, null, null, null, null, null);
            List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsersW = getAllUnivListUser(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), "稳一稳", null, null, null, null, null, null, null, null);
            List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsersB = getAllUnivListUser(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), "保一保", null, null, null, null, null, null, null, null);

            cTempNumTotal = removeDuplicateCaseUser(tCeeEnrollUnivListUsersC).size();
            wTempNumTotal = removeDuplicateCaseUser(tCeeEnrollUnivListUsersW).size();
            bTempNumTotal = removeDuplicateCaseUser(tCeeEnrollUnivListUsersB).size();

//            t_cee_enroll_univ_list_choose 中已设置冲稳保个类型院校数量
            List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListChoosesC = getAllUnivListChoose(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), "冲一冲", null, null, null, null, null, null, null, null, null, null, null);
            List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListChoosesW = getAllUnivListChoose(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), "稳一稳", null, null, null, null, null, null, null, null, null, null, null);
            List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListChoosesB = getAllUnivListChoose(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), "保一保", null, null, null, null, null, null, null, null, null, null, null);

            cTempNumChoose = removeDuplicateCaseChoose(tCeeEnrollUnivListChoosesC).size();
            wTempNumChoose = removeDuplicateCaseChoose(tCeeEnrollUnivListChoosesW).size();
            bTempNumChoose = removeDuplicateCaseChoose(tCeeEnrollUnivListChoosesB).size();


//           保的不足，补充稳和冲的学校
            if ((bTempNumTotal < backwardUnivNum) && (wTempNumTotal > stableUnivNum) && (cTempNumTotal > advancedUnivNum)) {

                int w9 = backwardUnivNum - bTempNumChoose;
                if (wTempNumTotal >= wTempNumChoose + w9) {
                    chooseZhiyuan(tCeeApplications, tCeeApplicationsRequire, tCeeBatch, "稳一稳", w9, null, 1);
                } else {
                    int w10 = wTempNumTotal - wTempNumChoose;
                    int w11 = univCount - w10 - bTempNumChoose - cTempNumChoose - wTempNumChoose;
                    chooseZhiyuan(tCeeApplications, tCeeApplicationsRequire, tCeeBatch, "稳一稳", w10, null, 1);
                    chooseZhiyuan(tCeeApplications, tCeeApplicationsRequire, tCeeBatch, "冲一冲", w11, null, 1);
                }
            }

//          保的学校不足 ,冲的也不足
            if ((bTempNumTotal < backwardUnivNum) && (wTempNumTotal > stableUnivNum) && (cTempNumTotal < advancedUnivNum)) {
                int w12 = univCount - bTempNumChoose - cTempNumChoose - wTempNumChoose;
                chooseZhiyuan(tCeeApplications, tCeeApplicationsRequire, tCeeBatch, "稳一稳", w12, null, 1);
            }

//            保的学校不足 ,稳的也不足
            if ((bTempNumTotal < backwardUnivNum) && (wTempNumTotal < stableUnivNum) && (cTempNumTotal > advancedUnivNum)) {
                int w13 = univCount - bTempNumChoose - cTempNumChoose - wTempNumChoose;
                chooseZhiyuan(tCeeApplications, tCeeApplicationsRequire, tCeeBatch, "冲一冲", w13, null, 1);
            }

//            保的充足，稳也有，补充冲的学校,优先补充稳的学校
            if ((bTempNumTotal > backwardUnivNum) && (wTempNumTotal > stableUnivNum) && (cTempNumTotal < advancedUnivNum)) {
                int w14 = advancedUnivNum - cTempNumChoose;
                if (wTempNumTotal >= wTempNumChoose + w14) {
                    chooseZhiyuan(tCeeApplications, tCeeApplicationsRequire, tCeeBatch, "稳一稳", w14, null, 1);
                } else {
                    int w15 = wTempNumTotal - wTempNumChoose;
                    int w16 = univCount - w15 - bTempNumChoose - cTempNumChoose - wTempNumChoose;
                    chooseZhiyuan(tCeeApplications, tCeeApplicationsRequire, tCeeBatch, "稳一稳", w15, null, 1);
                    chooseZhiyuan(tCeeApplications, tCeeApplicationsRequire, tCeeBatch, "保一保", w16, null, 1);
                }
            }

//          保的学校足够 ,稳、冲不足的院校
            if ((bTempNumTotal > backwardUnivNum) && (wTempNumTotal < stableUnivNum) && (cTempNumTotal < advancedUnivNum)) {
                int w17 = univCount - bTempNumChoose - cTempNumChoose - wTempNumChoose;
                chooseZhiyuan(tCeeApplications, tCeeApplicationsRequire, tCeeBatch, "保一保", w17, null, 1);
            }

//          保的充足，冲充足，优先补充保的学校
            if ((bTempNumTotal > backwardUnivNum) && (wTempNumTotal < stableUnivNum) && (cTempNumTotal > advancedUnivNum)) {
                int w18 = advancedUnivNum - cTempNumChoose;
                if (bTempNumTotal >= bTempNumChoose + w18) {
                    chooseZhiyuan(tCeeApplications, tCeeApplicationsRequire, tCeeBatch, "稳一稳", w18, null, 1);
                } else {
                    int w19 = bTempNumTotal - bTempNumChoose;
                    int w20 = univCount - w19 - bTempNumChoose - cTempNumChoose - wTempNumChoose;
                    chooseZhiyuan(tCeeApplications, tCeeApplicationsRequire, tCeeBatch, "稳一稳", w19, null, 1);
                    chooseZhiyuan(tCeeApplications, tCeeApplicationsRequire, tCeeBatch, "保一保", w20, null, 1);
                }
            }
        }

    }

    //    设置univ_list_choose专业
    @Override
    public void setUnivListChooseMajor(TCeeApplications tCeeApplications, TCeeApplicationsRequire tCeeApplicationsRequire, TCeeBatch tCeeBatch) {

        int majorNum = tCeeBatch.getMajorNum();
        int y_num1, y_num2;
        List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListChooses = getAllUnivListChoose(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), null, null, null, null, null, null, null, null, null, null, null, null);
        for (TCeeEnrollUnivListChoose tCeeEnrollUnivListChoose : tCeeEnrollUnivListChooses) {

            String application_id = String.valueOf(tCeeEnrollUnivListChoose.getApplicationId());
            String batch_id = String.valueOf(tCeeEnrollUnivListChoose.getBatchId());
            String univ_id = tCeeEnrollUnivListChoose.getUnivId();
            String univ_code = tCeeEnrollUnivListChoose.getUnivCode();
            String univ_name = tCeeEnrollUnivListChoose.getUnivName();
            String intent_major = tCeeApplicationsRequire.getIntentMajor();
            String intent_major_id = tCeeApplicationsRequire.getIntentMajorId();

            String psk = tCeeEnrollUnivListChoose.getPskSet();

            String inUnivListStr = " ( '" + application_id + "','" + batch_id + "','" + univ_id + "','" + univ_code + "','" + univ_name + "' ) ";
            List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListChooseTmp = getAllUnivListChoose(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), psk, null, null, null, null, null, inUnivListStr, null, null, null, null, null);
            int univMajorNum = tCeeEnrollUnivListChooseTmp.size();

//            专业数量不够的情况下选中所有专业
            if (univMajorNum < majorNum) {
                tCeeEnrollUnivListChoose.setChooseFlag(1);
                commonSvc.updateEntitie(tCeeEnrollUnivListChoose);
            } else {
                List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListChooseIntent = getAllUnivListChoose(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), psk, intent_major, intent_major_id, null, null, null, inUnivListStr, null, null, null, null, null);

                int yNum = tCeeEnrollUnivListChooseIntent.size();
                if (yNum > majorNum) {
                    y_num1 = majorNum;
                    y_num2 = 0;
                } else {
                    y_num1 = yNum;
                    y_num2 = majorNum - yNum;
                }

                if (yNum > majorNum) {
//                    意向专业过多
                    List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListChooseIntentOrders0 = getAllUnivListChoose(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), psk, intent_major, intent_major_id, null, null, null, inUnivListStr, "major_xh", "ASC", majorNum, null, null);
                    for (TCeeEnrollUnivListChoose tCeeEnrollUnivListChooseIntentOrder : tCeeEnrollUnivListChooseIntentOrders0) {
                        tCeeEnrollUnivListChooseIntentOrder.setChooseFlag(1);
                        commonSvc.updateEntitie(tCeeEnrollUnivListChooseIntentOrder);
                    }
                } else {
//                    意向专业不够
                    List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListChooseIntentOrders1 = getAllUnivListChoose(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), psk, intent_major, intent_major_id, "NOT", null, null, inUnivListStr, "major_xh", "ASC", y_num2, null, null);
                    List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListChooseIntentOrders2 = getAllUnivListChoose(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), psk, intent_major, intent_major_id, null, null, null, inUnivListStr, "major_xh", "ASC", y_num1, null, null);
                    for (TCeeEnrollUnivListChoose tCeeEnrollUnivListChooseIntentOrder1 : tCeeEnrollUnivListChooseIntentOrders1) {
                        tCeeEnrollUnivListChooseIntentOrder1.setChooseFlag(1);
                        commonSvc.updateEntitie(tCeeEnrollUnivListChooseIntentOrder1);
                    }

                    for (TCeeEnrollUnivListChoose tCeeEnrollUnivListChooseIntentOrder2 : tCeeEnrollUnivListChooseIntentOrders2) {
                        tCeeEnrollUnivListChooseIntentOrder2.setChooseFlag(1);
                        commonSvc.updateEntitie(tCeeEnrollUnivListChooseIntentOrder2);
                    }
                }

            }

        }

    }

    @Override
    //    将最终univ_list_choose插入志愿表
    public void doInsertApplicationDetail(TCeeApplications tCeeApplications, TCeeApplicationsRequire tCeeApplicationsRequire, TCeeBatch tCeeBatch) {

        //        清空之前的数据
        String deletSqlDetail = " DELETE FROM t_cee_applications_detail\n" +
                "WHERE application_id = " + tCeeApplications.getId() + " AND batch_id = " + tCeeApplicationsRequire.getBatchId() + " AND is_formal = 0 ";

        commonSvc.executeSql(deletSqlDetail);

        List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListChooseTotal = getAllUnivListChoose(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), null, null, null, null, null, null, null, null, null, null, "choosed", null);
        List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListChooseP = getAllUnivListChoose(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), "冲一冲", null, null, null, null, null, null, null, null, null, "choosed", null);
        List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListChooseS = getAllUnivListChoose(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), "稳一稳", null, null, null, null, null, null, null, null, null, "choosed", null);
        List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListChooseK = getAllUnivListChoose(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), "保一保", null, null, null, null, null, null, null, null, null, "choosed", null);

        int chooseTotal = removeDuplicateCaseChoose(tCeeEnrollUnivListChooseTotal).size();
        int chooseTotalCnum = removeDuplicateCaseChoose(tCeeEnrollUnivListChooseP).size();
        int chooseTotalWnum = removeDuplicateCaseChoose(tCeeEnrollUnivListChooseS).size();
        int chooseTotalBnum = removeDuplicateCaseChoose(tCeeEnrollUnivListChooseK).size();

        //        设置限定冲院校数
        int advancedUnivNum = tCeeApplicationsRequire.getAdvancedUnivNum();
//        设置限定稳院校数
        int stableUnivNum = tCeeApplicationsRequire.getStableUnivNum();
//        设置限定保院校数
        int backwardUnivNum = tCeeApplicationsRequire.getBackwardUnivNum();
//        批次限定院校数
        int univNum = tCeeBatch.getUnivNum();
//        批次备选院校数
        int extraUnivNum = tCeeBatch.getExtraNum();
//        批次限定院校总数
        int univCount = univNum;
//        int univCount = univNum + extraUnivNum;

        if (chooseTotal >= univCount) {
            String inUnivListStr = getDistinctUnivListUserOrChooseStr(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), null, tCeeApplicationsRequire.getIntentMajor(), "univ_xh", " ASC ", univCount, "choosed", "t_cee_enroll_univ_list_user");

            List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListChooseList = getAllUnivListChoose(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), null, null, null, null, null, null, inUnivListStr, null, null, null, "choosed", null);

            List<TCeeApplicationsDetail> tCeeApplicationsDetails = new ArrayList<>();
            for (TCeeEnrollUnivListChoose tCeeEnrollUnivListChoose : tCeeEnrollUnivListChooseList) {
                TCeeApplicationsDetail tCeeApplicationsDetail = new TCeeApplicationsDetail();
                tCeeApplicationsDetail.setLocal(tCeeEnrollUnivListChoose);
                commonSvc.save(tCeeApplicationsDetail);

                tCeeApplicationsDetails.add(tCeeApplicationsDetail);
            }

            if ((chooseTotalCnum == advancedUnivNum + 1) && (chooseTotalWnum == stableUnivNum + 1) && (chooseTotalBnum == backwardUnivNum + 1)) {

                String PunivXhStr = getDistinctUnivListChooseXHStr(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), "冲一冲", null, "univ_xh", " ASC ", 1, "choosed", " TCeeEnrollUnivListChoose");
                List<TCeeApplicationsDetail> tCeeApplicationsDetailsP = getAllApplicationDetail(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), 0, PunivXhStr);
                String SunivXhStr = getDistinctUnivListChooseXHStr(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), "稳一稳", null, "univ_xh", " ASC ", 1, "choosed", " TCeeEnrollUnivListChoose");
                List<TCeeApplicationsDetail> tCeeApplicationsDetailsS = getAllApplicationDetail(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), 0, SunivXhStr);
                String KunivXhStr = getDistinctUnivListChooseXHStr(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), "保一保", null, "univ_xh", " ASC ", 1, "choosed", " TCeeEnrollUnivListChoose");
                List<TCeeApplicationsDetail> tCeeApplicationsDetailsK = getAllApplicationDetail(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), 0, KunivXhStr);

                for (TCeeApplicationsDetail tCeeApplicationsDetail1 : tCeeApplicationsDetailsP) {
                    tCeeApplicationsDetail1.setIsFormal(1);
                    commonSvc.updateEntitie(tCeeApplicationsDetail1);
                }
                for (TCeeApplicationsDetail tCeeApplicationsDetail2 : tCeeApplicationsDetailsS) {
                    tCeeApplicationsDetail2.setIsFormal(1);
                    commonSvc.updateEntitie(tCeeApplicationsDetail2);
                }
                for (TCeeApplicationsDetail tCeeApplicationsDetail3 : tCeeApplicationsDetailsK) {
                    tCeeApplicationsDetail3.setIsFormal(1);
                    commonSvc.updateEntitie(tCeeApplicationsDetail3);
                }

                int limit_cformal = 0;
                int limit_wformal = 0;
                int limit_bformal = 0;

                String psk_set_cvalue = "冲一冲";
                String psk_set_wvalue = "稳一稳";
                String psk_set_bvalue = "保一保";

                if ((chooseTotalCnum <= advancedUnivNum) && (chooseTotalWnum > stableUnivNum) && (chooseTotalBnum > backwardUnivNum)) {
                    if ((chooseTotalWnum - stableUnivNum) - (chooseTotalBnum - backwardUnivNum) > 0) {
                        psk_set_wvalue = "稳一稳";
                        limit_wformal = 2;
                        psk_set_bvalue = "保一保";
                        limit_bformal = 1;
                    } else {
                        psk_set_wvalue = "稳一稳";
                        limit_wformal = 1;
                        psk_set_bvalue = "保一保";
                        limit_bformal = 2;
                    }
                }

                if ((chooseTotalCnum <= advancedUnivNum) && (chooseTotalWnum <= stableUnivNum) && (chooseTotalBnum > backwardUnivNum)) {
                    psk_set_bvalue = "保一保";
                    limit_bformal = extraUnivNum;
                }

                if ((chooseTotalCnum <= advancedUnivNum) && (chooseTotalWnum > stableUnivNum) && (chooseTotalBnum <= backwardUnivNum)) {
                    psk_set_wvalue = "稳一稳";
                    limit_wformal = extraUnivNum;
                }

                if ((chooseTotalCnum > advancedUnivNum) && (chooseTotalWnum > stableUnivNum) && (chooseTotalBnum < backwardUnivNum)) {
                    if ((chooseTotalCnum - advancedUnivNum) - (chooseTotalWnum - stableUnivNum) > 0) {
                        psk_set_cvalue = "冲一冲";
                        limit_cformal = 2;
                        psk_set_wvalue = "稳一稳";
                        limit_wformal = 1;
                    } else {
                        psk_set_cvalue = "冲一冲";
                        limit_cformal = 1;
                        psk_set_wvalue = "稳一稳";
                        limit_wformal = 2;
                    }
                }

                if ((chooseTotalCnum > advancedUnivNum) && (chooseTotalWnum <= stableUnivNum) && (chooseTotalBnum <= backwardUnivNum)) {
                    psk_set_cvalue = "冲一冲";
                    limit_cformal = extraUnivNum;
                }

                if ((chooseTotalCnum > advancedUnivNum) && (chooseTotalWnum <= stableUnivNum) && (chooseTotalBnum > backwardUnivNum)) {
                    if ((chooseTotalCnum - advancedUnivNum) - (chooseTotalBnum - backwardUnivNum) > 0) {
                        psk_set_cvalue = "冲一冲";
                        limit_cformal = 2;
                        psk_set_bvalue = "保一保";
                        limit_bformal = 1;
                    } else {
                        psk_set_cvalue = "冲一冲";
                        limit_cformal = 1;
                        psk_set_bvalue = "保一保";
                        limit_bformal = 2;
                    }
                }

                int v_limit_cform_num = limit_cformal;
                int v_limit_wform_num = limit_wformal;
                int v_limit_bform_num = limit_bformal;

                if (limit_cformal > 0) {
                    String PunivXhStr11 = getDistinctUnivListChooseXHStr(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), "冲一冲", null, "univ_xh", " ASC ", v_limit_cform_num, "choosed", " TCeeEnrollUnivListChoose");
                    List<TCeeApplicationsDetail> tCeeApplicationsDetailsP1 = getAllApplicationDetail(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), 0, PunivXhStr11);
                    for (TCeeApplicationsDetail tCeeApplicationsDetail11 : tCeeApplicationsDetailsP1) {
                        tCeeApplicationsDetail11.setIsFormal(1);
                        commonSvc.updateEntitie(tCeeApplicationsDetail11);
                    }
                }
                if (limit_wformal > 0) {
                    String SunivXhStr1 = getDistinctUnivListChooseXHStr(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), "稳一稳", null, "univ_xh", " ASC ", v_limit_wform_num, "choosed", " TCeeEnrollUnivListChoose");
                    List<TCeeApplicationsDetail> tCeeApplicationsDetailsS1 = getAllApplicationDetail(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), 0, SunivXhStr1);
                    for (TCeeApplicationsDetail tCeeApplicationsDetail12 : tCeeApplicationsDetailsS1) {
                        tCeeApplicationsDetail12.setIsFormal(1);
                        commonSvc.updateEntitie(tCeeApplicationsDetail12);
                    }
                }
                if (limit_bformal > 0) {
                    String KunivXhStr1 = getDistinctUnivListChooseXHStr(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), "保一保", null, "univ_xh", " ASC ", v_limit_bform_num, "choosed", " TCeeEnrollUnivListChoose");
                    List<TCeeApplicationsDetail> tCeeApplicationsDetailsK1 = getAllApplicationDetail(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), 0, KunivXhStr1);
                    for (TCeeApplicationsDetail tCeeApplicationsDetail13 : tCeeApplicationsDetailsK1) {
                        tCeeApplicationsDetail13.setIsFormal(1);
                        commonSvc.saveOrUpdate(tCeeApplicationsDetail13);
                    }
                }
            }
        } else if ((chooseTotal <= univNum) && (chooseTotal > 0)) {

            List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListChooseList = getAllUnivListChoose(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), null, null, null, null, null, null, null, null, null, null, "choosed", null);

            int max_univ_order_px1 = 0;
            for (TCeeEnrollUnivListChoose tCeeEnrollUnivListChoose1 : tCeeEnrollUnivListChooseList) {
                TCeeApplicationsDetail tCeeApplicationsDetail = new TCeeApplicationsDetail();
                int univ_order = tCeeEnrollUnivListChoose1.getUnivXh();
                if (univ_order > max_univ_order_px1) {
                    max_univ_order_px1 = univ_order;
                }
                tCeeApplicationsDetail.setLocal(tCeeEnrollUnivListChoose1);
                commonSvc.save(tCeeApplicationsDetail);
            }
            int blank_zhiyuan_px1 = univCount - chooseTotal;
            while (blank_zhiyuan_px1 > 0) {
                max_univ_order_px1 += 1;
                TCeeApplicationsDetail tCeeApplicationsDetailNewPx = new TCeeApplicationsDetail();
                tCeeApplicationsDetailNewPx.setYear(tCeeApplications.getYear());
                tCeeApplicationsDetailNewPx.setProvinceId(tCeeApplications.getProvinceId());
                tCeeApplicationsDetailNewPx.setMajorTypeId(tCeeApplications.getMajorTypeId());
                tCeeApplicationsDetailNewPx.setApplicationId(tCeeApplications.getId());
                tCeeApplicationsDetailNewPx.setBatchId(tCeeApplicationsRequire.getBatchId());
                tCeeApplicationsDetailNewPx.setUnivOrder(max_univ_order_px1);
                tCeeApplicationsDetailNewPx.setMajorOrder(0);
                tCeeApplicationsDetailNewPx.setIsFormal(0);
                commonSvc.saveOrUpdate(tCeeApplicationsDetailNewPx);
                blank_zhiyuan_px1 -= 1;
            }

            int blank_zhiyuan_bx1 = 0;
            int max_univ_order_bx1 = 0;
            blank_zhiyuan_bx1 = extraUnivNum;

            while (blank_zhiyuan_bx1 > 0) {
                max_univ_order_bx1 += 1;
                TCeeApplicationsDetail tCeeApplicationsDetailNewBx = new TCeeApplicationsDetail();
                tCeeApplicationsDetailNewBx.setYear(tCeeApplications.getYear());
                tCeeApplicationsDetailNewBx.setProvinceId(tCeeApplications.getProvinceId());
                tCeeApplicationsDetailNewBx.setMajorTypeId(tCeeApplications.getMajorTypeId());
                tCeeApplicationsDetailNewBx.setApplicationId(tCeeApplications.getId());
                tCeeApplicationsDetailNewBx.setBatchId(tCeeApplicationsRequire.getBatchId());
                tCeeApplicationsDetailNewBx.setUnivOrder(max_univ_order_bx1);
                tCeeApplicationsDetailNewBx.setMajorOrder(0);
                tCeeApplicationsDetailNewBx.setIsFormal(1);
                commonSvc.saveOrUpdate(tCeeApplicationsDetailNewBx);
                blank_zhiyuan_bx1 -= 1;
            }

        } else if (chooseTotal == 0) {

            int blank_zhiyuan_px2 = univCount - chooseTotal;
            int max_univ_order_px2 = 0;
            while (blank_zhiyuan_px2 > 0) {
                max_univ_order_px2 += 1;
                TCeeApplicationsDetail tCeeApplicationsDetailNewPx2 = new TCeeApplicationsDetail();
                tCeeApplicationsDetailNewPx2.setYear(tCeeApplications.getYear());
                tCeeApplicationsDetailNewPx2.setProvinceId(tCeeApplications.getProvinceId());
                tCeeApplicationsDetailNewPx2.setMajorTypeId(tCeeApplications.getMajorTypeId());
                tCeeApplicationsDetailNewPx2.setApplicationId(tCeeApplications.getId());
                tCeeApplicationsDetailNewPx2.setBatchId(tCeeApplicationsRequire.getBatchId());
                tCeeApplicationsDetailNewPx2.setUnivOrder(max_univ_order_px2);
                tCeeApplicationsDetailNewPx2.setMajorOrder(0);
                tCeeApplicationsDetailNewPx2.setIsFormal(0);
                commonSvc.saveOrUpdate(tCeeApplicationsDetailNewPx2);
                blank_zhiyuan_px2 -= 1;
            }

            int blank_zhiyuan_bx2 = 0;
            int max_univ_order_bx2 = 0;
            blank_zhiyuan_bx2 = extraUnivNum;

            while (blank_zhiyuan_bx2 > 0) {
                max_univ_order_bx2 += 1;
                TCeeApplicationsDetail tCeeApplicationsDetailNewBx2 = new TCeeApplicationsDetail();
                tCeeApplicationsDetailNewBx2.setYear(tCeeApplications.getYear());
                tCeeApplicationsDetailNewBx2.setProvinceId(tCeeApplications.getProvinceId());
                tCeeApplicationsDetailNewBx2.setMajorTypeId(tCeeApplications.getMajorTypeId());
                tCeeApplicationsDetailNewBx2.setApplicationId(tCeeApplications.getId());
                tCeeApplicationsDetailNewBx2.setBatchId(tCeeApplicationsRequire.getBatchId());
                tCeeApplicationsDetailNewBx2.setUnivOrder(max_univ_order_bx2);
                tCeeApplicationsDetailNewBx2.setMajorOrder(0);
                tCeeApplicationsDetailNewBx2.setIsFormal(1);
                commonSvc.saveOrUpdate(tCeeApplicationsDetailNewBx2);
                blank_zhiyuan_bx2 -= 1;
            }

        }


    }


    //    function_choose_zhiyuan
    public int chooseZhiyuan(TCeeApplications tCeeApplications, TCeeApplicationsRequire tCeeApplicationsRequire, TCeeBatch tCeeBatch, String pskSet, Integer limitUnivNum, Integer limitMajorNum, int chooseType) {

        int x_num = 0;
        int x_num2 = 0;
        int x_num1 = 0;

        int yx_univ_total_num = 0;
        int yx_univ_choose_num = 0;
        int all_univ_choose_num = 0;

        if(limitUnivNum!=0) {
            if (chooseType == 1) {

                List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsersIntentPUniv = getAllUnivListUser(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), pskSet, tCeeApplicationsRequire.getIntentMajor(), null, null, null, null, null, null, "TCeeEnrollUnivListUser");
                List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListUsersIntentSUniv = getAllUnivListChoose(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), pskSet, tCeeApplicationsRequire.getIntentMajor(), null, null, null, null, null, null, null, null, null, null);
                List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListUsersIntentKUniv = getAllUnivListChoose(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), pskSet, null, null, null, null, null, null, null, null, null, null, null);

//        所有意向专业院校数
                yx_univ_total_num = removeDuplicateCaseUser(tCeeEnrollUnivListUsersIntentPUniv).size();
//        已选意向专业院校数
                yx_univ_choose_num = removeDuplicateCaseChoose(tCeeEnrollUnivListUsersIntentSUniv).size();
//        已选所有院校数
                all_univ_choose_num = removeDuplicateCaseChoose(tCeeEnrollUnivListUsersIntentKUniv).size();

                if (yx_univ_total_num - yx_univ_choose_num > limitUnivNum) {
                    x_num1 = limitUnivNum;
                    x_num2 = 0;
                } else {
                    x_num1 = yx_univ_total_num - yx_univ_choose_num;
                    x_num2 = limitUnivNum - x_num1;
                }

                List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsers1 = new ArrayList<>();
                List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsers2 = new ArrayList<>();
                List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsersList = new ArrayList<>();

                String intentMajorUnivListStr1 = getDistinctUnivListUserOrChooseStr(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), null, tCeeApplicationsRequire.getIntentMajor(), null, null, null, null, "t_cee_enroll_univ_list_user");

                if ("冲一冲".equals(pskSet)) {
                    if (all_univ_choose_num > 0) {
                        String choosedUnivListStr1 = getDistinctUnivListUserOrChooseStr(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), pskSet, null, null, null, null, null, "t_cee_enroll_univ_list_choose");
//                    排除意向选择以及已选择的univ_list_user
                        tCeeEnrollUnivListUsers1 = getAllUnivListUserDISUniv(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), pskSet, null, intentMajorUnivListStr1, choosedUnivListStr1, null, "univ_xh", " DESC ", x_num2, "TCeeEnrollUnivListUser");
//                    排除意向选择以及已选择的univ_list_user
                        tCeeEnrollUnivListUsers2 = getAllUnivListUserDISUniv(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), pskSet, null, null, choosedUnivListStr1, null, "univ_xh", " DESC ", x_num1, "TCeeEnrollUnivListUser");
                        tCeeEnrollUnivListUsersList = getAllUnivListUser(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), pskSet, null, null, null, getDistinctUnivListUserStr(tCeeEnrollUnivListUsers1, tCeeEnrollUnivListUsers2), null, null, null, "TCeeEnrollUnivListUser");

                    } else {
                        tCeeEnrollUnivListUsers1 = getAllUnivListUserDISUniv(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), pskSet, null, intentMajorUnivListStr1, null, null, "univ_xh", " DESC ", x_num2, "TCeeEnrollUnivListUser");
                        tCeeEnrollUnivListUsers2 = getAllUnivListUserDISUniv(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), pskSet, tCeeApplicationsRequire.getIntentMajor(), null, null, null, "univ_xh", "DESC", x_num1, "TCeeEnrollUnivListUser");
                        tCeeEnrollUnivListUsersList = getAllUnivListUser(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), pskSet, null, null, null, getDistinctUnivListUserStr(tCeeEnrollUnivListUsers1, tCeeEnrollUnivListUsers2), null, null, null, "TCeeEnrollUnivListUser");
                    }
                } else if ("稳一稳".equals(pskSet) || "保一保".equals(pskSet)) {
                    if (all_univ_choose_num > 0) {
                        String choosedUnivListStr1 = getDistinctUnivListUserOrChooseStr(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), pskSet, null, null, null, null, null, "t_cee_enroll_univ_list_choose");
//                    排除意向选择以及已选择的univ_list_user
                        tCeeEnrollUnivListUsers1 = getAllUnivListUserDISUniv(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), pskSet, null, intentMajorUnivListStr1, choosedUnivListStr1, null, "univ_xh", " ASC ", x_num2, "TCeeEnrollUnivListUser");
//                    排除意向选择以及已选择的univ_list_user
                        tCeeEnrollUnivListUsers2 = getAllUnivListUserDISUniv(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), pskSet, null, null, choosedUnivListStr1, null, "univ_xh", " ASC ", x_num1, "TCeeEnrollUnivListUser");
                        tCeeEnrollUnivListUsersList = getAllUnivListUser(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), pskSet, null, null, null, getDistinctUnivListUserStr(tCeeEnrollUnivListUsers1, tCeeEnrollUnivListUsers2), null, null, null, "TCeeEnrollUnivListUser");
                    } else {
                        tCeeEnrollUnivListUsers1 = getAllUnivListUserDISUniv(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), pskSet, null, intentMajorUnivListStr1, null, null, "univ_xh", " ASC ", x_num2, "TCeeEnrollUnivListUser");
                        tCeeEnrollUnivListUsers2 = getAllUnivListUserDISUniv(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), pskSet, tCeeApplicationsRequire.getIntentMajor(), null, null, null, "univ_xh", " ASC ", x_num1, "TCeeEnrollUnivListUser");
                        tCeeEnrollUnivListUsersList = getAllUnivListUser(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), pskSet, null, null, null, getDistinctUnivListUserStr(tCeeEnrollUnivListUsers1, tCeeEnrollUnivListUsers2), null, null, null, "TCeeEnrollUnivListUser");
                    }
                }

                for (TCeeEnrollUnivListUser tCeeEnrollUnivListUserTmp : tCeeEnrollUnivListUsersList) {
                    TCeeEnrollUnivListChoose tCeeEnrollUnivListChooseTmp = new TCeeEnrollUnivListChoose();
                    tCeeEnrollUnivListChooseTmp.setLocal(tCeeEnrollUnivListUserTmp);
                    commonSvc.save(tCeeEnrollUnivListChooseTmp);
                }

            } else if (chooseType == 2) {
//            院校不足将univ_list_user全部使用，返回已选择的院校数一遍后续补全
                List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsers = getAllUnivListUser(String.valueOf(tCeeApplications.getId()), tCeeApplicationsRequire.getBatchId(), null, null, null, null, null, null, null, null, "TCeeEnrollUnivListChoose");
                x_num = removeDuplicateCaseUser(tCeeEnrollUnivListUsers).size();
//            List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListChooses = new ArrayList<>();
                for (TCeeEnrollUnivListUser tCeeEnrollUnivListUser : tCeeEnrollUnivListUsers) {
                    TCeeEnrollUnivListChoose tCeeEnrollUnivListChoose = new TCeeEnrollUnivListChoose();
                    tCeeEnrollUnivListChoose.setLocal(tCeeEnrollUnivListUser);
//                tCeeEnrollUnivListChooses.add(tCeeEnrollUnivListChoose);
                    commonSvc.saveOrUpdate(tCeeEnrollUnivListChoose);
                }
            }
        }
        return x_num;
    }

    //    获取各种类型univ_list_user
    public List<TCeeEnrollUnivListUser> getAllUnivListUser(String applicationId, Integer batchId, String pskSet, String intentMajor, String intentMajorUnivListStr, String chooseUnivListStr, String inUnivListStr, String orderByType, String order, Integer limitUnivNum, String type) {
        StringBuffer univSql = new StringBuffer();
        univSql.append("SELECT * FROM t_cee_enroll_univ_list_user  \n" +
                "WHERE application_id = " + applicationId + " AND batch_id = " + batchId + " \n");
//        冲稳保条件
        if (StringUtils.isNotBlank(pskSet)) {
            univSql.append("AND psk_set = '" + pskSet + "' ");
        }
//        意向专业条件
        if (StringUtils.isNotBlank(intentMajor)) {
            univSql.append(" AND major_list_id in (" + intentMajor.replace("|", ",") + ") ");
//            univHql.append(" AND majorListId REGEXP '" + intentMajor + "' ");
        }
//      意向院校筛选
        if (StringUtils.isNotBlank(intentMajorUnivListStr)) {
            univSql.append(" AND (application_id,batch_id,univ_id,univ_code,univ_name) NOT IN  ( " + intentMajorUnivListStr + " )");
        }
//        已选择筛选
        if (StringUtils.isNotBlank(chooseUnivListStr)) {
            univSql.append(" AND (application_id,batch_id,univ_id,univ_code,univ_name) NOT IN  ( " + chooseUnivListStr + " )");
        }
//        条件相等筛选
        if (StringUtils.isNotBlank(inUnivListStr)) {
//            if (inUnivListStr != "nothing") {
                univSql.append(" AND (application_id,batch_id,univ_id,univ_code,univ_name) IN  ( " + inUnivListStr + " )");
//            }
        }

        if (StringUtils.isNotBlank(orderByType)) {
            univSql.append(" ORDER BY " + orderByType + " " + order + " \n ");
        }
//        if (inUnivListStr == "nothing") {
//            limitUnivNum = 0;
//        }
        if (limitUnivNum != null) {
            if (limitUnivNum == 0) {
                univSql.append(" LIMIT " + limitUnivNum + " ");
            }
        }

//        List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsersUniv = commonSvc.findByQueryString(univHql.toString());
        List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsersUniv = commonSvc.findListbySql(univSql.toString(), TCeeEnrollUnivListUser.class);

        return tCeeEnrollUnivListUsersUniv;
    }

    //    获取各种类型univ_list_choose
    public List<TCeeEnrollUnivListChoose> getAllUnivListChoose(String applicationId, Integer batchId, String pskSet, String intentMajor, String intentMajorId, String intentMajorOrNot, String intentMajorUnivListStr, String chooseUnivListStr, String inUnivListStr, String orderByType, String order, Integer limitUnivNum, String chooseOrNot, String type) {
        StringBuffer univSql = new StringBuffer();
//        univHql.append(" FROM TCeeEnrollUnivListChoose \n" +
//                "WHERE applicationId = " + applicationId + " AND batchId = " + batchId + " \n");
        univSql.append(" SELECT * FROM t_cee_enroll_univ_list_choose\n" +
                "WHERE application_id = " + applicationId + " \n" +
                "AND batch_id=" + batchId + " \n");
//        冲稳保条件
        if (StringUtils.isNotBlank(pskSet)) {
            univSql.append("AND psk_set = '" + pskSet + "' ");
        }
//        意向专业条件
        if (StringUtils.isNotBlank(intentMajor)) {
            univSql.append(" AND ( major_list_id REGEXP '" + intentMajor + "' ");

            //        意向具体专业条件
            if (StringUtils.isNotBlank(intentMajorId)) {
                if (StringUtils.isNotBlank(intentMajorOrNot)) {
                    univSql.append(" OR  SUBSTRING(major_id,1,4)  " + intentMajorOrNot + "  REGEXP '" + intentMajorId + "' ) ");
                } else {
                    univSql.append(" OR SUBSTRING(major_id,1,4)  REGEXP '" + intentMajorId + "' ) ");
                }
            } else {
                univSql.append(" ) ");
            }
        }
//      意向院校筛选
        if (StringUtils.isNotBlank(intentMajorUnivListStr)) {
            univSql.append(" AND (application_id,batch_id,univ_id,univ_code,univ_name) NOT IN  ( " + intentMajorUnivListStr + " )");
        }
//        已选择筛选
        if (StringUtils.isNotBlank(chooseUnivListStr)) {
            univSql.append(" AND (application_id,batch_id,univ_id,univ_code,univ_name) NOT IN  ( " + chooseUnivListStr + " )");
        }
//        条件相等筛选
        if (StringUtils.isNotBlank(inUnivListStr)) {
            univSql.append(" AND (application_id,batch_id,univ_id,univ_code,univ_name) IN  ( " + inUnivListStr + " )");
        }
//        是否选中
        if (StringUtils.isNotBlank(chooseOrNot)) {
            univSql.append(" AND choose_flag = 1 ");
        }

        if (StringUtils.isNotBlank(orderByType)) {
            univSql.append(" ORDER BY " + orderByType + " " + order + " \n ");
        }
        if (limitUnivNum != null) {
            univSql.append(" LIMIT " + limitUnivNum + " ");
        }

//        List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListUsersUniv = commonSvc.findByQueryString(univHql.toString());
        List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListUsersUniv = commonSvc.findListbySql(univSql.toString(), TCeeEnrollUnivListChoose.class);
        return tCeeEnrollUnivListUsersUniv;
    }

    //    获取各种类型Application_detail
    public List<TCeeApplicationsDetail> getAllApplicationDetail(String applicationId, Integer batchId, Integer isFormal, String univOrderStr) {
        StringBuffer detailSql = new StringBuffer();
        detailSql.append(" SELECT * FROM t_cee_applications_detail\n" +
                "WHERE application_id = " + applicationId + " \n" +
                "AND batch_id=" + batchId + " \n");

        if (isFormal != null) {
            detailSql.append(" is_formal = " + isFormal + " ");
        }
        if (StringUtils.isNotBlank(univOrderStr)) {
            detailSql.append(" AND univ_order  IN  ( " + univOrderStr + " )");
        }
        List<TCeeApplicationsDetail> tCeeApplicationsDetails = commonSvc.findListbySql(detailSql.toString(), TCeeApplicationsDetail.class);
        return tCeeApplicationsDetails;
    }

    //    根据univ_id去重univ_list_user
    private List<TCeeEnrollUnivListUser> removeDuplicateCaseUser(List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsers) {
        Set<TCeeEnrollUnivListUser> set = new TreeSet<>(new Comparator<TCeeEnrollUnivListUser>() {
            @Override
            public int compare(TCeeEnrollUnivListUser tCeeEnrollUnivListUser1, TCeeEnrollUnivListUser tCeeEnrollUnivListUser2) {
                //字符串,则按照asicc码升序排列
                return tCeeEnrollUnivListUser1.getUnivId().compareTo(tCeeEnrollUnivListUser2.getUnivId());
            }
        });
        set.addAll(tCeeEnrollUnivListUsers);
        return new ArrayList<>(set);
    }

    //    根据univ_id去重univ_list_choose
    private List<TCeeEnrollUnivListChoose> removeDuplicateCaseChoose(List<TCeeEnrollUnivListChoose> tCeeEnrollUnivListChooses) {
        Set<TCeeEnrollUnivListChoose> set = new TreeSet<>(new Comparator<TCeeEnrollUnivListChoose>() {
            @Override
            public int compare(TCeeEnrollUnivListChoose tCeeEnrollUnivListUser1, TCeeEnrollUnivListChoose tCeeEnrollUnivListUser2) {
                //字符串,则按照asicc码升序排列
                return tCeeEnrollUnivListUser1.getUnivId().compareTo(tCeeEnrollUnivListUser2.getUnivId());
            }
        });
        set.addAll(tCeeEnrollUnivListChooses);
        return new ArrayList<>(set);
    }

    //    获取各种类型univ_list_user或者univ_list_choose 去重后的list 的条件拼接
    public String getDistinctUnivListUserOrChooseStr(String applicationId, Integer batchId, String pskSet, String intentMajor, String orderByType, String order, Integer limitUnivNum, String chooseOrNot, String tableType) {
        StringBuffer univSql = new StringBuffer();
        univSql.append("SELECT DISTINCT application_id,batch_id,univ_id,univ_code,univ_name FROM " + tableType + " \n" +
                "WHERE application_id = " + applicationId + " AND batch_id = " + batchId + " \n");
//        冲稳保条件
        if (StringUtils.isNotBlank(pskSet)) {
            univSql.append("AND psk_set = '" + pskSet + "' ");
        }
//        意向专业条件
        if (StringUtils.isNotBlank(intentMajor)) {
            univSql.append(" AND major_list_id in (" + intentMajor.replace("|", ",") + ") ");
        }
//        是否选中
        if (StringUtils.isNotBlank(chooseOrNot)) {
            univSql.append(" AND choose_flag = 1 ");
        }

        if (StringUtils.isNotBlank(orderByType)) {
            univSql.append(" ORDER BY " + orderByType + " " + order + " \n ");
        }
        if (limitUnivNum != null) {
            univSql.append(" LIMIT " + limitUnivNum + " ");
        }

        List<Map<String, Object>> univList = commonSvc.findForJdbc(univSql.toString());

        String ABUUU = "";
        for (int i = 0; i < univList.size(); i++) {
            String aa = String.valueOf(univList.get(i).get("application_id"));
            String bb = String.valueOf(univList.get(i).get("batch_id"));
            String cc = String.valueOf(univList.get(i).get("univ_id"));
            String dd = String.valueOf(univList.get(i).get("univ_code"));
            String ee = String.valueOf(univList.get(i).get("univ_name"));
            String MBUStr = " ('" + aa + "','" + bb + "','" + cc + "','" + dd + "','" + ee + "') ";
            if (i != (univList.size() - 1)) {
                MBUStr += ",";
            }
            ABUUU += MBUStr;
        }
        return ABUUU;
    }

    public String getDistinctUnivListChooseXHStr(String applicationId, Integer batchId, String pskSet, String intentMajor, String orderByType, String order, Integer limitUnivNum, String chooseOrNot, String tableType) {
        StringBuffer univSql = new StringBuffer();
        univSql.append("SELECT DISTINCT univ_xh FROM " + tableType + " \n" +
                "WHERE application_id = " + applicationId + " AND batch_id = " + batchId + " \n");
//        冲稳保条件
        if (StringUtils.isNotBlank(pskSet)) {
            univSql.append("AND psk_set = '" + pskSet + "' ");
        }
//        意向专业条件
        if (StringUtils.isNotBlank(intentMajor)) {
            univSql.append(" AND major_list_id REGEXP '" + intentMajor + "' ");
        }
//        是否选中
        if (StringUtils.isNotBlank(chooseOrNot)) {
            univSql.append(" AND chooseFlag = 1 ");
        }

        if (StringUtils.isNotBlank(orderByType)) {
            univSql.append(" ORDER BY " + orderByType + " " + order + " \n ");
        }
        if (limitUnivNum != null) {
            univSql.append(" LIMIT " + limitUnivNum + " ");
        }

        List<Map<String, Object>> univList = commonSvc.findForJdbc(univSql.toString());

        String ABUUU = "";
        for (int i = 0; i < univList.size(); i++) {
            String aa = String.valueOf(univList.get(i).get("application_id"));
            String bb = String.valueOf(univList.get(i).get("batch_id"));
            String cc = String.valueOf(univList.get(i).get("univ_id"));
            String dd = String.valueOf(univList.get(i).get("univ_code"));
            String ee = String.valueOf(univList.get(i).get("univ_name"));
            String MBUStr = " ('" + aa + "','" + bb + "','" + cc + "','" + dd + "','" + ee + "') ";
            if (i != (univList.size() - 1)) {
                MBUStr += ",";
            }
            ABUUU += MBUStr;
        }
        return ABUUU;
    }

    //    获取两个univ_list_user集合的条件拼接
    public String getDistinctUnivListUserStr(List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsers1, List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsers2) {

        List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsers = new ArrayList<>();

        for (TCeeEnrollUnivListUser tCeeEnrollUnivListUser1 : tCeeEnrollUnivListUsers1) {
            tCeeEnrollUnivListUsers.add(tCeeEnrollUnivListUser1);
        }

        for (TCeeEnrollUnivListUser tCeeEnrollUnivListUser2 : tCeeEnrollUnivListUsers2) {
            tCeeEnrollUnivListUsers.add(tCeeEnrollUnivListUser2);
        }

        String ABUUUStr = "";
        for (int i = 0; i < tCeeEnrollUnivListUsers.size(); i++) {
            String aa = String.valueOf(tCeeEnrollUnivListUsers.get(i).getApplicationId());
            String bb = String.valueOf(tCeeEnrollUnivListUsers.get(i).getBatchId());
            String cc = String.valueOf(tCeeEnrollUnivListUsers.get(i).getUnivId());
            String dd = String.valueOf(tCeeEnrollUnivListUsers.get(i).getUnivCode());
            String ee = String.valueOf(tCeeEnrollUnivListUsers.get(i).getUnivName());
            String ABUUU = " ('" + aa + "','" + bb + "','" + cc + "','" + dd + "','" + ee + "') ";
            if (i != (tCeeEnrollUnivListUsers.size() - 1)) {
                ABUUU += ",";
            }
            ABUUUStr += ABUUU;
        }

//        if (tCeeEnrollUnivListUsers1.size() == 0 && tCeeEnrollUnivListUsers2.size() == 0) {
//            ABUUUStr = "nothing";
//        }
        return ABUUUStr;
    }

    //    获取各种类型univ_list_user至院校级别
    public List<TCeeEnrollUnivListUser> getAllUnivListUserDISUniv(String applicationId, Integer batchId, String pskSet, String intentMajor, String intentMajorUnivListStr, String chooseUnivListStr, String inUnivListStr, String orderByType, String order, Integer limitUnivNum, String type) {
        StringBuffer univSql = new StringBuffer();

        univSql.append("SELECT * FROM (\n");
        univSql.append("SELECT * FROM t_cee_enroll_univ_list_user  \n" +
                "WHERE application_id = " + applicationId + " AND batch_id = " + batchId + " \n");
//        冲稳保条件
        if (StringUtils.isNotBlank(pskSet)) {
            univSql.append("AND psk_set = '" + pskSet + "' ");
        }
//        意向专业条件
        if (StringUtils.isNotBlank(intentMajor)) {
            univSql.append(" AND major_list_id in (" + intentMajor.replace("|", ",") + ") ");
//            univHql.append(" AND majorListId REGEXP '" + intentMajor + "' ");
        }
//      意向院校筛选
        if (StringUtils.isNotBlank(intentMajorUnivListStr)) {
            univSql.append(" AND (application_id,batch_id,univ_id,univ_code,univ_name) NOT IN  ( " + intentMajorUnivListStr + " )");
        }
//        已选择筛选
        if (StringUtils.isNotBlank(chooseUnivListStr)) {
            univSql.append(" AND (application_id,batch_id,univ_id,univ_code,univ_name) NOT IN  ( " + chooseUnivListStr + " )");
        }
//        条件相等筛选
        if (StringUtils.isNotBlank(inUnivListStr)) {
            univSql.append(" AND (application_id,batch_id,univ_id,univ_code,univ_name) IN  ( " + inUnivListStr + " )");
        }

        if (StringUtils.isNotBlank(orderByType)) {
            univSql.append(" ORDER BY " + orderByType + " " + order + " \n ");
        }

        univSql.append("\n ) aa \n" +
                "GROUP BY aa.univ_code,aa.univ_id,aa.univ_name\n");

        if (limitUnivNum != null) {
            univSql.append(" LIMIT " + limitUnivNum + " ");
        }

//        List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsersUniv = commonSvc.findByQueryString(univHql.toString());
        List<TCeeEnrollUnivListUser> tCeeEnrollUnivListUsersUniv = commonSvc.findListbySql(univSql.toString(), TCeeEnrollUnivListUser.class);

        return tCeeEnrollUnivListUsersUniv;
    }

}

