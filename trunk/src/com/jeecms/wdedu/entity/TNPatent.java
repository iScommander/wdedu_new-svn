package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_patent", schema = "wodecareer", catalog = "")
public class TNPatent {
    private String id;
    private String userAccount;
    private String patentName;
    private String patentType;
    private String patentee;
    private String applydate;
    private String accreditDate;
    private String patentHolder;
    private String patentNumber;
    private String patentPhoto;
    private Integer photoCount;
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
    @Column(name = "patentName")
    public String getPatentName() {
        return patentName;
    }

    public void setPatentName(String patentName) {
        this.patentName = patentName;
    }

    @Basic
    @Column(name = "patentType")
    public String getPatentType() {
        return patentType;
    }

    public void setPatentType(String patentType) {
        this.patentType = patentType;
    }

    @Basic
    @Column(name = "patentee")
    public String getPatentee() {
        return patentee;
    }

    public void setPatentee(String patentee) {
        this.patentee = patentee;
    }

    @Basic
    @Column(name = "applydate")
    public String getApplydate() {
        return applydate;
    }

    public void setApplydate(String applydate) {
        this.applydate = applydate;
    }

    @Basic
    @Column(name = "accreditDate")
    public String getAccreditDate() {
        return accreditDate;
    }

    public void setAccreditDate(String accreditDate) {
        this.accreditDate = accreditDate;
    }

    @Basic
    @Column(name = "patentHolder")
    public String getPatentHolder() {
        return patentHolder;
    }

    public void setPatentHolder(String patentHolder) {
        this.patentHolder = patentHolder;
    }

    @Basic
    @Column(name = "patentNumber")
    public String getPatentNumber() {
        return patentNumber;
    }

    public void setPatentNumber(String patentNumber) {
        this.patentNumber = patentNumber;
    }

    @Basic
    @Column(name = "patentPhoto")
    public String getPatentPhoto() {
        return patentPhoto;
    }

    public void setPatentPhoto(String patentPhoto) {
        this.patentPhoto = patentPhoto;
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
        TNPatent tnPatent = (TNPatent) o;
        return Objects.equals(id, tnPatent.id) &&
                Objects.equals(userAccount, tnPatent.userAccount) &&
                Objects.equals(patentName, tnPatent.patentName) &&
                Objects.equals(patentType, tnPatent.patentType) &&
                Objects.equals(patentee, tnPatent.patentee) &&
                Objects.equals(applydate, tnPatent.applydate) &&
                Objects.equals(accreditDate, tnPatent.accreditDate) &&
                Objects.equals(patentHolder, tnPatent.patentHolder) &&
                Objects.equals(patentNumber, tnPatent.patentNumber) &&
                Objects.equals(patentPhoto, tnPatent.patentPhoto) &&
                Objects.equals(photoCount, tnPatent.photoCount) &&
                Objects.equals(userId, tnPatent.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userAccount, patentName, patentType, patentee, applydate, accreditDate, patentHolder, patentNumber, patentPhoto, photoCount, userId);
    }
}
