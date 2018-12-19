package com.zuql.jt.order.job;

import java.util.Date;

import com.zuql.jt.order.mapper.OrderMapper;
import org.joda.time.DateTime;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class PaymentOrderJob extends QuartzJobBean{
	
	//如果超时2天则修改订单  现在时间 - 2天  > 创建时间
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		//获取spring容器对象 可以获取任意对象
		ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");
		//生成超时时间
		Date dateAgo = new DateTime().minusDays(2).toDate();
		OrderMapper orderMapper = applicationContext.getBean(OrderMapper.class);
		orderMapper.updateStatus(dateAgo);
		System.out.println("定时任务执行完成");
	}
}
