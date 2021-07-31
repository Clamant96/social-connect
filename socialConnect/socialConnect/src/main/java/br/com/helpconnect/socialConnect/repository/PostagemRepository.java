package br.com.helpconnect.socialConnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.helpconnect.socialConnect.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	//@Query(value="select * from blog_pessoal.tb_produto where ativo = :ativo", nativeQuery = true)
	@Query(value="SELECT * FROM social_connect.postagem WHERE usuario_id = :id", nativeQuery = true)
	public List<Postagem> findAllByUsuario(@Param("id") long id);
	
}
