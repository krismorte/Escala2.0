/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2;

import com.alee.laf.WebLookAndFeel;
import com.krismorte.escala2.view.Tela;
import javax.swing.SwingUtilities;

/**
 *
 * @author c007329
 */
public class Main {

    public static void main(String[] args) {
        /*
        
        Cadatro equipes
        adicionar horario e vagas por horarios entre senrio e pleno
        novatos nao podem ficar aos domingos
        
        adicao de analistas com equipe
        flag de pleno senior e novato
        
        alterar tela de cadastro de equipe remvoer o list para uma outra tela e adicionar uma jtable com campo editavel
        
         */

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                WebLookAndFeel.install();
                Tela tela = new Tela();
                tela.setLocationRelativeTo(null);
                tela.setVisible(true);
            }
        });

    }

}
