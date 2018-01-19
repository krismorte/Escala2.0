package com.krismorte.escala2.view;

import com.krismorte.escala2.model.Analista;
import com.krismorte.escala2.model.Equipe;
import com.krismorte.escala2.model.Escala;
import com.krismorte.escala2.model.Horario;
import com.krismorte.escala2.service.CrudService;
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

    private CrudService crudService = new CrudService();
    private Equipe equipe;
    private List<Escala> escalas;
    private List<Analista> analistas;
    private List<String[]> participante = new ArrayList<>();
    private List<Horario> horarios;
    private int maximoFrequenciaEscala = 3;
    private LocalDate mesInicio;
    private LocalDate mesFim;

    public JPanelMes(LocalDate mesAtual, Equipe equipe, List<Analista> analistas, List<Horario> horarios) {
        this.setLayout(new GridLayout(2, 7));
        this.horarios = horarios;
        this.equipe = equipe;
        this.mesInicio = mesAtual;
        this.analistas = analistas;
        int ultimoDiaDoMes = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        this.mesFim = LocalDate.of(mesAtual.getYear(), mesAtual.getMonthValue(), ultimoDiaDoMes);
        updateMoth(mesAtual);
    }

    private void updateMoth(LocalDate data) {
        this.removeAll();

        this.setLayout(new GridLayout(2, 7));
        this.setOpaque(false);
        int totalSabados = 0;
        int totalDomingos = 0;
        escalas = crudService.listaEscalaPorMes(mesInicio, mesFim,equipe);
        List<Horario> horariosSabado = new ArrayList<>();
        List<Horario> horariosDomingo = new ArrayList<>();
        for (Horario horario : horarios) {
            if (horario.getDia() == 6) {
                horariosSabado.add(horario);
            }
            if (horario.getDia() == 0) {
                horariosDomingo.add(horario);
            }
        }

        List<Escala> escalasPorData = new ArrayList<>();
        for (int i = 1; i <= mesFim.getDayOfMonth(); i++) {
            escalasPorData.clear();
            if (data.getDayOfWeek().toString().equals("SATURDAY")) {
                if (!horariosSabado.isEmpty()) {
                    for (Escala escala : escalas) {
                        if (escala.getData().equals(data)) {
                            escalasPorData.add(escala);
                        }
                    }
                    this.add(new JPanelDia(this, data, escalasPorData, horariosSabado));
                    totalSabados++;
                }
            } else if (data.getDayOfWeek().toString().equals("SUNDAY")) {
                if (!horariosDomingo.isEmpty()) {
                    for (Escala escala : escalas) {
                        if (escala.getData().equals(data)) {
                            escalasPorData.add(escala);
                        }
                    }
                    this.add(new JPanelDia(this, data, escalasPorData, horariosDomingo));
                    totalDomingos++;
                }
            }

            data = data.plusDays(1);
        }
        if (totalSabados == 5 | totalDomingos == 5) {//5 finais de semana no mes
            maximoFrequenciaEscala++;
        }
    }

    public boolean addParticipante(JPanelDia panelDia, Analista analista) {
        if (contaParticipante(analista.getNome()) == maximoFrequenciaEscala) {
            JOptionPane.showMessageDialog(null, "Participante \"" + analista.getNome() + "\" já foi adicionado " + maximoFrequenciaEscala + " vezes");
            return false;
        }
        boolean jaExiste = encontraParticipante("" + panelDia.getData().getDayOfMonth(), analista.getNome());
        if (jaExiste) {
            JOptionPane.showMessageDialog(null, "Participante \"" + analista.getNome() + "\" já adicionado a essa dia");
            return false;
        }
        if (panelDia.eSabado()) {
            LocalDate nextData = panelDia.getData().plusDays(1);
            //jaExiste = encontraParticipante(JPanelDia.titleToString(nextData), analista.getNome());
            jaExiste = encontraParticipante("" + nextData.getDayOfMonth(), analista.getNome());
        }
        if (panelDia.eDomingo()) {
            LocalDate lastData = panelDia.getData().minusDays(1);
            //jaExiste = encontraParticipante(JPanelDia.titleToString(nextData), analista.getNome());
            jaExiste = encontraParticipante("" + lastData.getDayOfMonth(), analista.getNome());
        }
        if (jaExiste) {
            JOptionPane.showMessageDialog(null, "Participante \"" + analista.getNome() + "\" já adicionado a essa final de semana");
            return false;
        } else {
            String[] array = new String[2];
            array[0] = "" + panelDia.getData().getDayOfMonth();//panelDia.getTitle();
            array[1] = analista.getNome();
            participante.add(array);
            crudService.salvarEscala(panelDia.getData(), analista, analista.getEquipe(), panelDia.horarioEscolhido());
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

    public void removerParticipante(LocalDate data, Analista analista) {
        System.out.println("com.krismorte.escala2.view.JPanelMes.removerParticipante()");
        for (String[] s : participante) {
            System.out.println(s[0] + "=" + data.getDayOfMonth() + " " + s[1] + "=" + analista.getNome());
            if (s[0].trim().equals("" + data.getDayOfMonth()) & s[1].trim().equals(analista.getNome().trim())) {
                System.out.println("Removeu");
                participante.remove(s);
                crudService.removerEscala(data, analista);
                break;
            }
        }
    }

    public Analista encontraAnalista(String nome) {
        Analista analistaEncontrado = null;
        for (Analista analista : analistas) {
            if (analista.getNome().equals(nome)) {
                analistaEncontrado = analista;
                break;
            }
        }
        return analistaEncontrado;
    }

    public List<String[]> getParticipante() {
        return participante;
    }

}
