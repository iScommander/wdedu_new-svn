package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/22
 */
@Entity
@Table(name = "t_qst_a_major", schema = "wodecareer", catalog = "")
public class TQstAMajor {
    private int id;
    private String majorParentId;
    private String majorParent;
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
    @Column(name = "major_parent_id")
    public String getMajorParentId() {
        return majorParentId;
    }

    public void setMajorParentId(String majorParentId) {
        this.majorParentId = majorParentId;
    }

    @Basic
    @Column(name = "major_parent")
    public String getMajorParent() {
        return majorParent;
    }

    public void setMajorParent(String majorParent) {
        this.majorParent = majorParent;
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
        TQstAMajor that = (TQstAMajor) o;
        return id == that.id &&
                Objects.equals(majorParentId, that.majorParentId) &&
                Objects.equals(majorParent, that.majorParent) &&
                Objects.equals(majorId, that.majorId) &&
                Objects.equals(major, that.major) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, majorParentId, majorParent, majorId, major, type);
    }
}
