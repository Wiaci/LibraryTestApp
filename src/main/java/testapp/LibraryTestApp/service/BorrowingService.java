package testapp.LibraryTestApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import testapp.LibraryTestApp.dto.BorrowingInfoDto;
import testapp.LibraryTestApp.model.Borrowing;
import testapp.LibraryTestApp.repository.BorrowingRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с заказами
 */
@Service
@RequiredArgsConstructor
public class BorrowingService {

	private final BorrowingRepository borrowingRepository;

	/**
	 * Вернуть страницу из списка заказов
	 */
	public Page<Borrowing> findAll(Pageable pageable) {
		return borrowingRepository.findAll(pageable);
	}

	/**
	 * Вернуть заказ по id
	 */
	public Borrowing findById(Long id) {
		return borrowingRepository.findById(id).orElseGet(Borrowing::new);
	}

	/**
	 * Сохранить заказ
	 */
	public void save(Borrowing borrowing) {
		borrowingRepository.save(borrowing);
	}

	/**
	 * Удалить заказ по id
	 */
	public void deleteById(Long id) {
		borrowingRepository.deleteById(id);
	}

	/**
	 * Получить информацию о всех бронированиях
	 */
	public List<BorrowingInfoDto> getAllBorrowingInfo() {
		return borrowingRepository.findAll()
			.stream()
			.map(BorrowingInfoDto::new)
			.collect(Collectors.toList());
	}

}
