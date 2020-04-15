package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_location_univ_condition", schema = "wodecareer", catalog = "")
public class TNLocationUnivCondition {
    private int id;
    private int year;
    private int majorId;
    private int univType;
    private String applyType;
    private String applyCondition;
    private Integer contestType;
    private Integer contestName;
    private Integer contestCategory;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "YEAR")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "MAJOR_ID")
    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    @Basic
    @Column(name = "UNIV_TYPE")
    public int getUnivType() {
        return univType;
    }

    public void setUnivType(int univType) {
        this.univType = univType;
    }

    @Basic
    @Column(name = "APPLY_TYPE")
    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    @Basic
    @Column(name = "APPLY_CONDITION")
    public String getApplyCondition() {
        return applyCondition;
    }

    public void setApplyCondition(String applyCondition) {
        this.applyCondition = applyCondition;
    }

    @Basic
    @Column(name = "CONTEST_TYPE")
    public Integer getContestType() {
        return contestType;
    }

    public void setContestType(Integer contestType) {
        this.contestType = contestType;
    }

    @Basic
    @Column(name = "CONTEST_NAME")
    public Integer getContestName() {
        return contestName;
    }

    public void setContestName(Integer contestName) {
        this.contestName = contestName;
    }

    @Basic
    @Column(name = "CONTEST_CATEGORY")
    public Integer getContestCategory() {
        return contestCategory;
    }

    public void setContestCategory(Integer contestCategory) {
        this.contestCategory = contestCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TNLocationUnivCondition that = (TNLocationUnivCondition) o;
        return id == that.id &&
                year == that.year &&
                majorId == that.majorId &&
                univType == that.univType &&
                Objects.equals(applyType, that.applyType) &&
                Objects.equals(applyCondition, that.applyCondition) &&
                Objects.equals(contestType, that.contestType) &&
                Objects.equals(contestName, that.contestName) &&
                Objects.equals(contestCategory, that.contestCategory);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, year, majorId, univType, applyType, applyCondition, contestType, contestName, contestCategory);
    }
}
