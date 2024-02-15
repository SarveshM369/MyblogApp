package com.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myblog.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
