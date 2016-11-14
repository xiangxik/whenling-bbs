package com.whenling.bbs.website.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.whenling.bbs.api.TabService;
import com.whenling.bbs.api.TopicService;

@Controller
@RequestMapping
public class IndexController {

	@Reference
	private TopicService topicService;
	
	@Reference
	private TabService tabService;

	@RequestMapping(value = { "", "/", "/index" }, method = RequestMethod.GET)
	public String get(String tab, Pageable pageable, Model model) {
		if(Strings.isNullOrEmpty(tab)) {
			model.addAttribute("page", topicService.findAll(pageable));
		} else if(Objects.equal(tab, "good")) {
			model.addAttribute("page", topicService.findGood(pageable));
		} else {
			model.addAttribute("page", topicService.findByTab(tab, pageable));
			model.addAttribute("chooseTab", tabService.findByCode(tab));
		}
		return "/index";
	}

}
