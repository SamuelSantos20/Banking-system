package util;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class GeneratorNumber {

	private static final SecureRandom secureRandom = new SecureRandom();
	private static final int ID_LENGTH = 6;

	public static Long generateNumber() {

		StringBuilder idBuilder = new StringBuilder();
		
		for (int i = 0; i < ID_LENGTH; i++) {
			int digit = secureRandom.nextInt(10);
			idBuilder.append(digit);
		}

		String id = idBuilder.toString();

		Long cod = Long.parseLong(id);
		
		
		return cod;
	}

}
