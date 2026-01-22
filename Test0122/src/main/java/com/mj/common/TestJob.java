package com.mj.common;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import com.mj.dao.TestDao;

public class TestJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 1. 현재 실행중인 웹의 모든 정보를 담고 있는 바구니(appCtx)를 바로 가져옵니다.
        WebApplicationContext appCtx = ContextLoader.getCurrentWebApplicationContext();
        
        // 2. 바구니에서 TestDao를 꺼냅니다. (이게 가장 확실합니다)
        TestDao tDao = appCtx.getBean(TestDao.class);
        
        // 3. 로직 실행
        java.util.Date now = new java.util.Date();
        tDao.pointUpdate();
        int count = tDao.userSelect();
        
        System.out.println("Job이 실행됨 : " + now + " / " + count + "명에게 포인트 부여(1점).");
    }
}