package br.com.helpconnect.socialConnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.helpconnect.socialConnect.model.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
	
	@Query(value="SELECT * FROM social_connect.mensagem AS m INNER JOIN social_connect.usuario AS u ON m.username = u.username", nativeQuery = true)
	public List<Mensagem> findAllByUsername(@Param("id") String username);
	
}
