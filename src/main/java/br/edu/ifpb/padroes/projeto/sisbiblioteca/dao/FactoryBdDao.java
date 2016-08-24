/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Aluno;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Endereco;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.LivroPadrao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Pessoa;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Usuario;

/**
 *
 * @author kieckegard
 */
public class FactoryBdDao implements FactoryDaoIF {

    @Override
    public Dao<Aluno, String> getAlunoDao() {
        return new AlunoBdDao();
    }

    @Override
    public Dao<LivroPadrao, Long> getLivroDao() {
        return new LivroBdDao();
    }

    @Override
    public SimpleDao<Pessoa, String> getPessoaDao() {
        return new PessoaBdDao();
    }

    @Override
    public Dao<Usuario, String> getUsuarioDao() {
        return new UsuarioBdDao();
    }

    @Override
    public Dao<Endereco, Pessoa> getEnderecoDao() {
        return new EnderecoBdDao();
    }

}
