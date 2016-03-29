package com.lili.quartz;

import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class QuartzTaskFactory implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		ParticipleManager participleManager;
		@SuppressWarnings("rawtypes")
		Map map = (Map)context.getMergedJobDataMap().get("scheduleJob");
		participleManager = (ParticipleManager) map.get("ParticipleManager");
		participleManager.participleContent();
	}

}
