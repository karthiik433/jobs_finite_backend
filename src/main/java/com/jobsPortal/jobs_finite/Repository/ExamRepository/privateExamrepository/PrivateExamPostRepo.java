package com.jobsPortal.jobs_finite.Repository.ExamRepository.privateExamrepository;

import com.jobsPortal.jobs_finite.Entity.PrivateExamPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivateExamPostRepo extends JpaRepository<PrivateExamPost ,Integer> {

    public List<PrivateExamPost> findAll();
    PrivateExamPost findById(int id);
}
