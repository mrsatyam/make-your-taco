package com.taco.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import com.taco.service.UserRepositoryUserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;

	@Autowired
	UserRepositoryUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * //This is in memory authentication where the users are provided in the code
		 * and to add new user codebase has to be changed. auth
		 * .inMemoryAuthentication() .withUser("buzz") .password("infinity")
		 * .authorities("ROLE_USER") .and() .withUser("woody") .password("bullseye")
		 * .authorities("ROLE_USER");
		 */

		/*
		 * auth .ldapAuthentication() .userSearchBase("ou=people") //Authentication with
		 * ldap based user store .userSearchFilter("(uid={0})")
		 * .groupSearchBase("ou=groups") .groupSearchFilter("member={0}")
		 * .passwordCompare() .passwordEncoder(new BCryptPasswordEncoder())
		 * .passwordAttribute("passcode") .and()
		 * .contextSource()//.url("ldap://tacocloud.com:389/dc=tacocloud,dc=com");
		 * .root("dc=tacocloud,dc=com");
		 */

		/*
		 * auth // Authentication with relational DB .jdbcAuthentication()
		 * .dataSource(dataSource).authoritiesByUsernameQuery(null).usersByUsernameQuery
		 * (null).passwordEncoder(null);
		 */

		// Authentication with custom UserDetailsService implementation using Spring JPA
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

    @Bean
    PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    	.csrf().disable()
    	.authorizeRequests()
    	.antMatchers("/design", "/orders")
    	.hasAuthority("ROLE_USER")
    	.antMatchers("/", "/**")
    	.access("permitAll")
    	.and()
    	.formLogin()
    	.loginPage("/login")
    	.and()
    	.logout()
    	.logoutSuccessUrl("/"); //.loginProcessingUrl("/authenticate").usernameParameter("user")
    	//.passwordParameter("pwd").defaultSuccessUrl("/design", true)

    	 
    	
		/*
		 * http .authorizeRequests() .antMatchers("/design", "/orders")
		 * .access("hasRole('ROLE_USER') && " +
		 * "T(java.util.Calendar).getInstance().get("+
		 * "T(java.util.Calendar).DAY_OF_WEEK) == " + "T(java.util.Calendar).TUESDAY")
		 * .antMatchers(“/”, "/**").access("permitAll") ;
		 */

    }
}
