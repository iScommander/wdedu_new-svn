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
@Table(name = "t_qst_d_result", schema = "wodecareer", catalog = "")
public class TQstDResult {
    private int id;
    private String userId;
    private Integer r;
    private Integer i;
    private Integer a;
    private Integer s;
    private Integer e;
    private Integer c;
    private String best;
    private Integer wuliD3;
    private Integer lishiD3;
    private Integer diliD3;
    private Integer shengwuD3;
    private Integer zhengzhiD3;
    private Integer huaxueD3;
    private Integer jishuD3;
    private Integer wuliD4;
    private Integer lishiD4;
    private Integer diliD4;
    private Integer shengwuD4;
    private Integer zhengzhiD4;
    private int huaxueD4;
    private Integer jishuD4;
    private Date time;
    private Integer number;

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
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "R")
    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    @Basic
    @Column(name = "I")
    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    @Basic
    @Column(name = "A")
    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    @Basic
    @Column(name = "S")
    public Integer getS() {
        return s;
    }

    public void setS(Integer s) {
        this.s = s;
    }

    @Basic
    @Column(name = "E")
    public Integer getE() {
        return e;
    }

    public void setE(Integer e) {
        this.e = e;
    }

    @Basic
    @Column(name = "C")
    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    @Basic
    @Column(name = "best")
    public String getBest() {
        return best;
    }

    public void setBest(String best) {
        this.best = best;
    }

    @Basic
    @Column(name = "wuli_D3")
    public Integer getWuliD3() {
        return wuliD3;
    }

    public void setWuliD3(Integer wuliD3) {
        this.wuliD3 = wuliD3;
    }

    @Basic
    @Column(name = "lishi_D3")
    public Integer getLishiD3() {
        return lishiD3;
    }

    public void setLishiD3(Integer lishiD3) {
        this.lishiD3 = lishiD3;
    }

    @Basic
    @Column(name = "dili_D3")
    public Integer getDiliD3() {
        return diliD3;
    }

    public void setDiliD3(Integer diliD3) {
        this.diliD3 = diliD3;
    }

    @Basic
    @Column(name = "shengwu_D3")
    public Integer getShengwuD3() {
        return shengwuD3;
    }

    public void setShengwuD3(Integer shengwuD3) {
        this.shengwuD3 = shengwuD3;
    }

    @Basic
    @Column(name = "zhengzhi_D3")
    public Integer getZhengzhiD3() {
        return zhengzhiD3;
    }

    public void setZhengzhiD3(Integer zhengzhiD3) {
        this.zhengzhiD3 = zhengzhiD3;
    }

    @Basic
    @Column(name = "huaxue_D3")
    public Integer getHuaxueD3() {
        return huaxueD3;
    }

    public void setHuaxueD3(Integer huaxueD3) {
        this.huaxueD3 = huaxueD3;
    }

    @Basic
    @Column(name = "jishu_D3")
    public Integer getJishuD3() {
        return jishuD3;
    }

    public void setJishuD3(Integer jishuD3) {
        this.jishuD3 = jishuD3;
    }

    @Basic
    @Column(name = "wuli_D4")
    public Integer getWuliD4() {
        return wuliD4;
    }

    public void setWuliD4(Integer wuliD4) {
        this.wuliD4 = wuliD4;
    }

    @Basic
    @Column(name = "lishi_D4")
    public Integer getLishiD4() {
        return lishiD4;
    }

    public void setLishiD4(Integer lishiD4) {
        this.lishiD4 = lishiD4;
    }

    @Basic
    @Column(name = "dili_D4")
    public Integer getDiliD4() {
        return diliD4;
    }

    public void setDiliD4(Integer diliD4) {
        this.diliD4 = diliD4;
    }

    @Basic
    @Column(name = "shengwu_D4")
    public Integer getShengwuD4() {
        return shengwuD4;
    }

    public void setShengwuD4(Integer shengwuD4) {
        this.shengwuD4 = shengwuD4;
    }

    @Basic
    @Column(name = "zhengzhi_D4")
    public Integer getZhengzhiD4() {
        return zhengzhiD4;
    }

    public void setZhengzhiD4(Integer zhengzhiD4) {
        this.zhengzhiD4 = zhengzhiD4;
    }

    @Basic
    @Column(name = "huaxue_D4")
    public int getHuaxueD4() {
        return huaxueD4;
    }

    public void setHuaxueD4(int huaxueD4) {
        this.huaxueD4 = huaxueD4;
    }

    @Basic
    @Column(name = "jishu_D4")
    public Integer getJishuD4() {
        return jishuD4;
    }

    public void setJishuD4(Integer jishuD4) {
        this.jishuD4 = jishuD4;
    }

    @Basic
    @Column(name = "time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Basic
    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TQstDResult that = (TQstDResult) o;
        return id == that.id &&
                huaxueD4 == that.huaxueD4 &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(r, that.r) &&
                Objects.equals(i, that.i) &&
                Objects.equals(a, that.a) &&
                Objects.equals(s, that.s) &&
                Objects.equals(e, that.e) &&
                Objects.equals(c, that.c) &&
                Objects.equals(best, that.best) &&
                Objects.equals(wuliD3, that.wuliD3) &&
                Objects.equals(lishiD3, that.lishiD3) &&
                Objects.equals(diliD3, that.diliD3) &&
                Objects.equals(shengwuD3, that.shengwuD3) &&
                Objects.equals(zhengzhiD3, that.zhengzhiD3) &&
                Objects.equals(huaxueD3, that.huaxueD3) &&
                Objects.equals(jishuD3, that.jishuD3) &&
                Objects.equals(wuliD4, that.wuliD4) &&
                Objects.equals(lishiD4, that.lishiD4) &&
                Objects.equals(diliD4, that.diliD4) &&
                Objects.equals(shengwuD4, that.shengwuD4) &&
                Objects.equals(zhengzhiD4, that.zhengzhiD4) &&
                Objects.equals(jishuD4, that.jishuD4) &&
                Objects.equals(time, that.time) &&
                Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, r, i, a, s, e, c, best, wuliD3, lishiD3, diliD3, shengwuD3, zhengzhiD3, huaxueD3, jishuD3, wuliD4, lishiD4, diliD4, shengwuD4, zhengzhiD4, huaxueD4, jishuD4, time, number);
    }
}
