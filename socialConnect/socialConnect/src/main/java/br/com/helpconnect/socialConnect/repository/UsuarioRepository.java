package br.com.helpconnect.socialConnect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.helpconnect.socialConnect.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByUsername(String username);
	
	//public List<Usuario> findAllByUsernameContainingIgnoreCase(String username);

}
