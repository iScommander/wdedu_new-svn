package com.wdedu.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "t_data_activity_detail", schema = "wodecareer", catalog = "")
public class TDataActivityDetailEntity {
    private int id;
    private int activityId;
    private String activityType;
    private String activityTitle;
    private String activitySmallImg;
    private String activitySpecialImg;
    private String activityFirstImg;
    private String activityStartTime;
    private String activityEndTime;
    private String activityAddress;
    private String activityPeoples;
    private String activityContent;
    private String activityRegion;
    private String activityRegionsponsor;
    private String activityRegioncharge;
    private String activityRegioncontact;
    private Time activityDatetime;
    private Integer activityIsplay;
    private Integer activityProductId;
    private Double activityPrice;
    private Double activityDiscount;
    private String branchCompanyId;
    private String remarks;
    private Integer userId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "activity_id")
    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "activity_type")
    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    @Basic
    @Column(name = "activity_title")
    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    @Basic
    @Column(name = "activity_small_img")
    public String getActivitySmallImg() {
        return activitySmallImg;
    }

    public void setActivitySmallImg(String activitySmallImg) {
        this.activitySmallImg = activitySmallImg;
    }

    @Basic
    @Column(name = "activity_special_img")
    public String getActivitySpecialImg() {
        return activitySpecialImg;
    }

    public void setActivitySpecialImg(String activitySpecialImg) {
        this.activitySpecialImg = activitySpecialImg;
    }

    @Basic
    @Column(name = "activity_first_img")
    public String getActivityFirstImg() {
        return activityFirstImg;
    }

    public void setActivityFirstImg(String activityFirstImg) {
        this.activityFirstImg = activityFirstImg;
    }

    @Basic
    @Column(name = "activity_start_time")
    public String getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(String activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    @Basic
    @Column(name = "activity_end_time")
    public String getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(String activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    @Basic
    @Column(name = "activity_address")
    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }

    @Basic
    @Column(name = "activity_peoples")
    public String getActivityPeoples() {
        return activityPeoples;
    }

    public void setActivityPeoples(String activityPeoples) {
        this.activityPeoples = activityPeoples;
    }

    @Basic
    @Column(name = "activity_content")
    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    @Basic
    @Column(name = "activity_region")
    public String getActivityRegion() {
        return activityRegion;
    }

    public void setActivityRegion(String activityRegion) {
        this.activityRegion = activityRegion;
    }

    @Basic
    @Column(name = "activity_regionsponsor")
    public String getActivityRegionsponsor() {
        return activityRegionsponsor;
    }

    public void setActivityRegionsponsor(String activityRegionsponsor) {
        this.activityRegionsponsor = activityRegionsponsor;
    }

    @Basic
    @Column(name = "activity_regioncharge")
    public String getActivityRegioncharge() {
        return activityRegioncharge;
    }

    public void setActivityRegioncharge(String activityRegioncharge) {
        this.activityRegioncharge = activityRegioncharge;
    }

    @Basic
    @Column(name = "activity_regioncontact")
    public String getActivityRegioncontact() {
        return activityRegioncontact;
    }

    public void setActivityRegioncontact(String activityRegioncontact) {
        this.activityRegioncontact = activityRegioncontact;
    }

    @Basic
    @Column(name = "activity_datetime")
    public Time getActivityDatetime() {
        return activityDatetime;
    }

    public void setActivityDatetime(Time activityDatetime) {
        this.activityDatetime = activityDatetime;
    }

    @Basic
    @Column(name = "activity_isplay")
    public Integer getActivityIsplay() {
        return activityIsplay;
    }

    public void setActivityIsplay(Integer activityIsplay) {
        this.activityIsplay = activityIsplay;
    }

    @Basic
    @Column(name = "activity_product_id")
    public Integer getActivityProductId() {
        return activityProductId;
    }

    public void setActivityProductId(Integer activityProductId) {
        this.activityProductId = activityProductId;
    }

    @Basic
    @Column(name = "activity_price")
    public Double getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(Double activityPrice) {
        this.activityPrice = activityPrice;
    }

    @Basic
    @Column(name = "activity_discount")
    public Double getActivityDiscount() {
        return activityDiscount;
    }

    public void setActivityDiscount(Double activityDiscount) {
        this.activityDiscount = activityDiscount;
    }

    @Basic
    @Column(name = "branch_company_id")
    public String getBranchCompanyId() {
        return branchCompanyId;
    }

    public void setBranchCompanyId(String branchCompanyId) {
        this.branchCompanyId = branchCompanyId;
    }

    @Basic
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDataActivityDetailEntity that = (TDataActivityDetailEntity) o;

        if (id != that.id) return false;
        if (activityId != that.activityId) return false;
        if (activityType != null ? !activityType.equals(that.activityType) : that.activityType != null) return false;
        if (activityTitle != null ? !activityTitle.equals(that.activityTitle) : that.activityTitle != null)
            return false;
        if (activitySmallImg != null ? !activitySmallImg.equals(that.activitySmallImg) : that.activitySmallImg != null)
            return false;
        if (activitySpecialImg != null ? !activitySpecialImg.equals(that.activitySpecialImg) : that.activitySpecialImg != null)
            return false;
        if (activityFirstImg != null ? !activityFirstImg.equals(that.activityFirstImg) : that.activityFirstImg != null)
            return false;
        if (activityStartTime != null ? !activityStartTime.equals(that.activityStartTime) : that.activityStartTime != null)
            return false;
        if (activityEndTime != null ? !activityEndTime.equals(that.activityEndTime) : that.activityEndTime != null)
            return false;
        if (activityAddress != null ? !activityAddress.equals(that.activityAddress) : that.activityAddress != null)
            return false;
        if (activityPeoples != null ? !activityPeoples.equals(that.activityPeoples) : that.activityPeoples != null)
            return false;
        if (activityContent != null ? !activityContent.equals(that.activityContent) : that.activityContent != null)
            return false;
        if (activityRegion != null ? !activityRegion.equals(that.activityRegion) : that.activityRegion != null)
            return false;
        if (activityRegionsponsor != null ? !activityRegionsponsor.equals(that.activityRegionsponsor) : that.activityRegionsponsor != null)
            return false;
        if (activityRegioncharge != null ? !activityRegioncharge.equals(that.activityRegioncharge) : that.activityRegioncharge != null)
            return false;
        if (activityRegioncontact != null ? !activityRegioncontact.equals(that.activityRegioncontact) : that.activityRegioncontact != null)
            return false;
        if (activityDatetime != null ? !activityDatetime.equals(that.activityDatetime) : that.activityDatetime != null)
            return false;
        if (activityIsplay != null ? !activityIsplay.equals(that.activityIsplay) : that.activityIsplay != null)
            return false;
        if (activityProductId != null ? !activityProductId.equals(that.activityProductId) : that.activityProductId != null)
            return false;
        if (activityPrice != null ? !activityPrice.equals(that.activityPrice) : that.activityPrice != null)
            return false;
        if (activityDiscount != null ? !activityDiscount.equals(that.activityDiscount) : that.activityDiscount != null)
            return false;
        if (branchCompanyId != null ? !branchCompanyId.equals(that.branchCompanyId) : that.branchCompanyId != null)
            return false;
        if (remarks != null ? !remarks.equals(that.remarks) : that.remarks != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + activityId;
        result = 31 * result + (activityType != null ? activityType.hashCode() : 0);
        result = 31 * result + (activityTitle != null ? activityTitle.hashCode() : 0);
        result = 31 * result + (activitySmallImg != null ? activitySmallImg.hashCode() : 0);
        result = 31 * result + (activitySpecialImg != null ? activitySpecialImg.hashCode() : 0);
        result = 31 * result + (activityFirstImg != null ? activityFirstImg.hashCode() : 0);
        result = 31 * result + (activityStartTime != null ? activityStartTime.hashCode() : 0);
        result = 31 * result + (activityEndTime != null ? activityEndTime.hashCode() : 0);
        result = 31 * result + (activityAddress != null ? activityAddress.hashCode() : 0);
        result = 31 * result + (activityPeoples != null ? activityPeoples.hashCode() : 0);
        result = 31 * result + (activityContent != null ? activityContent.hashCode() : 0);
        result = 31 * result + (activityRegion != null ? activityRegion.hashCode() : 0);
        result = 31 * result + (activityRegionsponsor != null ? activityRegionsponsor.hashCode() : 0);
        result = 31 * result + (activityRegioncharge != null ? activityRegioncharge.hashCode() : 0);
        result = 31 * result + (activityRegioncontact != null ? activityRegioncontact.hashCode() : 0);
        result = 31 * result + (activityDatetime != null ? activityDatetime.hashCode() : 0);
        result = 31 * result + (activityIsplay != null ? activityIsplay.hashCode() : 0);
        result = 31 * result + (activityProductId != null ? activityProductId.hashCode() : 0);
        result = 31 * result + (activityPrice != null ? activityPrice.hashCode() : 0);
        result = 31 * result + (activityDiscount != null ? activityDiscount.hashCode() : 0);
        result = 31 * result + (branchCompanyId != null ? branchCompanyId.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
