package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_message", schema = "wodecareer", catalog = "")
public class TNMessage {
    private int id;
    private int userId;
    private String title;
    private String content;
    private String receiveTime;
    private String status;
    private String readTime;
    private String reveiveTime;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER_ID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "RECEIVE_TIME")
    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    @Basic
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "READ_TIME")
    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }

    @Basic
    @Column(name = "reveive_time")
    public String getReveiveTime() {
        return reveiveTime;
    }

    public void setReveiveTime(String reveiveTime) {
        this.reveiveTime = reveiveTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TNMessage tnMessage = (TNMessage) o;
        return id == tnMessage.id &&
                userId == tnMessage.userId &&
                Objects.equals(title, tnMessage.title) &&
                Objects.equals(content, tnMessage.content) &&
                Objects.equals(receiveTime, tnMessage.receiveTime) &&
                Objects.equals(status, tnMessage.status) &&
                Objects.equals(readTime, tnMessage.readTime) &&
                Objects.equals(reveiveTime, tnMessage.reveiveTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, title, content, receiveTime, status, readTime, reveiveTime);
    }
}
