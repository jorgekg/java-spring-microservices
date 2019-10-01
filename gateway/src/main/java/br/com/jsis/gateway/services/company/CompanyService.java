package br.com.jsis.gateway.services.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jsis.gateway.models.User;
import br.com.jsis.gateway.models.company.Company;
import br.com.jsis.gateway.repositories.company.CompanyRepository;
import br.com.jsis.gateway.services.UserService;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private UserService userService;
	
	public Company create(Company company) {
		User user = this.userService.getUserLogged();
		List<User> users = new ArrayList<User>();
		users.add(user);
		company.setUsers(users);
		company.setUserId(user.getId());
		company.setCreateAt(new Date());
		company.setUpdateAt(new Date());
		return this.companyRepository.save(company);
	}
	
}
