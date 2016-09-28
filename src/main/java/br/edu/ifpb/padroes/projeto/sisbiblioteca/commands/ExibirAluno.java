/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.commands;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.aluno.Aluno;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.QueryAlunoBo;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kieckegard
 */
public class ExibirAluno implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String matricula = request.getParameter("matricula");
        
        QueryAlunoBo alunoBo = new QueryAlunoBo();
        
        Aluno aluno = alunoBo.getAlunoByMatricula(matricula);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("alunos.jsp");
        
        request.setAttribute("aluno", aluno);
        
        request.setAttribute("isEdit", true);
        
        try{
            aluno.getMatricula();
        } catch(UnsupportedOperationException ex) {
            request.setAttribute("isEdit", false);
        }
        
        dispatcher.forward(request, response);
    }
    
}
