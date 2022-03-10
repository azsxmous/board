package com.example.jpa.notice.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.notice.model.NoticeModel;

@RestController
public class ApiNoticeController {
	
	
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
}
