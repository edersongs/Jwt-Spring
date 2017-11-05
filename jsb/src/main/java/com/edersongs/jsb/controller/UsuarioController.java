/**
 * 
 */
package com.edersongs.jsb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edersongs.jsb.model.Usuario;
import com.edersongs.jsb.repository.UsuarioRepository;

/**
 * @author Éderson Gervásio
		   edersongervasiosilva@gmail.com
		   github.com/edersongs	
 *
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired private UsuarioRepository usuarioRepository;
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_CADASTRAR_USUARIO')")
	public ResponseEntity<?> listarUsuarios() {
		
		return new ResponseEntity<List<Usuario>>(usuarioRepository.findAll(), HttpStatus.OK);
	}
}
