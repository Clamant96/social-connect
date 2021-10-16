package br.com.helpconnect.socialConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.helpconnect.socialConnect.model.Seguindo;
import br.com.helpconnect.socialConnect.model.Usuario;
import br.com.helpconnect.socialConnect.repository.SeguindoRepository;
import br.com.helpconnect.socialConnect.service.PostagemService;

@RestController
@RequestMapping("/seguindo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SeguindoController {
	
	@Autowired
	private SeguindoRepository repository;
	
	@Autowired
	private PostagemService service;
	
	@GetMapping
	public ResponseEntity<List<Seguindo>> findAllByListaSeguindo() {
		
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Seguindo> findByIdSeguindo(@PathVariable long id) {
		
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/lista_seguindo/{idSeguindo}/seguindo/{idUsuario}")
	public ResponseEntity<Usuario> seguirUsuario(@PathVariable long idSeguindo, @PathVariable long idUsuario) {
		
		return ResponseEntity.ok(service.seguirUsuario(idSeguindo, idUsuario));
	}
	
	@PostMapping
	public ResponseEntity<Seguindo> postSeguindo(@RequestBody Seguindo seguindo) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(seguindo));
	}

}
