package com.ngsky.quertzngsky.demo;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloworldJobListener implements JobListener {

    private final static Logger logger = LoggerFactory.getLogger(HelloworldJobListener.class);

    // 获取 JobListener 的名称
    @Override
    public String getName() {
        String name = getClass().getSimpleName();
        logger.info("JobListener name: {}", name);
        return name;
    }

    // Scheduler 在将要执行 JobDetail时调用该方法
    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        String jobName = jobExecutionContext.getJobDetail().getKey().getName();
        logger.info("jobToBeExecuted, JobName : {}", jobName);
    }

    // Scheduler 在将要执行 JobDetail 时，被 TriggerListener 否决时执行该方法
    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        String jobName = jobExecutionContext.getJobDetail().getKey().getName();
        logger.info("jobExecutionVetoed, jobName: {}", jobName);
    }

    // Scheduler 执行完 JobDetail 后执行该方法
    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        String jobName = jobExecutionContext.getJobDetail().getKey().getName();
        logger.info("jobWasExecuted jobName: {}", jobName);
    }
}
