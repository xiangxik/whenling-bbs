package com.whenling.bbs.website.security;

import org.springframework.security.core.Authentication;
import org.thymeleaf.extras.springsecurity4.auth.AuthUtils;

import com.whenling.castle.security.CustomUserDetails;

public class AuthVariable {

	private static final AuthVariable INSTANCE = new AuthVariable();

	private AuthVariable() {
	}

	public Object getCurrentUser() {
		Authentication authentication = AuthUtils.getAuthenticationObject();
		if (authentication == null) {
			return null;
		}

		Object principal = authentication.getPrincipal();
		if (principal instanceof CustomUserDetails) {
			CustomUserDetails<?, ?> userDetails = (CustomUserDetails<?, ?>) principal;
			return userDetails.getCustomUser();
		}
		return null;
	}

	public static AuthVariable getInstance() {
		return INSTANCE;
	}

}
