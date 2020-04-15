package com.jeecms.wdedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_cee_batch", schema = "wodecareer", catalog = "")
public class TCeeBatch {
    private Integer id;
    private Integer year;
    private Integer provinceId;
    private Integer majorTypeId;
    private Integer batchId;
    private String batchName;
    private Integer score;
    private String fillDate;
    private Integer univNum;
    private Integer majorNum;
    private Integer extraNum;
    private String ceePre;
    private Integer order;
    private String isShow;
    private String batchIdAlias;
    private String nameAlias;
    private String remark;
    private String state;
    private Integer cNum;
    private Integer wNum;
    private Integer bNum;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "batch_name")
    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    @Basic
    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "fill_date")
    public String getFillDate() {
        return fillDate;
    }

    public void setFillDate(String fillDate) {
        this.fillDate = fillDate;
    }

    @Basic
    @Column(name = "univ_num")
    public Integer getUnivNum() {
        return univNum;
    }

    public void setUnivNum(Integer univNum) {
        this.univNum = univNum;
    }

    @Basic
    @Column(name = "major_num")
    public Integer getMajorNum() {
        return majorNum;
    }

    public void setMajorNum(Integer majorNum) {
        this.majorNum = majorNum;
    }

    @Basic
    @Column(name = "extra_num")
    public Integer getExtraNum() {
        return extraNum;
    }

    public void setExtraNum(Integer extraNum) {
        this.extraNum = extraNum;
    }

    @Basic
    @Column(name = "cee_pre")
    public String getCeePre() {
        return ceePre;
    }

    public void setCeePre(String ceePre) {
        this.ceePre = ceePre;
    }

    @Basic
    @Column(name = "order")
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Basic
    @Column(name = "is_show")
    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    @Basic
    @Column(name = "batch_id_alias")
    public String getBatchIdAlias() {
        return batchIdAlias;
    }

    public void setBatchIdAlias(String batchIdAlias) {
        this.batchIdAlias = batchIdAlias;
    }

    @Basic
    @Column(name = "name_alias")
    public String getNameAlias() {
        return nameAlias;
    }

    public void setNameAlias(String nameAlias) {
        this.nameAlias = nameAlias;
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
    @Column(name = "c_num")
    public Integer getcNum() {
        return cNum;
    }

    public void setcNum(Integer cNum) {
        this.cNum = cNum;
    }

    @Basic
    @Column(name = "w_num")
    public Integer getwNum() {
        return wNum;
    }

    public void setwNum(Integer wNum) {
        this.wNum = wNum;
    }

    @Basic
    @Column(name = "b_num")
    public Integer getbNum() {
        return bNum;
    }

    public void setbNum(Integer bNum) {
        this.bNum = bNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TCeeBatch tCeeBatch = (TCeeBatch) o;

        if (id != null ? !id.equals(tCeeBatch.id) : tCeeBatch.id != null) return false;
        if (year != null ? !year.equals(tCeeBatch.year) : tCeeBatch.year != null) return false;
        if (provinceId != null ? !provinceId.equals(tCeeBatch.provinceId) : tCeeBatch.provinceId != null) return false;
        if (majorTypeId != null ? !majorTypeId.equals(tCeeBatch.majorTypeId) : tCeeBatch.majorTypeId != null)
            return false;
        if (batchId != null ? !batchId.equals(tCeeBatch.batchId) : tCeeBatch.batchId != null) return false;
        if (batchName != null ? !batchName.equals(tCeeBatch.batchName) : tCeeBatch.batchName != null) return false;
        if (score != null ? !score.equals(tCeeBatch.score) : tCeeBatch.score != null) return false;
        if (fillDate != null ? !fillDate.equals(tCeeBatch.fillDate) : tCeeBatch.fillDate != null) return false;
        if (univNum != null ? !univNum.equals(tCeeBatch.univNum) : tCeeBatch.univNum != null) return false;
        if (majorNum != null ? !majorNum.equals(tCeeBatch.majorNum) : tCeeBatch.majorNum != null) return false;
        if (extraNum != null ? !extraNum.equals(tCeeBatch.extraNum) : tCeeBatch.extraNum != null) return false;
        if (ceePre != null ? !ceePre.equals(tCeeBatch.ceePre) : tCeeBatch.ceePre != null) return false;
        if (order != null ? !order.equals(tCeeBatch.order) : tCeeBatch.order != null) return false;
        if (isShow != null ? !isShow.equals(tCeeBatch.isShow) : tCeeBatch.isShow != null) return false;
        if (batchIdAlias != null ? !batchIdAlias.equals(tCeeBatch.batchIdAlias) : tCeeBatch.batchIdAlias != null)
            return false;
        if (nameAlias != null ? !nameAlias.equals(tCeeBatch.nameAlias) : tCeeBatch.nameAlias != null) return false;
        if (remark != null ? !remark.equals(tCeeBatch.remark) : tCeeBatch.remark != null) return false;
        if (state != null ? !state.equals(tCeeBatch.state) : tCeeBatch.state != null) return false;
        if (cNum != null ? !cNum.equals(tCeeBatch.cNum) : tCeeBatch.cNum != null) return false;
        if (wNum != null ? !wNum.equals(tCeeBatch.wNum) : tCeeBatch.wNum != null) return false;
        if (bNum != null ? !bNum.equals(tCeeBatch.bNum) : tCeeBatch.bNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (provinceId != null ? provinceId.hashCode() : 0);
        result = 31 * result + (majorTypeId != null ? majorTypeId.hashCode() : 0);
        result = 31 * result + (batchId != null ? batchId.hashCode() : 0);
        result = 31 * result + (batchName != null ? batchName.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (fillDate != null ? fillDate.hashCode() : 0);
        result = 31 * result + (univNum != null ? univNum.hashCode() : 0);
        result = 31 * result + (majorNum != null ? majorNum.hashCode() : 0);
        result = 31 * result + (extraNum != null ? extraNum.hashCode() : 0);
        result = 31 * result + (ceePre != null ? ceePre.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (isShow != null ? isShow.hashCode() : 0);
        result = 31 * result + (batchIdAlias != null ? batchIdAlias.hashCode() : 0);
        result = 31 * result + (nameAlias != null ? nameAlias.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (cNum != null ? cNum.hashCode() : 0);
        result = 31 * result + (wNum != null ? wNum.hashCode() : 0);
        result = 31 * result + (bNum != null ? bNum.hashCode() : 0);
        return result;
    }
}
