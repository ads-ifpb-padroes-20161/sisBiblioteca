/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.livro;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.LivroIndisponivelException;

/**
 *
 * @author kieckegard
 */
public interface Livro {
    
    long getIsbn();
    String getTitulo();
    String getAutor();
    String getDescricao();
    int getEstoque();
    void emprestar() throws LivroIndisponivelException ;
    void devolver();
}
