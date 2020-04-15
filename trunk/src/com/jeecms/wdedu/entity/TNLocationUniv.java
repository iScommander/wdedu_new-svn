package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_location_univ", schema = "wodecareer", catalog = "")
public class TNLocationUniv {
    private int id;
    private int year;
    private int univId;
    private int majorId;
    private Integer univType;
    private Integer limit;
    private String benefit;
    private String declareStartDate;
    private String declareEndDate;
    private String payStopDate;
    private String interviewDate;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "YEAR")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "UNIV_ID")
    public int getUnivId() {
        return univId;
    }

    public void setUnivId(int univId) {
        this.univId = univId;
    }

    @Basic
    @Column(name = "MAJOR_ID")
    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    @Basic
    @Column(name = "UNIV_TYPE")
    public Integer getUnivType() {
        return univType;
    }

    public void setUnivType(Integer univType) {
        this.univType = univType;
    }

    @Basic
    @Column(name = "LIMIT")
    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Basic
    @Column(name = "BENEFIT")
    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    @Basic
    @Column(name = "DECLARE_START_DATE")
    public String getDeclareStartDate() {
        return declareStartDate;
    }

    public void setDeclareStartDate(String declareStartDate) {
        this.declareStartDate = declareStartDate;
    }

    @Basic
    @Column(name = "DECLARE_END_DATE")
    public String getDeclareEndDate() {
        return declareEndDate;
    }

    public void setDeclareEndDate(String declareEndDate) {
        this.declareEndDate = declareEndDate;
    }

    @Basic
    @Column(name = "PAY_STOP_DATE")
    public String getPayStopDate() {
        return payStopDate;
    }

    public void setPayStopDate(String payStopDate) {
        this.payStopDate = payStopDate;
    }

    @Basic
    @Column(name = "INTERVIEW_DATE")
    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TNLocationUniv that = (TNLocationUniv) o;
        return id == that.id &&
                year == that.year &&
                univId == that.univId &&
                majorId == that.majorId &&
                Objects.equals(univType, that.univType) &&
                Objects.equals(limit, that.limit) &&
                Objects.equals(benefit, that.benefit) &&
                Objects.equals(declareStartDate, that.declareStartDate) &&
                Objects.equals(declareEndDate, that.declareEndDate) &&
                Objects.equals(payStopDate, that.payStopDate) &&
                Objects.equals(interviewDate, that.interviewDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, year, univId, majorId, univType, limit, benefit, declareStartDate, declareEndDate, payStopDate, interviewDate);
    }
}
