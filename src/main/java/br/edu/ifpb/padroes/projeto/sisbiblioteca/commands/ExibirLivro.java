/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.commands;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.livro.Livro;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.LivroBo;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kieckegard
 */
public class ExibirLivro implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Long isbn = Long.parseLong(request.getParameter("isbn"));
        
        LivroBo livroBo = new LivroBo();
        
        Livro livro = livroBo.getLivro(isbn);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("livros.jsp");
        
        request.setAttribute("isEdit", true);
        
        try{
            livro.getIsbn();
            request.setAttribute("livro", livro);
            
        } catch(UnsupportedOperationException ex) {
            request.setAttribute("isEdit", false);
        }
        
        dispatcher.forward(request, response);
    }
    
}
