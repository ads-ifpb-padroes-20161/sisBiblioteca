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
public enum TipoUsuario
{
    BIBLIOTECARIO(1);
    
    int tipo;
    
    TipoUsuario(int tipo){
        this.tipo = tipo;
    }
    
    public int getValue(){
        return this.tipo;
    }
    
    public static TipoUsuario getEnum(int tipo){
       for(TipoUsuario tipoUsuario : TipoUsuario.values())
           if(tipoUsuario.getValue() == tipo) return tipoUsuario;
       return null;
    }
}
