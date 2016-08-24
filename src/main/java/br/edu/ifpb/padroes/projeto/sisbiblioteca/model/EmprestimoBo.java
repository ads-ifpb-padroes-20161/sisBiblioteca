/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.model;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Aluno;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Bloqueio;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Livro;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.EstadoEmprestimoEnum;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.AlunoInabilitadoException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.EmprestimoAtrasadoException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.LivroIndisponivelException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public class EmprestimoBo {
    
    
    public void realizarEmprestimo(Aluno aluno, Livro livro, LocalDate startDate) throws LivroIndisponivelException, AlunoInabilitadoException {
        Emprestimo emprestimo = new Emprestimo(aluno,livro,startDate,EstadoEmprestimoEnum.CORRENTE);
        emprestimo.processarEmprestimo();
        //persist Emprestimo
    }
    
    public void finalizaEmprestimo(Emprestimo emprestimo) throws EmprestimoAtrasadoException {
        try {
            emprestimo.finalizarEmprestimo();
        }
        catch (EmprestimoAtrasadoException ex) {
            LocalDate now = LocalDate.now();
            Bloqueio bloqueio = new Bloqueio(now, now.plusDays(3),emprestimo.getAluno());
            //insere o bloqueio!
            throw new EmprestimoAtrasadoException("O Devolução do empréstimo foi feita com sucesso"
                    + "mas pela entrega do mesmo atrasou. O Aluno ficará banido de realizar empréstimos"
                    + "durante 3 dias! ");
        } finally {
            //update Empréstimo e Livro
        }
    }
    
    public List<Emprestimo> listarEmprestimos() {
        return new LinkedList<>();
    }
    
    public Emprestimo getEmprestimoById(Integer id) {
        return null;
    }
}
