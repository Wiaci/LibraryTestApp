package testapp.LibraryTestApp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testapp.LibraryTestApp.model.Client;

import java.util.List;

/**
 * Репозиторий для работы с клиентами
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	Page<Client> findAll(Pageable pageable);
	List<Client> findByFullNameContainingIgnoreCase(String fullName);
}
