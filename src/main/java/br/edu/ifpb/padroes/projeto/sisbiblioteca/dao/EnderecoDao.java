/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Endereco;

/**
 *
 * @author kieckegard
 */
public interface EnderecoDao {
    void adicionarEndereco(Endereco endereco);
    void removerEndereco(Integer id);
    void atualizarEndereco(Endereco endereco);
    Endereco recuperarEnderecoPorPessoaCpf(String cpf);
}
