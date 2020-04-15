package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/30
 */
@Entity
@Table(name = "t_xueji_sign", schema = "wodecareer", catalog = "")
public class TXuejiSign {
    private int id;
    private String proCode;
    private Integer proId;
    private String proName;
    private String cityCode;
    private String cityName;
    private String quxianCode;
    private String quxianName;
    private String schoolCode;
    private String schoolName;
    private String code;
    private String name;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pro_code")
    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    @Basic
    @Column(name = "pro_id")
    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    @Basic
    @Column(name = "pro_name")
    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    @Basic
    @Column(name = "city_code")
    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @Basic
    @Column(name = "city_name")
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Basic
    @Column(name = "quxian_code")
    public String getQuxianCode() {
        return quxianCode;
    }

    public void setQuxianCode(String quxianCode) {
        this.quxianCode = quxianCode;
    }

    @Basic
    @Column(name = "quxian_name")
    public String getQuxianName() {
        return quxianName;
    }

    public void setQuxianName(String quxianName) {
        this.quxianName = quxianName;
    }

    @Basic
    @Column(name = "school_code")
    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
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
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TXuejiSign that = (TXuejiSign) o;
        return id == that.id &&
                Objects.equals(proCode, that.proCode) &&
                Objects.equals(proId, that.proId) &&
                Objects.equals(proName, that.proName) &&
                Objects.equals(cityCode, that.cityCode) &&
                Objects.equals(cityName, that.cityName) &&
                Objects.equals(quxianCode, that.quxianCode) &&
                Objects.equals(quxianName, that.quxianName) &&
                Objects.equals(schoolCode, that.schoolCode) &&
                Objects.equals(schoolName, that.schoolName) &&
                Objects.equals(code, that.code) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, proCode, proId, proName, cityCode, cityName, quxianCode, quxianName, schoolCode, schoolName, code, name);
    }
}
