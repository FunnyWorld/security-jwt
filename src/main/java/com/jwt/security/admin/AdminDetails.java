package com.jwt.security.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jwt.member.entity.Member;

public class AdminDetails implements UserDetails {
	private static final long serialVersionUID = -2044592452610558825L;

	private final Member member;
	
	public AdminDetails(Member member) {
		this.member = member;
	}
	
	public Long getId() {
		return member.getId();
	}

	public String getEmail() {
		return member.getEmail();
	}
	
	@Override
	public String getUsername() {
		return member.getUsername();
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(member.getRole()));
		return authorities;
	}

	public String getRole() {
		return member.getRole();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
