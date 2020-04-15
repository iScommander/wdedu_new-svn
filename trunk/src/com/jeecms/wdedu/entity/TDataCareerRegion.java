package com.jeecms.wdedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_data_career_region", schema = "wodecareer", catalog = "")
public class TDataCareerRegion {
    private Integer id;
    private Integer year;
    private String careerId;
    private String careerName;
    private String careerSubjeetId;
    private String careerSubjeetName;
    private String careerCatelogId;
    private String careerCatelogName;
    private String careerOnlevelId;
    private String careerOnlevelName;
    private String regionId;
    private String regionName;
    private Double regionSalary;
    private Integer regionSample;

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
    @Column(name = "career_onlevel_id")
    public String getCareerOnlevelId() {
        return careerOnlevelId;
    }

    public void setCareerOnlevelId(String careerOnlevelId) {
        this.careerOnlevelId = careerOnlevelId;
    }

    @Basic
    @Column(name = "career_onlevel_name")
    public String getCareerOnlevelName() {
        return careerOnlevelName;
    }

    public void setCareerOnlevelName(String careerOnlevelName) {
        this.careerOnlevelName = careerOnlevelName;
    }

    @Basic
    @Column(name = "region_id")
    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    @Basic
    @Column(name = "region_name")
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Basic
    @Column(name = "region_salary")
    public Double getRegionSalary() {
        return regionSalary;
    }

    public void setRegionSalary(Double regionSalary) {
        this.regionSalary = regionSalary;
    }

    @Basic
    @Column(name = "region_sample")
    public Integer getRegionSample() {
        return regionSample;
    }

    public void setRegionSample(Integer regionSample) {
        this.regionSample = regionSample;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDataCareerRegion that = (TDataCareerRegion) o;

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
        if (careerOnlevelId != null ? !careerOnlevelId.equals(that.careerOnlevelId) : that.careerOnlevelId != null)
            return false;
        if (careerOnlevelName != null ? !careerOnlevelName.equals(that.careerOnlevelName) : that.careerOnlevelName != null)
            return false;
        if (regionId != null ? !regionId.equals(that.regionId) : that.regionId != null) return false;
        if (regionName != null ? !regionName.equals(that.regionName) : that.regionName != null) return false;
        if (regionSalary != null ? !regionSalary.equals(that.regionSalary) : that.regionSalary != null) return false;
        if (regionSample != null ? !regionSample.equals(that.regionSample) : that.regionSample != null) return false;

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
        result = 31 * result + (careerOnlevelId != null ? careerOnlevelId.hashCode() : 0);
        result = 31 * result + (careerOnlevelName != null ? careerOnlevelName.hashCode() : 0);
        result = 31 * result + (regionId != null ? regionId.hashCode() : 0);
        result = 31 * result + (regionName != null ? regionName.hashCode() : 0);
        result = 31 * result + (regionSalary != null ? regionSalary.hashCode() : 0);
        result = 31 * result + (regionSample != null ? regionSample.hashCode() : 0);
        return result;
    }
}
