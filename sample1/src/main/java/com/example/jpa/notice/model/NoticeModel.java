package com.example.jpa.notice.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
//���ſ� Getter/Setter ���. ������ Data ������̼����� ���� --> Lombok�� ���
@Data
public class NoticeModel {
	
	// ID, ����, ����, �����
	private int id;
	private String title;
	private String contents;
	private LocalDateTime regDate;
	
	// ���ſ� Getter/Setter ���. ������ ������̼����� ����
}
