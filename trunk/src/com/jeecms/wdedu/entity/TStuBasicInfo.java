package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_stu_basic_info", schema = "wodecareer", catalog = "")
public class TStuBasicInfo {
    private int id;
    private String xm1;
    private String xm2;
    private String xm3;
    private String xm4;
    private String xm5;
    private String xm6;
    private String xm7;
    private String xm8;
    private String xm9;
    private String xm10;
    private String xm11;
    private String xm12;
    private String xm13;
    private String xm14;
    private String xm15;
    private String xm16;
    private String xm17;
    private String xm18;
    private String xm19;
    private String xm20;
    private String xm21;
    private String xm22;
    private String xm23;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "xm1")
    public String getXm1() {
        return xm1;
    }

    public void setXm1(String xm1) {
        this.xm1 = xm1;
    }

    @Basic
    @Column(name = "xm2")
    public String getXm2() {
        return xm2;
    }

    public void setXm2(String xm2) {
        this.xm2 = xm2;
    }

    @Basic
    @Column(name = "xm3")
    public String getXm3() {
        return xm3;
    }

    public void setXm3(String xm3) {
        this.xm3 = xm3;
    }

    @Basic
    @Column(name = "xm4")
    public String getXm4() {
        return xm4;
    }

    public void setXm4(String xm4) {
        this.xm4 = xm4;
    }

    @Basic
    @Column(name = "xm5")
    public String getXm5() {
        return xm5;
    }

    public void setXm5(String xm5) {
        this.xm5 = xm5;
    }

    @Basic
    @Column(name = "xm6")
    public String getXm6() {
        return xm6;
    }

    public void setXm6(String xm6) {
        this.xm6 = xm6;
    }

    @Basic
    @Column(name = "xm7")
    public String getXm7() {
        return xm7;
    }

    public void setXm7(String xm7) {
        this.xm7 = xm7;
    }

    @Basic
    @Column(name = "xm8")
    public String getXm8() {
        return xm8;
    }

    public void setXm8(String xm8) {
        this.xm8 = xm8;
    }

    @Basic
    @Column(name = "xm9")
    public String getXm9() {
        return xm9;
    }

    public void setXm9(String xm9) {
        this.xm9 = xm9;
    }

    @Basic
    @Column(name = "xm10")
    public String getXm10() {
        return xm10;
    }

    public void setXm10(String xm10) {
        this.xm10 = xm10;
    }

    @Basic
    @Column(name = "xm11")
    public String getXm11() {
        return xm11;
    }

    public void setXm11(String xm11) {
        this.xm11 = xm11;
    }

    @Basic
    @Column(name = "xm12")
    public String getXm12() {
        return xm12;
    }

    public void setXm12(String xm12) {
        this.xm12 = xm12;
    }

    @Basic
    @Column(name = "xm13")
    public String getXm13() {
        return xm13;
    }

    public void setXm13(String xm13) {
        this.xm13 = xm13;
    }

    @Basic
    @Column(name = "xm14")
    public String getXm14() {
        return xm14;
    }

    public void setXm14(String xm14) {
        this.xm14 = xm14;
    }

    @Basic
    @Column(name = "xm15")
    public String getXm15() {
        return xm15;
    }

    public void setXm15(String xm15) {
        this.xm15 = xm15;
    }

    @Basic
    @Column(name = "xm16")
    public String getXm16() {
        return xm16;
    }

    public void setXm16(String xm16) {
        this.xm16 = xm16;
    }

    @Basic
    @Column(name = "xm17")
    public String getXm17() {
        return xm17;
    }

    public void setXm17(String xm17) {
        this.xm17 = xm17;
    }

    @Basic
    @Column(name = "xm18")
    public String getXm18() {
        return xm18;
    }

    public void setXm18(String xm18) {
        this.xm18 = xm18;
    }

    @Basic
    @Column(name = "xm19")
    public String getXm19() {
        return xm19;
    }

    public void setXm19(String xm19) {
        this.xm19 = xm19;
    }

    @Basic
    @Column(name = "xm20")
    public String getXm20() {
        return xm20;
    }

    public void setXm20(String xm20) {
        this.xm20 = xm20;
    }

    @Basic
    @Column(name = "xm21")
    public String getXm21() {
        return xm21;
    }

    public void setXm21(String xm21) {
        this.xm21 = xm21;
    }

    @Basic
    @Column(name = "xm22")
    public String getXm22() {
        return xm22;
    }

    public void setXm22(String xm22) {
        this.xm22 = xm22;
    }

    @Basic
    @Column(name = "xm23")
    public String getXm23() {
        return xm23;
    }

    public void setXm23(String xm23) {
        this.xm23 = xm23;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TStuBasicInfo that = (TStuBasicInfo) o;
        return id == that.id &&
                Objects.equals(xm1, that.xm1) &&
                Objects.equals(xm2, that.xm2) &&
                Objects.equals(xm3, that.xm3) &&
                Objects.equals(xm4, that.xm4) &&
                Objects.equals(xm5, that.xm5) &&
                Objects.equals(xm6, that.xm6) &&
                Objects.equals(xm7, that.xm7) &&
                Objects.equals(xm8, that.xm8) &&
                Objects.equals(xm9, that.xm9) &&
                Objects.equals(xm10, that.xm10) &&
                Objects.equals(xm11, that.xm11) &&
                Objects.equals(xm12, that.xm12) &&
                Objects.equals(xm13, that.xm13) &&
                Objects.equals(xm14, that.xm14) &&
                Objects.equals(xm15, that.xm15) &&
                Objects.equals(xm16, that.xm16) &&
                Objects.equals(xm17, that.xm17) &&
                Objects.equals(xm18, that.xm18) &&
                Objects.equals(xm19, that.xm19) &&
                Objects.equals(xm20, that.xm20) &&
                Objects.equals(xm21, that.xm21) &&
                Objects.equals(xm22, that.xm22) &&
                Objects.equals(xm23, that.xm23);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, xm1, xm2, xm3, xm4, xm5, xm6, xm7, xm8, xm9, xm10, xm11, xm12, xm13, xm14, xm15, xm16, xm17, xm18, xm19, xm20, xm21, xm22, xm23);
    }
}
