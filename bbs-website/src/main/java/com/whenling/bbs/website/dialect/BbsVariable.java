package com.whenling.bbs.website.dialect;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.base.MoreObjects;
import com.whenling.bbs.api.TabService;
import com.whenling.bbs.api.TopicService;
import com.whenling.bbs.domain.Tab;
import com.whenling.bbs.domain.Topic;

public class BbsVariable {
	
	@Reference
	private TabService tabService;
	
	@Reference
	private TopicService topicService;

	public List<Tab> getTabs() {
		return tabService.findAll();
	}
	
	public Page<Topic> findPage(Long tabId, Integer page, Integer size) {
		PageRequest pageRequest = new PageRequest(MoreObjects.firstNonNull(page, 0), MoreObjects.firstNonNull(size, 20));
		return topicService.findByTab(tabId, pageRequest);
	}
}
