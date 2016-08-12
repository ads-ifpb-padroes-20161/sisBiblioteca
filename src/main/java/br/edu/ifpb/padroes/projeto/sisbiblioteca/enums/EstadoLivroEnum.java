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
public enum EstadoLivroEnum implements EstadoLivroIF
{
    DISPONIVEL{

        @Override
        public EstadoLivroIF emprestar(ItemLivroPadrao livro)
        {
            livro.decrementarEstoque();
            if(livro.getEstoque() == 0) return INDISPONIVEL;
            return this;
        }

        @Override
        public EstadoLivroIF devolver(ItemLivroPadrao livro)
        {
            livro.incrementarEstoque();
            return this;
        }
        
        @Override
        public int getIndex(){
            return 1;
        }

        @Override
        public String getValue()
        {
            return this.name();
        }
        
    },
    INDISPONIVEL{

        @Override
        public EstadoLivroIF emprestar(ItemLivroPadrao livro) throws LivroIndisponivelException
        {
            throw new LivroIndisponivelException("O livro encontra-se indisponível.");
        }

        @Override
        public EstadoLivroIF devolver(ItemLivroPadrao livro)
        {
            livro.incrementarEstoque();
            return DISPONIVEL;
        }
        
        @Override
        public int getIndex(){
            return 2;
        }

        @Override
        public String getValue()
        {
            return this.name();
        }
    }
}
