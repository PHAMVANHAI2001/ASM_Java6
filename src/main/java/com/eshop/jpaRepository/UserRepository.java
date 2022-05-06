package com.eshop.jpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eshop.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsernameAndPassword(String username, String password);

	User findByUsername(String username);

	User findByEmail(String email);

	Boolean existsByUsernameOrEmail(String username, String email);

	Boolean existsByEmail(String email);

	Boolean existsByUsername(String username);

	@Query("SELECT DISTINCT ar.user FROM Authority ar WHERE ar.role.id in (2,3)")
	List<User> getAdministrators();

	@Query("SELECT DISTINCT ar.user FROM Authority ar WHERE ar.role.id in (1)")
	List<User> getCustomers();

	@Query(value = "UPDATE [User] SET [Enabled] = 0 WHERE [Username] = ?", nativeQuery = true)
	void disabled(String username);

	@Query(value = "UPDATE [User] SET [Enabled] = 1 WHERE [Username] = ?", nativeQuery = true)
	void enabled(String username);
}
