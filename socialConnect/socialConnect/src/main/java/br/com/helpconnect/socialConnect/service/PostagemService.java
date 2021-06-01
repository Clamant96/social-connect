package br.com.helpconnect.socialConnect.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpconnect.socialConnect.model.Mensagem;
import br.com.helpconnect.socialConnect.model.Postagem;
import br.com.helpconnect.socialConnect.repository.MensagemRepository;
import br.com.helpconnect.socialConnect.repository.PostagemRepository;

@Service
public class PostagemService {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private MensagemRepository mensagemRepository;
	
	/*public Postagem inserirMensagem(long idPostagem, Mensagem mensagem) {
		
		Optional<Postagem> postagemExistente = postagemRepository.findById(idPostagem);
		
		Mensagem msg = new Mensagem();
		
		msg.setId(mensagem.getId());
		msg.setDescricao(mensagem.getDescricao());
		msg.setPostagem(postagemExistente.get());
		
		mensagemRepository.save(msg);
		
		if(postagemExistente.isPresent()) {
			postagemExistente.get().getMensagens().add(msg);
			
		}
		
		postagemRepository.save(postagemExistente.get());
		
		return postagemRepository.save(postagemExistente.get());
	}*/

}
