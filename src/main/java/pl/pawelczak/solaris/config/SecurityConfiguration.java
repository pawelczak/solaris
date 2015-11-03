package pl.pawelczak.solaris.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:application-security.properties")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Resource
	private Environment environment;
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
	               .withUser(environment.getRequiredProperty("security.admin.username"))
	               .password(environment.getRequiredProperty("security.admin.password"))
	               .roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

	    http.authorizeRequests()
	    .antMatchers("/admin/login").permitAll()
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		.and()
		    .formLogin().loginPage("/admin/login").failureUrl("/admin/login?error")
		    .usernameParameter("username").passwordParameter("password")
		    .defaultSuccessUrl("/admin/")
		.and()
		    .logout().logoutSuccessUrl("/admin/login?logout")
		.and()
		    .csrf().disable(); 		
	}
}

