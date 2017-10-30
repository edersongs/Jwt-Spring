/**
 * 
 */
package com.edersongs.jsb.seguranca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.edersongs.jsb.seguranca.UsuarioDetalheService;
import com.edersongs.jsb.seguranca.jwt.JWTFiltroAutenticacao;
import com.edersongs.jsb.seguranca.jwt.JWTFiltroLogin;

/**
 * @author Éderson Gervásio
		   edersongervasiosilva@gmail.com
		   github.com/edersongs	
 *
 *		Classe responsável por criar um Filtro Servlet "springSecurityFilterChain" responsável por toda a segurança do aplicativo.
 *		Nele ficou definido os filtros de login e autenticação com JWT
 */
@Configuration
@EnableWebSecurity
public class ConfiguracaoSegurancaWeb extends WebSecurityConfigurerAdapter {
	
	@Bean
	public UsuarioDetalheService userDetailsService() {
		
		return new UsuarioDetalheService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		
	    return new HttpSessionEventPublisher();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			.anyRequest().authenticated()
		.and()
			.addFilterBefore(new JWTFiltroLogin("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new JWTFiltroAutenticacao(), UsernamePasswordAuthenticationFilter.class);
	}
}
