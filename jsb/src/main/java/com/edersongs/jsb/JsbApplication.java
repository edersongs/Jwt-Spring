package com.edersongs.jsb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.edersongs.jsb.model.Usuario;
import com.edersongs.jsb.repository.UsuarioRepository;

/**
 * 
 * @author Éderson Gervásio
		   edersongervasiosilva@gmail.com
		   github.com/edersongs	
 *
 */
@SpringBootApplication
public class JsbApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsbApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner iniciar(UsuarioRepository usuarioRepository) {
		
		return (args) -> {
			
			usuarioRepository.save(new Usuario("Éderson Gervásio Silva", "edersongs", new BCryptPasswordEncoder().encode("root")));
			usuarioRepository.save(new Usuario("Usuário de Teste", "usuariotst", new BCryptPasswordEncoder().encode("teste")));
		};
	}
}
