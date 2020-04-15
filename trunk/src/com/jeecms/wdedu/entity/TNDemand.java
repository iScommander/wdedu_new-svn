package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_demand", schema = "wodecareer", catalog = "")
public class TNDemand {
    private String demandNo;
    private String orderId;
    private String demandName;
    private String demandType;
    private String endDate;
    private String submitDate;
    private String status;
    private String demandContent;
    private String realName;
    private String writerId;
    private String writerName;
    private String approveStatus;
    private String remark;
    private String demandKind;

    @Id
    @Column(name = "demandNo")
    public String getDemandNo() {
        return demandNo;
    }

    public void setDemandNo(String demandNo) {
        this.demandNo = demandNo;
    }

    @Basic
    @Column(name = "orderId")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "demandName")
    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName;
    }

    @Basic
    @Column(name = "demandType")
    public String getDemandType() {
        return demandType;
    }

    public void setDemandType(String demandType) {
        this.demandType = demandType;
    }

    @Basic
    @Column(name = "endDate")
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "submitDate")
    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "demandContent")
    public String getDemandContent() {
        return demandContent;
    }

    public void setDemandContent(String demandContent) {
        this.demandContent = demandContent;
    }

    @Basic
    @Column(name = "realName")
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "writerId")
    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    @Basic
    @Column(name = "writerName")
    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    @Basic
    @Column(name = "approveStatus")
    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
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
    @Column(name = "demandKind")
    public String getDemandKind() {
        return demandKind;
    }

    public void setDemandKind(String demandKind) {
        this.demandKind = demandKind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TNDemand tnDemand = (TNDemand) o;
        return Objects.equals(demandNo, tnDemand.demandNo) &&
                Objects.equals(orderId, tnDemand.orderId) &&
                Objects.equals(demandName, tnDemand.demandName) &&
                Objects.equals(demandType, tnDemand.demandType) &&
                Objects.equals(endDate, tnDemand.endDate) &&
                Objects.equals(submitDate, tnDemand.submitDate) &&
                Objects.equals(status, tnDemand.status) &&
                Objects.equals(demandContent, tnDemand.demandContent) &&
                Objects.equals(realName, tnDemand.realName) &&
                Objects.equals(writerId, tnDemand.writerId) &&
                Objects.equals(writerName, tnDemand.writerName) &&
                Objects.equals(approveStatus, tnDemand.approveStatus) &&
                Objects.equals(remark, tnDemand.remark) &&
                Objects.equals(demandKind, tnDemand.demandKind);
    }

    @Override
    public int hashCode() {

        return Objects.hash(demandNo, orderId, demandName, demandType, endDate, submitDate, status, demandContent, realName, writerId, writerName, approveStatus, remark, demandKind);
    }
}
