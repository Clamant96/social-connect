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

import br.com.helpconnect.socialConnect.model.Mensagem;
import br.com.helpconnect.socialConnect.model.Postagem;
import br.com.helpconnect.socialConnect.repository.MensagemRepository;

@RestController
@RequestMapping("/mensagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MensagemController {
	
	@Autowired
	private MensagemRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Mensagem>> findAllByMensagens() {
		
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Mensagem> findByIdMensagem(@PathVariable long id) {
		
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Mensagem> postMensagem(@RequestBody Mensagem mensagem) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(mensagem));
	}
	
	@PutMapping
	public ResponseEntity<Mensagem> putMensagem(@RequestBody Mensagem mensagem) {
		
		return ResponseEntity.ok(repository.save(mensagem));
	}
	
	@DeleteMapping("/{id}")
	public void deleteMensagem(@PathVariable long id) {
		
		repository.deleteById(id);
	}
	
}
