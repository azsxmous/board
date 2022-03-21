package com.example.jpa.notice.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.notice.entity.Notice;
import com.example.jpa.notice.exception.NoticeNotFoundException;
import com.example.jpa.notice.model.NoticeInput;
import com.example.jpa.notice.model.NoticeModel;
import com.example.jpa.notice.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ApiNoticeController {
	
//	// ���� ��� 
//	@Autowired
//	NoticeRepository noticeRepository;
//	
//	// ���� ���
//	// �����ڿ��� ���Թ���
//	public ApiNoticeController(NoticeRepository noticeRepository) {
//		this.noticeRepository = noticeRepository;
//	}
	
	// @RequiredArgsConstructor ���
	// final �Ⱦ��� ������!
	private final NoticeRepository noticeRepository;
	

	@GetMapping("/api/notice")
	public String noticeString() {
		return "���������Դϴ�.";
	}
	
	@GetMapping("/api/notice2")
	public NoticeModel notice() {
		
		// Date ����
		LocalDateTime regDate = LocalDateTime.of(2022,2,22,0,0);
		// �Խñ�ID = 1, ���� = ���������Դϴ�, ���� = �������� �����Դϴ�, ����� = 2022-2-22
		NoticeModel notice = new NoticeModel();
		notice.setId(1);
		notice.setTitle("���������Դϴ�.");
		notice.setContents("�������� �����Դϴ�.");
		notice.setRegDate(regDate);
		
		return notice;
	}
	
	@GetMapping("/api/noticelist")
	public List<NoticeModel> noticeList(){

		List<NoticeModel> notice = new ArrayList<>();
		
		NoticeModel notice1 = new NoticeModel();
		notice1.setId(1);
		notice1.setTitle("���������Դϴ�.");
		notice1.setContents("�������׳����Դϴ�.");
		notice1.setRegDate(LocalDateTime.of(2021, 1, 30, 0, 0));
		notice.add(notice1);
		
		NoticeModel notice2 = new NoticeModel();
		notice2.setId(2);
		notice2.setTitle("�� ��° ���������Դϴ�.");
		notice2.setContents("�� ��° �������׳����Դϴ�.");
		notice2.setRegDate(LocalDateTime.of(2021, 1, 31, 0, 0));
		notice.add(notice2);
		
		return notice;
	}
	
	// ��������(@Builder) �̿�
	@GetMapping("/api/noticelist2")
	public List<NoticeModel> noticeList2(){

		List<NoticeModel> notice = new ArrayList<>();
		
		notice.add(NoticeModel.builder()
				.id(1)
				.title("���������Դϴ�.")
				.contents("�������׳����Դϴ�.")
				.regDate(LocalDateTime.of(2021, 1, 30, 0, 0))
				.build());

		notice.add(NoticeModel.builder()
				.id(2)
				.title("�� ��° ���������Դϴ�.")
				.contents("�� ��° �������׳����Դϴ�.")
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
	// POST �̱� ������ ������ �ִ� GET ��İ� url �����ص� ������
	@PostMapping("/api/notice")
	// @RequestParam : ��������� �Ķ���ͷ� ���� �޴´ٰ� ǥ��
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
	// json ���·� �ޱ� ������ @RequestBody��� ����ؾ� ��
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
	
	@PostMapping("/api/addnotice5")
	public Notice addNotice5(@RequestBody NoticeInput noticeInput) {
		
		Notice notice = Notice.builder()
				.title(noticeInput.getTitle())
				.contents(noticeInput.getContents())
				.regDate(LocalDateTime.now())
				.hits(0)
				.likes(0)
				.build();
		Notice resultNotice = noticeRepository.save(notice);
		return resultNotice;
	}
	
	// path�� ������ �ޱ� ������ @PathVariable ���
	@GetMapping("/api/notice/{id}")
	public Notice notice(@PathVariable Long id) {
		Optional<Notice> notice = noticeRepository.findById(id);
		
		if(notice.isPresent()) {
			return notice.get();
		}
		
		return null;
	}
	
	@PutMapping("/api/notice/{id}")
	public void updateNotice(@PathVariable Long id, @RequestBody NoticeInput noticeInput) {
		
		Optional<Notice> notice = noticeRepository.findById(id);
		if(notice.isPresent()) {
			notice.get().setTitle(noticeInput.getTitle());
			notice.get().setContents(noticeInput.getContents());
			notice.get().setUpdateDate(LocalDateTime.now());
			noticeRepository.save(notice.get());
		}
	}
	
	// �����ڵ鷯
	@ExceptionHandler(NoticeNotFoundException.class)
	public ResponseEntity<String> handlerNoticeNotFoundException(NoticeNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/api/updatenotice/{id}")
	public void updateNotice2(@PathVariable Long id, @RequestBody NoticeInput noticeInput) {
		
		/*
		Optional<Notice> notice = noticeRepository.findById(id);
		
		// ���� �߻�
		if(!notice.isPresent()) {
			throw new NoticeNotFoundException("�������׿� ���� �������� �ʽ��ϴ�.");
		}
		
		// �������� ���� ���� ��
		notice.get().setTitle(noticeInput.getTitle());
		notice.get().setContents(noticeInput.getContents());
		notice.get().setUpdateDate(LocalDateTime.now());
		noticeRepository.save(notice.get());
		*/
		
		// ���� ����
		Notice notice = noticeRepository.findById(id)
				.orElseThrow(() -> new NoticeNotFoundException("�������׿� ���� �������� �ʽ��ϴ�."));
		
		notice.setTitle(noticeInput.getTitle());
		notice.setContents(noticeInput.getContents());
		notice.setUpdateDate(LocalDateTime.now());
		noticeRepository.save(notice);
		
	}
}
