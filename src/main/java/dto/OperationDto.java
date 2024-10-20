package dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationDto {

	
	private String type;
	
	private Double value;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	
}
