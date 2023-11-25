package com.jobsPortal.jobs_finite.Repository.ExamRepository.FeedBackRepository;


import com.jobsPortal.jobs_finite.Entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepo extends JpaRepository<Feedback,Integer> {


}
