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
        int ultimoDiaDoMes = mesAtual.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        this.mesFim = LocalDate.of(mesAtual.getYear(), mesAtual.getMonthValue(), ultimoDiaDoMes);
        updateMoth(mesAtual);
    }

    private void updateMoth(LocalDate data) {
        this.removeAll();

        this.setLayout(new GridLayout(2, 7));
        this.setOpaque(false);
        int totalSabados = 0;
        int totalDomingos = 0;
        escalas = crudService.listaEscalaPorMes(mesInicio, mesFim, equipe);

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
        if (contaParticipante(analista) == maximoFrequenciaEscala) {
            JOptionPane.showMessageDialog(null, "Participante \"" + analista.getNome() + "\" já foi adicionado " + maximoFrequenciaEscala + " vezes");
            return false;
        }
        boolean jaExiste = encontraParticipante(panelDia.getData(), analista) != null;
        if (jaExiste) {
            JOptionPane.showMessageDialog(null, "Participante \"" + analista.getNome() + "\" já adicionado a essa dia");
            return false;
        }
        if (panelDia.eSabado()) {
            LocalDate nextData = panelDia.getData().plusDays(1);
            jaExiste = encontraParticipante(nextData, analista) != null;
        }
        if (panelDia.eDomingo()) {
            LocalDate lastData = panelDia.getData().minusDays(1);
            jaExiste = encontraParticipante(lastData, analista) != null;
        }

        if (jaExiste) {
            JOptionPane.showMessageDialog(null, "Participante \"" + analista.getNome() + "\" já adicionado a essa final de semana");
            return false;
        } else {
            Escala escala = crudService.salvarEscala(panelDia.getData(), analista, analista.getEquipe(), panelDia.horarioEscolhido());
            escalas.add(escala);
        }
        return true;
    }

    private boolean validaVagasHorario(Horario horario) {
        boolean retornoOk = false;
        for (Escala escala : escalas) {

        }
        return retornoOk;
    }

    private Escala encontraParticipante(LocalDate date, Analista analista) {
        Escala escalaEncontrada = null;
        for (Escala escala : escalas) {
            if (escala.getData().equals(date) & escala.getAnalista().equals(analista)) {
                escalaEncontrada = escala;
            }
        }
        return escalaEncontrada;
    }

    private int contaParticipante(Analista analista) {
        int frequencia = 0;
        for (Escala escala : escalas) {
            if (escala.getAnalista().equals(analista)) {
                frequencia++;
            }
        }

        return frequencia;
    }

    public void removerParticipante(JPanelDia panelDia, LocalDate data, Analista analista) {
        for (Escala escala : escalas) {
            if (escala.getData().equals(data) & escala.getAnalista().equals(analista)) {
                crudService.removerEscala(data, analista);
                break;
            }
        }
        Escala escala = encontraParticipante(data, analista);
        escalas.remove(escala);
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

}
