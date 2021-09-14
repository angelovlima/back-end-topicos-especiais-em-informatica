package com.fatec.backendtopicosespeciais.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fatec.backendtopicosespeciais.services.impl.UsuarioServiceImpl;

//Intercepta uma requisição e adiciona um usuário autenticado caso o token JWT seja válido dentra da sessão
public class JwtAuthFilter extends OncePerRequestFilter {

	private JwtService jwtService;
	private UsuarioServiceImpl usuarioService;
	
	
	
	public JwtAuthFilter(JwtService jwtService, UsuarioServiceImpl usuarioService) {
		this.jwtService = jwtService;
		this.usuarioService = usuarioService;
	}



    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        String authorization = httpServletRequest.getHeader("Authorization");

        if( authorization != null && authorization.startsWith("Bearer")){
            String token = authorization.split(" ")[1];
            boolean isValid = jwtService.tokenValido(token);

            if(isValid){
            	//Pegar dados do usuario do token
                String loginUsuario = jwtService.obterLoginUsuario(token);
                UserDetails usuario = usuarioService.loadUserByUsername(loginUsuario);
                UsernamePasswordAuthenticationToken user = new
                        UsernamePasswordAuthenticationToken(usuario,null,
                        usuario.getAuthorities());
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(user);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
	
}
