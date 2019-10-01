package br.com.jsis.gateway.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.jsis.gateway.models.User;
import br.com.jsis.gateway.models.enums.Perfil;
import br.com.jsis.gateway.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService service;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = this.service.findEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		List<Perfil> perfis = new ArrayList<>();
		perfis.add(Perfil.ADMIN);
		return new UserSS(user.getId(), user.getEmail(), user.getPassword(), perfis);
	}

}
