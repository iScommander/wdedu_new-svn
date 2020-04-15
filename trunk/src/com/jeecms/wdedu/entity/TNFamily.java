package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_family", schema = "wodecareer", catalog = "")
@IdClass(TNFamilyPK.class)
public class TNFamily {
    private int id;
    private String name;
    private String relative;
    private String userAccount;
    private String telephone;
    private String educationLevel;
    private String job;
    private String office;
    private String vote;
    private String userAccout;
    private Integer userId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "relative")
    public String getRelative() {
        return relative;
    }

    public void setRelative(String relative) {
        this.relative = relative;
    }

    @Id
    @Column(name = "userAccount")
    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Basic
    @Column(name = "Telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "educationLevel")
    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    @Basic
    @Column(name = "job")
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Basic
    @Column(name = "office")
    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    @Basic
    @Column(name = "vote")
    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    @Basic
    @Column(name = "userAccout")
    public String getUserAccout() {
        return userAccout;
    }

    public void setUserAccout(String userAccout) {
        this.userAccout = userAccout;
    }

    @Basic
    @Column(name = "userId")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TNFamily tnFamily = (TNFamily) o;
        return id == tnFamily.id &&
                Objects.equals(name, tnFamily.name) &&
                Objects.equals(relative, tnFamily.relative) &&
                Objects.equals(userAccount, tnFamily.userAccount) &&
                Objects.equals(telephone, tnFamily.telephone) &&
                Objects.equals(educationLevel, tnFamily.educationLevel) &&
                Objects.equals(job, tnFamily.job) &&
                Objects.equals(office, tnFamily.office) &&
                Objects.equals(vote, tnFamily.vote) &&
                Objects.equals(userAccout, tnFamily.userAccout) &&
                Objects.equals(userId, tnFamily.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, relative, userAccount, telephone, educationLevel, job, office, vote, userAccout, userId);
    }
}
