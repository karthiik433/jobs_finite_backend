package com.jobsPortal.jobs_finite.Repository.ExamRepository.stateGovtRepository;


import com.jobsPortal.jobs_finite.Entity.StateGovtExamPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateGovtExamServiceImpl {

    @Autowired
    private StateGovtRepo stateGovtRepo;




    public List<StateGovtExamPost> fetchAllStateGovtExamPosts(){
        return stateGovtRepo.findAll();
    }
}
