/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.entities;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.utils.DateUtils;
import java.time.LocalDate;

/**
 *
 * @author kieckegard
 */
public abstract class Pessoa {

    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    private Endereco endereco;

    public Pessoa(String cpf, String nome, LocalDate dataNascimento, Endereco endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    public Pessoa(String cpf, String nome, LocalDate dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }
    
    public Pessoa() {
        
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    public String getFormattedDataNascimento() {
        return DateUtils.formatToBrazilPattern(dataNascimento);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco e) {
        this.endereco = e;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    } 

    @Override
    public String toString() {
        return "Pessoa{" + "cpf=" + cpf + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", endereco=" + endereco + '}';
    }

}
