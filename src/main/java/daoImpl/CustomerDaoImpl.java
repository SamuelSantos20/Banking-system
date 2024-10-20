package daoImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import dao.CustomerDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import model.Customer;

@Repository
public class CustomerDaoImpl extends AbstractDao<Customer, Long> implements CustomerDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Customer findByName(String name) throws Exception {
		
		try {
			
			String sql = "select c from Customer c where c.name =:name";
			Query query  = entityManager.createQuery(sql);
			query.setParameter("name", name);
			Customer customer = new Customer();
			List<Customer> customers = query.getResultList();
			
			for(Customer c : customers) {
				
				customer = c;
				
				
			}
			
			return customer;
		} catch (Exception e) {
			
			System.err.println(e);
			throw new Exception("unknown erro");
	
			
			
		}
	}
	
	
	
	public Optional<Customer> findByCpf(String cpf) throws Exception {
		
		try {
		
			String sql = "select c from Customer c where c.cpf=:cpf ";
			
			Query query = entityManager.createQuery(sql);
			
			query.setParameter("cpf", cpf);
			
			Optional<Customer> optional = Optional.empty();
			
			List<Customer> customers = query.getResultList();
			
			for(Customer c : customers) {
				
				optional = Optional.of(c);
				
			}
			
			return optional;
			
		} catch (Exception e) {

			System.err.println(e);
			return Optional.empty();
			
			
		}
		
		
	}
	
	
}
