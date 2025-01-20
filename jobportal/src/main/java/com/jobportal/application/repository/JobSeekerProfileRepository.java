package com.jobportal.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.application.entities.JobSeekerProfile;

public interface JobSeekerProfileRepository extends JpaRepository<JobSeekerProfile, Integer> {

}
