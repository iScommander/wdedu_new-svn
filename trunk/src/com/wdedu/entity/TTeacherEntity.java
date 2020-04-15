package com.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_teacher", schema = "wodecareer", catalog = "")
public class TTeacherEntity {
    private int id;
    private String code;
    private String name;
    private String name2;
    private String contact;
    private String group;
    private String course;
    private Integer maxNumDay;
    private Integer maxNumWeek;
    private String title;
    private String school;
    private String grade;
    private String level;
    private Integer status;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "name2")
    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    @Basic
    @Column(name = "contact")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Basic
    @Column(name = "group")
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Basic
    @Column(name = "course")
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Basic
    @Column(name = "maxNumDay")
    public Integer getMaxNumDay() {
        return maxNumDay;
    }

    public void setMaxNumDay(Integer maxNumDay) {
        this.maxNumDay = maxNumDay;
    }

    @Basic
    @Column(name = "maxNumWeek")
    public Integer getMaxNumWeek() {
        return maxNumWeek;
    }

    public void setMaxNumWeek(Integer maxNumWeek) {
        this.maxNumWeek = maxNumWeek;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "school")
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
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

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TTeacherEntity that = (TTeacherEntity) o;
        return id == that.id &&
                Objects.equals(code, that.code) &&
                Objects.equals(name, that.name) &&
                Objects.equals(name2, that.name2) &&
                Objects.equals(contact, that.contact) &&
                Objects.equals(group, that.group) &&
                Objects.equals(course, that.course) &&
                Objects.equals(maxNumDay, that.maxNumDay) &&
                Objects.equals(maxNumWeek, that.maxNumWeek) &&
                Objects.equals(title, that.title) &&
                Objects.equals(school, that.school) &&
                Objects.equals(grade, that.grade) &&
                Objects.equals(level, that.level) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, name2, contact, group, course, maxNumDay, maxNumWeek, title, school, grade, level, status);
    }
}
