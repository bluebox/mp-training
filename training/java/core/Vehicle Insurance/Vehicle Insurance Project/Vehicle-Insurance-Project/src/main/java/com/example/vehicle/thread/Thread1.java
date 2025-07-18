package com.example.vehicle.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.vehicle.repo.PolicyDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Thread1 extends Thread{
	@Autowired
	private final PolicyDao repo;
	private static JdbcTemplate jdbcTemplate;
	public Thread1(PolicyDao repo) {
		this.repo=repo;
	}
	public void run() {
		while(true) {
			log.info(repo.dueDate());
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		Thread1 t=new Thread1(new PolicyDao(jdbcTemplate));
		t.run();
	}
}
