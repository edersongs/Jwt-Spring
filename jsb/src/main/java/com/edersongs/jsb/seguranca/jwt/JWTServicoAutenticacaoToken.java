/**
 * 
 */
package com.edersongs.jsb.seguranca.jwt;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Éderson Gervásio
		   edersongervasiosilva@gmail.com
		   github.com/edersongs	
 *
 *
 *		Classe responsável pela geração e validação dos Tokens
 */
public class JWTServicoAutenticacaoToken {
	
	static final long TEMPO_EXPIRACAO = 30000; // 30 segundos
	static final String PALAVRA_SECRETA = "GervasioIdToken";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
	static void gerarTokenAutenticacao(HttpServletResponse response, String nomeUsuario) {
		
		String jwt = Jwts.builder()
						.setSubject(nomeUsuario)
						.setExpiration(new Date (System.currentTimeMillis() + TEMPO_EXPIRACAO))
						.signWith(SignatureAlgorithm.HS512, PALAVRA_SECRETA)
						.compact();
		
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);

	}

	static Authentication validarTokenAutenticacao(HttpServletRequest request) {
		
		String token = request.getHeader(HEADER_STRING);
		
		if (token != null) {

			String user = Jwts.parser()
							.setSigningKey(PALAVRA_SECRETA)
							.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
							.getBody()
							.getSubject();
			
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
			}
		}
		
		return null;
	}
}
