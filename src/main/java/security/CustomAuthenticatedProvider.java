package security;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import daoImpl.IdentificationDaoImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import model.Customer;

@Component
@RequiredArgsConstructor
public class CustomAuthenticatedProvider implements AuthenticationProvider {

	private final PasswordEncoder passwordEncoder;

	private final IdentificationDaoImpl identificatioDaoImpl;

	private final HttpServletRequest httpServletRequest;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String cpf = authentication.getName();

		System.out.println(cpf);

		Optional<Customer> Optionalcustomer = Optional.ofNullable(identificatioDaoImpl.findByCpf(cpf));

		if (Optionalcustomer.isEmpty()) {

			throw new UsernameNotFoundException("Usuário não encontrado para o CPF: " + cpf);

		}

		Customer customer = Optionalcustomer.get();

		System.out.println(customer);

		String password = (String) authentication.getCredentials();

		Boolean IsValid = passwordEncoder.matches(password, customer.getPassword());

		if (IsValid) {

			System.out.println(IsValid);

			System.out.println(customer.getAuthentication());

			HttpSession httpSession = httpServletRequest.getSession();

			httpSession.setAttribute("name", customer.getName());

			IdentificatioUser identificatioUser = new IdentificatioUser(customer.getName(), customer.getCpf(),
					customer.getPassword(), customer.getAuthentication());

			return new CustomAuthentication(identificatioUser);

		}

		if (!IsValid) {
			throw new BadCredentialsException("Invalid CPF or password");
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {

		return true;
	}

}
