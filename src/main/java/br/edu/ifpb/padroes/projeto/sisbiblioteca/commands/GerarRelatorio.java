/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.commands;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.EmprestimoBo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.QueryEmprestimoBo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.relatorios.EmprestimoRelatorioBo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.relatorios.RelatorioTipo;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
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
        
        String key = "idEstado";
        String value = request.getParameter("tipoRelatorio");
        
        Map<String, String> attributes = new HashMap<>();
        attributes.put(key, value);
        
        Integer enumValue = Integer.parseInt(value);
        
        RelatorioTipo relatorioTipo = RelatorioTipo.getByValue(enumValue);
        
        String path = request.getSession().getServletContext().getRealPath("/") + relatorioTipo.getFileName();
        
        List<Emprestimo> emprestimos = new QueryEmprestimoBo().listEmprestimosByAttributes(attributes);
        
        EmprestimoRelatorioBo relatorio = new EmprestimoRelatorioBo();     
        relatorio.generatePdfStream(emprestimos,path,response.getOutputStream());
    }
    
}
