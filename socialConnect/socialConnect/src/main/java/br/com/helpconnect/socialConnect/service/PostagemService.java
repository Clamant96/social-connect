package br.com.helpconnect.socialConnect.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpconnect.socialConnect.model.Postagem;
import br.com.helpconnect.socialConnect.repository.PostagemRepository;

@Service
public class PostagemService {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	/* LIKE */
	/* ADICIONAR LIKE */
	public Postagem adicionarLike(long idProduto) {
		Optional<Postagem> postagemExistente = postagemRepository.findById(idProduto);
		
		if(postagemExistente.isPresent()) {
			postagemExistente.get().setLikes(postagemExistente.get().getLikes() + 1);
			
			postagemRepository.save(postagemExistente.get());
		
		}
		
		return postagemRepository.save(postagemExistente.get());
	}
	
	/* RETIRAR LIKE */
	public Postagem retirarLike(long idProduto) {
		Optional<Postagem> postagemExistente = postagemRepository.findById(idProduto);
		
		if(postagemExistente.isPresent() && postagemExistente.get().getLikes() > 0) {
			postagemExistente.get().setLikes(postagemExistente.get().getLikes() - 1);
			
			postagemRepository.save(postagemExistente.get());
			
		}
		
		return postagemRepository.save(postagemExistente.get());
	}

}
