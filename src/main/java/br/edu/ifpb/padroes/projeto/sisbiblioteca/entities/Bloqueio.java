/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.entities;

import java.time.LocalDate;

/**
 *
 * @author kieckegard
 */
public class Bloqueio {
    
    private LocalDate  dataInicio;
    private LocalDate  dataFim;
    private Aluno      aluno;
    
    public Bloqueio(LocalDate dataInicio, LocalDate dataFim, Aluno aluno) {
        this.dataInicio  = dataInicio;
        this.dataFim     = dataFim;
        this.aluno       = aluno;
    }
    
    public LocalDate getDataInicio() {
        return this.dataInicio;
    }
    
    public LocalDate getDataFim() {
        return this.dataFim;
    }
    
    public Aluno getAluno() {
        return this.aluno;
    }

    @Override
    public String toString()
    {
        return "Bloqueio{" + "dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", aluno=" + aluno + '}';
    }
}
