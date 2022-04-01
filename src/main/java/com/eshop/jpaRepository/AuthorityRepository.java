package com.eshop.jpaRepository;

import com.eshop.entities.Authority;
import com.eshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	List<Authority> findByUser(User user);
}
