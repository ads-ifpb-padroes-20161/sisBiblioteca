/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.enums;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.EmprestimoEstadoIF;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.EmprestimoJaFinalizadoException;
import java.io.Serializable;

/**
 *
 * @author kieckegard
 */
public enum EstadoEmprestimoEnum implements EmprestimoEstadoIF, Serializable {
    ANDAMENTO{

        @Override
        public EmprestimoEstadoIF processarEmprestimo() {
            return this;
        }

        @Override
        public EmprestimoEstadoIF finalizarEmprestimo() {
            return FINALIZADO;
        }

        @Override
        public String getDescricao() {
            return "Em Andamento";
        }

        @Override
        public Integer getValue() {
            return 0;
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
        
        @Override
        public String getDescricao() {
            return "Finalizado";
        }

        @Override
        public Integer getValue() {
            return 1;
        }
        
    }
}
