package app.core.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.core.entity.Company;
import app.core.entity.Customer;

@Repository
public interface CustomerRepos extends JpaRepository<Customer, Integer> {

	List<Customer> findByFirstName(String firstName);

	Optional<Customer> findByEmail(String Email);

	boolean existsByEmail(String email);

	Optional<Customer> findByEmailIgnoreCaseAndPassword(String email, String password);

	boolean existsByEmailAndPassword(String email, String password);
}
