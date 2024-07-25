package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.mapper.LikeMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LikeBO {

	@Autowired
	private LikeMapper likeMapper;
	
	// input: userId, postId	output: X
	public void likeToggle(int userId, int postId) {
		
		// 조회
		
		int like = likeMapper.selectLikeCountByPostIdOrUserId( postId, userId);
		
		// 여부 => 삭제 or 추가
		if (like > 0) {
			likeMapper.deleteLike(userId, postId);
		} else {
			likeMapper.insertLike(userId, postId);
		}
		
	}
	
	public int getlikeCountByPostId(int postId) {
		
		return likeMapper.selectLikeCountByPostIdOrUserId(postId , null);
		
	}
	
	public boolean filledLikeByPostIdUserId(Integer userId, int postId) {
		
		if (userId == null) {
			return false;
		}
		
		// 로그인이면 : 1. 행이 있으면(1) true 		2. 없으면(0) false
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) == 1 ? true : false;
		
	}
	
	public void deleteLikeByPostId(int postId) {
		
		likeMapper.deleteLikeByPostId(postId);
	}
	
}
