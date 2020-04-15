package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_data_university_rank", schema = "wodecareer", catalog = "")
public class TDataUniversityRank {
    private int id;
    private Integer year;
    private Integer baseId;
    private String univName;
    private Integer rank;
    private Integer provinceId;
    private String provinceName;
    private Integer rankArea;
    private String cateName;
    private Integer rankCate;
    private String belongName;
    private Double scoreTotal;
    private String typeCreate;
    private String typeSchool;
    private String grade;
    private String level;
    private String rcpy;
    private String kxyj;
    private String shkxyj;
    private String rankType;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "base_id")
    public Integer getBaseId() {
        return baseId;
    }

    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
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
    @Column(name = "rank")
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Basic
    @Column(name = "province_id")
    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @Basic
    @Column(name = "province_name")
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Basic
    @Column(name = "rank_area")
    public Integer getRankArea() {
        return rankArea;
    }

    public void setRankArea(Integer rankArea) {
        this.rankArea = rankArea;
    }

    @Basic
    @Column(name = "cate_name")
    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    @Basic
    @Column(name = "rank_cate")
    public Integer getRankCate() {
        return rankCate;
    }

    public void setRankCate(Integer rankCate) {
        this.rankCate = rankCate;
    }

    @Basic
    @Column(name = "belong_name")
    public String getBelongName() {
        return belongName;
    }

    public void setBelongName(String belongName) {
        this.belongName = belongName;
    }

    @Basic
    @Column(name = "score_total")
    public Double getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Double scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    @Basic
    @Column(name = "type_create")
    public String getTypeCreate() {
        return typeCreate;
    }

    public void setTypeCreate(String typeCreate) {
        this.typeCreate = typeCreate;
    }

    @Basic
    @Column(name = "type_school")
    public String getTypeSchool() {
        return typeSchool;
    }

    public void setTypeSchool(String typeSchool) {
        this.typeSchool = typeSchool;
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
    @Column(name = "level")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TDataUniversityRank that = (TDataUniversityRank) o;
        return id == that.id &&
                Objects.equals(year, that.year) &&
                Objects.equals(baseId, that.baseId) &&
                Objects.equals(univName, that.univName) &&
                Objects.equals(rank, that.rank) &&
                Objects.equals(provinceId, that.provinceId) &&
                Objects.equals(provinceName, that.provinceName) &&
                Objects.equals(rankArea, that.rankArea) &&
                Objects.equals(cateName, that.cateName) &&
                Objects.equals(rankCate, that.rankCate) &&
                Objects.equals(belongName, that.belongName) &&
                Objects.equals(scoreTotal, that.scoreTotal) &&
                Objects.equals(typeCreate, that.typeCreate) &&
                Objects.equals(typeSchool, that.typeSchool) &&
                Objects.equals(grade, that.grade) &&
                Objects.equals(level, that.level);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, year, baseId, univName, rank, provinceId, provinceName, rankArea, cateName, rankCate, belongName, scoreTotal, typeCreate, typeSchool, grade, level);
    }

    @Basic
    @Column(name = "rcpy")
    public String getRcpy() {
        return rcpy;
    }

    public void setRcpy(String rcpy) {
        this.rcpy = rcpy;
    }

    @Basic
    @Column(name = "kxyj")
    public String getKxyj() {
        return kxyj;
    }

    public void setKxyj(String kxyj) {
        this.kxyj = kxyj;
    }

    @Basic
    @Column(name = "shkxyj")
    public String getShkxyj() {
        return shkxyj;
    }

    public void setShkxyj(String shkxyj) {
        this.shkxyj = shkxyj;
    }

    @Basic
    @Column(name = "rank_type")
    public String getRankType() {
        return rankType;
    }

    public void setRankType(String rankType) {
        this.rankType = rankType;
    }
}
