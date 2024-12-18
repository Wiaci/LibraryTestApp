package testapp.LibraryTestApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import testapp.LibraryTestApp.model.Client;
import testapp.LibraryTestApp.repository.ClientRepository;

import java.util.List;

/**
 * Сервис для работы с клиентами
 */
@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;

	/**
	 * Вернуть страницу из списка клиентов
	 */
	public Page<Client> findAll(Pageable pageable) {
		return clientRepository.findAll(pageable);
	}

	/**
	 * Вернуть клиента по id
	 */
	public Client findById(Long id) {
		return clientRepository.findById(id).orElseGet(Client::new);
	}

	/**
	 * Сохранить клиента
	 */
	public void save(Client client) {
		clientRepository.save(client);
	}

	/**
	 * Сохранить список клиентов
	 */
	public void saveAll(List<Client> clients) {
		clientRepository.saveAll(clients);
	}

	/**
	 * Удалить клиента по id
	 */
	public void deleteById(Long id) {
		clientRepository.deleteById(id);
	}

	/**
	 * Найти клиентов, содержащих в имени query
	 */
	public List<Client> searchClientsByName(String query) {
		return clientRepository.findByFullNameContainingIgnoreCase(query);
	}

}
