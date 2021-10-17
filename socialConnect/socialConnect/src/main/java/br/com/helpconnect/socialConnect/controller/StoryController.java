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

import br.com.helpconnect.socialConnect.model.Story;
import br.com.helpconnect.socialConnect.repository.StoryRepository;

@RestController
@RequestMapping("/story")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StoryController {
	
	@Autowired
	private StoryRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Story>> getAllStorys() {
		
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Story> getByIdStory(@PathVariable long id) {
		
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Story> postStory(@RequestBody Story story) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(story));
	}
	
	@PutMapping
	public ResponseEntity<Story> putStory(@RequestBody Story story) {
		
		return ResponseEntity.ok(repository.save(story));
	}
	
	@DeleteMapping("/{id}")
	public void deletaStory(@PathVariable long id) {
		repository.deleteById(id);
	
	}

}
