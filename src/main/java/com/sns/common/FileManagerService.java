package com.sns.common;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileManagerService {
	
	// 실제 업로드가 된 이미지가 저장될 서버의 경로
	public static final String FILE_UPLOAD_PATH = "D:\\전지성\\6_Spring_Project\\SNS\\sns_workspace\\sns_1\\images/";
	
	// input:MultipartFile, userLoginId
	// output:String(이미지 경로)
	public String uploadFile(MultipartFile file, String loginId) {
		
		
		String directoryName = loginId + "_" + System.currentTimeMillis();
		// E:\\신보람\\6_spring_project\\SNS\\sns_workspace\\sns_1\\images/aaaa_17348493489/
		String filePath = FILE_UPLOAD_PATH + directoryName + "/";
		
		// 폴더 생성
		File directory = new File(filePath);
		if (directory.mkdir() == false) {
			// 폴더 생성 시 실패하면 경로를 null로 리턴
			return null;
		}
		
		// 파일 업로드
		try {
			byte[] bytes = file.getBytes();
			//★★★★★★★ 한글명으로 된 이미지는 업로드 불가하므로 나중에 영문자로 바꾸기
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return null; // 이미지 업로드 실패시 경로 null
		}
		
		// 파일업로드가 성공하면 이미지 url path를 리턴
		// 주소는 이렇게 될 것이다.(예언)
		// /images/aaaa_17348493489/sun.png
		return "/images/" + directoryName + "/" + file.getOriginalFilename();
	}
	
	public void deleteFile(String imagePath) {	// /images/aaaa_1721287755457/spring_mvc.png
		// D:\전지성\6_Spring_Project\MEMO\memo_workspace\Memo\images\aaaa_1721287821082
		// D:\\전지성\\6_Spring_Project\\MEMO\\memo_workspace\\Memo\\images/images/aaaa_1721287755457/spring_mvc.png
		// 주소가 겹치는 /images/를 지운다
		Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", ""));
		
		// 삭제할 이미지가 존재하는가?
		if(Files.exists(path)) {
			// 이미지 삭제
			try {
				Files.delete(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.info("[FileManagerService 파일삭제] 삭제 실패. path:{}", path.toString());
				return;
			}
			
			
			// 폴더 (디렉토리) 삭제
			path = path.getParent();
			if(Files.exists(path)) {
				try {
					Files.delete(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.info("[FileManagerService 파일삭제] 디렉토리 삭제 실패. path:{}", path.toString());
				}
			}
		}
	}
	
	
}