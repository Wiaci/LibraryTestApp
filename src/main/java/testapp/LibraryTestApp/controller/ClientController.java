package testapp.LibraryTestApp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import testapp.LibraryTestApp.model.Client;
import testapp.LibraryTestApp.service.ClientService;

/**
 * Контроллер для работы с клиентами
 */
@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

	private final ClientService clientService;

	/**
	 * Вернуть страницу из списка клиентов
	 */
	@GetMapping
	public String listClients(Model model, Pageable pageable) {
		model.addAttribute(Client.LIST_NAME, clientService.findAll(pageable));
		return "clients/list";
	}

	/**
	 * Вернуть форму добавления клиента
	 */
	@GetMapping("/add")
	public String addClient(Model model) {
		model.addAttribute(Client.NAME, new Client());
		return "clients/add";
	}

	/**
	 * Сохранить клиента
	 */
	@PostMapping("/save")
	public String saveClient(@ModelAttribute Client client) {
		clientService.save(client);
		return "redirect:";
	}

	/**
	 * Вернуть форму редактирования клиента
	 */
	@GetMapping("/edit/{id}")
	public String editClient(@PathVariable Long id, Model model) {
		Client client = clientService.findById(id);
		model.addAttribute(Client.NAME, client);
		return "clients/add";
	}

	/**
	 * Удалить клиента
	 */
	@GetMapping("/delete/{id}")
	public String deleteClient(@PathVariable("id") Long id) {
		clientService.deleteById(id);
		return "redirect:/clients";
	}
}
