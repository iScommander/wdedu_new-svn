package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_cee_enroll_univ_list", schema = "wodecareer", catalog = "")
public class TCeeEnrollUnivList_bak {
    private int id;
    private int year;
    private int provinceId;
    private int majorTypeId;
    private int batchId;
    private Integer oldBatchId;
    private Integer univId;
    private String univCode;
    private String univName;
    private Integer univProvince;
    private String univCity;
    private Integer univTestName;
    private Integer univTestLevel;
    private String univType;
    private Integer univTypeid;
    private Integer univIs985;
    private Integer univIs211;
    private Integer univIsFirstRateUniv;
    private Integer univIsFirstRateMajor;
    private Integer changePlanNum;
    private Integer enrolledYear1;
    private Integer enrolledNum1;
    private Integer scoreLow1;
    private Integer rankScoreLow1;
    private Integer enrolledYear2;
    private Integer enrolledNum2;
    private Integer scoreLow2;
    private Integer rankScoreLow2;
    private Integer enrolledYear3;
    private Integer enrolledNum3;
    private Integer scoreLow3;
    private Integer rankScoreLow3;
    private Integer enrolledYear4;
    private Integer enrolledNum4;
    private Integer scoreLow4;
    private Integer rankScoreLow4;
    private Integer enrollPlanId;
    private Integer enrollHistoryId1;
    private Integer enrollHistoryId2;
    private Integer enrollHistoryId3;
    private Integer enrollHistoryId4;
    private Integer univIsKeylab;
    private Integer univIsExcellent;
    private Integer univIsDefence;
    private Integer univIsArtStudent;
    private Integer univIsIndependence;
    private Integer univRank;
    private String oldBatchName;
    private Integer univMajorNum;
    private Integer planedChangedNum;
    private Integer oldUnivListId;
    private String logoUrl;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "province_id")
    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    @Basic
    @Column(name = "major_type_id")
    public int getMajorTypeId() {
        return majorTypeId;
    }

    public void setMajorTypeId(Integer majorTypeId) {
        this.majorTypeId = majorTypeId;
    }

    public void setMajorTypeId(int majorTypeId) {
        this.majorTypeId = majorTypeId;
    }

    @Basic
    @Column(name = "batch_id")
    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    @Basic
    @Column(name = "old_batch_id")
    public Integer getOldBatchId() {
        return oldBatchId;
    }

    public void setOldBatchId(Integer oldBatchId) {
        this.oldBatchId = oldBatchId;
    }

    @Basic
    @Column(name = "univ_id")
    public Integer getUnivId() {
        return univId;
    }

    public void setUnivId(Integer univId) {
        this.univId = univId;
    }

    @Basic
    @Column(name = "univ_code")
    public String getUnivCode() {
        return univCode;
    }

    public void setUnivCode(String univCode) {
        this.univCode = univCode;
    }

    @Basic
    @Column(name = "univ_name")
    public String getUnivName() {
        return univName;
    }

    public void setUnivName(String univName) {
        this.univName = univName;
    }

    @Basic
    @Column(name = "univ_province")
    public Integer getUnivProvince() {
        return univProvince;
    }

    public void setUnivProvince(Integer univProvince) {
        this.univProvince = univProvince;
    }

    @Basic
    @Column(name = "univ_city")
    public String getUnivCity() {
        return univCity;
    }

    public void setUnivCity(String univCity) {
        this.univCity = univCity;
    }

    @Basic
    @Column(name = "univ_test_name")
    public Integer getUnivTestName() {
        return univTestName;
    }

    public void setUnivTestName(Integer univTestName) {
        this.univTestName = univTestName;
    }

    @Basic
    @Column(name = "univ_test_level")
    public Integer getUnivTestLevel() {
        return univTestLevel;
    }

    public void setUnivTestLevel(Integer univTestLevel) {
        this.univTestLevel = univTestLevel;
    }

    @Basic
    @Column(name = "univ_type")
    public String getUnivType() {
        return univType;
    }

    public void setUnivType(String univType) {
        this.univType = univType;
    }

    @Basic
    @Column(name = "univ_typeid")
    public Integer getUnivTypeid() {
        return univTypeid;
    }

    public void setUnivTypeid(Integer univTypeid) {
        this.univTypeid = univTypeid;
    }

    @Basic
    @Column(name = "univ_is985")
    public Integer getUnivIs985() {
        return univIs985;
    }

    public void setUnivIs985(Integer univIs985) {
        this.univIs985 = univIs985;
    }

    @Basic
    @Column(name = "univ_is211")
    public Integer getUnivIs211() {
        return univIs211;
    }

    public void setUnivIs211(Integer univIs211) {
        this.univIs211 = univIs211;
    }

    @Basic
    @Column(name = "univ_isFirstRateUniv")
    public Integer getUnivIsFirstRateUniv() {
        return univIsFirstRateUniv;
    }

    public void setUnivIsFirstRateUniv(Integer univIsFirstRateUniv) {
        this.univIsFirstRateUniv = univIsFirstRateUniv;
    }

    @Basic
    @Column(name = "univ_isFirstRateMajor")
    public Integer getUnivIsFirstRateMajor() {
        return univIsFirstRateMajor;
    }

    public void setUnivIsFirstRateMajor(Integer univIsFirstRateMajor) {
        this.univIsFirstRateMajor = univIsFirstRateMajor;
    }

    @Basic
    @Column(name = "change_plan_num")
    public Integer getChangePlanNum() {
        return changePlanNum;
    }

    public void setChangePlanNum(Integer changePlanNum) {
        this.changePlanNum = changePlanNum;
    }

    @Basic
    @Column(name = "enrolled_year_1")
    public Integer getEnrolledYear1() {
        return enrolledYear1;
    }

    public void setEnrolledYear1(Integer enrolledYear1) {
        this.enrolledYear1 = enrolledYear1;
    }

    @Basic
    @Column(name = "enrolled_num_1")
    public Integer getEnrolledNum1() {
        return enrolledNum1;
    }

    public void setEnrolledNum1(Integer enrolledNum1) {
        this.enrolledNum1 = enrolledNum1;
    }

    @Basic
    @Column(name = "score_low_1")
    public Integer getScoreLow1() {
        return scoreLow1;
    }

    public void setScoreLow1(Integer scoreLow1) {
        this.scoreLow1 = scoreLow1;
    }

    @Basic
    @Column(name = "rank_score_low_1")
    public Integer getRankScoreLow1() {
        return rankScoreLow1;
    }

    public void setRankScoreLow1(Integer rankScoreLow1) {
        this.rankScoreLow1 = rankScoreLow1;
    }

    @Basic
    @Column(name = "enrolled_year_2")
    public Integer getEnrolledYear2() {
        return enrolledYear2;
    }

    public void setEnrolledYear2(Integer enrolledYear2) {
        this.enrolledYear2 = enrolledYear2;
    }

    @Basic
    @Column(name = "enrolled_num_2")
    public Integer getEnrolledNum2() {
        return enrolledNum2;
    }

    public void setEnrolledNum2(Integer enrolledNum2) {
        this.enrolledNum2 = enrolledNum2;
    }

    @Basic
    @Column(name = "score_low_2")
    public Integer getScoreLow2() {
        return scoreLow2;
    }

    public void setScoreLow2(Integer scoreLow2) {
        this.scoreLow2 = scoreLow2;
    }

    @Basic
    @Column(name = "rank_score_low_2")
    public Integer getRankScoreLow2() {
        return rankScoreLow2;
    }

    public void setRankScoreLow2(Integer rankScoreLow2) {
        this.rankScoreLow2 = rankScoreLow2;
    }

    @Basic
    @Column(name = "enrolled_year_3")
    public Integer getEnrolledYear3() {
        return enrolledYear3;
    }

    public void setEnrolledYear3(Integer enrolledYear3) {
        this.enrolledYear3 = enrolledYear3;
    }

    @Basic
    @Column(name = "enrolled_num_3")
    public Integer getEnrolledNum3() {
        return enrolledNum3;
    }

    public void setEnrolledNum3(Integer enrolledNum3) {
        this.enrolledNum3 = enrolledNum3;
    }

    @Basic
    @Column(name = "score_low_3")
    public Integer getScoreLow3() {
        return scoreLow3;
    }

    public void setScoreLow3(Integer scoreLow3) {
        this.scoreLow3 = scoreLow3;
    }

    @Basic
    @Column(name = "rank_score_low_3")
    public Integer getRankScoreLow3() {
        return rankScoreLow3;
    }

    public void setRankScoreLow3(Integer rankScoreLow3) {
        this.rankScoreLow3 = rankScoreLow3;
    }

    @Basic
    @Column(name = "enrolled_year_4")
    public Integer getEnrolledYear4() {
        return enrolledYear4;
    }

    public void setEnrolledYear4(Integer enrolledYear4) {
        this.enrolledYear4 = enrolledYear4;
    }

    @Basic
    @Column(name = "enrolled_num_4")
    public Integer getEnrolledNum4() {
        return enrolledNum4;
    }

    public void setEnrolledNum4(Integer enrolledNum4) {
        this.enrolledNum4 = enrolledNum4;
    }

    @Basic
    @Column(name = "score_low_4")
    public Integer getScoreLow4() {
        return scoreLow4;
    }

    public void setScoreLow4(Integer scoreLow4) {
        this.scoreLow4 = scoreLow4;
    }

    @Basic
    @Column(name = "rank_score_low_4")
    public Integer getRankScoreLow4() {
        return rankScoreLow4;
    }

    public void setRankScoreLow4(Integer rankScoreLow4) {
        this.rankScoreLow4 = rankScoreLow4;
    }

    @Basic
    @Column(name = "enroll_plan_id")
    public Integer getEnrollPlanId() {
        return enrollPlanId;
    }

    public void setEnrollPlanId(Integer enrollPlanId) {
        this.enrollPlanId = enrollPlanId;
    }

    @Basic
    @Column(name = "enroll_history_id_1")
    public Integer getEnrollHistoryId1() {
        return enrollHistoryId1;
    }

    public void setEnrollHistoryId1(Integer enrollHistoryId1) {
        this.enrollHistoryId1 = enrollHistoryId1;
    }

    @Basic
    @Column(name = "enroll_history_id_2")
    public Integer getEnrollHistoryId2() {
        return enrollHistoryId2;
    }

    public void setEnrollHistoryId2(Integer enrollHistoryId2) {
        this.enrollHistoryId2 = enrollHistoryId2;
    }

    @Basic
    @Column(name = "enroll_history_id_3")
    public Integer getEnrollHistoryId3() {
        return enrollHistoryId3;
    }

    public void setEnrollHistoryId3(Integer enrollHistoryId3) {
        this.enrollHistoryId3 = enrollHistoryId3;
    }

    @Basic
    @Column(name = "enroll_history_id_4")
    public Integer getEnrollHistoryId4() {
        return enrollHistoryId4;
    }

    public void setEnrollHistoryId4(Integer enrollHistoryId4) {
        this.enrollHistoryId4 = enrollHistoryId4;
    }

    @Basic
    @Column(name = "univ_isKeylab")
    public Integer getUnivIsKeylab() {
        return univIsKeylab;
    }

    public void setUnivIsKeylab(Integer univIsKeylab) {
        this.univIsKeylab = univIsKeylab;
    }

    @Basic
    @Column(name = "univ_isExcellent")
    public Integer getUnivIsExcellent() {
        return univIsExcellent;
    }

    public void setUnivIsExcellent(Integer univIsExcellent) {
        this.univIsExcellent = univIsExcellent;
    }

    @Basic
    @Column(name = "univ_isDefence")
    public Integer getUnivIsDefence() {
        return univIsDefence;
    }

    public void setUnivIsDefence(Integer univIsDefence) {
        this.univIsDefence = univIsDefence;
    }

    @Basic
    @Column(name = "univ_isArtStudent")
    public Integer getUnivIsArtStudent() {
        return univIsArtStudent;
    }

    public void setUnivIsArtStudent(Integer univIsArtStudent) {
        this.univIsArtStudent = univIsArtStudent;
    }

    @Basic
    @Column(name = "univ_isIndependence")
    public Integer getUnivIsIndependence() {
        return univIsIndependence;
    }

    public void setUnivIsIndependence(Integer univIsIndependence) {
        this.univIsIndependence = univIsIndependence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TCeeEnrollUnivList_bak that = (TCeeEnrollUnivList_bak) o;
        return id == that.id &&
                year == that.year &&
                provinceId == that.provinceId &&
                majorTypeId == that.majorTypeId &&
                batchId == that.batchId &&
                Objects.equals(oldBatchId, that.oldBatchId) &&
                Objects.equals(univId, that.univId) &&
                Objects.equals(univCode, that.univCode) &&
                Objects.equals(univName, that.univName) &&
                Objects.equals(univProvince, that.univProvince) &&
                Objects.equals(univCity, that.univCity) &&
                Objects.equals(univTestName, that.univTestName) &&
                Objects.equals(univTestLevel, that.univTestLevel) &&
                Objects.equals(univType, that.univType) &&
                Objects.equals(univTypeid, that.univTypeid) &&
                Objects.equals(univIs985, that.univIs985) &&
                Objects.equals(univIs211, that.univIs211) &&
                Objects.equals(univIsFirstRateUniv, that.univIsFirstRateUniv) &&
                Objects.equals(univIsFirstRateMajor, that.univIsFirstRateMajor) &&
                Objects.equals(changePlanNum, that.changePlanNum) &&
                Objects.equals(enrolledYear1, that.enrolledYear1) &&
                Objects.equals(enrolledNum1, that.enrolledNum1) &&
                Objects.equals(scoreLow1, that.scoreLow1) &&
                Objects.equals(rankScoreLow1, that.rankScoreLow1) &&
                Objects.equals(enrolledYear2, that.enrolledYear2) &&
                Objects.equals(enrolledNum2, that.enrolledNum2) &&
                Objects.equals(scoreLow2, that.scoreLow2) &&
                Objects.equals(rankScoreLow2, that.rankScoreLow2) &&
                Objects.equals(enrolledYear3, that.enrolledYear3) &&
                Objects.equals(enrolledNum3, that.enrolledNum3) &&
                Objects.equals(scoreLow3, that.scoreLow3) &&
                Objects.equals(rankScoreLow3, that.rankScoreLow3) &&
                Objects.equals(enrolledYear4, that.enrolledYear4) &&
                Objects.equals(enrolledNum4, that.enrolledNum4) &&
                Objects.equals(scoreLow4, that.scoreLow4) &&
                Objects.equals(rankScoreLow4, that.rankScoreLow4) &&
                Objects.equals(enrollPlanId, that.enrollPlanId) &&
                Objects.equals(enrollHistoryId1, that.enrollHistoryId1) &&
                Objects.equals(enrollHistoryId2, that.enrollHistoryId2) &&
                Objects.equals(enrollHistoryId3, that.enrollHistoryId3) &&
                Objects.equals(enrollHistoryId4, that.enrollHistoryId4) &&
                Objects.equals(univIsKeylab, that.univIsKeylab) &&
                Objects.equals(univIsExcellent, that.univIsExcellent) &&
                Objects.equals(univIsDefence, that.univIsDefence) &&
                Objects.equals(univIsArtStudent, that.univIsArtStudent) &&
                Objects.equals(univIsIndependence, that.univIsIndependence);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, year, provinceId, majorTypeId, batchId, oldBatchId, univId, univCode, univName, univProvince, univCity, univTestName, univTestLevel, univType, univTypeid, univIs985, univIs211, univIsFirstRateUniv, univIsFirstRateMajor, changePlanNum, enrolledYear1, enrolledNum1, scoreLow1, rankScoreLow1, enrolledYear2, enrolledNum2, scoreLow2, rankScoreLow2, enrolledYear3, enrolledNum3, scoreLow3, rankScoreLow3, enrolledYear4, enrolledNum4, scoreLow4, rankScoreLow4, enrollPlanId, enrollHistoryId1, enrollHistoryId2, enrollHistoryId3, enrollHistoryId4, univIsKeylab, univIsExcellent, univIsDefence, univIsArtStudent, univIsIndependence);
    }

    @Basic
    @Column(name = "univ_rank")
    public Integer getUnivRank() {
        return univRank;
    }

    public void setUnivRank(Integer univRank) {
        this.univRank = univRank;
    }

    @Basic
    @Column(name = "old_batch_name")
    public String getOldBatchName() {
        return oldBatchName;
    }

    public void setOldBatchName(String oldBatchName) {
        this.oldBatchName = oldBatchName;
    }

    @Basic
    @Column(name = "univ_major_num")
    public Integer getUnivMajorNum() {
        return univMajorNum;
    }

    public void setUnivMajorNum(Integer univMajorNum) {
        this.univMajorNum = univMajorNum;
    }

    @Basic
    @Column(name = "planed_changed_num")
    public Integer getPlanedChangedNum() {
        return planedChangedNum;
    }

    public void setPlanedChangedNum(Integer planedChangedNum) {
        this.planedChangedNum = planedChangedNum;
    }

    @Basic
    @Column(name = "old_univ_list_id")
    public Integer getOldUnivListId() {
        return oldUnivListId;
    }

    public void setOldUnivListId(Integer oldUnivListId) {
        this.oldUnivListId = oldUnivListId;
    }

    @Basic
    @Column(name = "logo_url")
    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
