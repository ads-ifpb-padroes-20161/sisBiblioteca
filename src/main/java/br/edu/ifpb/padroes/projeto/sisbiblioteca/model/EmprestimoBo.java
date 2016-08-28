/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.model;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.aluno.AlunoDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.bloqueio.BloqueioDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.emprestimo.EmprestimoDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.FactoryProvider;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.livro.LivroDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.aluno.Aluno;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Bloqueio;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Emprestimo;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.livro.Livro;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.EstadoBloqueioEnum;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.enums.EstadoEmprestimoEnum;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.AlunoInabilitadoException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.EmprestimoAtrasadoException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.EmprestimoJaFinalizadoException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.LivroIndisponivelException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public class EmprestimoBo {

    private final EmprestimoDao emprestimoDao;
    private final LivroDao livroDao;
    private final AlunoDao alunoDao;
    private final BloqueioDao bloqueioDao;

    public EmprestimoBo() {
        emprestimoDao = FactoryProvider.createFactory(FactoryProvider.jdbc).getEmprestimoDao();
        livroDao = FactoryProvider.createFactory(FactoryProvider.jdbc).getLivroDao();
        alunoDao = FactoryProvider.createFactory(FactoryProvider.jdbc).getAlunoDao();
        bloqueioDao = FactoryProvider.createFactory(FactoryProvider.jdbc).getBloqueioDao();
    }

    public Emprestimo realizarEmprestimo(Aluno aluno, Livro livro, LocalDate startDate) throws LivroIndisponivelException, AlunoInabilitadoException {
        
        Emprestimo emprestimo = new Emprestimo(aluno, livro, startDate, startDate.plusDays(10), EstadoEmprestimoEnum.ANDAMENTO);
        
        emprestimo.processarEmprestimo();
        
        newEmprestimoOnDao(emprestimo);
        
        return emprestimo;
    }

    public void finalizaEmprestimo(Emprestimo emprestimo) throws EmprestimoAtrasadoException, EmprestimoJaFinalizadoException {
        try {
            emprestimo.finalizarEmprestimo();
            finalizeEmprestimoOnDao(emprestimo);
        }
        catch (EmprestimoAtrasadoException ex) {
            LocalDate now = LocalDate.now();
            Bloqueio bloqueio = new Bloqueio(now, now.plusDays(3), emprestimo.getAluno(), EstadoBloqueioEnum.ANDAMENTO);
            //insere o bloqueio!
            bloqueioDao.inserir(bloqueio);
            finalizeEmprestimoOnDao(emprestimo);
            throw new EmprestimoAtrasadoException("O Devolução do empréstimo foi feita com sucesso"
                    + " mas a entrega do mesmo atrasou. O Aluno ficará banido de realizar empréstimos"
                    + " durante 3 dias! ");
        }
    }
    
    private void newEmprestimoOnDao(Emprestimo emprestimo) {
        
        emprestimoDao.adicionarEmprestimo(emprestimo);
        
        updateAlunoAndLivro(emprestimo.getAluno(), emprestimo.getLivro());
    }

    private void finalizeEmprestimoOnDao(Emprestimo emprestimo) {
        
        emprestimoDao.finalizarEmprestimo(emprestimo);
        
        updateAlunoAndLivro(emprestimo.getAluno(), emprestimo.getLivro());
    }
    
    private void updateAlunoAndLivro(Aluno aluno, Livro livro) {
        
        livroDao.atualizarEstoque(livro);
        alunoDao.atualizarEstadoAluno(aluno);
    }

    public List<Emprestimo> listarEmprestimos() { 
        List<Emprestimo> emprestimos = emprestimoDao.listarEmprestimos();
        Collections.sort(emprestimos);
        return Collections.unmodifiableList(emprestimos);
    }

    public Emprestimo getEmprestimoById(Integer id) { 
        return emprestimoDao.recuperarEmprestimoPorId(id);
    }
}
