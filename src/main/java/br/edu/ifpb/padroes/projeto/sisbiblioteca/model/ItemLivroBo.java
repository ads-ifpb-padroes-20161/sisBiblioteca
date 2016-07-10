/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.model;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.Dao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.FactoryProvider;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.ItemLivro;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.ISBNJaExisteException;


/**
 *
 * @author kieckegard
 */
public class ItemLivroBo
{
    private final Dao<ItemLivro, Long> itemLivroDao;
    private final LivroBo livroBo;
    
    public ItemLivroBo(){
        itemLivroDao = FactoryProvider.createFactory(1).getItemLivroDao();
        livroBo = new LivroBo();
    }
    
    public void cadastrarLivro(ItemLivro itemLivro) throws ISBNJaExisteException{
        verificaLivro(itemLivro);
        itemLivroDao.add(itemLivro);
    }
    
    public void removerLivro(ItemLivro itemLivro){
        itemLivroDao.rem(itemLivro);
    }
    
    public void verificaLivro(ItemLivro itemLivro) throws ISBNJaExisteException{
        livroBo.verificaLivro(itemLivro.getItemLivro());
    }
    
}
