/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.livro.LivroDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.livro.LivroBdDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.emprestimo.EmprestimoDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.emprestimo.EmprestimoBdDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.bloqueio.BloqueioBdDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.bloqueio.BloqueioDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.aluno.AlunoDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.aluno.AlunoBdDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Endereco;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.IPessoa;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Usuario;

/**
 *
 * @author kieckegard
 */
public class FactoryBdDao implements FactoryDaoIF {

    @Override
    public AlunoDao getAlunoDao() {
        return new AlunoBdDao();
    }

    @Override
    public LivroDao getLivroDao() {
        return new LivroBdDao();
    }

    @Override
    public PessoaDao getPessoaDao() {
        return new PessoaBdDao();
    }

    @Override
    public UsuarioDao getUsuarioDao() {
        return new UsuarioBdDao();
    }

    @Override
    public EnderecoDao getEnderecoDao() {
        return new EnderecoBdDao();
    }

    @Override
    public EmprestimoDao getEmprestimoDao() {
        return new EmprestimoBdDao();
    }

    @Override
    public BloqueioDao getBloqueioDao() {
        return new BloqueioBdDao();
    }

}
