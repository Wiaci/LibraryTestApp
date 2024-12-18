package testapp.LibraryTestApp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testapp.LibraryTestApp.model.Book;

import java.util.List;

/**
 * Репозиторий для работы с книгами
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	Page<Book> findAll(Pageable pageable);
	List<Book> findByTitleContainingIgnoreCase(String title);
}
