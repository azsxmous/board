package com.example.jpa.notice.exception;

// RuntimeException을 상속받으면 사용하는 곳에서 따로 throw 해주지 않아도 됨
public class NoticeNotFoundException extends RuntimeException {

	public NoticeNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
