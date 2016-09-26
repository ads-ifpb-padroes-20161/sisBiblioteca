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
import org.quartz.SchedulerException;

/**
 *
 * @author kieckegard
 */

@WebListener
public class InitializeListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        QuartzFacade quartzFacade = new QuartzFacade();
        try {
            quartzFacade.startScheduler(10, NotifyStudentsJob.class);
            Log.log(1, "Agendador Iniciado. Verificando Alunos...");
        }
        catch (SchedulerException ex) {
            Log.log(1, "Não foi possível iniciar o agendador.", ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
        Log.log(1, "Encerrando sisBiblioteca... ");
    }
    
}
