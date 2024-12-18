package testapp.LibraryTestApp.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Клиент
 */
@Entity
@Data
public class Client {

	public static final String NAME = "client";
	public static final String LIST_NAME = "clients";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * ФИО
	 */
	private String fullName;
	/**
	 * Дата рождения
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
}
