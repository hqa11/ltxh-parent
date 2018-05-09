package com.agc.utils.quartz;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.agc.service.quartz.AutoJobService;
import com.agc.utils.ye.YeRedisUtil;


public class AutoJobUtil  
extends QuartzJobBean   
{  

	//判断作业是否执行的旗标  
	private boolean isRunning = false;  

	//该作业类所依赖的业务逻辑组件  
	private AutoJobService empMgr;

	public void setEmpMgr(AutoJobService empMgr)  
	{  
		this.empMgr = empMgr;  
	}  
	//定义任务执行体  
	public void executeInternal(JobExecutionContext ctx)   
			throws JobExecutionException   
	{  
		if (!isRunning)  
		{  
			isRunning = true;  

			empMgr.testRedis();

			isRunning = false;  
		}  
	}  
}  