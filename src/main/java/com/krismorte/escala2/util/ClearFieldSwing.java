/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.util;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 * Class that will have comon methods for several classes. Have just one
 * constructor whithout parameter.
 *
 * @author Krisnamourt
 */
public final class ClearFieldSwing implements ClearField {

    public ClearFieldSwing() {
    }

    @Override
    public boolean erase(Object container) {
        Component componentes[] = null;
        if (container instanceof JFrame) {
            componentes = ((JPanel) ((JFrame) container).getContentPane()).getComponents();
        } else if (container instanceof JDialog) {
            componentes = ((JPanel) ((JDialog) container).getContentPane()).getComponents();
        } else if (container instanceof JInternalFrame) {
            componentes = ((JPanel) ((JInternalFrame) container).getContentPane()).getComponents();
        } else if (container instanceof JPanel) {
            componentes = ((JPanel) container).getComponents();
        }
        if (componentes != null) {
            for (Component c : componentes) {
                if (c instanceof JTextField || c instanceof JTextArea || c instanceof JEditorPane) {
                    ((JTextComponent) c).setText("");
                } else if (c instanceof JComboBox) {
                    ((JComboBox) c).setSelectedItem(null);
                } else if (c instanceof JList) {
                    ((JList) c).removeAll();
                } else if (c instanceof JCheckBox) {
                    ((JCheckBox) c).setSelected(false);
                } else if (c instanceof JRadioButton) {
                    if (((JRadioButton) c).getActionCommand().equals("Limpa")) {
                        ((JRadioButton) c).setSelected(true);
                    }
                } else if (c instanceof JPanel) {
                    erase((JPanel) c);
                } else if (c instanceof JScrollPane) {
                    erase((JScrollPane) c);
                } else if (c instanceof JScrollBar) {
                    erase((JScrollBar) c);
                }
            }
        }
        return true;

    }

}
