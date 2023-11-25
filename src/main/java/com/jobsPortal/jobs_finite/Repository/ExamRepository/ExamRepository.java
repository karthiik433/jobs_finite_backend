package com.jobsPortal.jobs_finite.Repository.ExamRepository;

import com.jobsPortal.jobs_finite.Entity.StateGovtExamPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<StateGovtExamPost,Integer> {

}
