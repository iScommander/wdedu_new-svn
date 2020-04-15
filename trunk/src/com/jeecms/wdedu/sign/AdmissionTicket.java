/*
 * Copyright (C), 2017-2018 志愿无忧
 * FileName: AdmissionTicket.java
 * Author:   panglv
 * Date:     2018年1月8日 下午4:33:44
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.jeecms.wdedu.sign;

import java.util.Date;

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
@Table(name = "get_tickets")
public class AdmissionTicket {
    
    
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;//int(11)编号
        
        @Column(name = "active_id")
        private Integer active_id;
        
        @Column(name = "name")
        private String name;//varchar(20)姓名
        
        @Column(name = "telephone")
        private String telephone;//int(11)手机号
        
        @Column(name = "pro")
        private String pro;//varchar(11)省份名称
        
        @Column(name = "city")
        private String city;//varchar(11)市名称
        
        @Column(name = "quxian")
        private String quxian;//varchar(11)区县名称
        
        @Column(name = "school_name")
        private String school_name;//varchar(20)学校名称
        
        @Column(name = "classes")
        private String classes;//varchar(11)年级
        
        @Column(name = "major_type")
        private String major_type;//varchar(11)文理科
        
        
        @Column(name = "class_rank")
        private Integer class_rank;//int(11)年级排名
        
        @Column(name = "peonum")
        private Integer peonum;//int(11)参会人数
        
        @Column(name = "ruchangquan_type")
        private String  ruchangquan_type;//varchar(11)是否领取入场券 1为领取 默认为0
        
        @Column(name = "qiandao_type")
        private String qiandao_type;//varchar(11)是否签到 1为签到 默认为0
        
        @Column(name = "ruchangquan_code")
        private String ruchangquan_code;
        
        @Column(name = "qiandao_time")
        private Date qiandao_time;
        
        @Column(name = "lingquan_time")
        private String lingquan_time;//varchar(55)领取入场券的时间
        @Column(name = "lingquziliao")
        private String lingquziliao;//varchar(11)是否领取资料
        @Column(name = "lzl_time")
        private String lzl_time; 
        
        @Column(name = "bm_rank")
        private Integer bm_rank ;
        @Column(name = "qd_rank")
        private Integer qd_rank ;
        
        
        
        /**
         * @return the bm_rank
         */
        public Integer getBm_rank() {
            return bm_rank;
        }
        /**
         * @param bm_rank the bm_rank to set
         */
        public void setBm_rank(Integer bm_rank) {
            this.bm_rank = bm_rank;
        }
        /**
         * @return the qd_rank
         */
        public Integer getQd_rank() {
            return qd_rank;
        }
        /**
         * @param qd_rank the qd_rank to set
         */
        public void setQd_rank(Integer qd_rank) {
            this.qd_rank = qd_rank;
        }
        /**
         * @return the lingquan_time
         */
        public String getLingquan_time() {
            return lingquan_time;
        }
        /**
         * @param lingquan_time the lingquan_time to set
         */
        public void setLingquan_time(String lingquan_time) {
            this.lingquan_time = lingquan_time;
        }
        /**
         * @return the lingquziliao
         */
        public String getLingquziliao() {
            return lingquziliao;
        }
        /**
         * @param lingquziliao the lingquziliao to set
         */
        public void setLingquziliao(String lingquziliao) {
            this.lingquziliao = lingquziliao;
        }
        /**
         * @return the lzl_time
         */
        public String getLzl_time() {
            return lzl_time;
        }
        /**
         * @param lzl_time the lzl_time to set
         */
        public void setLzl_time(String lzl_time) {
            this.lzl_time = lzl_time;
        }
        /**
         * @return the qiandao_time
         */
        public Date getQiandao_time() {
            return qiandao_time;
        }
        /**
         * @param qiandao_time the qiandao_time to set
         */
        public void setQiandao_time(Date qiandao_time) {
            this.qiandao_time = qiandao_time;
        }
        /**
         * @return the active_id
         */
        public Integer getActive_id() {
            return active_id;
        }
        /**
         * @param active_id the active_id to set
         */
        public void setActive_id(Integer active_id) {
            this.active_id = active_id;
        }
        /**
         * @return the ruchangquan_code
         */
        public String getRuchangquan_code() {
            return ruchangquan_code;
        }
        /**
         * @param ruchangquan_code the ruchangquan_code to set
         */
        public void setRuchangquan_code(String ruchangquan_code) {
            this.ruchangquan_code = ruchangquan_code;
        }
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
         * @return the pro
         */
        public String getPro() {
            return pro;
        }
        /**
         * @param pro the pro to set
         */
        public void setPro(String pro) {
            this.pro = pro;
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
         * @return the peonum
         */
        public Integer getPeonum() {
            return peonum;
        }
        /**
         * @param peonum the peonum to set
         */
        public void setPeonum(Integer peonum) {
            this.peonum = peonum;
        }
        /**
         * @return the ruchangquan_type
         */
        public String getRuchangquan_type() {
            return ruchangquan_type;
        }
        /**
         * @param ruchangquan_type the ruchangquan_type to set
         */
        public void setRuchangquan_type(String ruchangquan_type) {
            this.ruchangquan_type = ruchangquan_type;
        }
        /**
         * @return the qiandao_type
         */
        public String getQiandao_type() {
            return qiandao_type;
        }
        /**
         * @param qiandao_type the qiandao_type to set
         */
        public void setQiandao_type(String qiandao_type) {
            this.qiandao_type = qiandao_type;
        }
        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("AdmissionTicket [id=");
            builder.append(id);
            builder.append(", name=");
            builder.append(name);
            builder.append(", telephone=");
            builder.append(telephone);
            builder.append(", pro=");
            builder.append(pro);
            builder.append(", city=");
            builder.append(city);
            builder.append(", quxian=");
            builder.append(quxian);
            builder.append(", school_name=");
            builder.append(school_name);
            builder.append(", classes=");
            builder.append(classes);
            builder.append(", major_type=");
            builder.append(major_type);
            builder.append(", class_rank=");
            builder.append(class_rank);
            builder.append(", peonum=");
            builder.append(peonum);
            builder.append(", ruchangquan_type=");
            builder.append(ruchangquan_type);
            builder.append(", qiandao_type=");
            builder.append(qiandao_type);
            builder.append("]");
            return builder.toString();
        }


    
    
    
    
    
    
    
    
    
}
