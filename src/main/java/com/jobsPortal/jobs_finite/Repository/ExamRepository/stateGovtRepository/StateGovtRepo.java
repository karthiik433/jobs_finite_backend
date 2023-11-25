package com.jobsPortal.jobs_finite.Repository.ExamRepository.stateGovtRepository;


import com.jobsPortal.jobs_finite.Entity.StateGovtExamPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateGovtRepo extends JpaRepository<StateGovtExamPost,Integer> {

    public List<StateGovtExamPost> findAll();
    StateGovtExamPost findById(int id);

}
