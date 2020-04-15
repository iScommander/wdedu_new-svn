package com.jeecms.wdedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_data_university_majorintroduce", schema = "wodecareer", catalog = "")
public class TDataUniversityMajorintroduce {
    private Integer id;
    private Integer universityBaseId;
    private String univName;
    private Integer dataType;
    private Integer classType;
    private String univMajorCateId;
    private String univMajorCateName;
    private String univMajorId;
    private String univMajorName;
    private String univMajorIntr;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "university_base_id")
    public Integer getUniversityBaseId() {
        return universityBaseId;
    }

    public void setUniversityBaseId(Integer universityBaseId) {
        this.universityBaseId = universityBaseId;
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
    @Column(name = "data_type")
    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    @Basic
    @Column(name = "class_type")
    public Integer getClassType() {
        return classType;
    }

    public void setClassType(Integer classType) {
        this.classType = classType;
    }

    @Basic
    @Column(name = "univ_major_cate_id")
    public String getUnivMajorCateId() {
        return univMajorCateId;
    }

    public void setUnivMajorCateId(String univMajorCateId) {
        this.univMajorCateId = univMajorCateId;
    }

    @Basic
    @Column(name = "univ_major_cate_name")
    public String getUnivMajorCateName() {
        return univMajorCateName;
    }

    public void setUnivMajorCateName(String univMajorCateName) {
        this.univMajorCateName = univMajorCateName;
    }

    @Basic
    @Column(name = "univ_major_id")
    public String getUnivMajorId() {
        return univMajorId;
    }

    public void setUnivMajorId(String univMajorId) {
        this.univMajorId = univMajorId;
    }

    @Basic
    @Column(name = "univ_major_name")
    public String getUnivMajorName() {
        return univMajorName;
    }

    public void setUnivMajorName(String univMajorName) {
        this.univMajorName = univMajorName;
    }

    @Basic
    @Column(name = "univ_major_intr")
    public String getUnivMajorIntr() {
        return univMajorIntr;
    }

    public void setUnivMajorIntr(String univMajorIntr) {
        this.univMajorIntr = univMajorIntr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDataUniversityMajorintroduce that = (TDataUniversityMajorintroduce) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (universityBaseId != null ? !universityBaseId.equals(that.universityBaseId) : that.universityBaseId != null)
            return false;
        if (univName != null ? !univName.equals(that.univName) : that.univName != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (classType != null ? !classType.equals(that.classType) : that.classType != null) return false;
        if (univMajorCateId != null ? !univMajorCateId.equals(that.univMajorCateId) : that.univMajorCateId != null)
            return false;
        if (univMajorCateName != null ? !univMajorCateName.equals(that.univMajorCateName) : that.univMajorCateName != null)
            return false;
        if (univMajorId != null ? !univMajorId.equals(that.univMajorId) : that.univMajorId != null) return false;
        if (univMajorName != null ? !univMajorName.equals(that.univMajorName) : that.univMajorName != null)
            return false;
        if (univMajorIntr != null ? !univMajorIntr.equals(that.univMajorIntr) : that.univMajorIntr != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (universityBaseId != null ? universityBaseId.hashCode() : 0);
        result = 31 * result + (univName != null ? univName.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (classType != null ? classType.hashCode() : 0);
        result = 31 * result + (univMajorCateId != null ? univMajorCateId.hashCode() : 0);
        result = 31 * result + (univMajorCateName != null ? univMajorCateName.hashCode() : 0);
        result = 31 * result + (univMajorId != null ? univMajorId.hashCode() : 0);
        result = 31 * result + (univMajorName != null ? univMajorName.hashCode() : 0);
        result = 31 * result + (univMajorIntr != null ? univMajorIntr.hashCode() : 0);
        return result;
    }
}
