package com.example.jpa.notice.model;

import java.util.List;

import lombok.Data;

@Data
public class NoticeDeleteInput {
	private List<Long> idList;
}
