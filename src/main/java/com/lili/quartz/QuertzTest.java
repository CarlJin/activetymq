package com.lili.quartz;

import org.quartz.SchedulerException;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

@ContextConfiguration(locations = { "classpath:/applicationContext-service.xml"})
public class QuertzTest {
	@Test
	public void test() throws InterruptedException, SchedulerException {
		// 实际应用中主线程不能停止，否则Scheduler得不到执行
		Thread.sleep(120000);
	}
}