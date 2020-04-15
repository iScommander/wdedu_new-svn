package com.jeecms.wdedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_data_salary_university", schema = "wodecareer", catalog = "")
public class TDataSalaryUniversity {
    private int id;
    private Integer universityBaseId;
    private String univName;
    private String univBelong;
    private String univLevel;
    private String offOrVol;
    private int year;
    private Double oneYearSalary;
    private Double threeYearSalary;
    private Double fiveYearSalary;
    private Double tenYearSalary;
    private String reportUrl;
    private Integer rank;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    @Column(name = "univ_belong")
    public String getUnivBelong() {
        return univBelong;
    }

    public void setUnivBelong(String univBelong) {
        this.univBelong = univBelong;
    }

    @Basic
    @Column(name = "univ_level")
    public String getUnivLevel() {
        return univLevel;
    }

    public void setUnivLevel(String univLevel) {
        this.univLevel = univLevel;
    }

    @Basic
    @Column(name = "off_OR_vol")
    public String getOffOrVol() {
        return offOrVol;
    }

    public void setOffOrVol(String offOrVol) {
        this.offOrVol = offOrVol;
    }

    @Basic
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
    @Column(name = "threeYearSalary")
    public Double getThreeYearSalary() {
        return threeYearSalary;
    }

    public void setThreeYearSalary(Double threeYearSalary) {
        this.threeYearSalary = threeYearSalary;
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
    @Column(name = "tenYearSalary")
    public Double getTenYearSalary() {
        return tenYearSalary;
    }

    public void setTenYearSalary(Double tenYearSalary) {
        this.tenYearSalary = tenYearSalary;
    }

    @Basic
    @Column(name = "report_url")
    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    @Basic
    @Column(name = "rank")
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDataSalaryUniversity that = (TDataSalaryUniversity) o;

        if (id != that.id) return false;
        if (year != that.year) return false;
        if (universityBaseId != null ? !universityBaseId.equals(that.universityBaseId) : that.universityBaseId != null)
            return false;
        if (univName != null ? !univName.equals(that.univName) : that.univName != null) return false;
        if (univBelong != null ? !univBelong.equals(that.univBelong) : that.univBelong != null) return false;
        if (univLevel != null ? !univLevel.equals(that.univLevel) : that.univLevel != null) return false;
        if (offOrVol != null ? !offOrVol.equals(that.offOrVol) : that.offOrVol != null) return false;
        if (oneYearSalary != null ? !oneYearSalary.equals(that.oneYearSalary) : that.oneYearSalary != null)
            return false;
        if (threeYearSalary != null ? !threeYearSalary.equals(that.threeYearSalary) : that.threeYearSalary != null)
            return false;
        if (fiveYearSalary != null ? !fiveYearSalary.equals(that.fiveYearSalary) : that.fiveYearSalary != null)
            return false;
        if (tenYearSalary != null ? !tenYearSalary.equals(that.tenYearSalary) : that.tenYearSalary != null)
            return false;
        if (reportUrl != null ? !reportUrl.equals(that.reportUrl) : that.reportUrl != null) return false;
        if (rank != null ? !rank.equals(that.rank) : that.rank != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (universityBaseId != null ? universityBaseId.hashCode() : 0);
        result = 31 * result + (univName != null ? univName.hashCode() : 0);
        result = 31 * result + (univBelong != null ? univBelong.hashCode() : 0);
        result = 31 * result + (univLevel != null ? univLevel.hashCode() : 0);
        result = 31 * result + (offOrVol != null ? offOrVol.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (oneYearSalary != null ? oneYearSalary.hashCode() : 0);
        result = 31 * result + (threeYearSalary != null ? threeYearSalary.hashCode() : 0);
        result = 31 * result + (fiveYearSalary != null ? fiveYearSalary.hashCode() : 0);
        result = 31 * result + (tenYearSalary != null ? tenYearSalary.hashCode() : 0);
        result = 31 * result + (reportUrl != null ? reportUrl.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        return result;
    }
}
