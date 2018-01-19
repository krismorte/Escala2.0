package com.krismorte.escala2.view;

import com.krismorte.escala2.model.Analista;
import com.krismorte.escala2.model.Escala;
import com.krismorte.escala2.model.Horario;
import com.krismorte.escala2.util.Tradutor;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author c007329
 */
public class JPanelDia extends JPanel {

    private JPanelMes panelMes;
    private List<Horario> horarios;
    private List<Escala> escalas;
    private String title;
    private LocalDate data;
    private Horario horarioEscolhido;
    //public List<String> participante = new ArrayList<>();

    public JPanelDia(JPanelMes panelMes, LocalDate data, List<Escala> escalas, List<Horario> horarios) {
        this.title = JPanelDia.titleToString(data);//data.getDayOfMonth() + " " + data.format(Tradutor.getDayFormater());
        this.panelMes = panelMes;
        this.horarios = horarios;
        this.escalas = escalas;
        this.data = data;

        TitledBorder border = BorderFactory.createTitledBorder(title);
        border.setTitleFont(new Font("times new roman", Font.BOLD, 14));
        setBorder(border);
        setLayout(new GridLayout(3, 1));
        setFocusable(true);
        setOpaque(false);
        adicionaPanelHorarios();
    }

    private void adicionaPanelHorarios() {
        List<Escala> escalasPorhorario = new ArrayList<>();
        for (Horario s : horarios) {
            escalasPorhorario.clear();
            for (Escala escala : escalas) {
                if (escala.getHorario().equals(s)) {
                    escalasPorhorario.add(escala);
                }
            }
            DNDLabelPanel pane1 = new DNDLabelPanel(this, s, escalasPorhorario);
            this.add(pane1);
        }
    }

    public boolean addParticipante(Analista analista, Horario horario) {
        horarioEscolhido = horario;
        return panelMes.addParticipante(this, analista);
    }

    public void removerParticipante(Analista analista) {
        panelMes.removerParticipante(data, analista);
    }

    public Horario horarioEscolhido() {
        return horarioEscolhido;
    }

    public Analista encontraAnalista(String noma) {
        return panelMes.encontraAnalista(noma);

    }

    public String getTitle() {
        return title;
    }

    public LocalDate getData() {
        return data;
    }

    public boolean eSabado() {
        return data.getDayOfWeek().toString().equals("SATURDAY");
    }

    public boolean eDomingo() {
        return data.getDayOfWeek().toString().equals("SUNDAY");
    }

    public static String titleToString(LocalDate date) {
        return date.getDayOfMonth() + " " + date.format(Tradutor.getDayFormater());
    }

}
