package com.jeecms.wdedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_data_career_profession", schema = "wodecareer", catalog = "")
public class TDataCareerProfession {
    private Integer id;
    private Integer year;
    private String careerId;
    private String careerName;
    private String careerSubjeetId;
    private String careerSubjeetName;
    private String careerCatelogId;
    private String careerCatelogName;
    private String careerCatelogIdOnlevelId;
    private String careerCatelogIdOnlevelName;
    private String careerCateId;
    private String careerCateName;
    private Double careerSalary;
    private Integer careerSample;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "career_id")
    public String getCareerId() {
        return careerId;
    }

    public void setCareerId(String careerId) {
        this.careerId = careerId;
    }

    @Basic
    @Column(name = "career_name")
    public String getCareerName() {
        return careerName;
    }

    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }

    @Basic
    @Column(name = "career_subjeet_id")
    public String getCareerSubjeetId() {
        return careerSubjeetId;
    }

    public void setCareerSubjeetId(String careerSubjeetId) {
        this.careerSubjeetId = careerSubjeetId;
    }

    @Basic
    @Column(name = "career_subjeet_name")
    public String getCareerSubjeetName() {
        return careerSubjeetName;
    }

    public void setCareerSubjeetName(String careerSubjeetName) {
        this.careerSubjeetName = careerSubjeetName;
    }

    @Basic
    @Column(name = "career_catelog_id")
    public String getCareerCatelogId() {
        return careerCatelogId;
    }

    public void setCareerCatelogId(String careerCatelogId) {
        this.careerCatelogId = careerCatelogId;
    }

    @Basic
    @Column(name = "career_catelog_name")
    public String getCareerCatelogName() {
        return careerCatelogName;
    }

    public void setCareerCatelogName(String careerCatelogName) {
        this.careerCatelogName = careerCatelogName;
    }

    @Basic
    @Column(name = "career_catelog_id_onlevel_id")
    public String getCareerCatelogIdOnlevelId() {
        return careerCatelogIdOnlevelId;
    }

    public void setCareerCatelogIdOnlevelId(String careerCatelogIdOnlevelId) {
        this.careerCatelogIdOnlevelId = careerCatelogIdOnlevelId;
    }

    @Basic
    @Column(name = "career_catelog_id_onlevel_name")
    public String getCareerCatelogIdOnlevelName() {
        return careerCatelogIdOnlevelName;
    }

    public void setCareerCatelogIdOnlevelName(String careerCatelogIdOnlevelName) {
        this.careerCatelogIdOnlevelName = careerCatelogIdOnlevelName;
    }

    @Basic
    @Column(name = "career_cate_id")
    public String getCareerCateId() {
        return careerCateId;
    }

    public void setCareerCateId(String careerCateId) {
        this.careerCateId = careerCateId;
    }

    @Basic
    @Column(name = "career_cate_name")
    public String getCareerCateName() {
        return careerCateName;
    }

    public void setCareerCateName(String careerCateName) {
        this.careerCateName = careerCateName;
    }

    @Basic
    @Column(name = "career_salary")
    public Double getCareerSalary() {
        return careerSalary;
    }

    public void setCareerSalary(Double careerSalary) {
        this.careerSalary = careerSalary;
    }

    @Basic
    @Column(name = "career_sample")
    public Integer getCareerSample() {
        return careerSample;
    }

    public void setCareerSample(Integer careerSample) {
        this.careerSample = careerSample;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDataCareerProfession that = (TDataCareerProfession) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (careerId != null ? !careerId.equals(that.careerId) : that.careerId != null) return false;
        if (careerName != null ? !careerName.equals(that.careerName) : that.careerName != null) return false;
        if (careerSubjeetId != null ? !careerSubjeetId.equals(that.careerSubjeetId) : that.careerSubjeetId != null)
            return false;
        if (careerSubjeetName != null ? !careerSubjeetName.equals(that.careerSubjeetName) : that.careerSubjeetName != null)
            return false;
        if (careerCatelogId != null ? !careerCatelogId.equals(that.careerCatelogId) : that.careerCatelogId != null)
            return false;
        if (careerCatelogName != null ? !careerCatelogName.equals(that.careerCatelogName) : that.careerCatelogName != null)
            return false;
        if (careerCatelogIdOnlevelId != null ? !careerCatelogIdOnlevelId.equals(that.careerCatelogIdOnlevelId) : that.careerCatelogIdOnlevelId != null)
            return false;
        if (careerCatelogIdOnlevelName != null ? !careerCatelogIdOnlevelName.equals(that.careerCatelogIdOnlevelName) : that.careerCatelogIdOnlevelName != null)
            return false;
        if (careerCateId != null ? !careerCateId.equals(that.careerCateId) : that.careerCateId != null) return false;
        if (careerCateName != null ? !careerCateName.equals(that.careerCateName) : that.careerCateName != null)
            return false;
        if (careerSalary != null ? !careerSalary.equals(that.careerSalary) : that.careerSalary != null) return false;
        if (careerSample != null ? !careerSample.equals(that.careerSample) : that.careerSample != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (careerId != null ? careerId.hashCode() : 0);
        result = 31 * result + (careerName != null ? careerName.hashCode() : 0);
        result = 31 * result + (careerSubjeetId != null ? careerSubjeetId.hashCode() : 0);
        result = 31 * result + (careerSubjeetName != null ? careerSubjeetName.hashCode() : 0);
        result = 31 * result + (careerCatelogId != null ? careerCatelogId.hashCode() : 0);
        result = 31 * result + (careerCatelogName != null ? careerCatelogName.hashCode() : 0);
        result = 31 * result + (careerCatelogIdOnlevelId != null ? careerCatelogIdOnlevelId.hashCode() : 0);
        result = 31 * result + (careerCatelogIdOnlevelName != null ? careerCatelogIdOnlevelName.hashCode() : 0);
        result = 31 * result + (careerCateId != null ? careerCateId.hashCode() : 0);
        result = 31 * result + (careerCateName != null ? careerCateName.hashCode() : 0);
        result = 31 * result + (careerSalary != null ? careerSalary.hashCode() : 0);
        result = 31 * result + (careerSample != null ? careerSample.hashCode() : 0);
        return result;
    }
}
