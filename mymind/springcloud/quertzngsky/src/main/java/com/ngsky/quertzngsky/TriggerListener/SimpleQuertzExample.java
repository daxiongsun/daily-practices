package com.ngsky.quertzngsky.TriggerListener;

import com.ngsky.quertzngsky.joblistener.HelloworldJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <dl>
 * <dt>SimpleQuertzExample</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 12/10/2018 11:54 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
public class SimpleQuertzExample {
    private final static Logger logger = LoggerFactory.getLogger(SimpleQuertzExample.class);

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        SimpleQuertzExample exam = new SimpleQuertzExample();
        logger.info("init scheduler components");

        // 创建调度器
        Scheduler scheduler = exam.createScheduler();

        // 创建两个任务及对应的触发器
        JobDetail upJobDetail = exam.createJobDetail("jobUp", "jobUpGroup");
        Trigger upTrigger = exam.createTrigger("jobTir", "jobTriGroup");

        JobDetail downJobDetail = exam.createJobDetail("jobDown", "jobDownGroup");
        Trigger downTrigger = exam.createTrigger("jobDownTri", "jobTriGroup");

        // 构建调度任务
        scheduler.scheduleJob(upJobDetail, upTrigger);
        scheduler.scheduleJob(downJobDetail, downTrigger);

        // 创建并注册一个局部的 Trigger Listener
        scheduler.getListenerManager().addTriggerListener(new SimpleTriggerListener("SimpleTrigger"), EverythingMatcher.allTriggers());

        logger.info("execute scheduler");

        // 开启调度器
        scheduler.start();

        Thread.sleep(20000);
        scheduler.shutdown();
        logger.info("shut down scheduler");
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
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();
    }

}
