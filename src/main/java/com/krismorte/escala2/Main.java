/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2;

import com.alee.laf.WebLookAndFeel;
import com.krismorte.escala2.model.ConexaoJPA;
import com.krismorte.escala2.util.ConexaoUtil;
import com.krismorte.escala2.view.Tela;
import java.util.Properties;
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

                boolean conexaoOk = false;
                Properties prop = null;
                if (Conf.fileExists()) {
                    conexaoOk = true;
                    prop = Conf.load();
                }
                if (conexaoOk) {
                    try {
                        ConexaoJPA conexaoJPA = ConexaoJPA.loadFromProperties(prop);
                        conexaoOk = ConexaoUtil.test(conexaoJPA);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                Tela tela = new Tela(conexaoOk);
                tela.setLocationRelativeTo(null);
                tela.setVisible(true);
            }
        });

    }

}
