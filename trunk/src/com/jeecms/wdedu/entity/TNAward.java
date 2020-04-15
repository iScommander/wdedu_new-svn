package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_award", schema = "wodecareer", catalog = "")
public class TNAward {
    private String id;
    private String userAccount;
    private String awardType;
    private String awardName;
    private String awardTime;
    private String organizational;
    private String degree;
    private String certificate;
    private Integer photoCount;
    private String awardLevel;
    private Integer userId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userAccount")
    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Basic
    @Column(name = "awardType")
    public String getAwardType() {
        return awardType;
    }

    public void setAwardType(String awardType) {
        this.awardType = awardType;
    }

    @Basic
    @Column(name = "awardName")
    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    @Basic
    @Column(name = "awardTime")
    public String getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(String awardTime) {
        this.awardTime = awardTime;
    }

    @Basic
    @Column(name = "organizational")
    public String getOrganizational() {
        return organizational;
    }

    public void setOrganizational(String organizational) {
        this.organizational = organizational;
    }

    @Basic
    @Column(name = "degree")
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Basic
    @Column(name = "certificate")
    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    @Basic
    @Column(name = "photoCount")
    public Integer getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
    }

    @Basic
    @Column(name = "awardLevel")
    public String getAwardLevel() {
        return awardLevel;
    }

    public void setAwardLevel(String awardLevel) {
        this.awardLevel = awardLevel;
    }

    @Basic
    @Column(name = "userId")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TNAward tnAward = (TNAward) o;
        return Objects.equals(id, tnAward.id) &&
                Objects.equals(userAccount, tnAward.userAccount) &&
                Objects.equals(awardType, tnAward.awardType) &&
                Objects.equals(awardName, tnAward.awardName) &&
                Objects.equals(awardTime, tnAward.awardTime) &&
                Objects.equals(organizational, tnAward.organizational) &&
                Objects.equals(degree, tnAward.degree) &&
                Objects.equals(certificate, tnAward.certificate) &&
                Objects.equals(photoCount, tnAward.photoCount) &&
                Objects.equals(awardLevel, tnAward.awardLevel) &&
                Objects.equals(userId, tnAward.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userAccount, awardType, awardName, awardTime, organizational, degree, certificate, photoCount, awardLevel, userId);
    }
}
