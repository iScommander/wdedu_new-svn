package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu
 * @date 2018/10/22
 */
@Entity
@Table(name = "t_qst_d_major", schema = "wodecareer", catalog = "")
public class TQstDMajor {
    private int id;
    private String majorTypeId;
    private String majorType;
    private String majorId;
    private String major;
    private String type;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "major_type_id")
    public String getMajorTypeId() {
        return majorTypeId;
    }

    public void setMajorTypeId(String majorTypeId) {
        this.majorTypeId = majorTypeId;
    }

    @Basic
    @Column(name = "major_type")
    public String getMajorType() {
        return majorType;
    }

    public void setMajorType(String majorType) {
        this.majorType = majorType;
    }

    @Basic
    @Column(name = "major_id")
    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    @Basic
    @Column(name = "major")
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TQstDMajor that = (TQstDMajor) o;
        return id == that.id &&
                Objects.equals(majorTypeId, that.majorTypeId) &&
                Objects.equals(majorType, that.majorType) &&
                Objects.equals(majorId, that.majorId) &&
                Objects.equals(major, that.major) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, majorTypeId, majorType, majorId, major, type);
    }
}
