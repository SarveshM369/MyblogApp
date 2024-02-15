package com.myblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myblog.entities.Category;
import com.myblog.entities.Post;
import com.myblog.entities.User;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByCategory(Category cat);

	List<Post> findByUser(User user);

	@Query("select p from Post p where p.title like:key")
	List<Post> searchByTitle(@Param("key") String title);

}
