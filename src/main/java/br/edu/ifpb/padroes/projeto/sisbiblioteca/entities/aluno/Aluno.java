/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.aluno;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Endereco;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.IPessoa;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.AlunoInabilitadoException;
import java.time.LocalDate;

/**
 *
 * @author kieckegard
 */
public interface Aluno extends IPessoa {
    
    public String getMatricula();

    public String getEmail();

    public Integer getValorEstado();

    public String getDescricaoEstado();

    public void realizarEmprestimo() throws AlunoInabilitadoException;

    public void finalizarEmprestimo();
    
}
