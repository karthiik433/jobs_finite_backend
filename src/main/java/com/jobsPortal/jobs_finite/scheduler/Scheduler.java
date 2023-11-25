package com.jobsPortal.jobs_finite.scheduler;


import com.jobsPortal.jobs_finite.Entity.Admin;
import com.jobsPortal.jobs_finite.Entity.CentralGovtExamPost;
import com.jobsPortal.jobs_finite.Entity.PrivateExamPost;
import com.jobsPortal.jobs_finite.Entity.StateGovtExamPost;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.AdminRepository.AdminRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.CentralGovtRepository.CentralGovtRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.privateExamrepository.PrivateExamPostRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.stateGovtRepository.StateGovtRepo;
import com.jobsPortal.jobs_finite.processor.PasswordGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
@Slf4j
public class Scheduler {

    @Autowired
    private CentralGovtRepo centralGovtRepo;

    @Autowired
    private StateGovtRepo stateGovtRepo;

    @Autowired
    private PrivateExamPostRepo privateExamPostRepo;

    @Autowired
    private PasswordGenerator passwordGenerator;

    @Autowired
    private AdminRepo adminRepo;

    @Scheduled(cron = "0 0/5 * * * *")
    public void generatePassword(){

        log.info("########### Scheduler job started for password generation ##############");

        String password = passwordGenerator.generatePassword();
        log.info("Generated password "+password);

        List<Admin> adminList = adminRepo.findAll();

        adminList.stream().forEach(item->{
            item.setPassword(password);
            adminRepo.save(item);
        });

        log.info("########### Admin passwords updated ##############");


    }

//    @Scheduled(cron = "0 0/5 * * * *" )
    @Scheduled(cron = "0 0 12 ? * *")
    public void testScheduling(){

        log.info("########### Scheduler job started ##############");

        Date today = new Date();

        List<CentralGovtExamPost> centralGovtExamPostList = centralGovtRepo.findAll();
        DeleteOutDatedCentralGovtPost(centralGovtExamPostList,today);

        List<StateGovtExamPost> stateGovtExamPostList = stateGovtRepo.findAll();
        DeleteOutDatedStateGovtPost(stateGovtExamPostList,today);

        List<PrivateExamPost> privateExamPostList = privateExamPostRepo.findAll();
        DeleteOutDatedPrivatePosts(privateExamPostList,today);

        log.info("########### Scheduler job finished ##############");
    }

    private void DeleteOutDatedCentralGovtPost(List<CentralGovtExamPost> centralGovtExamPostList,Date today){

        centralGovtExamPostList.stream().forEach(post->{

            if(post.getPostLastDate().getTime() - today.getTime() < 0 ){

                centralGovtRepo.delete(post);
            }
        });
        log.info("Executed the job of deleting out dated Central Government job posts");
    }

    private void DeleteOutDatedStateGovtPost(List<StateGovtExamPost> stateGovtExamPostList,Date today){

        stateGovtExamPostList.stream().forEach(post->{

            if(post.getPostLastDate().getTime() - today.getTime() < 0){
                stateGovtRepo.delete(post);
            }
        });
        log.info("Executed the job of deleting out dated State Government job posts");
    }

    private void DeleteOutDatedPrivatePosts(List<PrivateExamPost> privateExamPostList,Date today){
        privateExamPostList.stream().forEach(post->{

            if(post.getPostLastDate().getTime() - today.getTime() < 0){
                privateExamPostRepo.delete(post);
            }
        });
        log.info("Executed the job of deleting out dated private job posts");
    }
}
