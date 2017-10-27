/**
 * 
 */
package com.edersongs.jsb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edersongs.jsb.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Éderson Gervásio
		   edersongervasiosilva@gmail.com
		   github.com/edersongs	
 *
 */
@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired private ObjectMapper mapper;
	@Autowired private UsuarioRepository usuarioRepository;
	
	public ResponseEntity<?> realizarLogin() {
		
		return null;
	}
}
