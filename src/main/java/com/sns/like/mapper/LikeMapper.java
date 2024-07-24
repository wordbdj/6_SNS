package com.sns.like.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sns.like.domain.Like;

@Mapper
public interface LikeMapper {

//	public int selectLikeByUserIdPostId(
//			@Param("userId") int userId,
//			@Param("postId") int postId);
	
//	public int selectLikeCountByPostId(
//			int postId);
	
	// 카운트 쿼리를 하나로 합친다.
	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId,
			@Param("userId") Integer userId);
	
	public void insertLike(
			@Param("userId") int userId,
			@Param("postId") int postId);
	
	public void deleteLike(
			@Param("userId") int userId,
			@Param("postId") int postId);
	
}
