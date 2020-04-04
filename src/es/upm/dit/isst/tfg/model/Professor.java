package es.upm.dit.isst.tfg.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Professor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String email;
	private String password;
	private String name;
	
	@OneToMany(mappedBy = "advisor", fetch = FetchType.EAGER)
	private Collection<TFG> advisedTFGs;
	
	public Professor() {
		super();
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Collection<TFG> getAdvisedTFGs() {
		return advisedTFGs;
	}
	
	public void setAdvisedTFGs(Collection<TFG> advisedTFGs) {
		this.advisedTFGs = advisedTFGs;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Professor [email=" + email + ", password=" + password + ", name=" + name + ", advisedTFGs="
				+ advisedTFGs + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}


}
