package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_school", schema = "wodecareer", catalog = "")
public class TNSchool {
    private String userAccount;
    private String rollProvince;
    private String rollCity;
    private String rollRegion;
    private String rollSchool;
    private String province;
    private String city;
    private String region;
    private String school;
    private String schoolLevel;
    private String grade;
    private String classLeader;
    private String headTeacher;
    private String headTeacherPhone;
    private String schoolAddress;
    private String schoolPostcode;
    private String gradeLeader;
    private String gradeLeaderPhone;

    @Id
    @Column(name = "userAccount")
    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Basic
    @Column(name = "rollProvince")
    public String getRollProvince() {
        return rollProvince;
    }

    public void setRollProvince(String rollProvince) {
        this.rollProvince = rollProvince;
    }

    @Basic
    @Column(name = "rollCity")
    public String getRollCity() {
        return rollCity;
    }

    public void setRollCity(String rollCity) {
        this.rollCity = rollCity;
    }

    @Basic
    @Column(name = "rollRegion")
    public String getRollRegion() {
        return rollRegion;
    }

    public void setRollRegion(String rollRegion) {
        this.rollRegion = rollRegion;
    }

    @Basic
    @Column(name = "rollSchool")
    public String getRollSchool() {
        return rollSchool;
    }

    public void setRollSchool(String rollSchool) {
        this.rollSchool = rollSchool;
    }

    @Basic
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
    @Column(name = "schoolLevel")
    public String getSchoolLevel() {
        return schoolLevel;
    }

    public void setSchoolLevel(String schoolLevel) {
        this.schoolLevel = schoolLevel;
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
    @Column(name = "classLeader")
    public String getClassLeader() {
        return classLeader;
    }

    public void setClassLeader(String classLeader) {
        this.classLeader = classLeader;
    }

    @Basic
    @Column(name = "headTeacher")
    public String getHeadTeacher() {
        return headTeacher;
    }

    public void setHeadTeacher(String headTeacher) {
        this.headTeacher = headTeacher;
    }

    @Basic
    @Column(name = "headTeacherPhone")
    public String getHeadTeacherPhone() {
        return headTeacherPhone;
    }

    public void setHeadTeacherPhone(String headTeacherPhone) {
        this.headTeacherPhone = headTeacherPhone;
    }

    @Basic
    @Column(name = "schoolAddress")
    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    @Basic
    @Column(name = "schoolPostcode")
    public String getSchoolPostcode() {
        return schoolPostcode;
    }

    public void setSchoolPostcode(String schoolPostcode) {
        this.schoolPostcode = schoolPostcode;
    }

    @Basic
    @Column(name = "gradeLeader")
    public String getGradeLeader() {
        return gradeLeader;
    }

    public void setGradeLeader(String gradeLeader) {
        this.gradeLeader = gradeLeader;
    }

    @Basic
    @Column(name = "gradeLeaderPhone")
    public String getGradeLeaderPhone() {
        return gradeLeaderPhone;
    }

    public void setGradeLeaderPhone(String gradeLeaderPhone) {
        this.gradeLeaderPhone = gradeLeaderPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TNSchool tnSchool = (TNSchool) o;
        return Objects.equals(userAccount, tnSchool.userAccount) &&
                Objects.equals(rollProvince, tnSchool.rollProvince) &&
                Objects.equals(rollCity, tnSchool.rollCity) &&
                Objects.equals(rollRegion, tnSchool.rollRegion) &&
                Objects.equals(rollSchool, tnSchool.rollSchool) &&
                Objects.equals(province, tnSchool.province) &&
                Objects.equals(city, tnSchool.city) &&
                Objects.equals(region, tnSchool.region) &&
                Objects.equals(school, tnSchool.school) &&
                Objects.equals(schoolLevel, tnSchool.schoolLevel) &&
                Objects.equals(grade, tnSchool.grade) &&
                Objects.equals(classLeader, tnSchool.classLeader) &&
                Objects.equals(headTeacher, tnSchool.headTeacher) &&
                Objects.equals(headTeacherPhone, tnSchool.headTeacherPhone) &&
                Objects.equals(schoolAddress, tnSchool.schoolAddress) &&
                Objects.equals(schoolPostcode, tnSchool.schoolPostcode) &&
                Objects.equals(gradeLeader, tnSchool.gradeLeader) &&
                Objects.equals(gradeLeaderPhone, tnSchool.gradeLeaderPhone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userAccount, rollProvince, rollCity, rollRegion, rollSchool, province, city, region, school, schoolLevel, grade, classLeader, headTeacher, headTeacherPhone, schoolAddress, schoolPostcode, gradeLeader, gradeLeaderPhone);
    }
}
