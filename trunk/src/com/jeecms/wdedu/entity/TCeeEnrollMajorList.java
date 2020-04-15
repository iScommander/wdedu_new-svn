package com.jeecms.wdedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_cee_enroll_major_list", schema = "wodecareer", catalog = "")
public class TCeeEnrollMajorList {
    private int id;
    private int univListId;
    private int planOrHistory;
    private Integer year;
    private Integer provinceId;
    private Integer majorTypeId;
    private Integer batchId;
    private Integer dataType;
    private String univId;
    private String univCode;
    private String univName;
    private String majorId;
    private String majorCode;
    private String majorName;
    private String majorSubjects;
    private String majorSubjectsLevel;
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
    private Integer majorRate;
    private String majorClass;
    private String majorRank;

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
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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
    @Column(name = "univ_id")
    public String getUnivId() {
        return univId;
    }

    public void setUnivId(String univId) {
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

    @Basic
    @Column(name = "major_rate")
    public Integer getMajorRate() {
        return majorRate;
    }

    public void setMajorRate(Integer majorRate) {
        this.majorRate = majorRate;
    }

    @Basic
    @Column(name = "major_class")
    public String getMajorClass() {
        return majorClass;
    }

    public void setMajorClass(String majorClass) {
        this.majorClass = majorClass;
    }

    @Basic
    @Column(name = "major_rank")
    public String getMajorRank() {
        return majorRank;
    }

    public void setMajorRank(String majorRank) {
        this.majorRank = majorRank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TCeeEnrollMajorList that = (TCeeEnrollMajorList) o;

        if (id != that.id) return false;
        if (univListId != that.univListId) return false;
        if (planOrHistory != that.planOrHistory) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (provinceId != null ? !provinceId.equals(that.provinceId) : that.provinceId != null) return false;
        if (majorTypeId != null ? !majorTypeId.equals(that.majorTypeId) : that.majorTypeId != null) return false;
        if (batchId != null ? !batchId.equals(that.batchId) : that.batchId != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (univId != null ? !univId.equals(that.univId) : that.univId != null) return false;
        if (univCode != null ? !univCode.equals(that.univCode) : that.univCode != null) return false;
        if (univName != null ? !univName.equals(that.univName) : that.univName != null) return false;
        if (majorId != null ? !majorId.equals(that.majorId) : that.majorId != null) return false;
        if (majorCode != null ? !majorCode.equals(that.majorCode) : that.majorCode != null) return false;
        if (majorName != null ? !majorName.equals(that.majorName) : that.majorName != null) return false;
        if (majorSubjects != null ? !majorSubjects.equals(that.majorSubjects) : that.majorSubjects != null)
            return false;
        if (majorSubjectsLevel != null ? !majorSubjectsLevel.equals(that.majorSubjectsLevel) : that.majorSubjectsLevel != null)
            return false;
        if (planNum != null ? !planNum.equals(that.planNum) : that.planNum != null) return false;
        if (planFee != null ? !planFee.equals(that.planFee) : that.planFee != null) return false;
        if (planSchoolLength != null ? !planSchoolLength.equals(that.planSchoolLength) : that.planSchoolLength != null)
            return false;
        if (planRemark != null ? !planRemark.equals(that.planRemark) : that.planRemark != null) return false;
        if (histNum != null ? !histNum.equals(that.histNum) : that.histNum != null) return false;
        if (histLowScore != null ? !histLowScore.equals(that.histLowScore) : that.histLowScore != null) return false;
        if (histLowRank != null ? !histLowRank.equals(that.histLowRank) : that.histLowRank != null) return false;
        if (histAveScore != null ? !histAveScore.equals(that.histAveScore) : that.histAveScore != null) return false;
        if (histAveRank != null ? !histAveRank.equals(that.histAveRank) : that.histAveRank != null) return false;
        if (histHighScore != null ? !histHighScore.equals(that.histHighScore) : that.histHighScore != null)
            return false;
        if (histHighRank != null ? !histHighRank.equals(that.histHighRank) : that.histHighRank != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (majorRate != null ? !majorRate.equals(that.majorRate) : that.majorRate != null) return false;
        if (majorClass != null ? !majorClass.equals(that.majorClass) : that.majorClass != null) return false;
        if (majorRank != null ? !majorRank.equals(that.majorRank) : that.majorRank != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + univListId;
        result = 31 * result + planOrHistory;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (provinceId != null ? provinceId.hashCode() : 0);
        result = 31 * result + (majorTypeId != null ? majorTypeId.hashCode() : 0);
        result = 31 * result + (batchId != null ? batchId.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (univId != null ? univId.hashCode() : 0);
        result = 31 * result + (univCode != null ? univCode.hashCode() : 0);
        result = 31 * result + (univName != null ? univName.hashCode() : 0);
        result = 31 * result + (majorId != null ? majorId.hashCode() : 0);
        result = 31 * result + (majorCode != null ? majorCode.hashCode() : 0);
        result = 31 * result + (majorName != null ? majorName.hashCode() : 0);
        result = 31 * result + (majorSubjects != null ? majorSubjects.hashCode() : 0);
        result = 31 * result + (majorSubjectsLevel != null ? majorSubjectsLevel.hashCode() : 0);
        result = 31 * result + (planNum != null ? planNum.hashCode() : 0);
        result = 31 * result + (planFee != null ? planFee.hashCode() : 0);
        result = 31 * result + (planSchoolLength != null ? planSchoolLength.hashCode() : 0);
        result = 31 * result + (planRemark != null ? planRemark.hashCode() : 0);
        result = 31 * result + (histNum != null ? histNum.hashCode() : 0);
        result = 31 * result + (histLowScore != null ? histLowScore.hashCode() : 0);
        result = 31 * result + (histLowRank != null ? histLowRank.hashCode() : 0);
        result = 31 * result + (histAveScore != null ? histAveScore.hashCode() : 0);
        result = 31 * result + (histAveRank != null ? histAveRank.hashCode() : 0);
        result = 31 * result + (histHighScore != null ? histHighScore.hashCode() : 0);
        result = 31 * result + (histHighRank != null ? histHighRank.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (majorRate != null ? majorRate.hashCode() : 0);
        result = 31 * result + (majorClass != null ? majorClass.hashCode() : 0);
        result = 31 * result + (majorRank != null ? majorRank.hashCode() : 0);
        return result;
    }
}
