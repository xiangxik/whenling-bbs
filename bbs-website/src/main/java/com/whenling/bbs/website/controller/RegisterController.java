package com.whenling.bbs.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.base.Strings;
import com.whenling.castle.repo.domain.Result;
import com.whenling.main.api.UserService;
import com.whenling.main.domain.User;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Reference
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(method = RequestMethod.GET)
	public String registerPage() {
		return "/register";
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Result register(String password, @ModelAttribute User user, BindingResult bindingResult) {
		if (Strings.isNullOrEmpty(password)) {
			bindingResult.addError(new FieldError("user", "password", "密码不能为空"));
		}
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		userService.register(user, passwordEncoder.encode(password));

		return Result.success();
	}

}
