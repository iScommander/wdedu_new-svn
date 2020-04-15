package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_data_online_sign", schema = "wodecareer", catalog = "")
public class TDataOnlineSign {
    private Integer id;
    private String serverNo;
    private String serverType;
    private String activityType;
    private String activityId;
    private String realname;
    private String telephone;
    private String majorType;
    private String classes;
    private String jiebie;
    private String school;
    private String quxian;
    private String city;
    private String province;
    private Timestamp datetimes;
    private String channelFlag;
    private String remarks;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "server_no")
    public String getServerNo() {
        return serverNo;
    }

    public void setServerNo(String serverNo) {
        this.serverNo = serverNo;
    }

    @Basic
    @Column(name = "server_type")
    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    @Basic
    @Column(name = "activity_type")
    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    @Basic
    @Column(name = "activity_id")
    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "realname")
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
    @Column(name = "classes")
    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    @Basic
    @Column(name = "jiebie")
    public String getJiebie() {
        return jiebie;
    }

    public void setJiebie(String jiebie) {
        this.jiebie = jiebie;
    }

    @Basic
    @Column(name = "school")
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Basic
    @Column(name = "quxian")
    public String getQuxian() {
        return quxian;
    }

    public void setQuxian(String quxian) {
        this.quxian = quxian;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
    @Column(name = "datetimes")
    public Timestamp getDatetimes() {
        return datetimes;
    }

    public void setDatetimes(Timestamp datetimes) {
        this.datetimes = datetimes;
    }

    @Basic
    @Column(name = "channel_flag")
    public String getChannelFlag() {
        return channelFlag;
    }

    public void setChannelFlag(String channelFlag) {
        this.channelFlag = channelFlag;
    }

    @Basic
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDataOnlineSign that = (TDataOnlineSign) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (serverNo != null ? !serverNo.equals(that.serverNo) : that.serverNo != null) return false;
        if (serverType != null ? !serverType.equals(that.serverType) : that.serverType != null) return false;
        if (activityType != null ? !activityType.equals(that.activityType) : that.activityType != null) return false;
        if (activityId != null ? !activityId.equals(that.activityId) : that.activityId != null) return false;
        if (realname != null ? !realname.equals(that.realname) : that.realname != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        if (majorType != null ? !majorType.equals(that.majorType) : that.majorType != null) return false;
        if (classes != null ? !classes.equals(that.classes) : that.classes != null) return false;
        if (jiebie != null ? !jiebie.equals(that.jiebie) : that.jiebie != null) return false;
        if (school != null ? !school.equals(that.school) : that.school != null) return false;
        if (quxian != null ? !quxian.equals(that.quxian) : that.quxian != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (datetimes != null ? !datetimes.equals(that.datetimes) : that.datetimes != null) return false;
        if (channelFlag != null ? !channelFlag.equals(that.channelFlag) : that.channelFlag != null) return false;
        if (remarks != null ? !remarks.equals(that.remarks) : that.remarks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (serverNo != null ? serverNo.hashCode() : 0);
        result = 31 * result + (serverType != null ? serverType.hashCode() : 0);
        result = 31 * result + (activityType != null ? activityType.hashCode() : 0);
        result = 31 * result + (activityId != null ? activityId.hashCode() : 0);
        result = 31 * result + (realname != null ? realname.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (majorType != null ? majorType.hashCode() : 0);
        result = 31 * result + (classes != null ? classes.hashCode() : 0);
        result = 31 * result + (jiebie != null ? jiebie.hashCode() : 0);
        result = 31 * result + (school != null ? school.hashCode() : 0);
        result = 31 * result + (quxian != null ? quxian.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (datetimes != null ? datetimes.hashCode() : 0);
        result = 31 * result + (channelFlag != null ? channelFlag.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        return result;
    }
}
