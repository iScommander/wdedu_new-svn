package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
@Entity
@Table(name = "t_n_code_mas", schema = "wodecareer", catalog = "")
public class TNCodeMas {
    private int id;
    private String key;
    private String name;
    private Integer pid;
    private String status;
    private Integer order;
    private String code;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "KEY")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "PID")
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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
    @Column(name = "ORDER")
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TNCodeMas tnCodeMas = (TNCodeMas) o;
        return id == tnCodeMas.id &&
                Objects.equals(key, tnCodeMas.key) &&
                Objects.equals(name, tnCodeMas.name) &&
                Objects.equals(pid, tnCodeMas.pid) &&
                Objects.equals(status, tnCodeMas.status) &&
                Objects.equals(order, tnCodeMas.order) &&
                Objects.equals(code, tnCodeMas.code);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, key, name, pid, status, order, code);
    }
}
