package com.example.jpa.notice.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.jpa.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
public class Notice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn
	private User user;
	
	@Column
	private String title;
	
	@Column
	private String contents;
	
	@Column
	private LocalDateTime regDate;
	
	@Column
	private LocalDateTime updateDate;
	
	@Column
	private int hits;
	
	@Column
	private int likes;
	
	@Column
	private boolean deleted;
	
	@Column
	private LocalDateTime deletedDate;
}
