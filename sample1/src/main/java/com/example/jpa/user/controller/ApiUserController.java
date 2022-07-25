package com.example.jpa.user.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.notice.entity.Notice;
import com.example.jpa.notice.model.NoticeResponse;
import com.example.jpa.notice.model.ResponseError;
import com.example.jpa.notice.repository.NoticeRepository;
import com.example.jpa.user.entity.User;
import com.example.jpa.user.exception.ExistsEmailExcetion;
import com.example.jpa.user.exception.PasswordNotMatchException;
import com.example.jpa.user.exception.UserNotFoundException;
import com.example.jpa.user.model.UserInput;
import com.example.jpa.user.model.UserInputFind;
import com.example.jpa.user.model.UserInputPassword;
import com.example.jpa.user.model.UserResponse;
import com.example.jpa.user.model.UserUpdate;
import com.example.jpa.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ApiUserController {
	
	private final UserRepository userRepository;
	private final NoticeRepository noticeRepository;
	private ObjectError FieldError;
	
	@PostMapping("/api/user")
	public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {
		
		List<ResponseError> responseErrorList = new ArrayList<>();
		if(errors.hasErrors()) {
			errors.getAllErrors().forEach((e) -> {
				responseErrorList.add(ResponseError.of((FieldError)e));
			});
			return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.ok().build();
		// == return new ResponseEntity<>(HttpStatus.OK);
	}
	// {"email":"abc@now.com", "userName":"홍길동", "password":"1234", "phone":"010-111-2222"}
	@PostMapping("/api/user2")
	public ResponseEntity<?> addUser2(@RequestBody @Valid UserInput userInput, Errors errors) {
		
		List<ResponseError> responseErrorList = new ArrayList<>();
		if(errors.hasErrors()) {
			errors.getAllErrors().forEach((e) -> {
				responseErrorList.add(ResponseError.of((FieldError)e));
			});
			return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
		}
		
		User user = User.builder()
				.email(userInput.getEmail())
				.userName(userInput.getUserName())
				.password(userInput.getPassword())
				.phone(userInput.getPhone())
				.regDate(LocalDateTime.now())
				.build();
		
		userRepository.save(user);
		
		return ResponseEntity.ok().build();
		// == return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// localhost:8080/api/user/1
	// {"phone":"010-1212-3434"}
	@PutMapping("/api/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdate userUpdate, Errors errors) {
		System.out.println("userID : " + id);
		List<ResponseError> responseErrorList = new ArrayList<>();
		if(errors.hasErrors()) {
			errors.getAllErrors().forEach((e) -> {
				responseErrorList.add(ResponseError.of((FieldError)e));
			});
			
			return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
		}
		
		User user = userRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
		
		user.setPhone(userUpdate.getPhone());
		user.setUpdateDate(LocalDateTime.now());
		
		userRepository.save(user);
		
		return ResponseEntity.ok().build();
		// == return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> UserNotFoundExceptionHandler(UserNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	// localhost:8080/api/user/1
	@GetMapping("/api/user/{id}")
	public UserResponse getUser(@PathVariable Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
		
		// UserResponse userResponse = new UserResponse(user);
		UserResponse userResponse = UserResponse.of(user);
		
		return userResponse;
	}
	
	// localhost:8080/api/user/1/notice
	@GetMapping("/api/user/{id}/notice")
	public List<NoticeResponse> userNotice(@PathVariable Long id) {
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
		
		List<Notice> noticeList = noticeRepository.findByUser(user);
		
		List<NoticeResponse> noticeResponseList = new ArrayList<>();
		
		noticeList.stream().forEach((e) -> {
			noticeResponseList.add(NoticeResponse.of(e));
		});
		
		return noticeResponseList;
	}

	// localhost:8080/api/user3
	// {"email":"abc@now.com", "userName":"홍길동", "password":"1234", "phone":"010-111-2222"}
	@PostMapping("/api/user3")
	public ResponseEntity<?> addUser3(@RequestBody @Valid UserInput userInput, Errors errors) {
		
		List<ResponseError> responseErrorList = new ArrayList<>();
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach((e) -> {
				responseErrorList.add(ResponseError.of((FieldError)e));
			});
			return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
		}
		
		if(userRepository.countByEmail(userInput.getEmail()) > 0) {
			throw new ExistsEmailExcetion("이미 존재하는 이메일입니다.");
		}
		

		User user = User.builder()
				.email(userInput.getEmail())
				.userName(userInput.getUserName())
				.password(userInput.getPassword())
				.phone(userInput.getPhone())
				.regDate(LocalDateTime.now())
				.build();
		
		userRepository.save(user);
		
		return ResponseEntity.ok().build();
		// return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ExceptionHandler(value = {ExistsEmailExcetion.class, PasswordNotMatchException.class})
	public ResponseEntity<?> ExistsEmailExceptionHandler(RuntimeException exception) {
	
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	// localhost:8080/api/user/4/password
	// { "password":"4444", "newPassword" : "2222"}
	@PatchMapping("/api/user/{id}/password")
	public ResponseEntity<?> updateUserPassword(@PathVariable Long id, @RequestBody UserInputPassword userInputPassword, Errors errors) {
		
		List<ResponseError> responseErrorList = new ArrayList<>();
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach((e) -> {
				responseErrorList.add(ResponseError.of((FieldError)e));
			});
			return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
		}
		
		User user = userRepository.findByIdAndPassword(id, userInputPassword.getPassword())
				.orElseThrow(() -> new PasswordNotMatchException("비밀번호가 일치하지 않습니다."));
		
		user.setPassword(userInputPassword.getNewPassword());
		
		userRepository.save(user);
		
		return ResponseEntity.ok().build();
		
	}
	
	// 비밀번호 암호화 함수
	private String getEncryptPassword(String password) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(password);
	}
	// localhost:8080/api/user4
	// {"email":"abc@now.com", "userName":"홍길동", "password":"1234", "phone":"010-111-2222"}
	@PostMapping("/api/user4")
	public ResponseEntity<?> addUser4(@RequestBody @Valid UserInput userInput, Errors errors){
		
		List<ResponseError> responseErrorList = new ArrayList<>();
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach((e) -> {
				responseErrorList.add(ResponseError.of((FieldError)e));
			});
			return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
		}

		if(userRepository.countByEmail(userInput.getEmail()) > 0) {
			throw new ExistsEmailExcetion("이미 존재하는 이메일입니다.");
		}
		
		// 비밀번호 암호화
		String encryptPassword = getEncryptPassword(userInput.getPassword());
		
		User user = User.builder()
				.email(userInput.getEmail())
				.userName(userInput.getUserName())
				.password(encryptPassword)
				.phone(userInput.getPhone())
				.regDate(LocalDateTime.now())
				.build();
		
		userRepository.save(user);
		
		return ResponseEntity.ok().build();
	}
	
	// localhost:8080/api/user/1
	@DeleteMapping("/api/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

		try {
			userRepository.delete(user);
		} catch (DataIntegrityViolationException e) {
			String message = "제약조건에 문제가 발생하였습니다.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			String massage = "회원탈퇴 중 문제가 발생하였습니다.";
			return new ResponseEntity<>(massage, HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.ok().build();
	}
	
	// localhost:8080/api/user
	@GetMapping("/api/user")
	public ResponseEntity<?> findUser(@RequestBody UserInputFind userInputFind) {
		User user = userRepository.findByUserNameAndPhone(userInputFind.getUserName(), userInputFind.getPhone())
				.orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
		
		UserResponse userResponse = UserResponse.of(user);
		
		return ResponseEntity.ok().body(userResponse);
	}
	
	// localhost:8080/api/user/1/password/reset
	@GetMapping("/api/user/{id}/password/reset")
	public ResponseEntity<?> resetUserPassword(@PathVariable Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
		// 비밀번호 초기화
		String resetPassword = getResetPassword();
		user.setPassword(resetPassword);
		userRepository.save(user);
		
		// 문자메시지 전송
		String message = String.format("[%s]님의 임시비밀번호가 [%s]로 초기화 되었습니다." , user.getUserName(), resetPassword);
		sendSMS(message);
		
		return ResponseEntity.ok().build();
	}
	
	private String getResetPassword() {
		return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
	}
	
	void sendSMS(String message) {
		System.out.println("[문자메시지전송]");
		System.out.println(message);
	}
}
