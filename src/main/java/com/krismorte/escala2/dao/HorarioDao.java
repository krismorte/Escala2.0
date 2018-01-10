/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.dao;

import com.krismorte.escala2.model.Equipe;
import com.krismorte.escala2.model.Horario;
import java.util.List;

/**
 *
 * @author c007329
 */
public class HorarioDao extends GenericDao<Horario> {

    public HorarioDao() {
        super(Horario.class);
    }

    public List<Horario> listByEquipe(Equipe equipe) {
        return getEntityManager().createQuery("select eh.horario from EquipeHorario eh where eh.equipe=:equipe",
                Horario.class)
                .setParameter("equipe", equipe)
                .getResultList();

    }

}
