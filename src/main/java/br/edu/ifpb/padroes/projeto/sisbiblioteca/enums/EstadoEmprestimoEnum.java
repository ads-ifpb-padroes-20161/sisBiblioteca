/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.enums;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.EmprestimoEstadoIF;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.EmprestimoJaFinalizadoException;

/**
 *
 * @author kieckegard
 */
public enum EstadoEmprestimoEnum implements EmprestimoEstadoIF{
    CORRENTE{

        @Override
        public EmprestimoEstadoIF processarEmprestimo() {
            return this;
        }

        @Override
        public EmprestimoEstadoIF finalizarEmprestimo() {
            return FINALIZADO;
        }
        
    },
    FINALIZADO {

        @Override
        public EmprestimoEstadoIF processarEmprestimo() {
            throw new EmprestimoJaFinalizadoException("Este empréstimo já foi finalizado!");
        }

        @Override
        public EmprestimoEstadoIF finalizarEmprestimo() {
            throw new EmprestimoJaFinalizadoException("Este empréstimo já foi finalizado!");
        }
        
    }
}
