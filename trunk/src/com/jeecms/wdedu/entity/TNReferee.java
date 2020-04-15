package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_referee", schema = "wodecareer", catalog = "")
public class TNReferee {
    private int id;
    private String userAccount;
    private String refereeType;
    private String refereeName;
    private String linkMan;
    private String province;
    private String city;
    private String region;
    private String unit;
    private String positionalTitles;
    private String duties;
    private String telephone;
    private String email;
    private Integer userId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    @Column(name = "refereeType")
    public String getRefereeType() {
        return refereeType;
    }

    public void setRefereeType(String refereeType) {
        this.refereeType = refereeType;
    }

    @Basic
    @Column(name = "refereeName")
    public String getRefereeName() {
        return refereeName;
    }

    public void setRefereeName(String refereeName) {
        this.refereeName = refereeName;
    }

    @Basic
    @Column(name = "linkMan")
    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    @Basic
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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
    @Column(name = "region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "positionalTitles")
    public String getPositionalTitles() {
        return positionalTitles;
    }

    public void setPositionalTitles(String positionalTitles) {
        this.positionalTitles = positionalTitles;
    }

    @Basic
    @Column(name = "duties")
    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        TNReferee tnReferee = (TNReferee) o;
        return id == tnReferee.id &&
                Objects.equals(userAccount, tnReferee.userAccount) &&
                Objects.equals(refereeType, tnReferee.refereeType) &&
                Objects.equals(refereeName, tnReferee.refereeName) &&
                Objects.equals(linkMan, tnReferee.linkMan) &&
                Objects.equals(province, tnReferee.province) &&
                Objects.equals(city, tnReferee.city) &&
                Objects.equals(region, tnReferee.region) &&
                Objects.equals(unit, tnReferee.unit) &&
                Objects.equals(positionalTitles, tnReferee.positionalTitles) &&
                Objects.equals(duties, tnReferee.duties) &&
                Objects.equals(telephone, tnReferee.telephone) &&
                Objects.equals(email, tnReferee.email) &&
                Objects.equals(userId, tnReferee.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userAccount, refereeType, refereeName, linkMan, province, city, region, unit, positionalTitles, duties, telephone, email, userId);
    }
}
