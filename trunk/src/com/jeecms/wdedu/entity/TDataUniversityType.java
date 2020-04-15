package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_data_university_type", schema = "wodecareer", catalog = "")
public class TDataUniversityType {
    private int id;
    private String typeName;
    private String descr;
    private Integer majorTypeId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type_name")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Basic
    @Column(name = "descr")
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Basic
    @Column(name = "major_type_id")
    public Integer getMajorTypeId() {
        return majorTypeId;
    }

    public void setMajorTypeId(Integer majorTypeId) {
        this.majorTypeId = majorTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TDataUniversityType that = (TDataUniversityType) o;
        return id == that.id &&
                Objects.equals(typeName, that.typeName) &&
                Objects.equals(descr, that.descr) &&
                Objects.equals(majorTypeId, that.majorTypeId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, typeName, descr, majorTypeId);
    }
}
