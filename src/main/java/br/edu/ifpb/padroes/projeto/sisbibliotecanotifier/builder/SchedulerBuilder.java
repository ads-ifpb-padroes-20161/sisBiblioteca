/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbibliotecanotifier.builder;

import br.edu.ifpb.padroes.projeto.sisbibliotecanotifier.facade.QuartzFacade;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

/**
 *
 * @author kieckegard
 */
public class SchedulerBuilder {
    
    private final Scheduler scheduler;
    
    public SchedulerBuilder() throws SchedulerException {
        this.scheduler = QuartzFacade.createScheduler();
    }
    
    public SchedulerBuilder addJob(Integer secInterval, Class<? extends Job> job, String typeName) throws SchedulerException {
        
        JobDetail jobDetail = QuartzFacade.createJobDetail(job, job.toString(), typeName);
        Trigger trigger = QuartzFacade.createTrigger(secInterval, job.toString()+"Trigger", typeName);
        
        scheduler.scheduleJob(jobDetail,trigger);     
        
        return this;
    }
    
    public Scheduler getInstance() {
        return this.scheduler;
    }
}
