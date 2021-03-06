package com.example.jpa.notice.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.notice.entity.Notice;
import com.example.jpa.notice.exception.AlreadyDeletedException;
import com.example.jpa.notice.exception.DuplicateNoticeException;
import com.example.jpa.notice.exception.NoticeNotFoundException;
import com.example.jpa.notice.model.NoticeDeleteInput;
import com.example.jpa.notice.model.NoticeInput;
import com.example.jpa.notice.model.NoticeModel;
import com.example.jpa.notice.model.ResponseError;
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
	// final 안쓰면 에러남!
	private final NoticeRepository noticeRepository;
	

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
/*	
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
	*/
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
	
	// path에 변수를 받기 때문에 @PathVariable 사용
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
	
	// 에러핸들러
	@ExceptionHandler(NoticeNotFoundException.class)
	public ResponseEntity<String> handlerNoticeNotFoundException(NoticeNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/api/updatenotice/{id}")
	public void updateNotice2(@PathVariable Long id, @RequestBody NoticeInput noticeInput) {
		
		Optional<Notice> notice = noticeRepository.findById(id);
		
		// 예외 발생
		if(!notice.isPresent()) {
			throw new NoticeNotFoundException("공지사항에 글이 존재하지 않습니다.");
		}
		
		// 공지사항 글이 있을 때
		notice.get().setTitle(noticeInput.getTitle());
		notice.get().setContents(noticeInput.getContents());
		notice.get().setUpdateDate(LocalDateTime.now());
		noticeRepository.save(notice.get());
		
		/*
		// 위와 동일
		Notice notice = noticeRepository.findById(id)
				.orElseThrow(() -> new NoticeNotFoundException("공지사항에 글이 존재하지 않습니다."));
		
		notice.setTitle(noticeInput.getTitle());
		notice.setContents(noticeInput.getContents());
		notice.setUpdateDate(LocalDateTime.now());
		noticeRepository.save(notice);
		*/
	}
	
	@PutMapping("/api/updatenotice2/{id}")
	public void updateNotice3(@PathVariable Long id, @RequestBody NoticeInput noticeInput) {
		Notice notice = noticeRepository.findById(id)
				.orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));
		notice.setTitle(noticeInput.getTitle());
		notice.setContents(noticeInput.getContents());
		notice.setUpdateDate(LocalDateTime.now());
		noticeRepository.save(notice);
		
	}
	
	@PatchMapping("/api/notice/{id}/hits")
	public void noticeHits(@PathVariable Long id) {
		Notice notice = noticeRepository.findById(id)
				.orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));
		notice.setHits(notice.getHits() + 1);
		
		noticeRepository.save(notice);
	}
	
	@DeleteMapping("/api/notice/{id}")
	public void deleteNotice(@PathVariable Long id) {
		
		Notice notice = noticeRepository.findById(id)
		.orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));
		
		noticeRepository.delete(notice);
	}
	
	// 에러핸들러2
	@ExceptionHandler(AlreadyDeletedException.class)
	public ResponseEntity<String> handlerAlreadyDeletedException(AlreadyDeletedException exception) {
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.OK);
	}
	
	@DeleteMapping("/api/notice2/{id}")
	public void deleteNotice2(@PathVariable Long id) {
		Notice notice = noticeRepository.findById(id)
				.orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));
		
		// 삭제여부
		if(notice.isDeleted()) {
			throw new AlreadyDeletedException("이미 삭제된 글입니다.");
		}
		
		notice.setDeleted(true);
		notice.setDeletedDate(LocalDateTime.now());
		
		noticeRepository.save(notice);
	}
	
	@DeleteMapping("/api/notice")
	public void deleteNoticeList(@RequestBody NoticeDeleteInput noticeDeleteInput) {

		List<Notice> noticeList = noticeRepository.findByIdIn(noticeDeleteInput.getIdList())
				.orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));
		
		noticeList.stream().forEach(e -> {
			e.setDeleted(true);
			e.setDeletedDate(LocalDateTime.now());
		});
		
		noticeRepository.saveAll(noticeList);
				
	}
	
	@DeleteMapping("/api/notice/all")
	public void deleteNoticeAll() {
		
		noticeRepository.deleteAll();
		
	}
	
	
	// 게시판 작성
	@PostMapping("/api/addnotice")
	public void addNotice(@RequestBody Notice noticeInput) {
		Notice notice = Notice.builder()
				.title(noticeInput.getTitle())
				.contents(noticeInput.getContents())
				.hits(0)
				.likes(0)
				.regDate(LocalDateTime.now())
				.build();
		
		noticeRepository.save(notice);
		
	}
	
	
	@PostMapping("/api/addnotice2")
	public ResponseEntity<Object> addNotice2(@RequestBody Notice noticeInput) {
		if(noticeInput.getTitle() == null ||
				noticeInput.getTitle().length() < 1 ||
				noticeInput.getContents() == null ||
				noticeInput.getContents().length() < 1) {
			return new ResponseEntity<>("입력값이 정확하지 않습니다.", HttpStatus.BAD_REQUEST);
		}
		
		// 정상적인 저장 로직	
		noticeRepository.save(Notice.builder()
				.title(noticeInput.getTitle())
				.contents(noticeInput.getContents())
				.hits(0)
				.likes(0)
				.regDate(LocalDateTime.now())
				.build());
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/api/adnotice3")
	public ResponseEntity<Object> adNotice3(@RequestBody @Valid NoticeInput noticeInput, Errors errors) {
		
		if(errors.hasErrors()) {
//			return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
			List<ResponseError> responseErrors = new ArrayList<>();
			
//			errors.getAllErrors().stream().forEach(e -> {
//				ResponseError responseError = new ResponseError();
//				responseError.setField(((FieldError)e).getField());
//				responseError.setMessage(e.getDefaultMessage());
//				responseErrors.add(responseError);
//			});
			
			errors.getAllErrors().stream().forEach(e -> {
				responseErrors.add(ResponseError.of((FieldError)e));
			});
			return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
		}
		// 정상적인 저장 로직	
		noticeRepository.save(Notice.builder()
				.title(noticeInput.getTitle())
				.contents(noticeInput.getContents())
				.hits(0)
				.likes(0)
				.regDate(LocalDateTime.now())
				.build());
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/api/notice/latest/{size}")
	public Page<Notice> noticeLatest(@PathVariable int size){
		
		Page<Notice> noticeList = 
		noticeRepository.findAll(PageRequest.of(0, size, Sort.Direction.DESC, "regDate"));
		
		return noticeList;
	}
	
	
	@ExceptionHandler(DuplicateNoticeException.class)
	public ResponseEntity handlerDuplicateNoticeException(DuplicateNoticeException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/api/adnotice4")
	public ResponseEntity<Object> adNotice4(@RequestBody NoticeInput noticeInput) {
		
		// 중복체크
		LocalDateTime checkDate = LocalDateTime.now().minusMinutes(1);
		
//		Optional<List<Notice>> noticeList = noticeRepository.findByTitleAndContentsAndRegDateIsGreaterThanEqual(
//				noticeInput.getTitle()
//				, noticeInput.getContents()
//				, checkDate);
//		if(noticeList.isPresent()) {
//			if(noticeList.get().size()>0) {
//				throw new DuplicateNoticeException("1분 이내에 등록된 동일한 공지사항이 존재합니다.");
//			}
//		}
		
		int noticeCount = noticeRepository.countByTitleAndContentsAndRegDateIsGreaterThanEqual(
				noticeInput.getTitle()
				, noticeInput.getContents()
				, checkDate);
		if(noticeCount > 0) {
			throw new DuplicateNoticeException("1분 이내에 등록된 동일한 공지사항이 존재합니다.");
		}
		
		noticeRepository.save(Notice.builder()
				.title(noticeInput.getTitle())
				.contents(noticeInput.getContents())
				.hits(0)
				.likes(0)
				.regDate(LocalDateTime.now())
				.build());
		
		return ResponseEntity.ok().build();
	}
}
