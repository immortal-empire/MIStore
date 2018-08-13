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
    @Scheduled(cron = "0/5 * * * * ? ") // �������ִ��
    public void taskCycle() {
    	System.out.println("��ʱ��");
    	this.jdbcTemplate.execute("call ListeningTime()");
    }
}
