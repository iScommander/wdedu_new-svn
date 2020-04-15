package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_sc_active_detail", schema = "wodecareer", catalog = "")
@IdClass(TScActiveDetailPK.class)
public class TScActiveDetail {
    private int id;
    private int activeId;
    private String activeType;
    private String province;
    private String proId;
    private String city;
    private String quxian;
    private String activeTarge;
    private String activeClass;
    private Integer productId;
    private String lecturer1;
    private Integer teacher1Id;
    private String lecturerPicture1;
    private String lecturerExpert1;
    private String lecturer2;
    private Integer teacher2Id;
    private String lecturerPicture2;
    private String lecturerExpert2;
    private String address;
    private Timestamp activeTime;
    private String theme;
    private String details;
    private String activeStatus;
    private Integer type;
    private Integer free;
    private Timestamp activeStartTime;
    private Timestamp activeEndTime;
    private Timestamp qdStartTime;
    private Timestamp qdEndTime;
    private String hostDepartment;
    private String image;
    private String image2;
    private String lqzlContent;
    private String remark;
    private Integer people;
    private String signKind;
    private Integer sfxybm;
    private Integer sfxyqd;
    private String infoPaySuccess;
    private Integer ispublish;
    private Integer isShowKind;
    private String branchCompany;
    private String schoolName;
    private Integer initialNumber;
    private Integer userId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "active_Id", nullable = false)
    public int getActiveId() {
        return activeId;
    }

    public void setActiveId(int activeId) {
        this.activeId = activeId;
    }

    @Basic
    @Column(name = "active_type", nullable = true, length = 20)
    public String getActiveType() {
        return activeType;
    }

    public void setActiveType(String activeType) {
        this.activeType = activeType;
    }

    @Basic
    @Column(name = "province", nullable = true, length = 11)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "pro_id", nullable = true, length = 100)
    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    @Basic
    @Column(name = "city", nullable = true, length = 11)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "quxian", nullable = true, length = 11)
    public String getQuxian() {
        return quxian;
    }

    public void setQuxian(String quxian) {
        this.quxian = quxian;
    }

    @Basic
    @Column(name = "active_targe", nullable = true, length = 100)
    public String getActiveTarge() {
        return activeTarge;
    }

    public void setActiveTarge(String activeTarge) {
        this.activeTarge = activeTarge;
    }

    @Basic
    @Column(name = "active_class", nullable = true, length = 100)
    public String getActiveClass() {
        return activeClass;
    }

    public void setActiveClass(String activeClass) {
        this.activeClass = activeClass;
    }

    @Basic
    @Column(name = "product_id", nullable = true)
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "lecturer1", nullable = true, length = 50)
    public String getLecturer1() {
        return lecturer1;
    }

    public void setLecturer1(String lecturer1) {
        this.lecturer1 = lecturer1;
    }

    @Basic
    @Column(name = "teacher1_id", nullable = true)
    public Integer getTeacher1Id() {
        return teacher1Id;
    }

    public void setTeacher1Id(Integer teacher1Id) {
        this.teacher1Id = teacher1Id;
    }

    @Basic
    @Column(name = "lecturer_picture1", nullable = true, length = 50)
    public String getLecturerPicture1() {
        return lecturerPicture1;
    }

    public void setLecturerPicture1(String lecturerPicture1) {
        this.lecturerPicture1 = lecturerPicture1;
    }

    @Basic
    @Column(name = "lecturer_expert1", nullable = true, length = 50)
    public String getLecturerExpert1() {
        return lecturerExpert1;
    }

    public void setLecturerExpert1(String lecturerExpert1) {
        this.lecturerExpert1 = lecturerExpert1;
    }

    @Basic
    @Column(name = "lecturer2", nullable = true, length = 50)
    public String getLecturer2() {
        return lecturer2;
    }

    public void setLecturer2(String lecturer2) {
        this.lecturer2 = lecturer2;
    }

    @Basic
    @Column(name = "teacher2_id", nullable = true)
    public Integer getTeacher2Id() {
        return teacher2Id;
    }

    public void setTeacher2Id(Integer teacher2Id) {
        this.teacher2Id = teacher2Id;
    }

    @Basic
    @Column(name = "lecturer_picture2", nullable = true, length = 50)
    public String getLecturerPicture2() {
        return lecturerPicture2;
    }

    public void setLecturerPicture2(String lecturerPicture2) {
        this.lecturerPicture2 = lecturerPicture2;
    }

    @Basic
    @Column(name = "lecturer_expert2", nullable = true, length = 50)
    public String getLecturerExpert2() {
        return lecturerExpert2;
    }

    public void setLecturerExpert2(String lecturerExpert2) {
        this.lecturerExpert2 = lecturerExpert2;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "active_time", nullable = true)
    public Timestamp getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Timestamp activeTime) {
        this.activeTime = activeTime;
    }

    @Basic
    @Column(name = "theme", nullable = true, length = 100)
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Basic
    @Column(name = "details", nullable = true, length = -1)
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Basic
    @Column(name = "active_status", nullable = true, length = 20)
    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "free", nullable = true)
    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }

    @Basic
    @Column(name = "active_start_time", nullable = true)
    public Timestamp getActiveStartTime() {
        return activeStartTime;
    }

    public void setActiveStartTime(Timestamp activeStartTime) {
        this.activeStartTime = activeStartTime;
    }

    @Basic
    @Column(name = "active_end_time", nullable = true)
    public Timestamp getActiveEndTime() {
        return activeEndTime;
    }

    public void setActiveEndTime(Timestamp activeEndTime) {
        this.activeEndTime = activeEndTime;
    }

    @Basic
    @Column(name = "qd_start_time", nullable = true)
    public Timestamp getQdStartTime() {
        return qdStartTime;
    }

    public void setQdStartTime(Timestamp qdStartTime) {
        this.qdStartTime = qdStartTime;
    }

    @Basic
    @Column(name = "qd_end_time", nullable = true)
    public Timestamp getQdEndTime() {
        return qdEndTime;
    }

    public void setQdEndTime(Timestamp qdEndTime) {
        this.qdEndTime = qdEndTime;
    }

    @Basic
    @Column(name = "host_department", nullable = true, length = 32)
    public String getHostDepartment() {
        return hostDepartment;
    }

    public void setHostDepartment(String hostDepartment) {
        this.hostDepartment = hostDepartment;
    }

    @Basic
    @Column(name = "image", nullable = true, length = 100)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "image2", nullable = true, length = 255)
    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    @Basic
    @Column(name = "lqzl_content", nullable = true, length = 2048)
    public String getLqzlContent() {
        return lqzlContent;
    }

    public void setLqzlContent(String lqzlContent) {
        this.lqzlContent = lqzlContent;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "people", nullable = true)
    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    @Basic
    @Column(name = "sign_kind", nullable = true, length = 255)
    public String getSignKind() {
        return signKind;
    }

    public void setSignKind(String signKind) {
        this.signKind = signKind;
    }

    @Basic
    @Column(name = "sfxybm", nullable = true)
    public Integer getSfxybm() {
        return sfxybm;
    }

    public void setSfxybm(Integer sfxybm) {
        this.sfxybm = sfxybm;
    }

    @Basic
    @Column(name = "sfxyqd", nullable = true)
    public Integer getSfxyqd() {
        return sfxyqd;
    }

    public void setSfxyqd(Integer sfxyqd) {
        this.sfxyqd = sfxyqd;
    }

    @Basic
    @Column(name = "info_pay_success", nullable = true, length = 255)
    public String getInfoPaySuccess() {
        return infoPaySuccess;
    }

    public void setInfoPaySuccess(String infoPaySuccess) {
        this.infoPaySuccess = infoPaySuccess;
    }

    @Basic
    @Column(name = "ispublish", nullable = true)
    public Integer getIspublish() {
        return ispublish;
    }

    public void setIspublish(Integer ispublish) {
        this.ispublish = ispublish;
    }

    @Basic
    @Column(name = "isShowKind", nullable = true)
    public Integer getIsShowKind() {
        return isShowKind;
    }

    public void setIsShowKind(Integer isShowKind) {
        this.isShowKind = isShowKind;
    }

    @Basic
    @Column(name = "branch_company", nullable = true, length = 50)
    public String getBranchCompany() {
        return branchCompany;
    }

    public void setBranchCompany(String branchCompany) {
        this.branchCompany = branchCompany;
    }

    @Basic
    @Column(name = "school_name", nullable = true, length = 100)
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TScActiveDetail that = (TScActiveDetail) o;
        return id == that.id &&
                activeId == that.activeId &&
                Objects.equals(activeType, that.activeType) &&
                Objects.equals(province, that.province) &&
                Objects.equals(proId, that.proId) &&
                Objects.equals(city, that.city) &&
                Objects.equals(quxian, that.quxian) &&
                Objects.equals(activeTarge, that.activeTarge) &&
                Objects.equals(activeClass, that.activeClass) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(lecturer1, that.lecturer1) &&
                Objects.equals(teacher1Id, that.teacher1Id) &&
                Objects.equals(lecturerPicture1, that.lecturerPicture1) &&
                Objects.equals(lecturerExpert1, that.lecturerExpert1) &&
                Objects.equals(lecturer2, that.lecturer2) &&
                Objects.equals(teacher2Id, that.teacher2Id) &&
                Objects.equals(lecturerPicture2, that.lecturerPicture2) &&
                Objects.equals(lecturerExpert2, that.lecturerExpert2) &&
                Objects.equals(address, that.address) &&
                Objects.equals(activeTime, that.activeTime) &&
                Objects.equals(theme, that.theme) &&
                Objects.equals(details, that.details) &&
                Objects.equals(activeStatus, that.activeStatus) &&
                Objects.equals(type, that.type) &&
                Objects.equals(free, that.free) &&
                Objects.equals(activeStartTime, that.activeStartTime) &&
                Objects.equals(activeEndTime, that.activeEndTime) &&
                Objects.equals(qdStartTime, that.qdStartTime) &&
                Objects.equals(qdEndTime, that.qdEndTime) &&
                Objects.equals(hostDepartment, that.hostDepartment) &&
                Objects.equals(image, that.image) &&
                Objects.equals(image2, that.image2) &&
                Objects.equals(lqzlContent, that.lqzlContent) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(people, that.people) &&
                Objects.equals(signKind, that.signKind) &&
                Objects.equals(sfxybm, that.sfxybm) &&
                Objects.equals(sfxyqd, that.sfxyqd) &&
                Objects.equals(infoPaySuccess, that.infoPaySuccess) &&
                Objects.equals(ispublish, that.ispublish) &&
                Objects.equals(isShowKind, that.isShowKind) &&
                Objects.equals(branchCompany, that.branchCompany) &&
                Objects.equals(schoolName, that.schoolName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activeId, activeType, province, proId, city, quxian, activeTarge, activeClass, productId, lecturer1, teacher1Id, lecturerPicture1, lecturerExpert1, lecturer2, teacher2Id, lecturerPicture2, lecturerExpert2, address, activeTime, theme, details, activeStatus, type, free, activeStartTime, activeEndTime, qdStartTime, qdEndTime, hostDepartment, image, image2, lqzlContent, remark, people, signKind, sfxybm, sfxyqd, infoPaySuccess, ispublish, isShowKind, branchCompany, schoolName);
    }

    @Basic
    @Column(name = "initial_number")
    public Integer getInitialNumber() {
        return initialNumber;
    }

    public void setInitialNumber(Integer initialNumber) {
        this.initialNumber = initialNumber;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
