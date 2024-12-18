package testapp.LibraryTestApp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testapp.LibraryTestApp.model.Borrowing;

/**
 * Репозиторий для работы с заказами
 */
@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
	Page<Borrowing> findAll(Pageable pageable);
}
