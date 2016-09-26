/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.control;

import br.edu.ifpb.padroes.projeto.sisbibliotecanotifier.facade.QuartzFacade;
import br.edu.ifpb.padroes.projeto.sisbibliotecanotifier.job.NotifyStudentsJob;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.jfree.util.Log;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

/**
 *
 * @author kieckegard
 */

@WebListener
public class InitializeListener implements ServletContextListener {
    
    private Scheduler scheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        QuartzFacade quartzFacade = new QuartzFacade();
        try {
            
            this.scheduler = quartzFacade.createScheduler(10, NotifyStudentsJob.class);
            //this.scheduler.start();
            
            Log.log(1, "Agendador Iniciado. Verificando Alunos...");
        }
        catch (SchedulerException ex) {
            Log.log(1, "Não foi possível iniciar o agendador.", ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
        try {
            scheduler.shutdown();
            Log.log(1, "Finalizando Agendador...");
        }
        catch (SchedulerException ex) {
            Log.log(1, "Não foi possível finalizar o agendador.", ex);
        }
    }
    
}
