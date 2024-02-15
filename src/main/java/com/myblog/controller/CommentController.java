package com.myblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.payloads.ApiResponse;
import com.myblog.payloads.CommentDto;
import com.myblog.service.CommentService;


@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
     private CommentService commentService;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Long postId) {
		CommentDto createComment = this.commentService.createComment(commentDto, postId);
		
		return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId) {
		this.commentService.deletedComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment is deleted Successfully!!", true), HttpStatus.OK);
	}

}