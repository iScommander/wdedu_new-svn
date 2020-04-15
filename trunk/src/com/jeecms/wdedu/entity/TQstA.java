package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/22
 */
@Entity
@Table(name = "t_qst_a", schema = "wodecareer", catalog = "")
public class TQstA {
    private int id;
    private String what;
    private String describe;
    private String type;
    private String name;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "what")
    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    @Basic
    @Column(name = "describe")
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TQstA tQstA = (TQstA) o;
        return id == tQstA.id &&
                Objects.equals(what, tQstA.what) &&
                Objects.equals(describe, tQstA.describe) &&
                Objects.equals(type, tQstA.type) &&
                Objects.equals(name, tQstA.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, what, describe, type, name);
    }
}
