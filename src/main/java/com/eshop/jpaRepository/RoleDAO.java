package com.eshop.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.entities.Role;

public interface RoleDAO extends JpaRepository<Role, Integer>{

}
