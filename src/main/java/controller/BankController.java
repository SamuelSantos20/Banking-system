package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dto.CustomerDto;
import enums.AuthenticationUser;
import enums.Banks;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import model.Account;
import model.Bank;
import model.Customer;
import serviceImpl.AccountServiceImpl;
import serviceImpl.CustomerServiceImpl;
import util.GeneratorNumber;

@Controller
@RequiredArgsConstructor
public class BankController {

	private final CustomerServiceImpl customerserviceImpl;

	private final AccountServiceImpl accountserviceImpl;

	private final HttpServletRequest httpServletRequest;

	@GetMapping("/pre/register/customer/")
	public ModelAndView RegisterCustomer() {

		ModelAndView mv = new ModelAndView();

		try {

			mv.addObject("Customer", new CustomerDto());
			mv.addObject("authentication", AuthenticationUser.USER.name());
			mv.setViewName("register.html");
			return mv;

		} catch (Exception e) {

			System.out.println(e);
			mv.setViewName("redirect:/error");
			return mv;

		}

	}

	@PostMapping("/register/customer")
	public ModelAndView RegisterCustomer(CustomerDto customerDto,
			@RequestParam("confirm-password") String confirmPassword , @RequestParam("authentication") String authentication) {
		ModelAndView mv = new ModelAndView();

		try {

			saveCustomer(customerDto, confirmPassword , authentication);
			mv.addObject("Customer", new CustomerDto());
			mv.setViewName("register.html");
			return mv;

		} catch (Exception e) {
			System.out.println(e);
			mv.setViewName("redirect:/error");
			return mv;

		}

	}

	private void saveCustomer(CustomerDto customerDto, String passwordConfirmed , String authentication) throws Exception {

		if (customerDto == null) {

			throw new Exception("Customer data cannot be null");

		}

		
		//Validação para saber se já existe um cadastro
		Optional<Customer> existingCustomer  = customerserviceImpl.ListCustomCpf(customerDto.getCpf());
		System.out.println(existingCustomer);
		
		if (!existingCustomer.isEmpty()) {

			throw new Exception("Account already registered");

		}

		try {

			Boolean isValid = (customerDto.getPassword().equals(passwordConfirmed)
					&& customerDto.getCpf().length() == 11 ? true : false);

			if (!isValid) {

				System.out.println(isValid);
				throw new Exception("Not it was possible perform registration ");
			}

			
			
			// Faz a criação de uma nova sessaão
			HttpSession httpSession = httpServletRequest.getSession();

			
			
			// Transfer os valores do Dto paras as respectivas variaveis
			String name = customerDto.getName();

			String cpf = customerDto.getCpf();

			String password = customerDto.getPassword();

			String email = customerDto.getEmail();

			List<String> authorite = new ArrayList<>();
			authorite.add(authentication);
			
			// Seta o valor do nome do Usuario para a sessão
			httpSession.setAttribute("name", name);
			
			

			// Registra o Usuario
			Customer customer = new Customer();
			customer.setName(name);
			customer.setCpf(cpf);
			customer.setPassword(password);
			customer.setEmail(email);
			customer.setAuthentication(authorite);

			customerserviceImpl.saveCustomer(customer);

			
			
			
			// Registra o nome do Banco em que o Usuario será cadastrado
			String nameBank = Banks.SAMBANK.name();
			Bank bank = new Bank();
			bank.setName(nameBank);

			
			
			
			// Recupera os dados do Usuario recem cadastrado pelo nome guardado na Session
			String nameUser = (String) httpSession.getAttribute("name");
			Customer customers = customerserviceImpl.ListCustomerName(nameUser);
			System.out.println(customers);

			
			
			
			// Cria a conta do Usuario que está sendo cadastrado
			Account account = new Account();
			account.setBalance(0.0);
			account.setBank(bank);
			account.setNumber(GeneratorNumber.generateNumber());
			account.setCustomer(customers);

			
			
			accountserviceImpl.saveAccount(account);

		} catch (Exception e) {

			System.out.println(e);
			throw new Exception("Not it was possible perform registration ");

		}

	}

	

}
