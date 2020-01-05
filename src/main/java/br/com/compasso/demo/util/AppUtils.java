package br.com.compasso.demo.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.util.StringUtils;

public class AppUtils {

	public static LocalDate convertStringToLocalDate(String dateString) {

		LocalDate output = null;
		if (StringUtils.hasText(dateString)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			output = LocalDate.parse(dateString, formatter);
		}

		return output;
	}

	public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
		if ((birthDate != null) && (currentDate != null)) {
			return Period.between(birthDate, currentDate).getYears();
		} else {
			return 0;
		}
	}
}
