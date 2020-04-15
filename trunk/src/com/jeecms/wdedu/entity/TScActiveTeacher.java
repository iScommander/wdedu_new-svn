package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/30
 */
@Entity
@Table(name = "t_sc_active_teacher", schema = "wodecareer", catalog = "")
public class TScActiveTeacher {
    private int id;
    private int teacherId;
    private String name;
    private String province;
    private Integer proId;
    private String introduce0;
    private String introduce1;
    private String introduce2;
    private String introduce3;
    private Blob introduce4;
    private String image1;
    private String image2;
    private String strintroduce4;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "teacher_id")
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "pro_id")
    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    @Basic
    @Column(name = "introduce0")
    public String getIntroduce0() {
        return introduce0;
    }

    public void setIntroduce0(String introduce0) {
        this.introduce0 = introduce0;
    }

    @Basic
    @Column(name = "introduce1")
    public String getIntroduce1() {
        return introduce1;
    }

    public void setIntroduce1(String introduce1) {
        this.introduce1 = introduce1;
    }

    @Basic
    @Column(name = "introduce2")
    public String getIntroduce2() {
        return introduce2;
    }

    public void setIntroduce2(String introduce2) {
        this.introduce2 = introduce2;
    }

    @Basic
    @Column(name = "introduce3")
    public String getIntroduce3() {
        return introduce3;
    }

    public void setIntroduce3(String introduce3) {
        this.introduce3 = introduce3;
    }

    @Basic
    @Column(name = "introduce4")
    public Blob getIntroduce4() {
        return introduce4;
    }

    public void setIntroduce4(Blob introduce4) {
        this.introduce4 = introduce4;
    }

    @Basic
    @Column(name = "image1")
    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    @Basic
    @Column(name = "image2")
    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    @Basic
    @Column(name = "strintroduce4")
    public String getStrintroduce4() {
        return strintroduce4;
    }

    public void setStrintroduce4(String strintroduce4) {
        this.strintroduce4 = strintroduce4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TScActiveTeacher that = (TScActiveTeacher) o;
        return id == that.id &&
                teacherId == that.teacherId &&
                Objects.equals(name, that.name) &&
                Objects.equals(province, that.province) &&
                Objects.equals(proId, that.proId) &&
                Objects.equals(introduce0, that.introduce0) &&
                Objects.equals(introduce1, that.introduce1) &&
                Objects.equals(introduce2, that.introduce2) &&
                Objects.equals(introduce3, that.introduce3) &&
                Objects.equals(image1, that.image1) &&
                Objects.equals(image2, that.image2) &&
                Objects.equals(strintroduce4, that.strintroduce4);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, teacherId, name, province, proId, introduce0, introduce1, introduce2, introduce3, image1, image2, strintroduce4);
        return result;
    }
}
