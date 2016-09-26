/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.commands;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.QueryEmprestimoBo;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kieckegard
 */
public class BuscaEmprestimo implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String key = request.getParameter("searchCriteria");
        String value = request.getParameter("searchText");
        
        Map<String, String> attributes = new HashMap<>();
        attributes.put(key, value);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("emprestimos.jsp");
        
        if(key.equals("") || value.equals("")) {
            request.setAttribute("success", false);
            request.setAttribute("errorMsg", "Por favor, preencha todos os campos para realizar a busca!");
            dispatcher.forward(request, response);
        }
        
        List<Emprestimo> emprestimos = new QueryEmprestimoBo().listEmprestimosByAttributes(attributes);
        System.out.println("Printing search result: ");
        for(Emprestimo emp : emprestimos){
            System.out.println(emp);
        }
        
        request.setAttribute("emprestimos", emprestimos);
        request.getSession().setAttribute("emprestimosCached", emprestimos);
        
        dispatcher.forward(request, response);
    }
    
}
