package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_activity", schema = "wodecareer", catalog = "")
public class TNActivity {
    private String id;
    private String userAccount;
    private String active;
    private String role;
    private String activeStratTime;
    private String activeEndTime;
    private String organizational;
    private Integer photoCount;
    private String provePhoto;
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
    @Column(name = "active")
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "activeStratTime")
    public String getActiveStratTime() {
        return activeStratTime;
    }

    public void setActiveStratTime(String activeStratTime) {
        this.activeStratTime = activeStratTime;
    }

    @Basic
    @Column(name = "activeEndTime")
    public String getActiveEndTime() {
        return activeEndTime;
    }

    public void setActiveEndTime(String activeEndTime) {
        this.activeEndTime = activeEndTime;
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
    @Column(name = "photoCount")
    public Integer getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
    }

    @Basic
    @Column(name = "provePhoto")
    public String getProvePhoto() {
        return provePhoto;
    }

    public void setProvePhoto(String provePhoto) {
        this.provePhoto = provePhoto;
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
        TNActivity that = (TNActivity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userAccount, that.userAccount) &&
                Objects.equals(active, that.active) &&
                Objects.equals(role, that.role) &&
                Objects.equals(activeStratTime, that.activeStratTime) &&
                Objects.equals(activeEndTime, that.activeEndTime) &&
                Objects.equals(organizational, that.organizational) &&
                Objects.equals(photoCount, that.photoCount) &&
                Objects.equals(provePhoto, that.provePhoto) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userAccount, active, role, activeStratTime, activeEndTime, organizational, photoCount, provePhoto, userId);
    }
}
