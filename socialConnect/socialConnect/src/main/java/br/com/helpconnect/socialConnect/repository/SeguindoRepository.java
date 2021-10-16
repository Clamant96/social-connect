package br.com.helpconnect.socialConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.helpconnect.socialConnect.model.Seguindo;

@Repository
public interface SeguindoRepository extends JpaRepository<Seguindo, Long>{

}
