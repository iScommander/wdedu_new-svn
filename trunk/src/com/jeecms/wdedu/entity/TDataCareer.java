package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_data_career", schema = "wodecareer", catalog = "")
public class TDataCareer {
    private int id;
    private String class1;
    private String class2;
    private String career;
    private String summary;

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
    @Column(name = "class1")
    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    @Basic
    @Column(name = "class2")
    public String getClass2() {
        return class2;
    }

    public void setClass2(String class2) {
        this.class2 = class2;
    }

    @Basic
    @Column(name = "career")
    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    @Basic
    @Column(name = "summary")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TDataCareer that = (TDataCareer) o;
        return id == that.id &&
                Objects.equals(class1, that.class1) &&
                Objects.equals(class2, that.class2) &&
                Objects.equals(career, that.career) &&
                Objects.equals(summary, that.summary);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, class1, class2, career, summary);
    }
}
