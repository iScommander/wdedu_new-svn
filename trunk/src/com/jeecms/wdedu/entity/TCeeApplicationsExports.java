package com.jeecms.wdedu.entity;

import com.utils.excel.Excel;

import javax.persistence.*;
import java.util.Objects;

/**
 * Copyright (C),
 * FileName:
 * Author:
 * Date:
 * Description: //模块目的、功能描述
 * History: //修改记录
 * &lt;author&gt;      &lt;time&gt;      &lt;version&gt;    &lt;desc&gt;
 * 修改人姓名             修改时间            版本号                  描述
 **/
@Entity
@Table(name = "t_cee_applications_exports", schema = "wodecareer", catalog = "")
public class TCeeApplicationsExports {
    private int id;
    private int applicationId;
    private String applicationName;
    private Integer year;
    private String provinceName;
    private String majorTypeName;
    @Excel(exportName = "批次", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private String batchName;
    @Excel(exportName = "志愿名称", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private String zhiyuanName;
    @Excel(exportName = "风险设置", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private String dataTypeName;
    @Excel(exportName = "大学/专业", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private String univOrMajorAlias;
//    @Excel(exportName = "内部ID", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private String univOrMajorId;
    @Excel(exportName = "院校/招生代码", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private String univOrMajorCode;
    @Excel(exportName = "大学/专业名称", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private String univOrMajorName;
    @Excel(exportName = "招生人数", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private String univOrMajorPlan;
    @Excel(exportName = "2018年录取最低分/排名", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private String enrollYear1;
    @Excel(exportName = "2017年录取最低分/排名", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private String enrollYear2;
    @Excel(exportName = "2016年录取最低分/排名", exportConvertSign = 0, exportFieldWidth = 10, importConvertSign = 0, showTitle = 1)
    private String enrollYear3;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "application_id")
    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    @Basic
    @Column(name = "application_name")
    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
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
    @Column(name = "province_name")
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Basic
    @Column(name = "major_type_name")
    public String getMajorTypeName() {
        return majorTypeName;
    }

    public void setMajorTypeName(String majorTypeName) {
        this.majorTypeName = majorTypeName;
    }

    @Basic
    @Column(name = "batch_name")
    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    @Basic
    @Column(name = "zhiyuan_name")
    public String getZhiyuanName() {
        return zhiyuanName;
    }

    public void setZhiyuanName(String zhiyuanName) {
        this.zhiyuanName = zhiyuanName;
    }

    @Basic
    @Column(name = "data_type_name")
    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    @Basic
    @Column(name = "univOrMajor_alias")
    public String getUnivOrMajorAlias() {
        return univOrMajorAlias;
    }

    public void setUnivOrMajorAlias(String univOrMajorAlias) {
        this.univOrMajorAlias = univOrMajorAlias;
    }

    @Basic
    @Column(name = "univOrMajor_id")
    public String getUnivOrMajorId() {
        return univOrMajorId;
    }

    public void setUnivOrMajorId(String univOrMajorId) {
        this.univOrMajorId = univOrMajorId;
    }

    @Basic
    @Column(name = "univOrMajor_code")
    public String getUnivOrMajorCode() {
        return univOrMajorCode;
    }

    public void setUnivOrMajorCode(String univOrMajorCode) {
        this.univOrMajorCode = univOrMajorCode;
    }

    @Basic
    @Column(name = "univOrMajor_name")
    public String getUnivOrMajorName() {
        return univOrMajorName;
    }

    public void setUnivOrMajorName(String univOrMajorName) {
        this.univOrMajorName = univOrMajorName;
    }

    @Basic
    @Column(name = "univOrMajor_plan")
    public String getUnivOrMajorPlan() {
        return univOrMajorPlan;
    }

    public void setUnivOrMajorPlan(String univOrMajorPlan) {
        this.univOrMajorPlan = univOrMajorPlan;
    }

    @Basic
    @Column(name = "enroll_year1")
    public String getEnrollYear1() {
        return enrollYear1;
    }

    public void setEnrollYear1(String enrollYear1) {
        this.enrollYear1 = enrollYear1;
    }

    @Basic
    @Column(name = "enroll_year2")
    public String getEnrollYear2() {
        return enrollYear2;
    }

    public void setEnrollYear2(String enrollYear2) {
        this.enrollYear2 = enrollYear2;
    }

    @Basic
    @Column(name = "enroll_year3")
    public String getEnrollYear3() {
        return enrollYear3;
    }

    public void setEnrollYear3(String enrollYear3) {
        this.enrollYear3 = enrollYear3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TCeeApplicationsExports that = (TCeeApplicationsExports) o;
        return id == that.id &&
                Objects.equals( applicationId, that.applicationId ) &&
                Objects.equals( applicationName, that.applicationName ) &&
                Objects.equals( year, that.year ) &&
                Objects.equals( provinceName, that.provinceName ) &&
                Objects.equals( majorTypeName, that.majorTypeName ) &&
                Objects.equals( batchName, that.batchName ) &&
                Objects.equals( zhiyuanName, that.zhiyuanName ) &&
                Objects.equals( dataTypeName, that.dataTypeName ) &&
                Objects.equals( univOrMajorAlias, that.univOrMajorAlias ) &&
                Objects.equals( univOrMajorId, that.univOrMajorId ) &&
                Objects.equals( univOrMajorCode, that.univOrMajorCode ) &&
                Objects.equals( univOrMajorName, that.univOrMajorName ) &&
                Objects.equals( univOrMajorPlan, that.univOrMajorPlan ) &&
                Objects.equals( enrollYear1, that.enrollYear1 ) &&
                Objects.equals( enrollYear2, that.enrollYear2 ) &&
                Objects.equals( enrollYear3, that.enrollYear3 );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, applicationId, applicationName, year, provinceName, majorTypeName, batchName, zhiyuanName, dataTypeName, univOrMajorAlias, univOrMajorId, univOrMajorCode, univOrMajorName, univOrMajorPlan, enrollYear1, enrollYear2, enrollYear3 );
    }
}
