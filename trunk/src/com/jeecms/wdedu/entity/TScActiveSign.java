package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_sc_active_sign", schema = "wodecareer", catalog = "")
public class TScActiveSign {
    private Integer activeId;
    private int id;
    private String name;
    private String telephone;
    private String pro;
    private String city;
    private String quxian;
    private String schoolName;
    private String classes;
    private String majorType;
    private Integer classRank;
    private Integer peonum;
    private String ruchangquanType;
    private Integer bmRank;
    private String ruchangquanCode;
    private String lingquanTime;
    private String qiandaoType;
    private Integer qdRank;
    private String qiandaoTime;
    private String lingquziliao;
    private String lzlTime;
    private String infoPaySuccess;
    private Integer ifzxd;
    private String telephone2;
    private String jiebie;
    private String branchCompany;
    private String remark;

    @Basic
    @Column(name = "active_id")
    public Integer getActiveId() {
        return activeId;
    }

    public void setActiveId(Integer activeId) {
        this.activeId = activeId;
    }

    @Basic
    @Column(name = "ifzxd")
    public Integer getIfzxd() {
        return ifzxd;
    }

    public void setIfzxd(Integer ifzxd) {
        this.ifzxd = ifzxd;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "pro")
    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "quxian")
    public String getQuxian() {
        return quxian;
    }

    public void setQuxian(String quxian) {
        this.quxian = quxian;
    }

    @Basic
    @Column(name = "school_name")
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Basic
    @Column(name = "classes")
    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    @Basic
    @Column(name = "major_type")
    public String getMajorType() {
        return majorType;
    }

    public void setMajorType(String majorType) {
        this.majorType = majorType;
    }

    @Basic
    @Column(name = "class_rank")
    public Integer getClassRank() {
        return classRank;
    }

    public void setClassRank(Integer classRank) {
        this.classRank = classRank;
    }

    @Basic
    @Column(name = "peonum")
    public Integer getPeonum() {
        return peonum;
    }

    public void setPeonum(Integer peonum) {
        this.peonum = peonum;
    }

    @Basic
    @Column(name = "ruchangquan_type")
    public String getRuchangquanType() {
        return ruchangquanType;
    }

    public void setRuchangquanType(String ruchangquanType) {
        this.ruchangquanType = ruchangquanType;
    }

    @Basic
    @Column(name = "bm_rank")
    public Integer getBmRank() {
        return bmRank;
    }

    public void setBmRank(Integer bmRank) {
        this.bmRank = bmRank;
    }

    @Basic
    @Column(name = "ruchangquan_code")
    public String getRuchangquanCode() {
        return ruchangquanCode;
    }

    public void setRuchangquanCode(String ruchangquanCode) {
        this.ruchangquanCode = ruchangquanCode;
    }

    @Basic
    @Column(name = "lingquan_time")
    public String getLingquanTime() {
        return lingquanTime;
    }

    public void setLingquanTime(String lingquanTime) {
        this.lingquanTime = lingquanTime;
    }

    @Basic
    @Column(name = "qiandao_type")
    public String getQiandaoType() {
        return qiandaoType;
    }

    public void setQiandaoType(String qiandaoType) {
        this.qiandaoType = qiandaoType;
    }

    @Basic
    @Column(name = "qd_rank")
    public Integer getQdRank() {
        return qdRank;
    }

    public void setQdRank(Integer qdRank) {
        this.qdRank = qdRank;
    }

    @Basic
    @Column(name = "qiandao_time")
    public String getQiandaoTime() {
        return qiandaoTime;
    }

    public void setQiandaoTime(String qiandaoTime) {
        this.qiandaoTime = qiandaoTime;
    }

    @Basic
    @Column(name = "lingquziliao")
    public String getLingquziliao() {
        return lingquziliao;
    }

    public void setLingquziliao(String lingquziliao) {
        this.lingquziliao = lingquziliao;
    }

    @Basic
    @Column(name = "lzl_time")
    public String getLzlTime() {
        return lzlTime;
    }

    public void setLzlTime(String lzlTime) {
        this.lzlTime = lzlTime;
    }

    @Basic
    @Column(name = "info_pay_success")
    public String getInfoPaySuccess() {
        return infoPaySuccess;
    }

    public void setInfoPaySuccess(String infoPaySuccess) {
        this.infoPaySuccess = infoPaySuccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TScActiveSign that = (TScActiveSign) o;
        return id == that.id &&
                Objects.equals(activeId, that.activeId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(telephone, that.telephone) &&
                Objects.equals(pro, that.pro) &&
                Objects.equals(city, that.city) &&
                Objects.equals(quxian, that.quxian) &&
                Objects.equals(schoolName, that.schoolName) &&
                Objects.equals(classes, that.classes) &&
                Objects.equals(majorType, that.majorType) &&
                Objects.equals(classRank, that.classRank) &&
                Objects.equals(peonum, that.peonum) &&
                Objects.equals(ruchangquanType, that.ruchangquanType) &&
                Objects.equals(bmRank, that.bmRank) &&
                Objects.equals(ruchangquanCode, that.ruchangquanCode) &&
                Objects.equals(lingquanTime, that.lingquanTime) &&
                Objects.equals(qiandaoType, that.qiandaoType) &&
                Objects.equals(qdRank, that.qdRank) &&
                Objects.equals(qiandaoTime, that.qiandaoTime) &&
                Objects.equals(lingquziliao, that.lingquziliao) &&
                Objects.equals(lzlTime, that.lzlTime) &&
                Objects.equals(infoPaySuccess, that.infoPaySuccess);
    }

    @Override
    public int hashCode() {

        return Objects.hash(activeId, id, name, telephone, pro, city, quxian, schoolName, classes, majorType, classRank, peonum, ruchangquanType, bmRank, ruchangquanCode, lingquanTime, qiandaoType, qdRank, qiandaoTime, lingquziliao, lzlTime, infoPaySuccess);
    }

    @Basic
    @Column(name = "telephone2")
    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    @Basic
    @Column(name = "jiebie")
    public String getJiebie() {
        return jiebie;
    }

    public void setJiebie(String jiebie) {
        this.jiebie = jiebie;
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
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
