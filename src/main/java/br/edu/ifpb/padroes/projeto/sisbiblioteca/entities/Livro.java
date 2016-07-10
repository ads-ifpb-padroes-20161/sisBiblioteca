/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.entities;

/**
 *
 * @author kieckegard
 */
public class Livro
{
    private long isbn;
    private String titulo;
    private String autor;
    private String descricao;
    
    public Livro(long isbn, String titulo, String autor, String descricao){
        this.isbn      = isbn;
        this.titulo    = titulo;
        this.autor     = autor;
        this.descricao = descricao;
    }

    public long getIsbn()
    {
        return isbn;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public String getAutor()
    {
        return autor;
    }

    public String getDescricao()
    {
        return descricao;
    }
    
    @Override
    public String toString()
    {
        return "Livro{" + "isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", descricao=" + descricao + '}';
    }
}
