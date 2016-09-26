/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.enums;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.EstadoAlunoIF;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.AlunoInabilitadoException;
import java.io.Serializable;

/**
 *
 * @author kieckegard
 */
public enum EstadoAlunoEnum implements EstadoAlunoIF, Serializable {

    HABILITADO{

        @Override
        public EstadoAlunoIF realizarEmprestimo() {
            return INABILITADO;
        }

        @Override
        public EstadoAlunoIF finalizarEmprestimo() {
            return HABILITADO;
        }      

        @Override
        public String getDescricao() {
            return "Habilitado";
        }

        @Override
        public Integer getValue() {
            return 1;
        }
    }, 
    INABILITADO{

        @Override
        public EstadoAlunoIF realizarEmprestimo() throws AlunoInabilitadoException {
            throw new AlunoInabilitadoException("O aluno não pode realizar empréstimos agora.");
        }

        @Override
        public EstadoAlunoIF finalizarEmprestimo() {
            return HABILITADO;
        }   

        @Override
        public String getDescricao() {
            return "Inabilitado";
        }

        @Override
        public Integer getValue() {
            return 2;
        }
    }
}
