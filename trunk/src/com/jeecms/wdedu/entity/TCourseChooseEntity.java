package com.jeecms.wdedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_course_choose", schema = "wodecareer", catalog = "")
public class TCourseChooseEntity {
    private int id;
    private Integer userId;
    private String username;
    private String first;
    private String secondA;
    private String secondB;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "first")
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    @Basic
    @Column(name = "second_a")
    public String getSecondA() {
        return secondA;
    }

    public void setSecondA(String secondA) {
        this.secondA = secondA;
    }

    @Basic
    @Column(name = "second_b")
    public String getSecondB() {
        return secondB;
    }

    public void setSecondB(String secondB) {
        this.secondB = secondB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TCourseChooseEntity that = (TCourseChooseEntity) o;

        if (id != that.id) {
            return false;
        }
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) {
            return false;
        }
        if (username != null ? !username.equals(that.username) : that.username != null) {
            return false;
        }
        if (first != null ? !first.equals(that.first) : that.first != null) {
            return false;
        }
        if (secondA != null ? !secondA.equals(that.secondA) : that.secondA != null) {
            return false;
        }
        if (secondB != null ? !secondB.equals(that.secondB) : that.secondB != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (first != null ? first.hashCode() : 0);
        result = 31 * result + (secondA != null ? secondA.hashCode() : 0);
        result = 31 * result + (secondB != null ? secondB.hashCode() : 0);
        return result;
    }
}
