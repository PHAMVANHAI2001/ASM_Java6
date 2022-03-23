package com.eshop.jpaRepository;

import com.eshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	User findByUsernameAndPassword(String username,String password);
	User findByUsername(String username);
	User findByEmail(String email);
	Boolean existsByUsernameOrEmail(String username, String email);
	Boolean existsByEmail(String username);
	Boolean existsByUsername(String username);
}
