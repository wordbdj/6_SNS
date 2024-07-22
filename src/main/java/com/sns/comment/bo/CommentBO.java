package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentView;
import com.sns.comment.mapper.CommentMapper;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@Service
public class CommentBO {

	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserBO userBO;
	
	public void addComment(int postId, int userId, String content) {
		
		commentMapper.insertComment(postId, userId, content);
	}
	
	public List<Comment> getCommentListbyPostId(int postId) {
		
		return commentMapper.selectCommentListbyPostId(postId);
		
	}

	public List<Comment> getCommentList() {
		
		return commentMapper.selectCommentList();
	}
	
	
	
	// input: 글번호		output:List<CommentView>
	
	public List<CommentView> generateCommentViewListByPostId(int postId) {
		
		List<CommentView> commentViewList = new ArrayList<>();
		
		// 댓글들 가져옴
		List<Comment> commentList = commentMapper.selectCommentListbyPostId(postId);
		
		// 반복문 순회 => comment 0< commentView -> list에 담음
		for (Comment comment : commentList) {
			
			// 댓글 1개
			CommentView commentView = new CommentView();
			commentView.setComment(comment);
			
			// 댓글쓴이
			UserEntity user = userBO.getUserEntityById(commentView.getComment().getUserId());
			commentView.setUser(user);
			
			// !!!!!! list에 넣기
			commentViewList.add(commentView);
		}
		
		
		return commentViewList;
		 
	}
}
