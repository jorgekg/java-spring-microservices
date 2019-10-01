package br.com.jsis.gateway.repositories.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jsis.gateway.models.company.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
