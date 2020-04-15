package com.jeecms.wdedu.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/22
 */
@Entity
@Table(name = "t_qst_c", schema = "wodecareer", catalog = "")
public class TQstC {
    private int id;
    private String type;
    private String result;

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
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "result")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TQstC tQstC = (TQstC) o;
        return id == tQstC.id &&
                Objects.equals(type, tQstC.type) &&
                Objects.equals(result, tQstC.result);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, result);
    }
}
