/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Endereco;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Pessoa;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Usuario;

/**
 *
 * @author kieckegard
 */
public interface FactoryDaoIF {

    Dao<Usuario, String> getUsuarioDao();

    AlunoDao getAlunoDao();

    Dao<Endereco, Pessoa> getEnderecoDao();

    LivroDao getLivroDao();

    SimpleDao<Pessoa, String> getPessoaDao();
    
    EmprestimoDao getEmprestimoDao();
    
    BloqueioDao getBloqueioDao();
}
