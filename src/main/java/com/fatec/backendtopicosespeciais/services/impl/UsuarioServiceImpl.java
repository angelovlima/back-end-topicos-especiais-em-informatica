package com.fatec.backendtopicosespeciais.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fatec.backendtopicosespeciais.domain.Usuario;
import com.fatec.backendtopicosespeciais.dto.UsuarioDTO;
import com.fatec.backendtopicosespeciais.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
    @Transactional
    public Usuario inserir(UsuarioDTO usuarioDTO){
    	Usuario usuario = usuarioDTO.toEntityInsert();
    	usuario.setSenha(criptografarSenha(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByNome(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
		
        String[] roles = usuario.getAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};
		
		return User
				.builder()
				.username(usuario.getNome())
				.password(usuario.getSenha())
				.roles(roles)
				.build();
	}
	
	public String criptografarSenha (String senha){
		String senhaCriptografada = passwordEncoder.encode(senha);
		return senhaCriptografada;
	}
}
