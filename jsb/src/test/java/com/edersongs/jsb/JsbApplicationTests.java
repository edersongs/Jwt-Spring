package com.edersongs.jsb;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edersongs.jsb.model.Grupo;
import com.edersongs.jsb.repository.GrupoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsbApplicationTests {

	@Autowired private GrupoRepository grupoRepository;
	
	@Test
	public void contextLoads() {
		
	  System.out.println("Tamanho---------------" + grupoRepository.findByNome("ROLE_ADMINISTRADOR").size());
	}

}
