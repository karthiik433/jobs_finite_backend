package com.jobsPortal.jobs_finite.Repository.ExamRepository.CentralGovtRepository;

import com.jobsPortal.jobs_finite.Entity.CentralGovtExamPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentralGovtRepo extends JpaRepository<CentralGovtExamPost,Integer> {

    public List<CentralGovtExamPost> findAllByExamName(String examName);
    public List<CentralGovtExamPost> findAll();
    public CentralGovtExamPost findById(int id);
}
