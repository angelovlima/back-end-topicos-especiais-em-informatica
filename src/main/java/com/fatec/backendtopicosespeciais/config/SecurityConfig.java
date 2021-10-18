package com.fatec.backendtopicosespeciais.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fatec.backendtopicosespeciais.security.jwt.JwtAuthFilter;
import com.fatec.backendtopicosespeciais.security.jwt.JwtService;
import com.fatec.backendtopicosespeciais.services.impl.UsuarioServiceImpl;

//extends WebSecurityConfigurerAdapter dá acesso aos dois métodos configure
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
    @Autowired
    private JwtService jwtService;
	
    // PasswordEncoder cripta e decripta a senha de um usuário 
	@Bean
	public PasswordEncoder passwordEncoder() {
		// Toda vez que o usuário passa uma senha, o BCrypt gera um hash, toda vez que ele gera um hash da mesma senha, gera um hash diferente
		return new BCryptPasswordEncoder();
	}
	
    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioServiceImpl);
    }

    // Traz os objetos que vão fazer a autenticação dos usuários e adiciona esses usuários dentro do contexto do security (Autenticação)
    // Como vai configurar a senha, de onde iremos buscar os usuários, etc...
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(usuarioServiceImpl)
			.passwordEncoder(passwordEncoder());
	}
	
    //Cuida da parte de autorização
    //Pega o usuário autenticado no método acima e verifica se tem autorização para uma página específica
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
        		.anyRequest().authenticated()
        	//.and() Retorna para o objeto base (HttpSecurity http)
        	.and()
        	//Não utilizamos sessões, cada requisição contem todos os elementos para que ela aconteça
        	//Elementos esses presentes no token
        		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        	.and()
        	//Adiciona o JwtAuthFilter para ser executado antes do outro filtro já presente no spring security
        	//Aqui é onde definimos que o filter irá interceptar a requisição e colocar o usuário dentro do contexto
        		.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
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
