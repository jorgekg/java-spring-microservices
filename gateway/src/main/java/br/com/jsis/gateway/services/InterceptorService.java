package br.com.jsis.gateway.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class InterceptorService {

	public void validationSession(HttpServletRequest request, UsernamePasswordAuthenticationToken authenticationToken) {
		// validar sessão;
	}
	
}
