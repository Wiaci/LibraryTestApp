package testapp.LibraryTestApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import testapp.LibraryTestApp.model.Book;
import testapp.LibraryTestApp.model.Borrowing;
import testapp.LibraryTestApp.model.Client;

import java.time.LocalDate;

/**
 * Dto с информацией о заказе
 */
@Data
@NoArgsConstructor
public class BorrowingInfoDto {
	private String clientFullName; // ФИО клиента
	private LocalDate clientBirthDate; // Дата рождения клиента
	private String bookTitle; // Название книги
	private String bookAuthor; // Автор книги
	private String bookIsbn; // ISBN книги
	private LocalDate borrowingDate;

	public BorrowingInfoDto(Borrowing borrowing) {
		Client client = borrowing.getClient();
		Book book = borrowing.getBook();
		clientFullName = client.getFullName();
		clientBirthDate = client.getBirthDate();
		bookTitle = book.getTitle();
		bookAuthor = book.getAuthor();
		bookIsbn = book.getIsbn();
		borrowingDate = borrowing.getBorrowDate();
	}

}
