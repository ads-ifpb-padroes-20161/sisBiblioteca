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
}
