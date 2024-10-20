package serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import daoImpl.CustomerDaoImpl;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import model.Customer;
import service.CustomerService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class CustomerServiceImpl implements CustomerService {

	private final CustomerDaoImpl customerdaoImpl;

	private final PasswordEncoder passwordEncoder;

	@Override
	public void saveCustomer(Customer customer) throws Exception {
		if (customer == null) {

			throw new Exception("Customer data cannot be null");

		}

		try {

			String password = passwordEncoder.encode(customer.getPassword());
			customer.setPassword(password);

			customerdaoImpl.save(customer);

		} catch (Exception e) {

			System.err.println(e);
			throw new Exception("unknown erro");
		}
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerdaoImpl.update(customer);

	}

	@Override
	public void deleteCustomer(Long id) {
		customerdaoImpl.delete(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Customer ListCustomerId(Long id) {

		return customerdaoImpl.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Customer> ListCustomer() {

		return customerdaoImpl.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Customer ListCustomerName(String name) throws Exception {
		
		
		return customerdaoImpl.findByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Customer> ListCustomCpf(String cpf) throws Exception {
	
		return customerdaoImpl.findByCpf(cpf);
	}

}
