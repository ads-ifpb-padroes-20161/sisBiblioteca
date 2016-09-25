/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.entities;

import java.time.LocalDate;

/**
 *
 * @author kieckegard
 */
public interface IPessoa {
    
    public String getCpf();

    public String getNome();

    public LocalDate getDataNascimento();
    
    public Endereco getEndereco();
    
    public void setEndereco(Endereco e);

    public void setCpf(String cpf);

    public void setNome(String nome);

    public void setDataNascimento(LocalDate dataNascimento);
    
}
