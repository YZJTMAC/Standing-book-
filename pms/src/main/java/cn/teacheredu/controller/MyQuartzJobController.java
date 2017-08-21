package cn.teacheredu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Controller;

/**
 * 定时任务
 * @author jiajinlong
 *
 */
@Controller
public class MyQuartzJobController extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		Date date = new Date();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		
		System.out.println("============="+ dateFormat.format(date) + "统计开始=============");
		
	}

}
