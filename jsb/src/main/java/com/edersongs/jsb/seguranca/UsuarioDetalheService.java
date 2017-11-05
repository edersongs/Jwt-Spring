/**
 * 
 */
package com.edersongs.jsb.seguranca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.edersongs.jsb.model.Usuario;
import com.edersongs.jsb.repository.UsuarioRepository;

/**
 * @author Éderson Gervásio
		   edersongervasiosilva@gmail.com
		   github.com/edersongs	
 *
 */
public class UsuarioDetalheService implements UserDetailsService {

	@Autowired private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository
				.findByLogin(nomeUsuario).orElseThrow(
						() -> new UsernameNotFoundException("Nenhum Usuário encontrado!"));
		
		return new UsuarioAutentica(usuario, getGrupos(usuario));
	}
	
	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		List<String> grupos = usuarioRepository.findByNameGrupoPermissao(usuario.getLogin());
		
		grupos.forEach(s -> authorities.add(new SimpleGrantedAuthority("ROLE_" + s)));
		
		return authorities;
	}
}
