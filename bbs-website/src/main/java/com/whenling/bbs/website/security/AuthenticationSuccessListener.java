package com.whenling.bbs.website.security;

import java.util.Date;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whenling.castle.security.CustomUserDetails;
import com.whenling.main.api.UserService;
import com.whenling.main.domain.User;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

	@Reference
	private UserService userService;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		Authentication authentication = event.getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof CustomUserDetails) {
			@SuppressWarnings("unchecked")
			User user = ((CustomUserDetails<?, User>) principal).getCustomUser();
			user.setLastLoginDate(new Date());

			WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
			user.setLastLoginIp(details.getRemoteAddress());
			userService.update(user);
		}

	}

}
