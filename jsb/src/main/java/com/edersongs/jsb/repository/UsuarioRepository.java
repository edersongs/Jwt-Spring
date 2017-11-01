/**
 * 
 */
package com.edersongs.jsb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edersongs.jsb.model.Usuario;

/**
 * @author Éderson Gervásio
		   edersongervasiosilva@gmail.com
		   github.com/edersongs	
 *
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByLogin(String login);

	@Query("select distinct per.nome from Usuario usr inner join usr.grupos grp inner join grp.permissoes per where usr.login = :login")
	List<String> findByNameGrupoPermissao(@Param("login") String login);
}
