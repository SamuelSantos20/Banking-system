package dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {

	
	private String name;
	
	private String cpf;
	
	private String email;
	
	private String password;
	
	private List<String> authentication = new ArrayList<>();
	
	
	

}
