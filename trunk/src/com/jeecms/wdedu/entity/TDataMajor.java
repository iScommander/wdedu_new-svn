package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_data_major", schema = "wodecareer", catalog = "")
public class TDataMajor {
    private int id;
    private String parentMajorId;
    private String majorName;
    private String majorId;
    private Blob introduction;
    private Blob requirement;
    private Blob target;
    private Blob future;
    private Blob history;
    private Blob knowledge;
    private Blob parentMajor;
    private Blob course;
    private String year;
    private Blob employmentInfo;
    private Blob similarMajor;
    private Blob career;
    private Blob studentCapacity;
    private Blob degreeIssued;
    private Blob famousSchools;
    private Blob oldNames;
    private Blob guide;
    private Blob practice;
    private String majorUrl;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "parent_major_id")
    public String getParentMajorId() {
        return parentMajorId;
    }

    public void setParentMajorId(String parentMajorId) {
        this.parentMajorId = parentMajorId;
    }

    @Basic
    @Column(name = "major_name")
    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Basic
    @Column(name = "major_id")
    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    @Basic
    @Column(name = "introduction")
    public Blob getIntroduction() {
        return introduction;
    }

    public void setIntroduction(Blob introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "requirement")
    public Blob getRequirement() {
        return requirement;
    }

    public void setRequirement(Blob requirement) {
        this.requirement = requirement;
    }

    @Basic
    @Column(name = "target")
    public Blob getTarget() {
        return target;
    }

    public void setTarget(Blob target) {
        this.target = target;
    }

    @Basic
    @Column(name = "future")
    public Blob getFuture() {
        return future;
    }

    public void setFuture(Blob future) {
        this.future = future;
    }

    @Basic
    @Column(name = "history")
    public Blob getHistory() {
        return history;
    }

    public void setHistory(Blob history) {
        this.history = history;
    }

    @Basic
    @Column(name = "knowledge")
    public Blob getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(Blob knowledge) {
        this.knowledge = knowledge;
    }

    @Basic
    @Column(name = "parent_major")
    public Blob getParentMajor() {
        return parentMajor;
    }

    public void setParentMajor(Blob parentMajor) {
        this.parentMajor = parentMajor;
    }

    @Basic
    @Column(name = "course")
    public Blob getCourse() {
        return course;
    }

    public void setCourse(Blob course) {
        this.course = course;
    }

    @Basic
    @Column(name = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Basic
    @Column(name = "employment_Info")
    public Blob getEmploymentInfo() {
        return employmentInfo;
    }

    public void setEmploymentInfo(Blob employmentInfo) {
        this.employmentInfo = employmentInfo;
    }

    @Basic
    @Column(name = "similar_major")
    public Blob getSimilarMajor() {
        return similarMajor;
    }

    public void setSimilarMajor(Blob similarMajor) {
        this.similarMajor = similarMajor;
    }

    @Basic
    @Column(name = "career")
    public Blob getCareer() {
        return career;
    }

    public void setCareer(Blob career) {
        this.career = career;
    }

    @Basic
    @Column(name = "student_capacity")
    public Blob getStudentCapacity() {
        return studentCapacity;
    }

    public void setStudentCapacity(Blob studentCapacity) {
        this.studentCapacity = studentCapacity;
    }

    @Basic
    @Column(name = "degree_issued")
    public Blob getDegreeIssued() {
        return degreeIssued;
    }

    public void setDegreeIssued(Blob degreeIssued) {
        this.degreeIssued = degreeIssued;
    }

    @Basic
    @Column(name = "famous_schools")
    public Blob getFamousSchools() {
        return famousSchools;
    }

    public void setFamousSchools(Blob famousSchools) {
        this.famousSchools = famousSchools;
    }

    @Basic
    @Column(name = "old_names")
    public Blob getOldNames() {
        return oldNames;
    }

    public void setOldNames(Blob oldNames) {
        this.oldNames = oldNames;
    }

    @Basic
    @Column(name = "guide")
    public Blob getGuide() {
        return guide;
    }

    public void setGuide(Blob guide) {
        this.guide = guide;
    }

    @Basic
    @Column(name = "practice")
    public Blob getPractice() {
        return practice;
    }

    public void setPractice(Blob practice) {
        this.practice = practice;
    }

    @Basic
    @Column(name = "major_url")
    public String getMajorUrl() {
        return majorUrl;
    }

    public void setMajorUrl(String majorUrl) {
        this.majorUrl = majorUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TDataMajor that = (TDataMajor) o;
        return id == that.id &&
                Objects.equals(parentMajorId, that.parentMajorId) &&
                Objects.equals(majorName, that.majorName) &&
                Objects.equals(majorId, that.majorId) &&
                Objects.equals(year, that.year) &&
                Objects.equals(majorUrl, that.majorUrl);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, parentMajorId, majorName, majorId, year, majorUrl);
        return result;
    }
}
