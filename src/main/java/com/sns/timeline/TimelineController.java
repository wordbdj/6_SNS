package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.post.Entity.PostEntity;
import com.sns.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;


@Controller
public class TimelineController {
	
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/timeline/timeline-view")
	public String timelineView(Model model) {
		
	
		// DB Check - list<PostEntity>
		List<PostEntity> postList = postBO.getPostEntityList();
		
		// model
		model.addAttribute("postList", postList);
		
		return "timeline/timeline";
	}
}
