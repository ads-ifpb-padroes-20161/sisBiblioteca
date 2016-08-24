/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.entities;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.AlunoInabilitadoException;

/**
 *
 * @author kieckegard
 */
public interface EstadoAlunoIF {
    EstadoAlunoIF realizarEmprestimo() throws AlunoInabilitadoException;
    EstadoAlunoIF finalizarEmprestimo();
    String getDescricao();
    Integer getValue();
}
