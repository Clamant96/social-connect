package br.com.helpconnect.socialConnect.controller;

import java.util.List;

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
import br.com.helpconnect.socialConnect.repository.PostagemRepository;
import br.com.helpconnect.socialConnect.service.PostagemService;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	@Autowired
	private PostagemService service;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> findAllByPostagem() {
		
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> findByIdPostagem(@PathVariable long id) {
		
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/postagensUsuario/{id}")
	public ResponseEntity<List<Postagem>> findByPostagemUsuario(@PathVariable long id) {
		
		return ResponseEntity.ok(repository.findAllByUsuario(id));
	}
	
	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@RequestBody Postagem postagem) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> putPostagem(@RequestBody Postagem postagem) {
		
		return ResponseEntity.ok(repository.save(postagem));
	}
	
	/*
	 * EXPLICACAO URI:
	 * 
	 * 	/likes_usuario_postagem -> nome da tabela associativa que esta no model Postagem
	 * 	/likePostagem -> Lista de postagens na classes usuario
	 * 	/like -> Lista de usuarios na classe Postagem
	 * 
	 * */
	
	@PutMapping("/likes_usuario_postagem/likePostagem/{idUsuario}/like/{idPostagem}")
	public ResponseEntity<Postagem> postLikePostagem(@PathVariable long idPostagem, @PathVariable long idUsuario){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.likePostagem(idPostagem, idUsuario));
	}
	
	@DeleteMapping("/{id}")
	public void deletePostagem(@PathVariable long id) {
		
		repository.deleteById(id);
	}

}
