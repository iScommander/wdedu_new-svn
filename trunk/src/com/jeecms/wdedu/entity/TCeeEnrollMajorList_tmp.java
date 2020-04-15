package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_cee_enroll_major_list", schema = "wodecareer", catalog = "")
public class TCeeEnrollMajorList_tmp {
    private int id;
    private int univListId;
    private int planOrHistory;
    private int year;
    private int univId;
    private String univCode;
    private String univName;
    private String majorId;
    private String majorCode;
    private String majorName;
    private Integer planNum;
    private String planFee;
    private String planSchoolLength;
    private String planRemark;
    private Integer histNum;
    private Integer histLowScore;
    private Integer histLowRank;
    private Integer histAveScore;
    private Integer histAveRank;
    private Integer histHighScore;
    private Integer histHighRank;
    private String remark;
    private Integer provinceId;
    private Integer majorTypeId;
    private Integer batchId;
    private Integer dataType;
    private String majorSubjects;
    private String majorSubjectsLevel;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "univ_list_id")
    public int getUnivListId() {
        return univListId;
    }

    public void setUnivListId(int univListId) {
        this.univListId = univListId;
    }

    @Basic
    @Column(name = "plan_or_history")
    public int getPlanOrHistory() {
        return planOrHistory;
    }

    public void setPlanOrHistory(int planOrHistory) {
        this.planOrHistory = planOrHistory;
    }

    @Basic
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "univ_id")
    public int getUnivId() {
        return univId;
    }

    public void setUnivId(int univId) {
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
    @Column(name = "major_id")
    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    @Basic
    @Column(name = "major_code")
    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    @Basic
    @Column(name = "major_name")
    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Basic
    @Column(name = "plan_num")
    public Integer getPlanNum() {
        return planNum;
    }

    public void setPlanNum(Integer planNum) {
        this.planNum = planNum;
    }

    @Basic
    @Column(name = "plan_fee")
    public String getPlanFee() {
        return planFee;
    }

    public void setPlanFee(String planFee) {
        this.planFee = planFee;
    }

    @Basic
    @Column(name = "plan_school_length")
    public String getPlanSchoolLength() {
        return planSchoolLength;
    }

    public void setPlanSchoolLength(String planSchoolLength) {
        this.planSchoolLength = planSchoolLength;
    }

    @Basic
    @Column(name = "plan_remark")
    public String getPlanRemark() {
        return planRemark;
    }

    public void setPlanRemark(String planRemark) {
        this.planRemark = planRemark;
    }

    @Basic
    @Column(name = "hist_num")
    public Integer getHistNum() {
        return histNum;
    }

    public void setHistNum(Integer histNum) {
        this.histNum = histNum;
    }

    @Basic
    @Column(name = "hist_low_score")
    public Integer getHistLowScore() {
        return histLowScore;
    }

    public void setHistLowScore(Integer histLowScore) {
        this.histLowScore = histLowScore;
    }

    @Basic
    @Column(name = "hist_low_rank")
    public Integer getHistLowRank() {
        return histLowRank;
    }

    public void setHistLowRank(Integer histLowRank) {
        this.histLowRank = histLowRank;
    }

    @Basic
    @Column(name = "hist_ave_score")
    public Integer getHistAveScore() {
        return histAveScore;
    }

    public void setHistAveScore(Integer histAveScore) {
        this.histAveScore = histAveScore;
    }

    @Basic
    @Column(name = "hist_ave_rank")
    public Integer getHistAveRank() {
        return histAveRank;
    }

    public void setHistAveRank(Integer histAveRank) {
        this.histAveRank = histAveRank;
    }

    @Basic
    @Column(name = "hist_high_score")
    public Integer getHistHighScore() {
        return histHighScore;
    }

    public void setHistHighScore(Integer histHighScore) {
        this.histHighScore = histHighScore;
    }

    @Basic
    @Column(name = "hist_high_rank")
    public Integer getHistHighRank() {
        return histHighRank;
    }

    public void setHistHighRank(Integer histHighRank) {
        this.histHighRank = histHighRank;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TCeeEnrollMajorList_tmp that = (TCeeEnrollMajorList_tmp) o;
        return id == that.id &&
                univListId == that.univListId &&
                planOrHistory == that.planOrHistory &&
                year == that.year &&
                univId == that.univId &&
                Objects.equals(univCode, that.univCode) &&
                Objects.equals(univName, that.univName) &&
                Objects.equals(majorId, that.majorId) &&
                Objects.equals(majorCode, that.majorCode) &&
                Objects.equals(majorName, that.majorName) &&
                Objects.equals(planNum, that.planNum) &&
                Objects.equals(planFee, that.planFee) &&
                Objects.equals(planSchoolLength, that.planSchoolLength) &&
                Objects.equals(planRemark, that.planRemark) &&
                Objects.equals(histNum, that.histNum) &&
                Objects.equals(histLowScore, that.histLowScore) &&
                Objects.equals(histLowRank, that.histLowRank) &&
                Objects.equals(histAveScore, that.histAveScore) &&
                Objects.equals(histAveRank, that.histAveRank) &&
                Objects.equals(histHighScore, that.histHighScore) &&
                Objects.equals(histHighRank, that.histHighRank) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, univListId, planOrHistory, year, univId, univCode, univName, majorId, majorCode, majorName, planNum, planFee, planSchoolLength, planRemark, histNum, histLowScore, histLowRank, histAveScore, histAveRank, histHighScore, histHighRank, remark);
    }

    @Basic
    @Column(name = "province_id")
    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @Basic
    @Column(name = "major_type_id")
    public Integer getMajorTypeId() {
        return majorTypeId;
    }

    public void setMajorTypeId(Integer majorTypeId) {
        this.majorTypeId = majorTypeId;
    }

    @Basic
    @Column(name = "batch_id")
    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    @Basic
    @Column(name = "data_type")
    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    @Basic
    @Column(name = "major_subjects")
    public String getMajorSubjects() {
        return majorSubjects;
    }

    public void setMajorSubjects(String majorSubjects) {
        this.majorSubjects = majorSubjects;
    }

    @Basic
    @Column(name = "major_subjects_level")
    public String getMajorSubjectsLevel() {
        return majorSubjectsLevel;
    }

    public void setMajorSubjectsLevel(String majorSubjectsLevel) {
        this.majorSubjectsLevel = majorSubjectsLevel;
    }
}
