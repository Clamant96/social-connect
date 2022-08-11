package br.com.helpconnect.socialConnect.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.helpconnect.socialConnect.model.Seguindo;
import br.com.helpconnect.socialConnect.model.UserLogin;
import br.com.helpconnect.socialConnect.model.Usuario;
import br.com.helpconnect.socialConnect.repository.SeguindoRepository;
import br.com.helpconnect.socialConnect.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private SeguindoRepository seguindoRepository;

	public Optional<Usuario> CadastrarUsuario(Usuario usuario) {	
		
		/* CONDICAO PARA INPEDIR A CRIACAO DE UM USUARIO DUPLICADO DENTRO DA APLICACAO */
		if(repository.findByUsername(usuario.getUsername()).isPresent() && usuario.getId() == 0) {
			return null;
			
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		/* INSTANCIA UM NOVA LISTA DE SEGUINDO */
		Seguindo seguindo = new Seguindo();
		
		/* REGISTRA AS NOVAS LISTAS NA BASE DE DADOS */
		seguindoRepository.save(seguindo);
		
		/* ASSOCIA O USUARIO AS NOVAS LISTAS */
		usuario.setSeguindo(seguindo);
		
		/* REGISTRA O USUARIO NA BASE DE DADOS */
		repository.save(usuario);
		
		return Optional.of(repository.save(usuario));
	}

	public Optional<UserLogin> Logar(Optional<UserLogin> user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsername(user.get().getUsername());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {

				String auth = user.get().getUsername() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);	
				user.get().setId(usuario.get().getId());
				user.get().setNome(usuario.get().getNome());
				user.get().setImg(usuario.get().getImg());
				user.get().setSenha(usuario.get().getSenha());
				user.get().setUsername(usuario.get().getUsername());
				user.get().setSite(usuario.get().getSite());
				user.get().setBiografia(usuario.get().getBiografia());
				user.get().setEmail(usuario.get().getEmail());

				return user;

			}
		}
		return null;
	}
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return Optional.of(repository.save(usuario));
	}
	
	public String ConverterSenhaUsuario(String senha) {
		
		byte[] decodedAuth = Base64.decodeBase64(senha);
		String authHeader = new String(decodedAuth);
		
		System.out.println("SENHA: "+ authHeader);
		
		return authHeader;
	}
	
}
