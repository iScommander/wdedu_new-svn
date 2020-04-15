package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @date 2018/10/22
 */
@Entity
@Table(name = "t_qst", schema = "wodecareer", catalog = "")
public class TQst {
    private int id;
    private String content;
    private String type;
    private String option1;
    private String value1;
    private String option2;
    private String value2;
    private String option3;
    private String value3;
    private String option4;
    private String value4;
    private String option5;
    private String value5;
    private String option6;
    private String value6;
    private String option7;
    private String value7;
    private String dType;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "option1")
    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    @Basic
    @Column(name = "value1")
    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    @Basic
    @Column(name = "option2")
    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    @Basic
    @Column(name = "value2")
    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    @Basic
    @Column(name = "option3")
    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    @Basic
    @Column(name = "value3")
    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    @Basic
    @Column(name = "option4")
    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    @Basic
    @Column(name = "value4")
    public String getValue4() {
        return value4;
    }

    public void setValue4(String value4) {
        this.value4 = value4;
    }

    @Basic
    @Column(name = "option5")
    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    @Basic
    @Column(name = "value5")
    public String getValue5() {
        return value5;
    }

    public void setValue5(String value5) {
        this.value5 = value5;
    }

    @Basic
    @Column(name = "option6")
    public String getOption6() {
        return option6;
    }

    public void setOption6(String option6) {
        this.option6 = option6;
    }

    @Basic
    @Column(name = "value6")
    public String getValue6() {
        return value6;
    }

    public void setValue6(String value6) {
        this.value6 = value6;
    }

    @Basic
    @Column(name = "option7")
    public String getOption7() {
        return option7;
    }

    public void setOption7(String option7) {
        this.option7 = option7;
    }

    @Basic
    @Column(name = "value7")
    public String getValue7() {
        return value7;
    }

    public void setValue7(String value7) {
        this.value7 = value7;
    }

    @Basic
    @Column(name = "D_type")
    public String getdType() {
        return dType;
    }

    public void setdType(String dType) {
        this.dType = dType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TQst tQst = (TQst) o;
        return id == tQst.id &&
                Objects.equals(content, tQst.content) &&
                Objects.equals(type, tQst.type) &&
                Objects.equals(option1, tQst.option1) &&
                Objects.equals(value1, tQst.value1) &&
                Objects.equals(option2, tQst.option2) &&
                Objects.equals(value2, tQst.value2) &&
                Objects.equals(option3, tQst.option3) &&
                Objects.equals(value3, tQst.value3) &&
                Objects.equals(option4, tQst.option4) &&
                Objects.equals(value4, tQst.value4) &&
                Objects.equals(option5, tQst.option5) &&
                Objects.equals(value5, tQst.value5) &&
                Objects.equals(option6, tQst.option6) &&
                Objects.equals(value6, tQst.value6) &&
                Objects.equals(option7, tQst.option7) &&
                Objects.equals(value7, tQst.value7) &&
                Objects.equals(dType, tQst.dType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, content, type, option1, value1, option2, value2, option3, value3, option4, value4, option5, value5, option6, value6, option7, value7, dType);
    }
}
