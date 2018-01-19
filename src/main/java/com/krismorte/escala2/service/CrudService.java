/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.service;

import com.krismorte.escala2.dao.AnalistaDao;
import com.krismorte.escala2.dao.AuditRule;
import com.krismorte.escala2.dao.EquipeDao;
import com.krismorte.escala2.dao.EquipeHorarioDao;
import com.krismorte.escala2.dao.EscalaDao;
import com.krismorte.escala2.dao.HorarioDao;
import com.krismorte.escala2.dao.filter.EscalaPredicados;
import com.krismorte.escala2.model.Analista;
import com.krismorte.escala2.model.Equipe;
import com.krismorte.escala2.model.EquipeHorario;
import com.krismorte.escala2.model.Escala;
import com.krismorte.escala2.model.Horario;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author c007329
 */
public class CrudService {

    private EquipeDao equipeDao = new EquipeDao();
    private HorarioDao horarioDao = new HorarioDao();
    private EquipeHorarioDao equipeHorarioDao = new EquipeHorarioDao();
    private AnalistaDao analistaDao = new AnalistaDao();
    private EscalaDao escalaDao = new EscalaDao();

    public void salvarEscala(LocalDate data, Analista analista, Equipe equipe, Horario horario) {
        Escala escala = new Escala();
        escala.setAnalista(analista);
        escala.setData(data);
        escala.setEquipe(equipe);
        escala.setHorario(horario);
        AuditRule.audit(escala);
        escalaDao.beginTransaction();
        escalaDao.save(escala);
        escalaDao.commitAndCloseTransaction();
    }

    public void removerEscala(LocalDate data, Analista analista) {
        EscalaPredicados filtro = new EscalaPredicados();
        filtro.addParametro("data", data);
        filtro.addParametro("analista", analista);
        escalaDao.beginTransaction();
        CriteriaDelete delete = filtro.processaDelete(escalaDao.getEntityManager().getCriteriaBuilder());
        escalaDao.execUpdate(delete);
        //int total = escalaDao.deleteDate(data, analista);
        escalaDao.commitAndCloseTransaction();
    }

    public List<Escala> listaEscalaPorMes(LocalDate dataInicial, LocalDate dataFinal, Equipe equipe) {
        EscalaPredicados filtro = new EscalaPredicados();
        escalaDao.beginTransaction();
        CriteriaQuery query = filtro.listarEscalasMesEquipe(escalaDao.getEntityManager().getCriteriaBuilder(), dataInicial, dataFinal, equipe);
        List<Escala> lista = escalaDao.search(query);
        escalaDao.commitAndCloseTransaction();
        return lista;
    }

    public void salvarHorario(Horario horario) {
        AuditRule.audit(horario);
        horarioDao.beginTransaction();
        horarioDao.save(horario);
        horarioDao.commitAndCloseTransaction();
    }

    public List<Horario> listarHorarios() {
        horarioDao.beginTransaction();
        List<Horario> horarios = horarioDao.findAll();
        horarioDao.commitAndCloseTransaction();
        return horarios;
    }

    public List<Horario> listarHorariosPorEquipe(Equipe equipe) {
        horarioDao.beginTransaction();
        List<Horario> horarios = horarioDao.listByEquipe(equipe);
        horarioDao.commitAndCloseTransaction();
        return horarios;
    }

    public void salvarEquipe(Equipe equipe, List<EquipeHorario> equipeHorarios) throws Exception {
        boolean validarVagasEquipe = true;
        for (EquipeHorario equipeHorario : equipeHorarios) {
            if (equipeHorario.getVagasPleno() == 0 & equipeHorario.getVagasSenior() == 0) {
                validarVagasEquipe = false;
                break;
            }
        }
        if (validarVagasEquipe) {
            AuditRule.audit(equipe);
            equipeDao.beginTransaction();
            equipeDao.save(equipe);
            equipeDao.commitAndCloseTransaction();
            equipeHorarioDao.beginTransaction();
            for (EquipeHorario equipeHorario : equipeHorarios) {
                AuditRule.audit(equipeHorario);
                equipeHorario.setEquipe(equipe);
                equipeHorarioDao.save(equipeHorario);
            }
            equipeHorarioDao.commitAndCloseTransaction();
        } else {
            System.out.println("");
            throw new Exception("Cadastre as vagas referentes ao horários");
        }

    }

    public List<Equipe> listarEquipes() {
        equipeDao.beginTransaction();
        List<Equipe> horarios = equipeDao.findAll();
        equipeDao.commitAndCloseTransaction();
        return horarios;
    }

    public void salvarAnalista(Analista analista) {
        AuditRule.audit(analista);
        analistaDao.beginTransaction();
        analistaDao.save(analista);
        analistaDao.commitAndCloseTransaction();
    }

    public List<Analista> listarAnalistas() {
        analistaDao.beginTransaction();
        List<Analista> analistas = analistaDao.findAll();
        analistaDao.commitAndCloseTransaction();
        return analistas;
    }

    public List<Analista> listarAnalistasPorEquipe(Equipe equipe) {
        analistaDao.beginTransaction();
        List<Analista> analistas = analistaDao.listByEquipe(equipe);
        analistaDao.commitAndCloseTransaction();
        return analistas;
    }

}
