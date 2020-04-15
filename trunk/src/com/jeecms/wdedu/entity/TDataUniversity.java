package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/24
 */
@Entity
@Table(name = "t_data_university", schema = "wodecareer", catalog = "")
public class TDataUniversity {
    private int univId;
    private int universityBaseId;
    private Integer provinceId;
    private Integer cityId;
    private String univName;
    private String univAttr;
    private String univAttrId;
    private String oldUnivName;
    private String oldUniversityName2;
    private String location;
    private String univBelong;
    private String univLevel;
    private String univType;
    private String eduType;
    private String offOrVol;
    private Integer bachelor;
    private Integer threeYearDegree;
    private Integer is211;
    private Integer is985;
    private Integer isDefence;
    private Integer isExcellent;
    private int isIndependence;
    private int isArtStudent;
    private int isKeylab;
    private int isFirstRateUniv;
    private int isFirstRateMajor;
    private String telephone;
    private String address;
    private String zipcode;
    private String logoUrl;
    private String univUrl;
    private Integer coreSubjectNum;
    private Integer masterNum;
    private Integer doctorNum;

    @Id
    @Column(name = "univ_id")
    public int getUnivId() {
        return univId;
    }

    public void setUnivId(int univId) {
        this.univId = univId;
    }

    @Basic
    @Column(name = "university_base_id")
    public int getUniversityBaseId() {
        return universityBaseId;
    }

    public void setUniversityBaseId(int universityBaseId) {
        this.universityBaseId = universityBaseId;
    }

    @Basic
    @Column(name = "province_id")
    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @Basic
    @Column(name = "city_id")
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "univ_name")
    public String getUnivName() {
        return univName;
    }

    public void setUnivName(String univName) {
        this.univName = univName;
    }

    @Basic
    @Column(name = "univ_attr")
    public String getUnivAttr() {
        return univAttr;
    }

    public void setUnivAttr(String univAttr) {
        this.univAttr = univAttr;
    }

    @Basic
    @Column(name = "univ_attr_id")
    public String getUnivAttrId() {
        return univAttrId;
    }

    public void setUnivAttrId(String univAttrId) {
        this.univAttrId = univAttrId;
    }

    @Basic
    @Column(name = "old_univ_name")
    public String getOldUnivName() {
        return oldUnivName;
    }

    public void setOldUnivName(String oldUnivName) {
        this.oldUnivName = oldUnivName;
    }

    @Basic
    @Column(name = "old_university_name_2")
    public String getOldUniversityName2() {
        return oldUniversityName2;
    }

    public void setOldUniversityName2(String oldUniversityName2) {
        this.oldUniversityName2 = oldUniversityName2;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "univ_belong")
    public String getUnivBelong() {
        return univBelong;
    }

    public void setUnivBelong(String univBelong) {
        this.univBelong = univBelong;
    }

    @Basic
    @Column(name = "univ_level")
    public String getUnivLevel() {
        return univLevel;
    }

    public void setUnivLevel(String univLevel) {
        this.univLevel = univLevel;
    }

    @Basic
    @Column(name = "univ_type")
    public String getUnivType() {
        return univType;
    }

    public void setUnivType(String univType) {
        this.univType = univType;
    }

    @Basic
    @Column(name = "edu_type")
    public String getEduType() {
        return eduType;
    }

    public void setEduType(String eduType) {
        this.eduType = eduType;
    }

    @Basic
    @Column(name = "off_OR_vol")
    public String getOffOrVol() {
        return offOrVol;
    }

    public void setOffOrVol(String offOrVol) {
        this.offOrVol = offOrVol;
    }

    @Basic
    @Column(name = "bachelor")
    public Integer getBachelor() {
        return bachelor;
    }

    public void setBachelor(Integer bachelor) {
        this.bachelor = bachelor;
    }

    @Basic
    @Column(name = "threeYearDegree")
    public Integer getThreeYearDegree() {
        return threeYearDegree;
    }

    public void setThreeYearDegree(Integer threeYearDegree) {
        this.threeYearDegree = threeYearDegree;
    }

    @Basic
    @Column(name = "is211")
    public Integer getIs211() {
        return is211;
    }

    public void setIs211(Integer is211) {
        this.is211 = is211;
    }

    @Basic
    @Column(name = "is985")
    public Integer getIs985() {
        return is985;
    }

    public void setIs985(Integer is985) {
        this.is985 = is985;
    }

    @Basic
    @Column(name = "isDefence")
    public Integer getIsDefence() {
        return isDefence;
    }

    public void setIsDefence(Integer isDefence) {
        this.isDefence = isDefence;
    }

    @Basic
    @Column(name = "isExcellent")
    public Integer getIsExcellent() {
        return isExcellent;
    }

    public void setIsExcellent(Integer isExcellent) {
        this.isExcellent = isExcellent;
    }

    @Basic
    @Column(name = "isIndependence")
    public int getIsIndependence() {
        return isIndependence;
    }

    public void setIsIndependence(int isIndependence) {
        this.isIndependence = isIndependence;
    }

    @Basic
    @Column(name = "isArtStudent")
    public int getIsArtStudent() {
        return isArtStudent;
    }

    public void setIsArtStudent(int isArtStudent) {
        this.isArtStudent = isArtStudent;
    }

    @Basic
    @Column(name = "isKeylab")
    public int getIsKeylab() {
        return isKeylab;
    }

    public void setIsKeylab(int isKeylab) {
        this.isKeylab = isKeylab;
    }

    @Basic
    @Column(name = "isFirstRateUniv")
    public int getIsFirstRateUniv() {
        return isFirstRateUniv;
    }

    public void setIsFirstRateUniv(int isFirstRateUniv) {
        this.isFirstRateUniv = isFirstRateUniv;
    }

    @Basic
    @Column(name = "isFirstRateMajor")
    public int getIsFirstRateMajor() {
        return isFirstRateMajor;
    }

    public void setIsFirstRateMajor(int isFirstRateMajor) {
        this.isFirstRateMajor = isFirstRateMajor;
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
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "zipcode")
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Basic
    @Column(name = "logo_url")
    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @Basic
    @Column(name = "univ_url")
    public String getUnivUrl() {
        return univUrl;
    }

    public void setUnivUrl(String univUrl) {
        this.univUrl = univUrl;
    }

    @Basic
    @Column(name = "coreSubjectNum")
    public Integer getCoreSubjectNum() {
        return coreSubjectNum;
    }

    public void setCoreSubjectNum(Integer coreSubjectNum) {
        this.coreSubjectNum = coreSubjectNum;
    }

    @Basic
    @Column(name = "masterNum")
    public Integer getMasterNum() {
        return masterNum;
    }

    public void setMasterNum(Integer masterNum) {
        this.masterNum = masterNum;
    }

    @Basic
    @Column(name = "doctorNum")
    public Integer getDoctorNum() {
        return doctorNum;
    }

    public void setDoctorNum(Integer doctorNum) {
        this.doctorNum = doctorNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TDataUniversity that = (TDataUniversity) o;
        return univId == that.univId &&
                universityBaseId == that.universityBaseId &&
                isIndependence == that.isIndependence &&
                isArtStudent == that.isArtStudent &&
                isKeylab == that.isKeylab &&
                isFirstRateUniv == that.isFirstRateUniv &&
                isFirstRateMajor == that.isFirstRateMajor &&
                Objects.equals(provinceId, that.provinceId) &&
                Objects.equals(cityId, that.cityId) &&
                Objects.equals(univName, that.univName) &&
                Objects.equals(univAttr, that.univAttr) &&
                Objects.equals(univAttrId, that.univAttrId) &&
                Objects.equals(oldUnivName, that.oldUnivName) &&
                Objects.equals(oldUniversityName2, that.oldUniversityName2) &&
                Objects.equals(location, that.location) &&
                Objects.equals(univBelong, that.univBelong) &&
                Objects.equals(univLevel, that.univLevel) &&
                Objects.equals(univType, that.univType) &&
                Objects.equals(eduType, that.eduType) &&
                Objects.equals(offOrVol, that.offOrVol) &&
                Objects.equals(bachelor, that.bachelor) &&
                Objects.equals(threeYearDegree, that.threeYearDegree) &&
                Objects.equals(is211, that.is211) &&
                Objects.equals(is985, that.is985) &&
                Objects.equals(isDefence, that.isDefence) &&
                Objects.equals(isExcellent, that.isExcellent) &&
                Objects.equals(telephone, that.telephone) &&
                Objects.equals(address, that.address) &&
                Objects.equals(zipcode, that.zipcode) &&
                Objects.equals(logoUrl, that.logoUrl) &&
                Objects.equals(univUrl, that.univUrl) &&
                Objects.equals(coreSubjectNum, that.coreSubjectNum) &&
                Objects.equals(masterNum, that.masterNum) &&
                Objects.equals(doctorNum, that.doctorNum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(univId, universityBaseId, provinceId, cityId, univName, univAttr, univAttrId, oldUnivName, oldUniversityName2, location, univBelong, univLevel, univType, eduType, offOrVol, bachelor, threeYearDegree, is211, is985, isDefence, isExcellent, isIndependence, isArtStudent, isKeylab, isFirstRateUniv, isFirstRateMajor, telephone, address, zipcode, logoUrl, univUrl, coreSubjectNum, masterNum, doctorNum);
    }
}
