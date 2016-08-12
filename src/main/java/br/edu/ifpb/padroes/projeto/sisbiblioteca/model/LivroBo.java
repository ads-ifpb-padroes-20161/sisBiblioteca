/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.model;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.Dao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.FactoryProvider;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Livro;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.ISBNJaExisteException;

/**
 *
 * @author kieckegard
 */
public class LivroBo {

    private Dao<Livro, Long> livroDao;

    public LivroBo() {
        livroDao = FactoryProvider.createFactory(1).getLivroDao();
    }

    public void verificaLivro(Livro livro) throws ISBNJaExisteException {
        verificaIsbn(livro.getIsbn());
    }

    public void verificaIsbn(long isbn) throws ISBNJaExisteException {
        for (Livro l : livroDao.list()) {
            if (l.getIsbn() == isbn) {
                throw new ISBNJaExisteException("Já existe um livro salvo no banco com esse mesmo ISBN!");
            }
        }
    }
}
