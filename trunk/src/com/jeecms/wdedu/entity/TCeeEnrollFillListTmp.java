package com.jeecms.wdedu.entity;

public class TCeeEnrollFillListTmp extends TCeeEnrollFillList  {

    private Integer isQualification;

    public Integer getIsQualification() {
        return isQualification;
    }

    public void setIsQualification(Integer isQualification) {
        this.isQualification = isQualification;
    }

    public void setChild(TCeeEnrollFillList tCeeEnrollFillList) {
        this.id = tCeeEnrollFillList.getId();
        this.univListId = tCeeEnrollFillList.univListId;
        this.year = tCeeEnrollFillList.getYear();
        this.provinceId = tCeeEnrollFillList.getProvinceId();
        this.majorTypeId = tCeeEnrollFillList.getMajorTypeId();
        this.batchId = tCeeEnrollFillList.getBatchId();
        this.batchIdOld = tCeeEnrollFillList.batchIdOld;
        this.oldBatchName = tCeeEnrollFillList.getOldBatchName();
        this.dataType = tCeeEnrollFillList.dataType;
        this.univId = tCeeEnrollFillList.getUnivId();
        this.univCode = tCeeEnrollFillList.getUnivCode();
        this.univName = tCeeEnrollFillList.getUnivName();
        this.majorId = tCeeEnrollFillList.getMajorId();
        this.majorId2 = tCeeEnrollFillList.majorId2;
        this.majorCode = tCeeEnrollFillList.getMajorCode();
        this.majorName = tCeeEnrollFillList.getMajorName();
        this.majorSubjects = tCeeEnrollFillList.getMajorSubjects();
        this.majorSubjectsLevel = tCeeEnrollFillList.getMajorSubjectsLevel();
        this.planNum = tCeeEnrollFillList.getPlanNum();
        this.planFee = tCeeEnrollFillList.getPlanFee();
        this.planSchoolLength = tCeeEnrollFillList.getPlanSchoolLength();
        this.planRemark = tCeeEnrollFillList.getPlanRemark();
        this.histYear1 = tCeeEnrollFillList.getHistYear1();
        this.histNum1 = tCeeEnrollFillList.histNum1;
        this.histLowScore1 = tCeeEnrollFillList.getHistLowScore1();
        this.histLowRank1 = tCeeEnrollFillList.getHistLowRank1();
        this.histAveScore1 = tCeeEnrollFillList.getHistAveScore1();
        this.histAveRank1 = tCeeEnrollFillList.getHistAveRank1();
        this.histHighScore1 = tCeeEnrollFillList.getHistHighScore1();
        this.histHighRank1 = tCeeEnrollFillList.getHistHighRank1();
        this.histYear2 = tCeeEnrollFillList.getHistYear2();
        this.histNum2 = tCeeEnrollFillList.getHistNum2();
        this.histLowScore2 = tCeeEnrollFillList.getHistLowScore2();
        this.histLowRank2 = tCeeEnrollFillList.getHistLowRank2();
        this.histAveScore2 = tCeeEnrollFillList.getHistAveScore2();
        this.histAveRank2 = tCeeEnrollFillList.getHistAveRank2();
        this.histHighScore2 = tCeeEnrollFillList.getHistHighScore2();
        this.histHighRank2 = tCeeEnrollFillList.getHistHighRank2();
        this.histYear3 = tCeeEnrollFillList.getHistYear3();
        this.histNum3 = tCeeEnrollFillList.getHistNum3();
        this.histLowScore3 = tCeeEnrollFillList.getHistLowScore3();
        this.histLowRank3 = tCeeEnrollFillList.getHistLowRank3();
        this.histAveScore3 = tCeeEnrollFillList.getHistAveScore3();
        this.histAveRank3 = tCeeEnrollFillList.getHistAveRank3();
        this.histHighScore3 = tCeeEnrollFillList.getHistHighScore3();
        this.histHighRank3 = tCeeEnrollFillList.getHistHighRank3();
        this.remark = tCeeEnrollFillList.getRemark();
        this.majorRate = tCeeEnrollFillList.getMajorRate();
        this.majorClass = tCeeEnrollFillList.majorClass;
        this.majorRank = tCeeEnrollFillList.getMajorRank();
        this.oldUnivListId = tCeeEnrollFillList.getOldUnivListId();
        this.majorSubjectsRemark = tCeeEnrollFillList.getMajorSubjectsRemark();
        this.univMajorGroup = tCeeEnrollFillList.univMajorGroup;
        this.histNum11 = tCeeEnrollFillList.getHistNum11();
        this.histLowScore11 = tCeeEnrollFillList.getHistLowScore11();
        this.histLowRank11 = tCeeEnrollFillList.getHistLowRank11();
        this.histNum21 = tCeeEnrollFillList.getHistNum21();
        this.histLowScore21 = tCeeEnrollFillList.getHistLowScore21();
        this.histLowRank21 = tCeeEnrollFillList.getHistLowRank21();
        this.histNum31 = tCeeEnrollFillList.getHistNum31();
        this.histLowScore31 = tCeeEnrollFillList.getHistLowScore31();
        this.histLowRank31 = tCeeEnrollFillList.getHistLowRank31();
    }

}