/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.view;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.COPY;

/**
 *
 * @author c007329
 */
public class DNDLabelPanel extends JPanel {

    private JPanelDia panelDia;

    public DNDLabelPanel(JPanelDia panelDia, String title) {
        this.panelDia = panelDia;
        this.setBorder(BorderFactory.createTitledBorder(title));
        this.setLayout(new GridLayout(5, 1));
        this.setFocusable(true);
        this.setOpaque(false);
        this.setTransferHandler(new TextHandler());
    }

    public void removeAnalista(JPanelAnalista panelAnalista) {
        panelDia.removerParticipante(panelAnalista.getTexto());
        this.remove(panelAnalista);
        this.validate();
        this.repaint();
    }

    class TextHandler extends TransferHandler {

        public boolean canImport(TransferHandler.TransferSupport info) {
            return getPropertyDataFlavor(String.class, info.getDataFlavors()) != null;
        }

        public boolean importData(TransferHandler.TransferSupport info) {

            if (!info.isDrop()) {
                return false;
            }

            // Get the string that is being dropped.
            Transferable t = info.getTransferable();
            String data = "";
            try {

                data = (String) t.getTransferData(getPropertyDataFlavor(String.class, info.getDataFlavors()));
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

            if (getThis().getComponents().length > 0) {
                for (Component c : getThis().getComponents()) {
                    if (c instanceof JPanelAnalista) {
                        if (((JPanelAnalista) c).getTexto().equals(data)) {
                            JOptionPane.showMessageDialog(null, "Nome \"" + data + "\" já adicionado a essa horário");
                            return false;
                        }
                        /*JLabel label = (JLabel) c;
                        System.out.println("labekl "+label.getText()+" data"+data);
                        if (label.getText().equals(data)) {
                            JOptionPane.showMessageDialog(null, "Nome \"" + data + "\" já adicionado a essa horário");
                            break;
                        }*/
                    }
                }
            }
            if (panelDia.addParticipante(data)) {
                //panel.add(new JLabel(text));
                getThis().add(new JPanelAnalista(getThis(), data));
                getThis().validate();
            }

            /**
             * This is commented out for the basicdemo.html tutorial page. * If
             * you add this code snippet back and delete the * "return false;"
             * line, the list will accept drops * of type string. // Perform the
             * actual import. if (insert) { listModel.add(index, data); } else {
             * listModel.set(index, data); } return true;
             */
            return false;
        }

        public int getSourceActions(JComponent c) {
            return COPY;
        }

        protected Transferable createTransferable(JComponent c) {
            JLabel lab = (JLabel) c;
            return new StringSelection(lab.getText());
        }
    }

    private DNDLabelPanel getThis() {
        return this;
    }

    private DataFlavor getPropertyDataFlavor(Class<?> k, DataFlavor[] flavors) {
        for (int i = 0; i < flavors.length; i++) {
            DataFlavor flavor = flavors[i];
            if ("application".equals(flavor.getPrimaryType())
                    && "x-java-jvm-local-objectref".equals(flavor.getSubType())
                    && k.isAssignableFrom(flavor.getRepresentationClass())) {
                return flavor;
            }
        }
        return null;
    }

}
