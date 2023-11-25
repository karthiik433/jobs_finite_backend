package com.jobsPortal.jobs_finite.Repository.ExamRepository;


import com.jobsPortal.jobs_finite.Entity.CentralGovtSubscriber;
import com.jobsPortal.jobs_finite.Entity.PrivateJobSubscriber;
import com.jobsPortal.jobs_finite.Entity.StateGovtSubscriber;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.centralGovtSubscriberRepository.CentralGovtSubscriberRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.privateJobSubscriberRepository.PrivateJobSubscriberRepo;
import com.jobsPortal.jobs_finite.Repository.ExamRepository.stateGovtSubscriberRepository.StateGovtSubscriberRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AllPostsUnSubscribeService {

    @Autowired
    private CentralGovtSubscriberRepo centralGovtSubscriberRepo;

    @Autowired
    private StateGovtSubscriberRepo stateGovtSubscriberRepo;

    @Autowired
    private PrivateJobSubscriberRepo privateJobSubscriberRepo;



    public void unSubscribe(String email){

        CentralGovtSubscriber centralGovtSubscriber = centralGovtSubscriberRepo.findByEmail(email);
        if(centralGovtSubscriber != null){
            centralGovtSubscriberRepo.deleteById(centralGovtSubscriber.getId());
        }

        StateGovtSubscriber stateGovtSubscriber = stateGovtSubscriberRepo.findByEmail(email);
        if(stateGovtSubscriber != null){
            stateGovtSubscriberRepo.deleteById(stateGovtSubscriber.getId());
        }

        PrivateJobSubscriber privateJobSubscriber = privateJobSubscriberRepo.findByEmail(email);
        if(privateJobSubscriber != null){
            privateJobSubscriberRepo.deleteById(privateJobSubscriber.getId());
        }

        log.info("UnSubscribed to the posts successfully");
    }
}
