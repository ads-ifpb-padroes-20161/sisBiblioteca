/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbibliotecanotifier.facade;

import br.edu.ifpb.padroes.projeto.sisbibliotecanotifier.job.NotifyStudentsJob;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author kieckegard
 */
public class QuartzFacade {
    
    public void startScheduler(Integer secInterval, Class<? extends Job> job) throws SchedulerException {
        
        JobDetail jobDetail = createJobDetail(job);
        Trigger trigger = createTrigger(secInterval);
        
        Scheduler scheduler = createScheduler();
        
        scheduler.start();
        scheduler.scheduleJob(jobDetail,trigger);        
    }
    
    private JobDetail createJobDetail(Class<? extends Job> job) {
        
        JobDetail jobDetail = JobBuilder
                .newJob(job)
                .withIdentity("studentNotifierJob", "notifier")
                .build();
        
        return jobDetail;
    }
    
    private Trigger createTrigger(Integer secInterval) {
        
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("studentNotifierTrigger","notifier")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(secInterval).repeatForever())
                .build();
        
        return trigger;
    }
    
    private Scheduler createScheduler() throws SchedulerException {
        
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        
        return scheduler;
    }
}
