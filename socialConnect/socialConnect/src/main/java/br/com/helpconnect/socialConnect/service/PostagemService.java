package br.com.helpconnect.socialConnect.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpconnect.socialConnect.model.Postagem;
import br.com.helpconnect.socialConnect.model.Seguindo;
import br.com.helpconnect.socialConnect.model.Story;
import br.com.helpconnect.socialConnect.model.Usuario;
import br.com.helpconnect.socialConnect.repository.PostagemRepository;
import br.com.helpconnect.socialConnect.repository.SeguindoRepository;
import br.com.helpconnect.socialConnect.repository.StoryRepository;
import br.com.helpconnect.socialConnect.repository.UsuarioRepository;

@Service
public class PostagemService {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SeguindoRepository seguindoRepository;

	@Autowired
	private StoryRepository storyRepository;
	
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
	
	/* GERENCIAMENTO DE TABELAS ASSOCIATIVAS (MANY-TO-MANY) */
	public Usuario seguirUsuario(long idSeguindo, long idUsuario) {
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findById(idUsuario);
		Optional<Seguindo> seguindoExistente = seguindoRepository.findById(idSeguindo);
		
		// CASO CASO O USUARIO AINDA NAO TENHA DADO LIKE, ENTAO E COMPUTADO UM NOVO LIKE NA POSTAGEM 
		if(!(seguindoExistente.get().getListaDeSeguindo().contains(usuarioExistente.get())) && usuarioExistente.get().getId() != seguindoExistente.get().getId()) {
			
			if(usuarioExistente.isPresent() && seguindoExistente.isPresent()) {
				usuarioExistente.get().getListaSeguindo().add(seguindoExistente.get());
				
				usuarioRepository.save(usuarioExistente.get());
				
				return usuarioRepository.save(usuarioExistente.get());
				
			}
			
		} else if(usuarioExistente.get().getId() == seguindoExistente.get().getId()) {
			
			System.out.println("Você não pode se auto seguir!!");
			
			return null;
			
		} else {
			// CASO CONTRARIO O LIKE DO USUARIO E REMOVIDO DA POSTAGEM 
			if(usuarioExistente.isPresent() && seguindoExistente.isPresent()) {
				usuarioExistente.get().getListaSeguindo().remove(seguindoExistente.get());
				
				usuarioRepository.save(usuarioExistente.get());
				
				return usuarioRepository.save(usuarioExistente.get());
				
			}
			
		}

		return null;
	}

	public List<Postagem> postagensSeguidores(long idUsuario) {

		List<Postagem> listaPostagens = new ArrayList<>();
		Optional<Seguindo> usuarioExistente = seguindoRepository.findById(idUsuario);

		// NAVEGA NO ARRAY DE STORYs
		for (Postagem postagem : postagemRepository.findAll()) {

			// NAVEGA NO ARRAY DE SEGUIDORES
			for (Usuario usuario : postagem.getUsuario().getSeguindo().getListaDeSeguindo()) {
				
				// INSERE NO ARRAY CASO O USUARIO SIGA A PESSOA
				if(usuario.getId() == usuarioExistente.get().getId()) {
					listaPostagens.add(postagem);

				}
				
			}

			// INSERE NO ARRAY CASO A POSTAGEM SEJA DO PROPRIO USUARIO
			if(postagem.getUsuario().getId() == usuarioExistente.get().getId()) {
				listaPostagens.add(postagem);

			}
			
		}
		
		return listaPostagens;
	}

	public List<Story> storysSeguidores(long idUsuario) {

		List<Story> listaStorys = new ArrayList<>();
		Optional<Seguindo> usuarioExistente = seguindoRepository.findById(idUsuario);

		// NAVEGA NO ARRAY DE STORYs
		for (Story postagem : storyRepository.findAll()) {

			// NAVEGA NO ARRAY DE SEGUIDORES
			for (Usuario usuario : postagem.getUsuario().getSeguindo().getListaDeSeguindo()) {
				
				// INSERE NO ARRAY CASO O USUARIO SIGA A PESSOA
				if(usuario.getId() == usuarioExistente.get().getId()) {
					listaStorys.add(postagem);

				}
				
			}

			// INSERE NO ARRAY CASO A POSTAGEM SEJA DO PROPRIO USUARIO
			if(postagem.getUsuario().getId() == usuarioExistente.get().getId()) {
				listaStorys.add(postagem);
				
			}
			
		}
		
		return listaStorys;
	}

	public List<Usuario> usuariosParaSeguir(long idUsuario) {

		Optional<Usuario> usuarioExistente = usuarioRepository.findById(idUsuario);

		// CASO A LISTA DO USUARIO ESTEJA VAZIA, E RETORNAR TODOS OS USUARIOS 
		if(usuarioExistente.get().getListaSeguindo().size() == 0) {

			List<Usuario> memoriaUsuarios = new ArrayList<>();
			
			// REMOVE O USUARIO LOGADO DA RELACAO
			for (Usuario usuario : usuarioRepository.findAll()) {

				if(usuario.getId() != usuarioExistente.get().getId()) {
					memoriaUsuarios.add(usuario);

				}

			}

			return memoriaUsuarios;
		}

		
		// INSTANCIA UMA NOVA LISTA DE USUARIOS
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		
		// NAVEGA NO ARRAY DE LISTA DE USUARIO SEGUIDOS PELO USUARIO LOGADO
		for (Seguindo usuario : usuarioExistente.get().getListaSeguindo()) {
				
			// CASO O USUARIO JA ESTEJA SENDO SEGUIDO, ELE E RETIRADO DA LISTA DE RECOMENDACOES
			listaUsuarios.remove(usuario.getUsuario());
			
		}

		// REMOVE O USUARIO LOGADO
		listaUsuarios.remove(usuarioExistente.get());

		return listaUsuarios;
	}

	public List<Usuario> usuariosSeguidos(long id) {

		// CAPTURA OS DADOS DO USUARIO LOGADO
		Optional<Usuario> usuarioExistete = usuarioRepository.findById(id);

		// INSTANCIA UMA LISTA DE USUARIO SEGUIDOS PELO USUARIO LOGADO
		List<Usuario> listaUsuariosSeguidos = new ArrayList<>();

		// NAVEGA NA LISTA DE USUARIO SEGUIDOS PELO USUARIO LOGADO
		for (Seguindo usuarioSeguido : usuarioExistete.get().getListaSeguindo()) {
			
			// INSERE O OBJ DO USUARIO SEGUIDO DENTRO DA LISTA
			listaUsuariosSeguidos.add(usuarioRepository.findById(usuarioSeguido.getId()).get());

		}

		return listaUsuariosSeguidos;
	}

}
