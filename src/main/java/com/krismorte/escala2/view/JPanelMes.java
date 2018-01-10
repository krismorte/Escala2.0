package com.krismorte.escala2.view;

import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
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
public class JPanelMes extends JPanel {

    private List<String> horariosSabado = new ArrayList<>();
    private List<String> horariosDomingo = new ArrayList<>();
    private List<String[]> participante = new ArrayList<>();
    private int maximoFrequenciaEscala = 3;

    public JPanelMes(LocalDate mesAtual) {
        this.setLayout(new GridLayout(2, 7));
        horariosSabado.add("07:00-16:00");
        horariosSabado.add("15:15-00:00");
        horariosDomingo.add("00:00-08:00");
        horariosDomingo.add("15:15-00:00");
        horariosDomingo.add("00:00-08:00");
        updateMoth(mesAtual);
    }

    private void updateMoth(LocalDate data) {
        this.removeAll();
        int ultimoDiaDoMes = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

        this.setLayout(new GridLayout(2, 7));
        this.setOpaque(false);
        int totalSabados = 0;
        int totalDomingos = 0;
        for (int i = 1; i <= ultimoDiaDoMes; i++) {
            if (data.getDayOfWeek().toString().equals("SATURDAY")) {
                this.add(new JPanelDia(this, data, horariosSabado));
                totalSabados++;
            } else if (data.getDayOfWeek().toString().equals("SUNDAY")) {
                this.add(new JPanelDia(this, data, horariosDomingo));
                totalDomingos++;
            }

            data = data.plusDays(1);
        }
        if (totalSabados == 5 | totalDomingos == 5) {//5 finais de semana no mes
            maximoFrequenciaEscala++;
        }
    }

    public boolean addParticipante(JPanelDia panelDia, String text) {
        if(contaParticipante(text)==maximoFrequenciaEscala){
            JOptionPane.showMessageDialog(null, "Participante \"" + text + "\" já foi adicionado "+maximoFrequenciaEscala+" vezes");
            return false;
        }
        boolean jaExiste = encontraParticipante(panelDia.getTitle(), text);
        if (jaExiste) {
            JOptionPane.showMessageDialog(null, "Participante \"" + text + "\" já adicionado a essa dia");
            return false;
        }
        if (panelDia.eSabado()) {
            LocalDate nextData = panelDia.getData().plusDays(1);
            jaExiste = encontraParticipante(JPanelDia.titleToString(nextData), text);
        }
        if (panelDia.eDomingo()) {
            LocalDate nextData = panelDia.getData().minusDays(1);
            jaExiste = encontraParticipante(JPanelDia.titleToString(nextData), text);
        }
        if (jaExiste) {
            JOptionPane.showMessageDialog(null, "Participante \"" + text + "\" já adicionado a essa final de semana");
            return false;
        } else {
            String[] array = new String[2];
            array[0] = panelDia.getTitle();
            array[1] = text;
            participante.add(array);
        }
        return true;
    }

    private boolean encontraParticipante(String title, String text) {
        for (String[] s : participante) {
            if (s[0].equals(title) & s[1].equals(text)) {
                return true;
            }
        }

        return false;
    }

    private int contaParticipante(String text) {
        int frequencia = 0;
        for (String[] s : participante) {
            if (s[1].equals(text)) {
                frequencia++;
            }
        }

        return frequencia;
    }

    public void removerParticipante(String dia, String text) {
        for (String[] s : participante) {
            if (s[0].equals(dia) & s[1].equals(text)) {
                participante.remove(s);
                break;
            }
        }
    }

    public List<String[]> getParticipante() {
        return participante;
    }

}
