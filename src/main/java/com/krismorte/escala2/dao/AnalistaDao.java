/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.dao;

import com.krismorte.escala2.model.Analista;
import com.krismorte.escala2.model.Equipe;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author c007329
 */
public class AnalistaDao extends GenericDao<Analista> {

    public AnalistaDao() {
        super(Analista.class);
    }

    public List<Analista> listByEquipe(Equipe equipe) {
        List<Analista> entities = new ArrayList<>();

        try {
            entities = getEntityManager().createQuery("select t from  Analista t "
                    + "where t.equipe=:equipe",
                    Analista.class)
                    .setParameter("equipe", equipe)
                    .getResultList();
        } catch (NoResultException ex) {
            return entities;
        }
        return entities;
    }

}
