package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/11/20
 */
@Entity
@Table(name = "t_mgr_srv_order", schema = "wodecareer", catalog = "")
public class TMgrSrvOrder {
    private String orderId;
    private String contractNo;
    private Integer srvSinglerUserid;
    private String srvType;
    private String provinceName;
    private String srvContent;
    private String srvNode;
    private String payStatus;
    private String isVioded;
    private String srvMainTeacher;
    private String srvAssistTeacher;
    private String singlerName;
    private String orderFee;
    private String singleTime;
    private String orderDescr;
    private String otherDescr;
    private String stuUsername;
    private Integer stuUserid;
    private String stuName;
    private String updateTime;
    private String branchCompany;
    private String srvMainTeacherid;
    private String srvAssistTeacherid;
    private String srvNewServerid;
    private String srvNewServerName;
    private String srvPromotionId;
    private String srvPromotionName;
    private String isPayAll;

    @Id
    @Column(name = "order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "contract_no")
    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    @Basic
    @Column(name = "srv_singler_userid")
    public Integer getSrvSinglerUserid() {
        return srvSinglerUserid;
    }

    public void setSrvSinglerUserid(Integer srvSinglerUserid) {
        this.srvSinglerUserid = srvSinglerUserid;
    }

    @Basic
    @Column(name = "srv_type")
    public String getSrvType() {
        return srvType;
    }

    public void setSrvType(String srvType) {
        this.srvType = srvType;
    }

    @Basic
    @Column(name = "province_name")
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Basic
    @Column(name = "srv_content")
    public String getSrvContent() {
        return srvContent;
    }

    public void setSrvContent(String srvContent) {
        this.srvContent = srvContent;
    }

    @Basic
    @Column(name = "srv_node")
    public String getSrvNode() {
        return srvNode;
    }

    public void setSrvNode(String srvNode) {
        this.srvNode = srvNode;
    }

    @Basic
    @Column(name = "pay_status")
    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    @Basic
    @Column(name = "isVioded")
    public String getIsVioded() {
        return isVioded;
    }

    public void setIsVioded(String isVioded) {
        this.isVioded = isVioded;
    }

    @Basic
    @Column(name = "srv_main_teacher")
    public String getSrvMainTeacher() {
        return srvMainTeacher;
    }

    public void setSrvMainTeacher(String srvMainTeacher) {
        this.srvMainTeacher = srvMainTeacher;
    }

    @Basic
    @Column(name = "srv_assist_teacher")
    public String getSrvAssistTeacher() {
        return srvAssistTeacher;
    }

    public void setSrvAssistTeacher(String srvAssistTeacher) {
        this.srvAssistTeacher = srvAssistTeacher;
    }

    @Basic
    @Column(name = "singler_name")
    public String getSinglerName() {
        return singlerName;
    }

    public void setSinglerName(String singlerName) {
        this.singlerName = singlerName;
    }

    @Basic
    @Column(name = "order_fee")
    public String getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(String orderFee) {
        this.orderFee = orderFee;
    }

    @Basic
    @Column(name = "single_time")
    public String getSingleTime() {
        return singleTime;
    }

    public void setSingleTime(String singleTime) {
        this.singleTime = singleTime;
    }

    @Basic
    @Column(name = "order_descr")
    public String getOrderDescr() {
        return orderDescr;
    }

    public void setOrderDescr(String orderDescr) {
        this.orderDescr = orderDescr;
    }

    @Basic
    @Column(name = "other_descr")
    public String getOtherDescr() {
        return otherDescr;
    }

    public void setOtherDescr(String otherDescr) {
        this.otherDescr = otherDescr;
    }

    @Basic
    @Column(name = "stu_username")
    public String getStuUsername() {
        return stuUsername;
    }

    public void setStuUsername(String stuUsername) {
        this.stuUsername = stuUsername;
    }

    @Basic
    @Column(name = "stu_userid")
    public Integer getStuUserid() {
        return stuUserid;
    }

    public void setStuUserid(Integer stuUserid) {
        this.stuUserid = stuUserid;
    }

    @Basic
    @Column(name = "stu_name")
    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @Basic
    @Column(name = "update_time")
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "branch_company")
    public String getBranchCompany() {
        return branchCompany;
    }

    public void setBranchCompany(String branchCompany) {
        this.branchCompany = branchCompany;
    }

    @Basic
    @Column(name = "srv_main_teacherid")
    public String getSrvMainTeacherid() {
        return srvMainTeacherid;
    }

    public void setSrvMainTeacherid(String srvMainTeacherid) {
        this.srvMainTeacherid = srvMainTeacherid;
    }

    @Basic
    @Column(name = "srv_assist_teacherid")
    public String getSrvAssistTeacherid() {
        return srvAssistTeacherid;
    }

    public void setSrvAssistTeacherid(String srvAssistTeacherid) {
        this.srvAssistTeacherid = srvAssistTeacherid;
    }

    @Basic
    @Column(name = "srv_new_serverid")
    public String getSrvNewServerid() {
        return srvNewServerid;
    }

    public void setSrvNewServerid(String srvNewServerid) {
        this.srvNewServerid = srvNewServerid;
    }

    @Basic
    @Column(name = "srv_new_server_name")
    public String getSrvNewServerName() {
        return srvNewServerName;
    }

    public void setSrvNewServerName(String srvNewServerName) {
        this.srvNewServerName = srvNewServerName;
    }

    @Basic
    @Column(name = "srv_promotion_id")
    public String getSrvPromotionId() {
        return srvPromotionId;
    }

    public void setSrvPromotionId(String srvPromotionId) {
        this.srvPromotionId = srvPromotionId;
    }

    @Basic
    @Column(name = "srv_promotion_name")
    public String getSrvPromotionName() {
        return srvPromotionName;
    }

    public void setSrvPromotionName(String srvPromotionName) {
        this.srvPromotionName = srvPromotionName;
    }

    @Basic
    @Column(name = "isPayAll")
    public String getIsPayAll() {
        return isPayAll;
    }

    public void setIsPayAll(String isPayAll) {
        this.isPayAll = isPayAll;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TMgrSrvOrder that = (TMgrSrvOrder) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(contractNo, that.contractNo) &&
                Objects.equals(srvSinglerUserid, that.srvSinglerUserid) &&
                Objects.equals(srvType, that.srvType) &&
                Objects.equals(provinceName, that.provinceName) &&
                Objects.equals(srvContent, that.srvContent) &&
                Objects.equals(srvNode, that.srvNode) &&
                Objects.equals(payStatus, that.payStatus) &&
                Objects.equals(isVioded, that.isVioded) &&
                Objects.equals(srvMainTeacher, that.srvMainTeacher) &&
                Objects.equals(srvAssistTeacher, that.srvAssistTeacher) &&
                Objects.equals(singlerName, that.singlerName) &&
                Objects.equals(orderFee, that.orderFee) &&
                Objects.equals(singleTime, that.singleTime) &&
                Objects.equals(orderDescr, that.orderDescr) &&
                Objects.equals(otherDescr, that.otherDescr) &&
                Objects.equals(stuUsername, that.stuUsername) &&
                Objects.equals(stuUserid, that.stuUserid) &&
                Objects.equals(stuName, that.stuName) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(branchCompany, that.branchCompany) &&
                Objects.equals(srvMainTeacherid, that.srvMainTeacherid) &&
                Objects.equals(srvAssistTeacherid, that.srvAssistTeacherid) &&
                Objects.equals(srvNewServerid, that.srvNewServerid) &&
                Objects.equals(srvNewServerName, that.srvNewServerName) &&
                Objects.equals(srvPromotionId, that.srvPromotionId) &&
                Objects.equals(srvPromotionName, that.srvPromotionName) &&
                Objects.equals(isPayAll, that.isPayAll);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, contractNo, srvSinglerUserid, srvType, provinceName, srvContent, srvNode, payStatus, isVioded, srvMainTeacher, srvAssistTeacher, singlerName, orderFee, singleTime, orderDescr, otherDescr, stuUsername, stuUserid, stuName, updateTime, branchCompany, srvMainTeacherid, srvAssistTeacherid, srvNewServerid, srvNewServerName, srvPromotionId, srvPromotionName, isPayAll);
    }
}
