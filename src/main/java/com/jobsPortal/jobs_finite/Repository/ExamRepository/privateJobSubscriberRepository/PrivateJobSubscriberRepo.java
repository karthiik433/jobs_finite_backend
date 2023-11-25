package com.jobsPortal.jobs_finite.Repository.ExamRepository.privateJobSubscriberRepository;


import com.jobsPortal.jobs_finite.Entity.PrivateJobSubscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateJobSubscriberRepo extends JpaRepository<PrivateJobSubscriber , Integer> {

    PrivateJobSubscriber findByEmail(String emailId);
}
