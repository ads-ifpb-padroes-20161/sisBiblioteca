/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.entities;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.EstadoLivroEnum;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.EstadoLivroIF;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.LivroIndisponivelException;

/**
 *
 * @author kieckegard
 */
public class ItemLivro
{
    private Livro livro;
    private int estoque;
    private EstadoLivroIF estado;
    
    public ItemLivro(Livro livro, int estoque, EstadoLivroEnum estado){
        this.livro = livro;
        this.estoque = estoque;
        this.estado = estado;
    }
    
    public ItemLivro(Livro livro){
        this.livro = livro;
        this.estoque = 1;
        this.estado = EstadoLivroEnum.DISPONIVEL;
    }
    
    public Livro getItemLivro(){
        return this.livro;
    }
    
    public int getEstoque(){
        return this.estoque;
    }
    
    public String getEstadoValue(){
        return this.estado.getValue();
    }
    
    public int getEstadoIndex(){
        return this.estado.getIndex();
    }
    
    public void decrementarEstoque(){
        this.estoque--;
    }
    
    public void incrementarEstoque(){
        this.estoque++;
    }
    
    public void emprestar() throws LivroIndisponivelException{
        this.estado = estado.emprestar(this);
    }
    
    public void devolver(){
        this.estado = estado.devolver(this);
    }

    @Override
    public String toString()
    {
        return "ItemLivro{" + "Livro=" + livro + ", estoque=" + estoque + ", estado=" + estado + '}';
    }
    
    
}
