package dao;

import java.util.List;
import java.util.Optional;

import model.Customer;

public interface CustomerDao {

	public void save(Customer customer); 
    
    public void update(Customer customer); 
    
    public void delete(Long id); 

    public Customer findById(Long id);
    
    public List<Customer> findAll();

    public Customer findByName(String name) throws Exception;
	
    public Optional<Customer>  findByCpf(String cpf) throws Exception;
}
