package com.jobportal.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.application.entities.UsersType;

public interface UsersTypeRepository extends JpaRepository<UsersType, Integer> {
}
