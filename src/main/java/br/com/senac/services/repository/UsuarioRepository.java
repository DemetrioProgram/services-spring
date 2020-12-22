package br.com.senac.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.services.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>,JpaRepository<Usuario, Long>{
	
	@Query(value = "SELECT * FROM usuario WHERE id=?1", nativeQuery = true)
	Usuario findId(Long id);
	
	@Query(value = "SELECT * FROM usuario WHERE nome LIKE ?1%", nativeQuery = true)
	List<Usuario> findByName(String name);

}
