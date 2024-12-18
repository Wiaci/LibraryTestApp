package testapp.LibraryTestApp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import testapp.LibraryTestApp.model.Book;
import testapp.LibraryTestApp.service.BookService;

/**
 * Контроллер для работы с книгами
 */
@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;

	/**
	 * Вернуть страницу из списка книг
	 */
	@GetMapping
	public String listBooks(Model model, Pageable pageable) {
		Page<Book> bookPage = bookService.findAll(pageable);
		model.addAttribute(Book.LIST_NAME, bookPage);
		return "books/list";
	}

	/**
	 * Вернуть форму добавления книги
	 */
	@GetMapping("/add")
	public String addBook(Model model) {
		model.addAttribute(Book.NAME, new Book());
		return "books/add";
	}

	/**
	 * Сохранить книгу
	 */
	@PostMapping("/save")
	public String saveBook(@ModelAttribute Book book) {
		bookService.save(book);
		return "redirect:";
	}

	/**
	 * Вернуть форму редактирования книги
	 */
	@GetMapping("/edit/{id}")
	public String editBook(@PathVariable Long id, Model model) {
		Book book = bookService.findById(id);
		model.addAttribute(Book.NAME, book);
		return "books/add";
	}

	/**
	 * Удалить книгу
	 */
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") Long id) {
		bookService.deleteById(id);
		return "redirect:/books";
	}
}
