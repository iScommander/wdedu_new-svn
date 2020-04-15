package com.jeecms.wdedu.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TScActiveDetailPK implements Serializable {
    private int id;
    private int activeId;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "active_Id", nullable = false)
    @Id
    public int getActiveId() {
        return activeId;
    }

    public void setActiveId(int activeId) {
        this.activeId = activeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TScActiveDetailPK that = (TScActiveDetailPK) o;
        return id == that.id &&
                activeId == that.activeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activeId);
    }
}
