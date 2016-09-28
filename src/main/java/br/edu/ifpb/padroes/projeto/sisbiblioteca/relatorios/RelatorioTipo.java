/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.relatorios;

/**
 *
 * @author kieckegard
 */
public enum RelatorioTipo {
    
    EMPRESTIMO_ANDAMENTO(0,"relatorios/emprestimosAndamento.jrxml"),
    EMPRESTIMO_CONCLUIDO(1,"relatorios/emprestimosConcluido.jrxml");
    
    private final Integer value;
    private final String fileName;
    
    RelatorioTipo(Integer value, String fileName) {
        this.value = value;
        this.fileName = fileName;
    }
    
    public Integer getValue() {
        return this.value;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public static RelatorioTipo getByValue(Integer value) {
        for(RelatorioTipo tipo : RelatorioTipo.values()) {
            if(tipo.getValue().equals(value)) return tipo;
        }
        return null;
    }
}
