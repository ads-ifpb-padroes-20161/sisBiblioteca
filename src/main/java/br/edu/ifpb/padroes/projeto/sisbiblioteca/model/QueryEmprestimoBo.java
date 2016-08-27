/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.model;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.EmprestimoDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.FactoryProvider;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kieckegard
 */
public class QueryEmprestimoBo {
    
    private final EmprestimoDao emprestimoDao;
    
    public QueryEmprestimoBo() {
        emprestimoDao = FactoryProvider.createFactory(FactoryProvider.jdbc).getEmprestimoDao();
    }
    
    public List<Emprestimo> listEmprestimosByAttributes(Map<String, String> attributes) {
        List<Emprestimo> emprestimos = emprestimoDao.listByAttributes(attributes);
        Collections.sort(emprestimos);
        return emprestimos;
    }
    
}
