/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.model;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.AlunoDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.Dao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.EmprestimoDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.FactoryProvider;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.LivroDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Aluno;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Bloqueio;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Livro;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.EstadoEmprestimoEnum;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.AlunoInabilitadoException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.EmprestimoAtrasadoException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.LivroIndisponivelException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public class EmprestimoBo {
    
    private final EmprestimoDao emprestimoDao;
    private final LivroDao livroDao;
    private final AlunoDao alunoDao;
    private final Dao<Bloqueio, Integer> bloqueioDao;
    
    public EmprestimoBo() {
        emprestimoDao = FactoryProvider.createFactory(FactoryProvider.jdbc).getEmprestimoDao();
        livroDao = FactoryProvider.createFactory(FactoryProvider.jdbc).getLivroDao();
        alunoDao = FactoryProvider.createFactory(FactoryProvider.jdbc).getAlunoDao();
        bloqueioDao = FactoryProvider.createFactory(FactoryProvider.jdbc).getBloqueioDao();
    }
    
    public void realizarEmprestimo(Aluno aluno, Livro livro, LocalDate startDate) throws LivroIndisponivelException, AlunoInabilitadoException {
        Emprestimo emprestimo = new Emprestimo(aluno,livro,startDate,startDate.plusDays(10),EstadoEmprestimoEnum.CORRENTE);
        emprestimo.processarEmprestimo();
        emprestimoDao.add(emprestimo);
        livroDao.atualizarEstoque(livro);
        alunoDao.atualizarEstadoAluno(aluno);
    }
    
    public void finalizaEmprestimo(Emprestimo emprestimo) throws EmprestimoAtrasadoException {
        try {
            emprestimo.finalizarEmprestimo();
        }
        catch (EmprestimoAtrasadoException ex) {
            LocalDate now = LocalDate.now();
            Bloqueio bloqueio = new Bloqueio(now, now.plusDays(3),emprestimo.getAluno());
            //insere o bloqueio!
            bloqueioDao.add(bloqueio);
            throw new EmprestimoAtrasadoException("O Devolução do empréstimo foi feita com sucesso"
                    + " mas a entrega do mesmo atrasou. O Aluno ficará banido de realizar empréstimos"
                    + " durante 3 dias! ");
        } finally {
            //update Empréstimo e Livro
            emprestimoDao.finalizarEmprestimo(emprestimo);
            livroDao.atualizarEstoque(emprestimo.getLivro());
            alunoDao.atualizarEstadoAluno(emprestimo.getAluno());
        }
    }
    
    public List<Emprestimo> listarEmprestimos() {
        return emprestimoDao.list();
    }
    
    public Emprestimo getEmprestimoById(Integer id) {
        //retorna o empréstimo correspondente ao Id
        return null;
    }
}
