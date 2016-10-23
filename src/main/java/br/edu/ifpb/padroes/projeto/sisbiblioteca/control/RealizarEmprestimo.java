/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.control;

import br.edu.ifpb.bdnc.maisdenuncia.utils.DateUtils;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Aluno;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Livro;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.AlunoInabilitadoException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.LivroIndisponivelException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.AlunoBo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.EmprestimoBo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.LivroBo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.QueryAlunoBo;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kieckegard
 */
public class RealizarEmprestimo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        String matricula = request.getParameter("matricula");
        Long isbn = Long.parseLong(request.getParameter("isbn"));
        
        QueryAlunoBo queryAlunoBo = new QueryAlunoBo();
        LivroBo livroBo = new LivroBo();
        
        Aluno aluno = queryAlunoBo.getAlunoByMatricula(matricula);      
        Livro livro = livroBo.getLivro(isbn);
        
        EmprestimoBo emprestimoBo = new EmprestimoBo();
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("realizarEmprestimo.jsp");
        
        try {
            
            Emprestimo emprestimo = emprestimoBo.realizarEmprestimo(aluno, livro, LocalDate.now());
            
            request.setAttribute("success", true);
            request.setAttribute("emprestimoEndDate",DateUtils.formatToBrazilPattern(emprestimo.getEndDate()));
        }
        catch (LivroIndisponivelException | AlunoInabilitadoException ex) {
            request.setAttribute("success",false);
            request.setAttribute("errorMsg",ex.getMessage());
        } finally {
            dispatcher.forward(request, response);
        }
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
