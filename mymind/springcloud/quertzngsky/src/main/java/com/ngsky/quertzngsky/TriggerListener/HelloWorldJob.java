package com.ngsky.quertzngsky.TriggerListener;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <dl>
 * <dt>HelloWorldJob</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 12/10/2018 11:33 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
public class HelloWorldJob implements Job {

    private final static Logger logger = LoggerFactory.getLogger(HelloWorldJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("hello world!");
    }
}
