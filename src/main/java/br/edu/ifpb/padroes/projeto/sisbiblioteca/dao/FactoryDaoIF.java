/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Aluno;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Endereco;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.ItemLivro;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Livro;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Pessoa;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Usuario;


/**
 *
 * @author kieckegard
 */
public interface FactoryDaoIF
{
    
    Dao<Usuario, String> getUsuarioDao();
    Dao<Aluno, String> getAlunoDao();
    Dao<Endereco, Pessoa> getEnderecoDao();
    Dao<ItemLivro, Long> getItemLivroDao();
    Dao<Livro, Long> getLivroDao();
    SimpleDao<Pessoa, String> getPessoaDao();
}
