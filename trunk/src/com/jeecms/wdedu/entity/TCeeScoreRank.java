package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_cee_score_rank", schema = "wodecareer", catalog = "")
@IdClass(TCeeScoreRankPK.class)
public class TCeeScoreRank {
    private int id;
    private int year;
    private int provinceId;
    private int majorTypeId;
    private int score;
    private int rank;
    private Integer highRank;
    private Integer lowRank;
    private Integer num;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Id
    @Column(name = "province_id")
    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    @Id
    @Column(name = "major_type_id")
    public int getMajorTypeId() {
        return majorTypeId;
    }

    public void setMajorTypeId(int majorTypeId) {
        this.majorTypeId = majorTypeId;
    }

    @Id
    @Column(name = "score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Basic
    @Column(name = "rank")
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Basic
    @Column(name = "high_rank")
    public Integer getHighRank() {
        return highRank;
    }

    public void setHighRank(Integer highRank) {
        this.highRank = highRank;
    }

    @Basic
    @Column(name = "low_rank")
    public Integer getLowRank() {
        return lowRank;
    }

    public void setLowRank(Integer lowRank) {
        this.lowRank = lowRank;
    }

    @Basic
    @Column(name = "num")
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TCeeScoreRank that = (TCeeScoreRank) o;
        return id == that.id &&
                year == that.year &&
                provinceId == that.provinceId &&
                majorTypeId == that.majorTypeId &&
                score == that.score &&
                rank == that.rank &&
                Objects.equals(highRank, that.highRank) &&
                Objects.equals(lowRank, that.lowRank) &&
                Objects.equals(num, that.num);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, year, provinceId, majorTypeId, score, rank, highRank, lowRank, num);
    }
}
