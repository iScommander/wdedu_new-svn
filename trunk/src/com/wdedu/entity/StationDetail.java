package com.wdedu.entity;

import com.jeecms.wdedu.entity.TDataCareerProfession;
import com.jeecms.wdedu.entity.TDataCareerRegion;
import com.jeecms.wdedu.entity.TDataSalaryCareer;

import java.util.List;

public class StationDetail {
    private List<TDataSalaryCareer> salaryCareerList;
    private List<TDataCareerCompanyEntity> percentList;
    private List<TDataCareerCompanyEntity> salaryList;
    private List<TDataCareerCompanyEntity> educationList;
    private List<TDataCareerRegion> regionList;
    private List<TDataCareerProfession> professionList;
    private TDataSalaryCareer salaryCareer;

    public TDataSalaryCareer getSalaryCareer() {
        return salaryCareer;
    }

    public void setSalaryCareer(TDataSalaryCareer salaryCareer) {
        this.salaryCareer = salaryCareer;
    }

    public List<TDataSalaryCareer> getSalaryCareerList() {
        return salaryCareerList;
    }

    public void setSalaryCareerList(List<TDataSalaryCareer> salaryCareerList) {
        this.salaryCareerList = salaryCareerList;
    }

    public List<TDataCareerCompanyEntity> getPercentList() {
        return percentList;
    }

    public void setPercentList(List<TDataCareerCompanyEntity> percentList) {
        this.percentList = percentList;
    }

    public List<TDataCareerCompanyEntity> getSalaryList() {
        return salaryList;
    }

    public void setSalaryList(List<TDataCareerCompanyEntity> salaryList) {
        this.salaryList = salaryList;
    }

    public List<TDataCareerCompanyEntity> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<TDataCareerCompanyEntity> educationList) {
        this.educationList = educationList;
    }

    public List<TDataCareerRegion> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<TDataCareerRegion> regionList) {
        this.regionList = regionList;
    }

    public List<TDataCareerProfession> getProfessionList() {
        return professionList;
    }

    public void setProfessionList(List<TDataCareerProfession> professionList) {
        this.professionList = professionList;
    }
}
