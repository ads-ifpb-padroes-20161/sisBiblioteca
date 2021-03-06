/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.commands;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.livro.LivroPadrao;
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
public class EditarLivro implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("isbn: "+request.getParameter("isbn"));
        
        Long isbn = Long.valueOf(request.getParameter("isbn"));
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String descricao = request.getParameter("descricao");
        int estoque = Integer.valueOf(request.getParameter("estoque"));

        LivroPadrao livro = new LivroPadrao(isbn,titulo,autor,descricao,estoque);

        LivroBo bo = new LivroBo();

        RequestDispatcher dispatcher = request.getRequestDispatcher("livros.jsp");

        try {
            bo.atualizarLivro(livro);
            request.setAttribute("success", true);
            request.setAttribute("msg", "O livro "+livro.getIsbn()+" foi atualizado com sucesso!");
        }
        finally {
            dispatcher.forward(request, response);
        }
    }
    
}
