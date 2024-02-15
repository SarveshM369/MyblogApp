package com.myblog.service;

import com.myblog.payloads.CommentDto;

public interface CommentService {

	
	CommentDto createComment(CommentDto commentDto, Long postId);
	
	void deletedComment(Long commentId);
}