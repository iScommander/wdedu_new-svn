package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @date 2018/11/20
 */
@Entity
@Table(name = "t_mgr_srv_node", schema = "wodecareer", catalog = "")
public class TMgrSrvNode {
    private String id;
    private String orderId;
    private String nodeType;
    private String text1;
    private String isVisibleA;
    private String text2;
    private String isVisibleB;
    private String text3;
    private String isVisibleC;
    private String text4;
    private String isVisibleD;
    private String text5;
    private String isVisibleE;
    private String text6;
    private String isVisibleF;
    private String endTime;
    private String futureMajor;
    private String remark;
    private String modifyTime;
    private String fwName;
    private String isRelease;
    private String wenanName;
    private Integer solutionId;
    private String text7;
    private String isVisibleG;
    private String text8;
    private String isVisibleH;
    private String text9;
    private String isVisibleL;
    private String text10;
    private String isVisibleM;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "order_Id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "node_Type")
    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    @Basic
    @Column(name = "text1")
    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    @Basic
    @Column(name = "isVisibleA")
    public String getIsVisibleA() {
        return isVisibleA;
    }

    public void setIsVisibleA(String isVisibleA) {
        this.isVisibleA = isVisibleA;
    }

    @Basic
    @Column(name = "text2")
    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    @Basic
    @Column(name = "isVisibleB")
    public String getIsVisibleB() {
        return isVisibleB;
    }

    public void setIsVisibleB(String isVisibleB) {
        this.isVisibleB = isVisibleB;
    }

    @Basic
    @Column(name = "text3")
    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    @Basic
    @Column(name = "isVisibleC")
    public String getIsVisibleC() {
        return isVisibleC;
    }

    public void setIsVisibleC(String isVisibleC) {
        this.isVisibleC = isVisibleC;
    }

    @Basic
    @Column(name = "text4")
    public String getText4() {
        return text4;
    }

    public void setText4(String text4) {
        this.text4 = text4;
    }

    @Basic
    @Column(name = "isVisibleD")
    public String getIsVisibleD() {
        return isVisibleD;
    }

    public void setIsVisibleD(String isVisibleD) {
        this.isVisibleD = isVisibleD;
    }

    @Basic
    @Column(name = "text5")
    public String getText5() {
        return text5;
    }

    public void setText5(String text5) {
        this.text5 = text5;
    }

    @Basic
    @Column(name = "isVisibleE")
    public String getIsVisibleE() {
        return isVisibleE;
    }

    public void setIsVisibleE(String isVisibleE) {
        this.isVisibleE = isVisibleE;
    }

    @Basic
    @Column(name = "text6")
    public String getText6() {
        return text6;
    }

    public void setText6(String text6) {
        this.text6 = text6;
    }

    @Basic
    @Column(name = "isVisibleF")
    public String getIsVisibleF() {
        return isVisibleF;
    }

    public void setIsVisibleF(String isVisibleF) {
        this.isVisibleF = isVisibleF;
    }

    @Basic
    @Column(name = "endTime")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "future_major")
    public String getFutureMajor() {
        return futureMajor;
    }

    public void setFutureMajor(String futureMajor) {
        this.futureMajor = futureMajor;
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
    @Column(name = "modifyTime")
    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Basic
    @Column(name = "fwName")
    public String getFwName() {
        return fwName;
    }

    public void setFwName(String fwName) {
        this.fwName = fwName;
    }

    @Basic
    @Column(name = "isRelease")
    public String getIsRelease() {
        return isRelease;
    }

    public void setIsRelease(String isRelease) {
        this.isRelease = isRelease;
    }

    @Basic
    @Column(name = "wenanName")
    public String getWenanName() {
        return wenanName;
    }

    public void setWenanName(String wenanName) {
        this.wenanName = wenanName;
    }

    @Basic
    @Column(name = "solution_id")
    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    @Basic
    @Column(name = "text7")
    public String getText7() {
        return text7;
    }

    public void setText7(String text7) {
        this.text7 = text7;
    }

    @Basic
    @Column(name = "isVisibleG")
    public String getIsVisibleG() {
        return isVisibleG;
    }

    public void setIsVisibleG(String isVisibleG) {
        this.isVisibleG = isVisibleG;
    }

    @Basic
    @Column(name = "text8")
    public String getText8() {
        return text8;
    }

    public void setText8(String text8) {
        this.text8 = text8;
    }

    @Basic
    @Column(name = "isVisibleH")
    public String getIsVisibleH() {
        return isVisibleH;
    }

    public void setIsVisibleH(String isVisibleH) {
        this.isVisibleH = isVisibleH;
    }

    @Basic
    @Column(name = "text9")
    public String getText9() {
        return text9;
    }

    public void setText9(String text9) {
        this.text9 = text9;
    }

    @Basic
    @Column(name = "isVisibleL")
    public String getIsVisibleL() {
        return isVisibleL;
    }

    public void setIsVisibleL(String isVisibleL) {
        this.isVisibleL = isVisibleL;
    }

    @Basic
    @Column(name = "text10")
    public String getText10() {
        return text10;
    }

    public void setText10(String text10) {
        this.text10 = text10;
    }

    @Basic
    @Column(name = "isVisibleM")
    public String getIsVisibleM() {
        return isVisibleM;
    }

    public void setIsVisibleM(String isVisibleM) {
        this.isVisibleM = isVisibleM;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TMgrSrvNode that = (TMgrSrvNode) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(nodeType, that.nodeType) &&
                Objects.equals(text1, that.text1) &&
                Objects.equals(isVisibleA, that.isVisibleA) &&
                Objects.equals(text2, that.text2) &&
                Objects.equals(isVisibleB, that.isVisibleB) &&
                Objects.equals(text3, that.text3) &&
                Objects.equals(isVisibleC, that.isVisibleC) &&
                Objects.equals(text4, that.text4) &&
                Objects.equals(isVisibleD, that.isVisibleD) &&
                Objects.equals(text5, that.text5) &&
                Objects.equals(isVisibleE, that.isVisibleE) &&
                Objects.equals(text6, that.text6) &&
                Objects.equals(isVisibleF, that.isVisibleF) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(futureMajor, that.futureMajor) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(modifyTime, that.modifyTime) &&
                Objects.equals(fwName, that.fwName) &&
                Objects.equals(isRelease, that.isRelease) &&
                Objects.equals(wenanName, that.wenanName) &&
                Objects.equals(solutionId, that.solutionId) &&
                Objects.equals(text7, that.text7) &&
                Objects.equals(isVisibleG, that.isVisibleG) &&
                Objects.equals(text8, that.text8) &&
                Objects.equals(isVisibleH, that.isVisibleH) &&
                Objects.equals(text9, that.text9) &&
                Objects.equals(isVisibleL, that.isVisibleL) &&
                Objects.equals(text10, that.text10) &&
                Objects.equals(isVisibleM, that.isVisibleM);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, orderId, nodeType, text1, isVisibleA, text2, isVisibleB, text3, isVisibleC, text4, isVisibleD, text5, isVisibleE, text6, isVisibleF, endTime, futureMajor, remark, modifyTime, fwName, isRelease, wenanName, solutionId, text7, isVisibleG, text8, isVisibleH, text9, isVisibleL, text10, isVisibleM);
    }
}
