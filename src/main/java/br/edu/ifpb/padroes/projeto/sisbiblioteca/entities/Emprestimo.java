/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.entities;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.AlunoInabilitadoException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.EmprestimoAtrasadoException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.LivroIndisponivelException;
import java.time.LocalDate;

/**
 *
 * @author kieckegard
 */
public class Emprestimo {
    
    private Integer id;
    private Aluno aluno;
    private Livro livro;
    private LocalDate startDate;
    private LocalDate endDate;
    private EmprestimoEstadoIF estado;
    
    public Emprestimo(Aluno aluno, Livro livro, LocalDate startDate, EmprestimoEstadoIF estado) {
        this.aluno = aluno;
        this.livro = livro;
        this.startDate = startDate;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    
    public void processarEmprestimo() throws LivroIndisponivelException, AlunoInabilitadoException {
        this.estado = estado.processarEmprestimo();
        this.livro.emprestar();
        this.aluno.realizarEmprestimo();
    }
    
    public void finalizarEmprestimo() throws EmprestimoAtrasadoException {
        this.estado = estado.finalizarEmprestimo();
        this.livro.devolver();
        verificarAtraso();
        this.aluno.finalizarEmprestimo();
    }
    
    private void verificarAtraso() throws EmprestimoAtrasadoException {
        LocalDate now = LocalDate.now();
        if(now.isAfter(endDate))
            throw new EmprestimoAtrasadoException("O Empréstimo encontra-se atrasado!"
                    + " O Aluno vai recorrer a um bloqueio de 3 dias!");
    }
}
