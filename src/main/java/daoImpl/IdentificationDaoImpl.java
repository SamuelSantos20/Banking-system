package daoImpl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Customer;

@Repository
public interface IdentificationDaoImpl extends JpaRepository<Customer, Long> {

	Customer findByCpf(String cpf);
	
}
