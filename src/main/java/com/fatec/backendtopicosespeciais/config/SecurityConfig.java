package com.fatec.backendtopicosespeciais.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fatec.backendtopicosespeciais.services.impl.UsuarioServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(usuarioServiceImpl)
			.passwordEncoder(passwordEncoder());
	}
	
    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
        	// Retirar csrf quando for utilizar o front?
        	.csrf().disable()
        	.authorizeRequests()
        		.antMatchers(HttpMethod.GET, "/eventos/**")
        			.hasAnyRole("USER", "ADMIN")
            	.antMatchers(HttpMethod.POST, "/eventos/**")
        			.hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/eventos/**")
            		.hasRole("ADMIN")
                 .antMatchers(HttpMethod.DELETE, "/eventos/**")
            		.hasRole("ADMIN")
        		.antMatchers(HttpMethod.GET, "/categorias/**")
        			.permitAll()
                .antMatchers(HttpMethod.POST, "/categorias/**")
            		.hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/categorias/**")
                	.hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/categorias/**")
                	.hasRole("ADMIN")
        		.antMatchers(HttpMethod.POST, "/usuarios/**")
        			.permitAll()
        		.anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}
