package com.jeecms.wdedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_data_salary_major", schema = "wodecareer", catalog = "")
public class TDataSalaryMajor {
    private int id;
    private int year;
    private String majorId;
    private String majorName;
    private String majorSubjeetId;
    private String majorSubjeetName;
    private String majorCatelogId;
    private String majorCatelogName;
    private String majorOnlevelId;
    private String majorOnlevelName;
    private Double aveSalary;
    private Double zeroYearSalry;
    private Double onethreeYearSalary;
    private Double threefiveYearSalary;
    private Double fivetenYearSalary;
    private Double tenafterYearSalary;
    private String salaryOnelevelRank;
    private String salaryOnelevelRankRemark;
    private String salaryRegion;
    private String salaryRegionRemark;
    private String salaryCareer;
    private String salaryCareerRemark;
    private String majorJobDirect;

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
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "major_id")
    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
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
    @Column(name = "major_subjeet_id")
    public String getMajorSubjeetId() {
        return majorSubjeetId;
    }

    public void setMajorSubjeetId(String majorSubjeetId) {
        this.majorSubjeetId = majorSubjeetId;
    }

    @Basic
    @Column(name = "major_subjeet_name")
    public String getMajorSubjeetName() {
        return majorSubjeetName;
    }

    public void setMajorSubjeetName(String majorSubjeetName) {
        this.majorSubjeetName = majorSubjeetName;
    }

    @Basic
    @Column(name = "major_catelog_id")
    public String getMajorCatelogId() {
        return majorCatelogId;
    }

    public void setMajorCatelogId(String majorCatelogId) {
        this.majorCatelogId = majorCatelogId;
    }

    @Basic
    @Column(name = "major_catelog_name")
    public String getMajorCatelogName() {
        return majorCatelogName;
    }

    public void setMajorCatelogName(String majorCatelogName) {
        this.majorCatelogName = majorCatelogName;
    }

    @Basic
    @Column(name = "major_onlevel_id")
    public String getMajorOnlevelId() {
        return majorOnlevelId;
    }

    public void setMajorOnlevelId(String majorOnlevelId) {
        this.majorOnlevelId = majorOnlevelId;
    }

    @Basic
    @Column(name = "major_onlevel_name")
    public String getMajorOnlevelName() {
        return majorOnlevelName;
    }

    public void setMajorOnlevelName(String majorOnlevelName) {
        this.majorOnlevelName = majorOnlevelName;
    }

    @Basic
    @Column(name = "aveSalary")
    public Double getAveSalary() {
        return aveSalary;
    }

    public void setAveSalary(Double aveSalary) {
        this.aveSalary = aveSalary;
    }

    @Basic
    @Column(name = "zeroYearSalry")
    public Double getZeroYearSalry() {
        return zeroYearSalry;
    }

    public void setZeroYearSalry(Double zeroYearSalry) {
        this.zeroYearSalry = zeroYearSalry;
    }

    @Basic
    @Column(name = "onethreeYearSalary")
    public Double getOnethreeYearSalary() {
        return onethreeYearSalary;
    }

    public void setOnethreeYearSalary(Double onethreeYearSalary) {
        this.onethreeYearSalary = onethreeYearSalary;
    }

    @Basic
    @Column(name = "threefiveYearSalary")
    public Double getThreefiveYearSalary() {
        return threefiveYearSalary;
    }

    public void setThreefiveYearSalary(Double threefiveYearSalary) {
        this.threefiveYearSalary = threefiveYearSalary;
    }

    @Basic
    @Column(name = "fivetenYearSalary")
    public Double getFivetenYearSalary() {
        return fivetenYearSalary;
    }

    public void setFivetenYearSalary(Double fivetenYearSalary) {
        this.fivetenYearSalary = fivetenYearSalary;
    }

    @Basic
    @Column(name = "tenafterYearSalary")
    public Double getTenafterYearSalary() {
        return tenafterYearSalary;
    }

    public void setTenafterYearSalary(Double tenafterYearSalary) {
        this.tenafterYearSalary = tenafterYearSalary;
    }

    @Basic
    @Column(name = "Salary_onelevel_rank")
    public String getSalaryOnelevelRank() {
        return salaryOnelevelRank;
    }

    public void setSalaryOnelevelRank(String salaryOnelevelRank) {
        this.salaryOnelevelRank = salaryOnelevelRank;
    }

    @Basic
    @Column(name = "Salary_onelevel_rank_remark")
    public String getSalaryOnelevelRankRemark() {
        return salaryOnelevelRankRemark;
    }

    public void setSalaryOnelevelRankRemark(String salaryOnelevelRankRemark) {
        this.salaryOnelevelRankRemark = salaryOnelevelRankRemark;
    }

    @Basic
    @Column(name = "Salary_region")
    public String getSalaryRegion() {
        return salaryRegion;
    }

    public void setSalaryRegion(String salaryRegion) {
        this.salaryRegion = salaryRegion;
    }

    @Basic
    @Column(name = "Salary_region_remark")
    public String getSalaryRegionRemark() {
        return salaryRegionRemark;
    }

    public void setSalaryRegionRemark(String salaryRegionRemark) {
        this.salaryRegionRemark = salaryRegionRemark;
    }

    @Basic
    @Column(name = "Salary_career")
    public String getSalaryCareer() {
        return salaryCareer;
    }

    public void setSalaryCareer(String salaryCareer) {
        this.salaryCareer = salaryCareer;
    }

    @Basic
    @Column(name = "Salary_career_remark")
    public String getSalaryCareerRemark() {
        return salaryCareerRemark;
    }

    public void setSalaryCareerRemark(String salaryCareerRemark) {
        this.salaryCareerRemark = salaryCareerRemark;
    }

    @Basic
    @Column(name = "major_job_direct")
    public String getMajorJobDirect() {
        return majorJobDirect;
    }

    public void setMajorJobDirect(String majorJobDirect) {
        this.majorJobDirect = majorJobDirect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDataSalaryMajor that = (TDataSalaryMajor) o;

        if (id != that.id) return false;
        if (year != that.year) return false;
        if (majorId != null ? !majorId.equals(that.majorId) : that.majorId != null) return false;
        if (majorName != null ? !majorName.equals(that.majorName) : that.majorName != null) return false;
        if (majorSubjeetId != null ? !majorSubjeetId.equals(that.majorSubjeetId) : that.majorSubjeetId != null)
            return false;
        if (majorSubjeetName != null ? !majorSubjeetName.equals(that.majorSubjeetName) : that.majorSubjeetName != null)
            return false;
        if (majorCatelogId != null ? !majorCatelogId.equals(that.majorCatelogId) : that.majorCatelogId != null)
            return false;
        if (majorCatelogName != null ? !majorCatelogName.equals(that.majorCatelogName) : that.majorCatelogName != null)
            return false;
        if (majorOnlevelId != null ? !majorOnlevelId.equals(that.majorOnlevelId) : that.majorOnlevelId != null)
            return false;
        if (majorOnlevelName != null ? !majorOnlevelName.equals(that.majorOnlevelName) : that.majorOnlevelName != null)
            return false;
        if (aveSalary != null ? !aveSalary.equals(that.aveSalary) : that.aveSalary != null) return false;
        if (zeroYearSalry != null ? !zeroYearSalry.equals(that.zeroYearSalry) : that.zeroYearSalry != null)
            return false;
        if (onethreeYearSalary != null ? !onethreeYearSalary.equals(that.onethreeYearSalary) : that.onethreeYearSalary != null)
            return false;
        if (threefiveYearSalary != null ? !threefiveYearSalary.equals(that.threefiveYearSalary) : that.threefiveYearSalary != null)
            return false;
        if (fivetenYearSalary != null ? !fivetenYearSalary.equals(that.fivetenYearSalary) : that.fivetenYearSalary != null)
            return false;
        if (tenafterYearSalary != null ? !tenafterYearSalary.equals(that.tenafterYearSalary) : that.tenafterYearSalary != null)
            return false;
        if (salaryOnelevelRank != null ? !salaryOnelevelRank.equals(that.salaryOnelevelRank) : that.salaryOnelevelRank != null)
            return false;
        if (salaryOnelevelRankRemark != null ? !salaryOnelevelRankRemark.equals(that.salaryOnelevelRankRemark) : that.salaryOnelevelRankRemark != null)
            return false;
        if (salaryRegion != null ? !salaryRegion.equals(that.salaryRegion) : that.salaryRegion != null) return false;
        if (salaryRegionRemark != null ? !salaryRegionRemark.equals(that.salaryRegionRemark) : that.salaryRegionRemark != null)
            return false;
        if (salaryCareer != null ? !salaryCareer.equals(that.salaryCareer) : that.salaryCareer != null) return false;
        if (salaryCareerRemark != null ? !salaryCareerRemark.equals(that.salaryCareerRemark) : that.salaryCareerRemark != null)
            return false;
        if (majorJobDirect != null ? !majorJobDirect.equals(that.majorJobDirect) : that.majorJobDirect != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + year;
        result = 31 * result + (majorId != null ? majorId.hashCode() : 0);
        result = 31 * result + (majorName != null ? majorName.hashCode() : 0);
        result = 31 * result + (majorSubjeetId != null ? majorSubjeetId.hashCode() : 0);
        result = 31 * result + (majorSubjeetName != null ? majorSubjeetName.hashCode() : 0);
        result = 31 * result + (majorCatelogId != null ? majorCatelogId.hashCode() : 0);
        result = 31 * result + (majorCatelogName != null ? majorCatelogName.hashCode() : 0);
        result = 31 * result + (majorOnlevelId != null ? majorOnlevelId.hashCode() : 0);
        result = 31 * result + (majorOnlevelName != null ? majorOnlevelName.hashCode() : 0);
        result = 31 * result + (aveSalary != null ? aveSalary.hashCode() : 0);
        result = 31 * result + (zeroYearSalry != null ? zeroYearSalry.hashCode() : 0);
        result = 31 * result + (onethreeYearSalary != null ? onethreeYearSalary.hashCode() : 0);
        result = 31 * result + (threefiveYearSalary != null ? threefiveYearSalary.hashCode() : 0);
        result = 31 * result + (fivetenYearSalary != null ? fivetenYearSalary.hashCode() : 0);
        result = 31 * result + (tenafterYearSalary != null ? tenafterYearSalary.hashCode() : 0);
        result = 31 * result + (salaryOnelevelRank != null ? salaryOnelevelRank.hashCode() : 0);
        result = 31 * result + (salaryOnelevelRankRemark != null ? salaryOnelevelRankRemark.hashCode() : 0);
        result = 31 * result + (salaryRegion != null ? salaryRegion.hashCode() : 0);
        result = 31 * result + (salaryRegionRemark != null ? salaryRegionRemark.hashCode() : 0);
        result = 31 * result + (salaryCareer != null ? salaryCareer.hashCode() : 0);
        result = 31 * result + (salaryCareerRemark != null ? salaryCareerRemark.hashCode() : 0);
        result = 31 * result + (majorJobDirect != null ? majorJobDirect.hashCode() : 0);
        return result;
    }
}
