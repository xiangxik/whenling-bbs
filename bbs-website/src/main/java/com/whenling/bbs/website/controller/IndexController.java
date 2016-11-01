package com.whenling.bbs.website.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whenling.bbs.api.TopicService;

@Controller
@RequestMapping
public class IndexController {

	@Reference
	private TopicService topicService;
	
	@RequestMapping(value = { "", "/", "/index" }, method = RequestMethod.GET)
	public String get(Pageable pageable, Model model) {
		model.addAttribute("page", topicService.findAll(pageable));
		return "/index";
	}

}
