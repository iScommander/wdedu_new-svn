package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @date 2018/10/25
 */
@Entity
@Table(name = "t_data_major_rank", schema = "wodecareer", catalog = "")
public class TDataMajorRank {
    private int id;
    private Integer univId;
    private String univName;
    private String majorId;
    private String majorName;
    private Integer rank;
    private String grade;
    private Integer rank2008;
    private String rankFloat;
    private String rankFloatState;
    private String rankFloatSize;
    private String majorfirstname;
    private String majorsecondname;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "univ_name")
    public String getUnivName() {
        return univName;
    }

    public void setUnivName(String univName) {
        this.univName = univName;
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
    @Column(name = "rank")
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Basic
    @Column(name = "grade")
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "rank2008")
    public Integer getRank2008() {
        return rank2008;
    }

    public void setRank2008(Integer rank2008) {
        this.rank2008 = rank2008;
    }

    @Basic
    @Column(name = "rankFloat")
    public String getRankFloat() {
        return rankFloat;
    }

    public void setRankFloat(String rankFloat) {
        this.rankFloat = rankFloat;
    }

    @Basic
    @Column(name = "rankFloatState")
    public String getRankFloatState() {
        return rankFloatState;
    }

    public void setRankFloatState(String rankFloatState) {
        this.rankFloatState = rankFloatState;
    }

    @Basic
    @Column(name = "rankFloatSize")
    public String getRankFloatSize() {
        return rankFloatSize;
    }

    public void setRankFloatSize(String rankFloatSize) {
        this.rankFloatSize = rankFloatSize;
    }

    @Basic
    @Column(name = "majorfirstname")
    public String getMajorfirstname() {
        return majorfirstname;
    }

    public void setMajorfirstname(String majorfirstname) {
        this.majorfirstname = majorfirstname;
    }

    @Basic
    @Column(name = "majorsecondname")
    public String getMajorsecondname() {
        return majorsecondname;
    }

    public void setMajorsecondname(String majorsecondname) {
        this.majorsecondname = majorsecondname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TDataMajorRank that = (TDataMajorRank) o;
        return id == that.id &&
                Objects.equals(univId, that.univId) &&
                Objects.equals(univName, that.univName) &&
                Objects.equals(majorId, that.majorId) &&
                Objects.equals(majorName, that.majorName) &&
                Objects.equals(rank, that.rank) &&
                Objects.equals(grade, that.grade) &&
                Objects.equals(rank2008, that.rank2008) &&
                Objects.equals(rankFloat, that.rankFloat) &&
                Objects.equals(rankFloatState, that.rankFloatState) &&
                Objects.equals(rankFloatSize, that.rankFloatSize) &&
                Objects.equals(majorfirstname, that.majorfirstname) &&
                Objects.equals(majorsecondname, that.majorsecondname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, univId, univName, majorId, majorName, rank, grade, rank2008, rankFloat, rankFloatState, rankFloatSize, majorfirstname, majorsecondname);
    }
}
