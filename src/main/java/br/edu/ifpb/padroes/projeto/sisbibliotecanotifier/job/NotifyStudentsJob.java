/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbibliotecanotifier.job;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.model.EmprestimoBo;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author kieckegard
 */
@DisallowConcurrentExecution
public class NotifyStudentsJob implements Job {
    
    private final EmprestimoBo emprestimoBo;
    
    public NotifyStudentsJob() {
        emprestimoBo = new EmprestimoBo();
    }

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        System.out.println("1 - O Processo de verificação & notificação de alunos começou...");
        emprestimoBo.notificaAlunos();
    }
    
    @Override
    public String toString() {
        return "NotifyStudentsJob";
    }
    
}
