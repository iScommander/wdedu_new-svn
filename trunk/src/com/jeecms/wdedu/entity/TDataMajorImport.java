package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @date 2018/11/9
 */
@Entity
@Table(name = "t_data_major_import", schema = "wodecareer", catalog = "")
public class TDataMajorImport {
    private int id;
    private Integer provinceId;
    private Integer univId;
    private Integer univBaseId;
    private String univName;
    private String majorName;
    private Integer majorTypeId;
    private String remark;

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
    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @Basic
    @Column(name = "univ_id")
    public Integer getUnivId() {
        return univId;
    }

    public void setUnivId(Integer univId) {
        this.univId = univId;
    }

    @Basic
    @Column(name = "univ_base_id")
    public Integer getUnivBaseId() {
        return univBaseId;
    }

    public void setUnivBaseId(Integer univBaseId) {
        this.univBaseId = univBaseId;
    }

    @Basic
    @Column(name = "univ_name")
    public String getUnivName() {
        return univName;
    }

    public void setUnivName(String univName) {
        this.univName = univName;
    }

    @Basic
    @Column(name = "major_name")
    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Basic
    @Column(name = "major_type_id")
    public Integer getMajorTypeId() {
        return majorTypeId;
    }

    public void setMajorTypeId(Integer majorTypeId) {
        this.majorTypeId = majorTypeId;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TDataMajorImport that = (TDataMajorImport) o;
        return id == that.id &&
                Objects.equals(provinceId, that.provinceId) &&
                Objects.equals(univId, that.univId) &&
                Objects.equals(univBaseId, that.univBaseId) &&
                Objects.equals(univName, that.univName) &&
                Objects.equals(majorName, that.majorName) &&
                Objects.equals(majorTypeId, that.majorTypeId) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, provinceId, univId, univBaseId, univName, majorName, majorTypeId, remark);
    }
}
