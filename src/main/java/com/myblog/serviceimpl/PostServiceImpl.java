package com.myblog.serviceimpl;

import com.myblog.entities.Category;
import com.myblog.entities.Post;
import com.myblog.entities.User;
import com.myblog.exceptions.ResourceNotFoundException;
import com.myblog.payloads.PostDto;
import com.myblog.payloads.PostResponse;
import com.myblog.repository.CategoryRepository;
import com.myblog.repository.PostRepository;
import com.myblog.repository.UserReposiotry;
import com.myblog.service.PostService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserReposiotry userRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Long userId, Long categoryId) {
		
	 User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user id",userId));
		
	 Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
		
     Post post= this.modelMapper.map(postDto, Post.class);
     post.setImageName("default.png");
     post.setAddedDate(new Date());
     post.setUser(user);
     post.setCategory(category);
     
     Post newPost = this.postRepo.save(post);
     
     return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Long postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedpost = this.postRepo.save(post);
		return this.modelMapper.map(updatedpost, PostDto.class);
	}

	@Override
	public void deletePost(Long postId) {
         Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post Id", postId));
	      this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
	/*	if(sortDir.equalsIgnoreCase("asc")) {
			sort= Sort.by(sortBy).ascending();
		}else {
			sort= Sort.by(sortBy).descending();
		}*/
		
		
	Pageable p = PageRequest.of(pageNumber, pageSize, sort);
	Page<Post> pagePost = this.postRepo.findAll(p);
          
	      List<Post> allPosts = pagePost.getContent();
        
          List<PostDto> postDtos = allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
            
            PostResponse postResponse = new PostResponse();
            postResponse.setContent(postDtos);
            postResponse.setPageNumber(pagePost.getNumber());
            postResponse.setPageSize(pagePost.getSize());
            postResponse.setTotalElements(pagePost.getTotalElements());
            postResponse.setTotalPages(pagePost.getTotalPages());
            postResponse.setLastPage(pagePost.isLast());
            return postResponse;
	}

	@Override
	public PostDto getPostById(Long postId) {
    Post post = this.postRepo.findById(postId).orElseThrow(()-> new  ResourceNotFoundException("Post","post id", postId))	;	
return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Long categoryId) {
		Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "category id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Long userId) {
        User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "user id", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.searchByTitle("%" + keyword + "%");
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}
	
	
	
	

}