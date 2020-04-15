package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_copy", schema = "wodecareer", catalog = "")
public class TNCopy {
    private String copyNo;
    private String demandNo;
    private String orderId;
    private String copyName;
    private String copyType;
    private Integer writerId;
    private String writerName;
    private String finishTime;
    private String status;
    private String content;

    @Id
    @Column(name = "copyNo")
    public String getCopyNo() {
        return copyNo;
    }

    public void setCopyNo(String copyNo) {
        this.copyNo = copyNo;
    }

    @Basic
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
    @Column(name = "copyName")
    public String getCopyName() {
        return copyName;
    }

    public void setCopyName(String copyName) {
        this.copyName = copyName;
    }

    @Basic
    @Column(name = "copyType")
    public String getCopyType() {
        return copyType;
    }

    public void setCopyType(String copyType) {
        this.copyType = copyType;
    }

    @Basic
    @Column(name = "writerId")
    public Integer getWriterId() {
        return writerId;
    }

    public void setWriterId(Integer writerId) {
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
    @Column(name = "finishTime")
    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TNCopy tnCopy = (TNCopy) o;
        return Objects.equals(copyNo, tnCopy.copyNo) &&
                Objects.equals(demandNo, tnCopy.demandNo) &&
                Objects.equals(orderId, tnCopy.orderId) &&
                Objects.equals(copyName, tnCopy.copyName) &&
                Objects.equals(copyType, tnCopy.copyType) &&
                Objects.equals(writerId, tnCopy.writerId) &&
                Objects.equals(writerName, tnCopy.writerName) &&
                Objects.equals(finishTime, tnCopy.finishTime) &&
                Objects.equals(status, tnCopy.status) &&
                Objects.equals(content, tnCopy.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(copyNo, demandNo, orderId, copyName, copyType, writerId, writerName, finishTime, status, content);
    }
}
