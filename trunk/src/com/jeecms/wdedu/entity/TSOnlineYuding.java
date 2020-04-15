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
@Table(name = "t_s_online_yuding", schema = "wodecareer", catalog = "")
public class TSOnlineYuding {
    private int id;
    private String userName;
    private String name;
    private String city;
    private String borough;
    private String school;
    private String qq;
    private String eMail;
    private String remark;
    private Integer userId;
    private String type;
    private String majorTypeId;
    private String sex;
    private Timestamp bcDt;
    private String hfjl;
    private String otherPhone;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "borough")
    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    @Basic
    @Column(name = "school")
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Basic
    @Column(name = "qq")
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "E_mail")
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
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
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "major_type_id")
    public String getMajorTypeId() {
        return majorTypeId;
    }

    public void setMajorTypeId(String majorTypeId) {
        this.majorTypeId = majorTypeId;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "bc_dt")
    public Timestamp getBcDt() {
        return bcDt;
    }

    public void setBcDt(Timestamp bcDt) {
        this.bcDt = bcDt;
    }

    @Basic
    @Column(name = "hfjl")
    public String getHfjl() {
        return hfjl;
    }

    public void setHfjl(String hfjl) {
        this.hfjl = hfjl;
    }

    @Basic
    @Column(name = "other_phone")
    public String getOtherPhone() {
        return otherPhone;
    }

    public void setOtherPhone(String otherPhone) {
        this.otherPhone = otherPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TSOnlineYuding that = (TSOnlineYuding) o;
        return id == that.id &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(name, that.name) &&
                Objects.equals(city, that.city) &&
                Objects.equals(borough, that.borough) &&
                Objects.equals(school, that.school) &&
                Objects.equals(qq, that.qq) &&
                Objects.equals(eMail, that.eMail) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(type, that.type) &&
                Objects.equals(majorTypeId, that.majorTypeId) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(bcDt, that.bcDt) &&
                Objects.equals(hfjl, that.hfjl) &&
                Objects.equals(otherPhone, that.otherPhone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userName, name, city, borough, school, qq, eMail, remark, userId, type, majorTypeId, sex, bcDt, hfjl, otherPhone);
    }
}
