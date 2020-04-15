package com.jeecms.wdedu.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author yangchao
 * @ProjectName wdedu

 * @date 2018/10/17
 */
public class TNFamilyPK implements Serializable {
    private int id;
    private String name;
    private String userAccount;

    @Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "userAccount")
    @Id
    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TNFamilyPK that = (TNFamilyPK) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(userAccount, that.userAccount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, userAccount);
    }
}
