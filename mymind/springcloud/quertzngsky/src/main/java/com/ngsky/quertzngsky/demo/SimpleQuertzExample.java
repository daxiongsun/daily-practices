package com.ngsky.quertzngsky.demo;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleQuertzExample {

    private final static Logger logger = LoggerFactory.getLogger(SimpleQuertzExample.class);

    public static void main(String[] args) {
        SimpleQuertzExample sqe = new SimpleQuertzExample();
        try {
            // 创建任务调度器
            Scheduler scheduler = sqe.createScheduler();
            // 创建任务及触发器
            JobDetail jobDetailF = sqe.createJobDetail("uploadFileJob", "uploadJobGroup");
            Trigger triggerF = sqe.createTrigger("uploadFileTri", "uploadTriGroup");

            JobDetail jobDetailS = sqe.createJobDetail("downloadFileJob", "downloadJobGroup");
            Trigger triggerS = sqe.createTrigger("downloadFileTri", "downloadTriGroup");

            // 构建调度任务
            scheduler.scheduleJob(jobDetailF, triggerF);
            scheduler.scheduleJob(jobDetailS, triggerS);

            // 创建并注册全局 Job Listener
            scheduler.getListenerManager().addJobListener();


        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }

    protected Scheduler createScheduler() throws SchedulerException {
        return StdSchedulerFactory.getDefaultScheduler();
    }

    protected JobDetail createJobDetail(String jobName, String jobGroup) {
        return JobBuilder.newJob(HelloworldJob.class)
                .withIdentity(jobName, jobGroup)
                .build();
    }

    protected Trigger createTrigger(String triggerName, String triggerGroup) {
        return TriggerBuilder.newTrigger()
                .withIdentity(triggerName, triggerGroup)
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(10)
                                .repeatForever()
                ).build();
    }

}
