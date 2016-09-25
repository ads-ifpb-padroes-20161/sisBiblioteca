/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbibliotecanotifier.main;

import br.edu.ifpb.padroes.projeto.sisbibliotecanotifier.facade.QuartzFacade;
import br.edu.ifpb.padroes.projeto.sisbibliotecanotifier.job.NotifyStudentsJob;
import org.quartz.SchedulerException;

/**
 *
 * @author kieckegard
 */
public class AgendadorApp {
    
    public static void main(String[] args) {
        
        QuartzFacade facade = new QuartzFacade();
        
        try {
            facade.startScheduler(1, NotifyStudentsJob.class);
        }
        catch (SchedulerException ex) {
            ex.printStackTrace();
        }
    }
}
