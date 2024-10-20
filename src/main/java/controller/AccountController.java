package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dto.OperationDto;
import enums.TypeOperation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import model.Account;
import model.Customer;
import model.Operation;
import serviceImpl.AccountServiceImpl;
import serviceImpl.CustomerServiceImpl;
import serviceImpl.OperationServiceImpl;

@Controller
@RequiredArgsConstructor
public class AccountController {

	private final HttpServletRequest httpServletRequest;

	private final AccountServiceImpl accountServiceImpl;

	private final CustomerServiceImpl customerServiceImpl;

	private final OperationServiceImpl operationServiceImpl;

	@GetMapping("/pre/deposit/account")
	@PreAuthorize("hasAnyRole('USER')")
	public ModelAndView preDeposit() {

		ModelAndView mv = new ModelAndView();

		try {
			mv.setViewName("deposit.html");
			return mv;

		} catch (Exception e) {

			System.out.println(e);
			mv.setViewName("redirect:/error");
			return mv;

		}
	}

	@PostMapping("/register/new/deposit")
	@PreAuthorize("hasAnyRole('USER')")
	public ModelAndView Deposit(@RequestParam("deposit") Double value) {
		ModelAndView mv = new ModelAndView();

		try {

			saveDeposit(value);
			mv.addObject("successMessage", "deposit made successfully");
			mv.setViewName("deposit.html");
			return mv;

		} catch (Exception e) {

			System.out.println(e);
			mv.setViewName("redirect:/error");
			return mv;

		}

	}

	private void saveDeposit(Double value) throws Exception {

		try {
			HttpSession httpSession = httpServletRequest.getSession();

			String customerName = (String) httpSession.getAttribute("name");

			Customer customer = customerServiceImpl.ListCustomerName(customerName);

			Optional<Account> optional = accountServiceImpl.ListAccountUser(customer);

			Account account = optional.get();

			Double valueTotal = (value + account.getBalance());
			account.setBalance(valueTotal);

			accountServiceImpl.updateAccount(account);

			
			
			//Registro de operação 
			
			Operation operation = new Operation();

			Date date = new Date();

			operation.setDate(date);
			operation.setType(TypeOperation.DEPOSIT.name());
			operation.setValue(value);
			
			List<Account> accounts = new ArrayList<>();
			
			accounts.add(account);
			
			operation.setAccounts(accounts);
			
			operationServiceImpl.saveOperation(operation);

		} catch (Exception e) {
			System.out.println(e);
			throw new Exception("It was not possible to make the deposit");
		}

	}

	@GetMapping("/pre/sake/account")
	@PreAuthorize("hasAnyRole('USER')")
	public ModelAndView preSake() {

		ModelAndView mv = new ModelAndView();
		try {

			mv.setViewName("sake.html");
			return mv;
		} catch (Exception e) {

			System.out.println(e);
			mv.setViewName("redirect:/error");
			return mv;

		}

	}

	@PostMapping("/register/new/sake")
	@PreAuthorize("hasAnyRole('USER')")
	public ModelAndView Sake(@RequestParam("sake") Double value) {

		ModelAndView mv = new ModelAndView();

		try {

			saveSake(value);
			mv.addObject("successMessage", "withdrawal successful");
			mv.setViewName("sake.html");
			return mv;

		} catch (Exception e) {

			System.out.println(e);
			mv.setViewName("redirect:/error");
			return mv;

		}

	}

	private void saveSake(Double value) throws Exception {

		try {

			HttpSession httpSession = httpServletRequest.getSession();

			String customerName = (String) httpSession.getAttribute("name");

			Customer customer = customerServiceImpl.ListCustomerName(customerName);

			Optional<Account> optional = accountServiceImpl.ListAccountUser(customer);

			Account account = optional.get();

			Double valueTotal = (account.getBalance() - value);
			account.setBalance(valueTotal);

			accountServiceImpl.updateAccount(account);

			
			
			Operation operation = new Operation();

			Date date = new Date();

			operation.setDate(date);
			operation.setType(TypeOperation.SAKE.name());
			operation.setValue(value);
			List<Account> accounts = new ArrayList<>();
			
			accounts.add(account);
			
			operation.setAccounts(accounts);
			
			
			operationServiceImpl.saveOperation(operation);

		} catch (Exception e) {
			System.out.println(e);
			throw new Exception("It was not possible to make the sake");
		}

	}
	
	
	@GetMapping("/access/operations/list")
	@PreAuthorize("hasAnyRole('USER')")
	public ModelAndView OperationsList() {
		
		ModelAndView mv = new ModelAndView();
		try {
			
			HttpSession httpSession = httpServletRequest.getSession();
			
			String name_user  = (String) httpSession.getAttribute("name");
			
			Customer customer = customerServiceImpl.ListCustomerName(name_user);
			
			Optional<Account> optional_Account = accountServiceImpl.ListAccountUser(customer);
			
			Account  account = optional_Account.orElseThrow( ()-> new IllegalArgumentException("account not found!"));
			
			List<OperationDto> operationDtos =  operationServiceImpl.ListOperationAccount(account);
			
			mv.addObject("operation", operationDtos);
			mv.setViewName("operations_history.html");
			return mv;
			
			
		} catch (Exception e) {
			System.out.println(e);
			mv.setViewName("redirect:/error");
			return mv;
		}
		
	}
	
	
	

}
