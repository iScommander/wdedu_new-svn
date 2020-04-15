package com.jeecms.wdedu.entity;

import com.utils.excel.Excel;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_base_vrcards", schema = "wodecareer", catalog = "")
public class TBaseVrcards {
    @Excel(exportName = "卡号", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private Long cardNo;
    @Excel(exportName = "密码", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private String password;
    private int groupId;
    private Boolean activeStatus;
    private Date activeTime;
    private Date createTime;
    private Integer outchannel;
    private Integer cardFee;
    private Integer isBalance;
    private Integer inOutStorage;
    @Excel(exportName = "备注", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private String remark;
    private Date losedTime;
    @Excel(exportName = "省份", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private Integer provinceId;
    private Integer chnUserId;
    private Integer optUserId;
    private Integer bndUserId;

    @Id
    @Column(name = "cardNo")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getCardNo() {
        return cardNo;
    }

    public void setCardNo(Long cardNo) {
        this.cardNo = cardNo;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "group_id")
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "active_status")
    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Basic
    @Column(name = "active_time")
    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "outchannel")
    public Integer getOutchannel() {
        return outchannel;
    }

    public void setOutchannel(Integer outchannel) {
        this.outchannel = outchannel;
    }

    @Basic
    @Column(name = "card_fee")
    public Integer getCardFee() {
        return cardFee;
    }

    public void setCardFee(Integer cardFee) {
        this.cardFee = cardFee;
    }

    @Basic
    @Column(name = "isBalance")
    public Integer getIsBalance() {
        return isBalance;
    }

    public void setIsBalance(Integer isBalance) {
        this.isBalance = isBalance;
    }

    @Basic
    @Column(name = "in_out_storage")
    public Integer getInOutStorage() {
        return inOutStorage;
    }

    public void setInOutStorage(Integer inOutStorage) {
        this.inOutStorage = inOutStorage;
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
        TBaseVrcards that = (TBaseVrcards) o;
        return cardNo.equals(that.cardNo) &&
                groupId == that.groupId &&
                Objects.equals(password, that.password) &&
                Objects.equals(activeStatus, that.activeStatus) &&
                Objects.equals(activeTime, that.activeTime) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(outchannel, that.outchannel) &&
                Objects.equals(cardFee, that.cardFee) &&
                Objects.equals(isBalance, that.isBalance) &&
                Objects.equals(inOutStorage, that.inOutStorage) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cardNo, password, groupId, activeStatus, activeTime, createTime, outchannel, cardFee, isBalance, inOutStorage, remark);
    }


    @Basic
    @Column(name = "losed_time")
    public Date getLosedTime() {
        return losedTime;
    }

    public void setLosedTime(Date losedTime) {
        this.losedTime = losedTime;
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
    @Column(name = "chn_user_id")
    public Integer getChnUserId() {
        return chnUserId;
    }

    public void setChnUserId(Integer chnUserId) {
        this.chnUserId = chnUserId;
    }

    @Basic
    @Column(name = "opt_user_id")
    public Integer getOptUserId() {
        return optUserId;
    }

    public void setOptUserId(Integer optUserId) {
        this.optUserId = optUserId;
    }

    @Basic
    @Column(name = "bnd_User_Id")
    public Integer getBndUserId() {
        return bndUserId;
    }

    public void setBndUserId(Integer bndUserId) {
        this.bndUserId = bndUserId;
    }
}
