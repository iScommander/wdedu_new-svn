package com.jeecms.wdedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_data_employ_index", schema = "wodecareer", catalog = "")
public class TDataEmployIndex {
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
    private String itemIndex;

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
    @Column(name = "item_index")
    public String getItemIndex() {
        return itemIndex;
    }

    public void setItemIndex(String itemIndex) {
        this.itemIndex = itemIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDataEmployIndex that = (TDataEmployIndex) o;

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
        if (itemIndex != null ? !itemIndex.equals(that.itemIndex) : that.itemIndex != null) return false;

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
        result = 31 * result + (itemIndex != null ? itemIndex.hashCode() : 0);
        return result;
    }
}
