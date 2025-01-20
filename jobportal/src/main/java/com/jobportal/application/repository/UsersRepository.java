package com.jobportal.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.application.entities.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
	 Optional<Users> findByEmail(String email);
}
