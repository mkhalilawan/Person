package com.pf.sa.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {


	
	private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	public Authentication authenticate(Authentication authentication) {

		
	
		return null;
		

	}

	private List<GrantedAuthority> buildUserAuthority(List<String> userRoles) {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>" + Arrays.toString(userRoles.toArray())
				+ "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		List<GrantedAuthority> setAuths = new ArrayList<GrantedAuthority>();
		// Build user's authorities
		for (String userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority("ROLE_" + userRole));
		}
		return setAuths;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
