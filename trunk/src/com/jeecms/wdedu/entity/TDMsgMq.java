package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/30
 */
@Entity
@Table(name = "t_d_msg_mq", schema = "wodecareer", catalog = "")
public class TDMsgMq {
    private int id;
    private Integer type;
    private String phone;
    private String content;
    private Timestamp createDate;
    private Timestamp sendDate;
    private boolean sendState;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "send_date")
    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }

    @Basic
    @Column(name = "send_state")
    public boolean isSendState() {
        return sendState;
    }

    public void setSendState(boolean sendState) {
        this.sendState = sendState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TDMsgMq tdMsgMq = (TDMsgMq) o;
        return id == tdMsgMq.id &&
                sendState == tdMsgMq.sendState &&
                Objects.equals(type, tdMsgMq.type) &&
                Objects.equals(phone, tdMsgMq.phone) &&
                Objects.equals(content, tdMsgMq.content) &&
                Objects.equals(createDate, tdMsgMq.createDate) &&
                Objects.equals(sendDate, tdMsgMq.sendDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, phone, content, createDate, sendDate, sendState);
    }
}
