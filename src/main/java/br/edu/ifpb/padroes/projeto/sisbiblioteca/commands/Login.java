/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.commands;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Usuario;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.PrimeiroLoginException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.LoginBo;
import java.io.IOException;
import javax.security.auth.login.LoginException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kieckegard
 */
public class Login implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String matricula = request.getParameter("matricula");
        String senha = request.getParameter("senha");

        LoginBo bo = new LoginBo();
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");

        try {
            Usuario usuario = bo.login(matricula, senha);
            request.getSession().setAttribute("loggedUser", usuario);
            request.setAttribute("success", true);
        }
        catch (PrimeiroLoginException ex) {
            System.out.println(ex.getMessage());
            dispatcher = request.getRequestDispatcher("minhaconta.jsp");
            request.setAttribute("firstLogin", true);
            request.setAttribute("errorMsg", ex.getMessage());
        }
        catch (LoginException ex) {
            System.out.println(ex.getMessage());
            dispatcher = request.getRequestDispatcher("login.jsp");
            request.setAttribute("success", false);
            request.setAttribute("errorMsg", ex.getMessage());
        }
        finally {
            dispatcher.forward(request, response);
        }
    }
    
}
