package com.krismorte.escala2.view;


import com.krismorte.dragndrop.iDropAction;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author c007329
 */
public class AcaoJPanel implements iDropAction {

    private JPanel panel;
    private JPanelDia panelDia;
    private String text;

    public AcaoJPanel(JPanelDia panelDia) {
        this.panelDia = panelDia;
    }

    @Override
    public void process() {
        if (panel.getComponents().length > 0) {
            for (Component c : panel.getComponents()) {
                if (c instanceof JLabel) {
                    JLabel label = (JLabel) c;
                    if (label.getText().equals(text)) {
                        JOptionPane.showMessageDialog(null, "Nome \"" + text + "\" já adicionado a essa horário");
                        return;
                    }
                }
            }
        }
        if (panelDia.addParticipante(text)) {
            //panel.add(new JLabel(text));
            //panel.add(new JPanelAnalista(panelDia,text));
            panel.validate();
        }

    }

    @Override
    public void addJPanel(JPanel jPanel) {
        this.panel = jPanel;
    }

    @Override
    public void addText(String text) {
        this.text = text;
    }

}

