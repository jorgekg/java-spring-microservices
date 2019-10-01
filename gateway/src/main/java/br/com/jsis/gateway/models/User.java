package br.com.jsis.gateway.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotEmpty(message = "E-mail is required")
	@NotNull(message = "E-mail is required")
	private String email;

	@NotEmpty(message = "Password is required")
	@NotNull(message = "Password is required")
	private String password;
	
	@NotEmpty(message = "Document is required")
	@NotNull(message = "Document is required")
	private String document;
	
	private Integer companyId;
	private Date createAt;
	private Date updateAt;

	public User() {

	}

	public User(Integer id, String email, String password, Date createAt, Date updateAt) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}
}
