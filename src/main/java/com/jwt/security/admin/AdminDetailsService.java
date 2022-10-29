package com.jwt.security.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.member.entity.Member;
import com.jwt.member.repository.MemberRepository;

@Service
public class AdminDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByUsername(username);
		if (member != null) {
			return (new AdminDetails(member));
		}
		
		logger.debug("AdminDetailsService loadUserByUsername: no username([{}]) found....................", username);
		throw new UsernameNotFoundException("no username([" + username + "]) found");
	}

}
