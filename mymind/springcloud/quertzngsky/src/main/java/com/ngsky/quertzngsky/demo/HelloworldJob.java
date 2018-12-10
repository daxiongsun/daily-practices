package com.ngsky.quertzngsky.demo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloworldJob implements Job {

    private final static Logger logger = LoggerFactory.getLogger(HelloworldJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("hello world!");
    }
}
