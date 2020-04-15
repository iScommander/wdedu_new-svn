package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/22
 */
@Entity
@Table(name = "t_teacher", schema = "wodecareer", catalog = "")
public class TTeacher {
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TTeacher tTeacher = (TTeacher) o;
        return id == tTeacher.id &&
                Objects.equals(code, tTeacher.code) &&
                Objects.equals(name, tTeacher.name) &&
                Objects.equals(name2, tTeacher.name2) &&
                Objects.equals(contact, tTeacher.contact) &&
                Objects.equals(group, tTeacher.group) &&
                Objects.equals(course, tTeacher.course) &&
                Objects.equals(maxNumDay, tTeacher.maxNumDay) &&
                Objects.equals(maxNumWeek, tTeacher.maxNumWeek) &&
                Objects.equals(title, tTeacher.title) &&
                Objects.equals(school, tTeacher.school) &&
                Objects.equals(grade, tTeacher.grade) &&
                Objects.equals(level, tTeacher.level) &&
                Objects.equals(status, tTeacher.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, code, name, name2, contact, group, course, maxNumDay, maxNumWeek, title, school, grade, level, status);
    }
}
