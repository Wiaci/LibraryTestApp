package testapp.LibraryTestApp.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Заказ
 */
@Entity
@Data
public class Borrowing {

	public static final String NAME = "borrowing";
	public static final String LIST_NAME = "borrowings";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Клиент, взявший книгу
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Client client;
	/**
	 * Книга
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Book book;
	/**
	 * Дата взятия книги
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate borrowDate;
}
