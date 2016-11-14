package com.whenling.bbs.website;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import com.whenling.bbs.website.dialect.BbsVariable;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class BbsConfigBean {

	@Autowired
	private ThymeleafViewResolver thymeleafViewResolver;

	@Autowired
	private SpringTemplateEngine templateEngine;
	
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}
	
	@Bean
	public BbsVariable bbsVariable() {
		return new BbsVariable();
	}
	
	@PostConstruct
	public void init() {
		templateEngine.addDialect(layoutDialect());
		thymeleafViewResolver.addStaticVariable("bbs", bbsVariable());
	}
	
}
