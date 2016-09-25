/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.aluno;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.EstadoAlunoIF;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.IPessoa;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.AlunoInabilitadoException;

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
    
    public void setMatricula(String matricula);

    public void setEmail(String email);

    public void setEstado(EstadoAlunoIF estado);
    
}
