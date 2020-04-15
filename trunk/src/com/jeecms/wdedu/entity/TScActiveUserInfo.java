package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_sc_active_user_info", schema = "wodecareer", catalog = "")
public class TScActiveUserInfo {
    private int id;
    private String username;
    private String telephone;
    private String province;
    private String city;
    private String quxian;
    private String schoolName;
    private String majorType;
    private String classes;
    private Integer classRank;
    private Integer bmActiveNum;
    private Integer qdActiveNum;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    @Column(name = "major_type")
    public String getMajorType() {
        return majorType;
    }

    public void setMajorType(String majorType) {
        this.majorType = majorType;
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
    @Column(name = "class_rank")
    public Integer getClassRank() {
        return classRank;
    }

    public void setClassRank(Integer classRank) {
        this.classRank = classRank;
    }

    @Basic
    @Column(name = "bm_active_num")
    public Integer getBmActiveNum() {
        return bmActiveNum;
    }

    public void setBmActiveNum(Integer bmActiveNum) {
        this.bmActiveNum = bmActiveNum;
    }

    @Basic
    @Column(name = "qd_active_num")
    public Integer getQdActiveNum() {
        return qdActiveNum;
    }

    public void setQdActiveNum(Integer qdActiveNum) {
        this.qdActiveNum = qdActiveNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TScActiveUserInfo that = (TScActiveUserInfo) o;
        return id == that.id &&
                Objects.equals(username, that.username) &&
                Objects.equals(telephone, that.telephone) &&
                Objects.equals(province, that.province) &&
                Objects.equals(city, that.city) &&
                Objects.equals(quxian, that.quxian) &&
                Objects.equals(schoolName, that.schoolName) &&
                Objects.equals(majorType, that.majorType) &&
                Objects.equals(classes, that.classes) &&
                Objects.equals(classRank, that.classRank) &&
                Objects.equals(bmActiveNum, that.bmActiveNum) &&
                Objects.equals(qdActiveNum, that.qdActiveNum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, telephone, province, city, quxian, schoolName, majorType, classes, classRank, bmActiveNum, qdActiveNum);
    }
}
