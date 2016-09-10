/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.entities;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.EstadoAlunoEnum;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.EstadoEmprestimoEnum;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.AlunoInabilitadoException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.EmprestimoAtrasadoException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.LivroIndisponivelException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.EmprestimoBo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.QueryAlunoBo;
import java.time.LocalDate;
import java.time.Month;



/**
 *
 * @author kieckegard
 */
public class Loader {
    public static void main(String[] args) {
        /*Aluno aluno = new Aluno("","",LocalDate.now(),"12312341231","email");
        aluno.setEstado(EstadoAlunoEnum.INABILITADO);
        Livro livro = new LivroPadrao(100200300,"","","",9);
        
        EmprestimoBo bo = new EmprestimoBo();
        /*try {
            bo.realizarEmprestimo(aluno, livro, LocalDate.now());
            System.out.println("Empréstimo realizado com sucesso!");
        }
        catch (LivroIndisponivelException | AlunoInabilitadoException ex) {
            System.out.println(ex.getMessage());
        }*/
        
       /* LocalDate startDate = LocalDate.of(2016, Month.AUGUST, 26);
        Emprestimo e = new Emprestimo(aluno,livro,startDate, startDate.plusDays(10),EstadoEmprestimoEnum.CORRENTE);
        e.setId(6);
        try {
            bo.finalizaEmprestimo(e);
            System.out.println("Empréstimo finalizado com sucesso!");
        }
        catch (EmprestimoAtrasadoException ex) {
            System.out.println(ex.getMessage());
        }*/
        QueryAlunoBo bo = new QueryAlunoBo();
        
        for(Aluno aluno : bo.listar()){
            System.out.println(aluno);
        }
    }
}
