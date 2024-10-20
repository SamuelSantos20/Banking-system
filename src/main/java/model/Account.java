package model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class Account extends AbstractEntity<Long> {

	@Column(name = "number", unique = true, nullable = false, length = 20, updatable = false)
	private Long number;

	@Column(name = "balance", unique = false)
	private Double balance;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "customer_account")
	@ToString.Exclude // Excluir o campo da geração do toString para evitar loop
	private Customer customer;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bank_id", nullable = false)
	private Bank bank;

	@ManyToMany(mappedBy = "accounts", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Operation> operations = new ArrayList<>();

}
