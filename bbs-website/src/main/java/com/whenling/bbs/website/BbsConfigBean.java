package com.whenling.bbs.website;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class BbsConfigBean {

	@Autowired
	private SpringTemplateEngine templateEngine;
	
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}
	
	@PostConstruct
	public void init() {
		templateEngine.addDialect(layoutDialect());
	}
	
}
