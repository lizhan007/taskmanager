package com.scorpio.util;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.TriggerKey.triggerKey;

import java.util.HashMap;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 定时任务管理类
 *
 * @author 李展
 * @create 2017年11月29日
 */
public class QuartzManager {
	
	private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();

	/**
	 * 添加一个定时任务
	 * 
	 * @param jobName
	 *            任务名
	 * @param clazz
	 *            任务
	 * @param time
	 *            Cron表达式时间设置，参考quartz说明文档
	 * @param map
	 * 				参数
	 * @author lizhan
	 */
	public static void addJob(Class<? extends Job> clazz, String jobName, String triggerName,String time ,HashMap<String,String> map) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			JobDetail job = newJob(clazz).withIdentity(jobName).build();// 任务名，任务执行类
			// 触发器
			CronTrigger trigger = newTrigger()
					.withIdentity(triggerName)// 触发器名
					.withSchedule(cronSchedule(time))
					.build();
			job.getJobDataMap().put("name", map.get("name"));
			job.getJobDataMap().put("phone",map.get("phone"));
			sched.scheduleJob(job, trigger);
			// 启动
			if (!sched.isShutdown()) {
				sched.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 移除一个任务
	 * 
	 * @param jobName
	 * @param triggerName
	 * 
	 * @author lizhan
	 */
	public static void removeJob(String jobName,String triggerName) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched.pauseTrigger(triggerKey(triggerName));// 停止触发器
			sched.unscheduleJob(triggerKey(triggerName));// 移除触发器
			sched.deleteJob(jobKey(jobName));// 删除任务
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 启动所有定时任务
	 * 
	 * @author lizhan
	 */
	public static void startJobs() {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 关闭所有定时任务
	 * 
	 * @author lizhan
	 */
	public static void shutdownJobs() {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
