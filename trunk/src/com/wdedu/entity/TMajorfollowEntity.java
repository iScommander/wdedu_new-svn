package com.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_majorfollow", schema = "wodecareer", catalog = "")
public class TMajorfollowEntity {
    private int id;
    private Integer student;
    private Integer selector;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "student")
    public Integer getStudent() {
        return student;
    }

    public void setStudent(Integer student) {
        this.student = student;
    }

    @Basic
    @Column(name = "selector")
    public Integer getSelector() {
        return selector;
    }

    public void setSelector(Integer selector) {
        this.selector = selector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TMajorfollowEntity that = (TMajorfollowEntity) o;
        return id == that.id &&
                Objects.equals(student, that.student) &&
                Objects.equals(selector, that.selector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, selector);
    }
}
