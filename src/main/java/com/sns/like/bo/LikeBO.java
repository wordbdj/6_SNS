package com.sns.like.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.domain.Like;
import com.sns.like.mapper.LikeMapper;

@Service
public class LikeBO {

	@Autowired
	private LikeMapper likeMapper;
	
	// input: userId, postId	output: X
	public void likeToggle(int userId, int postId) {
		
		// 조회
		
		Like like = likeMapper.selectLikeByUserIdPostId(userId, postId);
		
		// 여부 => 삭제 or 추가
		if (like != null) {
			likeMapper.deleteLike(userId, postId);
		} else {
			likeMapper.insertLike(userId, postId);
		}
		
	}
	
	public int likeCount(int postId) {
		
		List<Like> like = likeMapper.selectLikeByPostId(postId);
		int count = like.size();
		
		return count;
		
	}
	
	public boolean filledLike(int userId, int postId) {
		
		Like like = likeMapper.selectLikeByUserIdPostId(userId, postId);
		
		if (like != null) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
