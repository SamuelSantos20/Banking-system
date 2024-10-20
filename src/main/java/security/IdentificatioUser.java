package security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class IdentificatioUser {

	private String name;

	private String cpf;

	private String password;

	private List<String> authentication = new ArrayList<>();

}
