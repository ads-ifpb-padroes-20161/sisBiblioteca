/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.enums;

/**
 *
 * @author kieckegard
 */
public enum EstadoAlunoEnum {

    REGULAR(1), BLOQUEADO(2);

    int tipo;

    EstadoAlunoEnum(int tipo) {
        this.tipo = tipo;
    }

    public int getIndex() {
        return this.tipo;
    }

    public static EstadoAlunoEnum get(int tipo) {
        for (EstadoAlunoEnum estado : EstadoAlunoEnum.values()) {
            if (estado.getIndex() == tipo) {
                return estado;
            }
        }
        return null;
    }
}
