/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.model;

import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author c007329
 */
@Entity
@Table(name = "dbo.scl_equipe")
public class Equipe extends IdentityAndAudit {

    @Column(length = 70)
    private String nome;
    @OneToMany
    private List<EquipeHorario> equipeHorarios;
    @Transient
    private int totalHorarios;

    public Equipe() {
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the totalHorarios
     */
    public int getTotalHorarios() {
        return totalHorarios;
    }

    /**
     * @param totalHorarios the totalHorarios to set
     */
    public void setTotalHorarios(int totalHorarios) {
        this.totalHorarios = totalHorarios;
    }

    /**
     * @return the equipeHorarios
     */
    public List<EquipeHorario> getEquipeHorarios() {
        return equipeHorarios;
    }

    /**
     * @param equipeHorarios the equipeHorarios to set
     */
    public void setEquipeHorarios(List<EquipeHorario> equipeHorarios) {
        this.equipeHorarios = equipeHorarios;
    }


}
