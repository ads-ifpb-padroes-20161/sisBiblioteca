/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.entities;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.EstadoAlunoEnum;
import java.time.LocalDate;


/**
 *
 * @author kieckegard
 */
public class Aluno extends Pessoa
{
    private String matricula;
    private String email;
    private EstadoAlunoEnum estado;
    
    public Aluno(String cpf, String nome, LocalDate dataNascimento, Endereco endereco, String matricula, String email, EstadoAlunoEnum estado)
    {
        super(cpf, nome, dataNascimento, endereco);
        this.matricula = matricula;
        this.email     = email;
        this.estado    = estado;
    }
    
    public Aluno(String cpf, String nome, LocalDate dataNascimento, Endereco endereco, String matricula, String email)
    {
        super(cpf, nome, dataNascimento, endereco);
        this.matricula = matricula;
        this.email     = email;
        this.estado    = EstadoAlunoEnum.REGULAR;
    }
    
    public Aluno(String cpf, String nome, LocalDate dataNascimento, String matricula, String email)
    {
        super(cpf, nome, dataNascimento);
        this.matricula = matricula;
        this.email     = email;
        this.estado    = EstadoAlunoEnum.REGULAR;
    }

    public String getMatricula()
    {
        return matricula;
    }

    public String getEmail()
    {
        return email;
    }

    public EstadoAlunoEnum getEstado()
    {
        return estado;
    }

    public void setEstado(EstadoAlunoEnum estado)
    {
        this.estado = estado;
    }

    @Override
    public String toString()
    {
        return "Aluno{" + "matricula=" + matricula + ", email=" + email + '}';
    }
}
