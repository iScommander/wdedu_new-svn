package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_contest", schema = "wodecareer", catalog = "")
public class TNContest {
    private int id;
    private String studentName;
    private String session;
    private String contestType;
    private String contestName;
    private String contestCategory;
    private String province;
    private String school;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "STUDENT_NAME")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Basic
    @Column(name = "SESSION")
    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Basic
    @Column(name = "CONTEST_TYPE")
    public String getContestType() {
        return contestType;
    }

    public void setContestType(String contestType) {
        this.contestType = contestType;
    }

    @Basic
    @Column(name = "CONTEST_NAME")
    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    @Basic
    @Column(name = "CONTEST_CATEGORY")
    public String getContestCategory() {
        return contestCategory;
    }

    public void setContestCategory(String contestCategory) {
        this.contestCategory = contestCategory;
    }

    @Basic
    @Column(name = "PROVINCE")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "SCHOOL")
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TNContest tnContest = (TNContest) o;
        return id == tnContest.id &&
                Objects.equals(studentName, tnContest.studentName) &&
                Objects.equals(session, tnContest.session) &&
                Objects.equals(contestType, tnContest.contestType) &&
                Objects.equals(contestName, tnContest.contestName) &&
                Objects.equals(contestCategory, tnContest.contestCategory) &&
                Objects.equals(province, tnContest.province) &&
                Objects.equals(school, tnContest.school);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, studentName, session, contestType, contestName, contestCategory, province, school);
    }
}
