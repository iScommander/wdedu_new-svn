package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_location", schema = "wodecareer", catalog = "")
public class TNLocation {
    private int id;
    private String orderId;
    private Integer univId;
    private String applyType;
    private String applyConditions;
    private String majorNames;
    private String locationTime;
    private String type;
    private String cstgbz;
    private Boolean iscstg;
    private Boolean isrxzg;
    private Boolean iszzlq;
    private String rxzgbz;
    private String zzlqbz;
    private Integer provinceId;
    private Integer year;
    private Integer majorId;
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
    @Column(name = "ORDER_ID")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "UNIV_ID")
    public Integer getUnivId() {
        return univId;
    }

    public void setUnivId(Integer univId) {
        this.univId = univId;
    }

    @Basic
    @Column(name = "APPLY_TYPE")
    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    @Basic
    @Column(name = "APPLY_CONDITIONS")
    public String getApplyConditions() {
        return applyConditions;
    }

    public void setApplyConditions(String applyConditions) {
        this.applyConditions = applyConditions;
    }

    @Basic
    @Column(name = "MAJOR_NAMES")
    public String getMajorNames() {
        return majorNames;
    }

    public void setMajorNames(String majorNames) {
        this.majorNames = majorNames;
    }

    @Basic
    @Column(name = "location_time")
    public String getLocationTime() {
        return locationTime;
    }

    public void setLocationTime(String locationTime) {
        this.locationTime = locationTime;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "cstgbz")
    public String getCstgbz() {
        return cstgbz;
    }

    public void setCstgbz(String cstgbz) {
        this.cstgbz = cstgbz;
    }

    @Basic
    @Column(name = "iscstg")
    public Boolean getIscstg() {
        return iscstg;
    }

    public void setIscstg(Boolean iscstg) {
        this.iscstg = iscstg;
    }

    @Basic
    @Column(name = "isrxzg")
    public Boolean getIsrxzg() {
        return isrxzg;
    }

    public void setIsrxzg(Boolean isrxzg) {
        this.isrxzg = isrxzg;
    }

    @Basic
    @Column(name = "iszzlq")
    public Boolean getIszzlq() {
        return iszzlq;
    }

    public void setIszzlq(Boolean iszzlq) {
        this.iszzlq = iszzlq;
    }

    @Basic
    @Column(name = "rxzgbz")
    public String getRxzgbz() {
        return rxzgbz;
    }

    public void setRxzgbz(String rxzgbz) {
        this.rxzgbz = rxzgbz;
    }

    @Basic
    @Column(name = "zzlqbz")
    public String getZzlqbz() {
        return zzlqbz;
    }

    public void setZzlqbz(String zzlqbz) {
        this.zzlqbz = zzlqbz;
    }

    @Basic
    @Column(name = "PROVINCE_ID")
    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @Basic
    @Column(name = "YEAR")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "MAJOR_ID")
    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
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
        TNLocation that = (TNLocation) o;
        return id == that.id &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(univId, that.univId) &&
                Objects.equals(applyType, that.applyType) &&
                Objects.equals(applyConditions, that.applyConditions) &&
                Objects.equals(majorNames, that.majorNames) &&
                Objects.equals(locationTime, that.locationTime) &&
                Objects.equals(type, that.type) &&
                Objects.equals(cstgbz, that.cstgbz) &&
                Objects.equals(iscstg, that.iscstg) &&
                Objects.equals(isrxzg, that.isrxzg) &&
                Objects.equals(iszzlq, that.iszzlq) &&
                Objects.equals(rxzgbz, that.rxzgbz) &&
                Objects.equals(zzlqbz, that.zzlqbz) &&
                Objects.equals(provinceId, that.provinceId) &&
                Objects.equals(year, that.year) &&
                Objects.equals(majorId, that.majorId) &&
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

        return Objects.hash(id, orderId, univId, applyType, applyConditions, majorNames, locationTime, type, cstgbz, iscstg, isrxzg, iszzlq, rxzgbz, zzlqbz, provinceId, year, majorId, univType, limit, benefit, declareStartDate, declareEndDate, payStopDate, interviewDate);
    }
}
