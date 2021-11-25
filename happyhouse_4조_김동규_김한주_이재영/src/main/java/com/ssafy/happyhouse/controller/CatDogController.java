package com.ssafy.happyhouse.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.happyhouse.model.FileInfoDto;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.CatDogDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.CatDogService;
import com.ssafy.util.PageNavigation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/catdog")
@Api("캣독 컨트롤러")
public class CatDogController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private CatDogService catDogService;
	
	@Autowired
	private ServletContext servletContext;
	
	
	@PostMapping("/register")
	public ResponseEntity<String> writeArticle(
			@RequestParam("userid") @ApiParam(value = "게시글 정보.", required = true) String userid, 
			@RequestParam("subject") @ApiParam(value = "게시글 정보.", required = true) String subject, 
			@RequestParam("content") @ApiParam(value = "게시글 정보.", required = true) String content, 
			@RequestParam("files") MultipartFile[] files ) throws Exception {
		
		
		logger.info("registerArticle - 호출" + userid +" "+subject+" "+files.length);
		
		CatDogDto catDogDto=new CatDogDto();
		catDogDto.setUserid(userid);
		catDogDto.setSubject(subject);
		catDogDto.setContent(content);
		
		
		System.out.println(files[0].getName());
		
//		System.out.println(catDogDto); 

//		FileUpload 관련 설정.
		logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
		if (!files[0].isEmpty()) {
//			String realPath = servletContext.getRealPath("/upload");
			String realPath = servletContext.getRealPath("/resources/img");
//			String realPath2 = servletContext.getRealPath("/resources/img");
			String today = new SimpleDateFormat("yyMMdd").format(new Date());
			String saveFolder = realPath + File.separator + today;
//			String saveFolder2 = realPath2 + File.separator + today;
			logger.debug("저장 폴더 : {}", saveFolder);
			File folder = new File(saveFolder);
//			File folder2 = new File(saveFolder2);
			if (!folder.exists())
				folder.mkdirs();
//			if (!folder2.exists())
//				folder2.mkdirs();
			List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
//			List<FileInfoDto> fileInfos2 = new ArrayList<FileInfoDto>();
			for (MultipartFile mfile : files) {
				FileInfoDto fileInfoDto = new FileInfoDto();
//				FileInfoDto fileInfoDto2 = new FileInfoDto();
				String originalFileName = mfile.getOriginalFilename();
//				String originalFileName2 = mfile.getOriginalFilename();
				if (!originalFileName.isEmpty()) {
					String saveFileName = UUID.randomUUID().toString()
							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
					fileInfoDto.setSaveFolder(today);
					fileInfoDto.setOriginFile(originalFileName);
					fileInfoDto.setSaveFile(saveFileName);
					logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
					mfile.transferTo(new File(folder, saveFileName));
				}
//				if (!originalFileName2.isEmpty()) {
//					String saveFileName2 = UUID.randomUUID().toString()
//							+ originalFileName2.substring(originalFileName2.lastIndexOf('.'));
//					fileInfoDto2.setSaveFolder(today);
//					fileInfoDto2.setOriginFile(originalFileName2);
//					fileInfoDto2.setSaveFile(saveFileName2);
//					logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName2);
//					mfile.transferTo(new File(folder, saveFileName2));
//				}
				fileInfos.add(fileInfoDto);
//				fileInfos2.add(fileInfoDto2);
			}
			catDogDto.setFileInfos(fileInfos);
		}
		if (catDogService.writeArticle(catDogDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> list(@RequestParam Map<String, String> map) throws Exception {
		Map<String,Object> resultMap = new HashMap<>();

		String spp = map.get("spp"); // size per page (페이지당 글갯수)
		map.put("spp", spp != null ? spp : "10");
		List<CatDogDto> list = catDogService.listArticle(map);
		System.out.println("**************************************");
		System.out.println("리스트 GET 호출시 무엇이 담겨있나요?? " + list.toString());
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("**************************************");
		PageNavigation pageNavigation = catDogService.makePageNavigation(map);
		resultMap.put("articles", list);
		resultMap.put("navigation", pageNavigation);
		resultMap.put("key", map.get("key"));
		resultMap.put("word", map.get("word"));
		
		return new ResponseEntity<Map<String, Object>>(resultMap,HttpStatus.OK);
	}
	 
	@GetMapping("/listfile")
	public ResponseEntity<Map<String, Object>> listImages(@RequestParam Map<String, String> map) throws Exception {
		Map<String,Object> resultMap = new HashMap<>();

		String spp = map.get("spp"); // size per page (페이지당 글갯수)
		map.put("spp", spp != null ? spp : "10");
		List<FileInfoDto> list = catDogService.listFile(map);
		System.out.println("**************************************");
		System.out.println("리스트 GET 호출시 무엇이 담겨있나요?? " + list.toString());
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("**************************************");
		PageNavigation pageNavigation = catDogService.makePageNavigation(map);
		resultMap.put("files", list);
		resultMap.put("navigation", pageNavigation);
		resultMap.put("key", map.get("key"));
		resultMap.put("word", map.get("word"));
		
		return new ResponseEntity<Map<String, Object>>(resultMap,HttpStatus.OK);
	}
	
	
}
