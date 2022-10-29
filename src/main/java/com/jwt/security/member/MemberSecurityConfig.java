package com.jwt.security.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class MemberSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired private MemberAuthProvider memberAuthProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(memberAuthProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();

		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/img/**").permitAll();
		
		http.antMatcher("/user/**")
				.authorizeRequests()
				.antMatchers("/user/**").hasRole("USER")
				.antMatchers("/newuser").permitAll()
				.anyRequest().authenticated();

		http.formLogin()
				.loginPage("/user/login")
				.loginProcessingUrl("/user/login_proc")
				.defaultSuccessUrl("/user/home")
				.failureUrl("/user/login/error")
				.permitAll()
			.and()
				.logout()
				.logoutUrl("/user/logout")
				.logoutSuccessUrl("/user/login")
			.and()
				.exceptionHandling().accessDeniedPage("/errors/403");

	}

}
