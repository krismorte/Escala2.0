/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.view;

import com.krismorte.escala2.model.Analista;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

/**
 *
 * @author c007329
 */
public class JLabelAnalista extends JLabel {

    private Analista analista;

    private JLabelAnalista(Analista analista) {
        super(analista.getNome());
        this.analista = analista;

    }

    public static JLabelAnalista deafult(Analista analista) {
        return new JLabelAnalista(analista);
    }

    public static JLabelAnalista transferable(Analista analista) {
        JLabelAnalista label = new JLabelAnalista(analista);
        final String propertyName = "text";
        label.setTransferHandler(new TransferHandler(propertyName));

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                JComponent comp = (JComponent) evt.getSource();
                TransferHandler th = comp.getTransferHandler();

                th.exportAsDrag(comp, evt, TransferHandler.COPY);
            }
        });
        return label;
    }

}
