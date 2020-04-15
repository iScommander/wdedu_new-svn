/*
 * Copyright (C), 2017-2018 志愿无忧
 * FileName: ActiveUserArttr.java
 * Author:   panglv
 * Date:     2018年1月15日 上午10:17:17
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.jeecms.wdedu.sign;

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
@Table(name = "active_user_attr")
public class ActiveUserArttr {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;//int(11)编号
    
    @Column(name = "username")
    private String username;//varchar(11)姓名
    
    @Column(name = "telephone")
    private String telephone;//varchar(15)电话号码
    
    @Column(name = "province")
    private String province;//varchar(11)省份名称
    
    @Column(name = "city")
    private String city;//varchar(11)城市名称
    
    @Column(name = "quxian")
    private String quxian;//varchar(20)区县名称
    
    @Column(name = "school_name")
    private String school_name;//varchar(50)学校名称
    
    @Column(name = "major_type")
    private String major_type;//varchar(11)文理科
    
    @Column(name = "classes")
    private String classes;//varchar(11)年级
    
    @Column(name = "class_rank")
    private Integer class_rank;//int(11)年级排名
    
    @Column(name = "bm_active_num")
    private Integer bm_active_num;//int(11)报名过多少此活动
    
    @Column(name = "qd_active_num")
    private Integer qd_active_num;//int(11)参加过多少次活动

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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the quxian
     */
    public String getQuxian() {
        return quxian;
    }

    /**
     * @param quxian the quxian to set
     */
    public void setQuxian(String quxian) {
        this.quxian = quxian;
    }

    /**
     * @return the school_name
     */
    public String getSchool_name() {
        return school_name;
    }

    /**
     * @param school_name the school_name to set
     */
    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    /**
     * @return the major_type
     */
    public String getMajor_type() {
        return major_type;
    }

    /**
     * @param major_type the major_type to set
     */
    public void setMajor_type(String major_type) {
        this.major_type = major_type;
    }

    /**
     * @return the classes
     */
    public String getClasses() {
        return classes;
    }

    /**
     * @param classes the classes to set
     */
    public void setClasses(String classes) {
        this.classes = classes;
    }

    /**
     * @return the class_rank
     */
    public Integer getClass_rank() {
        return class_rank;
    }

    /**
     * @param class_rank the class_rank to set
     */
    public void setClass_rank(Integer class_rank) {
        this.class_rank = class_rank;
    }

    /**
     * @return the bm_active_num
     */
    public Integer getBm_active_num() {
        return bm_active_num;
    }

    /**
     * @param bm_active_num the bm_active_num to set
     */
    public void setBm_active_num(Integer bm_active_num) {
        this.bm_active_num = bm_active_num;
    }

    /**
     * @return the qd_active_num
     */
    public Integer getQd_active_num() {
        return qd_active_num;
    }

    /**
     * @param qd_active_num the qd_active_num to set
     */
    public void setQd_active_num(Integer qd_active_num) {
        this.qd_active_num = qd_active_num;
    }
    
    
}
