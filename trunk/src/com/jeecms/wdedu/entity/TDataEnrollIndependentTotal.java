package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @date 2018/10/17
 */
@Entity
@Table(name = "t_data_enroll_independent_total", schema = "wodecareer", catalog = "")
public class TDataEnrollIndependentTotal {
    private int id;
    private Integer year;
    private String serviceType;
    private Integer passType;
    private String univName;
    private Integer univId;
    private Integer regNum;
    private Integer csNum;
    private BigInteger csPer;
    private Integer zsPlan;
    private BigInteger zgPer;
    private Integer rxNum;
    private BigInteger rxPer;
    private Integer zzNum;
    private BigInteger zzPer;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    @Column(name = "serviceType")
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @Basic
    @Column(name = "passType")
    public Integer getPassType() {
        return passType;
    }

    public void setPassType(Integer passType) {
        this.passType = passType;
    }

    @Basic
    @Column(name = "univName")
    public String getUnivName() {
        return univName;
    }

    public void setUnivName(String univName) {
        this.univName = univName;
    }

    @Basic
    @Column(name = "univ_id")
    public Integer getUnivId() {
        return univId;
    }

    public void setUnivId(Integer univId) {
        this.univId = univId;
    }

    @Basic
    @Column(name = "reg_num")
    public Integer getRegNum() {
        return regNum;
    }

    public void setRegNum(Integer regNum) {
        this.regNum = regNum;
    }

    @Basic
    @Column(name = "cs_num")
    public Integer getCsNum() {
        return csNum;
    }

    public void setCsNum(Integer csNum) {
        this.csNum = csNum;
    }

    @Basic
    @Column(name = "cs_per")
    public BigInteger getCsPer() {
        return csPer;
    }

    public void setCsPer(BigInteger csPer) {
        this.csPer = csPer;
    }

    @Basic
    @Column(name = "zs_plan")
    public Integer getZsPlan() {
        return zsPlan;
    }

    public void setZsPlan(Integer zsPlan) {
        this.zsPlan = zsPlan;
    }

    @Basic
    @Column(name = "zg_per")
    public BigInteger getZgPer() {
        return zgPer;
    }

    public void setZgPer(BigInteger zgPer) {
        this.zgPer = zgPer;
    }

    @Basic
    @Column(name = "rx_num")
    public Integer getRxNum() {
        return rxNum;
    }

    public void setRxNum(Integer rxNum) {
        this.rxNum = rxNum;
    }

    @Basic
    @Column(name = "rx_per")
    public BigInteger getRxPer() {
        return rxPer;
    }

    public void setRxPer(BigInteger rxPer) {
        this.rxPer = rxPer;
    }

    @Basic
    @Column(name = "zz_num")
    public Integer getZzNum() {
        return zzNum;
    }

    public void setZzNum(Integer zzNum) {
        this.zzNum = zzNum;
    }

    @Basic
    @Column(name = "zz_per")
    public BigInteger getZzPer() {
        return zzPer;
    }

    public void setZzPer(BigInteger zzPer) {
        this.zzPer = zzPer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TDataEnrollIndependentTotal that = (TDataEnrollIndependentTotal) o;
        return id == that.id &&
                Objects.equals(year, that.year) &&
                Objects.equals(serviceType, that.serviceType) &&
                Objects.equals(passType, that.passType) &&
                Objects.equals(univName, that.univName) &&
                Objects.equals(univId, that.univId) &&
                Objects.equals(regNum, that.regNum) &&
                Objects.equals(csNum, that.csNum) &&
                Objects.equals(csPer, that.csPer) &&
                Objects.equals(zsPlan, that.zsPlan) &&
                Objects.equals(zgPer, that.zgPer) &&
                Objects.equals(rxNum, that.rxNum) &&
                Objects.equals(rxPer, that.rxPer) &&
                Objects.equals(zzNum, that.zzNum) &&
                Objects.equals(zzPer, that.zzPer);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, year, serviceType, passType, univName, univId, regNum, csNum, csPer, zsPlan, zgPer, rxNum, rxPer, zzNum, zzPer);
    }
}
