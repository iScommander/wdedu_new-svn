package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_data_enroll_independent", schema = "wodecareer", catalog = "")
public class TDataEnrollIndependent {
    private int id;
    private Integer year;
    private Integer provId;
    private String provinceName;
    private String name;
    private String sex;
    private String schoolName;
    private String serviceType;
    private Integer passType;
    private Integer univId;
    private String univName;
    private String levelNum;
    private String studentNum;
    private String carePolicy;
    private String testItem;
    private String testResults;
    private String testStandard;
    private String selectedMajor;
    private String admissionMajor;
    private String discountScore;
    private String billType;
    private String remark;

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
    @Column(name = "provId")
    public Integer getProvId() {
        return provId;
    }

    public void setProvId(Integer provId) {
        this.provId = provId;
    }

    @Basic
    @Column(name = "provinceName")
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "schoolName")
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Basic
    @Column(name = "serviceType")
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @Basic
    @Column(name = "passType")
    public Integer getPassType() {
        return passType;
    }

    public void setPassType(Integer passType) {
        this.passType = passType;
    }

    @Basic
    @Column(name = "univId")
    public Integer getUnivId() {
        return univId;
    }

    public void setUnivId(Integer univId) {
        this.univId = univId;
    }

    @Basic
    @Column(name = "univName")
    public String getUnivName() {
        return univName;
    }

    public void setUnivName(String univName) {
        this.univName = univName;
    }

    @Basic
    @Column(name = "levelNum")
    public String getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(String levelNum) {
        this.levelNum = levelNum;
    }

    @Basic
    @Column(name = "studentNum")
    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    @Basic
    @Column(name = "carePolicy")
    public String getCarePolicy() {
        return carePolicy;
    }

    public void setCarePolicy(String carePolicy) {
        this.carePolicy = carePolicy;
    }

    @Basic
    @Column(name = "testItem")
    public String getTestItem() {
        return testItem;
    }

    public void setTestItem(String testItem) {
        this.testItem = testItem;
    }

    @Basic
    @Column(name = "testResults")
    public String getTestResults() {
        return testResults;
    }

    public void setTestResults(String testResults) {
        this.testResults = testResults;
    }

    @Basic
    @Column(name = "testStandard")
    public String getTestStandard() {
        return testStandard;
    }

    public void setTestStandard(String testStandard) {
        this.testStandard = testStandard;
    }

    @Basic
    @Column(name = "selectedMajor")
    public String getSelectedMajor() {
        return selectedMajor;
    }

    public void setSelectedMajor(String selectedMajor) {
        this.selectedMajor = selectedMajor;
    }

    @Basic
    @Column(name = "admissionMajor")
    public String getAdmissionMajor() {
        return admissionMajor;
    }

    public void setAdmissionMajor(String admissionMajor) {
        this.admissionMajor = admissionMajor;
    }

    @Basic
    @Column(name = "discountScore")
    public String getDiscountScore() {
        return discountScore;
    }

    public void setDiscountScore(String discountScore) {
        this.discountScore = discountScore;
    }

    @Basic
    @Column(name = "billType")
    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TDataEnrollIndependent that = (TDataEnrollIndependent) o;
        return id == that.id &&
                Objects.equals(year, that.year) &&
                Objects.equals(provId, that.provId) &&
                Objects.equals(provinceName, that.provinceName) &&
                Objects.equals(name, that.name) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(schoolName, that.schoolName) &&
                Objects.equals(serviceType, that.serviceType) &&
                Objects.equals(passType, that.passType) &&
                Objects.equals(univId, that.univId) &&
                Objects.equals(univName, that.univName) &&
                Objects.equals(levelNum, that.levelNum) &&
                Objects.equals(studentNum, that.studentNum) &&
                Objects.equals(carePolicy, that.carePolicy) &&
                Objects.equals(testItem, that.testItem) &&
                Objects.equals(testResults, that.testResults) &&
                Objects.equals(testStandard, that.testStandard) &&
                Objects.equals(selectedMajor, that.selectedMajor) &&
                Objects.equals(admissionMajor, that.admissionMajor) &&
                Objects.equals(discountScore, that.discountScore) &&
                Objects.equals(billType, that.billType) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, year, provId, provinceName, name, sex, schoolName, serviceType, passType, univId, univName, levelNum, studentNum, carePolicy, testItem, testResults, testStandard, selectedMajor, admissionMajor, discountScore, billType, remark);
    }
}
