/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.model;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.Dao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.FactoryProvider;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.ItemLivroPadrao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.ISBNJaExisteException;


/**
 *
 * @author kieckegard
 */
public class ItemLivroBo
{
    private final Dao<ItemLivroPadrao, Long> itemLivroDao;
    private final LivroBo livroBo;
    
    public ItemLivroBo(){
        itemLivroDao = FactoryProvider.createFactory(1).getItemLivroDao();
        livroBo = new LivroBo();
    }
    
    public void cadastrarLivro(ItemLivroPadrao itemLivro) throws ISBNJaExisteException{
        verificaLivro(itemLivro);
        itemLivroDao.add(itemLivro);
    }
    
    public void removerLivro(ItemLivroPadrao itemLivro){
        itemLivroDao.rem(itemLivro);
    }
    
    public void verificaLivro(ItemLivroPadrao itemLivro) throws ISBNJaExisteException{
        livroBo.verificaLivro(itemLivro.getItemLivro());
    }
    
}
