package com.myblog.service;

import java.util.List;

import com.myblog.payloads.PostDto;
import com.myblog.payloads.PostResponse;



public interface PostService {

	
	//create
	
	PostDto createPost(PostDto postDto, Long userId, Long categoryId);
	
	//update
	
	PostDto updatePost(PostDto postDto, Long postId);
	
	//delete
	
	void deletePost(Long postId);
	
	//get all posts
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize , String sortBy, String sortDir);
	
	//get single post
	
	PostDto getPostById(Long postId);
	
	//get all posts by category
	
	List<PostDto> getPostsByCategory(Long categoryId);
	
	//get all posts by user
	List<PostDto> getPostsByUser(Long userId);
	
	//search posts
	List<PostDto> searchPosts(String keyword);
	
	
}
