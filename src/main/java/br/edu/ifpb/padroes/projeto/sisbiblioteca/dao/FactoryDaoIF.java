/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.livro.LivroDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.emprestimo.EmprestimoDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.bloqueio.BloqueioDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.aluno.AlunoDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Endereco;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.IPessoa;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Usuario;

/**
 *
 * @author kieckegard
 */
public interface FactoryDaoIF {

    UsuarioDao getUsuarioDao();

    AlunoDao getAlunoDao();

    EnderecoDao getEnderecoDao();

    LivroDao getLivroDao();

    PessoaDao getPessoaDao();
    
    EmprestimoDao getEmprestimoDao();
    
    BloqueioDao getBloqueioDao();
}
