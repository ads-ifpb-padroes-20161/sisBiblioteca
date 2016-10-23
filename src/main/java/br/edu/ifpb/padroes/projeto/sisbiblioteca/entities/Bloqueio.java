/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.entities;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.EstadoBloqueioEnum;
import java.time.LocalDate;

/**
 *
 * @author kieckegard
 */
public class Bloqueio {
    
    private Integer    id;
    private LocalDate  dataInicio;
    private LocalDate  dataFim;
    private Aluno      aluno;
    private EstadoBloqueioEnum estado;
    
    public Bloqueio(LocalDate dataInicio, LocalDate dataFim, Aluno aluno, EstadoBloqueioEnum estado) {
        this.dataInicio  = dataInicio;
        this.dataFim     = dataFim;
        this.aluno       = aluno;
        this.estado = estado;
    }
    
    public Integer getId() {
        return this.id;
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
    
    public EstadoBloqueioEnum getEstado() {
        return this.estado;
    }

    @Override
    public String toString()
    {
        return "Bloqueio{" + "dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", aluno=" + aluno + ", estado=" + estado + "}";
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
