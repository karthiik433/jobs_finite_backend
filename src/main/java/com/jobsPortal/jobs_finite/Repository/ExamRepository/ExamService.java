package com.jobsPortal.jobs_finite.Repository.ExamRepository;


import com.jobsPortal.jobs_finite.Entity.StateGovtExamPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    public StateGovtExamPost saveExam(StateGovtExamPost stateGovtExamPost){

        examRepository.save(stateGovtExamPost);
        return stateGovtExamPost;
    }
}
