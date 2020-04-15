package com.jeecms.wdedu.entity;

import com.utils.excel.Excel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @date 2018/10/30
 */
@Entity
@Table(name = "t_cee_applications_detail", schema = "wodecareer", catalog = "")
public class TCeeApplicationsDetail implements Serializable {
    private int id;
    private int applicationId;
    private Integer year;
    private Integer provinceId;
    @Excel(exportName = "文理科", exportConvertSign = 1, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private Integer majorTypeId;
    private Integer batchId;
    private String univId;
    @Excel(exportName = "学校编码", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private String univCode;
    @Excel(exportName = "学校名称", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private String univName;
    private int univOrder;
    private String majorId;
    @Excel(exportName = "专业编码", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private String majorCode;
    @Excel(exportName = "专业名称", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private String majorName;
    private int majorOrder;
    private String majorSubjectsLevel;
    private String isObey;
    private Integer isFormal;
    private String majorNumId;
    private String univMajorGroup;
    private String univTestRemark;
    private String majorSubjectsRemark;

    public String convertGetMajorTypeId() {
        String majorType = "";
        switch (majorTypeId) {
            case 1:
                majorType = "文科";
                break;
            case 2:
                majorType = "理科";
                break;
            case 3:
                majorType = "综合改革";
                break;
        }
        return majorType;
    }

    @Basic
    @Column(name = "is_obey")
    public String getIsObey() {
        return isObey;
    }

    public void setIsObey(String isObey) {
        this.isObey = isObey;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "application_id")
    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
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
    @Column(name = "is_formal")
    public Integer getIsFormal() {
        return isFormal;
    }

    public void setIsFormal(Integer isFormal) {
        this.isFormal = isFormal;
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
    @Column(name = "major_type_id")
    public Integer getMajorTypeId() {
        return majorTypeId;
    }

    public void setMajorTypeId(Integer majorTypeId) {
        this.majorTypeId = majorTypeId;
    }

    @Basic
    @Column(name = "batch_id")
    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    @Basic
    @Column(name = "univ_id")
    public String getUnivId() {
        return univId;
    }

    public void setUnivId(String univId) {
        this.univId = univId;
    }

    @Basic
    @Column(name = "univ_code")
    public String getUnivCode() {
        return univCode;
    }

    public void setUnivCode(String univCode) {
        this.univCode = univCode;
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
    @Column(name = "univ_order")
    public int getUnivOrder() {
        return univOrder;
    }

    public void setUnivOrder(Integer univOrder) {
        this.univOrder = univOrder;
    }

    public void setUnivOrder(int univOrder) {
        this.univOrder = univOrder;
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
    @Column(name = "major_num_id")
    public String getMajorNumId() {
        return majorNumId;
    }

    public void setMajorNumId(String majorNumId) {
        this.majorNumId = majorNumId;
    }

    @Basic
    @Column(name = "major_code")
    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
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
    @Column(name = "major_order")
    public int getMajorOrder() {
        return majorOrder;
    }

    public void setMajorOrder(Integer majorOrder) {
        this.majorOrder = majorOrder;
    }

    public void setMajorOrder(int majorOrder) {
        this.majorOrder = majorOrder;
    }

    @Basic
    @Column(name = "major_subjects_level")
    public String getMajorSubjectsLevel() {
        return majorSubjectsLevel;
    }

    public void setMajorSubjectsLevel(String majorSubjectsLevel) {
        this.majorSubjectsLevel = majorSubjectsLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TCeeApplicationsDetail that = (TCeeApplicationsDetail) o;
        return id == that.id &&
                applicationId == that.applicationId &&
                univOrder == that.univOrder &&
                majorOrder == that.majorOrder &&
                Objects.equals(year, that.year) &&
                Objects.equals(isFormal, that.isFormal) &&
                Objects.equals(provinceId, that.provinceId) &&
                Objects.equals(majorTypeId, that.majorTypeId) &&
                Objects.equals(batchId, that.batchId) &&
                Objects.equals(univId, that.univId) &&
                Objects.equals(univCode, that.univCode) &&
                Objects.equals(univName, that.univName) &&
                Objects.equals(majorId, that.majorId) &&
                Objects.equals(majorNumId, that.majorNumId) &&
                Objects.equals(majorCode, that.majorCode) &&
                Objects.equals(majorName, that.majorName) &&
                Objects.equals(majorSubjectsLevel, that.majorSubjectsLevel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, applicationId, year, isFormal, provinceId, majorTypeId, batchId, univId, univCode, univName, univOrder, majorId, majorCode, majorName, majorOrder, majorSubjectsLevel, majorNumId);
    }

    public void setLocal(TCeeEnrollUnivListChoose tCeeEnrollUnivListChoose) {
        this.applicationId = tCeeEnrollUnivListChoose.getApplicationId();
        this.year = tCeeEnrollUnivListChoose.getYear();
        this.provinceId = tCeeEnrollUnivListChoose.getProvinceId();
        this.majorTypeId = tCeeEnrollUnivListChoose.getMajorTypeId();
        this.batchId = tCeeEnrollUnivListChoose.getBatchId();
        this.univId = tCeeEnrollUnivListChoose.getUnivId();
        this.univCode = tCeeEnrollUnivListChoose.getUnivCode();
        this.univName = tCeeEnrollUnivListChoose.getUnivName();
        this.majorId = tCeeEnrollUnivListChoose.getMajorId();
        this.majorCode = tCeeEnrollUnivListChoose.getMajorCode();
        this.majorName = tCeeEnrollUnivListChoose.getMajorName();
        this.univOrder = tCeeEnrollUnivListChoose.getUnivXh();
        this.majorOrder = tCeeEnrollUnivListChoose.getMajorXh();
        this.isObey = "1";
        this.isFormal = 0;
        this.majorNumId = String.valueOf(tCeeEnrollUnivListChoose.getMajorListId());
    }

    @Basic
    @Column(name = "univ_major_group")
    public String getUnivMajorGroup() {
        return univMajorGroup;
    }

    public void setUnivMajorGroup(String univMajorGroup) {
        this.univMajorGroup = univMajorGroup;
    }

    @Basic
    @Column(name = "univ_test_remark")
    public String getUnivTestRemark() {
        return univTestRemark;
    }

    public void setUnivTestRemark(String univTestRemark) {
        this.univTestRemark = univTestRemark;
    }

    @Basic
    @Column(name = "major_subjects_remark")
    public String getMajorSubjectsRemark() {
        return majorSubjectsRemark;
    }

    public void setMajorSubjectsRemark(String majorSubjectsRemark) {
        this.majorSubjectsRemark = majorSubjectsRemark;
    }
}
