package com.example.jpa.notice.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
//과거엔 Getter/Setter 사용. 요즘은 Data 어노테이션으로 가능 --> Lombok의 기능
@Data
public class NoticeModel {
	
	// ID, 제목, 내용, 등록일
	private int id;
	private String title;
	private String contents;
	private LocalDateTime regDate;
	
	// 과거엔 Getter/Setter 사용. 요즘은 어노테이션으로 가능
}
