/*
 * Copyright (C), 2017-2018 志愿无忧
 * FileName: ActiveTeacher.java
 * Author:   panglv
 * Date:     2018年2月2日 下午3:49:06
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.jeecms.wdedu.sign;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author panglv
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Entity
@Table(name="active_teacher")
public class ActiveTeacher {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;//int(11)
    
    @Column(name="teacher_id")
    private Integer teacher_id;//int(11)讲师编号
    
    @Column(name="name")
    private String name;//varchar(10)姓名
    
    @Column(name="province")
    private String province;//varchar(10)讲师归属省份
    
    @Column(name="pro_id")
    private Integer pro_id;//int(10)省份id
    
    @Column(name="introduce0")
    private String introduce0;//varchar(100)头衔
    
    @Column(name="introduce1")
    private String introduce1;//varchar(100)简介1
    
    @Column(name="introduce2")
    private String introduce2;//varchar(200)简介2
    
    @Column(name="introduce3")
    private String introduce3;//varchar(200)简介3
    
    @Column(name="introduce4")
    private Blob introduce4;//longblob简介4
    
    private String strintroduce4;//longblob简介4
    
    @Column(name="image1")
    private String image1;//varchar(100)讲师图片1
    
    @Column(name="image2")
    private String image2;//varchar(100)讲师图片2

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the teacher_id
     */
    public Integer getTeacher_id() {
        return teacher_id;
    }

    /**
     * @return the strintroduce4
     */
    public String getStrintroduce4() {
        return strintroduce4;
    }

    /**
     * @param strintroduce4 the strintroduce4 to set
     */
    public void setStrintroduce4(String strintroduce4) {
        this.strintroduce4 = strintroduce4;
    }

    /**
     * @param teacher_id the teacher_id to set
     */
    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the pro_id
     */
    public Integer getPro_id() {
        return pro_id;
    }

    /**
     * @param pro_id the pro_id to set
     */
    public void setPro_id(Integer pro_id) {
        this.pro_id = pro_id;
    }

    /**
     * @return the introduce0
     */
    public String getIntroduce0() {
        return introduce0;
    }

    /**
     * @param introduce0 the introduce0 to set
     */
    public void setIntroduce0(String introduce0) {
        this.introduce0 = introduce0;
    }

    /**
     * @return the introduce1
     */
    public String getIntroduce1() {
        return introduce1;
    }

    /**
     * @param introduce1 the introduce1 to set
     */
    public void setIntroduce1(String introduce1) {
        this.introduce1 = introduce1;
    }

    /**
     * @return the introduce2
     */
    public String getIntroduce2() {
        return introduce2;
    }

    /**
     * @param introduce2 the introduce2 to set
     */
    public void setIntroduce2(String introduce2) {
        this.introduce2 = introduce2;
    }

    /**
     * @return the introduce3
     */
    public String getIntroduce3() {
        return introduce3;
    }

    /**
     * @param introduce3 the introduce3 to set
     */
    public void setIntroduce3(String introduce3) {
        this.introduce3 = introduce3;
    }

    /**
     * @return the introduce4
     */
    public Blob getIntroduce4() {
        return introduce4;
    }

    /**
     * @param introduce4 the introduce4 to set
     */
    public void setIntroduce4(Blob introduce4) {
        this.introduce4 = introduce4;
    }

    /**
     * @return the image1
     */
    public String getImage1() {
        return image1;
    }

    /**
     * @param image1 the image1 to set
     */
    public void setImage1(String image1) {
        this.image1 = image1;
    }

    /**
     * @return the image2
     */
    public String getImage2() {
        return image2;
    }

    /**
     * @param image2 the image2 to set
     */
    public void setImage2(String image2) {
        this.image2 = image2;
    }
    
    
    
}
