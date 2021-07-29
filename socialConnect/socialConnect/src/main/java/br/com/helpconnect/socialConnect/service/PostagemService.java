package br.com.helpconnect.socialConnect.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpconnect.socialConnect.model.Postagem;
import br.com.helpconnect.socialConnect.model.Usuario;
import br.com.helpconnect.socialConnect.repository.PostagemRepository;
import br.com.helpconnect.socialConnect.repository.UsuarioRepository;

@Service
public class PostagemService {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/* GERENCIAMENTO DE TABELAS ASSOCIATIVAS (MANY-TO-MANY) */
	public Postagem likePostagem(long idPostagem, long idUsuario) {
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findById(idUsuario);
		Optional<Postagem> postagemExistente = postagemRepository.findById(idPostagem);
		
		/* CASO CASO O USUARIO AINDA NAO TENHA DADO LIKE, ENTAO E COMPUTADO UM NOVO LIKE NA POSTAGEM */
		if(!(postagemExistente.get().getLikePostagem().contains(usuarioExistente.get()))) {
			
			if(usuarioExistente.isPresent() && postagemExistente.isPresent()) {
				postagemExistente.get().getLikePostagem().add(usuarioExistente.get());
				
				postagemRepository.save(postagemExistente.get());
				
				return postagemRepository.save(postagemExistente.get());
				
			}
			
		}else {
			/* CASO CONTRARIO O LIKE DO USUARIO E REMOVIDO DA POSTAGEM */
			if(usuarioExistente.isPresent() && postagemExistente.isPresent()) {
				postagemExistente.get().getLikePostagem().remove(usuarioExistente.get());
				
				postagemRepository.save(postagemExistente.get());
				
				return postagemRepository.save(postagemExistente.get());
				
			}
			
		}

		return null;
	}

}
