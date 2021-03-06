package com.example.jpa.notice.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.jpa.notice.entity.Notice;
import com.example.jpa.user.entity.User;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long>{
	
	Optional<List<Notice>> findByIdIn(List<Long> idList);
	
	Optional<List<Notice>> findByTitleAndContentsAndRegDateIsGreaterThanEqual(String title, String contents, LocalDateTime regDate);
	
	int countByTitleAndContentsAndRegDateIsGreaterThanEqual(String title, String contents, LocalDateTime regDate);
	
	List<Notice> findByUser(User user);
}
