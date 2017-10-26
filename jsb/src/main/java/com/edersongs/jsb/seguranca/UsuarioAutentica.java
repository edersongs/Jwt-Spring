/**
 * 
 */
package com.edersongs.jsb.seguranca;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.edersongs.jsb.model.Usuario;

/**
 * @author Éderson Gervásio
		   edersongervasiosilva@gmail.com
		   github.com/edersongs	
 *
 */
public class UsuarioAutentica extends User {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	public UsuarioAutentica(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		
		super(usuario.getLogin(), usuario.getSenha(), authorities);
	}
	
	public Usuario getUsuario() {
		
		return usuario;
	}
}
