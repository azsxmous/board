package com.example.jpa.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.jpa.notice.entity.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long>{
	
}
