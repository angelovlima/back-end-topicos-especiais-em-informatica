package com.fatec.backendtopicosespeciais.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fatec.backendtopicosespeciais.domain.Usuario;
import com.fatec.backendtopicosespeciais.dto.LoginDTO;
import com.fatec.backendtopicosespeciais.dto.UsuarioDTO;
import com.fatec.backendtopicosespeciais.security.jwt.JwtService;
import com.fatec.backendtopicosespeciais.services.impl.UsuarioServiceImpl;

import io.swagger.annotations.ApiOperation;

@CrossOrigin()
@RestController
@RequestMapping("usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
    @Autowired
    private AuthenticationManager authManeger;
	
	@ApiOperation(value="Realiza o cadastro de um usuário")
	@JsonView(Views.Public.class)
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
		usuarioServiceImpl.inserir(usuarioDTO);
		return ResponseEntity.ok(usuarioDTO);
	}
	
    @PostMapping("/auth")
    public LoginDTO autenticar(@RequestBody Usuario usuario) throws JsonProcessingException {
    	Authentication auth = new UsernamePasswordAuthenticationToken(usuario.getNome(), usuario.getSenha());
    	//Se nome ou senha estiverem errados, dá exception aqui, não seguindo a diante
    	//Utiliza a classe UsuarioServiceImpl
    	auth = authManeger.authenticate(auth);
    	usuario.setAdmin(usuarioServiceImpl.verificarAutoridadePorNome(usuario.getNome()));
    	LoginDTO loginDTO = new LoginDTO(usuario.getNome(), null, 
    			JwtService.gerarToken(usuario));
    	return loginDTO;
    }

}
