package testapp.LibraryTestApp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import testapp.LibraryTestApp.dto.BorrowingInfoDto;
import testapp.LibraryTestApp.model.Book;
import testapp.LibraryTestApp.model.Client;
import testapp.LibraryTestApp.service.BookService;
import testapp.LibraryTestApp.service.BorrowingService;
import testapp.LibraryTestApp.service.ClientService;
import testapp.LibraryTestApp.service.TestDataGenerator;

import java.util.List;

/**
 * Rest-контроллер с необходимыми api endpoints
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LibraryRestController {

	private final BorrowingService borrowingService;
	private final BookService bookService;
	private final ClientService clientService;
	private final TestDataGenerator testDataGenerator;

	/**
	 * Вернуть информацию о всех заказах
	 */
	@GetMapping("/borrowings")
	public List<BorrowingInfoDto> getAllBorrowings() {
		return borrowingService.getAllBorrowingInfo();
	}

	/**
	 * Вернуть книги, содержащие query в названии
	 */
	@GetMapping("/books/search")
	public List<Book> searchBooks(@RequestParam String query) {
		return bookService.searchBooksByTitle(query);
	}

	/**
	 * Вернуть клиентов, содержащих query в имени
	 */
	@GetMapping("/clients/search")
	public List<Client> searchClients(@RequestParam String query) {
		return clientService.searchClientsByName(query);
	}

	/**
	 * Сгенерировать тестовые данные
	 */
	@GetMapping("/generate-test")
	public String generateTest() {
		testDataGenerator.generateTestData();
		return "Готово!";
	}
}
