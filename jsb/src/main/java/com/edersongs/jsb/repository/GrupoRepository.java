/**
 * 
 */
package com.edersongs.jsb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edersongs.jsb.model.Grupo;

/**
 * @author Éderson Gervásio
		   edersongervasiosilva@gmail.com
		   github.com/edersongs	
 *
 */
@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

	List<Grupo> findByNome(String nome);

}
