package br.com.jsis.gateway.controllers.company;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jsis.gateway.models.company.Company;
import br.com.jsis.gateway.services.company.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	public ResponseEntity<Company> create(@Valid @RequestBody Company company) {
		return ResponseEntity.ok().body(this.companyService.create(company));
	}
	
}
