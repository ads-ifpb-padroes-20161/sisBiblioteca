/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.commands;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.UsuarioBo;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kieckegard
 */
public class DeletarConta implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String matricula = request.getParameter("matricula");
        
        UsuarioBo bo = new UsuarioBo();
        
        bo.removerConta(matricula);
        
        request.getSession().invalidate();
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        
        request.setAttribute("deletedAccount", true);
        
        dispatcher.forward(request, response);
    }
    
}
