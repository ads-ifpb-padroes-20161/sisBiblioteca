/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

/**
 *
 * @author kieckegard
 */
public interface SimpleDao<T,I>
{
    void add(T obj);
    void rem(T obj);
    void update(T obj);
}
