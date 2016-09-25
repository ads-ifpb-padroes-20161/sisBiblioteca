/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.commands;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kieckegard
 */
public class Logout implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Usuario loggedUser = (Usuario) request.getAttribute("loggedUser");
        if (loggedUser != null) {
            request.getSession().invalidate();
        }
        response.sendRedirect("login.jsp");
    }
    
}
