package model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "bank")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuppressWarnings("serial")
public class Bank extends AbstractEntity<Long> {

	@Column(name = "name" , nullable = false ,  length = 200 ,  unique = false)
	private String name;
	
	   @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.EAGER)
	   @ToString.Exclude
	    private List<Account> accounts = new ArrayList<>();
}
