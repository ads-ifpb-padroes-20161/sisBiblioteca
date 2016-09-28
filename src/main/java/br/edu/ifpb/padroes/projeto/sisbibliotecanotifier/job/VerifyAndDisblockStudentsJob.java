/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbibliotecanotifier.job;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.FactoryProvider;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.bloqueio.BloqueioDao;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author kieckegard
 */
@DisallowConcurrentExecution
public class VerifyAndDisblockStudentsJob implements Job {
    
    private final BloqueioDao bloqueioDao;
    
    public VerifyAndDisblockStudentsJob() {
        bloqueioDao = FactoryProvider.createFactory(FactoryProvider.jdbc).getBloqueioDao();
    }

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        System.out.println("2 - Iniciando a verificação & desbloqueio de alunos começou...");
        bloqueioDao.verificaEDesbloqueiaAlunos();
    }
    
    @Override
    public String toString() {
        return "VerifyAndDisblockStudentsJob";
    }
    
}
