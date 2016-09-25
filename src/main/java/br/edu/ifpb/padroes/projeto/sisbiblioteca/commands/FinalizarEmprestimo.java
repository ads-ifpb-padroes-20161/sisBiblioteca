/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.commands;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.EmprestimoAtrasadoException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.EmprestimoJaFinalizadoException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.EmprestimoBo;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kieckegard
 */
public class FinalizarEmprestimo implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Integer id = Integer.parseInt(request.getParameter("id"));
        
        EmprestimoBo bo = new EmprestimoBo();
        Emprestimo e = bo.getEmprestimoById(id);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("emprestimos.jsp");
        
        try {
            bo.finalizaEmprestimo(e);
            request.setAttribute("success", true);
        }
        catch (EmprestimoAtrasadoException | EmprestimoJaFinalizadoException ex) {
            request.setAttribute("success", false);
            request.setAttribute("errorMsg", ex.getMessage());
        } finally {
            dispatcher.forward(request, response);
        }
    }
    
}
