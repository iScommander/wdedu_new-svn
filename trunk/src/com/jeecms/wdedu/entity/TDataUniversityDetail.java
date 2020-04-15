package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/25
 */
@Entity
@Table(name = "t_data_university_detail", schema = "wodecareer", catalog = "")
public class TDataUniversityDetail {
    private int id;
    private String univName;
    private Integer zszcYear;
    private String zszcYd;
    private String zszc;
    private String kyjg;
    private String kycg;
    private String xszy;
    private String yxsz;
    private String jxjs;
    private String xkjs;
    private String szll;
    private String xxpm;
    private String xyhj;
    private String zmxy;
    private String xxtc;
    private String zwhzbx;
    private String zzzsZc;
    private String url;
    private String urlVideo;
    private String uriMajor;
    private String mainlab;
    private String descr;
    private String studentInfo;
    private String teacherInfo;
    private String famousTeacherInfo;
    private String majorInfo;
    private String employmentInfo;
    private String pictureName;
    private Boolean typeIdVerified;
    private Boolean belongIdVerified;
    private String email;
    private Timestamp addDate;
    private String universityGrade;
    private String jobStat;
    private String logoUrl;
    private String descrShort;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "zszc_year")
    public Integer getZszcYear() {
        return zszcYear;
    }

    public void setZszcYear(Integer zszcYear) {
        this.zszcYear = zszcYear;
    }

    @Basic
    @Column(name = "zszc_yd")
    public String getZszcYd() {
        return zszcYd;
    }

    public void setZszcYd(String zszcYd) {
        this.zszcYd = zszcYd;
    }

    @Basic
    @Column(name = "zszc")
    public String getZszc() {
        return zszc;
    }

    public void setZszc(String zszc) {
        this.zszc = zszc;
    }

    @Basic
    @Column(name = "kyjg")
    public String getKyjg() {
        return kyjg;
    }

    public void setKyjg(String kyjg) {
        this.kyjg = kyjg;
    }

    @Basic
    @Column(name = "kycg")
    public String getKycg() {
        return kycg;
    }

    public void setKycg(String kycg) {
        this.kycg = kycg;
    }

    @Basic
    @Column(name = "xszy")
    public String getXszy() {
        return xszy;
    }

    public void setXszy(String xszy) {
        this.xszy = xszy;
    }

    @Basic
    @Column(name = "yxsz")
    public String getYxsz() {
        return yxsz;
    }

    public void setYxsz(String yxsz) {
        this.yxsz = yxsz;
    }

    @Basic
    @Column(name = "jxjs")
    public String getJxjs() {
        return jxjs;
    }

    public void setJxjs(String jxjs) {
        this.jxjs = jxjs;
    }

    @Basic
    @Column(name = "xkjs")
    public String getXkjs() {
        return xkjs;
    }

    public void setXkjs(String xkjs) {
        this.xkjs = xkjs;
    }

    @Basic
    @Column(name = "szll")
    public String getSzll() {
        return szll;
    }

    public void setSzll(String szll) {
        this.szll = szll;
    }

    @Basic
    @Column(name = "xxpm")
    public String getXxpm() {
        return xxpm;
    }

    public void setXxpm(String xxpm) {
        this.xxpm = xxpm;
    }

    @Basic
    @Column(name = "xyhj")
    public String getXyhj() {
        return xyhj;
    }

    public void setXyhj(String xyhj) {
        this.xyhj = xyhj;
    }

    @Basic
    @Column(name = "zmxy")
    public String getZmxy() {
        return zmxy;
    }

    public void setZmxy(String zmxy) {
        this.zmxy = zmxy;
    }

    @Basic
    @Column(name = "xxtc")
    public String getXxtc() {
        return xxtc;
    }

    public void setXxtc(String xxtc) {
        this.xxtc = xxtc;
    }

    @Basic
    @Column(name = "zwhzbx")
    public String getZwhzbx() {
        return zwhzbx;
    }

    public void setZwhzbx(String zwhzbx) {
        this.zwhzbx = zwhzbx;
    }

    @Basic
    @Column(name = "zzzs_zc")
    public String getZzzsZc() {
        return zzzsZc;
    }

    public void setZzzsZc(String zzzsZc) {
        this.zzzsZc = zzzsZc;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "urlVideo")
    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    @Basic
    @Column(name = "uriMajor")
    public String getUriMajor() {
        return uriMajor;
    }

    public void setUriMajor(String uriMajor) {
        this.uriMajor = uriMajor;
    }

    @Basic
    @Column(name = "mainlab")
    public String getMainlab() {
        return mainlab;
    }

    public void setMainlab(String mainlab) {
        this.mainlab = mainlab;
    }

    @Basic
    @Column(name = "descr")
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Basic
    @Column(name = "studentInfo")
    public String getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(String studentInfo) {
        this.studentInfo = studentInfo;
    }

    @Basic
    @Column(name = "teacherInfo")
    public String getTeacherInfo() {
        return teacherInfo;
    }

    public void setTeacherInfo(String teacherInfo) {
        this.teacherInfo = teacherInfo;
    }

    @Basic
    @Column(name = "famousTeacherInfo")
    public String getFamousTeacherInfo() {
        return famousTeacherInfo;
    }

    public void setFamousTeacherInfo(String famousTeacherInfo) {
        this.famousTeacherInfo = famousTeacherInfo;
    }

    @Basic
    @Column(name = "majorInfo")
    public String getMajorInfo() {
        return majorInfo;
    }

    public void setMajorInfo(String majorInfo) {
        this.majorInfo = majorInfo;
    }

    @Basic
    @Column(name = "employmentInfo")
    public String getEmploymentInfo() {
        return employmentInfo;
    }

    public void setEmploymentInfo(String employmentInfo) {
        this.employmentInfo = employmentInfo;
    }

    @Basic
    @Column(name = "pictureName")
    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    @Basic
    @Column(name = "typeIdVerified")
    public Boolean getTypeIdVerified() {
        return typeIdVerified;
    }

    public void setTypeIdVerified(Boolean typeIdVerified) {
        this.typeIdVerified = typeIdVerified;
    }

    @Basic
    @Column(name = "belongIdVerified")
    public Boolean getBelongIdVerified() {
        return belongIdVerified;
    }

    public void setBelongIdVerified(Boolean belongIdVerified) {
        this.belongIdVerified = belongIdVerified;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "addDate")
    public Timestamp getAddDate() {
        return addDate;
    }

    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }

    @Basic
    @Column(name = "universityGrade")
    public String getUniversityGrade() {
        return universityGrade;
    }

    public void setUniversityGrade(String universityGrade) {
        this.universityGrade = universityGrade;
    }

    @Basic
    @Column(name = "jobStat")
    public String getJobStat() {
        return jobStat;
    }

    public void setJobStat(String jobStat) {
        this.jobStat = jobStat;
    }

    @Basic
    @Column(name = "logoUrl")
    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @Basic
    @Column(name = "descrShort")
    public String getDescrShort() {
        return descrShort;
    }

    public void setDescrShort(String descrShort) {
        this.descrShort = descrShort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TDataUniversityDetail that = (TDataUniversityDetail) o;
        return id == that.id &&
                Objects.equals(univName, that.univName) &&
                Objects.equals(zszcYear, that.zszcYear) &&
                Objects.equals(zszcYd, that.zszcYd) &&
                Objects.equals(zszc, that.zszc) &&
                Objects.equals(kyjg, that.kyjg) &&
                Objects.equals(kycg, that.kycg) &&
                Objects.equals(xszy, that.xszy) &&
                Objects.equals(yxsz, that.yxsz) &&
                Objects.equals(jxjs, that.jxjs) &&
                Objects.equals(xkjs, that.xkjs) &&
                Objects.equals(szll, that.szll) &&
                Objects.equals(xxpm, that.xxpm) &&
                Objects.equals(xyhj, that.xyhj) &&
                Objects.equals(zmxy, that.zmxy) &&
                Objects.equals(xxtc, that.xxtc) &&
                Objects.equals(zwhzbx, that.zwhzbx) &&
                Objects.equals(zzzsZc, that.zzzsZc) &&
                Objects.equals(url, that.url) &&
                Objects.equals(urlVideo, that.urlVideo) &&
                Objects.equals(uriMajor, that.uriMajor) &&
                Objects.equals(mainlab, that.mainlab) &&
                Objects.equals(descr, that.descr) &&
                Objects.equals(studentInfo, that.studentInfo) &&
                Objects.equals(teacherInfo, that.teacherInfo) &&
                Objects.equals(famousTeacherInfo, that.famousTeacherInfo) &&
                Objects.equals(majorInfo, that.majorInfo) &&
                Objects.equals(employmentInfo, that.employmentInfo) &&
                Objects.equals(pictureName, that.pictureName) &&
                Objects.equals(typeIdVerified, that.typeIdVerified) &&
                Objects.equals(belongIdVerified, that.belongIdVerified) &&
                Objects.equals(email, that.email) &&
                Objects.equals(addDate, that.addDate) &&
                Objects.equals(universityGrade, that.universityGrade) &&
                Objects.equals(jobStat, that.jobStat) &&
                Objects.equals(logoUrl, that.logoUrl) &&
                Objects.equals(descrShort, that.descrShort);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, univName, zszcYear, zszcYd, zszc, kyjg, kycg, xszy, yxsz, jxjs, xkjs, szll, xxpm, xyhj, zmxy, xxtc, zwhzbx, zzzsZc, url, urlVideo, uriMajor, mainlab, descr, studentInfo, teacherInfo, famousTeacherInfo, majorInfo, employmentInfo, pictureName, typeIdVerified, belongIdVerified, email, addDate, universityGrade, jobStat, logoUrl, descrShort);
    }
}
