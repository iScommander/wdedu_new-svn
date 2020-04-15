package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_data_enroll_highschool", schema = "wodecareer", catalog = "")
public class TDataEnrollHighschool {
    private int id;
    private String schoolName;
    private Integer schoolCode;
    private int provinceId;
    private Integer majorType;
    private String univName;
    private int univCode;
    private String professionalName;
    private int year;
    private Integer register;
    private Integer lowScore;
    private Integer highScore;
    private Integer schoolLowRange;
    private Integer schoolHighRange;
    private Integer averageScore;
    private Integer lowRank;
    private Integer highRank;
    private Integer averageRank;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "schoolName")
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Basic
    @Column(name = "schoolCode")
    public Integer getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(Integer schoolCode) {
        this.schoolCode = schoolCode;
    }

    @Basic
    @Column(name = "provinceId")
    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    @Basic
    @Column(name = "majorType")
    public Integer getMajorType() {
        return majorType;
    }

    public void setMajorType(Integer majorType) {
        this.majorType = majorType;
    }

    @Basic
    @Column(name = "univName")
    public String getUnivName() {
        return univName;
    }

    public void setUnivName(String univName) {
        this.univName = univName;
    }

    @Basic
    @Column(name = "univCode")
    public int getUnivCode() {
        return univCode;
    }

    public void setUnivCode(int univCode) {
        this.univCode = univCode;
    }

    @Basic
    @Column(name = "professionalName")
    public String getProfessionalName() {
        return professionalName;
    }

    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName;
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
    @Column(name = "register")
    public Integer getRegister() {
        return register;
    }

    public void setRegister(Integer register) {
        this.register = register;
    }

    @Basic
    @Column(name = "lowScore")
    public Integer getLowScore() {
        return lowScore;
    }

    public void setLowScore(Integer lowScore) {
        this.lowScore = lowScore;
    }

    @Basic
    @Column(name = "highScore")
    public Integer getHighScore() {
        return highScore;
    }

    public void setHighScore(Integer highScore) {
        this.highScore = highScore;
    }

    @Basic
    @Column(name = "schoolLowRange")
    public Integer getSchoolLowRange() {
        return schoolLowRange;
    }

    public void setSchoolLowRange(Integer schoolLowRange) {
        this.schoolLowRange = schoolLowRange;
    }

    @Basic
    @Column(name = "schoolHighRange")
    public Integer getSchoolHighRange() {
        return schoolHighRange;
    }

    public void setSchoolHighRange(Integer schoolHighRange) {
        this.schoolHighRange = schoolHighRange;
    }

    @Basic
    @Column(name = "averageScore")
    public Integer getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Integer averageScore) {
        this.averageScore = averageScore;
    }

    @Basic
    @Column(name = "lowRank")
    public Integer getLowRank() {
        return lowRank;
    }

    public void setLowRank(Integer lowRank) {
        this.lowRank = lowRank;
    }

    @Basic
    @Column(name = "highRank")
    public Integer getHighRank() {
        return highRank;
    }

    public void setHighRank(Integer highRank) {
        this.highRank = highRank;
    }

    @Basic
    @Column(name = "averageRank")
    public Integer getAverageRank() {
        return averageRank;
    }

    public void setAverageRank(Integer averageRank) {
        this.averageRank = averageRank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TDataEnrollHighschool that = (TDataEnrollHighschool) o;
        return id == that.id &&
                provinceId == that.provinceId &&
                univCode == that.univCode &&
                year == that.year &&
                Objects.equals(schoolName, that.schoolName) &&
                Objects.equals(schoolCode, that.schoolCode) &&
                Objects.equals(majorType, that.majorType) &&
                Objects.equals(univName, that.univName) &&
                Objects.equals(professionalName, that.professionalName) &&
                Objects.equals(register, that.register) &&
                Objects.equals(lowScore, that.lowScore) &&
                Objects.equals(highScore, that.highScore) &&
                Objects.equals(schoolLowRange, that.schoolLowRange) &&
                Objects.equals(schoolHighRange, that.schoolHighRange) &&
                Objects.equals(averageScore, that.averageScore) &&
                Objects.equals(lowRank, that.lowRank) &&
                Objects.equals(highRank, that.highRank) &&
                Objects.equals(averageRank, that.averageRank);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, schoolName, schoolCode, provinceId, majorType, univName, univCode, professionalName, year, register, lowScore, highScore, schoolLowRange, schoolHighRange, averageScore, lowRank, highRank, averageRank);
    }
}
