package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Target;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Table(name = "operation")
@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
public class Operation extends AbstractEntity<Long>{

	@Column( name = "type" , nullable = false , updatable = false , length = 200 , unique = false)
	private String type;
	
	@Column(name = "value" ,nullable = false ,  length = 200 , updatable = false , unique = false)
	private Double value;
	
	
	@ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinTable(name = "operation_account" , joinColumns = @JoinColumn(name ="operation_id") , inverseJoinColumns = @JoinColumn(name = "account_id"))
	@ToString.Exclude 
	private List<Account> accounts =  new ArrayList<>();
	
	@Column(name = "date" , nullable = false , unique = false , updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	
	
	
	
	
}
