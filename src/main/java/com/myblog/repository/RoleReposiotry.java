package com.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myblog.entities.Role;

public interface RoleReposiotry extends JpaRepository<Role, Long>{

}
