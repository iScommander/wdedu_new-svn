package com.jeecms.wdedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_cee_enroll_fill_list", schema = "wodecareer", catalog = "" )
public class TCeeEnrollFillList_bak {
    public Integer id;
    public Integer univListId;
    public Integer year;
    public Integer provinceId;
    public Integer majorTypeId;
    public Integer batchId;
    public Integer batchIdOld;
    public String oldBatchName;
    public Integer dataType;
    public String univId;
    public String univCode;
    public String univName;
    public String majorId;
    public String majorId2;
    public String majorCode;
    public String majorName;
    public String majorSubjects;
    public String majorSubjectsLevel;
    public String majorSubjectsRemark;
    public Integer planNum;
    public String planFee;
    public String planSchoolLength;
    public String planRemark;
    public Integer histYear1;
    public Integer histNum1;
    public Integer histLowScore1;
    public Integer histLowRank1;
    public Integer histAveScore1;
    public Integer histAveRank1;
    public Integer histHighScore1;
    public Integer histHighRank1;
    public Integer histYear2;
    public Integer histNum2;
    public Integer histLowScore2;
    public Integer histLowRank2;
    public Integer histAveScore2;
    public Integer histAveRank2;
    public Integer histHighScore2;
    public Integer histHighRank2;
    public Integer histYear3;
    public Integer histNum3;
    public Integer histLowScore3;
    public Integer histLowRank3;
    public Integer histAveScore3;
    public Integer histAveRank3;
    public Integer histHighScore3;
    public Integer histHighRank3;
    public String remark;
    public Integer majorRate;
    public String majorClass;
    public String majorRank;
    public Integer oldUnivListId;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "univ_list_id")
    public Integer getUnivListId() {
        return univListId;
    }

    public void setUnivListId(Integer univListId) {
        this.univListId = univListId;
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
    @Column(name = "batch_id_old")
    public Integer getBatchIdOld() {
        return batchIdOld;
    }

    public void setBatchIdOld(Integer batchIdOld) {
        this.batchIdOld = batchIdOld;
    }

    @Basic
    @Column(name = "old_batch_name")
    public String getOldBatchName() {
        return oldBatchName;
    }

    public void setOldBatchName(String oldBatchName) {
        this.oldBatchName = oldBatchName;
    }

    @Basic
    @Column(name = "data_type")
    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
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
    @Column(name = "major_id")
    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    @Basic
    @Column(name = "major_id2")
    public String getMajorId2() {
        return majorId2;
    }

    public void setMajorId2(String majorId2) {
        this.majorId2 = majorId2;
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
    @Column(name = "major_subjects")
    public String getMajorSubjects() {
        return majorSubjects;
    }

    public void setMajorSubjects(String majorSubjects) {
        this.majorSubjects = majorSubjects;
    }

    @Basic
    @Column(name = "major_subjects_level")
    public String getMajorSubjectsLevel() {
        return majorSubjectsLevel;
    }

    public void setMajorSubjectsLevel(String majorSubjectsLevel) {
        this.majorSubjectsLevel = majorSubjectsLevel;
    }

    @Basic
    @Column(name = "major_subjects_remark")
    public String getMajorSubjectsRemark() {
        return majorSubjectsRemark;
    }

    public void setMajorSubjectsRemark(String majorSubjectsRemark) {
        this.majorSubjectsRemark = majorSubjectsRemark;
    }

    @Basic
    @Column(name = "plan_num")
    public Integer getPlanNum() {
        return planNum;
    }

    public void setPlanNum(Integer planNum) {
        this.planNum = planNum;
    }

    @Basic
    @Column(name = "plan_fee")
    public String getPlanFee() {
        return planFee;
    }

    public void setPlanFee(String planFee) {
        this.planFee = planFee;
    }

    @Basic
    @Column(name = "plan_school_length")
    public String getPlanSchoolLength() {
        return planSchoolLength;
    }

    public void setPlanSchoolLength(String planSchoolLength) {
        this.planSchoolLength = planSchoolLength;
    }

    @Basic
    @Column(name = "plan_remark")
    public String getPlanRemark() {
        return planRemark;
    }

    public void setPlanRemark(String planRemark) {
        this.planRemark = planRemark;
    }

    @Basic
    @Column(name = "hist_year1")
    public Integer getHistYear1() {
        return histYear1;
    }

    public void setHistYear1(Integer histYear1) {
        this.histYear1 = histYear1;
    }

    @Basic
    @Column(name = "hist_num1")
    public Integer getHistNum1() {
        return histNum1;
    }

    public void setHistNum1(Integer histNum1) {
        this.histNum1 = histNum1;
    }

    @Basic
    @Column(name = "hist_low_score1")
    public Integer getHistLowScore1() {
        return histLowScore1;
    }

    public void setHistLowScore1(Integer histLowScore1) {
        this.histLowScore1 = histLowScore1;
    }

    @Basic
    @Column(name = "hist_low_rank1")
    public Integer getHistLowRank1() {
        return histLowRank1;
    }

    public void setHistLowRank1(Integer histLowRank1) {
        this.histLowRank1 = histLowRank1;
    }

    @Basic
    @Column(name = "hist_ave_score1")
    public Integer getHistAveScore1() {
        return histAveScore1;
    }

    public void setHistAveScore1(Integer histAveScore1) {
        this.histAveScore1 = histAveScore1;
    }

    @Basic
    @Column(name = "hist_ave_rank1")
    public Integer getHistAveRank1() {
        return histAveRank1;
    }

    public void setHistAveRank1(Integer histAveRank1) {
        this.histAveRank1 = histAveRank1;
    }

    @Basic
    @Column(name = "hist_high_score1")
    public Integer getHistHighScore1() {
        return histHighScore1;
    }

    public void setHistHighScore1(Integer histHighScore1) {
        this.histHighScore1 = histHighScore1;
    }

    @Basic
    @Column(name = "hist_high_rank1")
    public Integer getHistHighRank1() {
        return histHighRank1;
    }

    public void setHistHighRank1(Integer histHighRank1) {
        this.histHighRank1 = histHighRank1;
    }

    @Basic
    @Column(name = "hist_year2")
    public Integer getHistYear2() {
        return histYear2;
    }

    public void setHistYear2(Integer histYear2) {
        this.histYear2 = histYear2;
    }

    @Basic
    @Column(name = "hist_num2")
    public Integer getHistNum2() {
        return histNum2;
    }

    public void setHistNum2(Integer histNum2) {
        this.histNum2 = histNum2;
    }

    @Basic
    @Column(name = "hist_low_score2")
    public Integer getHistLowScore2() {
        return histLowScore2;
    }

    public void setHistLowScore2(Integer histLowScore2) {
        this.histLowScore2 = histLowScore2;
    }

    @Basic
    @Column(name = "hist_low_rank2")
    public Integer getHistLowRank2() {
        return histLowRank2;
    }

    public void setHistLowRank2(Integer histLowRank2) {
        this.histLowRank2 = histLowRank2;
    }

    @Basic
    @Column(name = "hist_ave_score2")
    public Integer getHistAveScore2() {
        return histAveScore2;
    }

    public void setHistAveScore2(Integer histAveScore2) {
        this.histAveScore2 = histAveScore2;
    }

    @Basic
    @Column(name = "hist_ave_rank2")
    public Integer getHistAveRank2() {
        return histAveRank2;
    }

    public void setHistAveRank2(Integer histAveRank2) {
        this.histAveRank2 = histAveRank2;
    }

    @Basic
    @Column(name = "hist_high_score2")
    public Integer getHistHighScore2() {
        return histHighScore2;
    }

    public void setHistHighScore2(Integer histHighScore2) {
        this.histHighScore2 = histHighScore2;
    }

    @Basic
    @Column(name = "hist_high_rank2")
    public Integer getHistHighRank2() {
        return histHighRank2;
    }

    public void setHistHighRank2(Integer histHighRank2) {
        this.histHighRank2 = histHighRank2;
    }

    @Basic
    @Column(name = "hist_year3")
    public Integer getHistYear3() {
        return histYear3;
    }

    public void setHistYear3(Integer histYear3) {
        this.histYear3 = histYear3;
    }

    @Basic
    @Column(name = "hist_num3")
    public Integer getHistNum3() {
        return histNum3;
    }

    public void setHistNum3(Integer histNum3) {
        this.histNum3 = histNum3;
    }

    @Basic
    @Column(name = "hist_low_score3")
    public Integer getHistLowScore3() {
        return histLowScore3;
    }

    public void setHistLowScore3(Integer histLowScore3) {
        this.histLowScore3 = histLowScore3;
    }

    @Basic
    @Column(name = "hist_low_rank3")
    public Integer getHistLowRank3() {
        return histLowRank3;
    }

    public void setHistLowRank3(Integer histLowRank3) {
        this.histLowRank3 = histLowRank3;
    }

    @Basic
    @Column(name = "hist_ave_score3")
    public Integer getHistAveScore3() {
        return histAveScore3;
    }

    public void setHistAveScore3(Integer histAveScore3) {
        this.histAveScore3 = histAveScore3;
    }

    @Basic
    @Column(name = "hist_ave_rank3")
    public Integer getHistAveRank3() {
        return histAveRank3;
    }

    public void setHistAveRank3(Integer histAveRank3) {
        this.histAveRank3 = histAveRank3;
    }

    @Basic
    @Column(name = "hist_high_score3")
    public Integer getHistHighScore3() {
        return histHighScore3;
    }

    public void setHistHighScore3(Integer histHighScore3) {
        this.histHighScore3 = histHighScore3;
    }

    @Basic
    @Column(name = "hist_high_rank3")
    public Integer getHistHighRank3() {
        return histHighRank3;
    }

    public void setHistHighRank3(Integer histHighRank3) {
        this.histHighRank3 = histHighRank3;
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
    @Column(name = "major_rate")
    public Integer getMajorRate() {
        return majorRate;
    }

    public void setMajorRate(Integer majorRate) {
        this.majorRate = majorRate;
    }

    @Basic
    @Column(name = "major_class")
    public String getMajorClass() {
        return majorClass;
    }

    public void setMajorClass(String majorClass) {
        this.majorClass = majorClass;
    }

    @Basic
    @Column(name = "major_rank")
    public String getMajorRank() {
        return majorRank;
    }

    public void setMajorRank(String majorRank) {
        this.majorRank = majorRank;
    }

    @Basic
    @Column(name = "old_univ_list_id")
    public Integer getOldUnivListId() {
        return oldUnivListId;
    }

    public void setOldUnivListId(Integer oldUnivListId) {
        this.oldUnivListId = oldUnivListId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TCeeEnrollFillList_bak that = (TCeeEnrollFillList_bak) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (univListId != null ? !univListId.equals(that.univListId) : that.univListId != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (provinceId != null ? !provinceId.equals(that.provinceId) : that.provinceId != null) return false;
        if (majorTypeId != null ? !majorTypeId.equals(that.majorTypeId) : that.majorTypeId != null) return false;
        if (batchId != null ? !batchId.equals(that.batchId) : that.batchId != null) return false;
        if (batchIdOld != null ? !batchIdOld.equals(that.batchIdOld) : that.batchIdOld != null) return false;
        if (oldBatchName != null ? !oldBatchName.equals(that.oldBatchName) : that.oldBatchName != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (univId != null ? !univId.equals(that.univId) : that.univId != null) return false;
        if (univCode != null ? !univCode.equals(that.univCode) : that.univCode != null) return false;
        if (univName != null ? !univName.equals(that.univName) : that.univName != null) return false;
        if (majorId != null ? !majorId.equals(that.majorId) : that.majorId != null) return false;
        if (majorId2 != null ? !majorId2.equals(that.majorId2) : that.majorId2 != null) return false;
        if (majorCode != null ? !majorCode.equals(that.majorCode) : that.majorCode != null) return false;
        if (majorName != null ? !majorName.equals(that.majorName) : that.majorName != null) return false;
        if (majorSubjects != null ? !majorSubjects.equals(that.majorSubjects) : that.majorSubjects != null)
            return false;
        if (majorSubjectsLevel != null ? !majorSubjectsLevel.equals(that.majorSubjectsLevel) : that.majorSubjectsLevel != null)
            return false;
        if (majorSubjectsRemark != null ? !majorSubjectsRemark.equals(that.majorSubjectsRemark) : that.majorSubjectsRemark != null)
            return false;
        if (planNum != null ? !planNum.equals(that.planNum) : that.planNum != null) return false;
        if (planFee != null ? !planFee.equals(that.planFee) : that.planFee != null) return false;
        if (planSchoolLength != null ? !planSchoolLength.equals(that.planSchoolLength) : that.planSchoolLength != null)
            return false;
        if (planRemark != null ? !planRemark.equals(that.planRemark) : that.planRemark != null) return false;
        if (histYear1 != null ? !histYear1.equals(that.histYear1) : that.histYear1 != null) return false;
        if (histNum1 != null ? !histNum1.equals(that.histNum1) : that.histNum1 != null) return false;
        if (histLowScore1 != null ? !histLowScore1.equals(that.histLowScore1) : that.histLowScore1 != null)
            return false;
        if (histLowRank1 != null ? !histLowRank1.equals(that.histLowRank1) : that.histLowRank1 != null) return false;
        if (histAveScore1 != null ? !histAveScore1.equals(that.histAveScore1) : that.histAveScore1 != null)
            return false;
        if (histAveRank1 != null ? !histAveRank1.equals(that.histAveRank1) : that.histAveRank1 != null) return false;
        if (histHighScore1 != null ? !histHighScore1.equals(that.histHighScore1) : that.histHighScore1 != null)
            return false;
        if (histHighRank1 != null ? !histHighRank1.equals(that.histHighRank1) : that.histHighRank1 != null)
            return false;
        if (histYear2 != null ? !histYear2.equals(that.histYear2) : that.histYear2 != null) return false;
        if (histNum2 != null ? !histNum2.equals(that.histNum2) : that.histNum2 != null) return false;
        if (histLowScore2 != null ? !histLowScore2.equals(that.histLowScore2) : that.histLowScore2 != null)
            return false;
        if (histLowRank2 != null ? !histLowRank2.equals(that.histLowRank2) : that.histLowRank2 != null) return false;
        if (histAveScore2 != null ? !histAveScore2.equals(that.histAveScore2) : that.histAveScore2 != null)
            return false;
        if (histAveRank2 != null ? !histAveRank2.equals(that.histAveRank2) : that.histAveRank2 != null) return false;
        if (histHighScore2 != null ? !histHighScore2.equals(that.histHighScore2) : that.histHighScore2 != null)
            return false;
        if (histHighRank2 != null ? !histHighRank2.equals(that.histHighRank2) : that.histHighRank2 != null)
            return false;
        if (histYear3 != null ? !histYear3.equals(that.histYear3) : that.histYear3 != null) return false;
        if (histNum3 != null ? !histNum3.equals(that.histNum3) : that.histNum3 != null) return false;
        if (histLowScore3 != null ? !histLowScore3.equals(that.histLowScore3) : that.histLowScore3 != null)
            return false;
        if (histLowRank3 != null ? !histLowRank3.equals(that.histLowRank3) : that.histLowRank3 != null) return false;
        if (histAveScore3 != null ? !histAveScore3.equals(that.histAveScore3) : that.histAveScore3 != null)
            return false;
        if (histAveRank3 != null ? !histAveRank3.equals(that.histAveRank3) : that.histAveRank3 != null) return false;
        if (histHighScore3 != null ? !histHighScore3.equals(that.histHighScore3) : that.histHighScore3 != null)
            return false;
        if (histHighRank3 != null ? !histHighRank3.equals(that.histHighRank3) : that.histHighRank3 != null)
            return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (majorRate != null ? !majorRate.equals(that.majorRate) : that.majorRate != null) return false;
        if (majorClass != null ? !majorClass.equals(that.majorClass) : that.majorClass != null) return false;
        if (majorRank != null ? !majorRank.equals(that.majorRank) : that.majorRank != null) return false;
        if (oldUnivListId != null ? !oldUnivListId.equals(that.oldUnivListId) : that.oldUnivListId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (univListId != null ? univListId.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (provinceId != null ? provinceId.hashCode() : 0);
        result = 31 * result + (majorTypeId != null ? majorTypeId.hashCode() : 0);
        result = 31 * result + (batchId != null ? batchId.hashCode() : 0);
        result = 31 * result + (batchIdOld != null ? batchIdOld.hashCode() : 0);
        result = 31 * result + (oldBatchName != null ? oldBatchName.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (univId != null ? univId.hashCode() : 0);
        result = 31 * result + (univCode != null ? univCode.hashCode() : 0);
        result = 31 * result + (univName != null ? univName.hashCode() : 0);
        result = 31 * result + (majorId != null ? majorId.hashCode() : 0);
        result = 31 * result + (majorId2 != null ? majorId2.hashCode() : 0);
        result = 31 * result + (majorCode != null ? majorCode.hashCode() : 0);
        result = 31 * result + (majorName != null ? majorName.hashCode() : 0);
        result = 31 * result + (majorSubjects != null ? majorSubjects.hashCode() : 0);
        result = 31 * result + (majorSubjectsLevel != null ? majorSubjectsLevel.hashCode() : 0);
        result = 31 * result + (majorSubjectsRemark != null ? majorSubjectsRemark.hashCode() : 0);
        result = 31 * result + (planNum != null ? planNum.hashCode() : 0);
        result = 31 * result + (planFee != null ? planFee.hashCode() : 0);
        result = 31 * result + (planSchoolLength != null ? planSchoolLength.hashCode() : 0);
        result = 31 * result + (planRemark != null ? planRemark.hashCode() : 0);
        result = 31 * result + (histYear1 != null ? histYear1.hashCode() : 0);
        result = 31 * result + (histNum1 != null ? histNum1.hashCode() : 0);
        result = 31 * result + (histLowScore1 != null ? histLowScore1.hashCode() : 0);
        result = 31 * result + (histLowRank1 != null ? histLowRank1.hashCode() : 0);
        result = 31 * result + (histAveScore1 != null ? histAveScore1.hashCode() : 0);
        result = 31 * result + (histAveRank1 != null ? histAveRank1.hashCode() : 0);
        result = 31 * result + (histHighScore1 != null ? histHighScore1.hashCode() : 0);
        result = 31 * result + (histHighRank1 != null ? histHighRank1.hashCode() : 0);
        result = 31 * result + (histYear2 != null ? histYear2.hashCode() : 0);
        result = 31 * result + (histNum2 != null ? histNum2.hashCode() : 0);
        result = 31 * result + (histLowScore2 != null ? histLowScore2.hashCode() : 0);
        result = 31 * result + (histLowRank2 != null ? histLowRank2.hashCode() : 0);
        result = 31 * result + (histAveScore2 != null ? histAveScore2.hashCode() : 0);
        result = 31 * result + (histAveRank2 != null ? histAveRank2.hashCode() : 0);
        result = 31 * result + (histHighScore2 != null ? histHighScore2.hashCode() : 0);
        result = 31 * result + (histHighRank2 != null ? histHighRank2.hashCode() : 0);
        result = 31 * result + (histYear3 != null ? histYear3.hashCode() : 0);
        result = 31 * result + (histNum3 != null ? histNum3.hashCode() : 0);
        result = 31 * result + (histLowScore3 != null ? histLowScore3.hashCode() : 0);
        result = 31 * result + (histLowRank3 != null ? histLowRank3.hashCode() : 0);
        result = 31 * result + (histAveScore3 != null ? histAveScore3.hashCode() : 0);
        result = 31 * result + (histAveRank3 != null ? histAveRank3.hashCode() : 0);
        result = 31 * result + (histHighScore3 != null ? histHighScore3.hashCode() : 0);
        result = 31 * result + (histHighRank3 != null ? histHighRank3.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (majorRate != null ? majorRate.hashCode() : 0);
        result = 31 * result + (majorClass != null ? majorClass.hashCode() : 0);
        result = 31 * result + (majorRank != null ? majorRank.hashCode() : 0);
        result = 31 * result + (oldUnivListId != null ? oldUnivListId.hashCode() : 0);
        return result;
    }
}
