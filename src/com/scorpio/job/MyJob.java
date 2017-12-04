package com.scorpio.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class MyJob implements Job{

	public MyJob() {
    }
	
	public void execute(JobExecutionContext context)
	        throws JobExecutionException {
		JobKey jobKey = context.getJobDetail().getKey();
		JobDataMap data = context.getJobDetail().getJobDataMap();
		String name = data.getString("name");
		String phone = data.getString("phone");
		System.out.println("MyJob: " + jobKey + " executing at " + new Date() + "\n" +
	            "  name " + name + "\n" + 
	            "  phone " + phone);
	}

}
