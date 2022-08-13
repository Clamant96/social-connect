package br.com.helpconnect.socialConnect.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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
	
	@GetMapping("/carregar/{usuario}/{imagem}")
	public ResponseEntity<byte[]> visualizarImagem(@PathVariable String usuario, @PathVariable String imagem) throws IOException {
		
		System.out.println("C:\\Users\\kevin\\Desktop\\arquivosUpload\\"+ usuario +"\\"+ imagem);
		
		InputStream initialStream = new FileInputStream(new File("C:\\Users\\kevin\\Desktop\\arquivosUpload\\"+ usuario +"\\"+ imagem));
		
		byte[] imageBytes = StreamUtils.copyToByteArray(initialStream);
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}

	/*
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
	*/
}