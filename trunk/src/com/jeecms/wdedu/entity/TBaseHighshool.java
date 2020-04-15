package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @date 2018/10/17
 */
@Entity
@Table(name = "t_base_highshool", schema = "wodecareer", catalog = "")
public class TBaseHighshool {
    private int id;
    private String provinceId;
    private String provinceName;
    private String cityId;
    private String cityName;
    private String quxianId;
    private String quxianName;
    private String schoolId;
    private String schoolName;
    private String remark;
    private String state;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "province_id")
    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    @Basic
    @Column(name = "province_name")
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Basic
    @Column(name = "city_id")
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
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
    @Column(name = "quxian_id")
    public String getQuxianId() {
        return quxianId;
    }

    public void setQuxianId(String quxianId) {
        this.quxianId = quxianId;
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
    @Column(name = "school_id")
    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
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
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TBaseHighshool that = (TBaseHighshool) o;
        return id == that.id &&
                Objects.equals(provinceId, that.provinceId) &&
                Objects.equals(provinceName, that.provinceName) &&
                Objects.equals(cityId, that.cityId) &&
                Objects.equals(cityName, that.cityName) &&
                Objects.equals(quxianId, that.quxianId) &&
                Objects.equals(quxianName, that.quxianName) &&
                Objects.equals(schoolId, that.schoolId) &&
                Objects.equals(schoolName, that.schoolName) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, provinceId, provinceName, cityId, cityName, quxianId, quxianName, schoolId, schoolName, remark, state);
    }
}
