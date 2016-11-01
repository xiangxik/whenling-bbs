package com.whenling.bbs.website.security;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whenling.castle.core.helper.Patterns;
import com.whenling.castle.security.CustomUserDetails;
import com.whenling.main.api.UserService;
import com.whenling.main.domain.User;
import com.whenling.plugin.oauth.api.OauthService;

@Component
public class RemoteUserDetailsService implements UserDetailsService {

	@Reference
	private UserService userService;

	@Reference
	private OauthService oauthService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;

		if (username.startsWith(OauthAuthenticationFilter.PREFIX)) {
			String pluginId = StringUtils.substringBetween(username, OauthAuthenticationFilter.PREFIX, ",");
			String code = StringUtils.substringAfterLast(username, ",");

			user = oauthService.auth(pluginId, code);
		} else {
			if (Patterns.isMobile(username)) {
				user = userService.findByMobile(username);
			} else if (Patterns.isEmail(username)) {
				user = userService.findByEmail(username);
			} else if (Patterns.isUsername(username)) {
				user = userService.findByUsername(username);
			}
		}
		
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user " + username);
		}

		return new CurrentUserDetails(user.getId(), username,
				username.startsWith(OauthAuthenticationFilter.PREFIX) ? passwordEncoder.encode(OauthAuthenticationFilter.DEFAULT_PASSWORD) : userService.getPassword(user), user.isEnabled(),
				!user.isAccountExpired(), !user.isCredentialsExpired(), !user.isLocked(), AuthorityUtils.createAuthorityList("ROLE_USER"));
	}

	public class CurrentUserDetails extends CustomUserDetails<Long, User> {

		private static final long serialVersionUID = -5628398667154378890L;

		public CurrentUserDetails(Long id, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
				Collection<? extends GrantedAuthority> authorities) {
			super(id, username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

		}

		@Override
		public User getCustomUser() {
			return userService.findOne(getId());
		}

	}

}
