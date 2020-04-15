package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_contract_info", schema = "wodecareer", catalog = "")
public class TNContractInfo {
    private String contractNo;
    private String contractMan;
    private String contractDate;
    private String branchCompany;
    private String mailAddress;
    private String paymentRecord;
    private String isVioded;
    private String note;
    private String contractMailAddress;

    @Id
    @Column(name = "contractNo")
    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    @Basic
    @Column(name = "contractMan")
    public String getContractMan() {
        return contractMan;
    }

    public void setContractMan(String contractMan) {
        this.contractMan = contractMan;
    }

    @Basic
    @Column(name = "contractDate")
    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    @Basic
    @Column(name = "branchCompany")
    public String getBranchCompany() {
        return branchCompany;
    }

    public void setBranchCompany(String branchCompany) {
        this.branchCompany = branchCompany;
    }

    @Basic
    @Column(name = "mailAddress")
    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    @Basic
    @Column(name = "paymentRecord")
    public String getPaymentRecord() {
        return paymentRecord;
    }

    public void setPaymentRecord(String paymentRecord) {
        this.paymentRecord = paymentRecord;
    }

    @Basic
    @Column(name = "isVioded")
    public String getIsVioded() {
        return isVioded;
    }

    public void setIsVioded(String isVioded) {
        this.isVioded = isVioded;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "contractMailAddress")
    public String getContractMailAddress() {
        return contractMailAddress;
    }

    public void setContractMailAddress(String contractMailAddress) {
        this.contractMailAddress = contractMailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TNContractInfo that = (TNContractInfo) o;
        return Objects.equals(contractNo, that.contractNo) &&
                Objects.equals(contractMan, that.contractMan) &&
                Objects.equals(contractDate, that.contractDate) &&
                Objects.equals(branchCompany, that.branchCompany) &&
                Objects.equals(mailAddress, that.mailAddress) &&
                Objects.equals(paymentRecord, that.paymentRecord) &&
                Objects.equals(isVioded, that.isVioded) &&
                Objects.equals(note, that.note) &&
                Objects.equals(contractMailAddress, that.contractMailAddress);
    }

    @Override
    public int hashCode() {

        return Objects.hash(contractNo, contractMan, contractDate, branchCompany, mailAddress, paymentRecord, isVioded, note, contractMailAddress);
    }
}
