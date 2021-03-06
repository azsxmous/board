package com.example.jpa.user.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpa.user.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	int countByEmail(String email);
	
	Optional<User> findByIdAndPassword(Long id, String password);
	
	Optional<User> findByUserNameAndPhone(String userName, String Phone);
}
