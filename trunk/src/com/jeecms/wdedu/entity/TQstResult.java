package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/22
 */
@Entity
@Table(name = "t_qst_result", schema = "wodecareer", catalog = "")
public class TQstResult {
    private int id;
    private int userId;
    private String userName;
    private Date resultADate;
    private Date resultBDate;
    private Date resultCDate;
    private String resultType;
    private Integer retAR;
    private Integer retAI;
    private Integer retAA;
    private Integer retAS;
    private Integer retAE;
    private Integer retAC;
    private Integer rstBJ;
    private Integer rstBF;
    private Integer rstBS;
    private Integer rstBE;
    private Integer rstBN;
    private Integer rstBP;
    private Integer rstBI;
    private Integer rstBT;
    private Integer rstC1;
    private Integer rstC2;
    private Integer rstC3;
    private String rstA;
    private String rstB;
    private String rstCMajorName;
    private Integer rstANum;
    private Integer rstBNum;
    private Integer rstCNum;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "resultA_date")
    public Date getResultADate() {
        return resultADate;
    }

    public void setResultADate(Date resultADate) {
        this.resultADate = resultADate;
    }

    @Basic
    @Column(name = "resultB_date")
    public Date getResultBDate() {
        return resultBDate;
    }

    public void setResultBDate(Date resultBDate) {
        this.resultBDate = resultBDate;
    }

    @Basic
    @Column(name = "resultC_date")
    public Date getResultCDate() {
        return resultCDate;
    }

    public void setResultCDate(Date resultCDate) {
        this.resultCDate = resultCDate;
    }

    @Basic
    @Column(name = "result_type")
    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    @Basic
    @Column(name = "retA_R")
    public Integer getRetAR() {
        return retAR;
    }

    public void setRetAR(Integer retAR) {
        this.retAR = retAR;
    }

    @Basic
    @Column(name = "retA_I")
    public Integer getRetAI() {
        return retAI;
    }

    public void setRetAI(Integer retAI) {
        this.retAI = retAI;
    }

    @Basic
    @Column(name = "retA_A")
    public Integer getRetAA() {
        return retAA;
    }

    public void setRetAA(Integer retAA) {
        this.retAA = retAA;
    }

    @Basic
    @Column(name = "retA_S")
    public Integer getRetAS() {
        return retAS;
    }

    public void setRetAS(Integer retAS) {
        this.retAS = retAS;
    }

    @Basic
    @Column(name = "retA_E")
    public Integer getRetAE() {
        return retAE;
    }

    public void setRetAE(Integer retAE) {
        this.retAE = retAE;
    }

    @Basic
    @Column(name = "retA_C")
    public Integer getRetAC() {
        return retAC;
    }

    public void setRetAC(Integer retAC) {
        this.retAC = retAC;
    }

    @Basic
    @Column(name = "rstB_J")
    public Integer getRstBJ() {
        return rstBJ;
    }

    public void setRstBJ(Integer rstBJ) {
        this.rstBJ = rstBJ;
    }

    @Basic
    @Column(name = "rstB_F")
    public Integer getRstBF() {
        return rstBF;
    }

    public void setRstBF(Integer rstBF) {
        this.rstBF = rstBF;
    }

    @Basic
    @Column(name = "rstB_S")
    public Integer getRstBS() {
        return rstBS;
    }

    public void setRstBS(Integer rstBS) {
        this.rstBS = rstBS;
    }

    @Basic
    @Column(name = "rstB_E")
    public Integer getRstBE() {
        return rstBE;
    }

    public void setRstBE(Integer rstBE) {
        this.rstBE = rstBE;
    }

    @Basic
    @Column(name = "rstB_N")
    public Integer getRstBN() {
        return rstBN;
    }

    public void setRstBN(Integer rstBN) {
        this.rstBN = rstBN;
    }

    @Basic
    @Column(name = "rstB_P")
    public Integer getRstBP() {
        return rstBP;
    }

    public void setRstBP(Integer rstBP) {
        this.rstBP = rstBP;
    }

    @Basic
    @Column(name = "rstB_I")
    public Integer getRstBI() {
        return rstBI;
    }

    public void setRstBI(Integer rstBI) {
        this.rstBI = rstBI;
    }

    @Basic
    @Column(name = "rstB_T")
    public Integer getRstBT() {
        return rstBT;
    }

    public void setRstBT(Integer rstBT) {
        this.rstBT = rstBT;
    }

    @Basic
    @Column(name = "rstC_1")
    public Integer getRstC1() {
        return rstC1;
    }

    public void setRstC1(Integer rstC1) {
        this.rstC1 = rstC1;
    }

    @Basic
    @Column(name = "rstC_2")
    public Integer getRstC2() {
        return rstC2;
    }

    public void setRstC2(Integer rstC2) {
        this.rstC2 = rstC2;
    }

    @Basic
    @Column(name = "rstC_3")
    public Integer getRstC3() {
        return rstC3;
    }

    public void setRstC3(Integer rstC3) {
        this.rstC3 = rstC3;
    }

    @Basic
    @Column(name = "rst_A")
    public String getRstA() {
        return rstA;
    }

    public void setRstA(String rstA) {
        this.rstA = rstA;
    }

    @Basic
    @Column(name = "rst_B")
    public String getRstB() {
        return rstB;
    }

    public void setRstB(String rstB) {
        this.rstB = rstB;
    }

    @Basic
    @Column(name = "rst_C_majorName")
    public String getRstCMajorName() {
        return rstCMajorName;
    }

    public void setRstCMajorName(String rstCMajorName) {
        this.rstCMajorName = rstCMajorName;
    }

    @Basic
    @Column(name = "rst_A_num")
    public Integer getRstANum() {
        return rstANum;
    }

    public void setRstANum(Integer rstANum) {
        this.rstANum = rstANum;
    }

    @Basic
    @Column(name = "rst_B_num")
    public Integer getRstBNum() {
        return rstBNum;
    }

    public void setRstBNum(Integer rstBNum) {
        this.rstBNum = rstBNum;
    }

    @Basic
    @Column(name = "rst_C_num")
    public Integer getRstCNum() {
        return rstCNum;
    }

    public void setRstCNum(Integer rstCNum) {
        this.rstCNum = rstCNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TQstResult that = (TQstResult) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(resultADate, that.resultADate) &&
                Objects.equals(resultBDate, that.resultBDate) &&
                Objects.equals(resultCDate, that.resultCDate) &&
                Objects.equals(resultType, that.resultType) &&
                Objects.equals(retAR, that.retAR) &&
                Objects.equals(retAI, that.retAI) &&
                Objects.equals(retAA, that.retAA) &&
                Objects.equals(retAS, that.retAS) &&
                Objects.equals(retAE, that.retAE) &&
                Objects.equals(retAC, that.retAC) &&
                Objects.equals(rstBJ, that.rstBJ) &&
                Objects.equals(rstBF, that.rstBF) &&
                Objects.equals(rstBS, that.rstBS) &&
                Objects.equals(rstBE, that.rstBE) &&
                Objects.equals(rstBN, that.rstBN) &&
                Objects.equals(rstBP, that.rstBP) &&
                Objects.equals(rstBI, that.rstBI) &&
                Objects.equals(rstBT, that.rstBT) &&
                Objects.equals(rstC1, that.rstC1) &&
                Objects.equals(rstC2, that.rstC2) &&
                Objects.equals(rstC3, that.rstC3) &&
                Objects.equals(rstA, that.rstA) &&
                Objects.equals(rstB, that.rstB) &&
                Objects.equals(rstCMajorName, that.rstCMajorName) &&
                Objects.equals(rstANum, that.rstANum) &&
                Objects.equals(rstBNum, that.rstBNum) &&
                Objects.equals(rstCNum, that.rstCNum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, userName, resultADate, resultBDate, resultCDate, resultType, retAR, retAI, retAA, retAS, retAE, retAC, rstBJ, rstBF, rstBS, rstBE, rstBN, rstBP, rstBI, rstBT, rstC1, rstC2, rstC3, rstA, rstB, rstCMajorName, rstANum, rstBNum, rstCNum);
    }
}
