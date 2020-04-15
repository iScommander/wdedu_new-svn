package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/22
 */
@Entity
@Table(name = "t_qst_b", schema = "wodecareer", catalog = "")
public class TQstB {
    private int id;
    private String type;
    private String rgfx;
    private String rgys;
    private String qzmd;
    private String gzhjx;

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
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "rgfx")
    public String getRgfx() {
        return rgfx;
    }

    public void setRgfx(String rgfx) {
        this.rgfx = rgfx;
    }

    @Basic
    @Column(name = "rgys")
    public String getRgys() {
        return rgys;
    }

    public void setRgys(String rgys) {
        this.rgys = rgys;
    }

    @Basic
    @Column(name = "qzmd")
    public String getQzmd() {
        return qzmd;
    }

    public void setQzmd(String qzmd) {
        this.qzmd = qzmd;
    }

    @Basic
    @Column(name = "gzhjx")
    public String getGzhjx() {
        return gzhjx;
    }

    public void setGzhjx(String gzhjx) {
        this.gzhjx = gzhjx;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TQstB tQstB = (TQstB) o;
        return id == tQstB.id &&
                Objects.equals(type, tQstB.type) &&
                Objects.equals(rgfx, tQstB.rgfx) &&
                Objects.equals(rgys, tQstB.rgys) &&
                Objects.equals(qzmd, tQstB.qzmd) &&
                Objects.equals(gzhjx, tQstB.gzhjx);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, rgfx, rgys, qzmd, gzhjx);
    }
}
