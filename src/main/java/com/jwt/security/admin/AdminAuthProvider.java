package com.jwt.security.admin;

import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AdminAuthProvider implements AuthenticationProvider {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired private PasswordEncoder passwordEncoder;
	@Autowired private AdminDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
		String password = (String)authentication.getCredentials();	// 사용자가 입력한 password
		
		AdminDetails adminDetails = (AdminDetails)userDetailsService.loadUserByUsername(username);

		String password_in_db = adminDetails.getPassword();
		
		if (!passwordEncoder.matches(password, password_in_db)) {
			logger.info("[관리자] 아이디와 비밀번호를 확인하세요 1!");
			throw new BadCredentialsException("[관리자] 아이디와 비밀번호를 확인하세요 1!");
		}

		String role = adminDetails.getRole();
		if (role == null || !role.equals("ROLE_ADMIN")) {
			logger.info("[관리자] 아이디와 비밀번호를 확인하세요 2!");
			throw new BadCredentialsException("[관리자] 아이디와 비밀번호를 확인하세요 2!");
		}
		
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(adminDetails.getUsername(), null, adminDetails.getAuthorities());
		
		return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
