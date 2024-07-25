package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.Entity.PostEntity;
import com.sns.post.repository.PostRepository;


@Service
public class PostBO {
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	public List<PostEntity> getPostEntityList() {
		
		return postRepository.findAllByOrderByIdDesc();
	}
	
	public void addPost(int userId, String userLoginId, String content, MultipartFile file) {

		String imagePath = null;
		
		if (file != null) {
			// 업로드 할 이미지가 있을 때에만 업로드
			imagePath = fileManagerService.uploadFile(file, userLoginId);
		}
		
		postRepository.save(PostEntity.builder()
				.userId(userId)
				.content(content)
				.imagePath(imagePath)
				.build());
		
		
	}
	
	public void deletePost(int postId) {
		
		// 좋아요 삭제
		likeBO.deleteLikeByPostId(postId);
		
		// 댓글 삭제
		commentBO.deleteCommentByPostId(postId);
		
		
		PostEntity post = postRepository.findById(postId).orElse(null);
		// 글 삭제
		if (post != null) {
		    postRepository.delete(post);
		}
		
		// 이미지파일 삭제
		String imagePath = post.getImagePath();
		fileManagerService.deleteFile(imagePath);
		
		
		
	}
	
}
