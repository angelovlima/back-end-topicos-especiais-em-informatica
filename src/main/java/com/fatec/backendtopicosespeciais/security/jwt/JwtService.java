package com.fatec.backendtopicosespeciais.security.jwt;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatec.backendtopicosespeciais.domain.Usuario;
import com.fatec.backendtopicosespeciais.dto.UsuarioSemSenhaDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// Pega o usuário autenticado e gera um token utilizando suas informações (sem a senha)
// Serve para saber quem é o usuário e qual a data de autorização do token
@Service
public class JwtService {
	
	//Assinatura serve para garantir que o conteudo do token não foi alterado pelo usuário
	//@Value("${security.jwt.chave-assinatura}")
	private static String chaveAssinatura = "dHJhYmFsaG8gZmF0ZWMgdG9waWNvcyBlc3BlY2lhaXM=";
	
	//Define o tempo para que o token expire
	//Valores pegos de application.properties
	//@Value("${security.jwt.expiracao}")
	private static String expiracao = "60";
	
	public static String gerarToken(Usuario usuario) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		long expiracaoLong = Long.valueOf(expiracao);
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expiracaoLong);
		
		//Token JWT define expiração utilizando objeto do tipo Date
		Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instant);
		
		UsuarioSemSenhaDTO usuarioSemSenhaDTO = new UsuarioSemSenhaDTO(usuario);
		String usuarioJson = mapper.writeValueAsString(usuarioSemSenhaDTO);
		return Jwts
					.builder()
					.claim("userDetails", usuarioJson)
					.setIssuer("com.fatec")
					.setSubject(usuario.getNome())
					.setExpiration(data)
					.signWith( SignatureAlgorithm.HS512 , chaveAssinatura)
					.compact();
	}
	
    public static Authentication parseToken(String token) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String credentialsJson = Jwts.parser()
                .setSigningKey(chaveAssinatura)
                //Pega e abre o token, lança exceção caso esteja expirado, tenha sido alterado, etc.
                .parseClaimsJws(token)
                .getBody()
                .get("userDetails", String.class);
        System.out.println(credentialsJson);
        UsuarioSemSenhaDTO usuario = mapper.readValue(credentialsJson, UsuarioSemSenhaDTO.class);
        String[] roles = usuario.getAdmin() ?
                new String[]{"ROLE_ADMIN", "ROLE_USER"} : new String[]{"ROLE_USER"};
        UserDetails userDetails = User.builder().username(usuario.getNome()).password("secret")
        		.authorities(roles).build();
        //Objeto utilizado para fazer login no filtro
        return new UsernamePasswordAuthenticationToken(usuario.getNome(), null, userDetails.getAuthorities());
    }

}
