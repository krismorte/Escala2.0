/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.model;

import com.towel.el.annotation.Resolvable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author c007329
 */
@Entity
@Table(name = "scl_horario")
public class Horario extends IdentityAndAudit {

    @Resolvable(colName = "Descrição")
    @Column(length = 30)
    private String descricao;
    private int dia;
    @Resolvable(colName = "Horario Inicial")
    @Column(length = 10)
    private String horarioInicial;
    @Resolvable(colName = "Horario Final")
    @Column(length = 10)
    private String horarioFinal;

    public Horario() {        
        
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the dia
     */
    public int getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * @return the horarioInicial
     */
    public String getHorarioInicial() {
        return horarioInicial;
    }

    /**
     * @param horarioInicial the horarioInicial to set
     */
    public void setHorarioInicial(String horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    /**
     * @return the horarioFinal
     */
    public String getHorarioFinal() {
        return horarioFinal;
    }

    /**
     * @param horarioFinal the horarioFinal to set
     */
    public void setHorarioFinal(String horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    @Override
    public String toString() {
        return diaToString() + ": " + getDescricao();
    }

    public String diaToString() {
        switch (dia) {
            case 0:
                return "DOM";
            case 1:
                return "SEG";
            case 2:
                return "TER";
            case 3:
                return "QUA";
            case 4:
                return "QUI";
            case 5:
                return "SEX";
            case 6:
                return "SAB";
            default:
                return "ERRO";
        }
    }

    private String diaToString(int dia) {
        switch (dia) {
            case 0:
                return "DOM";
            case 1:
                return "SEG";
            case 2:
                return "TER";
            case 3:
                return "QUA";
            case 4:
                return "QUI";
            case 5:
                return "SEX";
            case 6:
                return "SAB";
            default:
                return "ERRO";
        }
    }

}
