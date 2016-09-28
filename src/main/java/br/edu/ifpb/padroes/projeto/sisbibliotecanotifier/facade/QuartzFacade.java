/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbibliotecanotifier.facade;

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
    
    public static JobDetail createJobDetail(Class<? extends Job> job, String jobGroupName, String jobName) {
        
        JobDetail jobDetail = JobBuilder
                .newJob(job)
                .withIdentity(jobGroupName, jobName)
                .build();
        
        return jobDetail;
    }
    
    public static Trigger createTrigger(Integer secInterval, String triggerName, String triggerGroupName) {
        
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(triggerName,triggerGroupName)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(secInterval).repeatForever())
                .build();
        
        return trigger;
    }
    
    public static Scheduler createScheduler() throws SchedulerException {
        
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        return scheduler;
    }
}
