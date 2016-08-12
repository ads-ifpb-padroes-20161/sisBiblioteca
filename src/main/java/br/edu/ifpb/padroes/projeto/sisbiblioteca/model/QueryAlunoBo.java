/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.model;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.Dao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.FactoryProvider;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Aluno;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public class QueryAlunoBo {

    private final Dao<Aluno, String> alunoDao;

    public QueryAlunoBo() {
        alunoDao = FactoryProvider.createFactory(1).getAlunoDao();
    }

    public List<Aluno> listar() {
        return Collections.unmodifiableList(alunoDao.list());
    }
}
