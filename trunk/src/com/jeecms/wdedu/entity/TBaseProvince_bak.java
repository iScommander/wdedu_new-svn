package com.jeecms.wdedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_base_province", schema = "wodecareer", catalog = "")
public class TBaseProvince_bak {
    private Integer provinceId;
    private String provinceName;
    private Integer dataBatchYear;
    private Integer dataScoreYear;
    private Integer dataPlanYear;
    private Integer dataEnrollYear;
    private String scoreStartDate;
    private String scoreEndDate;
    private Integer scoreNum;
    private String solutionStartDate;
    private String solutionEndDate;
    private Integer solutionNum;
    private String remark;
    private String state;
    private Integer gradP;
    private Integer gradS;
    private Integer gradK;
    private Integer upperValue;
    private Integer lowerValue;
    private Integer firstScoreValue;
    private Integer secondScoreValue;
    private Integer dataEnrollYearMajor;

    @Id
    @Column(name = "province_id")
    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
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
    @Column(name = "data_batch_year")
    public Integer getDataBatchYear() {
        return dataBatchYear;
    }

    public void setDataBatchYear(Integer dataBatchYear) {
        this.dataBatchYear = dataBatchYear;
    }

    @Basic
    @Column(name = "data_score_year")
    public Integer getDataScoreYear() {
        return dataScoreYear;
    }

    public void setDataScoreYear(Integer dataScoreYear) {
        this.dataScoreYear = dataScoreYear;
    }

    @Basic
    @Column(name = "data_plan_year")
    public Integer getDataPlanYear() {
        return dataPlanYear;
    }

    public void setDataPlanYear(Integer dataPlanYear) {
        this.dataPlanYear = dataPlanYear;
    }

    @Basic
    @Column(name = "data_enroll_year")
    public Integer getDataEnrollYear() {
        return dataEnrollYear;
    }

    public void setDataEnrollYear(Integer dataEnrollYear) {
        this.dataEnrollYear = dataEnrollYear;
    }

    @Basic
    @Column(name = "score_start_date")
    public String getScoreStartDate() {
        return scoreStartDate;
    }

    public void setScoreStartDate(String scoreStartDate) {
        this.scoreStartDate = scoreStartDate;
    }

    @Basic
    @Column(name = "score_end_date")
    public String getScoreEndDate() {
        return scoreEndDate;
    }

    public void setScoreEndDate(String scoreEndDate) {
        this.scoreEndDate = scoreEndDate;
    }

    @Basic
    @Column(name = "score_num")
    public Integer getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(Integer scoreNum) {
        this.scoreNum = scoreNum;
    }

    @Basic
    @Column(name = "solution_start_date")
    public String getSolutionStartDate() {
        return solutionStartDate;
    }

    public void setSolutionStartDate(String solutionStartDate) {
        this.solutionStartDate = solutionStartDate;
    }

    @Basic
    @Column(name = "solution_end_date")
    public String getSolutionEndDate() {
        return solutionEndDate;
    }

    public void setSolutionEndDate(String solutionEndDate) {
        this.solutionEndDate = solutionEndDate;
    }

    @Basic
    @Column(name = "solution_num")
    public Integer getSolutionNum() {
        return solutionNum;
    }

    public void setSolutionNum(Integer solutionNum) {
        this.solutionNum = solutionNum;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "grad_p")
    public Integer getGradP() {
        return gradP;
    }

    public void setGradP(Integer gradP) {
        this.gradP = gradP;
    }

    @Basic
    @Column(name = "grad_s")
    public Integer getGradS() {
        return gradS;
    }

    public void setGradS(Integer gradS) {
        this.gradS = gradS;
    }

    @Basic
    @Column(name = "grad_k")
    public Integer getGradK() {
        return gradK;
    }

    public void setGradK(Integer gradK) {
        this.gradK = gradK;
    }

    @Basic
    @Column(name = "upper_value")
    public Integer getUpperValue() {
        return upperValue;
    }

    public void setUpperValue(Integer upperValue) {
        this.upperValue = upperValue;
    }

    @Basic
    @Column(name = "lower_value")
    public Integer getLowerValue() {
        return lowerValue;
    }

    public void setLowerValue(Integer lowerValue) {
        this.lowerValue = lowerValue;
    }

    @Basic
    @Column(name = "first_score_value")
    public Integer getFirstScoreValue() {
        return firstScoreValue;
    }

    public void setFirstScoreValue(Integer firstScoreValue) {
        this.firstScoreValue = firstScoreValue;
    }

    @Basic
    @Column(name = "second_score_value")
    public Integer getSecondScoreValue() {
        return secondScoreValue;
    }

    public void setSecondScoreValue(Integer secondScoreValue) {
        this.secondScoreValue = secondScoreValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TBaseProvince_bak that = (TBaseProvince_bak) o;

        if (provinceId != null ? !provinceId.equals(that.provinceId) : that.provinceId != null) return false;
        if (provinceName != null ? !provinceName.equals(that.provinceName) : that.provinceName != null) return false;
        if (dataBatchYear != null ? !dataBatchYear.equals(that.dataBatchYear) : that.dataBatchYear != null)
            return false;
        if (dataScoreYear != null ? !dataScoreYear.equals(that.dataScoreYear) : that.dataScoreYear != null)
            return false;
        if (dataPlanYear != null ? !dataPlanYear.equals(that.dataPlanYear) : that.dataPlanYear != null) return false;
        if (dataEnrollYear != null ? !dataEnrollYear.equals(that.dataEnrollYear) : that.dataEnrollYear != null)
            return false;
        if (scoreStartDate != null ? !scoreStartDate.equals(that.scoreStartDate) : that.scoreStartDate != null)
            return false;
        if (scoreEndDate != null ? !scoreEndDate.equals(that.scoreEndDate) : that.scoreEndDate != null) return false;
        if (scoreNum != null ? !scoreNum.equals(that.scoreNum) : that.scoreNum != null) return false;
        if (solutionStartDate != null ? !solutionStartDate.equals(that.solutionStartDate) : that.solutionStartDate != null)
            return false;
        if (solutionEndDate != null ? !solutionEndDate.equals(that.solutionEndDate) : that.solutionEndDate != null)
            return false;
        if (solutionNum != null ? !solutionNum.equals(that.solutionNum) : that.solutionNum != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (gradP != null ? !gradP.equals(that.gradP) : that.gradP != null) return false;
        if (gradS != null ? !gradS.equals(that.gradS) : that.gradS != null) return false;
        if (gradK != null ? !gradK.equals(that.gradK) : that.gradK != null) return false;
        if (upperValue != null ? !upperValue.equals(that.upperValue) : that.upperValue != null) return false;
        if (lowerValue != null ? !lowerValue.equals(that.lowerValue) : that.lowerValue != null) return false;
        if (firstScoreValue != null ? !firstScoreValue.equals(that.firstScoreValue) : that.firstScoreValue != null)
            return false;
        if (secondScoreValue != null ? !secondScoreValue.equals(that.secondScoreValue) : that.secondScoreValue != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = provinceId != null ? provinceId.hashCode() : 0;
        result = 31 * result + (provinceName != null ? provinceName.hashCode() : 0);
        result = 31 * result + (dataBatchYear != null ? dataBatchYear.hashCode() : 0);
        result = 31 * result + (dataScoreYear != null ? dataScoreYear.hashCode() : 0);
        result = 31 * result + (dataPlanYear != null ? dataPlanYear.hashCode() : 0);
        result = 31 * result + (dataEnrollYear != null ? dataEnrollYear.hashCode() : 0);
        result = 31 * result + (scoreStartDate != null ? scoreStartDate.hashCode() : 0);
        result = 31 * result + (scoreEndDate != null ? scoreEndDate.hashCode() : 0);
        result = 31 * result + (scoreNum != null ? scoreNum.hashCode() : 0);
        result = 31 * result + (solutionStartDate != null ? solutionStartDate.hashCode() : 0);
        result = 31 * result + (solutionEndDate != null ? solutionEndDate.hashCode() : 0);
        result = 31 * result + (solutionNum != null ? solutionNum.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (gradP != null ? gradP.hashCode() : 0);
        result = 31 * result + (gradS != null ? gradS.hashCode() : 0);
        result = 31 * result + (gradK != null ? gradK.hashCode() : 0);
        result = 31 * result + (upperValue != null ? upperValue.hashCode() : 0);
        result = 31 * result + (lowerValue != null ? lowerValue.hashCode() : 0);
        result = 31 * result + (firstScoreValue != null ? firstScoreValue.hashCode() : 0);
        result = 31 * result + (secondScoreValue != null ? secondScoreValue.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "data_enroll_year_major")
    public Integer getDataEnrollYearMajor() {
        return dataEnrollYearMajor;
    }

    public void setDataEnrollYearMajor(Integer dataEnrollYearMajor) {
        this.dataEnrollYearMajor = dataEnrollYearMajor;
    }
}
