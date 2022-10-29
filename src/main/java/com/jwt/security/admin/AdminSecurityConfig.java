package com.jwt.security.admin;

import org.springframework.core.annotation.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@Order(2)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired private AdminAuthProvider adminAuthProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(adminAuthProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
	
	
		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/img/**").permitAll();

		http.antMatcher("/admin/**")
				.authorizeRequests()
				// .antMatchers("/user/**").hasRole("USER")
				.antMatchers("/admin/join", "/admin/index", "/admin/find", "/admin/index2", "/admin/find2").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated();

		http.formLogin()
				.loginPage("/admin/login")
				.loginProcessingUrl("/admin/login_proc")
				.defaultSuccessUrl("/admin/home")
				.failureUrl("/admin/login/error")
				.permitAll()
			.and()
				.logout()
				.logoutUrl("/admin/logout")
				.logoutSuccessUrl("/admin/login")
			.and()
				.exceptionHandling().accessDeniedPage("/errors/403");
	}

}
