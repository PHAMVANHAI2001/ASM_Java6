package com.eshop.config;

import com.eshop.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

//	Ma hoa mat khau
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	phan quyen su dung va hinh thuc dang nhap
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable();
//		http.authorizeRequests().antMatchers("/assets/**", "/home", "/login", "/register", "/forgot-pass", "/products",
//				"/product-details/**", "/api/**").permitAll().anyRequest().authenticated();
		http.authorizeRequests().antMatchers("/assets/**", "/home", "/login", "/register", "/forgot-pass", "/products",
				"/product-details/**", "/api/**","/search/**").permitAll()
		.antMatchers("/cart/**").authenticated();
		http.formLogin().loginProcessingUrl("/login").loginPage("/login").defaultSuccessUrl("/home", false)
				.failureUrl("/login?error=true").usernameParameter("username").passwordParameter("password");
		http.rememberMe().rememberMeParameter("rememberMe");
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/home");
	}

//	Quan ly du lieu nguoi su dung
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl);
	}
}
