package testapp.LibraryTestApp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import testapp.LibraryTestApp.model.Borrowing;
import testapp.LibraryTestApp.service.BorrowingService;

/**
 * Контроллер для работы с заказами
 */
@Controller
@RequestMapping("/borrowings")
@RequiredArgsConstructor
public class BorrowingController {
	
	private final BorrowingService borrowingService;

	/**
	 * Вернуть страницу из списка заказов
	 */
	@GetMapping
	public String listBorrowings(Model model, Pageable pageable) {
		model.addAttribute(Borrowing.LIST_NAME, borrowingService.findAll(pageable));
		return "borrowings/list";
	}

	/**
	 * Вернуть форму добавления заказа
	 */
	@GetMapping("/add")
	public String addBorrowing(Model model) {
		model.addAttribute(Borrowing.NAME, new Borrowing());
		return "borrowings/add";
	}

	/**
	 * Вернуть страницу просмотра заказа
	 */
	@GetMapping("/borrowing/{id}")
	public String showBorrowing(@PathVariable Long id, Model model) {
		Borrowing borrowing = borrowingService.findById(id);
		model.addAttribute(Borrowing.NAME, borrowing);
		return "borrowings/borrowing";
	}

	/**
	 * Сохранить заказ
	 */
	@GetMapping
	@RequestMapping("/save")
	public String saveBorrowing(@ModelAttribute Borrowing borrowing) {
		borrowingService.save(borrowing);
		return "redirect:";
	}

	/**
	 * Удалить заказ
	 */
	@GetMapping("/delete/{id}")
	public String deleteBorrowing(@PathVariable("id") Long id) {
		borrowingService.deleteById(id);
		return "redirect:/borrowings";
	}
}
