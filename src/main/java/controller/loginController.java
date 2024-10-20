package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class loginController {

	@GetMapping("/loginRequest")
	public ModelAndView preLogin() {

		ModelAndView mv = new ModelAndView();

		try {
			mv.setViewName("login.html");
			return mv;
		} catch (Exception e) {

			System.out.println(e);
			mv.setViewName("redirect:/error");
			return mv;
		}

	}
	
	//Não retorna nada 
	@PostMapping("/login")
	public void Login() {}
	
	
	

}
