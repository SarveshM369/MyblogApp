package com.myblog.serviceimpl;

import com.myblog.entities.Comment;
import com.myblog.entities.Post;
import com.myblog.exceptions.ResourceNotFoundException;
import com.myblog.payloads.CommentDto;
import com.myblog.repository.CommentRepository;
import com.myblog.repository.PostRepository;
import com.myblog.service.CommentService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl  implements CommentService{

	@Autowired
	private CommentRepository commentrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private PostRepository postRepo;
	
	@Override
	public CommentDto   createComment(CommentDto commentDto, Long postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post Id", postId));
		  Comment comment = this.modelmapper.map(commentDto, Comment.class);
		  comment.setPost(post);
		  Comment saveedComment = this.commentrepo.save(comment);
		return this.modelmapper.map(saveedComment, CommentDto.class);
	}

	@Override
	public void deletedComment(Long commentId) {
		 Comment com= this.commentrepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "CommentId", commentId));
		this.commentrepo.delete(com);
	}

}