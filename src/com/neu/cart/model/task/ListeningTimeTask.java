package com.neu.cart.model.task;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ListeningTimeTask {
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {   
		   this.jdbcTemplate = jdbcTemplate;   
	} 
    @Scheduled(cron = "0/5 * * * * ? ") // 间隔分钟执行
    public void taskCycle() {
    	System.out.println("定时器");
    	this.jdbcTemplate.execute("call ListeningTime()");
    }
}
