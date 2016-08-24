/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.entities;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.LivroIndisponivelException;
import java.time.LocalDate;

/**
 *
 * @author kieckegard
 */
public class Emprestimo {
    
    private int id;
    private Aluno aluno;
    private LivroPadrao livro;
    private LocalDate startDate;
    private LocalDate endDate;
    private EmprestimoEstadoIF estado;
    
    public Emprestimo(int id, Aluno aluno, LivroPadrao livro, LocalDate startDate, EmprestimoEstadoIF estado) {
        this.id = id;
        this.aluno = aluno;
        this.livro = livro;
        this.startDate = startDate;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public LivroPadrao getLivro() {
        return livro;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    
    public void processarEmprestimo() throws LivroIndisponivelException {
        this.estado = estado.processarEmprestimo();
        this.livro.emprestar();
    }
    
    public void finalizarEmprestimo() {
        this.estado = estado.finalizarEmprestimo();
        this.livro.devolver();
    }
}
