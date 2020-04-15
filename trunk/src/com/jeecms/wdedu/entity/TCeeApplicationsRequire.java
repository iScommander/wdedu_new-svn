package com.jeecms.wdedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_cee_applications_require", schema = "wodecareer", catalog = "")
public class TCeeApplicationsRequire {
    private Integer id;
    private String orderId;
    private Integer stuUserId;
    private Integer applicationId;
    private Integer batchId;
    private String intentProvinceId;
    private String intentUnivType;
    private Integer is211;
    private Integer is985;
    private Integer isExcellent;
    private Integer isIndependence;
    private Integer isFirstRate;
    private Integer isKeylab;
    private String intentMajorId;
    private Integer advancedUnivNum;
    private Integer stableUnivNum;
    private Integer backwardUnivNum;
    private String intentUniv;
    private String avoidUniv;
    private String intentMajor;
    private String remark;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    @Column(name = "application_id")
    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
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
    @Column(name = "intent_province_id")
    public String getIntentProvinceId() {
        return intentProvinceId;
    }

    public void setIntentProvinceId(String intentProvinceId) {
        this.intentProvinceId = intentProvinceId;
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
    @Column(name = "intent_major_id")
    public String getIntentMajorId() {
        return intentMajorId;
    }

    public void setIntentMajorId(String intentMajorId) {
        this.intentMajorId = intentMajorId;
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
    @Column(name = "intent_univ")
    public String getIntentUniv() {
        return intentUniv;
    }

    public void setIntentUniv(String intentUniv) {
        this.intentUniv = intentUniv;
    }

    @Basic
    @Column(name = "avoid_univ")
    public String getAvoidUniv() {
        return avoidUniv;
    }

    public void setAvoidUniv(String avoidUniv) {
        this.avoidUniv = avoidUniv;
    }

    @Basic
    @Column(name = "intent_major")
    public String getIntentMajor() {
        return intentMajor;
    }

    public void setIntentMajor(String intentMajor) {
        this.intentMajor = intentMajor;
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

        TCeeApplicationsRequire that = (TCeeApplicationsRequire) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (stuUserId != null ? !stuUserId.equals(that.stuUserId) : that.stuUserId != null) return false;
        if (applicationId != null ? !applicationId.equals(that.applicationId) : that.applicationId != null)
            return false;
        if (batchId != null ? !batchId.equals(that.batchId) : that.batchId != null) return false;
        if (intentProvinceId != null ? !intentProvinceId.equals(that.intentProvinceId) : that.intentProvinceId != null)
            return false;
        if (intentUnivType != null ? !intentUnivType.equals(that.intentUnivType) : that.intentUnivType != null)
            return false;
        if (is211 != null ? !is211.equals(that.is211) : that.is211 != null) return false;
        if (is985 != null ? !is985.equals(that.is985) : that.is985 != null) return false;
        if (isExcellent != null ? !isExcellent.equals(that.isExcellent) : that.isExcellent != null) return false;
        if (isIndependence != null ? !isIndependence.equals(that.isIndependence) : that.isIndependence != null)
            return false;
        if (isFirstRate != null ? !isFirstRate.equals(that.isFirstRate) : that.isFirstRate != null) return false;
        if (isKeylab != null ? !isKeylab.equals(that.isKeylab) : that.isKeylab != null) return false;
        if (intentMajorId != null ? !intentMajorId.equals(that.intentMajorId) : that.intentMajorId != null)
            return false;
        if (advancedUnivNum != null ? !advancedUnivNum.equals(that.advancedUnivNum) : that.advancedUnivNum != null)
            return false;
        if (stableUnivNum != null ? !stableUnivNum.equals(that.stableUnivNum) : that.stableUnivNum != null)
            return false;
        if (backwardUnivNum != null ? !backwardUnivNum.equals(that.backwardUnivNum) : that.backwardUnivNum != null)
            return false;
        if (intentUniv != null ? !intentUniv.equals(that.intentUniv) : that.intentUniv != null) return false;
        if (avoidUniv != null ? !avoidUniv.equals(that.avoidUniv) : that.avoidUniv != null) return false;
        if (intentMajor != null ? !intentMajor.equals(that.intentMajor) : that.intentMajor != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (stuUserId != null ? stuUserId.hashCode() : 0);
        result = 31 * result + (applicationId != null ? applicationId.hashCode() : 0);
        result = 31 * result + (batchId != null ? batchId.hashCode() : 0);
        result = 31 * result + (intentProvinceId != null ? intentProvinceId.hashCode() : 0);
        result = 31 * result + (intentUnivType != null ? intentUnivType.hashCode() : 0);
        result = 31 * result + (is211 != null ? is211.hashCode() : 0);
        result = 31 * result + (is985 != null ? is985.hashCode() : 0);
        result = 31 * result + (isExcellent != null ? isExcellent.hashCode() : 0);
        result = 31 * result + (isIndependence != null ? isIndependence.hashCode() : 0);
        result = 31 * result + (isFirstRate != null ? isFirstRate.hashCode() : 0);
        result = 31 * result + (isKeylab != null ? isKeylab.hashCode() : 0);
        result = 31 * result + (intentMajorId != null ? intentMajorId.hashCode() : 0);
        result = 31 * result + (advancedUnivNum != null ? advancedUnivNum.hashCode() : 0);
        result = 31 * result + (stableUnivNum != null ? stableUnivNum.hashCode() : 0);
        result = 31 * result + (backwardUnivNum != null ? backwardUnivNum.hashCode() : 0);
        result = 31 * result + (intentUniv != null ? intentUniv.hashCode() : 0);
        result = 31 * result + (avoidUniv != null ? avoidUniv.hashCode() : 0);
        result = 31 * result + (intentMajor != null ? intentMajor.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
