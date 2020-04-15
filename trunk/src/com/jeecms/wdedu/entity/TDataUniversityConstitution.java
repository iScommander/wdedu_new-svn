package com.jeecms.wdedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_data_university_constitution", schema = "wodecareer", catalog = "")
public class TDataUniversityConstitution {
    private Integer id;
    private Integer universityBaseId;
    private String univName;
    private Integer dataType;
    private String dataTypeName;
    private Integer year;
    private String univConstitutionTitle;
    private String univConstitutionImport;
    private String univConstitutionDescr;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "university_base_id")
    public Integer getUniversityBaseId() {
        return universityBaseId;
    }

    public void setUniversityBaseId(Integer universityBaseId) {
        this.universityBaseId = universityBaseId;
    }

    @Basic
    @Column(name = "univ_name")
    public String getUnivName() {
        return univName;
    }

    public void setUnivName(String univName) {
        this.univName = univName;
    }

    @Basic
    @Column(name = "data_type")
    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    @Basic
    @Column(name = "data_type_name")
    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    @Basic
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "univ_constitution_title")
    public String getUnivConstitutionTitle() {
        return univConstitutionTitle;
    }

    public void setUnivConstitutionTitle(String univConstitutionTitle) {
        this.univConstitutionTitle = univConstitutionTitle;
    }

    @Basic
    @Column(name = "univ_constitution_import")
    public String getUnivConstitutionImport() {
        return univConstitutionImport;
    }

    public void setUnivConstitutionImport(String univConstitutionImport) {
        this.univConstitutionImport = univConstitutionImport;
    }

    @Basic
    @Column(name = "univ_constitution_descr")
    public String getUnivConstitutionDescr() {
        return univConstitutionDescr;
    }

    public void setUnivConstitutionDescr(String univConstitutionDescr) {
        this.univConstitutionDescr = univConstitutionDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDataUniversityConstitution that = (TDataUniversityConstitution) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (universityBaseId != null ? !universityBaseId.equals(that.universityBaseId) : that.universityBaseId != null)
            return false;
        if (univName != null ? !univName.equals(that.univName) : that.univName != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (dataTypeName != null ? !dataTypeName.equals(that.dataTypeName) : that.dataTypeName != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (univConstitutionTitle != null ? !univConstitutionTitle.equals(that.univConstitutionTitle) : that.univConstitutionTitle != null)
            return false;
        if (univConstitutionImport != null ? !univConstitutionImport.equals(that.univConstitutionImport) : that.univConstitutionImport != null)
            return false;
        if (univConstitutionDescr != null ? !univConstitutionDescr.equals(that.univConstitutionDescr) : that.univConstitutionDescr != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (universityBaseId != null ? universityBaseId.hashCode() : 0);
        result = 31 * result + (univName != null ? univName.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (dataTypeName != null ? dataTypeName.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (univConstitutionTitle != null ? univConstitutionTitle.hashCode() : 0);
        result = 31 * result + (univConstitutionImport != null ? univConstitutionImport.hashCode() : 0);
        result = 31 * result + (univConstitutionDescr != null ? univConstitutionDescr.hashCode() : 0);
        return result;
    }
}
