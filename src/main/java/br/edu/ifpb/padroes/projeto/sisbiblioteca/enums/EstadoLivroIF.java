/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.enums;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.ItemLivroPadrao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.LivroIndisponivelException;

/**
 *
 * @author kieckegard
 */
public interface EstadoLivroIF {

    public EstadoLivroIF emprestar(ItemLivroPadrao livro) throws LivroIndisponivelException;

    public EstadoLivroIF devolver(ItemLivroPadrao livro);

    public int getIndex();

    public String getValue();
}
