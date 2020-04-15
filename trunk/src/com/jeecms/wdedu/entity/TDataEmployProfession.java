package com.jeecms.wdedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_data_employ_profession", schema = "wodecareer", catalog = "")
public class TDataEmployProfession {
    private Integer id;
    private Integer year;
    private String majorId;
    private String majorName;
    private String majorSubjeetId;
    private String majorSubjeetName;
    private String majorCatelogId;
    private String majorCatelogName;
    private String majorOnlevelId;
    private String majorOnlevelName;
    private String careerCateId;
    private String careerCateName;
    private String careerPercent;
    private String careerSample;
    private Integer rank;

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
    @Column(name = "career_percent")
    public String getCareerPercent() {
        return careerPercent;
    }

    public void setCareerPercent(String careerPercent) {
        this.careerPercent = careerPercent;
    }

    @Basic
    @Column(name = "career_sample")
    public String getCareerSample() {
        return careerSample;
    }

    public void setCareerSample(String careerSample) {
        this.careerSample = careerSample;
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

        TDataEmployProfession that = (TDataEmployProfession) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
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
        if (careerCateId != null ? !careerCateId.equals(that.careerCateId) : that.careerCateId != null) return false;
        if (careerCateName != null ? !careerCateName.equals(that.careerCateName) : that.careerCateName != null)
            return false;
        if (careerPercent != null ? !careerPercent.equals(that.careerPercent) : that.careerPercent != null)
            return false;
        if (careerSample != null ? !careerSample.equals(that.careerSample) : that.careerSample != null) return false;
        if (rank != null ? !rank.equals(that.rank) : that.rank != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (majorId != null ? majorId.hashCode() : 0);
        result = 31 * result + (majorName != null ? majorName.hashCode() : 0);
        result = 31 * result + (majorSubjeetId != null ? majorSubjeetId.hashCode() : 0);
        result = 31 * result + (majorSubjeetName != null ? majorSubjeetName.hashCode() : 0);
        result = 31 * result + (majorCatelogId != null ? majorCatelogId.hashCode() : 0);
        result = 31 * result + (majorCatelogName != null ? majorCatelogName.hashCode() : 0);
        result = 31 * result + (majorOnlevelId != null ? majorOnlevelId.hashCode() : 0);
        result = 31 * result + (majorOnlevelName != null ? majorOnlevelName.hashCode() : 0);
        result = 31 * result + (careerCateId != null ? careerCateId.hashCode() : 0);
        result = 31 * result + (careerCateName != null ? careerCateName.hashCode() : 0);
        result = 31 * result + (careerPercent != null ? careerPercent.hashCode() : 0);
        result = 31 * result + (careerSample != null ? careerSample.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        return result;
    }
}
