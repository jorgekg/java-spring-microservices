package br.com.jsis.gateway.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import br.com.jsis.gateway.services.InterceptorService;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTUtil jwtUtil;
	private InterceptorService interceptorService;

	private UserDetailsService detailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
			UserDetailsService detailsService, InterceptorService interceptor) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.detailsService = detailsService;
		this.interceptorService = interceptor;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
					throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken authenticationToken = getAuthenticate(request, header.substring(7));
			this.interceptorService.validationSession(request, authenticationToken);
			if (authenticationToken != null) {
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthenticate(HttpServletRequest request, String token) {
		if (this.jwtUtil.isValidToken(token)) {
			String username = this.jwtUtil.getUsername(token);
			UserDetails details = this.detailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
		}
		return null;
	}

}