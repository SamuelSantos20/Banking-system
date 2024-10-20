package controller;

import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import model.Account;
import model.Customer;
import serviceImpl.AccountServiceImpl;
import serviceImpl.CustomerServiceImpl;

@Controller
@RequiredArgsConstructor
public class indexController {

	private final HttpServletRequest httpServletRequest;
	
	private final AccountServiceImpl accountServiceImpl;
	
	private final CustomerServiceImpl customerServiceImpl;
	
	@GetMapping("/index")
	@PreAuthorize("hasAnyRole('USER')")
	public ModelAndView Index() {

		ModelAndView mv = new ModelAndView();

		try {

			HttpSession httpSession = httpServletRequest.getSession();
			
			String name = (String) httpSession.getAttribute("name");
			
			Customer customer = customerServiceImpl.ListCustomerName(name);

			Optional<Account> optional =  accountServiceImpl.ListAccountUser(customer);
			
			Account account = optional.get();

			String valueCont = String.format("%.2f", account.getBalance());
			
			
			System.out.println(name);
			
			mv.setViewName("index.html");
			mv.addObject("name", name);
			mv.addObject("balance", valueCont);
			return mv;
		} catch (Exception e) {

			System.out.println(e);
			mv.setViewName("redirect:/error");
			return mv;

		}

	}

}
