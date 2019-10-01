package br.com.jsis.gateway.dtos;

import java.util.Date;

import br.com.jsis.gateway.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class userDTO {

	private Integer id;
	private String email;
	private String document;
	private Integer companyId;
	private Date createAt;
	private Date updateAt;
	
	public userDTO(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.document = user.getDocument();
		this.companyId = user.getCompanyId();
		this.createAt = user.getCreateAt();
		this.updateAt = user.getUpdateAt();
	}
}
