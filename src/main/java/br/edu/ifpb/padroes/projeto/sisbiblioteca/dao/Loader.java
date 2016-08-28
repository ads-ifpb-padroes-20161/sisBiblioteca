/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.emprestimo.EmprestimoBdDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kieckegard
 */
public class Loader {
    public static void main(String[] args) {
        EmprestimoBdDao dao = new EmprestimoBdDao();
        Map<String,String> attributes = new HashMap<>();
        attributes.put("dataInicio", "27/08/2016");
        for(Emprestimo e : dao.listByAttributes(attributes)){
            System.out.println(e);
        }
                
    }
}
