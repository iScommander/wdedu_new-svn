package com.wdedu.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_data_career_map_major", schema = "wodecareer", catalog = "")
public class TDataCareerMapMajorEntity {
    private String jobCat;
    private String jobSmall;
    private String jobName;
    private String majorName;
    private String majorCode;
    private String mapId;
    private int id;

    @Basic
    @Column(name = "job_cat")
    public String getJobCat() {
        return jobCat;
    }

    public void setJobCat(String jobCat) {
        this.jobCat = jobCat;
    }

    @Basic
    @Column(name = "job_small")
    public String getJobSmall() {
        return jobSmall;
    }

    public void setJobSmall(String jobSmall) {
        this.jobSmall = jobSmall;
    }

    @Basic
    @Column(name = "job_name")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Basic
    @Column(name = "major_name")
    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Basic
    @Column(name = "major_code")
    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    @Basic
    @Column(name = "map_id")
    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDataCareerMapMajorEntity that = (TDataCareerMapMajorEntity) o;

        if (jobCat != null ? !jobCat.equals(that.jobCat) : that.jobCat != null) return false;
        if (jobSmall != null ? !jobSmall.equals(that.jobSmall) : that.jobSmall != null) return false;
        if (jobName != null ? !jobName.equals(that.jobName) : that.jobName != null) return false;
        if (majorName != null ? !majorName.equals(that.majorName) : that.majorName != null) return false;
        if (majorCode != null ? !majorCode.equals(that.majorCode) : that.majorCode != null) return false;
        if (mapId != null ? !mapId.equals(that.mapId) : that.mapId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobCat != null ? jobCat.hashCode() : 0;
        result = 31 * result + (jobSmall != null ? jobSmall.hashCode() : 0);
        result = 31 * result + (jobName != null ? jobName.hashCode() : 0);
        result = 31 * result + (majorName != null ? majorName.hashCode() : 0);
        result = 31 * result + (majorCode != null ? majorCode.hashCode() : 0);
        result = 31 * result + (mapId != null ? mapId.hashCode() : 0);
        return result;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
