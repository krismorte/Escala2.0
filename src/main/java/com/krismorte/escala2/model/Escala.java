/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author c007329
 */
@Entity
@Table(name = "dbo.scl_escala")
public class Escala extends IdentityAndAudit {

    private LocalDate data;
    @OneToOne
    private Analista analista;
    @OneToOne
    private Equipe equipe;
    @OneToOne
    private Horario horario;

    public Escala() {
    }

    /**
     * @return the data
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * @return the analista
     */
    public Analista getAnalista() {
        return analista;
    }

    /**
     * @param analista the analista to set
     */
    public void setAnalista(Analista analista) {
        this.analista = analista;
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

}
