package security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CustomAuthentication implements Authentication {

	private IdentificatioUser identificatioUser;

	@Override
	public String getName() {

		return identificatioUser.getCpf();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return identificatioUser.getAuthentication().stream().map(map -> new SimpleGrantedAuthority("USER"))
				.collect(Collectors.toList());
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return this.identificatioUser;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

		throw new IllegalArgumentException("not is necessary");

	}

}
