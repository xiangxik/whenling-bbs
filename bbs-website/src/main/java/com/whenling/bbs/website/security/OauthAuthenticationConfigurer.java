package com.whenling.bbs.website.security;

import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class OauthAuthenticationConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractAuthenticationFilterConfigurer<H, OauthAuthenticationConfigurer<H>, OauthAuthenticationFilter> {

	protected OauthAuthenticationConfigurer() {
		super(new OauthAuthenticationFilter(), null);
	}

	@Override
	protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
		return new AntPathRequestMatcher("/oauth/**");
	}

}
