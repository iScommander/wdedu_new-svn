package com.jeecms.wdedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_data_salary_career", schema = "wodecareer", catalog = "")
public class TDataSalaryCareer {
    private int id;
    private Integer year;
    private String careerId;
    private String careerName;
    private String careerSubjeetId;
    private String careerSubjeetName;
    private String careerCatelogId;
    private String careerCatelogName;
    private String careerOnlevelId;
    private String careerOnlevelName;
    private Double oneYearSalary;
    private Double oneYearSalarySample;
    private Double threeYearSalary;
    private Double threeYearSalarySample;
    private Double fiveYearSalary;
    private Double fiveYearSalarySample;
    private Double tenYearSalary;
    private Double tenYearSalarySample;
    private Double moretenYearSalary;
    private Double moretenYearSalarySample;
    private Integer salaryOnelevelRank;
    private Integer salaryCatelogRank;
    private Double averageSalary;
    private String salaryInfo;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    @Column(name = "oneYearSalary")
    public Double getOneYearSalary() {
        return oneYearSalary;
    }

    public void setOneYearSalary(Double oneYearSalary) {
        this.oneYearSalary = oneYearSalary;
    }

    @Basic
    @Column(name = "oneYearSalary_sample")
    public Double getOneYearSalarySample() {
        return oneYearSalarySample;
    }

    public void setOneYearSalarySample(Double oneYearSalarySample) {
        this.oneYearSalarySample = oneYearSalarySample;
    }

    @Basic
    @Column(name = "threeYearSalary")
    public Double getThreeYearSalary() {
        return threeYearSalary;
    }

    public void setThreeYearSalary(Double threeYearSalary) {
        this.threeYearSalary = threeYearSalary;
    }

    @Basic
    @Column(name = "threeYearSalary_sample")
    public Double getThreeYearSalarySample() {
        return threeYearSalarySample;
    }

    public void setThreeYearSalarySample(Double threeYearSalarySample) {
        this.threeYearSalarySample = threeYearSalarySample;
    }

    @Basic
    @Column(name = "fiveYearSalary")
    public Double getFiveYearSalary() {
        return fiveYearSalary;
    }

    public void setFiveYearSalary(Double fiveYearSalary) {
        this.fiveYearSalary = fiveYearSalary;
    }

    @Basic
    @Column(name = "fiveYearSalary_sample")
    public Double getFiveYearSalarySample() {
        return fiveYearSalarySample;
    }

    public void setFiveYearSalarySample(Double fiveYearSalarySample) {
        this.fiveYearSalarySample = fiveYearSalarySample;
    }

    @Basic
    @Column(name = "tenYearSalary")
    public Double getTenYearSalary() {
        return tenYearSalary;
    }

    public void setTenYearSalary(Double tenYearSalary) {
        this.tenYearSalary = tenYearSalary;
    }

    @Basic
    @Column(name = "tenYearSalary_sample")
    public Double getTenYearSalarySample() {
        return tenYearSalarySample;
    }

    public void setTenYearSalarySample(Double tenYearSalarySample) {
        this.tenYearSalarySample = tenYearSalarySample;
    }

    @Basic
    @Column(name = "moretenYearSalary")
    public Double getMoretenYearSalary() {
        return moretenYearSalary;
    }

    public void setMoretenYearSalary(Double moretenYearSalary) {
        this.moretenYearSalary = moretenYearSalary;
    }

    @Basic
    @Column(name = "moretenYearSalary_sample")
    public Double getMoretenYearSalarySample() {
        return moretenYearSalarySample;
    }

    public void setMoretenYearSalarySample(Double moretenYearSalarySample) {
        this.moretenYearSalarySample = moretenYearSalarySample;
    }

    @Basic
    @Column(name = "Salary_onelevel_rank")
    public Integer getSalaryOnelevelRank() {
        return salaryOnelevelRank;
    }

    public void setSalaryOnelevelRank(Integer salaryOnelevelRank) {
        this.salaryOnelevelRank = salaryOnelevelRank;
    }

    @Basic
    @Column(name = "Salary_catelog_rank")
    public Integer getSalaryCatelogRank() {
        return salaryCatelogRank;
    }

    public void setSalaryCatelogRank(Integer salaryCatelogRank) {
        this.salaryCatelogRank = salaryCatelogRank;
    }

    @Basic
    @Column(name = "averageSalary")
    public Double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(Double averageSalary) {
        this.averageSalary = averageSalary;
    }

    @Basic
    @Column(name = "SalaryInfo")
    public String getSalaryInfo() {
        return salaryInfo;
    }

    public void setSalaryInfo(String salaryInfo) {
        this.salaryInfo = salaryInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDataSalaryCareer that = (TDataSalaryCareer) o;

        if (id != that.id) return false;
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
        if (oneYearSalary != null ? !oneYearSalary.equals(that.oneYearSalary) : that.oneYearSalary != null)
            return false;
        if (oneYearSalarySample != null ? !oneYearSalarySample.equals(that.oneYearSalarySample) : that.oneYearSalarySample != null)
            return false;
        if (threeYearSalary != null ? !threeYearSalary.equals(that.threeYearSalary) : that.threeYearSalary != null)
            return false;
        if (threeYearSalarySample != null ? !threeYearSalarySample.equals(that.threeYearSalarySample) : that.threeYearSalarySample != null)
            return false;
        if (fiveYearSalary != null ? !fiveYearSalary.equals(that.fiveYearSalary) : that.fiveYearSalary != null)
            return false;
        if (fiveYearSalarySample != null ? !fiveYearSalarySample.equals(that.fiveYearSalarySample) : that.fiveYearSalarySample != null)
            return false;
        if (tenYearSalary != null ? !tenYearSalary.equals(that.tenYearSalary) : that.tenYearSalary != null)
            return false;
        if (tenYearSalarySample != null ? !tenYearSalarySample.equals(that.tenYearSalarySample) : that.tenYearSalarySample != null)
            return false;
        if (moretenYearSalary != null ? !moretenYearSalary.equals(that.moretenYearSalary) : that.moretenYearSalary != null)
            return false;
        if (moretenYearSalarySample != null ? !moretenYearSalarySample.equals(that.moretenYearSalarySample) : that.moretenYearSalarySample != null)
            return false;
        if (salaryOnelevelRank != null ? !salaryOnelevelRank.equals(that.salaryOnelevelRank) : that.salaryOnelevelRank != null)
            return false;
        if (salaryCatelogRank != null ? !salaryCatelogRank.equals(that.salaryCatelogRank) : that.salaryCatelogRank != null)
            return false;
        if (averageSalary != null ? !averageSalary.equals(that.averageSalary) : that.averageSalary != null)
            return false;
        if (salaryInfo != null ? !salaryInfo.equals(that.salaryInfo) : that.salaryInfo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (careerId != null ? careerId.hashCode() : 0);
        result = 31 * result + (careerName != null ? careerName.hashCode() : 0);
        result = 31 * result + (careerSubjeetId != null ? careerSubjeetId.hashCode() : 0);
        result = 31 * result + (careerSubjeetName != null ? careerSubjeetName.hashCode() : 0);
        result = 31 * result + (careerCatelogId != null ? careerCatelogId.hashCode() : 0);
        result = 31 * result + (careerCatelogName != null ? careerCatelogName.hashCode() : 0);
        result = 31 * result + (careerOnlevelId != null ? careerOnlevelId.hashCode() : 0);
        result = 31 * result + (careerOnlevelName != null ? careerOnlevelName.hashCode() : 0);
        result = 31 * result + (oneYearSalary != null ? oneYearSalary.hashCode() : 0);
        result = 31 * result + (oneYearSalarySample != null ? oneYearSalarySample.hashCode() : 0);
        result = 31 * result + (threeYearSalary != null ? threeYearSalary.hashCode() : 0);
        result = 31 * result + (threeYearSalarySample != null ? threeYearSalarySample.hashCode() : 0);
        result = 31 * result + (fiveYearSalary != null ? fiveYearSalary.hashCode() : 0);
        result = 31 * result + (fiveYearSalarySample != null ? fiveYearSalarySample.hashCode() : 0);
        result = 31 * result + (tenYearSalary != null ? tenYearSalary.hashCode() : 0);
        result = 31 * result + (tenYearSalarySample != null ? tenYearSalarySample.hashCode() : 0);
        result = 31 * result + (moretenYearSalary != null ? moretenYearSalary.hashCode() : 0);
        result = 31 * result + (moretenYearSalarySample != null ? moretenYearSalarySample.hashCode() : 0);
        result = 31 * result + (salaryOnelevelRank != null ? salaryOnelevelRank.hashCode() : 0);
        result = 31 * result + (salaryCatelogRank != null ? salaryCatelogRank.hashCode() : 0);
        result = 31 * result + (averageSalary != null ? averageSalary.hashCode() : 0);
        result = 31 * result + (salaryInfo != null ? salaryInfo.hashCode() : 0);
        return result;
    }
}
