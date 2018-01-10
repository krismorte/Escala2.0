/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.service;

import com.krismorte.escala2.dao.AuditRule;
import com.krismorte.escala2.dao.EquipeDao;
import com.krismorte.escala2.dao.EquipeHorarioDao;
import com.krismorte.escala2.dao.HorarioDao;
import com.krismorte.escala2.model.Equipe;
import com.krismorte.escala2.model.EquipeHorario;
import com.krismorte.escala2.model.Horario;
import java.util.List;

/**
 *
 * @author c007329
 */
public class CrudService {

    private EquipeDao equipeDao = new EquipeDao();
    private HorarioDao horarioDao = new HorarioDao();
    private EquipeHorarioDao equipeHorarioDao = new EquipeHorarioDao();

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

    public void salvarEquipe(Equipe equipe, List<EquipeHorario> equipeHorarios) {
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

    }

    public List<Equipe> listarEquipes() {
        equipeDao.beginTransaction();
        List<Equipe> horarios = equipeDao.findAll();
        equipeDao.commitAndCloseTransaction();
        return horarios;
    }

}
