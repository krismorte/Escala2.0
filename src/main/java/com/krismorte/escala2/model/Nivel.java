/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.model;

/**
 *
 * @author c007329
 */
public enum Nivel {

    Pleno("Pleno"), Senior("Senior");

    private String descricao;

    private Nivel(String descricao) {
        this.descricao = descricao;
    }

}
