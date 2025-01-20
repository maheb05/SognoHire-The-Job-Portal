package com.jobportal.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobportal.application.entities.JobPostActivity;
import com.jobportal.application.entities.JobSeekerProfile;
import com.jobportal.application.entities.JobSeekerSave;
import com.jobportal.application.repository.JobSeekerSaveRepository;

@Service
public class JobSeekerSaveService {

    private final JobSeekerSaveRepository jobSeekerSaveRepository;

    public JobSeekerSaveService(JobSeekerSaveRepository jobSeekerSaveRepository) {
        this.jobSeekerSaveRepository = jobSeekerSaveRepository;
    }

    public List<JobSeekerSave> getCandidatesJob(JobSeekerProfile userAccountId) {
        return jobSeekerSaveRepository.findByUserId(userAccountId);
    }

    public List<JobSeekerSave> getJobCandidates(JobPostActivity job) {
        return jobSeekerSaveRepository.findByJob(job);
    }

    public void addNew(JobSeekerSave jobSeekerSave) {
        jobSeekerSaveRepository.save(jobSeekerSave);
    }
}
