/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.model;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.Dao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.FactoryProvider;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Livro;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.LivroPadrao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.ISBNJaExisteException;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public class LivroBo {

    private Dao<LivroPadrao, Long> livroDao;

    public LivroBo() {
        livroDao = FactoryProvider.createFactory(1).getLivroDao();
    }
    
    public void cadastraLivro(LivroPadrao livro) throws ISBNJaExisteException {
        verificaIsbn(livro.getIsbn());
        livroDao.add(livro);
    }

    private void verificaIsbn(long isbn) throws ISBNJaExisteException {
        for (LivroPadrao l : livroDao.list()) {
            if (l.getIsbn() == isbn) {
                throw new ISBNJaExisteException("Já existe um livro salvo no banco com esse mesmo ISBN!");
            }
        }
    }
    
    public Livro getLivro(long isbn) {
        return livroDao.get(isbn);
    }
    
    public List<LivroPadrao> list() {
        return livroDao.list();
    }
    
}
