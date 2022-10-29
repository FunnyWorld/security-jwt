package com.jwt.security.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MemberAuthProvider implements AuthenticationProvider {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired private MemberDetailsService memberDetailsService;
	@Autowired private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String)authentication.getCredentials();
		
		MemberDetails memberDetails = (MemberDetails)memberDetailsService.loadUserByUsername(username);

		String password_in_db = memberDetails.getPassword();
		
		if (!passwordEncoder.matches(password, password_in_db)) {
			logger.info("[사용자] 아이디와 비밀번호를 확인하세요 1!");
			throw new BadCredentialsException("[사용자] 아이디와 비밀번호를 확인하세요 1!");
		}

		String role = memberDetails.getRole();
		if (role == null || !role.equals("ROLE_USER")) {
			logger.info("[사용자] 아이디와 비밀번호를 확인하세요 2!");
			throw new BadCredentialsException("[사용자] 아이디와 비밀번호를 확인하세요 2!");
		}
		
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(memberDetails.getUsername(), null, memberDetails.getAuthorities());
		
		return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
