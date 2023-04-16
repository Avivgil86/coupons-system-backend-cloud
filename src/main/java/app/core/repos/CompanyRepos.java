package app.core.repos;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.core.entity.Company;

@Repository
public interface CompanyRepos extends JpaRepository<Company, Integer> {

	boolean existsByName(String name);

	boolean existsByEmail(String email);

	boolean existsByEmailAndPassword(String email, String password);

	Optional<Company> findByEmail(String email);
	Optional<Company> findByEmailIgnoreCaseAndPassword(String email, String password);

}
