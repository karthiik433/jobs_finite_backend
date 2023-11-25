package com.jobsPortal.jobs_finite.Repository.ExamRepository.centralGovtSubscriberRepository;


import com.jobsPortal.jobs_finite.Entity.CentralGovtSubscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentralGovtSubscriberRepo extends JpaRepository<CentralGovtSubscriber,Integer> {

    CentralGovtSubscriber findByEmail(String emailId);
}
