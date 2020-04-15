package com.jeecms.wdedu.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
public class TCeeEnrollPlanPK implements Serializable {
    private int id;
    private String majorSchoolLength;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "major_school_length", nullable = false, length = 255)
    @Id
    public String getMajorSchoolLength() {
        return majorSchoolLength;
    }

    public void setMajorSchoolLength(String majorSchoolLength) {
        this.majorSchoolLength = majorSchoolLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TCeeEnrollPlanPK that = (TCeeEnrollPlanPK) o;
        return id == that.id &&
                Objects.equals(majorSchoolLength, that.majorSchoolLength);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, majorSchoolLength);
    }
}
