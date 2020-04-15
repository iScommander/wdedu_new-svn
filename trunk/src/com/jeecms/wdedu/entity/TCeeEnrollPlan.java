package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_cee_enroll_plan", schema = "wodecareer", catalog = "")
@IdClass(TCeeEnrollPlanPK.class)
public class TCeeEnrollPlan {
    private int id;
    private Integer year;
    private Integer provinceId;
    private Integer majorTypeId;
    private Integer batchId;
    private Integer batchIdOld;
    private Integer planNum;
    private Integer planType;
    private String relationId;
    private String univId;
    private String univCode;
    private String univName;
    private String univRedirect;
    private Integer univSubjectsNum;
    private String univSubjects;
    private String univSubjectsLevel;
    private String univRemark;
    private String majorId;
    private String majorCode;
    private String majorName;
    private String majorFee;
    private String majorSchoolLength;
    private Integer majorSubjectsNum;
    private String majorSubjects;
    private String majorSubjectsLevel;
    private String majorRemark;
    private Integer majorRate;
    private String majorClass;
    private String majorRank;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "year", nullable = true)
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "province_id", nullable = true)
    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @Basic
    @Column(name = "major_type_id", nullable = true)
    public Integer getMajorTypeId() {
        return majorTypeId;
    }

    public void setMajorTypeId(Integer majorTypeId) {
        this.majorTypeId = majorTypeId;
    }

    @Basic
    @Column(name = "batch_id", nullable = true)
    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    @Basic
    @Column(name = "batch_id_old", nullable = true)
    public Integer getBatchIdOld() {
        return batchIdOld;
    }

    public void setBatchIdOld(Integer batchIdOld) {
        this.batchIdOld = batchIdOld;
    }

    @Basic
    @Column(name = "plan_num", nullable = true)
    public Integer getPlanNum() {
        return planNum;
    }

    public void setPlanNum(Integer planNum) {
        this.planNum = planNum;
    }

    @Basic
    @Column(name = "plan_type", nullable = true)
    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    @Basic
    @Column(name = "relation_id", nullable = true, length = 100)
    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    @Basic
    @Column(name = "univ_id", nullable = true, length = 255)
    public String getUnivId() {
        return univId;
    }

    public void setUnivId(String univId) {
        this.univId = univId;
    }

    @Basic
    @Column(name = "univ_code", nullable = true, length = 50)
    public String getUnivCode() {
        return univCode;
    }

    public void setUnivCode(String univCode) {
        this.univCode = univCode;
    }

    @Basic
    @Column(name = "univ_name", nullable = true, length = 255)
    public String getUnivName() {
        return univName;
    }

    public void setUnivName(String univName) {
        this.univName = univName;
    }

    @Basic
    @Column(name = "univ_redirect", nullable = true, length = 32)
    public String getUnivRedirect() {
        return univRedirect;
    }

    public void setUnivRedirect(String univRedirect) {
        this.univRedirect = univRedirect;
    }

    @Basic
    @Column(name = "univ_subjects_num", nullable = true)
    public Integer getUnivSubjectsNum() {
        return univSubjectsNum;
    }

    public void setUnivSubjectsNum(Integer univSubjectsNum) {
        this.univSubjectsNum = univSubjectsNum;
    }

    @Basic
    @Column(name = "univ_subjects", nullable = true, length = 50)
    public String getUnivSubjects() {
        return univSubjects;
    }

    public void setUnivSubjects(String univSubjects) {
        this.univSubjects = univSubjects;
    }

    @Basic
    @Column(name = "univ_subjects_level", nullable = true, length = 50)
    public String getUnivSubjectsLevel() {
        return univSubjectsLevel;
    }

    public void setUnivSubjectsLevel(String univSubjectsLevel) {
        this.univSubjectsLevel = univSubjectsLevel;
    }

    @Basic
    @Column(name = "univ_remark", nullable = true, length = 512)
    public String getUnivRemark() {
        return univRemark;
    }

    public void setUnivRemark(String univRemark) {
        this.univRemark = univRemark;
    }

    @Basic
    @Column(name = "major_id", nullable = true, length = 50)
    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    @Basic
    @Column(name = "major_code", nullable = true, length = 50)
    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    @Basic
    @Column(name = "major_name", nullable = true, length = 255)
    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Basic
    @Column(name = "major_fee", nullable = true, length = 255)
    public String getMajorFee() {
        return majorFee;
    }

    public void setMajorFee(String majorFee) {
        this.majorFee = majorFee;
    }

    @Id
    @Column(name = "major_school_length", nullable = false, length = 255)
    public String getMajorSchoolLength() {
        return majorSchoolLength;
    }

    public void setMajorSchoolLength(String majorSchoolLength) {
        this.majorSchoolLength = majorSchoolLength;
    }

    @Basic
    @Column(name = "major_subjects_num", nullable = true)
    public Integer getMajorSubjectsNum() {
        return majorSubjectsNum;
    }

    public void setMajorSubjectsNum(Integer majorSubjectsNum) {
        this.majorSubjectsNum = majorSubjectsNum;
    }

    @Basic
    @Column(name = "major_subjects", nullable = true, length = 50)
    public String getMajorSubjects() {
        return majorSubjects;
    }

    public void setMajorSubjects(String majorSubjects) {
        this.majorSubjects = majorSubjects;
    }

    @Basic
    @Column(name = "major_subjects_level", nullable = true, length = 255)
    public String getMajorSubjectsLevel() {
        return majorSubjectsLevel;
    }

    public void setMajorSubjectsLevel(String majorSubjectsLevel) {
        this.majorSubjectsLevel = majorSubjectsLevel;
    }

    @Basic
    @Column(name = "major_remark", nullable = true, length = 512)
    public String getMajorRemark() {
        return majorRemark;
    }

    public void setMajorRemark(String majorRemark) {
        this.majorRemark = majorRemark;
    }

    @Basic
    @Column(name = "major_rate", nullable = true)
    public Integer getMajorRate() {
        return majorRate;
    }

    public void setMajorRate(Integer majorRate) {
        this.majorRate = majorRate;
    }

    @Basic
    @Column(name = "major_class", nullable = true, length = 32)
    public String getMajorClass() {
        return majorClass;
    }

    public void setMajorClass(String majorClass) {
        this.majorClass = majorClass;
    }

    @Basic
    @Column(name = "major_rank", nullable = true, length = 10)
    public String getMajorRank() {
        return majorRank;
    }

    public void setMajorRank(String majorRank) {
        this.majorRank = majorRank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TCeeEnrollPlan that = (TCeeEnrollPlan) o;
        return id == that.id &&
                Objects.equals(year, that.year) &&
                Objects.equals(provinceId, that.provinceId) &&
                Objects.equals(majorTypeId, that.majorTypeId) &&
                Objects.equals(batchId, that.batchId) &&
                Objects.equals(batchIdOld, that.batchIdOld) &&
                Objects.equals(planNum, that.planNum) &&
                Objects.equals(planType, that.planType) &&
                Objects.equals(relationId, that.relationId) &&
                Objects.equals(univId, that.univId) &&
                Objects.equals(univCode, that.univCode) &&
                Objects.equals(univName, that.univName) &&
                Objects.equals(univRedirect, that.univRedirect) &&
                Objects.equals(univSubjectsNum, that.univSubjectsNum) &&
                Objects.equals(univSubjects, that.univSubjects) &&
                Objects.equals(univSubjectsLevel, that.univSubjectsLevel) &&
                Objects.equals(univRemark, that.univRemark) &&
                Objects.equals(majorId, that.majorId) &&
                Objects.equals(majorCode, that.majorCode) &&
                Objects.equals(majorName, that.majorName) &&
                Objects.equals(majorFee, that.majorFee) &&
                Objects.equals(majorSchoolLength, that.majorSchoolLength) &&
                Objects.equals(majorSubjectsNum, that.majorSubjectsNum) &&
                Objects.equals(majorSubjects, that.majorSubjects) &&
                Objects.equals(majorSubjectsLevel, that.majorSubjectsLevel) &&
                Objects.equals(majorRemark, that.majorRemark) &&
                Objects.equals(majorRate, that.majorRate) &&
                Objects.equals(majorClass, that.majorClass) &&
                Objects.equals(majorRank, that.majorRank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, provinceId, majorTypeId, batchId, batchIdOld, planNum, planType, relationId, univId, univCode, univName, univRedirect, univSubjectsNum, univSubjects, univSubjectsLevel, univRemark, majorId, majorCode, majorName, majorFee, majorSchoolLength, majorSubjectsNum, majorSubjects, majorSubjectsLevel, majorRemark, majorRate, majorClass, majorRank);
    }
}
