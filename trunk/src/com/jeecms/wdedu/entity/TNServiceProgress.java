package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_service_progress", schema = "wodecareer", catalog = "")
public class TNServiceProgress {
    private int id;
    private Integer locationId;
    private String progressCode;
    private String progressName;
    private String status;
    private String text;
    private String isConfirm;
    private String confirmTime;
    private String note;
    private String counselor;
    private String updateTime;
    private String progressStatus;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "locationId")
    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "progressCode")
    public String getProgressCode() {
        return progressCode;
    }

    public void setProgressCode(String progressCode) {
        this.progressCode = progressCode;
    }

    @Basic
    @Column(name = "progressName")
    public String getProgressName() {
        return progressName;
    }

    public void setProgressName(String progressName) {
        this.progressName = progressName;
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
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "isConfirm")
    public String getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(String isConfirm) {
        this.isConfirm = isConfirm;
    }

    @Basic
    @Column(name = "confirmTime")
    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "counselor")
    public String getCounselor() {
        return counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
    }

    @Basic
    @Column(name = "updateTime")
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "progressStatus")
    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TNServiceProgress that = (TNServiceProgress) o;
        return id == that.id &&
                Objects.equals(locationId, that.locationId) &&
                Objects.equals(progressCode, that.progressCode) &&
                Objects.equals(progressName, that.progressName) &&
                Objects.equals(status, that.status) &&
                Objects.equals(text, that.text) &&
                Objects.equals(isConfirm, that.isConfirm) &&
                Objects.equals(confirmTime, that.confirmTime) &&
                Objects.equals(note, that.note) &&
                Objects.equals(counselor, that.counselor) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(progressStatus, that.progressStatus);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, locationId, progressCode, progressName, status, text, isConfirm, confirmTime, note, counselor, updateTime, progressStatus);
    }
}
