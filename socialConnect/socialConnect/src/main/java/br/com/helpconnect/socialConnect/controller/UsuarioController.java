package br.com.helpconnect.socialConnect.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.helpconnect.socialConnect.model.Postagem;
import br.com.helpconnect.socialConnect.model.Story;
import br.com.helpconnect.socialConnect.model.UserLogin;
import br.com.helpconnect.socialConnect.model.Usuario;
import br.com.helpconnect.socialConnect.repository.UsuarioRepository;
import br.com.helpconnect.socialConnect.service.PostagemService;
import br.com.helpconnect.socialConnect.service.SendMailService;
import br.com.helpconnect.socialConnect.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PostagemService postagemService;
	
	@Autowired
	private SendMailService sendMailService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAllByUsuario() {
		
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/postagens-seguidores/{id}")
	public ResponseEntity<List<Postagem>> findByPostagensSeguidores(@PathVariable long id) {
		
		return ResponseEntity.ok(postagemService.postagensSeguidores(id));
	}

	@GetMapping("/storys-seguidores/{id}")
	public ResponseEntity<List<Story>> findByStorysSeguidores(@PathVariable long id) {
		
		return ResponseEntity.ok(postagemService.storysSeguidores(id));
	}

	@GetMapping("/usuarios-para-seguidor/{id}")
	public ResponseEntity<List<Usuario>> findByUsuariosparaSeguir(@PathVariable long id) {
		
		return ResponseEntity.ok(postagemService.usuariosParaSeguir(id));
	}

	@GetMapping("/usuarios-seguidos/{id}")
	public ResponseEntity<List<Usuario>> findByUsuariosSeguidos(@PathVariable long id) {
		
		return ResponseEntity.ok(postagemService.usuariosSeguidos(id));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findByIdUsuario(@PathVariable long id) {
		
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<List<Usuario>> findByIdNomeUsuario(@PathVariable String username) {
		
		System.out.println(usuarioService.ConverterSenhaUsuario("$2a$10$FcdIZwXPbf1OAqyQIwuFjuy2JVtmkuut5sEohq4ZCsTTIt5ztS3pS"));
		
		return ResponseEntity.ok(repository.findAllByUsernameContainingIgnoreCase(username));
	}
	
	@PostMapping("/email")
	public ResponseEntity<Boolean> enviarEmail(@RequestBody Usuario usuario) {
		
		return ResponseEntity.ok(sendMailService.sendMail(usuario));
	}
	
	@PostMapping
	public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}
	
	@PutMapping
	public ResponseEntity<Usuario> putUsuario(@RequestBody Usuario usuario) {
		
		return ResponseEntity.ok(repository.save(usuario));
	}
	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user) {
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario) {
		Optional<Usuario> user = usuarioService.CadastrarUsuario(usuario);
		
		try {
			return ResponseEntity.ok(user.get());
			
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
			
		}
		
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
		Optional<Usuario> user = usuarioService.atualizarUsuario(usuario);
		
		try {
			return ResponseEntity.ok(user.get());
			
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
			
		}
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteUsuario(@PathVariable long id) {
		
		repository.deleteById(id);
	}
	
}
