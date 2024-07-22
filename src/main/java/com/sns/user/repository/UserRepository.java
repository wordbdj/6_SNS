package com.sns.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	// JPQL
	public UserEntity findByLoginId(String loginId);
	public UserEntity findByLoginIdAndPassword(String loginId, String password);
	public UserEntity findById(int id);
}
