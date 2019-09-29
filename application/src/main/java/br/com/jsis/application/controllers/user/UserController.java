package br.com.jsis.application.controllers.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@GetMapping
	public ResponseEntity<?> getUsers() {
		return ResponseEntity.ok("Teste do endpoins");
	}
	
}
