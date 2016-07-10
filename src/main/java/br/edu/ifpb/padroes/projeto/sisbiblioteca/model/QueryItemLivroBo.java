/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.model;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.Dao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.FactoryProvider;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.ItemLivro;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public class QueryItemLivroBo
{
    private final Dao<ItemLivro, Long> itemLivroDao;
    
    public QueryItemLivroBo(){
        itemLivroDao = FactoryProvider.createFactory(1).getItemLivroDao();
    }
    
    public List<ItemLivro> listar(){
        return Collections.unmodifiableList(itemLivroDao.list());
    }
}
