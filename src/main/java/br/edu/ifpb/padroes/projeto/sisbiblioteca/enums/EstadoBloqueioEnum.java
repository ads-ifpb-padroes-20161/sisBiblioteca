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
public enum EstadoBloqueioEnum {
    ANDAMENTO(0),FINALIZADO(1);
    
    int id;
    
    EstadoBloqueioEnum(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
}
