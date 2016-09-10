/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Aluno;

/**
 *
 * @author kieckegard
 */
public interface AlunoDao extends Dao<Aluno, String> {
    
    void atualizarEstadoAluno(Aluno aluno);
    
}
