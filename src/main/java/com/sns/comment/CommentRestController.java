package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Comment;

import com.sns.comment.bo.CommentBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	
	@Autowired
	private CommentBO commentBO;
	
	@PostMapping("/create") 
	public Map<String, Object> create(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		
		if (userId == null) {
			result.put("code", 403);
			result.put("error_message", "로그인을 해주시길 바랍니다.");
			return result;
		}
		
		commentBO.addComment(postId, userId, content);
		
		
		result.put("code", 200);
		result.put("result", "성공");
		return result;
		
		
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("commentId")int commentId,
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		
		if (userId == null) {
			result.put("code", 403);
			result.put("error_message", "로그인을 해주시길 바랍니다.");
			return result;
		}
		
		commentBO.deleteCommentById(commentId);
		
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
		
	}
	

}
