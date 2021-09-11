package com.fatec.backendtopicosespeciais.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.fatec.backendtopicosespeciais.domain.Usuario;
import com.fatec.backendtopicosespeciais.dto.CredencialDTO;
import com.fatec.backendtopicosespeciais.dto.TokenDTO;
import com.fatec.backendtopicosespeciais.dto.UsuarioDTO;
import com.fatec.backendtopicosespeciais.exception.SenhaInvalidaException;
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
    private JwtService jwtService;
	
	@ApiOperation(value="Realiza o cadastro de um usuário")
	@JsonView(Views.Public.class)
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
		usuarioServiceImpl.inserir(usuarioDTO);
		
		return ResponseEntity.ok(usuarioDTO);
	}
	
    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredencialDTO credenciais){
        try{
            Usuario usuario = Usuario.builder()
                    .nome(credenciais.getLogin())
                    .senha(credenciais.getSenha()).build();
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getNome(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
