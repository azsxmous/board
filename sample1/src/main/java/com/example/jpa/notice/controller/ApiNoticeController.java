package com.example.jpa.notice.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.notice.entity.Notice;
import com.example.jpa.notice.model.NoticeInput;
import com.example.jpa.notice.model.NoticeModel;
import com.example.jpa.notice.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ApiNoticeController {
	
//	// 기존 방식 
//	@Autowired
//	NoticeRepository noticeRepository;
//	
//	// 요즘 방식
//	// 생성자에서 주입받음
//	public ApiNoticeController(NoticeRepository noticeRepository) {
//		this.noticeRepository = noticeRepository;
//	}
	
	// @RequiredArgsConstructor 사용
	private NoticeRepository noticeRepository;
	

	@GetMapping("/api/notice")
	public String noticeString() {
		return "공지사항입니다.";
	}
	
	@GetMapping("/api/notice2")
	public NoticeModel notice() {
		
		// Date 생성
		LocalDateTime regDate = LocalDateTime.of(2022,2,22,0,0);
		// 게시글ID = 1, 제목 = 공지사항입니다, 내용 = 공지사항 내용입니다, 등록일 = 2022-2-22
		NoticeModel notice = new NoticeModel();
		notice.setId(1);
		notice.setTitle("공지사항입니다.");
		notice.setContents("공지사항 내용입니다.");
		notice.setRegDate(regDate);
		
		return notice;
	}
	
	@GetMapping("/api/noticelist")
	public List<NoticeModel> noticeList(){

		List<NoticeModel> notice = new ArrayList<>();
		
		NoticeModel notice1 = new NoticeModel();
		notice1.setId(1);
		notice1.setTitle("공지사항입니다.");
		notice1.setContents("공지사항내용입니다.");
		notice1.setRegDate(LocalDateTime.of(2021, 1, 30, 0, 0));
		notice.add(notice1);
		
		NoticeModel notice2 = new NoticeModel();
		notice2.setId(2);
		notice2.setTitle("두 번째 공지사항입니다.");
		notice2.setContents("두 번째 공지사항내용입니다.");
		notice2.setRegDate(LocalDateTime.of(2021, 1, 31, 0, 0));
		notice.add(notice2);
		
		return notice;
	}
	
	// 빌더패턴(@Builder) 이용
	@GetMapping("/api/noticelist2")
	public List<NoticeModel> noticeList2(){

		List<NoticeModel> notice = new ArrayList<>();
		
		notice.add(NoticeModel.builder()
				.id(1)
				.title("공지사항입니다.")
				.contents("공지사항내용입니다.")
				.regDate(LocalDateTime.of(2021, 1, 30, 0, 0))
				.build());

		notice.add(NoticeModel.builder()
				.id(2)
				.title("두 번째 공지사항입니다.")
				.contents("두 번째 공지사항내용입니다.")
				.regDate(LocalDateTime.of(2021, 1, 31, 0, 0))
				.build());

		
		return notice;
	}
	
	@GetMapping("/api/noticelist3")
	public List<NoticeModel> noticeList3(){

		List<NoticeModel> noticeList = new ArrayList<>();
		return noticeList;
	}
	
	@GetMapping("/api/notice/count")
	public int noticeCount() {
		return 10;
	}
	
	// = @RequestMapping(value="/api/notice", method = RequestMethod.POST)
	// POST 이기 때문에 기존에 있는 GET 방식과 url 동일해도 괜찮음
	@PostMapping("/api/notice")
	// @RequestParam : 명시적으로 파라미터로 값을 받는다고 표시
	public NoticeModel addNotice(@RequestParam String title, @RequestParam String contents) {
		NoticeModel notice = NoticeModel.builder()
				.id(1)
				.title(title)
				.contents(contents)
				.regDate(LocalDateTime.now())
				.build();
		
		return notice;
	}
	
	@PostMapping("/api/notice2")
	public NoticeModel addNotice2(NoticeModel noticeModel) {

		noticeModel.setId(2);
		noticeModel.setRegDate(LocalDateTime.now());
		
		return noticeModel;
	}
	
	@PostMapping("/api/addnotice3")
	// json 형태로 받기 때문에 @RequestBody라고 명시해야 함
	public NoticeModel addNotice3(@RequestBody NoticeModel noticeModel) {

		noticeModel.setId(3);
		noticeModel.setRegDate(LocalDateTime.now());
		
		return noticeModel;
	}
	
	@PostMapping("/api/addnotice4")
	public Notice addNotice4(@RequestBody NoticeInput noticeInput) {
		
		Notice notice = Notice.builder()
				.title(noticeInput.getTitle())
				.contents(noticeInput.getContents())
				.regDate(LocalDateTime.now())
				.build();
		noticeRepository.save(notice);
		return notice;
	}
}
