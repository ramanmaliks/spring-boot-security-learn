package com.learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
		// this will bypass authonication
			//.antMatchers("/home","/login","/register").permitAll()
			// this will inclued all the pages whichever comes in public
			//.antMatchers("/public/**").permitAll()
			// this will inclued all the pages whichever comes in public and access type method HTTPMethod.Get
			// .antMatchers(HTTP.Get,"/public/**").permitAll()
			// giving access normal user -> /public , admin ->/users
			.antMatchers("/public/**").hasRole("NORMAL")
			.antMatchers("/users/**").hasRole("ADMIN")
			.antMatchers("/signin").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			//.httpBasic()
			.formLogin()
			.loginPage("/signin")
			.loginProcessingUrl("/dologin")
			.defaultSuccessUrl("/users/");
			
		
	}

	// inMemoryAuthentication is used to keep usrn and ps in memory not from db
	//passwordEncoder encrypt pass for security reason
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("john").password(this.passwordEncoder().encode("raman")).roles("NORMAL");
		auth.inMemoryAuthentication().withUser("raman").password(this.passwordEncoder().encode("raman")).roles("ADMIN");
	}
	//Bean is used here which will help to inject in other classes also, using Autowired
	@Bean
	public PasswordEncoder passwordEncoder() {
		//return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder(10);
	}

}
