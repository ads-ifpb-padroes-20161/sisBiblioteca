/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.aluno;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Endereco;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.EstadoAlunoIF;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Pessoa;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.AlunoInabilitadoException;
import java.time.LocalDate;

/**
 *
 * @author kieckegard
 */
public class AlunoPadrao extends Pessoa implements Aluno{

    private String matricula;
    private String email;
    private EstadoAlunoIF estado;

    public AlunoPadrao(String cpf, String nome, LocalDate dataNascimento, Endereco endereco, String matricula, String email, EstadoAlunoIF estado) {
        super(cpf, nome, dataNascimento, endereco);
        this.matricula = matricula;
        this.email = email;
        this.estado = estado;
    }
    
    public AlunoPadrao() {
        
    }

    public String getMatricula() {
        return matricula;
    }

    public String getEmail() {
        return email;
    }

    public Integer getValorEstado() {
        return this.estado.getValue();
    }

    public String getDescricaoEstado() {
        return this.estado.getDescricao();
    }

    public void realizarEmprestimo() throws AlunoInabilitadoException {
        this.estado = estado.realizarEmprestimo();
    }

    public void finalizarEmprestimo() {
        this.estado = estado.finalizarEmprestimo();
    }

    public void setEmail(String email) {
        this.email = email;
    } 
    
    @Override
    public String toString() {
        return "Aluno{" + "matricula=" + matricula + ", email=" + email + '}';
    }
}
