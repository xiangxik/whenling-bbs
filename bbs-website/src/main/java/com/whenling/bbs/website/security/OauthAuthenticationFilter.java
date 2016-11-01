package com.whenling.bbs.website.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class OauthAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public static final String PREFIX = "oauth:";

	public static final String DEFAULT_PASSWORD = "asdqwe123";

	protected OauthAuthenticationFilter() {
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/oauth/**"));
		setPostOnly(false);
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String[] segments = uri.split("/");
		String code = request.getParameter("code");
		return PREFIX + segments[segments.length - 1] + "," + code;
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		return DEFAULT_PASSWORD;
	}

}
