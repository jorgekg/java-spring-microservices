package br.com.jsis.gateway.models.company;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.jsis.gateway.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull(message = "Company is required")
	@NotEmpty(message = "Company is required")
	private String companyName;
	
	@NotNull(message = "Social name is required")
	@NotEmpty(message = "Social name is required")
	private String socialName;
	
	@Column(nullable = true)
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Company company;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<User> users;
	
	private Integer userId;
	private Date createAt;
	private Date updateAt;
	
	public Company() {
		
	}
	
}
