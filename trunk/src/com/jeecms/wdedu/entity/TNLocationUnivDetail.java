package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_location_univ_detail", schema = "wodecareer", catalog = "")
public class TNLocationUnivDetail {
    private String pkid;
    private String jbSchoolName;
    private Timestamp jbSignBeginDate;
    private Timestamp jbSignEndDate;
    private String jbRegulations;
    private String jbSignUrl;
    private String jbSchoolUrl;
    private String hxCondition;
    private String hxMajorItems;
    private String yhPreferential;
    private Boolean sbIsRecruit;
    private Boolean sbIsLocking;
    private String sbMajorType;
    private String sbCollegeQty;
    private String sbMajorQty;
    private String sbRemark;
    private String sbRemarkFile;
    private Boolean tjIsNeed;
    private Boolean tjIsUpload;
    private String tjUploadDate;
    private String tjPostClaim;
    private String tjPostCloseDate;
    private String tjRemark;
    private String zjLetterWords;
    private String zjClaimContent;
    private String zjSpecific;
    private String zjSend;
    private String zjSource;
    private String bsTestContent;
    private String bsBeginDate;
    private Boolean bsIsSameTime;
    private String bsAdress;
    private String bsSpecificDesc;
    private String yjBeginDate;
    private String yjEndDate;
    private String yjContent;
    private String yjSpecificClaim;
    private String qtClaim;
    private String userId;
    private String userName;
    private Timestamp dateline;
    private int id;
    private String uploadList;
    private String regulations;
    private String xzyDate;
    private String grcs;
    private String grcsDate;
    private String cjms;
    private String cjmsDate;
    private String dkcjms;
    private String dkcjmsDate;
    private String fjcl;
    private String fjclDate;
    private String tjrxxsm;
    private String tjrxxsmDate;
    private String ylwtsm;
    private String ylwtsmDate;
    private String qrzyjcnr;
    private String qrzyjcnrDate;
    private String xzsqbyq;
    private String xzsqbDate;
    private String scsqbyq;
    private String scsqbDate;
    private String yjcllb;
    private String yjcllbDate;
    private String cstgxygj;
    private String cstgDate;
    private String ksyq;
    private String ksDate;
    private String jfyq;
    private String jfDate;
    private String zkzyq;
    private String zkzdyDate;
    private String hdjfsm;
    private String zyDate;
    private String zhxxyq;
    private String zhxxyqDate;
    private Integer univType;
    private Integer year;
    private String xbcx;
    private String yhfz;
    private String lqyz;

    @Basic
    @Column(name = "PKID")
    public String getPkid() {
        return pkid;
    }

    public void setPkid(String pkid) {
        this.pkid = pkid;
    }

    @Basic
    @Column(name = "JBSchoolName")
    public String getJbSchoolName() {
        return jbSchoolName;
    }

    public void setJbSchoolName(String jbSchoolName) {
        this.jbSchoolName = jbSchoolName;
    }

    @Basic
    @Column(name = "JBSignBeginDate")
    public Timestamp getJbSignBeginDate() {
        return jbSignBeginDate;
    }

    public void setJbSignBeginDate(Timestamp jbSignBeginDate) {
        this.jbSignBeginDate = jbSignBeginDate;
    }

    @Basic
    @Column(name = "JBSignEndDate")
    public Timestamp getJbSignEndDate() {
        return jbSignEndDate;
    }

    public void setJbSignEndDate(Timestamp jbSignEndDate) {
        this.jbSignEndDate = jbSignEndDate;
    }

    @Basic
    @Column(name = "JBRegulations")
    public String getJbRegulations() {
        return jbRegulations;
    }

    public void setJbRegulations(String jbRegulations) {
        this.jbRegulations = jbRegulations;
    }

    @Basic
    @Column(name = "JBSignUrl")
    public String getJbSignUrl() {
        return jbSignUrl;
    }

    public void setJbSignUrl(String jbSignUrl) {
        this.jbSignUrl = jbSignUrl;
    }

    @Basic
    @Column(name = "JBSchoolUrl")
    public String getJbSchoolUrl() {
        return jbSchoolUrl;
    }

    public void setJbSchoolUrl(String jbSchoolUrl) {
        this.jbSchoolUrl = jbSchoolUrl;
    }

    @Basic
    @Column(name = "HXCondition")
    public String getHxCondition() {
        return hxCondition;
    }

    public void setHxCondition(String hxCondition) {
        this.hxCondition = hxCondition;
    }

    @Basic
    @Column(name = "HXMajorItems")
    public String getHxMajorItems() {
        return hxMajorItems;
    }

    public void setHxMajorItems(String hxMajorItems) {
        this.hxMajorItems = hxMajorItems;
    }

    @Basic
    @Column(name = "YHPreferential")
    public String getYhPreferential() {
        return yhPreferential;
    }

    public void setYhPreferential(String yhPreferential) {
        this.yhPreferential = yhPreferential;
    }

    @Basic
    @Column(name = "SBIsRecruit")
    public Boolean getSbIsRecruit() {
        return sbIsRecruit;
    }

    public void setSbIsRecruit(Boolean sbIsRecruit) {
        this.sbIsRecruit = sbIsRecruit;
    }

    @Basic
    @Column(name = "SBIsLocking")
    public Boolean getSbIsLocking() {
        return sbIsLocking;
    }

    public void setSbIsLocking(Boolean sbIsLocking) {
        this.sbIsLocking = sbIsLocking;
    }

    @Basic
    @Column(name = "SBMajorType")
    public String getSbMajorType() {
        return sbMajorType;
    }

    public void setSbMajorType(String sbMajorType) {
        this.sbMajorType = sbMajorType;
    }

    @Basic
    @Column(name = "SBCollegeQty")
    public String getSbCollegeQty() {
        return sbCollegeQty;
    }

    public void setSbCollegeQty(String sbCollegeQty) {
        this.sbCollegeQty = sbCollegeQty;
    }

    @Basic
    @Column(name = "SBMajorQty")
    public String getSbMajorQty() {
        return sbMajorQty;
    }

    public void setSbMajorQty(String sbMajorQty) {
        this.sbMajorQty = sbMajorQty;
    }

    @Basic
    @Column(name = "SBRemark")
    public String getSbRemark() {
        return sbRemark;
    }

    public void setSbRemark(String sbRemark) {
        this.sbRemark = sbRemark;
    }

    @Basic
    @Column(name = "SBRemarkFile")
    public String getSbRemarkFile() {
        return sbRemarkFile;
    }

    public void setSbRemarkFile(String sbRemarkFile) {
        this.sbRemarkFile = sbRemarkFile;
    }

    @Basic
    @Column(name = "TJIsNeed")
    public Boolean getTjIsNeed() {
        return tjIsNeed;
    }

    public void setTjIsNeed(Boolean tjIsNeed) {
        this.tjIsNeed = tjIsNeed;
    }

    @Basic
    @Column(name = "TJIsUpload")
    public Boolean getTjIsUpload() {
        return tjIsUpload;
    }

    public void setTjIsUpload(Boolean tjIsUpload) {
        this.tjIsUpload = tjIsUpload;
    }

    @Basic
    @Column(name = "TJUploadDate")
    public String getTjUploadDate() {
        return tjUploadDate;
    }

    public void setTjUploadDate(String tjUploadDate) {
        this.tjUploadDate = tjUploadDate;
    }

    @Basic
    @Column(name = "TJPostClaim")
    public String getTjPostClaim() {
        return tjPostClaim;
    }

    public void setTjPostClaim(String tjPostClaim) {
        this.tjPostClaim = tjPostClaim;
    }

    @Basic
    @Column(name = "TJPostCloseDate")
    public String getTjPostCloseDate() {
        return tjPostCloseDate;
    }

    public void setTjPostCloseDate(String tjPostCloseDate) {
        this.tjPostCloseDate = tjPostCloseDate;
    }

    @Basic
    @Column(name = "TJRemark")
    public String getTjRemark() {
        return tjRemark;
    }

    public void setTjRemark(String tjRemark) {
        this.tjRemark = tjRemark;
    }

    @Basic
    @Column(name = "ZJLetterWords")
    public String getZjLetterWords() {
        return zjLetterWords;
    }

    public void setZjLetterWords(String zjLetterWords) {
        this.zjLetterWords = zjLetterWords;
    }

    @Basic
    @Column(name = "ZJClaimContent")
    public String getZjClaimContent() {
        return zjClaimContent;
    }

    public void setZjClaimContent(String zjClaimContent) {
        this.zjClaimContent = zjClaimContent;
    }

    @Basic
    @Column(name = "ZJSpecific")
    public String getZjSpecific() {
        return zjSpecific;
    }

    public void setZjSpecific(String zjSpecific) {
        this.zjSpecific = zjSpecific;
    }

    @Basic
    @Column(name = "ZJSend")
    public String getZjSend() {
        return zjSend;
    }

    public void setZjSend(String zjSend) {
        this.zjSend = zjSend;
    }

    @Basic
    @Column(name = "ZJSource")
    public String getZjSource() {
        return zjSource;
    }

    public void setZjSource(String zjSource) {
        this.zjSource = zjSource;
    }

    @Basic
    @Column(name = "BSTestContent")
    public String getBsTestContent() {
        return bsTestContent;
    }

    public void setBsTestContent(String bsTestContent) {
        this.bsTestContent = bsTestContent;
    }

    @Basic
    @Column(name = "BSBeginDate")
    public String getBsBeginDate() {
        return bsBeginDate;
    }

    public void setBsBeginDate(String bsBeginDate) {
        this.bsBeginDate = bsBeginDate;
    }

    @Basic
    @Column(name = "BSIsSameTime")
    public Boolean getBsIsSameTime() {
        return bsIsSameTime;
    }

    public void setBsIsSameTime(Boolean bsIsSameTime) {
        this.bsIsSameTime = bsIsSameTime;
    }

    @Basic
    @Column(name = "BSAdress")
    public String getBsAdress() {
        return bsAdress;
    }

    public void setBsAdress(String bsAdress) {
        this.bsAdress = bsAdress;
    }

    @Basic
    @Column(name = "BSSpecificDesc")
    public String getBsSpecificDesc() {
        return bsSpecificDesc;
    }

    public void setBsSpecificDesc(String bsSpecificDesc) {
        this.bsSpecificDesc = bsSpecificDesc;
    }

    @Basic
    @Column(name = "YJBeginDate")
    public String getYjBeginDate() {
        return yjBeginDate;
    }

    public void setYjBeginDate(String yjBeginDate) {
        this.yjBeginDate = yjBeginDate;
    }

    @Basic
    @Column(name = "YJEndDate")
    public String getYjEndDate() {
        return yjEndDate;
    }

    public void setYjEndDate(String yjEndDate) {
        this.yjEndDate = yjEndDate;
    }

    @Basic
    @Column(name = "YJContent")
    public String getYjContent() {
        return yjContent;
    }

    public void setYjContent(String yjContent) {
        this.yjContent = yjContent;
    }

    @Basic
    @Column(name = "YJSpecificClaim")
    public String getYjSpecificClaim() {
        return yjSpecificClaim;
    }

    public void setYjSpecificClaim(String yjSpecificClaim) {
        this.yjSpecificClaim = yjSpecificClaim;
    }

    @Basic
    @Column(name = "QTClaim")
    public String getQtClaim() {
        return qtClaim;
    }

    public void setQtClaim(String qtClaim) {
        this.qtClaim = qtClaim;
    }

    @Basic
    @Column(name = "UserID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "UserName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "Dateline")
    public Timestamp getDateline() {
        return dateline;
    }

    public void setDateline(Timestamp dateline) {
        this.dateline = dateline;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uploadList")
    public String getUploadList() {
        return uploadList;
    }

    public void setUploadList(String uploadList) {
        this.uploadList = uploadList;
    }

    @Basic
    @Column(name = "regulations")
    public String getRegulations() {
        return regulations;
    }

    public void setRegulations(String regulations) {
        this.regulations = regulations;
    }

    @Basic
    @Column(name = "xzyDate")
    public String getXzyDate() {
        return xzyDate;
    }

    public void setXzyDate(String xzyDate) {
        this.xzyDate = xzyDate;
    }

    @Basic
    @Column(name = "grcs")
    public String getGrcs() {
        return grcs;
    }

    public void setGrcs(String grcs) {
        this.grcs = grcs;
    }

    @Basic
    @Column(name = "grcsDate")
    public String getGrcsDate() {
        return grcsDate;
    }

    public void setGrcsDate(String grcsDate) {
        this.grcsDate = grcsDate;
    }

    @Basic
    @Column(name = "cjms")
    public String getCjms() {
        return cjms;
    }

    public void setCjms(String cjms) {
        this.cjms = cjms;
    }

    @Basic
    @Column(name = "cjmsDate")
    public String getCjmsDate() {
        return cjmsDate;
    }

    public void setCjmsDate(String cjmsDate) {
        this.cjmsDate = cjmsDate;
    }

    @Basic
    @Column(name = "dkcjms")
    public String getDkcjms() {
        return dkcjms;
    }

    public void setDkcjms(String dkcjms) {
        this.dkcjms = dkcjms;
    }

    @Basic
    @Column(name = "dkcjmsDate")
    public String getDkcjmsDate() {
        return dkcjmsDate;
    }

    public void setDkcjmsDate(String dkcjmsDate) {
        this.dkcjmsDate = dkcjmsDate;
    }

    @Basic
    @Column(name = "fjcl")
    public String getFjcl() {
        return fjcl;
    }

    public void setFjcl(String fjcl) {
        this.fjcl = fjcl;
    }

    @Basic
    @Column(name = "fjclDate")
    public String getFjclDate() {
        return fjclDate;
    }

    public void setFjclDate(String fjclDate) {
        this.fjclDate = fjclDate;
    }

    @Basic
    @Column(name = "tjrxxsm")
    public String getTjrxxsm() {
        return tjrxxsm;
    }

    public void setTjrxxsm(String tjrxxsm) {
        this.tjrxxsm = tjrxxsm;
    }

    @Basic
    @Column(name = "tjrxxsmDate")
    public String getTjrxxsmDate() {
        return tjrxxsmDate;
    }

    public void setTjrxxsmDate(String tjrxxsmDate) {
        this.tjrxxsmDate = tjrxxsmDate;
    }

    @Basic
    @Column(name = "ylwtsm")
    public String getYlwtsm() {
        return ylwtsm;
    }

    public void setYlwtsm(String ylwtsm) {
        this.ylwtsm = ylwtsm;
    }

    @Basic
    @Column(name = "ylwtsmDate")
    public String getYlwtsmDate() {
        return ylwtsmDate;
    }

    public void setYlwtsmDate(String ylwtsmDate) {
        this.ylwtsmDate = ylwtsmDate;
    }

    @Basic
    @Column(name = "qrzyjcnr")
    public String getQrzyjcnr() {
        return qrzyjcnr;
    }

    public void setQrzyjcnr(String qrzyjcnr) {
        this.qrzyjcnr = qrzyjcnr;
    }

    @Basic
    @Column(name = "qrzyjcnrDate")
    public String getQrzyjcnrDate() {
        return qrzyjcnrDate;
    }

    public void setQrzyjcnrDate(String qrzyjcnrDate) {
        this.qrzyjcnrDate = qrzyjcnrDate;
    }

    @Basic
    @Column(name = "xzsqbyq")
    public String getXzsqbyq() {
        return xzsqbyq;
    }

    public void setXzsqbyq(String xzsqbyq) {
        this.xzsqbyq = xzsqbyq;
    }

    @Basic
    @Column(name = "xzsqbDate")
    public String getXzsqbDate() {
        return xzsqbDate;
    }

    public void setXzsqbDate(String xzsqbDate) {
        this.xzsqbDate = xzsqbDate;
    }

    @Basic
    @Column(name = "scsqbyq")
    public String getScsqbyq() {
        return scsqbyq;
    }

    public void setScsqbyq(String scsqbyq) {
        this.scsqbyq = scsqbyq;
    }

    @Basic
    @Column(name = "scsqbDate")
    public String getScsqbDate() {
        return scsqbDate;
    }

    public void setScsqbDate(String scsqbDate) {
        this.scsqbDate = scsqbDate;
    }

    @Basic
    @Column(name = "yjcllb")
    public String getYjcllb() {
        return yjcllb;
    }

    public void setYjcllb(String yjcllb) {
        this.yjcllb = yjcllb;
    }

    @Basic
    @Column(name = "yjcllbDate")
    public String getYjcllbDate() {
        return yjcllbDate;
    }

    public void setYjcllbDate(String yjcllbDate) {
        this.yjcllbDate = yjcllbDate;
    }

    @Basic
    @Column(name = "cstgxygj")
    public String getCstgxygj() {
        return cstgxygj;
    }

    public void setCstgxygj(String cstgxygj) {
        this.cstgxygj = cstgxygj;
    }

    @Basic
    @Column(name = "cstgDate")
    public String getCstgDate() {
        return cstgDate;
    }

    public void setCstgDate(String cstgDate) {
        this.cstgDate = cstgDate;
    }

    @Basic
    @Column(name = "ksyq")
    public String getKsyq() {
        return ksyq;
    }

    public void setKsyq(String ksyq) {
        this.ksyq = ksyq;
    }

    @Basic
    @Column(name = "ksDate")
    public String getKsDate() {
        return ksDate;
    }

    public void setKsDate(String ksDate) {
        this.ksDate = ksDate;
    }

    @Basic
    @Column(name = "jfyq")
    public String getJfyq() {
        return jfyq;
    }

    public void setJfyq(String jfyq) {
        this.jfyq = jfyq;
    }

    @Basic
    @Column(name = "jfDate")
    public String getJfDate() {
        return jfDate;
    }

    public void setJfDate(String jfDate) {
        this.jfDate = jfDate;
    }

    @Basic
    @Column(name = "zkzyq")
    public String getZkzyq() {
        return zkzyq;
    }

    public void setZkzyq(String zkzyq) {
        this.zkzyq = zkzyq;
    }

    @Basic
    @Column(name = "zkzdyDate")
    public String getZkzdyDate() {
        return zkzdyDate;
    }

    public void setZkzdyDate(String zkzdyDate) {
        this.zkzdyDate = zkzdyDate;
    }

    @Basic
    @Column(name = "hdjfsm")
    public String getHdjfsm() {
        return hdjfsm;
    }

    public void setHdjfsm(String hdjfsm) {
        this.hdjfsm = hdjfsm;
    }

    @Basic
    @Column(name = "zyDate")
    public String getZyDate() {
        return zyDate;
    }

    public void setZyDate(String zyDate) {
        this.zyDate = zyDate;
    }

    @Basic
    @Column(name = "zhxxyq")
    public String getZhxxyq() {
        return zhxxyq;
    }

    public void setZhxxyq(String zhxxyq) {
        this.zhxxyq = zhxxyq;
    }

    @Basic
    @Column(name = "zhxxyqDate")
    public String getZhxxyqDate() {
        return zhxxyqDate;
    }

    public void setZhxxyqDate(String zhxxyqDate) {
        this.zhxxyqDate = zhxxyqDate;
    }

    @Basic
    @Column(name = "univ_type")
    public Integer getUnivType() {
        return univType;
    }

    public void setUnivType(Integer univType) {
        this.univType = univType;
    }

    @Basic
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "xbcx")
    public String getXbcx() {
        return xbcx;
    }

    public void setXbcx(String xbcx) {
        this.xbcx = xbcx;
    }

    @Basic
    @Column(name = "yhfz")
    public String getYhfz() {
        return yhfz;
    }

    public void setYhfz(String yhfz) {
        this.yhfz = yhfz;
    }

    @Basic
    @Column(name = "lqyz")
    public String getLqyz() {
        return lqyz;
    }

    public void setLqyz(String lqyz) {
        this.lqyz = lqyz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TNLocationUnivDetail that = (TNLocationUnivDetail) o;
        return id == that.id &&
                Objects.equals(pkid, that.pkid) &&
                Objects.equals(jbSchoolName, that.jbSchoolName) &&
                Objects.equals(jbSignBeginDate, that.jbSignBeginDate) &&
                Objects.equals(jbSignEndDate, that.jbSignEndDate) &&
                Objects.equals(jbRegulations, that.jbRegulations) &&
                Objects.equals(jbSignUrl, that.jbSignUrl) &&
                Objects.equals(jbSchoolUrl, that.jbSchoolUrl) &&
                Objects.equals(hxCondition, that.hxCondition) &&
                Objects.equals(hxMajorItems, that.hxMajorItems) &&
                Objects.equals(yhPreferential, that.yhPreferential) &&
                Objects.equals(sbIsRecruit, that.sbIsRecruit) &&
                Objects.equals(sbIsLocking, that.sbIsLocking) &&
                Objects.equals(sbMajorType, that.sbMajorType) &&
                Objects.equals(sbCollegeQty, that.sbCollegeQty) &&
                Objects.equals(sbMajorQty, that.sbMajorQty) &&
                Objects.equals(sbRemark, that.sbRemark) &&
                Objects.equals(sbRemarkFile, that.sbRemarkFile) &&
                Objects.equals(tjIsNeed, that.tjIsNeed) &&
                Objects.equals(tjIsUpload, that.tjIsUpload) &&
                Objects.equals(tjUploadDate, that.tjUploadDate) &&
                Objects.equals(tjPostClaim, that.tjPostClaim) &&
                Objects.equals(tjPostCloseDate, that.tjPostCloseDate) &&
                Objects.equals(tjRemark, that.tjRemark) &&
                Objects.equals(zjLetterWords, that.zjLetterWords) &&
                Objects.equals(zjClaimContent, that.zjClaimContent) &&
                Objects.equals(zjSpecific, that.zjSpecific) &&
                Objects.equals(zjSend, that.zjSend) &&
                Objects.equals(zjSource, that.zjSource) &&
                Objects.equals(bsTestContent, that.bsTestContent) &&
                Objects.equals(bsBeginDate, that.bsBeginDate) &&
                Objects.equals(bsIsSameTime, that.bsIsSameTime) &&
                Objects.equals(bsAdress, that.bsAdress) &&
                Objects.equals(bsSpecificDesc, that.bsSpecificDesc) &&
                Objects.equals(yjBeginDate, that.yjBeginDate) &&
                Objects.equals(yjEndDate, that.yjEndDate) &&
                Objects.equals(yjContent, that.yjContent) &&
                Objects.equals(yjSpecificClaim, that.yjSpecificClaim) &&
                Objects.equals(qtClaim, that.qtClaim) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(dateline, that.dateline) &&
                Objects.equals(uploadList, that.uploadList) &&
                Objects.equals(regulations, that.regulations) &&
                Objects.equals(xzyDate, that.xzyDate) &&
                Objects.equals(grcs, that.grcs) &&
                Objects.equals(grcsDate, that.grcsDate) &&
                Objects.equals(cjms, that.cjms) &&
                Objects.equals(cjmsDate, that.cjmsDate) &&
                Objects.equals(dkcjms, that.dkcjms) &&
                Objects.equals(dkcjmsDate, that.dkcjmsDate) &&
                Objects.equals(fjcl, that.fjcl) &&
                Objects.equals(fjclDate, that.fjclDate) &&
                Objects.equals(tjrxxsm, that.tjrxxsm) &&
                Objects.equals(tjrxxsmDate, that.tjrxxsmDate) &&
                Objects.equals(ylwtsm, that.ylwtsm) &&
                Objects.equals(ylwtsmDate, that.ylwtsmDate) &&
                Objects.equals(qrzyjcnr, that.qrzyjcnr) &&
                Objects.equals(qrzyjcnrDate, that.qrzyjcnrDate) &&
                Objects.equals(xzsqbyq, that.xzsqbyq) &&
                Objects.equals(xzsqbDate, that.xzsqbDate) &&
                Objects.equals(scsqbyq, that.scsqbyq) &&
                Objects.equals(scsqbDate, that.scsqbDate) &&
                Objects.equals(yjcllb, that.yjcllb) &&
                Objects.equals(yjcllbDate, that.yjcllbDate) &&
                Objects.equals(cstgxygj, that.cstgxygj) &&
                Objects.equals(cstgDate, that.cstgDate) &&
                Objects.equals(ksyq, that.ksyq) &&
                Objects.equals(ksDate, that.ksDate) &&
                Objects.equals(jfyq, that.jfyq) &&
                Objects.equals(jfDate, that.jfDate) &&
                Objects.equals(zkzyq, that.zkzyq) &&
                Objects.equals(zkzdyDate, that.zkzdyDate) &&
                Objects.equals(hdjfsm, that.hdjfsm) &&
                Objects.equals(zyDate, that.zyDate) &&
                Objects.equals(zhxxyq, that.zhxxyq) &&
                Objects.equals(zhxxyqDate, that.zhxxyqDate) &&
                Objects.equals(univType, that.univType) &&
                Objects.equals(year, that.year) &&
                Objects.equals(xbcx, that.xbcx) &&
                Objects.equals(yhfz, that.yhfz) &&
                Objects.equals(lqyz, that.lqyz);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pkid, jbSchoolName, jbSignBeginDate, jbSignEndDate, jbRegulations, jbSignUrl, jbSchoolUrl, hxCondition, hxMajorItems, yhPreferential, sbIsRecruit, sbIsLocking, sbMajorType, sbCollegeQty, sbMajorQty, sbRemark, sbRemarkFile, tjIsNeed, tjIsUpload, tjUploadDate, tjPostClaim, tjPostCloseDate, tjRemark, zjLetterWords, zjClaimContent, zjSpecific, zjSend, zjSource, bsTestContent, bsBeginDate, bsIsSameTime, bsAdress, bsSpecificDesc, yjBeginDate, yjEndDate, yjContent, yjSpecificClaim, qtClaim, userId, userName, dateline, id, uploadList, regulations, xzyDate, grcs, grcsDate, cjms, cjmsDate, dkcjms, dkcjmsDate, fjcl, fjclDate, tjrxxsm, tjrxxsmDate, ylwtsm, ylwtsmDate, qrzyjcnr, qrzyjcnrDate, xzsqbyq, xzsqbDate, scsqbyq, scsqbDate, yjcllb, yjcllbDate, cstgxygj, cstgDate, ksyq, ksDate, jfyq, jfDate, zkzyq, zkzdyDate, hdjfsm, zyDate, zhxxyq, zhxxyqDate, univType, year, xbcx, yhfz, lqyz);
    }
}
