package com.lili.quartz;

import java.util.HashMap;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;

public class ParticipleTask {
	@Autowired
	ParticipleManager participleManager;
	@Autowired
	SchedulerFactory schedulerFactoryBean;

	public void initTask() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobDetail jobDetail = JobBuilder.newJob(QuartzTaskFactory.class)
				.withIdentity("taskId_" + "ParticipleManager", "taskName_" + "ParticipleManager").build();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ParticipleManager", participleManager);
		jobDetail.getJobDataMap().put("scheduleJob", map);
		// 时间表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 */1 * * * ?");// 每分钟执行一次
		// 构建一个trigger(触发器)
		CronTrigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("taskId_" + "ParticipleManager", "taskName_" + "ParticipleManager")
				.withSchedule(scheduleBuilder).build();
		scheduler.scheduleJob(jobDetail, trigger);
	}
}
