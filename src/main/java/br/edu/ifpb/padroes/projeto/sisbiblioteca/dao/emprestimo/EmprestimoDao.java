/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kieckegard
 */
public interface EmprestimoDao extends Dao<Emprestimo, Integer>{
    
    void finalizarEmprestimo(Emprestimo emprestimo);
    List<Emprestimo> listByAttributes(Map<String, String> attributes);
}
