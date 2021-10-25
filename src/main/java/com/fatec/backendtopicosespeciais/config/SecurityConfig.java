package com.fatec.backendtopicosespeciais.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fatec.backendtopicosespeciais.security.jwt.JwtAuthFilter;
import com.fatec.backendtopicosespeciais.security.jwt.JwtService;
import com.fatec.backendtopicosespeciais.security.jwt.RestAuthenticationEntryPoint;
import com.fatec.backendtopicosespeciais.services.impl.UsuarioServiceImpl;

//extends WebSecurityConfigurerAdapter dá acesso aos dois métodos configure
@EnableWebSecurity
//Habilita segurança por anotação
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
    @Autowired
    private JwtService jwtService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioServiceImpl);
	}
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.addFilterBefore(new JwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
					.exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint())
				.and()
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
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
