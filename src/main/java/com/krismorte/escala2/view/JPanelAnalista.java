/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.view;

import com.krismorte.escala2.model.Analista;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author c007329
 */
public class JPanelAnalista extends JPanel implements ActionListener {

    private DNDLabelPanel panelDia;
    private String texto;
    private Analista analista;

    public JPanelAnalista(DNDLabelPanel panel, Analista analista) {
        this.setLayout(new GridLayout(1, 2));
        this.panelDia = panel;
        this.analista = analista;
        this.texto = analista.getNome();
        //this.add(new JLabelAnalista(analista));
        this.add(JLabelAnalista.deafult(analista));

        JButton btn = new JButton("remover");
        btn.addActionListener(this);
        this.add(btn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("com.krismorte.escala2.view.JPanelAnalista.actionPerformed()");
        panelDia.removeAnalista(this);
    }

    public String getTexto() {
        return texto;
    }

    public Analista getAnalista() {
        return analista;
    }
    
    
}
