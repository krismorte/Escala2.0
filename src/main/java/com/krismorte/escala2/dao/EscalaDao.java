/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.dao;

import com.krismorte.escala2.model.Analista;
import com.krismorte.escala2.model.Escala;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author c007329
 */
public class EscalaDao extends GenericDao<Escala> {

    public EscalaDao() {
        super(Escala.class);
    }

    /*public int deleteDate(LocalDate data, Analista analista) {
        return getEntityManager().createQuery("delete from Escala e where e.data=:data and e.analista=:analista")
                .setParameter("data", data)
                .setParameter("analista", analista)
                .executeUpdate();

    }*/
    public List<Escala> search(CriteriaQuery query) {
        return getEntityManager().createQuery(query).getResultList();
    }

    public int execUpdate(CriteriaDelete delete) {
        return getEntityManager().createQuery(delete).executeUpdate();
    }

}
