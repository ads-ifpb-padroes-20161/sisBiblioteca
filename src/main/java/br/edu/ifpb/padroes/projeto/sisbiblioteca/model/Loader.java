/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.model;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.emprestimo.EmprestimoBdDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.emprestimo.EmprestimoDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public class Loader {
    
    public static void main(String[] args) {
        
        EmprestimoDao dao = new EmprestimoBdDao();
        
        List<Emprestimo> emprestimos = dao.listarEmprestimos();
        
        for(Emprestimo e : emprestimos) {
            System.out.println(e);
        }
        
        EmprestimoRelatorioBo relatorioBo = new EmprestimoRelatorioBo();
        //relatorioBo.imprimir(emprestimos);
        
        
    }
}
