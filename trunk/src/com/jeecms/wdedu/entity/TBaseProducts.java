package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_base_products", schema = "wodecareer", catalog = "")
public class TBaseProducts {
    private int id;
    private Integer type;
    private Integer provinceId;
    private String name;
    private String desc;
    private int groupId;
    private BigDecimal cost;
    private BigDecimal discountCost;
    private String imageUrl;
    private String zfbPartner;
    private String zfbEmail;
    private String zfbKey;
    private String cftPartner;
    private String cftEmail;
    private String cftKey;
    private String cftNotifyUrl;
    private Integer state;
    private Date endDate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Basic
    @Column(name = "group_id")
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "cost")
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "discount_cost")
    public BigDecimal getDiscountCost() {
        return discountCost;
    }

    public void setDiscountCost(BigDecimal discountCost) {
        this.discountCost = discountCost;
    }

    @Basic
    @Column(name = "imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Basic
    @Column(name = "zfb_partner")
    public String getZfbPartner() {
        return zfbPartner;
    }

    public void setZfbPartner(String zfbPartner) {
        this.zfbPartner = zfbPartner;
    }

    @Basic
    @Column(name = "zfb_email")
    public String getZfbEmail() {
        return zfbEmail;
    }

    public void setZfbEmail(String zfbEmail) {
        this.zfbEmail = zfbEmail;
    }

    @Basic
    @Column(name = "zfb_key")
    public String getZfbKey() {
        return zfbKey;
    }

    public void setZfbKey(String zfbKey) {
        this.zfbKey = zfbKey;
    }

    @Basic
    @Column(name = "cft_partner")
    public String getCftPartner() {
        return cftPartner;
    }

    public void setCftPartner(String cftPartner) {
        this.cftPartner = cftPartner;
    }

    @Basic
    @Column(name = "cft_email")
    public String getCftEmail() {
        return cftEmail;
    }

    public void setCftEmail(String cftEmail) {
        this.cftEmail = cftEmail;
    }

    @Basic
    @Column(name = "cft_key")
    public String getCftKey() {
        return cftKey;
    }

    public void setCftKey(String cftKey) {
        this.cftKey = cftKey;
    }

    @Basic
    @Column(name = "cft_notify_url")
    public String getCftNotifyUrl() {
        return cftNotifyUrl;
    }

    public void setCftNotifyUrl(String cftNotifyUrl) {
        this.cftNotifyUrl = cftNotifyUrl;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TBaseProducts that = (TBaseProducts) o;
        return id == that.id &&
                groupId == that.groupId &&
                cost.equals(that.cost) &&
                Objects.equals(type, that.type) &&
                Objects.equals(provinceId, that.provinceId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(desc, that.desc) &&
                Objects.equals(discountCost, that.discountCost) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(zfbPartner, that.zfbPartner) &&
                Objects.equals(zfbEmail, that.zfbEmail) &&
                Objects.equals(zfbKey, that.zfbKey) &&
                Objects.equals(cftPartner, that.cftPartner) &&
                Objects.equals(cftEmail, that.cftEmail) &&
                Objects.equals(cftKey, that.cftKey) &&
                Objects.equals(cftNotifyUrl, that.cftNotifyUrl) &&
                Objects.equals(state, that.state) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, provinceId, name, desc, groupId, cost, discountCost, imageUrl, zfbPartner, zfbEmail, zfbKey, cftPartner, cftEmail, cftKey, cftNotifyUrl, state, endDate);
    }
}
