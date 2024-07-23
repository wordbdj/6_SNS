package com.sns.like.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sns.like.domain.Like;

@Mapper
public interface LikeMapper {

	public Like selectLikeByUserIdPostId(
			@Param("userId")int userId,
			@Param("postId")int postId);
	
	public List<Like> selectLikeByPostId(
			@Param("postId")int postId);
	
	public void insertLike(
			@Param("userId")int userId,
			@Param("postId")int postId);
	
	public void deleteLike(
			@Param("userId")int userId,
			@Param("postId")int postId);
	
}
