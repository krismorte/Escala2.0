/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.dragndrop;

import com.krismorte.escala2.model.Analista;
import javax.swing.JPanel;

/**
 *
 * @author c007329
 */
public interface iDropAction {

    public void addJPanel(JPanel jPanel);

    public void addAnalista(Analista analista);

    public void process();

}
