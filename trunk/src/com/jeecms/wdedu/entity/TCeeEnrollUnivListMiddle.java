package com.jeecms.wdedu.entity;

//univ_list 中间类
public class TCeeEnrollUnivListMiddle extends TCeeEnrollUnivList {

    private String pskSet;

    public String getpskSet() {
        return pskSet;
    }

    public void setpskSet(String pskSet) {
        this.pskSet = pskSet;
    }

    public void childSet(TCeeEnrollUnivList tCeeEnrollUnivList) {
        this.setId(tCeeEnrollUnivList.getId());
        this.setYear(tCeeEnrollUnivList.getYear());
        this.setProvinceId(tCeeEnrollUnivList.getProvinceId());
        this.setMajorTypeId(tCeeEnrollUnivList.getMajorTypeId());
        this.setBatchId(tCeeEnrollUnivList.getBatchId());
        this.setOldBatchId(tCeeEnrollUnivList.getOldBatchId());
        this.setUnivId(tCeeEnrollUnivList.getUnivId());
        this.setUnivCode(tCeeEnrollUnivList.getUnivCode());
        this.setUnivName(tCeeEnrollUnivList.getUnivName());
        this.setUnivProvince(tCeeEnrollUnivList.getUnivProvince());
        this.setUnivCity(tCeeEnrollUnivList.getUnivCity());
        this.setUnivTestName(tCeeEnrollUnivList.getUnivTestName());
        this.setUnivTestLevel(tCeeEnrollUnivList.getUnivTestLevel());
        this.setUnivType(tCeeEnrollUnivList.getUnivType());
        this.setUnivTypeid(tCeeEnrollUnivList.getUnivTypeid());
        this.setUnivIs985(tCeeEnrollUnivList.getUnivIs985());
        this.setUnivIs211(tCeeEnrollUnivList.getUnivIs211());
        this.setUnivIsFirstRateUniv(tCeeEnrollUnivList.getUnivIsFirstRateUniv());
        this.setUnivIsFirstRateMajor(tCeeEnrollUnivList.getUnivIsFirstRateMajor());
        this.setChangePlanNum(tCeeEnrollUnivList.getChangePlanNum());
        this.setEnrolledYear1(tCeeEnrollUnivList.getEnrolledYear1());
        this.setEnrolledNum1(tCeeEnrollUnivList.getEnrolledNum1());
        this.setScoreLow1(tCeeEnrollUnivList.getScoreLow1());
        this.setRankScoreLow1(tCeeEnrollUnivList.getRankScoreLow1());
        this.setEnrolledYear2(tCeeEnrollUnivList.getEnrolledYear2());
        this.setEnrolledNum2(tCeeEnrollUnivList.getEnrolledNum2());
        this.setScoreLow2(tCeeEnrollUnivList.getScoreLow2());
        this.setRankScoreLow2(tCeeEnrollUnivList.getRankScoreLow2());
        this.setEnrolledYear3(tCeeEnrollUnivList.getEnrolledYear3());
        this.setEnrolledNum3(tCeeEnrollUnivList.getEnrolledNum3());
        this.setScoreLow3(tCeeEnrollUnivList.getScoreLow3());
        this.setRankScoreLow3(tCeeEnrollUnivList.getRankScoreLow3());
        this.setEnrolledYear4(tCeeEnrollUnivList.getEnrolledYear4());
        this.setEnrolledNum4(tCeeEnrollUnivList.getEnrolledNum4());
        this.setScoreLow4(tCeeEnrollUnivList.getScoreLow4());
        this.setRankScoreLow4(tCeeEnrollUnivList.getRankScoreLow4());
        this.setEnrollPlanId(tCeeEnrollUnivList.getEnrollPlanId());
        this.setEnrollHistoryId1(tCeeEnrollUnivList.getEnrollHistoryId1());
        this.setEnrollHistoryId2(tCeeEnrollUnivList.getEnrollHistoryId2());
        this.setEnrollHistoryId3(tCeeEnrollUnivList.getEnrollHistoryId3());
        this.setEnrollHistoryId4(tCeeEnrollUnivList.getEnrollHistoryId4());
        this.setUnivIsKeylab(tCeeEnrollUnivList.getUnivIsKeylab());
        this.setUnivIsExcellent(tCeeEnrollUnivList.getUnivIsExcellent());
        this.setUnivIsDefence(tCeeEnrollUnivList.getUnivIsDefence());
        this.setUnivIsArtStudent(tCeeEnrollUnivList.getUnivIsArtStudent());
        this.setUnivIsIndependence(tCeeEnrollUnivList.getUnivIsIndependence());
        this.setUnivRank(tCeeEnrollUnivList.getUnivRank());
        this.setOldBatchName(tCeeEnrollUnivList.getOldBatchName());
        this.setUnivMajorNum(tCeeEnrollUnivList.getUnivMajorNum());
        this.setPlanedChangedNum(tCeeEnrollUnivList.getPlanedChangedNum());
        this.setOldUnivListId(tCeeEnrollUnivList.getOldUnivListId());




    }

}
