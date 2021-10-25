package com.fatec.backendtopicosespeciais.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
	
	private String nome;
	private String senha;
	private String token;
	

}