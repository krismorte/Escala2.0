package com.krismorte.escala2.view;

import com.krismorte.escala2.util.Tradutor;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
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
    private List<String> horarios;
    private String title;
    private LocalDate data;
    //public List<String> participante = new ArrayList<>();

    public JPanelDia(JPanelMes panelMes, LocalDate data, List<String> horarios) {
        this.title = JPanelDia.titleToString(data);//data.getDayOfMonth() + " " + data.format(Tradutor.getDayFormater());
        this.panelMes = panelMes;
        this.horarios = horarios;
        this.data = data;
        /*if (title.contains("SATURDAY")) {
            title = title.replace("SATURDAY", "SAB");
        } else if (title.contains("SUNDAY")) {
            title = title.replace("SUNDAY", "DOM");
        }*/

        //this.setBorder(BorderFactory.createTitledBorder(title));
        TitledBorder border = BorderFactory.createTitledBorder(title);
        border.setTitleFont(new Font("times new roman", Font.BOLD, 14));
        this.setBorder(border);
        this.setLayout(new GridLayout(3, 1));
        this.setFocusable(true);
        this.setOpaque(false);
        adicionaPanelHorarios();
    }

    private void adicionaPanelHorarios() {
        for (String s : horarios) {
            DNDLabelPanel pane1 = new DNDLabelPanel(this/*new AcaoJPanel(this)*/, s);
            this.add(pane1);
        }
    }

    public boolean addParticipante(String text) {
        return panelMes.addParticipante(this, text);
        /*for (String s : panelMes.getParticipante()) {
            if (s.equals(text)) {
                JOptionPane.showMessageDialog(null, "Participante \"" + text + "\" já adicionado a essa dia");
                return false;
            }
        }
        participante.add(text);*/
    }

    public void removerParticipante(String text) {
        panelMes.removerParticipante(title, text);
        /*for (String s : participante) {
            if (s.equals(text)) {
                participante.remove(s);
                break;
            }
        }*/
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
