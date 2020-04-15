package com.jeecms.wdedu.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/11/8
 */
@Entity
@Table(name = "t_yn_online_order", schema = "wodecareer", catalog = "")
public class TYnOnlineOrder {
    private long id;
    private String phonenumber;
    private int productId;
    private BigDecimal cost;
    private Date createDate;
    private String cftSeq;
    private Integer payState;
    private Date payDate;
    private Long cardId;
    private String password;
    private String buyname;
    private String channel;
    private String remark;
    private TBaseProducts product;
    private Integer provinceId;
    private Integer majorTypeId;
    private Integer userId;
    private Integer groupId;

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "group_id")
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "phonenumber")
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Basic
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }



    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "cft_seq")
    public String getCftSeq() {
        return cftSeq;
    }

    public void setCftSeq(String cftSeq) {
        this.cftSeq = cftSeq;
    }

    @Basic
    @Column(name = "pay_state")
    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    @Basic
    @Column(name = "pay_date")
    public Date getPayDate() {
        return payDate;
    }


    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    @Basic
    @Column(name = "card_id")
    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "buyname")
    public String getBuyname() {
        return buyname;
    }

    public void setBuyname(String buyname) {
        this.buyname = buyname;
    }

    @Basic
    @Column(name = "channel")
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TYnOnlineOrder that = (TYnOnlineOrder) o;
        return id == that.id &&
                productId == that.productId &&
                Objects.equals(phonenumber, that.phonenumber) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(cftSeq, that.cftSeq) &&
                Objects.equals(payState, that.payState) &&
                Objects.equals(payDate, that.payDate) &&
                Objects.equals(cardId, that.cardId) &&
                Objects.equals(password, that.password) &&
                Objects.equals(buyname, that.buyname) &&
                Objects.equals(channel, that.channel) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, phonenumber, productId, cost, createDate, cftSeq, payState, payDate, cardId, password, buyname, channel, remark);
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "product_id", nullable = true, insertable = false, updatable = false)
    public TBaseProducts getProduct() {
        return product;
    }

    public void setProduct(TBaseProducts product) {
        this.product = product;
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
}
