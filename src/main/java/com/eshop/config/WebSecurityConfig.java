package com.eshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	Ma hoa mat khau
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	phan quyen su dung va hinh thuc dang nhap
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable();
		http.authorizeRequests().antMatchers("/assets/**","/home","/user/login").permitAll()
			.anyRequest().authenticated();
		http.formLogin().loginPage("/user/login")
			.loginProcessingUrl("/user/login")
			.defaultSuccessUrl("/home",false)
			.failureUrl("/login?error=true")
			.usernameParameter("username")
			.passwordParameter("password");
		http.rememberMe().rememberMeParameter("rememberMe");
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/home");
	}
	
	
//	Quan ly du lieu nguoi su dung
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("HaiPV").password(getPasswordEncoder().encode("123456")).roles("ADMIN");

	}
}
