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
	
	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@RequestBody Postagem postagem) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> putPostagem(@RequestBody Postagem postagem) {
		
		return ResponseEntity.ok(repository.save(postagem));
	}
	
	@GetMapping("/adicionarlike/{idPostagem}")
	public ResponseEntity<Postagem> adicionarLike(@PathVariable long idPostagem) {
		
		return ResponseEntity.ok(service.adicionarLike(idPostagem));
	}
	
	@GetMapping("/retirarlike/{idPostagem}")
	public ResponseEntity<Postagem> retirarLike(@PathVariable long idPostagem) {
		
		return ResponseEntity.ok(service.retirarLike(idPostagem));
	}
	
	@DeleteMapping
	public void deletePostagem(@PathVariable long id) {
		
		repository.deleteById(id);
	}

}
