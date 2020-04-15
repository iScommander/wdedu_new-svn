package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @date 2018/10/17
 */
@Entity
@Table(name = "t_data_university_rules", schema = "wodecareer", catalog = "")
public class TDataUniversityRules {
    private int id;
    private Integer universityId;
    private Integer universityBaseId;
    private String universityName;
    private Integer year;
    private String content;
    private String points;
    private String points01;
    private String points02;
    private String points03;
    private String points04;
    private String points05;
    private String points06;
    private String points07;
    private String points08;
    private String points09;
    private String points10;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "university_id")
    public Integer getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
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
    @Column(name = "university_name")
    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "points")
    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    @Basic
    @Column(name = "points01")
    public String getPoints01() {
        return points01;
    }

    public void setPoints01(String points01) {
        this.points01 = points01;
    }

    @Basic
    @Column(name = "points02")
    public String getPoints02() {
        return points02;
    }

    public void setPoints02(String points02) {
        this.points02 = points02;
    }

    @Basic
    @Column(name = "points03")
    public String getPoints03() {
        return points03;
    }

    public void setPoints03(String points03) {
        this.points03 = points03;
    }

    @Basic
    @Column(name = "points04")
    public String getPoints04() {
        return points04;
    }

    public void setPoints04(String points04) {
        this.points04 = points04;
    }

    @Basic
    @Column(name = "points05")
    public String getPoints05() {
        return points05;
    }

    public void setPoints05(String points05) {
        this.points05 = points05;
    }

    @Basic
    @Column(name = "points06")
    public String getPoints06() {
        return points06;
    }

    public void setPoints06(String points06) {
        this.points06 = points06;
    }

    @Basic
    @Column(name = "points07")
    public String getPoints07() {
        return points07;
    }

    public void setPoints07(String points07) {
        this.points07 = points07;
    }

    @Basic
    @Column(name = "points08")
    public String getPoints08() {
        return points08;
    }

    public void setPoints08(String points08) {
        this.points08 = points08;
    }

    @Basic
    @Column(name = "points09")
    public String getPoints09() {
        return points09;
    }

    public void setPoints09(String points09) {
        this.points09 = points09;
    }

    @Basic
    @Column(name = "points10")
    public String getPoints10() {
        return points10;
    }

    public void setPoints10(String points10) {
        this.points10 = points10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TDataUniversityRules that = (TDataUniversityRules) o;
        return id == that.id &&
                Objects.equals(universityId, that.universityId) &&
                Objects.equals(universityBaseId, that.universityBaseId) &&
                Objects.equals(universityName, that.universityName) &&
                Objects.equals(year, that.year) &&
                Objects.equals(content, that.content) &&
                Objects.equals(points, that.points) &&
                Objects.equals(points01, that.points01) &&
                Objects.equals(points02, that.points02) &&
                Objects.equals(points03, that.points03) &&
                Objects.equals(points04, that.points04) &&
                Objects.equals(points05, that.points05) &&
                Objects.equals(points06, that.points06) &&
                Objects.equals(points07, that.points07) &&
                Objects.equals(points08, that.points08) &&
                Objects.equals(points09, that.points09) &&
                Objects.equals(points10, that.points10);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, universityId, universityBaseId, universityName, year, content, points, points01, points02, points03, points04, points05, points06, points07, points08, points09, points10);
    }
}
