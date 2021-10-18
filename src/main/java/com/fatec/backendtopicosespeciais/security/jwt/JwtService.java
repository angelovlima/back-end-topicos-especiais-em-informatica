package com.fatec.backendtopicosespeciais.security.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fatec.backendtopicosespeciais.domain.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// Pega o usuário autenticado e gera um token utilizando suas informações (sem a senha)
// Serve para saber quem é o usuário e qual a data de autorização do token
@Service
public class JwtService {

	//Define o tempo para que o token expire
	//Valores pegos de application.properties
	@Value("${security.jwt.expiracao}")
	private String expiracao;
	
	@Value("${security.jwt.chave-assinatura}")
	private String chaveAssinatura;
	
	public String gerarToken(Usuario usuario) {
		long expiracaoLong = Long.valueOf(expiracao);
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expiracaoLong);
		
		//Token JWT define expiração utilizando objeto do tipo Date
		Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instant);

		return Jwts
					.builder()
					.setSubject(usuario.getNome())
					.setExpiration(data)
					.signWith( SignatureAlgorithm.HS512 , chaveAssinatura)
					.compact();
	}
	
	//Claims são as informações do token
    private Claims obterClaims( String token ) throws ExpiredJwtException {
        return Jwts
        		  //parser() decodifica o token
                 .parser()
                 //Define qual foi a chave utilizada para gerar o token
                 .setSigningKey(chaveAssinatura)
                 //Passa o token para o objeto
                 .parseClaimsJws(token)
                 .getBody();
    }
    
    public boolean tokenValido( String token ){
        try{
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data =
                    dataExpiracao.toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);
        }catch (Exception e){
            return false;
        }
    }
    
    //Traz o usuário que mandou o token
    public String obterLoginUsuario(String token) throws ExpiredJwtException{
        return (String) obterClaims(token).getSubject();
    }
}
