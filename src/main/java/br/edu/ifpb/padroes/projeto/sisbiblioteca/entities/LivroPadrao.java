/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.entities;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.LivroIndisponivelException;

/**
 *
 * @author kieckegard
 */
public class LivroPadrao implements Comparable<LivroPadrao>, Livro{

    private long isbn;
    private String titulo;
    private String autor;
    private String descricao;
    private int estoque;

    public LivroPadrao(long isbn, String titulo, String autor, String descricao, int estoque) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.descricao = descricao;
        this.estoque = estoque;
    }

    @Override
    public long getIsbn() {
        return isbn;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getAutor() {
        return autor;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public int getEstoque() {
        return this.estoque;
    }
    
    @Override
    public void emprestar() throws LivroIndisponivelException {
        verifyEstoque();
        this.estoque--;
    }
    
    @Override
    public void devolver() {
        this.estoque++;
    }
    
    private void verifyEstoque() throws LivroIndisponivelException {
        if(this.estoque == 0)
            throw new LivroIndisponivelException("Não há mais exemplares desse livro!");
    }

    @Override
    public String toString() {
        return "Livro{" + "isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", descricao=" + descricao + ", estoque=" + estoque + "}";
    }

    @Override
    public int compareTo(LivroPadrao o) {
        return this.titulo.compareTo(o.titulo);
    }
}
