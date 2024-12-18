package testapp.LibraryTestApp.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Книга
 */
@Entity
@Data
public class Book {

	public static final String NAME = "book";
	public static final String LIST_NAME = "books";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Название
	 */
	private String title;
	/**
	 * Автор
	 */
	private String author;
	/**
	 * ISBN
	 */
	@Column(unique = true, nullable = false)
	private String isbn;
}
