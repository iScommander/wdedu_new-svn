package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/22
 */
@Entity
@Table(name = "t_school", schema = "wodecareer", catalog = "")
public class TSchool {
    private int id;
    private String province;
    private String city;
    private String area;
    private String name;
    private String abbreviation;
    private String address;
    private String contacts;
    private String telephoneNum;
    private String code;
    private String name2;
    private String groups;
    private Integer status;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "area")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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
    @Column(name = "abbreviation")
    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "Contacts")
    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    @Basic
    @Column(name = "telephoneNum")
    public String getTelephoneNum() {
        return telephoneNum;
    }

    public void setTelephoneNum(String telephoneNum) {
        this.telephoneNum = telephoneNum;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name2")
    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    @Basic
    @Column(name = "groups")
    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TSchool tSchool = (TSchool) o;
        return id == tSchool.id &&
                Objects.equals(province, tSchool.province) &&
                Objects.equals(city, tSchool.city) &&
                Objects.equals(area, tSchool.area) &&
                Objects.equals(name, tSchool.name) &&
                Objects.equals(abbreviation, tSchool.abbreviation) &&
                Objects.equals(address, tSchool.address) &&
                Objects.equals(contacts, tSchool.contacts) &&
                Objects.equals(telephoneNum, tSchool.telephoneNum) &&
                Objects.equals(code, tSchool.code) &&
                Objects.equals(name2, tSchool.name2) &&
                Objects.equals(groups, tSchool.groups) &&
                Objects.equals(status, tSchool.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, province, city, area, name, abbreviation, address, contacts, telephoneNum, code, name2, groups, status);
    }
}
