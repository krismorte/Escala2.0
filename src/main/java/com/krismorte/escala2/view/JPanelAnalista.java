/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author c007329
 */
public class JPanelAnalista extends JPanel implements ActionListener {

    private DNDLabelPanel panelDia;
    private String texto;

    public JPanelAnalista(DNDLabelPanel panel, String texto) {
        this.setLayout(new GridLayout(1, 2));
        this.panelDia = panel;
        this.texto = texto;
        this.add(new JLabel(texto));
        JButton btn = new JButton("remover");
        btn.addActionListener(this);
        this.add(btn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panelDia.removeAnalista(this);
    }

    public String getTexto() {
        return texto;
    }

}
