package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_applycount", schema = "wodecareer", catalog = "")
public class TNApplycount {
    private int id;
    private Integer year;
    private String univ;
    private Integer applycount;

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
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "univ")
    public String getUniv() {
        return univ;
    }

    public void setUniv(String univ) {
        this.univ = univ;
    }

    @Basic
    @Column(name = "applycount")
    public Integer getApplycount() {
        return applycount;
    }

    public void setApplycount(Integer applycount) {
        this.applycount = applycount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TNApplycount that = (TNApplycount) o;
        return id == that.id &&
                Objects.equals(year, that.year) &&
                Objects.equals(univ, that.univ) &&
                Objects.equals(applycount, that.applycount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, year, univ, applycount);
    }
}
