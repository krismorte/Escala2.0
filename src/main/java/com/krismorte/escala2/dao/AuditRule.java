/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.dao;

import com.krismorte.escala2.model.AuditableEnitity;
import java.time.LocalDateTime;

/**
 *
 * @author c007329
 */
public class AuditRule {

    private static void createTime(AuditableEnitity entity) {
        entity.setCreateTime(LocalDateTime.now());
        entity.setActive(true);
    }

    private static void updateTime(AuditableEnitity entity) {
        entity.setUpdateTime(LocalDateTime.now());
    }

    public static void audit(AuditableEnitity entity) {
        if (entity.getCreateTime() == null) {
            createTime((AuditableEnitity) entity);
        } else {
            updateTime((AuditableEnitity) entity);
        }
    }
}
