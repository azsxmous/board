package com.example.jpa.notice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.jpa.notice.entity.NoticeLike;
import com.example.jpa.user.entity.User;

@Repository
public interface NoticeLikeRepository extends JpaRepository<NoticeLike, Long>{
	
	List<NoticeLike> findByUser(User user);
	
}
