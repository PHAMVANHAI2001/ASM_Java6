package com.eshop.jpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshop.entities.Authority;
import com.eshop.entities.User;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer>{
	List<Authority> findByUser(User user);
	List<Authority> findAllByRoleId(int roleId);
}
