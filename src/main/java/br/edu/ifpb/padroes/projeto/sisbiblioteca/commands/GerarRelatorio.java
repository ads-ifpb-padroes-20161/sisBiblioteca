/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.commands;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.EmprestimoRelatorioBo;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kieckegard
 */
public class GerarRelatorio implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setHeader("Content-Disposition", "inline; filename=\"application.pdf\"");
        response.setContentType("application/pdf");
        
        String path = request.getSession().getServletContext().getRealPath("/") + "relatorios/emprestimos.jrxml";
        
        List<Emprestimo> emprestimos = (List<Emprestimo>) request.getSession().getAttribute("emprestimosCached");
        
        EmprestimoRelatorioBo relatorio = new EmprestimoRelatorioBo();
        
        System.out.println("Path: "+path);
        
        relatorio.generatePdfStream(emprestimos,path,response.getOutputStream());
    }
    
}
