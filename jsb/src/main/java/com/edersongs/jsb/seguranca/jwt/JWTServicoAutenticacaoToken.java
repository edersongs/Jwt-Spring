/**
 * 
 */
package com.edersongs.jsb.seguranca.jwt;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.edersongs.jsb.seguranca.UsuarioAutentica;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	static final long TEMPO_EXPIRACAO = 300000; // 30 segundos
	static final String PALAVRA_SECRETA = "GervasioIdToken";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
	static void gerarTokenAutenticacao(HttpServletResponse response, UsuarioAutentica usuario) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jwt = Jwts.builder()
						.claim("usr", mapper.writeValueAsString(usuario))
						.setSubject(usuario.getUsername())
						.setExpiration(new Date (System.currentTimeMillis() + TEMPO_EXPIRACAO))
						.signWith(SignatureAlgorithm.HS512, PALAVRA_SECRETA)
						.compact();
		
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);

	}

	static UserDetails validarTokenAutenticacao(HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException {
		
		String token = request.getHeader(HEADER_STRING);
		
		if (token != null) {

			String user = Jwts.parser()
							.setSigningKey(PALAVRA_SECRETA)
							//.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
							.parseClaimsJws(token).getBody().get("usr", String.class);
							//.getBody()
							//.getSubject();
			
			if (user != null) {
				
				ObjectMapper mapper = new ObjectMapper();
				
				User usuario = mapper.readValue(user, User.class);
				
				return mapper.readValue(user, User.class);
			}
		}
		
		return null;
	}
}
