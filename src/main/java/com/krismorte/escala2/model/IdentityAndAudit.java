/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.model;

import com.towel.el.annotation.Resolvable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author c007329
 */
@MappedSuperclass
public abstract class IdentityAndAudit implements Serializable, AuditableEnitity {

    @Resolvable(colName = "ID")
    @Id
    private final String id;
    @Resolvable(colName = "Data Criação")
    private LocalDateTime createTime;
    @Resolvable(colName = "Data Atualização")
    private LocalDateTime updateTime;
    @Resolvable(colName = "Ativo")
    private boolean active;

    public IdentityAndAudit() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    @Override
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean getActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IdentityAndAudit identity = (IdentityAndAudit) o;
        return Objects.equals(id, identity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[id=" + id.substring(0, 8) + "...]";
    }
}
