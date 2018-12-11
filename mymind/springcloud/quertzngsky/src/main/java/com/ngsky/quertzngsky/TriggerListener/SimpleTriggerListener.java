package com.ngsky.quertzngsky.TriggerListener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <dl>
 * <dt>SimpleTriggerListener</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 12/10/2018 11:36 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
public class SimpleTriggerListener implements TriggerListener {

    private final static Logger logger = LoggerFactory.getLogger(SimpleTriggerListener.class);

    private String name;

    public SimpleTriggerListener(String name) {
        this.name = name;
    }

    // 获取TriggerListener 的名字
    @Override
    public String getName() {
        return name;
    }

    // 当与监听器关联的Trigger被触发，Job上的execute()方法将被执行时，Scheduler就调用该方法。
    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
        logger.info("triggerFired***, {}", trigger.getKey().getName() + " was fired!");
    }

    // 在Trigger触发后，Job将要执行时由Scheduler调用该方法，TriggerListener给了一个选择去否决Job的执行。若返回 true，则这个Job将不会
    // 为此次Trigger触发而得到执行
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        return false;
    }

    // Scheduler 调用这个方法是在Trigger错过触发时
    @Override
    public void triggerMisfired(Trigger trigger) {
        logger.info(trigger.getKey().getName() + " misfired!");
    }

    // Trigger 被触发并且完成了Job的执行时，Scheduler调用这个方法
    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
        logger.info(trigger.getKey().getName() + " was completed!");
    }
}
