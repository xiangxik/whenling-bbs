package com.whenling.bbs.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whenling.bbs.api.TopicService;
import com.whenling.bbs.domain.Topic;
import com.whenling.castle.repo.domain.Result;

@Controller
@RequestMapping("/topic")
public class TopicController {

	@Reference
	private TopicService topicService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createPage(Model model) {
		model.addAttribute("topic", new Topic());
		return "/topic/edit";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result save(@ModelAttribute Topic topic, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		topicService.save(topic);

		return Result.success();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editPage(@PathVariable("id") Long id, Model model) {
		Topic topic = topicService.findOne(id);
		model.addAttribute("topic", topic);
		return "/topic/edit";
	}

}
