package br.com.jsis.gateway.services.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
	
	@Transactional
	public Company create(Company company) {
		User user = this.userService.getUserLogged();
		if (user.getCompanyId() == null) {
			List<User> users = new ArrayList<User>();
			users.add(user);
			company.setUsers(users);
		} else {
			Optional<Company> optional = this.companyRepository.findById(user.getCompanyId());
			if (optional.isPresent()) {
				company.setCompany(optional.get());
			}
		}
		company.setUserId(user.getId());
		company.setCreateAt(new Date());
		company.setUpdateAt(new Date());
		Company companySaved = this.companyRepository.save(company);
		if (user.getCompanyId() == null) {
			user.setCompanyId(companySaved.getId());
			this.userService.updateCompany(user);
		}
		return company;
	}
	
}
