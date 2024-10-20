package model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer extends AbstractEntity<Long>{

	@Column(name = "name" , nullable = false , length = 200 , unique = false , updatable = false)
	private String name;
	
	@Column(name = "cpf" , nullable = false , length = 12 , unique = true ,updatable = false)
	private String cpf;
	
	@Column(name ="email" , nullable = false , length = 200 , updatable = true , unique = false)
	private String email;
	
	@Column(name = "password" , nullable = false , length = 200 , unique = true ,updatable = false)
	private String password;
	
	@Column(name = "authentication"  , nullable = false , unique = false , updatable = true , length = 200)
	private List<String> authentication = new ArrayList<>();
	
	
	@OneToOne(mappedBy = "customer" , cascade = CascadeType.ALL ,orphanRemoval = true)
	private Account account;
	
}
