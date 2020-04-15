package com.jeecms.wdedu.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_cee_applications", schema = "wodecareer")
public class TCeeApplications {
    private int id;
    private int userId;
    private String userName;
    private int year;
    private int provinceId;
    private int majorTypeId;
    private int applicationType;
    private String applicationName;
    private int score;
    private int rank;
    private String subjects;
    private String subjectsLevel;
    private String subjectsLevelVal;
    private Boolean isCheck;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String remark;
    private String pdfUrl;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @NotFound(action= NotFoundAction.IGNORE)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    @Column(name = "province_id")
    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    @Basic
    @Column(name = "major_type_id")
    public int getMajorTypeId() {
        return majorTypeId;
    }

    public void setMajorTypeId(int majorTypeId) {
        this.majorTypeId = majorTypeId;
    }

    @Basic
    @Column(name = "application_type")
    public int getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(int applicationType) {
        this.applicationType = applicationType;
    }

    @Basic
    @Column(name = "application_name")
    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    @Basic
    @Column(name = "score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Basic
    @Column(name = "rank")
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Basic
    @Column(name = "subjects")
    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    @Basic
    @Column(name = "subjects_level")
    public String getSubjectsLevel() {
        return subjectsLevel;
    }

    public void setSubjectsLevel(String subjectsLevel) {
        this.subjectsLevel = subjectsLevel;
    }

    @Basic
    @Column(name = "subjects_level_val")
    public String getSubjectsLevelVal() {
        return subjectsLevelVal;
    }

    public void setSubjectsLevelVal(String subjectsLevelVal) {
        this.subjectsLevelVal = subjectsLevelVal;
    }

    @Basic
    @Column(name = "is_check")
    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
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
        TCeeApplications that = (TCeeApplications) o;
        return id == that.id &&
                userId == that.userId &&
                year == that.year &&
                provinceId == that.provinceId &&
                majorTypeId == that.majorTypeId &&
                applicationType == that.applicationType &&
                score == that.score &&
                rank == that.rank &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(applicationName, that.applicationName) &&
                Objects.equals(subjects, that.subjects) &&
                Objects.equals(subjectsLevel, that.subjectsLevel) &&
                Objects.equals(subjectsLevelVal, that.subjectsLevelVal) &&
                Objects.equals(isCheck, that.isCheck) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, userName, year, provinceId, majorTypeId, applicationType, applicationName, score, rank, subjects, subjectsLevel, subjectsLevelVal, isCheck, createTime, updateTime, remark);
    }

    @Basic
    @Column(name = "pdfUrl")
    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }
}
