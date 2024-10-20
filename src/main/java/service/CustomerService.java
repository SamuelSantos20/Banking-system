package service;

import java.util.List;
import java.util.Optional;

import model.Customer;

public interface CustomerService {

	void saveCustomer(Customer customer) throws Exception;
	
	void updateCustomer(Customer customer);
	
	void deleteCustomer(Long id);
	
	Customer ListCustomerId(Long id);
	
	List<Customer> ListCustomer();
	
	Customer ListCustomerName(String name) throws Exception;
	
	Optional<Customer> ListCustomCpf(String cpf) throws Exception;
}
