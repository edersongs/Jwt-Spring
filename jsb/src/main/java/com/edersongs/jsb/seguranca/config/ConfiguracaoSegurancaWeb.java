/**
 * 
 */
package com.edersongs.jsb.seguranca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.edersongs.jsb.seguranca.UsuarioDetalheService;

/**
 * @author Éderson Gervásio
		   edersongervasiosilva@gmail.com
		   github.com/edersongs	
 *
 *		Classe responsável por criar um Filtro Servlet "springSecurityFilterChain" responsável por toda a segurança do aplicativo
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
			.anyRequest().authenticated();
	}
}
