/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.livro;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.livro.Livro;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public interface LivroDao {
    
    void cadastrarLivro(Livro livro);
    Livro recuperarLivroPorIsbn(Long isbn);
    void removerLivro(Long isbn);
    List<Livro> listarLivros();
    void atualizarEstoque(Livro livro);
    List<Livro> listarLivrosDisponiveis();
    
}
