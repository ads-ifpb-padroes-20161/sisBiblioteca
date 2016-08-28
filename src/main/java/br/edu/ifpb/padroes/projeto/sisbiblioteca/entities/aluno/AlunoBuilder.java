/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.aluno;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Endereco;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.EstadoAlunoIF;
import java.time.LocalDate;

/**
 *
 * @author kieckegard
 */
public class AlunoBuilder {
    
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    private Endereco endereco;
    private String matricula;
    private String email;
    private EstadoAlunoIF estado;

    public AlunoBuilder setMatricula(String matricula) {
        this.matricula = matricula;
        return this;
    }

    public AlunoBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public AlunoBuilder setEstado(EstadoAlunoIF estado) {
        this.estado = estado;
        return this;
    }

    public AlunoBuilder setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public AlunoBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public AlunoBuilder setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public AlunoBuilder setEndereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    } 
    
    public AlunoPadrao getInstance() {
        return new AlunoPadrao(cpf,nome,dataNascimento,endereco,matricula,email,estado);
    }
    
    
}
