/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.util;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;

/**
 *
 * @author krisnamourtscf
 */
public class MouseEventUtil extends MouseAdapter {

    private JPopupMenu menu;
    private ActionListener acao;
    private Boolean rightClick;

    public MouseEventUtil(ActionListener acao, Boolean rightClick) {
        this.acao = acao;
        this.rightClick = rightClick;
    }

    public MouseEventUtil(JPopupMenu menu, Boolean rightClick) {
        this.menu = menu;
        this.rightClick = rightClick;
    }

    public void mousePressed(MouseEvent e) {
        checkAction(e);
    }

    public void mouseClicked(MouseEvent e) {
        checkAction(e);
    }

    public void mouseReleased(MouseEvent e) {
        checkAction(e);
    }

    private void checkAction(MouseEvent e) {
        if (e.isPopupTrigger()) {
            //selectedComponent = e.getComponent();
            menu.show(e.getComponent(), e.getX(), e.getY());
        } else {
            if (rightClick) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    acao.actionPerformed(null);
                }
            } else {
                if (e.getClickCount() >= 2) {
                    acao.actionPerformed(null);
                }
            }

        }
    }

}
