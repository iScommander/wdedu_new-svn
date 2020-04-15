package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_company", schema = "wodecareer", catalog = "")
public class TNCompany {
    private int id;
    private String province;
    private Integer companyId;
    private String companyName;
    private String companyAddress;
    private String companyPhone;
    private String companyEmail;
    private Integer provinceId;

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
    @Column(name = "companyId")
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "companyName")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "companyAddress")
    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Basic
    @Column(name = "companyPhone")
    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    @Basic
    @Column(name = "companyEmail")
    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    @Basic
    @Column(name = "provinceId")
    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TNCompany tnCompany = (TNCompany) o;
        return id == tnCompany.id &&
                Objects.equals(province, tnCompany.province) &&
                Objects.equals(companyId, tnCompany.companyId) &&
                Objects.equals(companyName, tnCompany.companyName) &&
                Objects.equals(companyAddress, tnCompany.companyAddress) &&
                Objects.equals(companyPhone, tnCompany.companyPhone) &&
                Objects.equals(companyEmail, tnCompany.companyEmail) &&
                Objects.equals(provinceId, tnCompany.provinceId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, province, companyId, companyName, companyAddress, companyPhone, companyEmail, provinceId);
    }
}
