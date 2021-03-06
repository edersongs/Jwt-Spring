/**
 * 
 */
package com.edersongs.jsb.seguranca.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 * @author Éderson Gervásio
		   edersongervasiosilva@gmail.com
		   github.com/edersongs	
 *
 */
public class JWTFiltroAutenticacao extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) 
			throws IOException, ServletException {
		
		Authentication authentication = JWTServicoAutenticacaoToken
											.validarTokenAutenticacao((HttpServletRequest) request);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		filter.doFilter(request, response);
	}
}
