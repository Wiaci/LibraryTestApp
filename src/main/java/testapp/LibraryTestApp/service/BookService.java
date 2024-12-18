package testapp.LibraryTestApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import testapp.LibraryTestApp.model.Book;
import testapp.LibraryTestApp.repository.BookRepository;

import java.util.List;

/**
 * Сервис для работы с книгами
 */
@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;

	/**
	 * Вернуть страницу из списка книг
	 */
	public Page<Book> findAll(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}

	/**
	 * Вернуть книгу по id
	 */
	public Book findById(Long id) {
		return bookRepository.findById(id).orElseGet(Book::new);
	}

	/**
	 * Сохранить книгу
	 */
	public void save(Book book) {
		bookRepository.save(book);
	}

	/**
	 * Сохранить список книг
	 */
	public void saveAll(List<Book> books) {
		bookRepository.saveAll(books);
	}

	/**
	 * Удалить книгу по id
	 */
	public void deleteById(Long id) {
		bookRepository.deleteById(id);
	}

	/**
	 * Найти книги, содержащие в названии query
	 */
	public List<Book> searchBooksByTitle(String query) {
		return bookRepository.findByTitleContainingIgnoreCase(query);
	}
}
