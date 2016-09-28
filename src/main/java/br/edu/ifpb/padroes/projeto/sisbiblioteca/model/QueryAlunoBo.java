/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.model;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.aluno.AlunoDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.bloqueio.BloqueioDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.FactoryProvider;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.aluno.Aluno;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public class QueryAlunoBo {

    private final AlunoDao alunoDao;
    private final BloqueioDao bloqueioDao;
   

    public QueryAlunoBo() {
        alunoDao = FactoryProvider.createFactory(1).getAlunoDao();
        bloqueioDao = FactoryProvider.createFactory(1).getBloqueioDao();
    }

    public List<Aluno> listar() {
        //bloqueioDao.verificaEDesbloqueiaAlunos();
        return Collections.unmodifiableList(alunoDao.listarAlunos());
    }
    
    public List<Aluno> listarAlunosHabilitados() {
        return Collections.unmodifiableList(alunoDao.listarAlunosHabilitados());
    }
    
    public Aluno getAlunoByMatricula(String matricula) {
        bloqueioDao.verificaEDesbloqueiaAlunos();
        return alunoDao.recuperarAlunoPorMatricula(matricula);
    }
}
