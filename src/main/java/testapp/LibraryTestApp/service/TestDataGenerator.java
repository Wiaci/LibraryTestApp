package testapp.LibraryTestApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import testapp.LibraryTestApp.model.Book;
import testapp.LibraryTestApp.model.Client;

import java.time.LocalDate;
import java.util.*;

/**
 * Генератор тестовых данных
 */
@Service
@RequiredArgsConstructor
public class TestDataGenerator {

	private static final int TEST_DATA_SIZE = 500;

	private final List<String> nouns = Arrays.asList(
		"Человек", "Время", "Дело", "Жизнь", "День",
		"Рука", "Глаз", "Слово", "Место", "Лицо",
		"Друг", "Дом", "Сила", "Конец", "Ребёнок"
	);

	private final List<String> patronymics = Arrays.asList(
		"Алексеевич", "Иванович", "Владимирович", "Петрович",
		"Михайлович", "Сергеевич", "Романович"
	);

	private final List<String> surnames = Arrays.asList(
		"Иванов", "Петров", "Сидоров", "Михайлов",
		"Смирнов", "Кузнецов", "Романов"
	);

	private final List<String> names = Arrays.asList(
		"Алексей", "Иван", "Владимир", "Петр",
		"Михаил", "Сергей", "Роман"
	);

	private final BookService bookService;
	private final ClientService clientService;

	/**
	 * Сгенерировать тестовые данные
	 */
	public void generateTestData() {
		List<Book> books = new ArrayList<>();
		List<Client> clients = new ArrayList<>();
		List<String> titles = generateUniqueTitles();
		List<String> isbns = generateUniqueISBNs();
		List<String> authors = generateAuthors();
		List<String> clientNames = generateNames();
		List<LocalDate> birthDays = generateBirthDays();
		for (int i = 0; i < titles.size(); i++) {
			Book book = new Book();
			book.setTitle(titles.get(i));
			book.setIsbn(isbns.get(i));
			book.setAuthor(authors.get(i));
			books.add(book);

			Client client = new Client();
			client.setFullName(clientNames.get(i));
			client.setBirthDate(birthDays.get(i));
			clients.add(client);
		}
		bookService.saveAll(books);
		clientService.saveAll(clients);
	}

	private List<String> generateAuthors() {
		List<String> authors = new ArrayList<>();
		Random random = new Random();

		while (authors.size() < TEST_DATA_SIZE) {
			int iName = random.nextInt(names.size());
			int iSurname = random.nextInt(surnames.size());
			authors.add(names.get(iName) + " " + surnames.get(iSurname));
		}

		return authors;
	}

	private List<String> generateNames() {
		List<String> clientNames = new ArrayList<>();
		Random random = new Random();

		while (clientNames.size() < TEST_DATA_SIZE) {
			int iName = random.nextInt(names.size());
			int iSurname = random.nextInt(surnames.size());
			int iPatronymic = random.nextInt(patronymics.size());
			clientNames.add(surnames.get(iSurname) + " " + names.get(iName) + " " + patronymics.get(iPatronymic));
		}

		return clientNames;
	}

	private List<LocalDate> generateBirthDays() {
		Set<LocalDate> birthDays = new HashSet<>();
		while (birthDays.size() < TEST_DATA_SIZE) {
			birthDays.add(generateRandomDate(1960, 2024));
		}
		return new ArrayList<>(birthDays);
	}

	private List<String> generateUniqueTitles() {
		Set<String> uniqueTitles = new HashSet<>();
		Random random = new Random();

		while (uniqueTitles.size() < TEST_DATA_SIZE) {
			int numNouns = random.nextInt(3) + 1;  // Выбираем 1, 2 или 3 существительных

			// Формируем название из случайных существительных
			StringBuilder title = new StringBuilder();
			for (int i = 0; i < numNouns; i++) {
				String noun = nouns.get(random.nextInt(nouns.size()));
				if (i > 0) {
					title.append(" и ");
				}
				title.append(noun);
			}

			uniqueTitles.add(title.toString());
		}

		return new ArrayList<>(uniqueTitles);
	}

	private List<String> generateUniqueISBNs() {
		HashSet<String> uniqueISBNs = new HashSet<>();

		while (uniqueISBNs.size() < TEST_DATA_SIZE) {
			String isbn = generateRandomISBN();
			uniqueISBNs.add(isbn);
		}

		return new ArrayList<>(uniqueISBNs);
	}

	private String generateRandomISBN() {
		Random random = new Random();

		// Генерация первых 12 цифр случайным образом
		StringBuilder isbn = new StringBuilder();
		for (int i = 0; i < 12; i++) {
			isbn.append(random.nextInt(10)); // случайная цифра от 0 до 9
		}

		// Вычисление контрольной цифры (13-я цифра)
		int sum = 0;
		for (int i = 0; i < 12; i++) {
			int digit = Character.getNumericValue(isbn.charAt(i));
			if (i % 2 == 0) {
				sum += digit; // нечётные позиции (счёт с 0)
			} else {
				sum += digit * 3; // чётные позиции
			}
		}
		int controlDigit = (10 - sum % 10) % 10;

		// Добавляем контрольную цифру
		isbn.append(controlDigit);

		return isbn.toString();
	}

	private LocalDate generateRandomDate(int startYear, int endYear) {
		Random random = new Random();
		int year = random.nextInt(endYear - startYear + 1) + startYear;
		int month = random.nextInt(12) + 1;
		int dayOfMonth;
		if (month == 2) {
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
				dayOfMonth = random.nextInt(29) + 1; // Високосный год
			} else {
				dayOfMonth = random.nextInt(28) + 1; // Невисокосный год
			}
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			dayOfMonth = random.nextInt(30) + 1;
		} else {
			dayOfMonth = random.nextInt(31) + 1;
		}

		return LocalDate.of(year, month, dayOfMonth);
	}

}

