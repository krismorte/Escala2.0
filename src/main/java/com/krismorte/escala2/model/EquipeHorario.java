/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author c007329
 */
@Entity
@Table(name = "dbo.scl_equipe_horario")
public class EquipeHorario extends IdentityAndAudit {

    @ManyToOne
    private Equipe equipe;
    @OneToOne
    private Horario horario;
    private int vagasSenior;
    private int vagasPleno;

    public EquipeHorario() {
    }

    /**
     * @return the equipe
     */
    public Equipe getEquipe() {
        return equipe;
    }

    /**
     * @param equipe the equipe to set
     */
    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    /**
     * @return the horario
     */
    public Horario getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    /**
     * @return the vagasSenior
     */
    public int getVagasSenior() {
        return vagasSenior;
    }

    /**
     * @param vagasSenior the vagasSenior to set
     */
    public void setVagasSenior(int vagasSenior) {
        this.vagasSenior = vagasSenior;
    }

    /**
     * @return the vagasPleno
     */
    public int getVagasPleno() {
        return vagasPleno;
    }

    /**
     * @param vagasPleno the vagasPleno to set
     */
    public void setVagasPleno(int vagasPleno) {
        this.vagasPleno = vagasPleno;
    }

}
