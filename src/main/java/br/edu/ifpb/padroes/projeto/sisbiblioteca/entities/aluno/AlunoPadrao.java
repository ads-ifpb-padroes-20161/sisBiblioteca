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

    @Override
    public String getMatricula() {
        return matricula;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public Integer getValorEstado() {
        return this.estado.getValue();
    }

    @Override
    public String getDescricaoEstado() {
        return this.estado.getDescricao();
    }

    @Override
    public void realizarEmprestimo() throws AlunoInabilitadoException {
        this.estado = estado.realizarEmprestimo();
    }

    @Override
    public void finalizarEmprestimo() {
        this.estado = estado.finalizarEmprestimo();
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEstado(EstadoAlunoIF estado) {
        this.estado = estado;
    } 
    
    @Override
    public String toString() {
        return "Aluno{" + "matricula=" + matricula + ", email=" + email + '}';
    }
}
