package com.mj.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.mj.common.TestJob;
import com.mj.dao.TestDao;
import com.mj.dto.UsersDto;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	TestDao tDao;
	private ApplicationContext appCtx;
	private Scheduler s = null;
	
	@Override
	public int loginSelect(String id, String pw) {
		return tDao.loginSelect(id, pw);
	}

	@Override
	public void createId(String id, String pw, String name, int point) {
		tDao.createId(id, pw, name, point);
	}

	@Override
	public String getId(String id) {
		return tDao.getId(id);
	}

	@Override
	public UsersDto userInfo(String id) {
		return tDao.userInfo(id);
	}

	@Override
	public List<UsersDto> management() {
		return tDao.management();
	}

	@Override
	public void userUpdate(String pw, String name, int point, String id) {
		tDao.userUpdate(pw, name, point, id);
	}

	@Override
	public void userDelete(String id) {
		tDao.userDelete(id);
	}

	@Override
	public void pointUpdate() {
		tDao.pointUpdate();
	}

	@Override
	public void plusPoint(int plusPoint, String id) {
		tDao.plusPoint(plusPoint, id);
	}

	@Override
	public void minusPoint(int minusPoint, String id) {
		tDao.minusPoint(minusPoint, id);
	}

	@Override
	public void startScheduler() throws Exception {
		System.out.println("<<< 포인트 스케줄러가 시작되었습니다. >>>");
	    SchedulerFactory sf = new StdSchedulerFactory();
	    s = sf.getScheduler();
	    
	    JobDetail jobDetail = JobBuilder.newJob(TestJob.class).build();

	    CronTrigger trigger = (CronTrigger)TriggerBuilder.newTrigger()
	                            .withSchedule(CronScheduleBuilder.cronSchedule("*/20 * * * * ?"))
	                            .forJob(jobDetail)
	                            .build();

	    s.scheduleJob(jobDetail, trigger);
	    s.start();
		
	}

	@Override
	public void endScheduler() throws Exception {
		s.shutdown();
		System.out.println("<<< 포인트 스케줄러가 종료되었습니다. >>>");
	}

	@Override
	public int userSelect() {
		return tDao.userSelect();
	}
	

}
