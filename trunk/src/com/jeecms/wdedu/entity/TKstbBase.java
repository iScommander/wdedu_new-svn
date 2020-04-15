package com.jeecms.wdedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_kstb_base", schema = "wodecareer", catalog = "")
public class TKstbBase {
    private int id;
    private String orderId;
    private Integer stuUserId;
    private String intentProvinceId;
    private String avoidProvinceId;
    private String intentUnivType;
    private String avoidUnivType;
    private Integer is211;
    private Integer is985;
    private Integer isDefence;
    private Integer isExcellent;
    private Integer isIndependence;
    private Integer isFirstRate;
    private Integer isKeylab;
    private String favoriteUnivNames;
    private String intentMajorId;
    private String avoidMajorId;
    private Integer cwbBatchId;
    private Integer advancedUnivNum;
    private Integer stableUnivNum;
    private Integer backwardUnivNum;
    private Integer isChooseOrder;
    private String chooseOrder;
    private String physicalExamination;
    private String score;
    private String majorSubjects;
    private String majorSubjectsLevel;
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
    @Column(name = "order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "stu_user_id")
    public Integer getStuUserId() {
        return stuUserId;
    }

    public void setStuUserId(Integer stuUserId) {
        this.stuUserId = stuUserId;
    }

    @Basic
    @Column(name = "intent_province_id")
    public String getIntentProvinceId() {
        return intentProvinceId;
    }

    public void setIntentProvinceId(String intentProvinceId) {
        this.intentProvinceId = intentProvinceId;
    }

    @Basic
    @Column(name = "avoid_province_id")
    public String getAvoidProvinceId() {
        return avoidProvinceId;
    }

    public void setAvoidProvinceId(String avoidProvinceId) {
        this.avoidProvinceId = avoidProvinceId;
    }

    @Basic
    @Column(name = "intent_univ_type")
    public String getIntentUnivType() {
        return intentUnivType;
    }

    public void setIntentUnivType(String intentUnivType) {
        this.intentUnivType = intentUnivType;
    }

    @Basic
    @Column(name = "avoid_univ_type")
    public String getAvoidUnivType() {
        return avoidUnivType;
    }

    public void setAvoidUnivType(String avoidUnivType) {
        this.avoidUnivType = avoidUnivType;
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
    public Integer getIsIndependence() {
        return isIndependence;
    }

    public void setIsIndependence(Integer isIndependence) {
        this.isIndependence = isIndependence;
    }

    @Basic
    @Column(name = "isFirstRate")
    public Integer getIsFirstRate() {
        return isFirstRate;
    }

    public void setIsFirstRate(Integer isFirstRate) {
        this.isFirstRate = isFirstRate;
    }

    @Basic
    @Column(name = "isKeylab")
    public Integer getIsKeylab() {
        return isKeylab;
    }

    public void setIsKeylab(Integer isKeylab) {
        this.isKeylab = isKeylab;
    }

    @Basic
    @Column(name = "favorite_univ_names")
    public String getFavoriteUnivNames() {
        return favoriteUnivNames;
    }

    public void setFavoriteUnivNames(String favoriteUnivNames) {
        this.favoriteUnivNames = favoriteUnivNames;
    }

    @Basic
    @Column(name = "intent_major_id")
    public String getIntentMajorId() {
        return intentMajorId;
    }

    public void setIntentMajorId(String intentMajorId) {
        this.intentMajorId = intentMajorId;
    }

    @Basic
    @Column(name = "avoid_major_id")
    public String getAvoidMajorId() {
        return avoidMajorId;
    }

    public void setAvoidMajorId(String avoidMajorId) {
        this.avoidMajorId = avoidMajorId;
    }

    @Basic
    @Column(name = "cwb_batch_id")
    public Integer getCwbBatchId() {
        return cwbBatchId;
    }

    public void setCwbBatchId(Integer cwbBatchId) {
        this.cwbBatchId = cwbBatchId;
    }

    @Basic
    @Column(name = "advanced_univ_num")
    public Integer getAdvancedUnivNum() {
        return advancedUnivNum;
    }

    public void setAdvancedUnivNum(Integer advancedUnivNum) {
        this.advancedUnivNum = advancedUnivNum;
    }

    @Basic
    @Column(name = "stable_univ_num")
    public Integer getStableUnivNum() {
        return stableUnivNum;
    }

    public void setStableUnivNum(Integer stableUnivNum) {
        this.stableUnivNum = stableUnivNum;
    }

    @Basic
    @Column(name = "backward_univ_num")
    public Integer getBackwardUnivNum() {
        return backwardUnivNum;
    }

    public void setBackwardUnivNum(Integer backwardUnivNum) {
        this.backwardUnivNum = backwardUnivNum;
    }

    @Basic
    @Column(name = "is_choose_order")
    public Integer getIsChooseOrder() {
        return isChooseOrder;
    }

    public void setIsChooseOrder(Integer isChooseOrder) {
        this.isChooseOrder = isChooseOrder;
    }

    @Basic
    @Column(name = "choose_order")
    public String getChooseOrder() {
        return chooseOrder;
    }

    public void setChooseOrder(String chooseOrder) {
        this.chooseOrder = chooseOrder;
    }

    @Basic
    @Column(name = "physical_examination")
    public String getPhysicalExamination() {
        return physicalExamination;
    }

    public void setPhysicalExamination(String physicalExamination) {
        this.physicalExamination = physicalExamination;
    }

    @Basic
    @Column(name = "score")
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
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
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TKstbBase kstbBase = (TKstbBase) o;

        if (id != kstbBase.id) return false;
        if (orderId != null ? !orderId.equals(kstbBase.orderId) : kstbBase.orderId != null) return false;
        if (stuUserId != null ? !stuUserId.equals(kstbBase.stuUserId) : kstbBase.stuUserId != null) return false;
        if (intentProvinceId != null ? !intentProvinceId.equals(kstbBase.intentProvinceId) : kstbBase.intentProvinceId != null)
            return false;
        if (avoidProvinceId != null ? !avoidProvinceId.equals(kstbBase.avoidProvinceId) : kstbBase.avoidProvinceId != null)
            return false;
        if (intentUnivType != null ? !intentUnivType.equals(kstbBase.intentUnivType) : kstbBase.intentUnivType != null)
            return false;
        if (avoidUnivType != null ? !avoidUnivType.equals(kstbBase.avoidUnivType) : kstbBase.avoidUnivType != null)
            return false;
        if (is211 != null ? !is211.equals(kstbBase.is211) : kstbBase.is211 != null) return false;
        if (is985 != null ? !is985.equals(kstbBase.is985) : kstbBase.is985 != null) return false;
        if (isDefence != null ? !isDefence.equals(kstbBase.isDefence) : kstbBase.isDefence != null) return false;
        if (isExcellent != null ? !isExcellent.equals(kstbBase.isExcellent) : kstbBase.isExcellent != null)
            return false;
        if (isIndependence != null ? !isIndependence.equals(kstbBase.isIndependence) : kstbBase.isIndependence != null)
            return false;
        if (isFirstRate != null ? !isFirstRate.equals(kstbBase.isFirstRate) : kstbBase.isFirstRate != null)
            return false;
        if (isKeylab != null ? !isKeylab.equals(kstbBase.isKeylab) : kstbBase.isKeylab != null) return false;
        if (favoriteUnivNames != null ? !favoriteUnivNames.equals(kstbBase.favoriteUnivNames) : kstbBase.favoriteUnivNames != null)
            return false;
        if (intentMajorId != null ? !intentMajorId.equals(kstbBase.intentMajorId) : kstbBase.intentMajorId != null)
            return false;
        if (avoidMajorId != null ? !avoidMajorId.equals(kstbBase.avoidMajorId) : kstbBase.avoidMajorId != null)
            return false;
        if (cwbBatchId != null ? !cwbBatchId.equals(kstbBase.cwbBatchId) : kstbBase.cwbBatchId != null) return false;
        if (advancedUnivNum != null ? !advancedUnivNum.equals(kstbBase.advancedUnivNum) : kstbBase.advancedUnivNum != null)
            return false;
        if (stableUnivNum != null ? !stableUnivNum.equals(kstbBase.stableUnivNum) : kstbBase.stableUnivNum != null)
            return false;
        if (backwardUnivNum != null ? !backwardUnivNum.equals(kstbBase.backwardUnivNum) : kstbBase.backwardUnivNum != null)
            return false;
        if (isChooseOrder != null ? !isChooseOrder.equals(kstbBase.isChooseOrder) : kstbBase.isChooseOrder != null)
            return false;
        if (chooseOrder != null ? !chooseOrder.equals(kstbBase.chooseOrder) : kstbBase.chooseOrder != null)
            return false;
        if (physicalExamination != null ? !physicalExamination.equals(kstbBase.physicalExamination) : kstbBase.physicalExamination != null)
            return false;
        if (score != null ? !score.equals(kstbBase.score) : kstbBase.score != null) return false;
        if (majorSubjects != null ? !majorSubjects.equals(kstbBase.majorSubjects) : kstbBase.majorSubjects != null)
            return false;
        if (majorSubjectsLevel != null ? !majorSubjectsLevel.equals(kstbBase.majorSubjectsLevel) : kstbBase.majorSubjectsLevel != null)
            return false;
        if (remark != null ? !remark.equals(kstbBase.remark) : kstbBase.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (stuUserId != null ? stuUserId.hashCode() : 0);
        result = 31 * result + (intentProvinceId != null ? intentProvinceId.hashCode() : 0);
        result = 31 * result + (avoidProvinceId != null ? avoidProvinceId.hashCode() : 0);
        result = 31 * result + (intentUnivType != null ? intentUnivType.hashCode() : 0);
        result = 31 * result + (avoidUnivType != null ? avoidUnivType.hashCode() : 0);
        result = 31 * result + (is211 != null ? is211.hashCode() : 0);
        result = 31 * result + (is985 != null ? is985.hashCode() : 0);
        result = 31 * result + (isDefence != null ? isDefence.hashCode() : 0);
        result = 31 * result + (isExcellent != null ? isExcellent.hashCode() : 0);
        result = 31 * result + (isIndependence != null ? isIndependence.hashCode() : 0);
        result = 31 * result + (isFirstRate != null ? isFirstRate.hashCode() : 0);
        result = 31 * result + (isKeylab != null ? isKeylab.hashCode() : 0);
        result = 31 * result + (favoriteUnivNames != null ? favoriteUnivNames.hashCode() : 0);
        result = 31 * result + (intentMajorId != null ? intentMajorId.hashCode() : 0);
        result = 31 * result + (avoidMajorId != null ? avoidMajorId.hashCode() : 0);
        result = 31 * result + (cwbBatchId != null ? cwbBatchId.hashCode() : 0);
        result = 31 * result + (advancedUnivNum != null ? advancedUnivNum.hashCode() : 0);
        result = 31 * result + (stableUnivNum != null ? stableUnivNum.hashCode() : 0);
        result = 31 * result + (backwardUnivNum != null ? backwardUnivNum.hashCode() : 0);
        result = 31 * result + (isChooseOrder != null ? isChooseOrder.hashCode() : 0);
        result = 31 * result + (chooseOrder != null ? chooseOrder.hashCode() : 0);
        result = 31 * result + (physicalExamination != null ? physicalExamination.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (majorSubjects != null ? majorSubjects.hashCode() : 0);
        result = 31 * result + (majorSubjectsLevel != null ? majorSubjectsLevel.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
