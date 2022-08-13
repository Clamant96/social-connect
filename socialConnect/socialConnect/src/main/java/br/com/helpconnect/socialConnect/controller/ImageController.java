package br.com.helpconnect.socialConnect.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImageController {

	// Return the image from the classpath location using ResponseEntity
	@GetMapping("/classpath")
	public ResponseEntity<byte[]> fromClasspathAsResEntity() throws IOException {

		ClassPathResource imageFile = new ClassPathResource("uploads/instagram1.jpg");

		byte[] imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}
	
	@GetMapping("/get-imagem/{imagem}")
	public ResponseEntity<byte[]> getImage(@PathVariable String imagem) throws IOException {

		ClassPathResource imageFile = new ClassPathResource("uploads/"+ imagem);

		byte[] imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}
	
	// Return the image from the classpath location using HttpServletResponse
	@GetMapping(value = "classpath1", produces = MediaType.IMAGE_JPEG_VALUE)
	public void fromClasspathAsHttpServResp(HttpServletResponse response) throws IOException {

		ClassPathResource imageFile = new ClassPathResource("uploads/instagram1.jpg");

		StreamUtils.copy(imageFile.getInputStream(), response.getOutputStream());
	}
}