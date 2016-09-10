/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Livro;

/**
 *
 * @author kieckegard
 */
public interface LivroDao extends Dao<Livro, Long> {
    
    void atualizarEstoque(Livro livro);
    
}
