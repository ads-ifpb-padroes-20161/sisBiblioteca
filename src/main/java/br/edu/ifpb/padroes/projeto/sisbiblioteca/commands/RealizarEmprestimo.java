/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.commands;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.aluno.Aluno;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.livro.Livro;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.AlunoInabilitadoException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.LivroIndisponivelException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.EmprestimoBo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.LivroBo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.QueryAlunoBo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.utils.DateUtils;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kieckegard
 */
public class RealizarEmprestimo implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String matricula = request.getParameter("matricula");
        Long isbn = Long.parseLong(request.getParameter("isbn"));
        
        QueryAlunoBo queryAlunoBo = new QueryAlunoBo();
        LivroBo livroBo = new LivroBo();
        
        Aluno aluno = queryAlunoBo.getAlunoByMatricula(matricula);      
        Livro livro = livroBo.getLivro(isbn);
        
        EmprestimoBo emprestimoBo = new EmprestimoBo();
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("realizarEmprestimo.jsp");
        
        try {
            
            Emprestimo emprestimo = emprestimoBo.realizarEmprestimo(aluno, livro, LocalDate.now());
            
            request.setAttribute("success", true);
            request.setAttribute("emprestimoEndDate",DateUtils.formatToBrazilPattern(emprestimo.getEndDate()));
            
        }
        catch (LivroIndisponivelException | AlunoInabilitadoException ex) {
            
            request.setAttribute("success",false);
            request.setAttribute("errorMsg",ex.getMessage());
            
        } finally {
            
            dispatcher.forward(request, response);
        }
    }
    
}
