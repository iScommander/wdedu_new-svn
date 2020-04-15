package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_call", schema = "wodecareer", catalog = "")
public class TNCall {
    private int id;
    private Integer userId;
    private String copyNo;
    private String callTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userId")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "copyNo")
    public String getCopyNo() {
        return copyNo;
    }

    public void setCopyNo(String copyNo) {
        this.copyNo = copyNo;
    }

    @Basic
    @Column(name = "callTime")
    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TNCall tnCall = (TNCall) o;
        return id == tnCall.id &&
                Objects.equals(userId, tnCall.userId) &&
                Objects.equals(copyNo, tnCall.copyNo) &&
                Objects.equals(callTime, tnCall.callTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, copyNo, callTime);
    }
}
