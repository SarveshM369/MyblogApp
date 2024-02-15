package com.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myblog.entities.User;

public interface UserReposiotry extends JpaRepository<User,Long> {

	User  findByUsername(String username);

}
