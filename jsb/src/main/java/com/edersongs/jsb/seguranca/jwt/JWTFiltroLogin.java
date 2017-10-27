/**
 * 
 */
package com.edersongs.jsb.seguranca.jwt;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Éderson Gervásio
		   edersongervasiosilva@gmail.com
		   github.com/edersongs	
 *
 */
public class JWTFiltroLogin extends AbstractAuthenticationProcessingFilter {

	protected JWTFiltroLogin(String url, AuthenticationManager authManager) {
		
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		
		JWTCredenciais credenciais = new ObjectMapper()
											.readValue(request.getInputStream(), JWTCredenciais.class);
		
		return getAuthenticationManager().authenticate(
				
				new UsernamePasswordAuthenticationToken(
						credenciais.getNomeUsuario(), 
						credenciais.getSenha(), 
						Collections.emptyList())
				);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		
		JWTServicoAutenticacaoToken
			.gerarTokenAutenticacao(response, authResult.getName());
	}
}
