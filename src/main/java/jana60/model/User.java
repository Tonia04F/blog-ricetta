package jana60.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CollectionId;

@Entity

public class User {
	
	@Id
	private Integer id;
	
	
	@Column(nullable=false, unique = true)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	
	//fetchType= per assicurarsi che mappi la corretta entita da relazionare, performante 
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	


}
