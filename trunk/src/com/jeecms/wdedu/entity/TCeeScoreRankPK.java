package com.jeecms.wdedu.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
public class TCeeScoreRankPK implements Serializable {
    private int id;
    private int year;
    private int provinceId;
    private int majorTypeId;
    private int score;

    @Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "year")
    @Id
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Column(name = "province_id")
    @Id
    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    @Column(name = "major_type_id")
    @Id
    public int getMajorTypeId() {
        return majorTypeId;
    }

    public void setMajorTypeId(int majorTypeId) {
        this.majorTypeId = majorTypeId;
    }

    @Column(name = "score")
    @Id
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TCeeScoreRankPK that = (TCeeScoreRankPK) o;
        return id == that.id &&
                year == that.year &&
                provinceId == that.provinceId &&
                majorTypeId == that.majorTypeId &&
                score == that.score;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, year, provinceId, majorTypeId, score);
    }
}
