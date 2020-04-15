package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_mgr_user_base_user", schema = "wodecareer", catalog = "")
public class TMgrUserBaseUserEntity {
    private String userAccount;
    private String userName;
    private String telephone;
    private String phoneNumber;
    private String majorType;
    private String sex;
    private String birthday;
    private String nation;
    private String politics;
    private String colorBlind;
    private String height;
    private String weight;
    private String examProvince;
    private String examCity;
    private String examRegion;
    private String examNumber;
    private String studentType;
    private String identityNumber;
    private String language;
    private String subjectType;
    private String postcode;
    private String photo;
    private String email;
    private String mailAddress;
    private String leftEyesight;
    private String rightEyesight;
    private Integer userId;
    private String postName;
    private String postPhone;
    private String postAttress;
    private String postZip;
    private String mailProvince;
    private String mailCity;
    private String mailRegion;
    private String examSchool;

    @Id
    @Column(name = "userAccount")
    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Basic
    @Column(name = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "majorType")
    public String getMajorType() {
        return majorType;
    }

    public void setMajorType(String majorType) {
        this.majorType = majorType;
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
    @Column(name = "birthday")
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "nation")
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @Basic
    @Column(name = "politics")
    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    @Basic
    @Column(name = "colorBlind")
    public String getColorBlind() {
        return colorBlind;
    }

    public void setColorBlind(String colorBlind) {
        this.colorBlind = colorBlind;
    }

    @Basic
    @Column(name = "height")
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Basic
    @Column(name = "weight")
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "examProvince")
    public String getExamProvince() {
        return examProvince;
    }

    public void setExamProvince(String examProvince) {
        this.examProvince = examProvince;
    }

    @Basic
    @Column(name = "examCity")
    public String getExamCity() {
        return examCity;
    }

    public void setExamCity(String examCity) {
        this.examCity = examCity;
    }

    @Basic
    @Column(name = "examRegion")
    public String getExamRegion() {
        return examRegion;
    }

    public void setExamRegion(String examRegion) {
        this.examRegion = examRegion;
    }

    @Basic
    @Column(name = "ExamNumber")
    public String getExamNumber() {
        return examNumber;
    }

    public void setExamNumber(String examNumber) {
        this.examNumber = examNumber;
    }

    @Basic
    @Column(name = "studentType")
    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    @Basic
    @Column(name = "identityNumber")
    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    @Basic
    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "subjectType")
    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    @Basic
    @Column(name = "postcode")
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Basic
    @Column(name = "photo")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "mailAddress")
    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    @Basic
    @Column(name = "leftEyesight")
    public String getLeftEyesight() {
        return leftEyesight;
    }

    public void setLeftEyesight(String leftEyesight) {
        this.leftEyesight = leftEyesight;
    }

    @Basic
    @Column(name = "rightEyesight")
    public String getRightEyesight() {
        return rightEyesight;
    }

    public void setRightEyesight(String rightEyesight) {
        this.rightEyesight = rightEyesight;
    }

    @Basic
    @Column(name = "userId")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "postName")
    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    @Basic
    @Column(name = "postPhone")
    public String getPostPhone() {
        return postPhone;
    }

    public void setPostPhone(String postPhone) {
        this.postPhone = postPhone;
    }

    @Basic
    @Column(name = "postAttress")
    public String getPostAttress() {
        return postAttress;
    }

    public void setPostAttress(String postAttress) {
        this.postAttress = postAttress;
    }

    @Basic
    @Column(name = "postZip")
    public String getPostZip() {
        return postZip;
    }

    public void setPostZip(String postZip) {
        this.postZip = postZip;
    }

    @Basic
    @Column(name = "mailProvince")
    public String getMailProvince() {
        return mailProvince;
    }

    public void setMailProvince(String mailProvince) {
        this.mailProvince = mailProvince;
    }

    @Basic
    @Column(name = "mailCity")
    public String getMailCity() {
        return mailCity;
    }

    public void setMailCity(String mailCity) {
        this.mailCity = mailCity;
    }

    @Basic
    @Column(name = "mailRegion")
    public String getMailRegion() {
        return mailRegion;
    }

    public void setMailRegion(String mailRegion) {
        this.mailRegion = mailRegion;
    }

    @Basic
    @Column(name = "examSchool")
    public String getExamSchool() {
        return examSchool;
    }

    public void setExamSchool(String examSchool) {
        this.examSchool = examSchool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TMgrUserBaseUserEntity that = (TMgrUserBaseUserEntity) o;
        return Objects.equals(userAccount, that.userAccount) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(telephone, that.telephone) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(majorType, that.majorType) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(nation, that.nation) &&
                Objects.equals(politics, that.politics) &&
                Objects.equals(colorBlind, that.colorBlind) &&
                Objects.equals(height, that.height) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(examProvince, that.examProvince) &&
                Objects.equals(examCity, that.examCity) &&
                Objects.equals(examRegion, that.examRegion) &&
                Objects.equals(examNumber, that.examNumber) &&
                Objects.equals(studentType, that.studentType) &&
                Objects.equals(identityNumber, that.identityNumber) &&
                Objects.equals(language, that.language) &&
                Objects.equals(subjectType, that.subjectType) &&
                Objects.equals(postcode, that.postcode) &&
                Objects.equals(photo, that.photo) &&
                Objects.equals(email, that.email) &&
                Objects.equals(mailAddress, that.mailAddress) &&
                Objects.equals(leftEyesight, that.leftEyesight) &&
                Objects.equals(rightEyesight, that.rightEyesight) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(postName, that.postName) &&
                Objects.equals(postPhone, that.postPhone) &&
                Objects.equals(postAttress, that.postAttress) &&
                Objects.equals(postZip, that.postZip) &&
                Objects.equals(mailProvince, that.mailProvince) &&
                Objects.equals(mailCity, that.mailCity) &&
                Objects.equals(mailRegion, that.mailRegion) &&
                Objects.equals(examSchool, that.examSchool);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAccount, userName, telephone, phoneNumber, majorType, sex, birthday, nation, politics, colorBlind, height, weight, examProvince, examCity, examRegion, examNumber, studentType, identityNumber, language, subjectType, postcode, photo, email, mailAddress, leftEyesight, rightEyesight, userId, postName, postPhone, postAttress, postZip, mailProvince, mailCity, mailRegion, examSchool);
    }
}
