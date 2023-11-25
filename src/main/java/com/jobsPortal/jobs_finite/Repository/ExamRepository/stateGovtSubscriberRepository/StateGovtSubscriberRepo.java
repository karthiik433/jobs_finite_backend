package com.jobsPortal.jobs_finite.Repository.ExamRepository.stateGovtSubscriberRepository;


import com.jobsPortal.jobs_finite.Entity.StateGovtSubscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateGovtSubscriberRepo extends JpaRepository<StateGovtSubscriber , Integer> {

    StateGovtSubscriber findByEmail(String emailId);

    List<StateGovtSubscriber> findAllByState(String state);
}
