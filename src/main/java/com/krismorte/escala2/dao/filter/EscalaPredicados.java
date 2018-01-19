/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.dao.filter;

import com.krismorte.escala2.model.Equipe;
import com.krismorte.escala2.model.Escala;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author c007329
 */
public class EscalaPredicados {

    private HashMap<String, Object> parametros;

    public EscalaPredicados() {
        parametros = new HashMap();
    }

    public void addParametro(String key, Object valule) {
        parametros.put(key, valule);
    }

    public void removeParametro(String key) {
        parametros.remove(key);
    }

    public void limparParametro() {
        parametros.clear();
    }

    public CriteriaQuery listarEscalasMesEquipe(CriteriaBuilder builder, LocalDate dataInicial, LocalDate dataFinal, Equipe equipe) {
        CriteriaQuery<Escala> query = builder.createQuery(Escala.class);
        Root<Escala> entity = query.from(Escala.class);
        List<Predicate> predList = new LinkedList<>();
        Predicate predicate = builder.between(entity.get("data"), dataInicial, dataFinal);
        predList.add(predicate);
        Predicate predicate2 = builder.and(builder.equal(entity.get("equipe"), equipe));
        predList.add(predicate2);
        parametros.forEach((t, u) -> {

        });
        Predicate[] predArray = new Predicate[predList.size()];
        predList.toArray(predArray);
        query.where(predArray);
        return query;
    }

    public CriteriaDelete processaDelete(CriteriaBuilder builder) {
        CriteriaDelete<Escala> delete = builder.createCriteriaDelete(Escala.class);
        Root<Escala> entity = delete.from(Escala.class);
        List<Predicate> predList = new LinkedList<>();
        parametros.forEach((t, u) -> {
            Predicate predicate = builder.and(builder.equal(entity.get(t), u));
            predList.add(predicate);
        });
        Predicate[] predArray = new Predicate[predList.size()];
        predList.toArray(predArray);
        delete.where(predArray);
        return delete;
    }

}
